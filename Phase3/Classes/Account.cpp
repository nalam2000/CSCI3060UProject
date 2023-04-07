#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>
using namespace std;

class Account {
private:
    string accountNumber;
    int balance;
    
public:
    Account(string accNum, int bal): accountNumber(accNum), balance(bal) {}
    
    string getAccountNumber() const {
        return accountNumber;
    }
    
    int getBalance() const {
        return balance;
    }
    
    void setBalance(int newBalance) {
        balance = newBalance;
    }
    
    // Read accounts from a file and store them in an unordered_map
    static unordered_map<string, Account> readAccounts(string filename) {
        unordered_map<string, Account> accounts;
        ifstream inFile(filename);
        string line;
        while (getline(inFile, line)) {
            string accNum = line.substr(0, 6);
            int bal = stoi(line.substr(7));
            accounts[accNum] = Account(accNum, bal);
        }
        inFile.close();
        return accounts;
    }
    
    // Write account balances to a file
    static void writeAccounts(string filename, unordered_map<string, Account> &accounts) {
        ofstream outFile(filename);
        for (auto const &account: accounts) {
            outFile << account.second.getAccountNumber() << " " << account.second.getBalance() << endl;
        }
        outFile.close();
    }
};
