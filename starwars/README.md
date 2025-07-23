# Star Wars Spring Boot App

This is a Spring Boot application that displays Star Wars data (People and Starships) using the SWAPI API.  
It supports sorting by name and creation date (both ascending and descending), strictly on the backend (no JavaScript sorting).

---

## ✅ Features

- Spring Boot with Thymeleaf
- Sorting (Open-Closed Principle)
- People and Starships view
- Includes a home page for user-friendly navigation
- Integration with SWAPI API
- Backend-only sorting 
- Dockerized on port `6969` includes `Dockerfile` and `docker-compose.yaml`.
- Optional but working: Kubernetes deployment as a service (`.yaml` files provided).

---

## 🚀 Requirements

- Docker & Docker Compose installed and running
- Java 17+ (for building locally)
- Gradle (prebuilt JAR), I used Gradle 8.14.3

---

## 🛠️ Build & Run Instructions

### 🔧 Build with Gradle
1. Clean and build the project
```bash                  
  gradle clean build                                     
```

2. Build the Docker image:

```bash
  docker build -t starwars-app .
```
  The compiled .jar will be located in:

```bash
  build/libs/starwars-0.0.1-SNAPSHOT.jar
```

3. Execute the jar
```bash
  java -jar build/libs/starwars-0.0.1-SNAPSHOT.jar
```
  or manually

```bash
  docker run starwars-app
```

## 🐳 How to Run With Docker

1. Open starwars project with cmd 


2. Build the JAR & run:
```bash
   docker-compose up --build
```
2. Visit: http://localhost:6969



