#!/bin/bash

# clear the previous test results
: > results.txt
: > log.txt

cd Test
transactions=( Login Logout Create Delete Advertise Bid Refund AddCredit )

# for each transaction to test
for i in "${transactions[@]}"
do
  echo "$i:"
  # for each test case
  for j in $i/inputs/*
  do
    filename=$(basename $j)
    
    # delete the daily transaction file before running program
    : > "$i/DTF/$filename"
    touch "$i/DTF/$filename"

    # run program with alternate io files
    ../src/a.out currentusers.txt available-items.txt $i/DTF/$filename < $j > $i/DTF/$filename
    
    # find if there's a difference between expected output and generated output
    if diff $i/TestOutput/$filename $i/Output/$filename;
    then
      echo " $filename Result: Good Output" >> ../results.txt
    else
      echo " $filename Result: Bad Output" >> ../results.txt
    fi
  done
done