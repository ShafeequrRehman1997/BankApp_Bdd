Feature: Create new Account
Create new Account for the valid customers
Scenario: For valid customers create new account
Given customer details
When  Valid Customer
And   Valid Opening Balance
Then  create new Account
Scenario: For Invalid Customer
Given Customer details
When Invalid Customer
Then throw 'Invalid Customer' error message

Scenario: For Invalid Opening Balance
Given  Customer details and Opening Balance
When   Invalid Opening Balance
Then throw 'Invalid Balance' error message

Scenario: Find account details
find account details for the given account number
Given Account number
When valid account number
Then find account details

Scenario: Withdraw amount from account
find account details and withdraw amount
Given Account number 1001 and amount 1000
When Find account and do withdrawal
Then update the account

Scenario: Deposit amount from account
find account details and deposit amount
Given Account number 1001 and amount 1000
When Find account and deposit
Then update the account balance