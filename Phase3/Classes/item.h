#include <iostream>
#include <ctime>    
#include <time.h>
#include <string>

using namespace std;
class item
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
   void makebid(string name);
   int getProduct(){return product_id;};
   string getitemname(){return itemName;};
   float getPrice(){return itemPrice;};
   string getitemdescrtiption(){return itemDescription;};
   int getProductStock(){return product_stock;};
   void refundRequest(string itemName);
   void refundReply(string itemName);

};