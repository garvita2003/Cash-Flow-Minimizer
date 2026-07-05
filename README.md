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

**Console Output Example:**
![Screenshot 2025-01-03 134607](https://github.com/user-attachments/assets/acc674f6-9652-4b7c-971a-adcda2621bd8)

**Visual Transaction Flow:**
![image](https://user-images.githubusercontent.com/54183085/110007435-aab06d00-7d40-11eb-8e0c-ea5c7ec762a3.png)

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

---

**Created by:** Garvita Kesarwani | **Language:** Java | **Version:** 1.0.0

*Optimize your global banking transactions with intelligent settlement routing!* 💳✨
