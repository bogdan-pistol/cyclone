FROM adoptopenjdk/openjdk8:jdk8u202-b08-alpine-slim
COPY target/*.jar /server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/server.jar"]
