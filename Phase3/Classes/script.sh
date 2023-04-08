#!/bin/bash

# Compile the program
g++ Account.cpp User.cpp AuctionSystem.cpp Main.cpp -o auction-system

# Test create account
echo "Testing create account"
./auction-system current-accounts.txt available-items.txt transactions.txt createaccount john 1234

# Test delete account
echo "Testing delete account"
./auction-system current-accounts.txt available-items.txt transactions.txt deleteaccount john

# Test change password
echo "Testing change password"
./auction-system current-accounts.txt available-items.txt transactions.txt changepassword alice 1234 newpassword

# Test login and process transactions
echo "Testing login and process transactions"
./auction-system current-accounts.txt available-items.txt transactions.txt login alice 1234

# Test create user after login
echo "Testing create user after login"
./auction-system current-accounts.txt available-items.txt transactions.txt createuser bob 4321

# Test change password after login
echo "Testing change password after login"
./auction-system current-accounts.txt available-items.txt transactions.txt changepassword alice 1234 newpassword2

# Cleanup
rm auction-system
