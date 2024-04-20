import java.util.Arrays;

/**
 * The application of the components budget (example1).
 */
public class BudgetApplication1 {

    /**
     * The main class of using the components.
     */
    public static void main(String[] args) {
        Budget app1 = new Budget1L();

        String date1 = "01/01/2024";
        String date2 = "02/01/2024";
        app1.addTransaction(date1, -11, "food");
        app1.addTransaction(date1, -1200, "housing");
        app1.addTransaction(date1, -1200, "education");
        app1.addTransaction(date2, -50, "education");
        app1.addTransaction(date2, 2000, "salary");
        app1.addTransaction(date2, -1200, "housing");
        System.out.println("number of Transactions on Date " + date1 + " is : "
                + app1.numberOfTransactions(date1));
        System.out.println(
                "expense on Date " + date1 + " is : " + app1.getExpense(date1));
        System.out.println(
                "income on Date " + date1 + " is : " + app1.getIncome(date1));
        System.out.println(
                "expense on Date " + date2 + " is : " + app1.getExpense(date2));
        System.out.println(
                "imcome on Date " + date2 + " is : " + app1.getIncome(date2));
        System.out.println("total amount recorded in Budget is : "
                + app1.totalAmountRecorded());
        System.out.println("Is in debt? " + app1.isInDebt());
        System.out.println("The $1200 spent on date " + date1 + " is for "
                + Arrays.toString(app1.getCategory(date1, -1200)));
    }

}
