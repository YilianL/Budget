import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code Budget}'s constructor and kernel methods.
 *
 * @author Yilly Liu
 *
 */
public class BudgetTest {

    //test standard methods
    @Test
    public void testclear() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2023", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -10, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        test.clear();
        assertEquals(0, test.numberOfTransactions());
    }

    @Test
    public void testNewInstance() {

        Budget test = new Budget1L();
        Budget test2 = test.newInstance();
        boolean equal = test.equals(test2);

        assertEquals(true, equal);
    }

    @Test
    public void testTransferFrom() {

        Budget test = new Budget1L();

        test.addTransaction("4/17/2023", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -10, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        Budget test2 = new Budget1L();

        test2.addTransaction("4/17/2023", -10, "food");
        test2.addTransaction("4/17/2023", -30, "transportation");
        test2.addTransaction("4/17/2023", 1500, "salary");
        test2.addTransaction("4/17/2023", -10, "gift");
        test2.addTransaction("4/17/2023", -1200, "housing");
        Budget test3 = new Budget1L();

        test3.transferFrom(test2);
        boolean equal = test3.equals(test);

        assertEquals(true, equal);
        assertEquals(0, test2.numberOfTransactions());
    }

    //test kernel methods
    @Test
    public void testAddTransactiontoEmpty() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        boolean result = test.containsTransaction("4/17/2024", -10, "food");
        assertEquals(true, result);
    }

    @Test
    public void testAddTransactiontoNonEmpty() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        boolean result = test.containsTransaction("4/17/2023", -30,
                "transportation");
        assertEquals(true, result);
    }

    @Test
    public void testRemoveAnyTransactionLeavingEmpty() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2023", -30, "transportation");
        Budget.Transaction trans = test.removeAnyTransaction();
        boolean result = test.containsTransaction(trans.date(), trans.value(),
                trans.category());
        assertEquals(false, result);
        assertEquals(0, test.numberOfTransactions());
    }

    @Test
    public void testRemoveAnyTransactionLeavingNonEmpty() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        Budget.Transaction trans = test.removeAnyTransaction();
        boolean result = test.containsTransaction(trans.date(), trans.value(),
                trans.category());
        assertEquals(false, result);
        assertEquals(1, test.numberOfTransactions());
    }

    @Test
    public void testContainsTransactionTrue() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        boolean result = test.containsTransaction("4/17/2024", -10, "food");
        assertEquals(true, result);
    }

    @Test
    public void testContainsTransactionFalse() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        boolean result = test.containsTransaction("4/17/2023", -30,
                "transportation");
        assertEquals(false, result);
    }

    @Test
    public void testNumberOfTransactionsSize0() {
        Budget test = new Budget1L();
        int size = test.numberOfTransactions();
        assertEquals(0, size);
    }

    @Test
    public void testNumberOfTransactionsSize1() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        int size = test.numberOfTransactions();
        assertEquals(1, size);
    }

    @Test
    public void testNumberOfTransactionsSize3() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2022", 1500, "salary");
        int size = test.numberOfTransactions();
        assertEquals(3, size);
    }

    //test secondary methods
    @Test
    public void testEqualsTrue() {
        Budget test1 = new Budget1L();
        test1.addTransaction("4/17/2024", -10, "food");
        test1.addTransaction("4/17/2023", -30, "transportation");
        test1.addTransaction("4/17/2022", 1500, "salary");

        Budget test2 = new Budget1L();
        test2.addTransaction("4/17/2024", -10, "food");
        test2.addTransaction("4/17/2023", -30, "transportation");
        test2.addTransaction("4/17/2022", 1500, "salary");
        boolean ifEqual = test1.equals(test2);
        assertEquals(true, ifEqual);
    }

    @Test
    public void testEqualsFalse() {
        Budget test1 = new Budget1L();
        test1.addTransaction("4/17/2024", -10, "food");
        test1.addTransaction("4/17/2023", -30, "transportation");
        test1.addTransaction("4/17/2022", 1500, "salary");

        Budget test2 = new Budget1L();
        test2.addTransaction("4/17/2024", -10, "food");
        test2.addTransaction("4/17/2023", -30, "transportation");
        test2.addTransaction("4/17/2021", 1500, "salary");
        boolean ifEqual = test1.equals(test2);
        assertEquals(false, ifEqual);
    }

    @Test
    public void testToStringEmpty() {
        Budget test = new Budget1L();
        String budget = test.toString();
        assertEquals("Date,Category,Value\n", budget);
    }

    @Test
    public void testToStringNonEmpty() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        String budget = test.toString();
        assertEquals("Date,Category,Value\n4/17/2024,food,-10.0\n", budget);
    }

    @Test
    public void testRemoveTransactionLeaving0() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2023", -30, "transportation");
        test.removeTransaction("4/17/2023", -30, "transportation");
        assertEquals(0, test.numberOfTransactions());
    }

    @Test
    public void testRemoveTransactionLeaving1() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.removeTransaction("4/17/2023", -30, "transportation");
        assertEquals(1, test.numberOfTransactions());
    }

    @Test
    public void testRemoveTransactionLeaving3() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        test.removeTransaction("4/17/2023", 1500, "salary");
        assertEquals(3, test.numberOfTransactions());
    }

    @Test
    public void testremoveAnyTransactionDateLeaving0() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2023", -30, "transportation");
        Budget.Transaction trans = test.removeAnyTransaction("4/17/2023");

        assertEquals(0, test.numberOfTransactions());
        assertEquals("4/17/2023", trans.date());
    }

    @Test
    public void testremoveAnyTransactionDateLeaving1() {
        Budget test = new Budget1L();
        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");

        Budget.Transaction trans = test.removeAnyTransaction("4/17/2023");

        assertEquals(1, test.numberOfTransactions());
        assertEquals("4/17/2023", trans.date());
    }

    @Test
    public void testremoveAnyTransactionDateLeaving3() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2022", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        Budget.Transaction trans = test.removeAnyTransaction("4/17/2023");
        assertEquals(3, test.numberOfTransactions());
        assertEquals("4/17/2023", trans.date());
    }

    @Test
    public void testHasTransactionsTrue() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2022", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        boolean hasTrans = test.hasTransactions("4/17/2024");
        assertEquals(true, hasTrans);
    }

    @Test
    public void testHasTransactionsFalse() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2022", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        boolean hasTrans = test.hasTransactions("4/17/2026");
        assertEquals(false, hasTrans);
    }

    @Test
    public void testNumberOfTransactionsDate0() {

        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        int numberTrans = test.numberOfTransactions("4/17/2026");
        assertEquals(0, numberTrans);
    }

    @Test
    public void testNumberOfTransactionsDate1() {

        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2022", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        int numberTrans = test.numberOfTransactions("4/17/2024");
        assertEquals(1, numberTrans);
    }

    @Test
    public void testNumberOfTransactionsDate3() {

        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        int numberTrans = test.numberOfTransactions("4/17/2023");
        assertEquals(3, numberTrans);
    }

    @Test
    public void testGetExpenseEmpty() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        double expense = test.getExpense("4/17/2026");
        boolean equal = Math.abs(expense - 0) < 0.0001;
        assertEquals(true, equal);
    }

    @Test
    public void testGetExpenseNonEmpty() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        double expense = test.getExpense("4/17/2023");
        boolean equal = Math.abs(expense - 1230) < 0.0001;
        assertEquals(true, equal);
    }

    @Test
    public void testGetIncomeEmpty() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -1200, "housing");

        double income = test.getIncome("4/17/2026");
        boolean equal = Math.abs(income - 0) < 0.0001;
        assertEquals(true, equal);
    }

    @Test
    public void testGetIncomeNonEmpty() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2024", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", 200, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        double income = test.getIncome("4/17/2023");
        boolean equal = Math.abs(income - 1700) < 0.0001;
        assertEquals(true, equal);
    }

    @Test
    public void testGetCategoryEmpty() {

        Budget test = new Budget1L();

        test.addTransaction("4/17/2023", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -10, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        String[] category = test.getCategory("4/17/2023", 100);
        assertEquals(0, category.length);
    }

    @Test
    public void testGetCategoryNonEmpty() {

        Budget test = new Budget1L();

        test.addTransaction("4/17/2023", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -10, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        String[] category = test.getCategory("4/17/2023", -10);
        assertEquals(2, category.length);
    }

    @Test
    public void testTotalAmountEmpty() {
        Budget test = new Budget1L();

        double total = test.totalAmountRecorded();
        boolean equal = Math.abs(total - 0) < 0.0001;
        assertEquals(true, equal);
    }

    @Test
    public void testTotalAmountNonEmpty() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2023", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -10, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        double total = test.totalAmountRecorded();
        boolean equal = Math.abs(total - 250) < 0.0001;
        assertEquals(true, equal);
    }

    @Test
    public void testIsInDebtTrue() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2023", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", -10, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        boolean isDebt = test.isInDebt();
        assertEquals(true, isDebt);
    }

    @Test
    public void testIsInDebtFalse() {
        Budget test = new Budget1L();

        test.addTransaction("4/17/2023", -10, "food");
        test.addTransaction("4/17/2023", -30, "transportation");
        test.addTransaction("4/17/2023", 1500, "salary");
        test.addTransaction("4/17/2023", -10, "gift");
        test.addTransaction("4/17/2023", -1200, "housing");

        boolean isDebt = test.isInDebt();
        assertEquals(false, isDebt);
    }

    @Test
    public void testIsInDebt0() {
        Budget test = new Budget1L();

        boolean isDebt = test.isInDebt();
        assertEquals(false, isDebt);
    }

}
