# Versioned vs. Simple Banking System - User Manual

## Introduction
The document provides an overview of the two banking systems, e.g., their functionalities, installing and running the program, and utilizing the features effectively. The **Simple Banking System** is utilized to open an account, deposit and withdraw money, and check the balance but lacks a transaction history. The **Versioned Banking System**, however, keeps track of previous transactions, allows users to see prior states of their account, and gives rollbacks to undo transactions.

## Features Overview
The **Simple Banking System** offers basic banking operations like deposits, withdrawals, and balance inquiries. After a transaction, though, it cannot be retrieved or reversed. The **Versioned Banking System**, on the other hand, has a versioning mechanism wherein each transaction creates a new version. Users can view historical transactions, look through their record, and even roll back their account to an earlier status. Rolling back erases all future versions in order to preserve data integrity.

## Installation Guide
### Prerequisites
To run this project, ensure you have the following installed:
- **Java 8+** ([Download Here](https://www.oracle.com/java/technologies/javase-downloads.html))
- **Git** (Optional, for cloning the repository)

### Steps to Install and Run the Project
1. **Clone the Repository**
   
   git clone https://github.com/Bhavanabattala/VersionedBankingSystem/tree/main
   
2. **Navigate to the Project Directory:**
 
   cd VersionedBankingSystem
   
3. **Compile the Java Files:**

   javac -d bin src/*.java
  
4. **Run the Application:**
  
   java -cp bin Main
   

## Environment Setup
For successful development process, the following tools and configurations I used :

### **JDK**
Since the project is done in Java, **JDK 23** was utilized. You can get it from [Oracle JDK 23](https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html) or you may use OpenJDK. To verify if installed, run:

java -version
javac -version


### **Visual Studio Code (VS Code)**
VS Code was the preferred code editor for composing and structuring Java code. It can be downloaded from [VS Code](https://code.visualstudio.com). It is recommended to install the **"Extension Pack for Java"** from the Extensions Marketplace upon installation, which provides syntax highlighting, debugging, and an easy way of running Java programs.

### **GitHub**
GitHub was used to store and maintain the project repository. This enabled version control and backup of project files. The project files were committed directly onto GitHub using the web interface without the need to install Git locally.

## How to Use the Banking System

### **Simple Banking System**
1. Create an account.
2. Deposit money into the account.
3. Withdraw money when needed.
4. Check the current balance.
5. Exit the system.

**Example Usage:**
- Nick creates an account and deposits **$5000**.
- He withdraws **$500**, leaving a balance of **$4500**.
- He checks his balance and exits the system.
- However, there is no way to retrieve past transactions or undo any actions.

### **Versioned Banking System**
1. Create an account.
2. Each deposit or withdrawal creates a new version.
3. Users can check past transactions by selecting a version.
4. Users can **rollback** to a previous version, restoring their balance before certain transactions.
5. After rollback, all future versions are deleted to maintain data integrity.

**Example Usage:**
- Nick creates an account and deposits **$2500** (Version 1).
- He then withdraws **$200**, resulting in a balance of **$2300** (Version 2).
- Checking history at **Version 2** shows both transactions.
- Rolling back to **Version 1** restores his balance to **$2500**.
- Now, only **Version 1** remains, confirming that rollback removes future versions.
- Nick exits the system.

## Troubleshooting
### **Java Not Recognized**
- Ensure Java is installed and properly configured in your system's environment variables.
- Run `java -version` to check installation.

### **Compilation Errors**
- Run `java -version` to confirm installation. Look for syntax error or missing dependency in the code.
- Ensure that you are in the correct directory when compiling.
  
### **Unable to Rollback**
- Make sure there are earlier versions stored prior to trying a rollback.
- If there is an error, restart the application and try once again.

## License
This project is licensed under the MIT License.


For any issues or questions, please contact bhavanabattala9@gmail.com

