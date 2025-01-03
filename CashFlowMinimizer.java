import java.util.*;

class Bank {
    String name;
    int netAmount;
    Set<String> types;

    public Bank() {
        types = new HashSet<>();
    }
}

public class CashFlowMinimizer {

    static int getMinIndex(Bank[] listOfNetAmounts, int numBanks) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0) continue;

            if (listOfNetAmounts[i].netAmount < min) {
                minIndex = i;
                min = listOfNetAmounts[i].netAmount;
            }
        }
        return minIndex;
    }

    static int getSimpleMaxIndex(Bank[] listOfNetAmounts, int numBanks) {
        int max = Integer.MIN_VALUE, maxIndex = -1;
        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0) continue;

            if (listOfNetAmounts[i].netAmount > max) {
                maxIndex = i;
                max = listOfNetAmounts[i].netAmount;
            }
        }
        return maxIndex;
    }

    static Pair<Integer, String> getMaxIndex(Bank[] listOfNetAmounts, int numBanks, int minIndex, Bank[] input, int maxNumTypes) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        String matchingType = "";

        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0 || listOfNetAmounts[i].netAmount < 0) continue;

            List<String> v = new ArrayList<>(maxNumTypes);
            for (String type : listOfNetAmounts[minIndex].types) {
                if (listOfNetAmounts[i].types.contains(type)) {
                    v.add(type);
                }
            }

            if (!v.isEmpty() && max < listOfNetAmounts[i].netAmount) {
                max = listOfNetAmounts[i].netAmount;
                maxIndex = i;
                matchingType = v.get(0);
            }
        }

        return new Pair<>(maxIndex, matchingType);
    }

    static void printAns(List<List<Pair<Integer, String>>> ansGraph, int numBanks, Bank[] input) {
        System.out.println("\nThe transactions for minimum cash flow are as follows : \n");
        for (int i = 0; i < numBanks; i++) {
            for (int j = 0; j < numBanks; j++) {
                if (i == j) continue;

                if (ansGraph.get(i).get(j).getKey() != 0 && ansGraph.get(j).get(i).getKey() != 0) {
                    if (ansGraph.get(i).get(j).getKey().equals(ansGraph.get(j).get(i).getKey())) {
                        ansGraph.get(i).get(j).setKey(0);
                        ansGraph.get(j).get(i).setKey(0);
                    } else if (ansGraph.get(i).get(j).getKey() > ansGraph.get(j).get(i).getKey()) {
                        ansGraph.get(i).get(j).setKey(ansGraph.get(i).get(j).getKey() - ansGraph.get(j).get(i).getKey());
                        ansGraph.get(j).get(i).setKey(0);
                        System.out.println(input[i].name + " pays Rs " + ansGraph.get(i).get(j).getKey() + " to " + input[j].name + " via " + ansGraph.get(i).get(j).getValue());
                    } else {
                        ansGraph.get(j).get(i).setKey(ansGraph.get(j).get(i).getKey() - ansGraph.get(i).get(j).getKey());
                        ansGraph.get(i).get(j).setKey(0);
                        System.out.println(input[j].name + " pays Rs " + ansGraph.get(j).get(i).getKey() + " to " + input[i].name + " via " + ansGraph.get(j).get(i).getValue());
                    }
                } else if (ansGraph.get(i).get(j).getKey() != 0) {
                    System.out.println(input[i].name + " pays Rs " + ansGraph.get(i).get(j).getKey() + " to " + input[j].name + " via " + ansGraph.get(i).get(j).getValue());
                } else if (ansGraph.get(j).get(i).getKey() != 0) {
                    System.out.println(input[j].name + " pays Rs " + ansGraph .get(j).get(i).getKey() + " to " + input[i].name + " via " + ansGraph.get(j).get(i).getValue());
                }

                ansGraph.get(i).get(j).setKey(0);
                ansGraph.get(j).get(i).setKey(0);
            }
        }
        System.out.println();
    }

    static void minimizeCashFlow(int numBanks, Bank[] input, Map<String, Integer> indexOf, int numTransactions, List<List<Integer>> graph, int maxNumTypes) {
        Bank[] listOfNetAmounts = new Bank[numBanks];

        for (int b = 0; b < numBanks; b++) {
            listOfNetAmounts[b] = new Bank();
            listOfNetAmounts[b].name = input[b].name;
            listOfNetAmounts[b].types = input[b].types;

            int amount = 0;
            for (int i = 0; i < numBanks; i++) {
                amount += graph.get(i).get(b);
            }

            for (int j = 0; j < numBanks; j++) {
                amount += (-1) * graph.get(b).get(j);
            }

            listOfNetAmounts[b].netAmount = amount;
        }

        List<List<Pair<Integer, String>>> ansGraph = new ArrayList<>(numBanks);
        for (int i = 0; i < numBanks; i++) {
            ansGraph.add(new ArrayList<>(Collections.nCopies(numBanks, new Pair<>(0, ""))));
        }

        int numZeroNetAmounts = 0;

        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0) numZeroNetAmounts++;
        }
        while (numZeroNetAmounts != numBanks) {
            int minIndex = getMinIndex(listOfNetAmounts, numBanks);
            Pair<Integer, String> maxAns = getMaxIndex(listOfNetAmounts, numBanks, minIndex, input, maxNumTypes);

            int maxIndex = maxAns.getKey();

            if (maxIndex == -1) {
                ansGraph.get(minIndex).get(0).setKey(ansGraph.get(minIndex).get(0).getKey() + Math.abs(listOfNetAmounts[minIndex].netAmount));
                ansGraph.get(minIndex).get(0).setValue(input[minIndex].types.iterator().next());

                int simpleMaxIndex = getSimpleMaxIndex(listOfNetAmounts, numBanks);
                ansGraph.get(0).get(simpleMaxIndex).setKey(ansGraph.get(0).get(simpleMaxIndex).getKey() + Math.abs(listOfNetAmounts[minIndex].netAmount));
                ansGraph.get(0).get(simpleMaxIndex).setValue(input[simpleMaxIndex].types.iterator().next());

                listOfNetAmounts[simpleMaxIndex].netAmount += listOfNetAmounts[minIndex].netAmount;
                listOfNetAmounts[minIndex].netAmount = 0;

                if (listOfNetAmounts[minIndex].netAmount == 0) numZeroNetAmounts++;
                if (listOfNetAmounts[simpleMaxIndex].netAmount == 0) numZeroNetAmounts++;
            } else {
                int transactionAmount = Math.min(Math.abs(listOfNetAmounts[minIndex].netAmount), listOfNetAmounts[maxIndex].netAmount);

                ansGraph.get(minIndex).get(maxIndex).setKey(ansGraph.get(minIndex).get(maxIndex).getKey() + transactionAmount);
                ansGraph.get(minIndex).get(maxIndex).setValue(maxAns.getValue());

                listOfNetAmounts[minIndex].netAmount += transactionAmount;
                listOfNetAmounts[maxIndex].netAmount -= transactionAmount;

                if (listOfNetAmounts[minIndex].netAmount == 0) numZeroNetAmounts++;
                if (listOfNetAmounts[maxIndex].netAmount == 0) numZeroNetAmounts++;
            }
        }

        printAns(ansGraph, numBanks, input);
    }

    public static void main(String[] args) {
        System.out.println("\n\t\t\t\t********************* Welcome to CASH FLOW MINIMIZER SYSTEM ***********************\n");
        System.out.println("This system minimizes the number of transactions among multiple banks in the different corners of the world that use different modes of payment. There is one world bank (with all payment modes) to act as an intermediary between banks that have no common mode of payment.\n");
        System.out.println("Enter the number of banks participating in the transactions.");
        Scanner scanner = new Scanner(System.in);
        int numBanks = scanner.nextInt();

        Bank[] input = new Bank[numBanks];
        Map<String, Integer> indexOf = new HashMap<>();

        System.out.println("Enter the details of the banks and transactions as stated:");
        System.out.println("Bank name, number of payment modes it has, and the payment modes.");
        System.out.println("Bank name and payment modes should not contain spaces");

        int maxNumTypes = 0;
        for (int i = 0; i < numBanks; i++) {
            input[i] = new Bank();
            if (i == 0) {
                System.out.print("World Bank: ");
            } else {
                System.out.print("Bank " + i + ": ");
            }
            input[i].name = scanner.next();
            indexOf.put(input[i].name, i);
            int numTypes = scanner.nextInt();

            if (i == 0) maxNumTypes = numTypes;

            for (int j = 0; j < numTypes; j++) {
                String type = scanner.next();
                input[i].types.add(type);
            }
        }

        System.out.println("Enter number of transactions.");
        int numTransactions = scanner.nextInt();

        List<List<Integer>> graph = new ArrayList<>(numBanks);
        for (int i = 0; i < numBanks; i++) {
            graph.add(new ArrayList<>(Collections.nCopies(numBanks, 0)));
        }

        System.out.println("Enter the details of each transaction as stated:");
        System.out.println("Debtor Bank, creditor Bank, and amount");
        System.out.println("The transactions can be in any order");
        for (int i = 0; i < numTransactions; i++) {
            System.out.print(i + " th transaction: ");
            String s1 = scanner.next();
            String s2 = scanner.next();
            int amount = scanner.nextInt();

            graph.get(indexOf.get(s1)).set(indexOf.get(s2), amount);
        }

        minimizeCashFlow(numBanks, input, indexOf, numTransactions, graph, maxNumTypes);
        scanner.close();
    }
} 

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}