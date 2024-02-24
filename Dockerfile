ARG CODE_VERSION=17-alpine3.19
FROM 172.18.0.4:5000/amazoncorretto:${CODE_VERSION}
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]