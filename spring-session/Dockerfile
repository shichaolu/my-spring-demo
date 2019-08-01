FROM java:8
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /my-spring-session-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/my-spring-session-demo-0.0.1-SNAPSHOT.jar"]