# Share Market Management System

The Share Market Management System is a Java-based console application designed to simulate a stock trading platform tailored for educational and small business use. The application provides role-based access for admins and investors, offering functionalities for stock management and basic transactions.

---

## Abstract

The Share Market Management System is a Java-based console application designed to simulate a small-scale stock trading platform, specifically tailored to support both administrative and investor roles. This application facilitates the addition, updating, and removal of stocks, as well as allowing investors to view available stocks and conduct basic buy and sell operations. 

### Key Features:

#### User Roles
- **Admin**: Has full access to add, modify, and remove stocks, as well as to manage user accounts.
- **Investor**: Can view available stocks, check individual stock prices, and perform transactions to buy or sell shares.

#### Database Integration
- **SQLite Database**: The application stores user credentials and stock data in a SQLite database, ensuring data persistence across sessions.
- **Login Authentication**: Distinct login interfaces for Admin and Investor roles, enhancing security and role-based access control.

#### Interactive CLI
- A command-line interface that allows real-time interaction, enabling investors to make stock transactions and admins to manage stock listings directly.

### Objectives
- **Enhance User Experience**: With role-specific features, investors can make informed decisions, while admins streamline stock management.
- **Simplify Market Operations**: By offering essential stock management tools, admins can maintain an up-to-date listing, and investors can access accurate pricing.
- **Data Security**: Authentication ensures that sensitive operations are restricted, allowing only authorized access to admin functions.

### Design Patterns
1. **Singleton**: Ensures a single instance of the DatabaseManager class, providing a centralized point for database interactions.
2. **Strategy**: Defines flexible authentication strategies based on user roles, allowing different login methods for Admin and Investor.
3. **Observer**: Implements real-time stock update notifications, notifying investors of stock price changes as they occur.

This system is ideal for educational purposes and small businesses, providing users with a straightforward, simulated environment to explore basic functionalities of a stock trading platform.

---

## Prerequisites

1. **Java JDK (version 8 or above)**
   - Ensure Java is installed on your machine. To check, run the following command:
     ```bash
     java -version
     ```
   - [Download Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) if needed.

2. **Apache Maven**
   - Maven is required to build and manage dependencies for the project.
   - Verify Maven installation by running:
     ```bash
     mvn -version
     ```
   - [Download Maven](https://maven.apache.org/download.cgi) if it's not installed.

3. **SQLite JDBC Library**
   - The application uses SQLite for data persistence. Ensure that the SQLite JDBC library (`sqlite-jdbc-<version>.jar`) is in the project's `libs` directory.
   - If needed, download it from [SQLite JDBC Driver](https://bitbucket.org/xerial/sqlite-jdbc/downloads/).

# Building and Running the Share Market Management System

This guide provides step-by-step instructions to build and run the Share Market Management System Java application.

---

## Building and Running the Application

1. **Navigate to the Project Directory**  
   Open a terminal and navigate to the root directory of the project where `pom.xml` is located.

2. **Clean and Package the Project**  
   Run the following Maven command to clean and package the project:
   ```bash
   mvn clean package

3. **Run the Project**
   ```bash
   java -cp "ShareMarketApp-1.0-SNAPSHOT.jar;../libs/sqlite-jdbc-<version>.jar" ShareMarketApp.Main
