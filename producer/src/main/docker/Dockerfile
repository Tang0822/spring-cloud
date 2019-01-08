FROM java:8
VOLUME /temp
ADD producer.jar /app.jar
EXPOSE 6003
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=local2", "-jar","/app.jar"]
