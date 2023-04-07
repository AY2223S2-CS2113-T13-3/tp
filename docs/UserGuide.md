# BankWithUs User Guide

--------------------------------------------------------------------------------------------------------------------

## Quick start guide

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `BankWithUs.jar` from [here](https://github.com/AY2223S2-CS2113-T13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your BankWithUs app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar BankWithUs.jar` command to run the application.<br>

5. Follow the instruction as per the CLI and create your new account. e.g. typing **`help`** and pressing Enter will open the help option.<br>
   Some example commands you can try:

       * `view-transactions-all` : Lists all recorded transactions.

       * `withdraw 300` : Withdraws $300 from the current balance. (Subsitute 300 for other numbers)

       * `delete` : Deletes the current account which the user has initially created.

       * `deposit 100` : Deposits $100 into the users account. (Subsitute 100 for other numbers)

       * `exit` : Exits the application.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `deposit AMOUNT`, `AMOUNT` is a parameter. `deposit 300` is an example of a valid command syntax.

* Parameters MUST be in the specified order for the command to take appropriate effect.<br>


* Extraneous parameters for commands that do not take in parameters (such as `help`, `view-transactions-all`, `exit`, `add-account`) will be ignored.<br>
  e.g. If the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a list of all the commands available and what they do.

Format: `help`

Example:
```
>>help
----------------------------
help: displays the current menu
view-account: shows all the accounts' name and balance
withdraw <amount>: withdraws <amount> from available balance
deposit <amount>: deposits <amount> and adds deposit to balance
set-save-goal <amount> <date in dd-mm-yyyy format>: sets a saveGoal of <amount> until <date>
show-save-goal: shows the current save goal and the deadline
add-account: adds a new account
switch-to <account username>: switches to <account username> account
delete <account username>: deletes the account with username <account username>
set-wl <amount>: sets <amount> to be the withdrawal limit
check-wl: shows the withdrawal limit and the amount of money withdrawn this month
view-transactions-all: views all transactions across all accounts
exit: quits program and saves
```

### Adding an account: `add-account`

Initiates the process of adding a new account. The user is not required to input any parameters when calling the command.
However, once the command starts, it will prompt the user for their name and initial balance.

Format: `add-account` <br />
CLI prompts: "Whats your name?" <br />
your input: `NAME` <br />
CLI prompts: "How much would you like to add as Balance?" <br />
your input: `BALANCE` Note: Balance has to be a valid number. <br />




Examples:

```
add-account
>>What is your name?
Steve
>>How much would you like to add as Balance?
1000
Account created!
Name: Steve
Balance: $1000
```

### Switch current account: `switch-to`

Switches from the *current account* to the new requested account, if it exists.

Format: `switch-to NAME`

* The search is case-sensitive. e.g `jane` will NOT match `Jane`
* Only the name is searched.
* Only full words will be matched e.g. `Ben` will not match `Benjamin`



Examples:
```
switch-to jenson
Current Account switched
----------------------------
Current Account is:
----------------------------
Name: jenson
Balance: $90
```


### Deleting an account : `delete`


Format: `delete`

* Deletes the current account.
* If you want to delete a different account, switch to that account and run delete.

Examples:
```
Current Account is:
----------------------------
Name: jameson
Balance: $1000
----------------------------
>>delete
```
Deletes the jameson account.

### View Accounts : `view-account`

Shows a list of all the available accounts with their account name and balance.

Format: `view-account`

Example:
```
view-account
Current Account:
Name: james
Balance: $1000
----------------------------
Name: john
Balance: $300
```

### Depositing amount : `deposit`

Deposits AMOUNT into the *current account*.

Format: `deposit AMOUNT`

Example:
```
deposit 100
New deposit added!
You have $190 remaining!
```

* Cannot deposit negative numbers.
* Cannot deposit arguments that are not numbers.

Attempting to do so will show error messages.


### Withdraw : `withdraw`

Withdraws AMOUNT from the users balance.

Format: `withdraw AMOUNT`

Example:
```
withdraw 500
```

* Cannot withdraw more than balance.
* Cannot withdraw negative numbers.
* Cannot withdraw arguments that are not numbers.

Attempting to do so will show error messages.

See [saving goals](#add-a-savings-goal-to-the-current-account-save) and [withdrawal limit]() for more information.

### List all transactions: `view-transactions-all`

Shows a list of all the transactions processed by all the accounts of the user.

Format: `view-transactions-all`

Example:
```
>>view-transactions-all
Account Name: jenson Transaction Type: deposit Amount: 100 Date: 26/03/2023
Account Name: jenson Transaction Type: withdraw Amount: 10 Date: 26/03/2023
```

### Add a savings Goal to the current account: `set-save-goal`

Adds a savings goal to the *current account*. <br />
Will raise an alert when attempting to withdraw more than the savings goal, if attempt is before deadline

Format: `set-save-goal AMOUNT` >> will then prompt the user for the deadline.

* The amount to save has to be a number, -ve numbers allowed -- can be used to overwrite as no savings goal
* Deadline entered has to be in dd-mm-YYYY format. No other format will be entertained by the program
* Any error will only be displayed after the deadline is received and processed.

Examples:
```
save 300
>>What would be the end date for the duration of your Saving goal?
11-11-2011
>>Try saving a minimum of $300 until 11-11-2011
>>Save Goal has been created, Have fun staying frugal!
```

### Set a withdrawal limit: `set-wl`

Adds a withdrawal limit to the *current account*. <br />
Will prevent the user from making the withdrawal if it will result in them exceeding the withdrawal limit in the current month. The amount of withdrawals will reset at the beginning of every month.

Format: `set-wl AMOUNT` will set `AMOUNT` as the withdrawal limit.

* AMOUNT must be a valid number. If AMOUNT is not valid, an error message will be shown.

```
set-wl 100
Withdrawal limit set to $100!
----------------------------
withdraw 1000
Apologies! Your transaction did not go through as it will result
in you exceeding your withdrawal limit!
Withdrawal limit is currently $100.
You have withdrawn $0 this month.
```

### Check the current withdrawal limit: `check-wl`

Shows the current withdrawal limit and the total amount of money withdrawn in the current month.

Format: `check-wl`

Example:
```
set-wl 100
Withdrawal limit set to $100!
----------------------------
check-wl
Withdrawal limit is currently $100
You have withdrawn $0 this month.
----------------------------
withdraw 50
Withdrawal successful!
You have $9950 remaining!
----------------------------
check-wl
Withdrawal limit is currently $100.
You have withdrawn $50 this month.
```

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

BankWithUs data is automatically stored when user exits the program. No manual inteference is required.

### Editing the data files

Please do not edit the data files.

<div class="warning" style="color: red; background-color: #f2f2f2; padding: 10px;">
<strong>Warning:</strong> <br>
If you attempt to manipulate the data files (save.txt and transaction.txt) 
and corrupted data is being parsed, you will be thrown exceptions.
If this inhibits the program from running, 
please delete the existing files and run program again. 
Please keep in mind that this will result in irreversible data loss.
</div>



--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                        | Format, Examples        |
|-------------------------------|-------------------------|
| **Add an Account**            | `add-account`           |
| **switch to a new account**   | `switch-to ACC_NAME`    |
| **Delete an account**         | `delete`                |
| **withdraw some amount**      | `withdraw AMOUNT`       |
| **Add a savings goal**        | `set-save-goal`         |
| **Show the save Goal**        | `show-saveGoal`         |
| **Add a withdraw Limit**      | `set-wl`                |
| **View all the transactions** | `view-transactions-all` |
| **Help**                      | `help`                  |