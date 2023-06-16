package com.example.springbootopa.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoResource {

    @Operation(summary = "Requests unsecured content")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Content received",
            content = { @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }) })
    @GetMapping(value = "/unsecured", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> unsecuredEndpoint() {
        return ResponseEntity.ok("This is an unsecured endpoint!");
    }

    @Operation(summary = "Requests secured content")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Secured Content received",
            content = { @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "403", description = "User is not allowed to receive secured content",
                    content = @Content) })
    @GetMapping(value = "/secured", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> securedEndpoint() {

        return ResponseEntity.ok("This is a secured endpoint!");
    }
}
