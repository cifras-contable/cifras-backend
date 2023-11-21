FROM eclipse-temurin:21-jdk-alpine

RUN apk add maven

WORKDIR /opt/app

COPY . .

RUN mvn clean install

CMD ["java", "-jar", "target/cifras.backend-*.jar"]