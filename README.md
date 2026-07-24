<div align="center">

# 🚀 Short.Mono

### A Production-Ready URL Shortener Platform built with Spring Boot 4 & React

Generate secure short URLs, cache redirects with Redis, manage expiration policies, and deploy seamlessly with Docker.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-green?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?style=for-the-badge)
![Redis](https://img.shields.io/badge/Redis-Upstash-red?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-success?style=for-the-badge)

</div>

---

# 📖 Overview

**Short.Mono** is a modern URL shortening platform designed with scalability and production deployment in mind.

Instead of generating random UUIDs, the application uses **Base62 encoding** to create compact, user-friendly short URLs.

The system supports configurable expiration times, Redis caching for fast redirects, click analytics, automatic cleanup of expired links, and a clean layered architecture following Spring Boot best practices.

---

# ✨ Features

## 🔗 URL Shortening

- Generate unique short URLs using **Base62 encoding**
- Configurable domain
- Collision-free short code generation
- RESTful API

---

## ⏳ Flexible Expiration Policy

Support multiple expiration strategies:

- 15 minutes
- 30 minutes
- 1 hour
- 1 day
- Custom duration
- Never expire

Expired links automatically become inaccessible.

---

## ⚡ High Performance Redirect

- Redis cache (Upstash)
- Database fallback
- Automatic cache refresh
- Low latency redirect

Flow:

```
Browser
      │
      ▼
Short URL
      │
      ▼
Redis Cache
 ┌────────────┐
 │ Hit        │────► Redirect
 └────────────┘
      │
      ▼
Database
      │
      ▼
Save Cache
      │
      ▼
Redirect
```

---

## 📊 Click Analytics

Track total visits for every short URL.

Support batch statistics API.

Example:

```http
POST /api/urls/batch/clicks
```

```json
{
    "shortCodes": [
        "13KpAO",
        "03403G",
        "eEZo39"
    ]
}
```

Response

```json
[
    {
        "shortCode": "13KpAO",
        "clicks": 145
    },
    {
        "shortCode": "03403G",
        "clicks": 52
    }
]
```

---

## 🧹 Automatic Cleanup

A scheduled job periodically removes expired URLs.

- Spring Scheduler
- Configurable Cron Expression

---

## 🖥 Redirect Landing Page

Instead of redirecting immediately, users see a stylish transition page before being redirected.

Expired links display a custom error page.

---

# 🏗 Architecture

```
Client
   │
   ▼
Spring Boot API
   │
   ├────────────► Redis
   │                │
   │                ▼
   │            Cached URL
   │
   ▼
MySQL Database
```

---

# 🏛 Project Structure

```
src
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── service
├── util
├── config
└── scheduler
```

Architecture Principles

- DTO Pattern
- Service Layer
- Repository Pattern
- Global Exception Handler
- Validation
- Dependency Injection
- Clean Code

---

# 🛠 Tech Stack

## Backend

- Java 21
- Spring Boot 4
- Spring Data JPA
- Hibernate
- Maven

## Database

- MySQL

## Cache

- Redis
- Upstash Redis

## Frontend

- React
- Tailwind CSS

## Deployment

- Docker
- Render
- Railway
- Upstash

---

# 📌 API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/shorten` | Create short URL |
| GET | `/{code}` | Redirect to original URL |
| DELETE | `/api/urls/{code}` | Delete short URL |
| POST | `/api/urls/batch/clicks` | Get click statistics |

---

# ⚙ Environment Variables

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=

REDIS_URL=

SALT=
LINK_SIZE=
```

---

# 🚀 Run Locally

Clone project

```bash
git clone https://github.com/yourusername/url-shortener.git
```

Move into project

```bash
cd url-shortener
```

Run

```bash
./mvnw spring-boot:run
```

---

# 🐳 Docker

Build

```bash
docker build -t short-mono .
```

Run

```bash
docker run -p 8080:8080 short-mono
```

---

# 📈 Future Improvements

- User Authentication (JWT)
- User Dashboard
- QR Code Generator
- Custom Alias
- Password Protected Links
- Rate Limiting
- Swagger Documentation
- Monitoring (Prometheus + Grafana)
- Docker Compose
- Kubernetes Deployment

---

# 📸 Screenshots

> Coming soon...

---

# 🤝 Contributing

Contributions are welcome!

Feel free to fork the project and submit a Pull Request.

---

# 📄 License

This project is licensed under the MIT License.

---

<div align="center">

Made with ❤️ using Spring Boot

</div>
