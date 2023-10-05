FROM maven:3.9.4-eclipse-temurin-20

MAINTAINER https://github.com/WildDev

COPY . /
RUN --mount=type=cache,target=$MAVEN_CONFIG mvn clean package

FROM eclipse-temurin:20-jre

ENV JAVA_OPTS=-Xmx256M
ENV ALLOWED_ORIGINS=http://localhost
ENV SERVER_PORT=8080
ENV INTEGRATION_IPSTACK_URL=http://api.ipstack.com
ENV INTEGRATION_IPSTACK_ACCESS_KEY=ninja

COPY --from=0 /target/geo.jar /

ENTRYPOINT java -jar $JAVA_OPTS /geo.jar \
 --server.port=$SERVER_PORT \
 --spring.cors.allowed-origins=$ALLOWED_ORIGINS \
 --integration.ipstack.url=$INTEGRATION_IPSTACK_URL \
 --integration.ipstack.access-key=$INTEGRATION_IPSTACK_ACCESS_KEY
