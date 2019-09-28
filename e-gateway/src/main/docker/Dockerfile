FROM e_jdk1.8:1.0.0
VOLUME /tmp
ADD e-user-service.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 12000
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]
