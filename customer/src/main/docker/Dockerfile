FROM java:8
VOLUME /temp
ADD customer.jar /app.jar
EXPOSE 6002
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=customer1", "-jar","/app.jar"]
