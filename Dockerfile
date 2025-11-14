FROM eclipse-temurin:17-jdk-jammy

EXPOSE 8080

WORKDIR /app
COPY target/SupplyChainX.war /app/SupplyChainX.war

ENTRYPOINT ["java", "-jar", "SupplyChainX.war"]