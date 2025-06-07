
# 📦 MediaStack

**MediaStack** is a personal web application for managing and displaying your media content.  
It allows uploading, storing, viewing, and deleting **images**, **videos**, and **PDFs** through an intuitive user interface written entirely in Java (Vaadin).  
Data is managed locally using an SQLite database, while the actual files are stored in the local file system.

---

## 📚 Overview

- [Features](#-features)
- [Local Setup](#-local-setup)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure-v-01)
- [Screenshots](#-screenshots)

---

## 🚀 Features

- 🔼 Upload images, videos, and PDF files
- 📂 Overview of all stored media
- 🖼️ Display images directly in the browser
- 📄 Embed PDF files in the browser
- 🎬 Play videos
- 🗑️ Delete files via the UI
- 🏷️ Store metadata such as filename, type, and upload timestamp

---

## ⚙️ Tech Stack

| Technology  | Purpose                            |
|-------------|-------------------------------------|
| Java 23     | Programming language                |
| Vaadin 24   | UI framework (Java-based)           |
| Spring Boot | Backend framework & configuration   |
| SQLite      | Database for metadata               |
| File-System | Storage for media files             |

---

## 📁 Project Structure (v 0.1)

### Folder Structure

```text
mediastack/
├── src/
│   ├── main/
│   │   ├── java/com/example/mediastack/
│   │   │   ├── ui/       
│   │   │   ├── model/        
│   │   │   ├── repository/    
│   │   │   └── service/
```

### Class Diagram

```plantuml
@startuml
skinparam classAttributeIconSize 0

package "com.example.mediastack.model" {
    class MediaFile {
        - id: Long
        - fileName: String
        - fileType: String
        - uploadDate: LocalDateTime
        - path: String
    }
}

package "com.example.mediastack.repository" {
    interface MediaRepository {
        + save(file: MediaFile): MediaFile
        + findAll(): List<MediaFile>
        + deleteById(id: Long): void
    }
}

package "com.example.mediastack.service" {
    class MediaService {
        - repository: MediaRepository
        + upload(file: MultipartFile): void
        + delete(id: Long): void
        + listFiles(): List<MediaFile>
    }
}

package "com.example.mediastack.ui" {
    class MainView {
        - mediaService: MediaService
        + MainView(service: MediaService)
    }
}

MediaService --> MediaRepository : uses
MainView --> MediaService : uses
MediaService --> MediaFile : creates/uses
MediaRepository --> MediaFile : stores/uses

@enduml
```

![PlantUML Image](https://img.plantuml.biz/plantuml/png/ZPF1RW8X48Rl-nJ4qvf6s_EcCTeqJKni3zNqFeLH9y92WDNQndSlg-xAhjjKBX3cd-7_1XWx3zONIYPkGxa12ufn2SvDlBVqMNYSSftFwHlPGv8Ou1jO8UjnhORu1Sf872eK1A4CtmoL5YXxx2TXOHpBiDSo-a8Ipxlb630I6PlfV1LjBOFc3HHcR1vElWejzgOhL1YfGJo3nx8W1rak5wGmqXZmwqRg8JdypsBHQ4TUstq5HBb7kmIE9wZtMX2XtJ47MqnBnzc5lH-j8-cISZ6HCWtX6Jd_M6j6aIXq53q-xQSYhReMz5jDudOMXtPB73jkPdwAD2xdGfwrIIDVfyvNf8Nq55wFFzFMzcA6JlydWGpuPQhxgnStOHRK8WJAFmXt3JeLOMUD9aH-gipKTGdx5qkD9Wu6ewkd4L3HxZ0Nw9FQJosiCXkgpfB7BvGnjCwZbFw-fOzERASuWyJNQEESP1pcyjV_0W00)

---

## 🧪 Local Setup

### ✅ Prerequisites

- Java 17+
- Maven
- (Optional: IDE like IntelliJ or VS Code)

### ▶️ Run the project

```bash
git clone https://github.com/your-user/mediastack.git
cd mediastack
mvn spring-boot:run
```

The application will be available at [http://localhost:8080](http://localhost:8080).

---

## 📸 Screenshots

TODO

---
