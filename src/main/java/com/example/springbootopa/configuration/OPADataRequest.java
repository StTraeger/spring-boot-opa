package com.example.springbootopa.configuration;

import java.util.Map;

public class OPADataRequest {

    Map<String, Object> input;

    public OPADataRequest(Map<String, Object> input) {
        this.input = input;
    }

    @SuppressWarnings("unused")
    public Map<String, Object> getInput() {
        return this.input;
    }
}
