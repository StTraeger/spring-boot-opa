package com.example.springbootopa.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoResource {

    @GetMapping(value = "/unsecured", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> unsecuredEndpoint() {
        return ResponseEntity.ok("This is an unsecured endpoint!");
    }

    @GetMapping(value = "/secured", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> securedEndpoint() {

        return ResponseEntity.ok("This is a secured endpoint!");
    }
}
