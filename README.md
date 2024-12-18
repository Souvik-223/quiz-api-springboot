# Quiz Application

A Spring Boot-based RESTful Quiz Application that allows users to take quizzes, submit answers, and track their progress.

## Features

- Dynamic quiz session management
- Random question selection
- Answer submission and validation
- Quiz progress tracking
- Session-based scoring system
- Real-time accuracy calculation

## Technologies Used

- Java 17
- Spring Boot 3.4.0
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository.
  ```bash
     git clone https://github.com/Pragadeesh-19/quiz-app.git
  ```

2. Navigate to the project directory.
  ```bash
     cd quiz-app
  ```
  
3. Build the project.
  ```bash
     mvn clean install
  ```

4. Run the application.
  ```bash
     mvn spring-boot:run
  ```

The application will start on `http://localhost:8080`.


## API Endpoints

1. **Start Quiz Session**
   ```bash
     POST /api/quiz/start
   ```
   Starts a new quiz session and returns a session ID.

2. **Get Random Question**
   ```bash
     GET /api/quiz/question/{sessionId}
   ```
   Returns a random question for the given session.

3. **Submit Answer**
   ```bash
     POST /api/quiz/submit
   ```
  **Parameters:**
     - `sessionId` (Long): The session ID,
     - `questionId` (Long): The question ID,
     - `selectedAnswer` (String): The user's answer

4. **Get Quiz Summary**
   ```bash
     GET /api/quiz/summary/{sessionId}
   ```
   Returns the quiz progress and score for the session.

## Database Configuration

The application uses an H2 in-memory database. Configuration is provided in `application.properties`.

```bash
  spring.datasource.url=jdbc:h2:mem:quizdb
  spring.datasource.username=root
  spring.datasource.password=12345
  spring.datasource.driver-class-name=org.h2.Driver

  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
  spring.jpa.properties.hibernate.format_sql=true
  spring.jpa.show-sql=true
  spring.h2.console.enabled=true
  spring.jpa.hibernate.ddl-auto=create-drop
```

## Sample Data

The application comes pre-loaded with sample quiz questions.

```bash
  List<Question> questions = Arrays.asList(
    new Question("What is the capital of France?", "Paris",
            Arrays.asList("London", "Paris", "Berlin", "Madrid")),
    new Question("Which planet is known as the Red Planet?", "Mars",
            Arrays.asList("Venus", "Mars", "Jupiter", "Saturn")),
    new Question("What is 2 + 2?", "4",
            Arrays.asList("3", "4", "5", "6")),
    new Question("Who painted the Mona Lisa?", "Leonardo da Vinci",
            Arrays.asList("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"))
  );
```

## Project Structure

```bash
  quiz-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/quiz/quizapp/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```

## Contributing

1. Fork the repository.
   
2. Create your feature branch:  

    ```bash
   git checkout -b feature/AmazingFeature
   ```
    
3. Commit your changes:
   
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
   
4. Push to the branch:
   
   ```bash
    git push origin feature/AmazingFeature
   ```
   
6. Open a Pull Request
