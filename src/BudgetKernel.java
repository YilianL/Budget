import components.standard.Standard;

/**
 * {@code BudgetKernel} enhanced with secondary methods.
 */
public interface BudgetKernel extends Standard<Budget> {

    /**
     * Adds the ({@code date}, {@code transaction(value,category)}) to
     * this.budget if there is not a {@code date} exist as a key in this.budget.
     * Otherwise, add the transaction(value,category) to the value of the
     * key{@code date}
     *
     * @param date
     *            date of transaction
     * @param value
     *            value of transaction
     * @param category
     *            category of transaction
     * @aliases references {@code date, transaction(value,category)}
     * @updates this.budget
     * @ensures if there is not a {@code time} exist as a key in this.budget add
     *          the pair({@code date,Queue<Transaction>}to this.budget. Add the
     *          Transaction(value,category) to the value of key{@date} which is
     *          Queue<Transaction>.
     */
    void addTransaction(String date, double value, String category);

    /**
     * Removes and returns an arbitrary transaction from {@code this.budget}.
     *
     * @return the transaction removed from {@code this.budget.value(date)}
     * @updates this
     * @requires |this.budget| > 0
     * @ensures <pre>
     * removeAny is in #this.budget.value()  and
     * this.budget.value() = #this.budget.value() \ {removeAny}
     * </pre>
     */
    Budget.Transaction removeAnyTransaction();

    /**
     * Returns true of this.budget contains the transaction with date
     * {@code date}, value {@code value}, category{@code category}.
     *
     * @param date
     * @param value
     * @param category
     * @return true if this.budget contains transaction with date {@code date},
     *         value {@code value}, category{@code category}
     */
    boolean containsTransaction(String date, double value, String category);

    /**
     * Reports all transactions stored in this.budget.
     *
     * @return the number of transactions in {@code this.budget}
     * @ensures size = |transaction in {@code this.budget}|
     */
    int numberOfTransactions();

}
