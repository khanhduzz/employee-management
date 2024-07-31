# Employee Management System

## Overview
This project is an Employee Management System built using Spring MVC, Thymeleaf, Spring Security, and Maven. It manages employee information and their associated accounts.

## ER Model
<img width="1131" alt="Screenshot 2024-07-31 at 12 34 42" src="https://github.com/user-attachments/assets/8e8365b8-0e6e-41c3-bfaf-7587c987603f">

## Use cases
<img width="964" alt="Screenshot 2024-07-31 at 12 34 53" src="https://github.com/user-attachments/assets/ce03e32d-0080-4535-b47e-ff3be688565d">

## Key Features
- Employee management (CRUD operations, Pagination and Filter)
- Account management (Linked to employee)
- User authentication and authorization using Spring Security
- User-friendly interface built with Thymeleaf

## Technology Stack
- **Spring MVC**: [Spring MVC](https://spring.io/projects/spring-mvc)
- **Thymeleaf**: [Thymeleaf](https://www.thymeleaf.org/)
- **Spring Security**: [Spring Security](https://spring.io/projects/spring-security)
- **Maven**: [Apache Maven](https://maven.apache.org/)
- **H2 database**: [H2 Database](https://www.h2database.com/html/main.html)
- **CI/CD with GitHub Actions**: [GitHub Actions](https://github.com/features/actions)
- **Docker image and DockerHub**: [Docker Hub](https://hub.docker.com/)

## Project Structure
- **src/main/java**: Contains Java source code for controllers, services, repositories, entities, and other components.
- **src/main/resources**: Contains configuration files (application.properties or application.yml), templates (Thymeleaf), and other resources.
- .github/workflows: Contains configuration files for GitHub pipelines and ci-cd

## Database Setup
1. Create a database with the specified name and schema.
2. Create tables for Employee and Account entities according to your database schema.
3. Configure database connection details in application.yml.
4. Configure variable in .env file

## Running the Application
1. Clone the repository.
2. Adding a new .env file to the project folder, then configure the information base on the .env sample
3. Build the project using Maven: `mvn clean install`
4. Run the application as a Spring Boot application.

## Dependencies
Specify necessary dependencies in the `pom.xml` file, including Spring MVC, Thymeleaf, Spring Security, database driver, and other required libraries.

## Security Configuration
- Configure requests and authorization in Spring Security configuration.
- Implement authentication and authorization logic.

## CI-CD
- Pipeline for check maven build project: merge on main
- Pipeline for build docker image and push to DockerHub: merge to ci-cd branch

## Contributing
If you want to contribute to this project, please fork the repository and submit a pull request.

## License
MIT license

## Contact
Provide contact information for project inquiries or support.

---

Remember to replace the placeholders with specific details about your project, such as database name, technology versions, and project structure.

By following these guidelines, you can create a comprehensive and informative README file for your Employee Management project.
