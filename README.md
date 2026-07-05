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

**Language Composition:**
- Java: 100%

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

**Console Output Example:**

```
********************** Welcome to CASH FLOW MINIMIZER SYSTEM ***********************

This system minimizes the number of transactions among multiple banks 
in the different corners of the world that use different modes of payment. 
There is one world bank (with all payment modes) to act as an intermediary 
between banks that have no common mode of payment.

Enter the number of banks participating in the transactions.
3

Enter the details of the banks and transactions as stated:
Bank name, number of payment modes it has, and the payment modes.

World Bank: 
3
UPI SWIFT Crypto

Bank 1: 
2
UPI SWIFT

Bank 2: 
2
SWIFT Crypto

Enter number of transactions.
2

Enter the details of each transaction:
0 th transaction: World Bank Bank1 100
1 th transaction: Bank1 Bank2 50

The transactions for minimum cash flow are as follows:
Bank1 pays Rs 50 to Bank2 via SWIFT
Bank2 pays Rs 50 to World Bank via Crypto
```

**Visual Transaction Flow:**
```
Example with 4 Banks:

Input Transactions:
Bank A → Bank B: Rs 100
Bank A → Bank C: Rs 50
Bank C → Bank A: Rs 200
Bank B → Bank C: Rs 100

Optimized Output:
Bank A pays Rs 150 to Bank C via UPI
Bank B pays Rs 100 to Bank C via SWIFT
(Instead of 4 transactions, only 2 needed)
```

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

**5. Follow Console Instructions**
- Enter number of banks
- Input bank details and payment modes
- Enter transaction count
- Provide transaction details
- View optimized transactions

**Example Input Session:**
```
Enter the number of banks participating: 3
Bank name and payment modes:
World Bank: 3 UPI SWIFT Crypto
Bank 1: 2 UPI SWIFT
Bank 2: 2 SWIFT Crypto
Number of transactions: 2
Transaction 1 - Debtor: World_Bank, Creditor: Bank1, Amount: 100
Transaction 2 - Debtor: Bank1, Creditor: Bank2, Amount: 50
```

---

**Created by:** Garvita Kesarwani | **Language:** Java | **Version:** 1.0.0

*Optimize your global banking transactions with intelligent settlement routing!* 💳✨
