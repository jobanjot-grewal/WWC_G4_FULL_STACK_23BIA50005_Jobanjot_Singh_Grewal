class Account:
    def __init__(self, account_number, account_holder, initial_balance=0):
        self.account_number = account_number
        self.account_holder = account_holder
        self.balance = initial_balance

    def deposit(self, amount):
        if amount > 0:
            self.balance += amount
            print(f"Deposited: ${amount:.2f}. New balance: ${self.balance:.2f}")
            return True
        print("Invalid amount. Deposit failed.")
        return False

    def withdraw(self, amount):
        if 0 < amount <= self.balance:
            self.balance -= amount
            print(f"Withdrawn: ${amount:.2f}. New balance: ${self.balance:.2f}")
            return True
        print("Insufficient funds or invalid amount.")
        return False

    def get_balance(self):
        return self.balance

    def __str__(self):
        return f"Account: {self.account_number} | Holder: {self.account_holder} | Balance: ${self.balance:.2f}"


class Bank:
    def __init__(self):
        self.accounts = {}

    def create_account(self, account_number, account_holder):
        if account_number not in self.accounts:
            self.accounts[account_number] = Account(account_number, account_holder)
            print(f"Account created for {account_holder}")
            return True
        print("Account already exists.")
        return False

    def get_account(self, account_number):
        return self.accounts.get(account_number)

    def display_account(self, account_number):
        account = self.get_account(account_number)
        if account:
            print(account)
        else:
            print("Account not found.")


def main():
    bank = Bank()
    
    while True:
        print("\n--- Banking App ---")
        print("1. Create Account")
        print("2. Deposit")
        print("3. Withdraw")
        print("4. Check Balance")
        print("5. Exit")
        
        choice = input("Select option: ")
        
        if choice == "1":
            acc_num = input("Enter account number: ")
            holder = input("Enter account holder name: ")
            bank.create_account(acc_num, holder)
        
        elif choice == "2":
            acc_num = input("Enter account number: ")
            account = bank.get_account(acc_num)
            if account:
                amount = float(input("Enter deposit amount: $"))
                account.deposit(amount)
            else:
                print("Account not found.")
        
        elif choice == "3":
            acc_num = input("Enter account number: ")
            account = bank.get_account(acc_num)
            if account:
                amount = float(input("Enter withdrawal amount: $"))
                account.withdraw(amount)
            else:
                print("Account not found.")
        
        elif choice == "4":
            acc_num = input("Enter account number: ")
            bank.display_account(acc_num)
        
        elif choice == "5":
            print("Thank you for using Banking App!")
            break
        
        else:
            print("Invalid option. Try again.")


if __name__ == "__main__":
    main()