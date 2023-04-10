frontend="./Frontend1"

# compile frontend
g++ $frontend/main.cpp

# clear previous content in files
: > currentusers.txt
: > availableitems.txt

# set file content
text="Username    FS 001110.00
buyUsername     BS 009999.99
sellUsername    SS 000000.00
adminUsername   AA 000050.00"
echo "$text" > currentusers.txt

text="pockypack10x          User        Username    010.00
socks            exampleUsername someBidder      005.00"
echo "$text" > availableitems.txt

# run the daily script 5 times
for i in {1..5}
do
	foldername="day$i"
	./dailyscript.sh $foldername
done