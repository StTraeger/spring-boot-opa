package de.doubleslash.openpolicyageent.business.config;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class OpaVoter implements AccessDecisionVoter<FilterInvocation> {

    private static final String URI = "http://localhost:8181/v1/data/authz/allow";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean supports(final ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(final Authentication authentication, final FilterInvocation filterInvocation,
            final Collection<ConfigAttribute> attributes) {

        final String name = authentication.getName();
        final String method = filterInvocation.getRequest().getMethod();
        final String[] path = filterInvocation.getRequest().getRequestURI().replaceAll("^/|/$", "").split("/");

        final Map<String, Object> input = Map.of(
                "name", name,
                "method", method,
                "path", path
        );

        final ObjectNode requestNode = objectMapper.createObjectNode();
        requestNode.set("input", objectMapper.valueToTree(input));

        final JsonNode responseNode =
                Objects.requireNonNull(restTemplate.postForObject(URI, requestNode, JsonNode.class));

        if (responseNode.has("result") && responseNode.get("result").asBoolean()) {
            return ACCESS_GRANTED;
        } else {
            return ACCESS_DENIED;
        }
    }

}
