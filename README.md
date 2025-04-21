# 🎓 Student Academic Portal

An all-in-one academic management system designed for students and administrators to manage profiles, results, performance, feedback, and academic resources. Includes chatbot integration using Gemini AI, secure authentication using JWT, and a responsive Angular UI.

---

## 📌 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [How to Run](#how-to-run)
- [API Usage](#api-usage)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [Contributors](#contributors)
- [License](#license)
- [References](#references)
- [Contact](#contact)

---

## ✅ Features

### 👨‍🎓 Student

- JWT-based secure login.
- Profile details display.
- Results with semester-wise filters.
- Performance visualization (GPA, CGPA).
- Chatbot with Gemini AI (Ask: "What is my GPA?").
- Library: view & rate books.
- Feedback submission form.

### 🧑‍💼 Admin

- Add student profiles.
- Upload result PDFs (auto-parse).
- Add books with links.
- View and manage ratings.
- Role-based authentication (admin/student).

---

## ⚙️ Tech Stack

| Layer      | Technology           |
|------------|----------------------|
| Frontend   | Angular, TypeScript  |
| Backend    | Spring Boot, Java 17 |
| Database   | MySQL                |
| Security   | Spring Security + JWT |
| AI Chatbot | Gemini API (Google)  |
| Tools      | Postman, VS Code, springtoolsuit4 |

---

## 💻 Installation

### Prerequisites:

- **Java 17+ (springtoolsuit4)**
- **MySQL**
- **Node.js (v16+)**
- **Angular CLI**
- **Postman**

---

## 🚀 How to Run

### 1. MySQL Database Setup

1. Open your MySQL terminal or MySQL Workbench.
2. Run the following command to create the database:

```sql
CREATE DATABASE student_portal;
```

### 2. Backend Setup (Spring Boot)

#### Clone the Repository
```bash
git clone https://github.com/venkatesh2k3/Student_Portal.git
```
 #### Directory: * /Student_Portal 
 
1. Open Spring Tool Suite 4 (STS4).
2. Import the Student_Portal/ folder as a Maven project.
3. Navigate to src/main/resources/application.properties and configure:

4. Edit
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_portal
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
5. Run the application:
  - Right-click → Run As → Spring Boot App
  - OR run StudentPortalApplication.java
6. The backend will run on:
   http://localhost:8080
---
### 🔐 API Highlights
|Method | Endpoint                     | Description|
|-------|------------------------------|----------------|
|POST   | /auth/login/student          | Login as Student|
|POST   | /auth/register               | Register Student (Admin)|
|GET    | /student/details             |Fetch Student Profile|
|POST   | /api/results/upload          | Upload Result PDF (Admin)|
|GET    | /api/results/hallticket/{id} | Get Student Results|
|GET    | /api/performance/{b}/{y}/{s} | GPA/Performance API|
|POST   | /library/add                 | Add Book (Admin)|
|POST   | /library/rate                | Rate Book (Student)|
|POST   | /api/chatbot/ask             | Chatbot (Gemini API)|

---

### 3. Setup Frontend
#### ✅ Prerequisites

- Node.js & npm
- Angular CLI
- VS Code

```bash
git clone https://github.com/yourusername/student-portal-frontend.git
cd student-portal-frontend
npm install
npm install -g @angular/cli
ng serve
```
Visit 👉 http://localhost:4200

---

## 📬 API Usage

1. **Open Postman**

   Download and open Postman if you haven’t already.
2. **Admin Login (Default)**
   
    - **Method:** `POST`
    - **URL**: `http://localhost:8080/auth/login/admin`
    - **Headers:**
      `Content-Type:** application/json`
    - **Body:**

```json
{
  "username": "admin",
  "password": "admin@"
}
```

>📌 Copy the JWT token from the login response.

3. **Register a Student (Admin Only)**

   Only an admin can register a student. Use the **admin JWT token** in the Authorization header.

    - **Method**: `POST`  
    - **URL**: `http://localhost:8080/auth/register/student`
    - **Headers**:
      - `Content-Type: application/json`
      - `Authorization: Bearer <admin_token_here>`
    - **Body**:
```json
{
    "username": "21NQ1A0501",
    "password": "21NQ1A0501",
    "email": "21NQ1A0501@gmail.com",
    "role":"student"
}
```

4. **Upload Student Results (Admin Only)**

   Admins can upload result PDFs for a specific year, batch, and semester using this API. JWT token is required.

    - **Method**: `POST`  
    - **URL**: `http://localhost:8080/api/results/upload`
    - **Headers**:
      - `Authorization: Bearer <admin_token_here>`
    - **Body (form-data)**:

| Key      | Value (Example)         | Type  |
|----------|--------------------------|--------|
| file     | `results_sem2.pdf`      | File   |
| year     | `2025`                  | Text   |
| batch    | `CSE`                   | Text   |
| semester | `2`                     | Text   |

> 📌 Make sure the PDF is selected as `file` in the form-data and the other values are text fields.


5. **Add a Book to Library (Admin Only)**

   Admins can add books to the library. Use your **admin JWT token** to authorize the request.

    - **Method**: `POST`  
    - **URL**: `http://localhost:8080/library/add`
    - **Headers**:
      - `Content-Type: application/json`
      - `Authorization: Bearer <admin_token_here>`
    - **Body**:
```json
{
  "title": "Artificial Intelligence: A Modern Approach",
  "author": "Stuart Russell",
  "publishedYear": 2024,
  "link": "https://example.com/ai-modern-approach"
}
```

6. **Student Login**

   Students can log in using their registered username and password to receive a JWT token.

    - **Method**: `POST`  
    - **URL**: `http://localhost:8080/auth/login/student`
    - **Headers**:
      - `Content-Type: application/json`
    - **Body**:
    ```json
    {
      "username": "21NQ1A0002",
      "password": "student@123"
    }
    
7. **View Student Details**

    - **Method**: `GET`  
    - **URL**: `http://localhost:8080/student/details`
    - **Headers**:
      - `Authorization: Bearer <student_token_here>`
        
8. **View Library Books**

    - **Method**: `GET`  
    - **URL**: `http://localhost:8080/library/books`
    - **Headers**:
      - `Authorization: Bearer <student_token_here>`
 
9. **View Performance**

    - **Method**: `GET`  
    - **URL**:http://localhost:8080/api/results/performance/{batch}/{year}/{semester}
    - **Ex**: `http://localhost:8080/api/results/performance/2024-2025/4/1`
    - Replace `{batch}`, `{year}`, and `{semester}` with the student's details.
    - **Headers**:
    - `Authorization: Bearer <student_token_here>`

10. **View Results by Hall Ticket Number**

    Students can view their individual results by providing their hall ticket number.
     
      - **Method**: `GET`  
      - **URL**:  `http://localhost:8080/api/results/hallticket/{hallticketNumber}`
      - Replace `{hallticketNumber}` with the student’s hall ticket (e.g., `21NQ1A0002`)
      - **Headers**:
      - `Authorization: Bearer <student_token_here>`
      
      > ✅ This will return result data (if available) specific to that hall ticket.

11. **Rate a Book (Student)**

    Students can rate books available in the library using this endpoint.

      - **Method**: `POST`  
      - **URL**: `http://localhost:8080/library/rate`
      - **Headers**:
        - `Content-Type: application/json`
        - `Authorization: Bearer <student_token_here>`
      - **Body**:
      ```json
      {
        "bookId": 1,
        "rating": 4
      }
      ```
---
### 📷 Screenshots

✅ Home Page
<img width="1280" alt="image" src="https://github.com/user-attachments/assets/c63f4fdc-1ca5-4b6d-87bc-0793392762c8" />

✅ Login Page 
<img width="1280" alt="image" src="https://github.com/user-attachments/assets/cf8e41b2-779a-4a46-8338-0e9f30460fe2" />

✅ Student Dashboard
<img width="1280" alt="image" src="https://github.com/user-attachments/assets/838abaa5-fe57-4bd8-8661-29a7e62eb7cc" />

✅ Performance Chart
<img width="1280" alt="image" src="https://github.com/user-attachments/assets/e15098d6-bcd5-4e2c-be95-c59900b9226a" />

✅ Chatbot Demo
<img width="1280" alt="image" src="https://github.com/user-attachments/assets/8b3bff27-e8a1-4379-ae8a-63c658a12f3b" />

✅ Books
<img width="1280" alt="image" src="https://github.com/user-attachments/assets/ba70508a-d2cd-4975-9b5a-1568066c97ad" />

### 🌱 Future Enhancements
- 📧 **Email Notifications**

- 📊 **Admin Result Analytics Dashboard**

- 📕 **Faculty Login Module**

- 📈 **Book Recommendation using AI**

- 🔐 **OAuth / Biometric Login**
  
- 🕐 **Attendance Module**  

### 👩‍💻 Contributors

|Name | Role|
|------|------|
|[Venkatesh Sangam](https://www.linkedin.com/in/venkatesh-sangam/) | Backend & Integration|
|[Sudheer Mokara](https://www.linkedin.com/in/mokarasudheer/) | Frontend Design|

### 📚 References

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Angular Docs](https://angular.io/docs)
- [Google AI Studio (Gemini)](https://aistudio.google.com/)
- [JWT Guide](https://jwt.io/introduction)


### 📝 License**
This project is licensed under the MIT License.
See LICENSE for details.

### 📩 Contact
For any queries or feedback:
- 📧 **Email: venkatesh2k3@gmail.com**
- 🔗 **GitHub: venkatesh2k3**
