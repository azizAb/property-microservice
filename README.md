# Property Microservices

A microservices architecture project containing three independent services that communicate via REST APIs. This project demonstrates modern microservices patterns including service-to-service communication, reactive programming with Spring WebFlux, and containerization with Docker.

## 🏗️ Architecture

The application consists of three microservices:

1. **User Service** (Port 8081) - Java/Spring Boot
   - Manages user information and authentication
   - Built with Spring Boot and Maven

2. **Listing Service** (Port 6001) - Python
   - Handles property listings and related operations
   - RESTful API built with Python

3. **Public API** (Port 8082) - Java/Spring Boot
   - Gateway/aggregation layer that combines data from User and Listing services
   - Handles service orchestration and data composition

## 📋 Prerequisites

- **Java**: JDK 21 or higher
- **Python**: 3.11 or higher
- **Maven**: 3.9+
- **Docker Desktop**: Latest version 
- **Docker Compose**: Latest version (Optional if docker desktop not installed)
- **Colima** (for macOS users) (Optional if docker desktop not installed)

## 🛠️ Technology Stack

### User Service
- Spring Boot 3.x
- Java 21
- Maven
- Eclipse Temurin JRE

### Listing Service
- Python 3.11
- Flask
- Slim Docker base image

### Public API
- Spring Boot 3.x (WebFlux)
- Java 21
- Project Reactor (Mono/Flux)
- Maven
- Eclipse Temurin JRE

## 📦 Installation

### Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone https://github.com/azizAb/property-microservice.git
   cd property-microservice
   ```

2. **Start Colima (macOS users) (if docker desktop not installed)**
   ```bash
   colima start
   ```

3. **Build and run with Docker Compose**
   ```bash
   docker-compose up -d
   ```

4. **Verify services are running**
   ```bash
   docker-compose ps
   ```

