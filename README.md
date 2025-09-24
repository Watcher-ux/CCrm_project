# Campus Course & Records Manager (CCRM)

## Project Overview

The Campus Course & Records Manager (CCRM) is a console-based Java application designed to manage student records, course catalogs, and academic enrollments for an educational institute. It provides a command-line interface (CLI) for administrators to perform key operations such as adding students and courses, managing enrollments, assigning grades, and generating transcripts. The application demonstrates core Java SE principles, including Object-Oriented Programming, modern file I/O with NIO.2, the Streams API, and foundational design patterns.

---
## How to Run

**Prerequisites:**
* Java Development Kit (JDK) 17 or later.

**Steps:**
1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/Watcher-ux/CCrm_project.git](https://github.com/Watcher-ux/CCrm_project.git)
    cd CCrm_project
    ```
2.  **Compile the source code:**
    ```sh
    javac -d out --source-path src src/edu/ccrm/cli/Application.java
    ```
3.  **Run the application:**
    ```sh
    java -cp out edu.ccrm.cli.Application
    ```
4.  **To run with assertions enabled:**
    ```sh
    java -ea -cp out edu.ccrm.cli.Application
    ```

---
## Features
* **Student Management:** Add, find, and list students.
* **Course Management:** Add, find, and list courses using a Builder pattern for object creation.
* **Enrollment & Grading:** Enroll students in courses with validation (e.g., credit limits, duplicate enrollments) and assign grades.
* **Transcripts & GPA:** Generate student transcripts and calculate cumulative GPA using the Streams API.
* **File Operations:**
    * **Export:** Save current student, course, and enrollment data to `.csv` files.
    * **Import:** Load data from `.csv` files into the application on startup.
    * **Backup:** Create timestamped backups of all data files.

---
## Technical Concepts Demonstrated

| Syllabus Topic | File / Class / Method Where Demonstrated |
| :--- | :--- |
| **Packages** | Entire project structure (e.g., `edu.ccrm.domain`, `edu.ccrm.service`) |
| **OOP: Encapsulation** | `domain/Student.java` (private fields with public getters/setters) |
| **OOP: Inheritance** | `domain/Student.java` extends `domain/Person.java` |
| **OOP: Abstraction** | `domain/Person.java` (abstract class with abstract method `getDetails()`) |
| **OOP: Polymorphism** | (Not explicitly shown, but could be demonstrated with `List<Person>`) |
| **Design Pattern: Singleton** | `config/DataStore.java` (ensures one instance for all data) |
| **Design Pattern: Builder** | `domain/Course.java` (nested static `Builder` class) |
| **Interfaces** | (Add if you created one, e.g., `io/Persistable.java`) |
| **Lambdas & Streams API** | `service/TranscriptService.java` (`calculateGpa` method uses streams) |
| **NIO.2 File I/O** | `io/FileService.java` (uses `Path`, `Files`, `Paths`) |
| **Custom Exceptions** | `exception/StudentNotFoundException.java`, thrown in `EnrollmentService.java` |
| **Date/Time API** | `domain/Enrollment.java` (uses `LocalDateTime`), `FileService.java` (for backups) |
| **Enums with Fields** | `domain/Grade.java` (enum with description and grade points) |
| **Recursion (via Streams)** | `util/FileUtils.java` (`calculateDirectorySize` method uses `Files.walk`) |
| **Decision Structures** | `cli/Application.java` (`switch` in `main`, `if/else` in sub-menus) |
| **Loops & Jumps** | `cli/Application.java` (`while` loop in `main`), `TranscriptService.java` (`for` loop) |

---
## Java Architecture Overview

### Evolution of Java
* **1995:** Java 1.0 released by Sun Microsystems with the "Write Once, Run Anywhere" promise.
* **2004:** Java 5 (J2SE 5.0) introduces major features like generics, enums, and annotations.
* **2014:** Java 8 is a revolutionary release with Lambdas, the Streams API, and a new Date/Time API.
* **2018:** A new 6-month release cadence begins; Java 11 is released as a Long-Term Support (LTS) version.
* **2021:** Java 17 (LTS) is released, bringing further improvements like sealed classes and pattern matching.

### Java ME vs SE vs EE

| Feature | Java ME (Micro Edition) | Java SE (Standard Edition) | Java EE (Enterprise Edition) |
| :--- | :--- | :--- | :--- |
| **Target** | Resource-constrained devices | Desktop, server, console apps | Large-scale, distributed enterprise systems |
| **Core API** | A subset of the Java SE API | The core Java platform | Java SE + additional libraries for enterprise |
| **Typical Use** | Embedded systems, old mobile apps | General-purpose programming (this project) | Web services, banking applications, backends |

### JDK, JRE, and JVM
* **JVM (Java Virtual Machine):** An abstract machine that provides the runtime environment for executing Java bytecode, making Java platform-independent.
* **JRE (Java Runtime Environment):** The software package containing the JVM and core libraries, necessary to *run* Java applications.
* **JDK (Java Development Kit):** A superset of the JRE that includes development tools like the compiler (`javac`) and debugger, necessary to *develop* Java applications.

---
## Setup and Installation

### JDK Installation on Windows
The project was built using JDK 17. The installation was verified through the command line.

![Verification of java -version](screenshots/java-version.png)

### Eclipse IDE Setup
The project was developed in the Eclipse IDE. The project was created as a standard Java Project, and the source code was organized into packages as seen in the Package Explorer.

![Eclipse Project Setup](screenshots/ide-setup.png)
