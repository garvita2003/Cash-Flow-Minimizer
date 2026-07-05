# Cash Flow Minimizer 💰

## 📌 Introduction

**Cash Flow Minimizer** is a Java-based system that optimizes financial transactions among multiple banks worldwide. It minimizes the total number of transactions by calculating net amounts and intelligently routing payments through a World Bank intermediary when banks don't share common payment modes. Perfect for international banking settlements and multi-currency transactions.

**Key Features:**
- ✅ Minimizes transaction count across multiple banks
- ✅ Supports multiple payment modes per bank
- ✅ World Bank as intermediary for incompatible payment systems
- ✅ Handles net settlement calculations
- ✅ Payment mode matching algorithm
- ✅ Direct bank-to-bank transactions when possible
- ✅ Handles circular debt elimination

---

## 🔄 Process / Flow

**System Workflow:**
1. Enter number of banks participating
2. Input each bank name and their payment modes (UPI, SWIFT, Crypto, etc.)
3. Enter transaction details (Debtor, Creditor, Amount)
4. Calculate net amount for each bank (Total received - Total paid)
5. Find bank with minimum net amount (debtor)
6. Find bank with maximum net amount (creditor) with matching payment mode
7. Match payment modes between debtor and creditor
8. If no match exists, use World Bank as intermediary
9. Execute transaction and update net amounts
10. Repeat until all net amounts = 0
11. Output optimized transaction list

**Algorithm Logic:**
- Calculate net balance: Sum of all amounts received - Sum of all amounts paid
- Pair minimum debtor with maximum creditor
- Use intersection of payment modes for direct transaction
- Use World Bank for incompatible payment methods
- Eliminate circular transactions
- Display final optimized settlement

---

## 🛠️ Technology Used

| Component | Technology |
|-----------|-----------|
| **Language** | Java |
| **Data Structures** | Graphs, HashMap, HashSet, ArrayList |
| **Collections** | Multiset (via HashSet), Pair classes |
| **Algorithms** | Greedy matching, Net settlement |
| **I/O** | Scanner (console input) |

---

## 🎓 Skills Gained

**Data Structures & Algorithms:**
- Graph representation and manipulation
- HashMap for efficient lookups
- HashSet for payment mode matching
- ArrayList for dynamic lists
- Pair implementation for key-value storage

**Problem-Solving:**
- Greedy algorithm approach
- Net settlement calculation logic
- Payment mode intersection algorithm
- Circular debt elimination
- Optimal transaction routing

**Java Programming:**
- Object-oriented design (Bank, Pair classes)
- Collections framework usage
- Algorithm implementation
- Input/Output handling
- Complex data structure manipulation

**System Design:**
- Multi-entity transaction management
- Intermediary pattern implementation
- Transaction optimization
- Real-world financial system modeling

---

## 📸 Demonstration

Let's take an example. Say we have the following banks:
1. Bank_of_America (World bank)
2. Wells_Fargo
3. Royal_Bank_of_Canada
4. Westpac
5. National_Australia_Bank
6. Goldman_Sachs

Following are the payments to be done:\
&emsp;&emsp;&emsp;    **Debtor Bank**&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;                **Creditor Bank** &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; **Amount**
1. Goldman_Sachs   &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;             Bank_of_America &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;             Rs 100
2. Goldman_Sachs   &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;              Wells_Fargo &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;                Rs 300
3. Goldman_Sachs   &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;              Royal_Bank_of_Canada  &emsp;&emsp;&emsp;&emsp;&nbsp;      Rs 100
4. Goldman_Sachs   &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;              Westpac &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp; Rs 100
5. National_Australia_Bank &emsp;&emsp;&nbsp;&nbsp;       Bank_of_America &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp; Rs 300
6. National_Australia_Bank &emsp;&emsp;&nbsp;&nbsp;       Royal_Bank_of_Canada &emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Rs 100
7. Bank_of_America         &emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;       Wells_Fargo &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp; Rs 400
8. Wells_Fargo             &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;       Royal_Bank_of_Canada &emsp;&emsp;&emsp;&emsp;&nbsp; Rs 200
9. Royal_Bank_of_Canada    &emsp;&emsp;&nbsp;&nbsp;&nbsp;&nbsp;      Westpac &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp; Rs 500

This is represented below as a directed graph with the directed edge representing debts.
![image](https://user-images.githubusercontent.com/54183085/110007387-9c625100-7d40-11eb-9128-29073ea4b3f3.png)

**But there's a catch!!**
Each Bank only supports a set of modes of payments and can _make_ or _receive_ payments **only** via those. Only World Bank suppports **all** modes of payments.
In our current example we have only three payment modes :
1. Google_Pay
2. AliPay
3. Paytm

Following is the list of Banks and their supported payment modes :
1. Bank_of_America &emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;- &emsp; Google_Pay, AliPay, Paytm
2. Wells_Fargo &emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&emsp;&nbsp;- &emsp; Google_Pay, AliPay
3. Royal_Bank_of_Canada &nbsp;&emsp;&nbsp;&nbsp;&nbsp;- &emsp; AliPay
4. Westpac &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&nbsp; - &emsp; Google_Pay, Paytm
5. Goldman_Sachs &emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;- &emsp; Paytm
6. National_Australia_Bank &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - &emsp; AliPay, Paytm  

To pick the first Bank, we calculate the **net amount** for every Bank by using the below formula and store them in list:

net amount = [Sum of all **credits**(_amounts to be received_)] - [Sum of all **debits**(_amounts to pay_)]

Now the idea is that we are finding the bank which has _minimum_ net amount(_max debtor_) (_say Bank X, net amount x_) and then finding the bank which has the _maximum_ net amount( _max creditor_) (_say Bank Y, net amount y_) and also has a common payment mode (_say M1_) with the former bank. Then we find _minimum_ of absolute value of x and y, lets call it z.\
Now X pays the amount z to Y. Then 3 cases may arrived:
1. If (magnitude of x) < y  =>  X is completely settled and so removed from the list.
2. If (magnitude of x) > y  =>  Y is completely settled and so removed from the list.
3. If (magnitude of x) = y  =>  X and Y both are completely settled and so both are removed from the list.

The same process is repeated for the remaining banks.\

**Visual Transaction Flow:**
![image](https://user-images.githubusercontent.com/54183085/110007435-aab06d00-7d40-11eb-8e0c-ea5c7ec762a3.png)

**Console Output Example:**
![Screenshot 2025-01-03 134607](https://github.com/user-attachments/assets/acc674f6-9652-4b7c-971a-adcda2621bd8)

---

## ⚙️ Setup Instructions

### Prerequisites:
- Java Development Kit (JDK 8 or higher)
- Command prompt or terminal
- Text editor or IDE (VSCode, IntelliJ, Eclipse recommended)

### Installation Steps:

**1. Clone the Repository**
```bash
git clone https://github.com/garvita2003/Cash-Flow-Minimizer.git
cd Cash-Flow-Minimizer
```

**2. Verify Java Installation**
```bash
java -version
javac -version
```

**3. Compile the Program**
```bash
javac CashFlowMinimizer.java
```

**4. Run the Program**
```bash
java CashFlowMinimizer
```
