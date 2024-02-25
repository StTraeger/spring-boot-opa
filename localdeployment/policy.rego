package httpapi.authz

default allow := false

allow {
    input.user == "admin"
}

allow {
    input.method == "GET"
    contains(input.path, input.user)
}

allow {
    input.method == "POST"
    contains(input.path, input.user)
}

allow {
    input.method == "DELETE"
    contains(input.path, input.user)
}

allow {
    input.method == "PUT"
    contains(input.path, input.user)
}

contains(path, user) {
    startswith(path, "/breweries/")
    split_path := split(trim(path, "/"), "/")
    user == split_path[1]
}