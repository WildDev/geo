FROM maven:3.9.4-eclipse-temurin-20

MAINTAINER https://github.com/WildDev

COPY . /
RUN --mount=type=cache,target=$MAVEN_CONFIG mvn clean package

FROM eclipse-temurin:20-jre

ENV ALLOWED_ORIGINS=http://localhost
ENV JAVA_OPTS=-Xmx256M
ENV INTEGRATION_IPSTACK_URL=http://api.ipstack.com
ENV INTEGRATION_IPSTACK_ACCESS_KEY=ninja
ENV MONGODB_HOST=mongodb
ENV MONGODB_PORT=27017
ENV MONGODB_DATABASE=geo
ENV MONGODB_USER=geo
ENV MONGODB_PASS=test
ENV MONGODB_URI=''
ENV SERVER_PORT=8080

COPY --from=0 /target/geo.jar /

ENTRYPOINT java -jar $JAVA_OPTS /geo.jar \
 --integration.ipstack.url=$INTEGRATION_IPSTACK_URL \
 --integration.ipstack.access-key=$INTEGRATION_IPSTACK_ACCESS_KEY \
 --server.port=$SERVER_PORT \
 --spring.cors.allowed-origins=$ALLOWED_ORIGINS \
 --spring.data.mongodb.host=$MONGODB_HOST \
 --spring.data.mongodb.port=$MONGODB_PORT \
 --spring.data.mongodb.database=$MONGODB_DATABASE \
 --spring.data.mongodb.username=$MONGODB_USER \
 --spring.data.mongodb.password=$MONGODB_PASS \
 --spring.data.mongodb.uri=$MONGODB_URI \
$EXTRA_ARGS
