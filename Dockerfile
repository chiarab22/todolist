FROM maven:3.6.3 AS maven
RUN mvn package
WORKDIR /usr/src/app
COPY . /usr/src/app
FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=todolist-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","todolist-0.0.1-SNAPSHOT.jar"]
