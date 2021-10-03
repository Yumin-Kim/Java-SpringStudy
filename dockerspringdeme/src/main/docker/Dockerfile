FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT java  -DSPRING_DB_URL=dockerspringdeme_database_1 -jar ./app.jar
