FROM maven:3.6.0-jdk-11-slim AS build

ARG VERSION

LABEL name="chat-client"

LABEL vesrion=VERSION

WORKDIR /app

COPY pom.xml ./

COPY src ./src

RUN mvn -f pom.xml clean

RUN mvn package

FROM openjdk:11-jre-slim

COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar ./SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "SNAPSHOT.jar"]