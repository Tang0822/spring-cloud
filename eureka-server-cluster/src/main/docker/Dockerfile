FROM java:8
VOLUME /temp
ADD eureka-server-cluster.jar /app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
#EXPOSE 7003
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=peer3", "-jar","/app.jar"]
