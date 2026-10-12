FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/
COPY pom.xml .
COPY src/ src/

FROM build AS deps
WORKDIR /app

RUN ./mvnw dependency:go-offline -DskipTests

FROM deps AS package
WORKDIR /app

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:25-jre-alpine AS final
WORKDIR /app

RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

COPY --from=package /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
