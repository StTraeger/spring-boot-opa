package kubernetes.admission

import rego.v1

deny contains reason if {
    some container
    input_containers[container]
    not startswith(container.image, "myverifiedregistry.com/")
    reason := "container image refers to illegal registry (must be pulled from myverifiedregistry.com)"
}

input_containers contains container if {
    container := input.request.object.spec.containers[_]
}

input_containers contains container if {
    container := input.request.object.spec.template.spec.containers[_]
}