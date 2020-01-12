FROM openjdk:8-jre-alpine 

LABEL maintainer="nelson.lin@euroka.com.au"

ENV APPLICATION_USER ktor
RUN adduser -D -g '' $APPLICATION_USER

RUN mkdir /app
RUN chown -R $APPLICATION_USER /app

USER $APPLICATION_USER

##RUN ./gradlew --no-daemon jar 

COPY /build/libs/kotlinserver.jar /bin/run.jar
WORKDIR /bin

CMD ["java","-jar","run.jar"]
