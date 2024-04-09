#FROM eclipse-temurin:17
FROM amazoncorretto:17
#VOLUME /tmp
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} adm-azs-shipping.jar
#RUN bash -c 'touch adm-azs-shipping.jar'
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom","-jar","/adm-azs-shipping.jar"]