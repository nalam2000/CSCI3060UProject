#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <unordered_map>
using namespace std;

unordered_map<string, int> readAccounts(string filename){
	unordered_map<string, int> accounts;
	ifstream inFile(filename);
	string line;
	while (getline(inFile, line))
	{
		string accountNumber = line.substr(0, 6);
		int accountBalance = stoi(line.substr(7));
		accounts[accountNumber] = accountBalance;
	}

	inFile.close();
	return accounts;
}

void createAcc(string username, string password, unordered_map<string, string> &accounts){
	if (accounts.find(username) != accounts.end()){
		cout << "Error: username already exists" << endl;
	}
	else{
		accounts[username] = password;
		cout << "Account created successfully" << endl;
	}
}

void deleteAcc(string username, unordered_map<string, string> &accounts){
	if (accounts.find(username) == accounts.end()){
		cout << "Error: username does not exist" << endl;
	}
	else{
		accounts.erase(username);
		cout << "Account deleted successfully" << endl;
	}
}

void changePassword(string username, string password, unordered_map<string, string> &accounts){
	if (accounts.find(username) == accounts.end()){
		cout << "Error: username does not exist" << endl;
	}
	else{
		accounts[username] = password;
		cout << "Password changed successfully" << endl;
	}
}

vector<string> readItems(string filename){
	vector<string> items;
	ifstream inFile(filename);
	string line;
	while (getline(inFile, line)){
		items.push_back(line);
	}

	inFile.close();
	return items;
}

void processTransaction(string transaction, unordered_map<string, int> &accounts, vector<string> &items){
	// TODO: Implement transaction processing logic
}

void processTransactions(string filename, unordered_map<string, int> &accounts, vector<string> &items){
	ifstream inFile(filename);
	string line;
	while (getline(inFile, line))
	{
		processTransaction(line, accounts, items);
	}

	inFile.close();
}

void writeAccounts(string filename, unordered_map<string, int> &accounts)
{
	ofstream outFile(filename);
	for (auto account: accounts)
	{
		outFile << account.first << " " << account.second << endl;
	}

	outFile.close();
}

int main(int argc, char *argv[]){
	if (argc != 4){
		cout << "Usage: auction-system<current-accounts-file><available-items-file><transactions-file>" << endl;
		return 1;
	}

	unordered_map<string, int> accounts = readAccounts(argv[1]);
	vector<string> items = readItems(argv[2]);

	processTransactions(argv[3], accounts, items);
    writeAccounts("updated-accounts.txt", accounts);

	string username, password;
	cout << "Please enter your username: ";
	getline(cin, username);
	cout << "Please enter your password: ";
	getline(cin, password);

	unordered_map<string, string> userAccounts;
	userAccounts["shane"] = "shane";
	if (userAccounts[username] == password)
	{
		cout << "Login successful" << endl;
		processTransactions(argv[3], accounts, items);
		writeAccounts("updated-accounts.txt", accounts);
	}
	else
	{
		cout << "Error: Invalid login information" << endl;
	}

	return 0;

}