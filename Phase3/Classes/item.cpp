#include <iostream>
#include <ctime>    
#include <time.h>
#include <string>
#include "item.h"

 void item:: createitem()
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

 void item:: showitem()
{
  cout <<"product id: "<<product_id<<endl;
  cout <<"product name: "<< itemName<<endl;
  cout <<"price:"<< itemPrice<<endl;
  cout <<"product description: "<< itemDescription<<endl;
  cout <<"product stock: "<<product_stock<<endl;

}
void makebid(string name){

  item item;
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

//Refund function
// Should allow a buyer to request a refund from a seller based on an item they already purchased.

void item:: refundRequest(string itemName)
{
  item item;
  int userinput;
  cout << "Would you like to get a refund? (Yes: 1 , No: 2)" << endl;
  cin >> userinput;
  
  // If the user chooses to refund something, ask for an input for the name of the item.
  if(userinput == 1)
  {
    cout << "Please enter the name of the item you want a refund for:" << endl;
    cin >> userinput;

    //TODO: Make sure the item is already in the user's inventory
    if (itemName == item.itemName){
         cout << "Refund issued for " << item.itemName << ", at the price of $" << item.itemPrice << "." << endl;

    // Ask the user to confirm the refund
    cout << "Confirm: 1 , Cancel: 2" << endl;
    cin >> userinput;

    // If the user confirms the refund, then the refund request happens.
    if(userinput == 1)
    {
      cout << "Refund request completed!" << endl;
    }
    // If the user cancels the refundm send them back to the previous menu.
    else if(userinput == 2)
    {
      cout << "Refund request cancelled." << endl;
      // how do we return to main menu?
    }
    // If the user entered an invalid option, display an error message.
    else
    {
      cout << "Invalid option. Please try again." << endl;
    }

    }
    // Refund amount should be the same as the price of the item
 
  }
  // If the user does not want to refund, send them back to the previous menu.
  else if(userinput == 2)
  {
    cout << "Refund request cancelled." << endl;
    // how do we return to main menu?
  }
  // If the user entered an invalid option, display an error message.
  else
  {
    cout << "Invalid option. Please try again." << endl;
  }
}