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
    void create();
};

class Transaction{
  public:
   string commands[4] = {"Bid","Refund","AddCredit", "Exit" };
    string itemName;        // Attribute 
    double bidPrice;  // Attribute 
    double bidExpiry;

  void checkBidStatus(string Username, string Password); //Function (bool function which will authenticate user login)
   void doCommand(int command);
};

//TODO: create a method which is able to authenticate user and what type of user has logged in.
void User::isAuth(string Username, string Password) {
  cout << "User is authenticated"<< endl;
}
//TODO: create user by making more user objects 
void User::create(){}
//TODO: Be able to delete User by deleting user objects.
void User::Delete(){}
//TODO: Display Bid Status for an item placed by user. User will login and navigate to item and check bid status on that item
void Transaction::checkBidStatus(string Username, string Password){}

//TODO: receive command from user and perform the transaction commands
void Transaction::doCommand(int command) {
  for (int i = 0; i < 4; i++) {
      if(command-1 == i){
            cout <<"You have selected: " <<commands[i]<<endl;
            //TODO: Call a function to do the subsequent command picked.
        }else{
          if(command-1 == 4){
             cout <<"You have decided to logout. Goodbye."<<endl;

          }
        }
  }
 
}
 
int main()
{
  //TODO: implement main function/program in a loop until user decides to exit.
    User user1;
    string outputloginmsg = "Welcome User! Please log back in to get started";
    Transaction item;
    int userinput;
    
 
    cout << outputloginmsg <<" -----------------------------------------------------------------------" <<endl;
 
    cout << "Enter Username:";
    cin >> user1.Username;
    cout << "Enter Password:";
    cin >> user1.Password;

    // Call isauth to verify user. If User is authenticated move to next set of input/output statements
    user1.isAuth(user1.Password, user1.Username);

    cout << "Enter a transaction command(Bid: 1 , Refund: 2, AddCredit: 3, Exit: 4): ";
    cin >> userinput;

    // Perform commands inputed by user
    item.doCommand(userinput);
   


    return 0;


}