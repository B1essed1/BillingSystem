FROM amazoncorretto:17
ADD target/*.jar BillingSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java" , "-jar", "/BillingSystem-0.0.1-SNAPSHOT.jar"]
EXPOSE 8091
