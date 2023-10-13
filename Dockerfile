FROM bitnami/wildfly:29.0.1
COPY maven-artifact /app
ENV WILDFLY_USERNAME=user, WILDFLY_PASSWORD=password