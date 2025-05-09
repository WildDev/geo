### Description

Geographic data related service. Currently, is only integrated with [ipstack](https://ipstack.com)

[![Java CI with Maven](https://github.com/WildDev/geo/actions/workflows/maven.yml/badge.svg)](https://github.com/WildDev/geo/actions/workflows/maven.yml) [![Docker Image CI](https://github.com/WildDev/geo/actions/workflows/docker-image.yml/badge.svg)](https://github.com/WildDev/geo/actions/workflows/docker-image.yml)

### How it works

##### Find user location based on its ip
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

##### Search locations manually with the autocomplete

Use `q` request param to search predefined locations by a sample string. The search is performed in `startsWith` fashion on top of a qualifier with `city country` format:

Example
```cmd
curl "https://test.website/location/list?q=Istanbul&20Turk" -v

< HTTP/1.1 200 
< Content-Type: application/json
<
[
    {
        "country": {
            "code": "TR",
            "name": "Turkey"
        },
        "city": "Istanbul"
    },
    {
        "country": {
            "code": "TR",
            "name": "Turkey"
        },
        "city": "Istanbul Old Town"
    },
    {
        "country": {
            "code": "TR",
            "name": "Turkey"
        },
        "city": "Istanbulbogazi"
    }
]
```

> [!WARNING]
> The search is case sensitive

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
