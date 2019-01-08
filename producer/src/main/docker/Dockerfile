FROM java:8
VOLUME /temp
ADD producer.jar /app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
#EXPOSE 6003
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=local2", "-jar","/app.jar"]
