# Verwende ein offizielles Java-Image als Basis
FROM openjdk:25-jdk-slim

# Setze den Arbeitsordner im Container
WORKDIR /app

# Kopiere die kompilierten JAR-Dateien aus dem Build-Verzeichnis in das Container-Verzeichnis
COPY build/libs/ecommerce-system-0.0.1-SNAPSHOT.jar ecommerce-system.jar

# Exponiere den Port, auf dem die Anwendung läuft (Standardmäßig Spring Boot: 8080)
EXPOSE 8080

# Befehl zum Starten der Spring Boot-Anwendung
ENTRYPOINT ["java", "-jar", "ecommerce-system.jar"]