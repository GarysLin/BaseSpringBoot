#FROM maven:3.5.2-jdk-8-alpine AS build
#ARG env
#COPY pom.xml /build/
#COPY src /build/src/
#COPY src/main/resources/*.${env}.yml /build/src/main/resources/application.yml
#WORKDIR /build/
#RUN mvn package

FROM gradle:6.6-jdk11 AS build
ARG ENV
WORKDIR /gradle
COPY . .
#RUN gradle build --no-daemon
# 跳過test
RUN gradle build -x test --no-daemon

FROM openjdk:11-jdk
WORKDIR /app
#COPY --from=build /build/target/*.jar /app/springbootapi.jar
COPY --from=build /gradle/build/libs/*.war /app/springbootapi.war
ENTRYPOINT ["java","-jar","springbootapi.war"]
