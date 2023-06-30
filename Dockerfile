FROM openjdk:17

WORKDIR /usr/app

copy ./out/artifacts/demo_jar/demo.jar ./

EXPOSE 3001

CMD ["java", "-jar", "demo.jar"]