FROM java:8
VOLUME /temp
ADD customer.jar /app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
#EXPOSE 6002
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=customer1", "-jar","/app.jar"]
