FROM gradle:8.11.1-jdk17-corretto as builder
LABEL maintainer="jhkim"
WORKDIR /app
COPY settings.gradle build.gradle .
RUN gradle dependencies --no-daemon
COPY src ./src
RUN gradle clean build -x test --no-daemon

FROM amazoncorretto:17-al2-full
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
