# builder image
FROM amazoncorretto:17-al2-jdk AS builder

RUN mkdir /flow-auth
WORKDIR /flow-auth

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean bootJar

# runtime image
FROM amazoncorretto:17.0.12-al2

ENV TZ=Asia/Seoul
ENV PROFILE=${PROFILE}

RUN mkdir /flow-auth
WORKDIR /flow-auth

COPY --from=builder /flow-auth/build/libs/flow-auth-* /flow-auth/app.jar

CMD ["java", \
    "-Dspring.profiles.active=${PROFILE}", \
    "-jar", \
    "/flow-auth/app.jar"]
