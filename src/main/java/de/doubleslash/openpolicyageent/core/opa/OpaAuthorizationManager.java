package de.doubleslash.openpolicyageent.core.opa;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class OpaAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final String URI = "http://localhost:8181/v1/data/httpapi/authz";
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public OpaAuthorizationManager(final ObjectMapper objectMapper, final RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthorizationDecision check(final Supplier<Authentication> authentication,
            final RequestAuthorizationContext requestAuthorizationContext) {

        final HttpServletRequest request = requestAuthorizationContext.getRequest();

        final String user = authentication.get().getName();
        final String method = request.getMethod();
        final String path = request.getRequestURI();

        final Map<String, Object> input = Map.of(
                "user", user,
                "method", method,
                "path", path
        );

        final ObjectNode requestNode = objectMapper.createObjectNode();
        requestNode.set("input", objectMapper.valueToTree(input));

        final JsonNode responseNode =
                Objects.requireNonNull(restTemplate.postForObject(URI, requestNode, JsonNode.class));

        return new AuthorizationDecision(responseNode.has("result")
                && responseNode.get("result").has("allow")
                && responseNode.get("result").get("allow").asBoolean());
    }

}
