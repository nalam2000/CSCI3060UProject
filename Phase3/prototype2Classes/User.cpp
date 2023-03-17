#include <iostream>
#include <string>
#include <unordered_map>
#include "Account.h"
#include "Account.cpp"
using namespace std;

class User {
private:
    string username;
    string password;
    
public:
    User(string uname, string pass): username(uname), password(pass) {}
    
    string getUsername() const {
        return username;
    }
    
    string getPassword() const {
        return password;
    }
    
    // Create a new user account
    static void create(string username, string password, unordered_map<string, User> &users) {
        if (users.find(username) != users.end()) {
            cout << "Error: username already exists" << endl;
        } else {
            users[username] = User(username, password);
            cout << "Account created successfully" << endl;
        }
    }
    
    // Delete a user account
    static void remove(string username, unordered_map<string, User> &users) {
        if (users.find(username) == users.end()) {
            cout << "Error: username does not exist" << endl;
        } else {
            users.erase(username);
            cout << "Account deleted successfully" << endl;
        }
    }
    
    // Change the password of a user account
    static void changePassword(string username, string password, unordered_map<string, User> &users) {
        if (users.find(username) == users.end()) {
            cout << "Error: username does not exist" << endl;
        } else {
            users[username] = User(username, password);
            cout << "Password changed successfully" << endl;
        }
    }
         void AddCredit(string username,unordered_map<string, User> &users ){
            double amount;
         if (users.find(username) != users.end()){
            cout << "Enter Amount: ";
            cin >> amount;
            cout << "Amount added.";


         }

    }
    
    // Read user accounts from a file and store them in an unordered_map
    static unordered_map<string, User> readUsers(string filename) {
        unordered_map<string, User> users;
        ifstream inFile(filename);
        string line;
        while (getline(inFile, line)) {
            string username = line.substr(0, line.find(' '));
            string password = line.substr(line.find(' ') + 1);
            users[username] = User(username, password);
        }
        inFile.close();
        return users;
    }
  
};
