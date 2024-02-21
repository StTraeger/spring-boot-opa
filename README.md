# Open Policy Agent together with a Spring Boot API

With this example we want to show you how easy you can integrate Open Policy Agent in your Spring Boot project. This demo projects consists of 2 APIs:
- Beer API: The Beer API is the main API of this project. It is responsible for serving beer data. The API is secured with OPA.
- OPA API: The OPA API can be used to test statements directly against the OPA server. It is not secured with OPA.

## Endpoints

The following endpoints are available under /breverys/{brewery}:
- GET /beers: Returns all beers of the brewery
- GET /beers/{beerId}: Returns a specific beer of the brewery
- POST /beers: Creates a new beer for the brewery
- PUT /beers/{beerId}: Updates a specific beer of the brewery
- DELETE /beers/{beerId}: Deletes a specific beer of the brewery

## Local deployment

Within the localdeployment you can find a docker-compose file which can be used to start the project locally. The docker-compose file starts the following services:
- Open Policy Agent with loaded policies
- Postgres Database with the beer data

Just run the following command to start the docker-compose file:
```bash 
docker compose up
```

## User management

To keep it simple, we have in-memory users. The following users are available (syntax: username:password):
- tegernseer:test1234
- augustiner:test1234
- floetzinger:test1234
- admin:admin

Each brewery user can access its own data. The admin user can access all data.

## Troubleshooting