FROM openjdk:11
COPY target/chrisflix-0.0.1-SNAPSHOT.jar chrisflix-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/chrisflix-0.0.1-SNAPSHOT.jar"]