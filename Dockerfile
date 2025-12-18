# Етап 1: Збірка (на базі Ubuntu + Java 21)
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Копіюємо всі файли проекту
COPY . .

# 1. Встановлюємо утиліту для виправлення Windows-символів
RUN apt-get update && apt-get install -y dos2unix

# 2. Виправляємо файл gradlew і даємо права на запуск
RUN dos2unix gradlew
RUN chmod +x gradlew

# 3. Запускаємо збірку через wrapper (найбезпечніший метод)
RUN ./gradlew bootJar --no-daemon -x test

# Етап 2: Запуск готового додатку
FROM eclipse-temurin:21-jre-jammy
EXPOSE 8080
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]