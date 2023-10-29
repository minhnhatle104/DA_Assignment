FROM eclipse-temurin:11

LABEL mentainer="lnminh104@gmail.com"

WORKDIR /app

COPY target/lnminh_daassignment-0.0.1-SNAPSHOT.jar /app/lnminh_daassignment.jar

ENTRYPOINT ["java","-jar","lnminh_daassignment.jar"]