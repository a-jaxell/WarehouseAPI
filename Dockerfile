FROM bitnami/wildfly:29.0.1
EXPOSE 8080
COPY target/WarehouseApi-1.0-SNAPSHOT.war /app
ENV WILDFLY_USERNAME=user, WILDFLY_PASSWORD=password