# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=1.0.0
ARG APP_USERNAME=james
ARG APP_PASSWORD=bond123

# Define another things
WORKDIR /app
COPY --from=build /build/target/employee_management-*.war /app/

EXPOSE 8888

ENV DB_URL=jdbc:h2:mem:employee
ENV ACTIVE_PROFILE=${PROFILE}
ENV WAR_VERSION=${APP_VERSION}
ENV WAR_USERNAME=${APP_USERNAME}
ENV WAR_PASSWORD=${APP_PASSWORD}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} -Dapplication.admin.default.username=${WAR_USERNAME} -Dapplication.admin.default.password=${WAR_PASSWORD} employee_management-${WAR_VERSION}.war
