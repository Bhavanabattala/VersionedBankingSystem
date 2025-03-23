# VersionedBankingSystem

# Project Overview
The Versioned Bank Account is a Java banking system that has a history of transactions with rollback support to earlier states. The system keeps the previous transactions but only changes the current version, thus being efficient for financial tracking, auditing, and fraud prevention.

# Features
-  Deposit & Withdrawal – Make transactions with a historical record.
-  Transaction History – See previous transactions for any saved version.
-  Rollback Functionality – Reverse recent updates and return to a prior account state.
-  Efficient Storage – Utilizes a doubly linked list to store versions with low overhead.

# Installation
# Prerequisites
- Java 8+ (Download: [Oracle Java](https://www.oracle.com/java/technologies/javase-downloads.html))
- Git (Optional, for cloning the repository)

# Steps to Run the Project
1. Clone the Repository (or download the files manually):
  
   git clone https://github.com/Bhavanabattala/VersionedBankAccount.git
   cd VersionedBankAccount
