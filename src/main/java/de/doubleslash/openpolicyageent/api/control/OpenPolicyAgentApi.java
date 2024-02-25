package de.doubleslash.openpolicyageent.api.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.doubleslash.openpolicyageent.core.control.OpenPolicyAgentService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@OpenAPIDefinition(
        info = @Info(title = "Open Policy Agent API", version = "1.0", description = "Demo API for Open Policy Agent"))
@RestController
public class OpenPolicyAgentApi {

    final OpenPolicyAgentService service;

    public OpenPolicyAgentApi(final OpenPolicyAgentService service) {
        this.service = service;
    }

    @Operation(summary = "Checking policies against Open Policy Agent")
    @ApiResponses(value =
    @ApiResponse(responseCode = "200", description = "Always returns 200; Check the body for the real result"))
    @PostMapping(path = "/opa", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> checkPolicy(@RequestBody final String body) {
        return ResponseEntity.ok(service.checkCustomPolicy(body));
    }

}
