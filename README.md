# ATM Emulator
The application emulates the operation of an ATM.
The user must enter a valid card number corresponding to the pattern and the correct PIN code to access the account. After successful login, the user can:
- check card balance;
- withdraw funds from the account, not more than the amount on the current account or not exceeding the limit of funds in an ATM;
- deposit funds (the amount of deposit should not exceed 1,000,000).

### Additional:
- data storage is implemented in text files with a space separator (“”);
- card number should be of the form: “XXXX-XXXX-XXXX-XXXX”;
- the program retains its state after completion (the data file is updated);
- All messages about successful actions and errors are displayed on the console.

Databases are called "atm_db" and "cards_credentials"
Structure of DB's:
-cards_credentials:
| Card Number   | Pin Code |
| ------------- | ------------- |
| 1111-2222-3333-4444  | 1111  |
| 1111-1111-1111-1111  | 12345  |

-atm_db:
| Card Number   | Balance | Timestamp   | isCardAcvive |
| ------------- | ------------- | ------------- | ------------- |
| 1111-2222-3333-4444  | 120  | 148103111550  | true  |
| 1111-1111-1111-1111  | 300  | 148103111550  | false  |