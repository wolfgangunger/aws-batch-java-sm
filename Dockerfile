FROM amazoncorretto:8
RUN yum -y install which unzip aws-cli
COPY ./target/Batch-Service-0.0.1-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
USER nobody
ENTRYPOINT ["java", "-jar", "Batch-Service-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]


