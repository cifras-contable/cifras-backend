From openjdk:17
copy ./target/cifras-backend-0.0.1-SNAPSHOT.jar cifras-backend-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","employee-producer-0.0.1-SNAPSHOT.jar"]