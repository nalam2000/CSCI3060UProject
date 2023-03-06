#include <iostream>
#include <ctime>    
#include <time.h>
 
using namespace std;
// global variable
string bidname;

class User {       // The class
  public:             // Access specifier
    string Username;        // Attribute 
    string Password;  // Attribute 
    void isAuth(string Username, string Password); //Function (bool function which will authenticate user login)
    void Delete();
    void create();
};

class product
{
private:
  /* data */
public:
  int product_id;
  string itemName;
  float itemPrice;
  string itemDescription;
  int product_stock = 0;
  double bidExpiry;

//  product(int id, string Name, float price,string description){
//     product_id = id;
//     itemName = Name;
//     itemPrice = price;
//     itemDescription = description;


//  }
   void createitem();
   void showitem();
   int getProduct(){return product_id;};
   string getitemname(){return itemName;};
   float getPrice(){return itemPrice;};
   string getitemdescrtiption(){return itemDescription;};
   int getProductStock(){return product_stock;};

};

 void product:: createitem()
{
 
  cout <<"Enter a unique product id"<<endl;
  cin >> product_id;
  cout <<"Enter a unique product name"<<endl;
  cin >> itemName;
  cout <<"Enter a unique product price"<<endl;
  cin >> itemPrice;
  cout <<"Enter a unique product description"<<endl;
  cin >> itemDescription;
  product_stock++;
 


}
 void product:: showitem()
{
  cout <<"product id: "<<product_id<<endl;
  cout <<"product name: "<< itemName<<endl;
  cout <<"price:"<< itemPrice<<endl;
  cout <<"product description: "<< itemDescription<<endl;
  cout <<"product stock: "<<product_stock<<endl;

}

class Bid{
  public:
  float min_bid;
  void makebid(string name);


};


//TODO: create a method which is able to authenticate user and what type of user has logged in.
void User::isAuth(string Username, string Password) {
  cout << "User is authenticated"<< endl;
}
//TODO: create user by making more user objects 
void User::create(){}
//TODO: Be able to delete User by deleting user objects.
void User::Delete(){}
void Bid::makebid(string name){
  
  product item;
  float bid;
  while (name != "n"){
    cout << "Enter Product name of item which you would like to bid on"<<endl;
    cin >> name;

    if (name == item.itemName){
      cout << "Enter Mininum Bid: "<<endl;
      cin >> bid;
      if (bid > item.itemPrice){
        cout <<"Bid is greater than previous highest bid. Bid is successful"<<endl;
        // Swap previous highest bid for item with new bid
        item.itemPrice = bid;
        
        //Update Quantity of Item
        item.product_stock-=1;
      }

    }else{
      if(name != item.itemName){
        cout <<"No such product exists in database"<<endl;
      }
    }

    
  }
  

  
}
//TODO: Display Bid Status for an item placed by user. User will login and navigate to item and check bid status on that item
//TODO: receive command from user and perform the transaction commands

 
int main()
{
  //TODO: implement main function/program in a loop until user decides to exit.
    User user1;
    string outputloginmsg = "Welcome User! Please log back in to get started";
 
    int userinput;
    product item;
    
 
    cout << outputloginmsg <<" -----------------------------------------------------------------------" <<endl;
 
    cout << "Enter Username:";
    cin >> user1.Username;
    cout << "Enter Password:";
    cin >> user1.Password;

    // Call isauth to verify user. If User is authenticated move to next set of input/output statements
    user1.isAuth(user1.Password, user1.Username);

    cout << "Enter a transaction command(Bid: 1 , Refund: 2, AddCredit: 3, Exit: 4): ";
    cin >> userinput;

    if (userinput == 1){
        cout << "Enter the item name you would like to bid on(type n to exit): " <<endl;
        cin >> bidname;
        Bid b;
        b.makebid(bidname); 

    }
    if (userinput == 2){
      while (true){
          item.createitem();
          string input;
          cout << "Would you like to continue?"<<endl;
          cin >> input;
          if (input == "n"){
            item.showitem();
            break;
          }else{
            if (input == "y"){
              continue;
            }
          }
      }

    }
    // Perform commands inputed by user
    
    // switch(userinput) {
    //   case 1:
    //     while (true){
    //       item.createitem();
    //       string input;
    //       cout << "Would you like to continue?"<<endl;
    //       cin >> input;
    //       if (input == "n"){
    //         item.showitem();
    //         break;
    //       }else{
    //         if (input == "y"){
    //           continue;
    //         }
    //       }
    //   }
    //   case 2: 
    //     cout << "Enter the item name you would like to bid on(type n to exit): " <<endl;
    //     cin >> bidname;
    //     Bid b;
    //     b.makebid(bidname); 
   
    // }

    return 0;



}