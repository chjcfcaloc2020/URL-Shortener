<div align="center">

# 🚀 Short.Mono

### Production-ready URL Shortener Platform

A modern URL shortening platform built with **Spring Boot 4**, **React**, **MySQL**, and **Redis**, featuring Hashids-based URL generation, configurable expiration policies, redirect caching, click analytics, and cloud deployment.

<p>

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-success?style=for-the-badge)
![React](https://img.shields.io/badge/React-19-61DAFB?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?style=for-the-badge)
![Redis](https://img.shields.io/badge/Redis-Upstash-red?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge)

</p>

🌐 **Live Demo**

Frontend: https://short-mono-cfj3z5d53-ntc2.vercel.app/

</div>

---

# 📖 Overview

**Short.Mono** is a production-ready URL Shortener that transforms long URLs into compact, secure, and shareable links.

Instead of exposing sequential database IDs or generating random UUIDs, the application uses **Hashids** with a configurable **Salt** to generate deterministic, URL-safe short codes.

To improve performance, redirect requests are cached in **Redis (Upstash)**, significantly reducing database queries for frequently accessed links.

The project follows **Clean Architecture** principles with DTOs, validation, global exception handling, layered services, and cloud deployment.

---

# ✨ Features

## 🔗 URL Shortening

- Generate short URLs using **Hashids**
- Configurable Hashids Salt
- Configurable minimum hash length
- URL-safe short codes
- Collision-free generation

---

## ⏳ Flexible Expiration

Supports multiple expiration policies:

- 15 minutes
- 30 minutes
- 1 hour
- 1 day
- Never expire

Expired URLs automatically become inaccessible.

---

## ⚡ High Performance Redirect

Redirect flow:

```
Browser
      │
      ▼
 Short URL
      │
      ▼
 Redis Cache
   │        │
 Hit       Miss
   │        │
   │        ▼
   │     MySQL
   │        │
   └────────┘
      │
      ▼
 Landing Page
      │
      ▼
 Redirect
```

Features

- Redis Cache (Upstash)
- Automatic cache refresh
- Database fallback
- Fast redirect response

---

## 🖥 Redirect Landing Page

Instead of redirecting immediately, users will first see a transition page displaying:

- Short Code
- Destination URL
- Redirect progress animation

If the URL has expired, a dedicated error page is displayed instead.

---

## 📊 Click Analytics

Track total visits for every shortened URL.

Batch statistics API:

```http
POST /api/urls/batch/clicks
```

Request

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
    "clicks": 82
  }
]
```

---

## 🧹 Automatic Cleanup

Expired URLs are automatically removed by a scheduled task.

- Spring Scheduler
- Configurable Cron Expression

---

# 🔒 Hashids Flow

```
Original URL
      │
      ▼
Save into MySQL
      │
      ▼
Generated ID
      │
      ▼
Hashids.encode(id)
      │
      ▼
Short Code
      │
      ▼
https://your-domain/AbX91P
```

During redirect:

```
Short Code
      │
      ▼
Hashids.decode()
      │
      ▼
Database ID
      │
      ▼
Redis Cache
      │
      ▼
MySQL
      │
      ▼
Original URL
```

---

# 🏗 Architecture

```
                React Frontend
                       │
                       ▼
             Spring Boot REST API
                       │
        ┌──────────────┴──────────────┐
        ▼                             ▼
   Redis (Upstash)               MySQL (Railway)
        │                             │
        └──────────────┬──────────────┘
                       ▼
                Redirect Response
```

---

# 📂 Project Structure

```
src
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── scheduler
├── service
├── util
└── resources
    └── templates
```

Architecture

- DTO Pattern
- Repository Pattern
- Service Layer
- Validation
- Global Exception Handling
- Clean Architecture

---

# 🛠 Tech Stack

### Backend

- Java 21
- Spring Boot 4
- Spring Data JPA
- Hibernate
- Maven
- Hashids

### Frontend

- React
- Vite
- Tailwind CSS

### Database

- MySQL (Railway)

### Cache

- Redis (Upstash)

### Deployment

- Docker
- Render
- Railway
- Vercel

---

# 📌 API

| Method | Endpoint | Description |
|----------|----------|-------------|
| POST | `/api/shorten` | Create Short URL |
| GET | `/{code}` | Redirect |
| DELETE | `/api/urls/{code}` | Delete Short URL |
| POST | `/api/urls/batch/clicks` | Batch Click Statistics |

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

# 🚀 Getting Started

Clone the repository

```bash
git clone https://github.com/<your-username>/short-mono.git
```

Navigate to the project

```bash
cd short-mono
```

Run the application

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

# ☁ Cloud Deployment

| Service | Platform |
|----------|----------|
| Frontend | Vercel |
| Backend | Render |
| Database | Railway MySQL |
| Cache | Upstash Redis |

---

# 📸 Screenshots

> Coming soon...

---

# 🚀 Roadmap

- QR Code generation
- Custom Alias
- User Authentication
- User Dashboard
- Password-protected URLs
- Rate Limiting
- Swagger Documentation
- Prometheus & Grafana Monitoring

---

# 🤝 Contributing

Contributions are welcome!

Feel free to fork the project and submit a Pull Request.

---

# 📄 License

Licensed under the MIT License.

---

<div align="center">

⭐ If you like this project, don't forget to give it a Star!

Made with ❤️ using Spring Boot & React

</div>
