# Demo application with Spring Boot and Open Policy Agent

In this example you can see an example of how to use Open Policy Agent  (running as local Docker container) together
with Spring Security to secure the endpoint on `localhost:8080/demo/secured`.

In the same Controller there is also another endpoint (`localhost:8080/demo/unsecured`). This endpoint is reachable
without any authentication or authorization.

## Configuration of Open Policy Agent (OPA)

The OPA server is configured via the `policy.rego` file in the docker directory. All users that are configured are
located in `users.json` also in the same directory.# Spring Boot OPA

``curl -X PUT --data-binary @policy.rego  localhost:8181/v1/policies/authz``
``curl -X PUT -H "Content-Type: application/json" -d @data.json localhost:8181/v1/data/users``
