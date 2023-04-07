#pragma once

#include <string>

class Account {
public:
    Account(const std::string& accountNumber, int accountBalance) : accountNumber(accountNumber), accountBalance(accountBalance) {}
    std::string getAccountNumber() const { return accountNumber; }
    int getAccountBalance() const { return accountBalance; }
    void setAccountBalance(int balance) { accountBalance = balance; }
private:
    std::string accountNumber;
    int accountBalance;
};