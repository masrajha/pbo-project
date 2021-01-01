# Account Holder System
> Sistem dapat digunakan untuk transaksi perekaman data pemegang akun di bank, dimana terdapat 2 jenis akun yaitu individual dan corporate.
> Setiap Pemegang Akun dapat memiliki 1 atau lebih  akun.
> 
## Desain
### Class Diagram

```mermaid
classDiagram
    AccountHolder <|-- IndividualHolder
    AccountHolder <|-- CorporateHolder
    AccountHolder "1"--o"*" Account : has
    
    class AccountHolder{
      <<abstract>>
      #int ID
      #String name
      #String address
      +int nextID()
    }
    
    class IndividualHolder{
      -String SSN
      -String birthdate
    }
    class CorporateHolder{
      -String contact
    }
    class Account{
      -double balanace
      +deposite(double amt)
      +withdraw(double amt)
      +double getBalance()
    }
            
```

### ER Diagram

```mermaid
erDiagram
          ACCOUNTHOLDER ||..|| INDIVIDUAL-HOLDER : is
          ACCOUNTHOLDER ||--|| CORPORATE-HOLDER : is
          ACCOUNTHOLDER ||--|{ ACCOUNT: "has"
          ACCOUNTHOLDER {
            int id
            string name
            string adress
          }
          INDIVIDUAL-HOLDER{
            string SSN
            string birthdate
          }
          CORPORATE-HOLDER{
            string contact
          }
          ACCOUNT{
            int acc_number
            double balance
          }
```
