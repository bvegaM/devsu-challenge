FROM openjdk:16
EXPOSE 8080
COPY build/libs/devsu-challenge-0.0.1-SNAPSHOT.jar devsu-challenge.jar
ENTRYPOINT ["java", "-jar", "/devsu-challenge.jar"]