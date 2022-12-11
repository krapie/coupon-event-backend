FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} coupon-api-server.jar

ENTRYPOINT ["java","-jar","/coupon-api-server.jar"]