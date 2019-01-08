FROM java:8
VOLUME /temp
ADD dashboard.jar /app.jar
EXPOSE 6005
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=default", "-jar","/app.jar"]
