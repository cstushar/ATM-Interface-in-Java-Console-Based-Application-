# ATM Interface in Java (Console Based Application)

This project is a console-based ATM interface developed in Java. It aims to provide users with a secure and easy-to-use banking experience. The application allows users to perform various banking operations such as checking account details, viewing transaction history, making deposits, withdrawals, transfers, and quitting the session.

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Functionalities](#functionalities)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The ATM Interface in Java (Console Based Application) is designed to simulate an automated teller machine (ATM) experience. The project consists of five classes: account holder, account, bank transaction, bank, and the specific ATM of the bank. The system provides user authentication, account management, transaction history, and various banking functionalities.

## Technologies Used

- Java
- Eclipse IDE
- MySQL
- JDBC
- Maven
- Git

## Functionalities

1. User Authentication: Users are prompted to enter their user ID and PIN for authentication. Upon successful authentication, they gain access to the ATM functionalities.

2. Show Transaction History: Users can view their transaction history, which provides details of their past transactions such as withdrawals, deposits, and transfers.

3. Withdraw: Users can withdraw cash from their account by specifying the desired amount. The system verifies the availability of funds and updates the account balance accordingly.

4. Deposit: Users can deposit money into their account by entering the amount they wish to deposit. The system records the transaction and updates the account balance accordingly.

5. Transfer: Users can transfer funds from their account to another user's account. They need to provide the recipient's account details and the desired amount to be transferred. The system ensures the validity of the transaction and updates the account balances of both parties involved.

6. Quit: Users have the option to exit the ATM interface, concluding their banking session.

## Installation

1. Ensure that Java Development Kit (JDK) is installed on your system. If not, download and install it from the official Oracle website.

2. Download and install Eclipse IDE or any other Java IDE of your choice.

3. Set up the MySQL database and configure the necessary database settings in the application.

4. Clone this repository to your local machine using Git or download it as a ZIP file and extract it.

5. Open the project in Eclipse or your preferred Java IDE.

6. Build the project and resolve any dependencies using Maven.

7. Configure the necessary database connection details in the application code.

## Usage

1. Run the application from the IDE.

2. Follow the on-screen instructions to navigate through the ATM interface.

3. Enter the user ID and PIN to authenticate and access the available functionalities.

4. Perform desired banking operations such as checking account details, making transactions, and viewing transaction history.

5. Choose the "Quit" option to exit the ATM interface.

## Contributing

Contributions to the project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to customize this README.md file to provide more specific information about your project.
