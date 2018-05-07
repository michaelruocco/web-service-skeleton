FROM anapsix/alpine-java

ADD build/libs/app*.jar /opt/app.jar

ENV SERVER_PORT 8080
ENV MONGO_HOST localhost
ENV MONGO_PORT 27017
ENV MONGO_DATABASE skeleton_db

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app.jar"]