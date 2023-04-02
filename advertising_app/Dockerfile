FROM gradle:7.4.2-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src/applications/app-service
RUN gradle clean bootJar --no-daemon

FROM adoptopenjdk/openjdk8-openj9
VOLUME /tmp
COPY --from=build /home/gradle/src/applications/app-service/build/libs/*.jar advertising.jar
ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar advertising.jar" ]
EXPOSE 8080
