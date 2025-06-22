FROM maven:3.8.8-eclipse-temurin-21 AS builder
WORKDIR /workspace

COPY . .

RUN mvn clean package -pl customer-service -am -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /workspace/customer-service/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app/app.jar"]
