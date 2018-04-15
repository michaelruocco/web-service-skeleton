FROM openjdk:8-jdk-alpine

ADD build/libs/app*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]