package httpapi.authz

default allow := false

allow {
    input.method == "GET"
    contains(input.path, input.user)
}

contains(path, user) {
    startswith(path, "/beers/brewery/")
    split_path := split(trim(path, "/"), "/")
    user == split_path[2]
}