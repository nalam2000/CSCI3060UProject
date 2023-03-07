#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

class AuctionSystem {
private:
    unordered_map<string, int> accounts;
    vector<string> items;
    unordered_map<string, string> userAccounts;
    double credit;

public:
    AuctionSystem() {}

    bool login(string username, string password) {
        if (userAccounts.find(username) == userAccounts.end() || userAccounts[username] != password) {
            cout << "Error: Invalid login information" << endl;
            return false;
        }
        cout << "Login successful" << endl;
        return true;
    }

    void createAccount(string username, string password) {
        if (userAccounts.find(username) != userAccounts.end()){
            cout << "Error: username already exists" << endl;
        }
        else{
            userAccounts[username] = password;
            cout << "Account created successfully" << endl;
        }
    }

    void changePassword(string username, string password) {
        if (userAccounts.find(username) == userAccounts.end()){
            cout << "Error: username does not exist" << endl;
        }
        else{
            userAccounts[username] = password;
            cout << "Password changed successfully" << endl;
        }
    }

    void readAccounts(string filename) {
        ifstream inFile(filename);
        string line;
        while (getline(inFile, line)) {
            string accountNumber = line.substr(0, 6);
            int accountBalance = stoi(line.substr(7));
            accounts[accountNumber] = accountBalance;
        }
        inFile.close();
    }

    void writeAccounts(string filename) {
        ofstream outFile(filename);
        for (auto account: accounts) {
            outFile << account.first << " " << account.second << endl;
        }
        outFile.close();
    }

    void readItems(string filename) {
        ifstream inFile(filename);
        string line;
        while (getline(inFile, line)){
            items.push_back(line);
        }
        inFile.close();
    }

    void processTransaction(string transaction) {
        // TODO: Implement transaction processing logic
    }

    void processTransactions(string filename) {
        ifstream inFile(filename);
        string line;
        while (getline(inFile, line)) {
            processTransaction(line);
        }
        inFile.close();
    }
 
};
