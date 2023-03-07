#include <iostream>
#include <string>
#include "AuctionSystem.h"

int main(int argc, char* argv[]) {
    if (argc != 4) {
        std::cout << "Usage: auction-system <current-accounts-file> <available-items-file> <transactions-file>" << std::endl;
        return 1;
    }

    AuctionSystem auctionSystem(argv[1], argv[2], argv[3]);

    std::string username, password;
    std::cout << "Please enter your username: ";
    getline(std::cin, username);
    std::cout << "Please enter your password: ";
    getline(std::cin, password);

    if (auctionSystem.login(username, password)) {
        std::cout << "Login successful" << std::endl;
        auctionSystem.displayTransactions();
        std::string action;
        while (true) {
            std::cout << "Enter 'create' to create a new user, 'change' to change your password, or 'quit' to exit: ";
            getline(std::cin, action);
            if (action == "create") {
                std::string newUsername, newPassword;
                std::cout << "Enter new username: ";
                getline(std::cin, newUsername);
                std::cout << "Enter new password: ";
                getline(std::cin, newPassword);
                auctionSystem.createUser(newUsername, newPassword);
            }
            else if (action == "change") {
                std::string newPassword;
                std::cout << "Enter new password: ";
                getline(std::cin, newPassword);
                auctionSystem.changePassword(username, newPassword);
            }
            else if (action == "quit") {
                break;
            }
            else {
                std::cout << "Invalid action" << std::endl;
            }
        }
        //auctionSystem.writeAccounts("updated-accounts.txt");
    }
    else {
        std::cout << "Error: Invalid login information" << std::endl;
    }

    return 0;
}
