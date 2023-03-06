#!/bin/bash

# Create a temporary directory for testing
TEST_DIR="/tmp/auction-system-test"
mkdir -p $TEST_DIR
cd $TEST_DIR

# Create sample data files for testing
cat <<EOF >current-accounts.txt
100001 500
100002 1000
100003 200
EOF

cat <<EOF >available-items.txt
Item A 100
Item B 50
Item C 200
EOF

cat <<EOF >transactions.txt
100001 Item A 10
100002 Item B 20
100003 Item C 5
EOF

# Test readCurrentAccounts function
echo "Testing readCurrentAccounts function..."
echo "$(./auction-system current-accounts.txt available-items.txt transactions.txt)" | grep -q "100001 500" || echo "readCurrentAccounts test failed"

# Test readAvailableItems function
echo "Testing readAvailableItems function..."
echo "$(./auction-system current-accounts.txt available-items.txt transactions.txt)" | grep -q "Item A 100" || echo "readAvailableItems test failed"

# Test createAccount function
echo "Testing createAccount function..."
echo -e "exampleuser\nexamplepassword" | ./auction-system current-accounts.txt available-items.txt transactions.txt | grep -q "Account created successfully" || echo "createAccount test failed"

# Test deleteAccount function
echo "Testing deleteAccount function..."
echo -e "exampleuser\nexamplepassword" | ./auction-system current-accounts.txt available-items.txt transactions.txt > /dev/null
echo -e "exampleuser" | ./auction-system current-accounts.txt available-items.txt transactions.txt | grep -q "Account deleted successfully" || echo "deleteAccount test failed"

# Test changePassword function
echo "Testing changePassword function..."
echo -e "exampleuser\nexamplepassword" | ./auction-system current-accounts.txt available-items.txt transactions.txt > /dev/null
echo -e "exampleuser\nnewpassword" | ./auction-system current-accounts.txt available-items.txt transactions.txt | grep -q "Password changed successfully" || echo "changePassword test failed"

# Test processTransactions function
echo "Testing processTransactions function..."
echo "$(./auction-system current-accounts.txt available-items.txt transactions.txt)" | grep -q "100001 490" || echo "processTransactions test failed"

# Test writeUpdatedAccounts function
echo "Testing writeUpdatedAccounts function..."
./auction-system current-accounts.txt available-items.txt transactions.txt > /dev/null
echo "$(cat updated-accounts.txt)" | grep -q "100001 490" || echo "writeUpdatedAccounts test failed"

# Clean up temporary directory
cd ..
rm -rf $TEST_DIR
