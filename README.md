# Account Holder System
> Sistem dapat digunakan untuk transaksi perekaman data pemegang akun di bank, dimana terdapat 2 jenis akun yaitu individual dan corporate.
> Setiap Pemegang Akun dapat memiliki 1 atau lebih  akun.
> 
## Desain
To view the diagrams below install mermaid-diagram plugin at https://github.com/Redisrupt/mermaid-diagrams 
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
      -String gender
      -String birthdate
    }
    class CorporateHolder{
      -String contact
    }
    class Account{
      -double balance
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
            string gender
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
### Design Class Diagram for JavaFX and Database
```mermaid
classDiagram
    AccountHolder <|-- IndividualHolder
    AccountHolder <|-- CorporateHolder
    AccountHolder "1"--o"*" Account : has
    AccountHolder --o AccountHolderDataModel : Data Modeling
    AccountHolderDataModel .. AccountHolderController : Data Control
    AccountHolderDataModel -- DBHelper : DB Connection
    AccountHolderController -- AccountHolderForm : Form Control      

    class AccountHolder{
      <<abstract>>
      #IntegerProperty ID
      #StringProperty name
      #StringProperty address
      #IntegerProperty numAccounts
      
      +IntegerProperty nextID()
    }
    
    class IndividualHolder{
      -StringProperty gender
      -StringProperty birthdate
    }
    class CorporateHolder{
      -StringProperty contact
    }
    class Account{
      -DoubleProperty balance
      +deposite(double amt)
      +withdraw(double amt)
      +double getBalance()
    }

    class AccountHolderDataModel{
        Connection conn
        addAccountHolder()
        getIndividualHolders()
        getCorporateHolders()
        nextAccountHolderID()
    }

    class AccountHolderController{
        initialize()
        handleButtonAddAccount()
        loadDataIndividualHolder()
        loadDataCorporateHolder()
        loadDataAccount()
        handleClearForm()
    }
    class DBHelper{
        getConnection()
        getConnection(String driver)
        createTable();
    }
            
```