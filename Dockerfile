# Étape 1 : builder
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

# Compile et renomme le jar
RUN mvn clean package -DskipTests && cp target/testDockerSQL-0.0.1-SNAPSHOT.jar app.jar

# Étape 2 : runtime
FROM eclipse-temurin:17-jdk AS runner
WORKDIR /app

# Copie le .jar renommé
COPY --from=builder /app/app.jar app.jar

# Script d’attente
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["/wait-for-it.sh", "mysql-db:3306", "--", "java", "-jar", "app.jar"]
