#include <iostream>
#include <ctime>    
#include <time.h>
 
using namespace std;

class User {       // The class
  public:             // Access specifier
    string Username;        // Attribute 
    string Password;  // Attribute 
    void isAuth(string Username, string Password); //Function (bool function which will authenticate user login)
    void Delete();
};

class Bid {       // The class
  public:             // Access specifier
    string itemName;        // Attribute 
    double bidPrice;  // Attribute 
    double bidExpiry;

    void isAuth(string Username, string Password); //Function (bool function which will authenticate user login)
};

class Transaction{
  public:
   string commands[3] = {"Bid","Refund","AddCredit" };
   void doCommand(int command);
};

//TODO: create a method which is able to authenticate user and what type of user has logged in.
void User::isAuth(string Username, string Password) {
  cout << "User is authenticated"<< endl;
}

//TODO: receive command from user and perform the transaction commands
void Transaction::doCommand(int command) {
  for (int i = 0; i < 3; i++) {
      if(command-1 == i){
            cout <<"You have selected: " <<commands[i]<<endl;
            break;
        }
  }
 
}
 
int main()
{
    User user1;
    string outputloginmsg = "Welcome User! Please log back in to get started";
    Transaction d;
    int userinput;
 
    cout << outputloginmsg <<" -----------------------------------------------------------------------" <<endl;
 
    cout << "Enter Username:";
    cin >> user1.Username;
    cout << "Enter Password:";
    cin >> user1.Password;

    // Call isauth to verify user. If User is authenticated move to next set of input/output statements
    user1.isAuth(user1.Password, user1.Username);

    cout << "Enter a transaction command(Bid: 1 , Refund: 2, AddCredit: 3): ";
    cin >> userinput;
    d.doCommand(userinput);


   







    return 0;


}