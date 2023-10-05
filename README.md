### Description

Service that allows to detect a user location based on it's ip. Currently, is only integrated with [ipstack](https://ipstack.com)

[![Java CI with Maven](https://github.com/WildDev/geo/actions/workflows/maven.yml/badge.svg)](https://github.com/WildDev/geo/actions/workflows/maven.yml) [![Docker Image CI](https://github.com/WildDev/geo/actions/workflows/docker-image.yml/badge.svg)](https://github.com/WildDev/geo/actions/workflows/docker-image.yml)

### How it works

1. Configure proxy to set `X-Real-IP` header
2. Route traffic to the service
3. Send a request

Example:
```cmd
curl "https://test.website/location" -H "X-Real-IP: 127.0.0.1" -v

< HTTP/1.1 200
< Content-Type: application/json
<
{
    "country": {
        "code": "DE",
        "name": "Germany"
    },
    "city": "Dusseldorf"
}
```

### Get started

Build requirements:
* latest JDK and Maven

Runtime stack:
* Java 20+
* API credentials for [ipstack](https://ipstack.com)

Checkout the project and build it using `mvn package` command

An example run:

```cmd
java -jar -Xmx256M target/geo.jar \
    --server.port=8000 \
    --spring.cors.allowed-origins=https://test.website \
    --integration.ipstack.url=https://api.ipstack.com \
    --integration.ipstack.access-key=723953e7b0e27c762c38213b7c71fd77
```

Also available on [Docker Hub](https://hub.docker.com/r/wilddev/geo)
