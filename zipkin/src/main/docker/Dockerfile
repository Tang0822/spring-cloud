FROM java:8
VOLUME /temp
ADD zipkin.jar /app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
#EXPOSE 8004
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=default", "-jar","/app.jar"]
