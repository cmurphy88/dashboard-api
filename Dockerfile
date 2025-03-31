FROM amazonlinux:2023
RUN yum install -y java-17-amazon-corretto-headless
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]