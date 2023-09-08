Welcome to the Quizzy Library documentation.
This library, developed in Java, serves as the final project for the Design Patterns course. Quizzy is a versatile library for creating and managing quizzes, supporting both terminal-based and GUI-based quizzes.

**Project Package**
All classes and interfaces are part of the il.ac.hit.quizzy package.


**Design patterns used :**
*Singleton Design Pattern*
Used in: SimpleCSVQuizFilesDAO class
Explanation: The Singleton pattern ensures that only one instance of the SimpleCSVQuizFilesDAO class is created, providing a single point of access to the DAO functionality for saving and loading quizzes from files.
*Data Access Object (DAO) Design Pattern*
Used in: SimpleCSVQuizFilesDAO class
Explanation: The DAO pattern separates the data access logic from the business logic. SimpleCSVQuizFilesDAO encapsulates the operations for saving and loading quizzes from CSV files, providing a clean and consistent interface for data access.
*Factory Design Pattern*
Used in: QuizFactory class
Explanation: The Factory pattern abstracts the process of creating IQuiz objects based on a specified QuizType. Clients can create instances of quizzes without needing to know the specific implementation class, promoting flexibility and easy extensibility.
*Prototype Design Pattern*
Used in: Classes implementing the IQuiz interface
Explanation: The Prototype pattern allows for the creation of new IQuiz objects by cloning existing instances, enabling efficient object creation. Each quiz type (TERMINAL or GUI) can act as a prototype for creating multiple quizzes with similar characteristics.
*Builder Design Pattern*
Used in: QuizQuestion class
Explanation: The Builder pattern separates the construction of complex objects from their representation. QuizQuestion.Builder is responsible for creating IQuizQuestion instances with various properties (title, question text, answers), making it easier to construct quiz questions with different configurations.


**Usage**
To use the Quizzy Library, you can follow these steps:


Create an instance of the QuizFactory.
Use the factory to create a new IQuiz object based on the desired QuizType.
Set the name of the quiz using setName(String text).
Create and configure multiple-choice questions using the QuizQuestion.Builder class.
Add the questions to the quiz using addQuestion(IQuizQuestion question).
Save the quiz to a file using the IQuizFilesDAO interface.
Load the quiz from a file using the IQuizFilesDAO interface.
Start the quiz using the start() method.

*By :*
Yossef Davarashvili and Maxim Belavus