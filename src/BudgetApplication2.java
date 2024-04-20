import java.util.Arrays;

/**
 * The application of the components budget (example2).
 */
public class BudgetApplication2 {

    /**
     * The main class of using the components.
     */
    public static void main(String[] args) {

        Budget expense = new Budget1L();

        String date1 = "2024/04/01";
        String date2 = "2024/04/02";
        expense.addTransaction(date1, -30, "food");
        expense.addTransaction(date1, -1200, "housing");
        expense.addTransaction(date2, -12000, "tuition");
        expense.removeTransaction(date1, -30, "food");
        expense.addTransaction(date2, -30, "food");
        System.out.println("number of Transactions in budget is: "
                + expense.numberOfTransactions());
        System.out.println("number of Transactions on Date " + date1 + " is : "
                + expense.numberOfTransactions(date1));
        System.out.println("number of Transactions on Date " + date2 + " is : "
                + expense.numberOfTransactions(date2));
        System.out.println("expense on Date " + date1 + " is : "
                + expense.getExpense(date1));
        System.out.println("expense on Date " + date2 + " is : "
                + expense.getExpense(date2));
        System.out.println("Is there an expense of on Date 2024/03/01?");
        System.out.println(expense.hasTransactions("2024/03/01"));

        System.out.println("total amount recorded in Budget is : "
                + expense.totalAmountRecorded());
        System.out.println("Is in debt? " + expense.isInDebt());
        System.out.println("The $1200 spent on date " + date1 + "is for "
                + Arrays.toString(expense.getCategory(date1, -1200)));
    }

}
