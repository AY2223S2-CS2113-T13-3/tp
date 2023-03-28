### BankWithUs User Guide

--------------------------------------------------------------------------------------------------------------------

## Quick start guide

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `BankWithUs.jar` from [here](https://github.com/AY2223S2-CS2113-T13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your BankWithUs app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar BankWithUs.jar` command to run the application.<br>

5. Follow the instruction as per the CLI and create your new account. e.g. typing **`help`** and pressing Enter will open the help option.<br>
   Some example commands you can try:

       * `view-transactions-all` : Lists all transactions which has been committed in the app (a record)

       * `withdraw 300` : withdraws $300 from the current balance. (subsitute 300 for other numbers)

       * `delete` : deletes the current account which the user has initially created.

       * `deposit 100` : Deposits $100 into the users account. (subsitute 100 for other numbers)

       * `exit` : Exits the application

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `deposit AMOUNT`, `AMOUNT` is a parameter which can be used as `deposit 300`.

* Parameters MUST be in the specified order for the command to take appropriate effect.<br>


* Extraneous parameters for commands that do not take in parameters (such as `help`, `view-transactions-all`, `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a list of all the commands available and what they attempt to do.

Format: `help`
### Adding an account: `add-account`

Initiates Adding a new account. When calling the command there is no requirement for any parameter.
However, once the command starts, it will lead you through the process of a new account creation
prompting your name and balance to be stored.

Format: `add-account` <br />
CLI prompts: "Whats your name?" <br />
your input: `NAME` <br />
CLI prompts: "How much would you like to add as Balance?" <br />
your input: `BALANCE` Note: Balance has to be a valid number. <br />




Examples:

```agsl
add-account
>>What is your name?
Steve
>>How much would you like to add as Balance?
1000
```


### List all transactions: `view-transactions-all`

Shows a list of all the transactions processed by all the accounts of the user.

Format: `view-transactions-all`

Example:
```agsl
>>view-transactions-all
Account Name: jenson Transaction Type: deposit Amount: 100 Date: 26/03/2023
Account Name: jenson Transaction Type: withdraw Amount: 10 Date: 26/03/2023
```


### Depositing amount : `deposit`

Deposits AMOUNT into the *current account*.

Format: `deposit AMOUNT`

Examples:
`deposit 100`


### switch current account: `switch-to`

Switches from the *current account* to the new requested account, if it exists.

Format: `switch-to NAME`

* The search is case-insensitive. e.g `jane` will match `Jane`
* Only the name is searched.
* Only full words will be matched e.g. `Ben` will not match `Benjamin`



Examples:
```
switch-to jenson
```


### Add a savings Goal to the current account: `save`

Adds a savings goal to the *current account*. <br />
Will raise an alert when attempting to withdraw more than the savings goal, if attempt is before deadline

Format: `save AMOUNT` >> will then prompt the user for the deadline.

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

### Deleting an account : `delete`


Format: `delete`

* Deletes the current account.
* if you want to delete a different account, switch to that account and run delete.

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


### Withdraw : `withdraw`

Withdraws AMOUNT from the users balance.

Format: `withdraw AMOUNT`

Example:
```agsl
withdraw 500
```

* cannot withdraw more than balance.
* cannot withdraw negative or non numbers.


### View Accounts : `view-account`

Shows a list of all the available accounts--account name and balance.

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
----------------------------
```


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

BankWithUs data is automatically stored when user exist the program and there is no manual inteference required.

### Editing the data files

Editing the savefile data is highly not recommended, and users should attempt to only do so at their own discretion.

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

| Action                      | Format, Examples     |
|-----------------------------|----------------------|
| **Add an Account**          | `add-account`        |
| **swicth to a new account** | `switch-to ACC_NAME` |
| **Delete an account**       | `delete`             |
| **withdraw some amount**    | `withdraw AMOUNT`    |
| **Add a savings goal**      | `save AMOUNT`        |
| **Add a withdraw Limit**    | `set-wl`             |
| **Help**                    | `help`               |