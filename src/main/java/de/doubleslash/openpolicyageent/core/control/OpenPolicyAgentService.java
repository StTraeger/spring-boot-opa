package de.doubleslash.openpolicyageent.core.control;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.doubleslash.openpolicyageent.core.entity.OpaInput;

@Service
public class OpenPolicyAgentService {

    private final RestTemplate restTemplate;

    public OpenPolicyAgentService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String checkCustomPolicy(final String input) {
        final String url = "http://localhost:8181/v1/data/httpapi/authz";
        final HttpEntity<String> request = new HttpEntity<>(input);
        final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        return response.getBody();
    }

    public boolean checkPolicy(final String method, final String username, final String breweryName) {
        final String url = "http://localhost:8181/v1/data/httpapi/authz";
        final HttpEntity<OpaInput> request = new HttpEntity<>(createInput(method, username, breweryName));
        final ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.POST, request, Boolean.class);
        return response.getBody();
    }

    private OpaInput createInput(final String method, final String username, final String breweryname) {
        final OpaInput input = new OpaInput();
        input.setMethod(method);
        input.setUser(username);
        input.setPath(breweryname);
        return input;
    }

}
