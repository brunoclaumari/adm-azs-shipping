FROM amazoncorretto:17
#VOLUME /tmp
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} adm-azs-shipping.jar
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom","-jar","/adm-azs-shipping.jar"]