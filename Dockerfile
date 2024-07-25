# builder image
FROM amazoncorretto:17-al2-jdk AS builder

ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

RUN mkdir /flow-auth
WORKDIR /flow-auth

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean bootJar

# runtime image
FROM amazoncorretto:17.0.12-al2

ENV TZ=Asia/Seoul
ENV PROFILE=${PROFILE}
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

RUN mkdir /flow-auth
WORKDIR /flow-auth

COPY --from=builder /flow-auth/build/libs/flow-auth-* /flow-auth/app.jar

CMD ["java", \
    "-Dspring.profiles.active=${PROFILE}", \
    "-Dspring.datasource.url=${DB_URL}", \
    "-Dspring.datasource.username=${DB_USERNAME}", \
    "-Dspring.datasource.password=${DB_PASSWORD}", \
    "-jar", \
    "/flow-auth/app.jar"]
