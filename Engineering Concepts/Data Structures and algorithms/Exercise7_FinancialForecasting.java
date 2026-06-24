import java.util.HashMap;
import java.util.Map;

// Exercise 7: Financial Forecasting using Recursive Algorithm

public class Exercise7_FinancialForecasting {

    // Recursive method - calculates future value after n periods
    // Formula: FV = PV * (1 + rate)^n
    // Time Complexity: O(n) — n recursive calls
    public static double futureValueRecursive(double presentValue, double growthRate, int periods) {
        if (periods == 0) {
            return presentValue; // Base case
        }
        // Recursive case: grow by one period
        return futureValueRecursive(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    // Memoized recursive version to avoid redundant calculations
    // Uses a cache (HashMap) to store already computed values
    // Time Complexity: O(n) but avoids re-computation
    private static Map<Integer, Double> memo = new HashMap<>();

    public static double futureValueMemo(double presentValue, double growthRate, int periods) {
        if (periods == 0) return presentValue;

        if (memo.containsKey(periods)) {
            return memo.get(periods);
        }

        double result = futureValueMemo(presentValue, growthRate, periods - 1) * (1 + growthRate);
        memo.put(periods, result);
        return result;
    }

    // Iterative version for comparison
    // Time Complexity: O(n) — more efficient (no call stack overhead)
    public static double futureValueIterative(double presentValue, double growthRate, int periods) {
        double value = presentValue;
        for (int i = 0; i < periods; i++) {
            value *= (1 + growthRate);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println("=== Exercise 7: Financial Forecasting (Recursion) ===\n");

        double presentValue = 10000.00; // Initial investment
        double growthRate = 0.08;       // 8% annual growth
        int periods = 10;               // 10 years

        System.out.printf("Initial Investment : $%.2f%n", presentValue);
        System.out.printf("Annual Growth Rate : %.0f%%%n", growthRate * 100);
        System.out.printf("Forecast Periods   : %d years%n%n", periods);

        // Recursive
        memo.clear();
        double rvRecursive = futureValueRecursive(presentValue, growthRate, periods);
        System.out.printf("Recursive Result   : $%.2f%n", rvRecursive);

        // Memoized
        memo.clear();
        double rvMemo = futureValueMemo(presentValue, growthRate, periods);
        System.out.printf("Memoized Result    : $%.2f%n", rvMemo);

        // Iterative
        double rvIterative = futureValueIterative(presentValue, growthRate, periods);
        System.out.printf("Iterative Result   : $%.2f%n", rvIterative);

        // Year-by-year forecast
        System.out.println("\n--- Year-by-Year Growth Forecast ---");
        System.out.printf("%-6s %-15s%n", "Year", "Future Value");
        System.out.println("----------------------------");
        for (int y = 0; y <= periods; y++) {
            double fv = futureValueIterative(presentValue, growthRate, y);
            System.out.printf("%-6d $%-14.2f%n", y, fv);
        }

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Recursive (no memo) : O(n) calls, O(n) stack space");
        System.out.println("Memoized Recursive  : O(n) time, O(n) space — avoids recomputation");
        System.out.println("Iterative           : O(n) time, O(1) space — most efficient");
        System.out.println("Math formula FV=PV*(1+r)^n: O(1) — fastest, no recursion needed.");
    }
}
