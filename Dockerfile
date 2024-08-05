# builder image
FROM amazoncorretto:17-al2-jdk AS builder

RUN mkdir /flow-main
WORKDIR /flow-main

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean bootJar

# runtime image
FROM amazoncorretto:17.0.12-al2

ENV TZ=Asia/Seoul
ENV PROFILE=${PROFILE}

RUN mkdir /flow-main
WORKDIR /flow-main

COPY --from=builder /flow-main/build/libs/flow-main-* /flow-main/app.jar

CMD ["sh", "-c", " \
    java -Dspring.profiles.active=${PROFILE} \
         -jar /flow-main/app.jar"]
