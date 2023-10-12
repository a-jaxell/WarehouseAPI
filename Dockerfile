FROM eclipse-temurin:21-alpine
LABEL authors="alvarjaxell"
WORKDIR /app
COPY ./target/WarehouseApi-1.0-SNAPSHOT.war .
EXPOSE 8080
ENTRYPOINT ["top", "-b"]

# Commands for creating a builder for multiple architectures and then building them
# docker buildx create --platform linux/arm64/v8,linux/amd64 --use
# docker buildx build --platform linux/arm64/v8,linux/amd64 --tag kappsegla/welcome-to-docker:latest --push .
