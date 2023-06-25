FROM openjdk:17-alpine
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} /mbti.jar
ENTRYPOINT ["java", "-jar", "/mbti.jar"]
