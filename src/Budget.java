/**
 * {@code Budget} enhanced with Budget Kernel.
 */
public interface Budget extends BudgetKernel {

    /**
     * Transaction interface for transactions in budget.
     */
    interface Transaction {

        /**
         * Returns this {@code Transaction}'s category.
         *
         * @return the category
         * @aliases reference returned by {@code category}
         */
        String category();

        /**
         * Returns this {@code Transaction}'s date.
         *
         * @return the date
         * @aliases reference returned by {@code date}
         */
        String date();

        /**
         * Returns this {@code Transaction}'s value.
         *
         * @return the value of money spent or saved
         * @ensures value > 0 indicates saving, and value < 0 indicates expense
         * @aliases reference returned by {@code value}
         */
        double value();

    }

    /**
     * Removes the Transaction with {@code value, @code category} from
     * this.budget.
     *
     * @param date
     *            the date of transaction stored
     * @param value
     *            the amount of money
     * @param category
     *            what the money for
     * @requires {@code date,@code value, @code category} are not null and
     *           {@code date,@code value, @code category} is in DOMAIN
     *           this.budget and |this.budget| > 0
     * @ensures remove the transaction that has date, value and category from
     *          this.budget
     */
    void removeTransaction(String date, double value, String category);

    /**
     * Removes and returns an arbitrary transaction from {@code this.budget} on
     * date {@code date}.
     *
     * @param date
     *            date of transaction
     * @return the transaction on date {@date} removed from
     *         {@code this.budget.value(date)}
     * @updates this
     * @requires {@code date} is not null and |this.budget.value({@code date})|
     *           > 0
     * @ensures <pre>
     * removeAny is in #this.budget.value({@code date})  and
     * this.budget.value({@code date}) = #this.budget.value({@code date}) \ {removeAny}
     * </pre>
     */
    Budget.Transaction removeAnyTransaction(String date);

    /**
     * Reports whether there is a transaction in {@code this.budget} on date
     * {@code date}.
     *
     * @param date
     *            the date of transaction stored
     * @return true iff there is a transaction in
     *         this.budget.value({@code date})
     * @requires {@code date} is not null
     * @ensures hasTransactions = ({@code date} is in DOMAIN(this)) &&
     *          this.budget.value({@code date}).length() > 0
     */
    boolean hasTransactions(String date);

    /**
     * returns number of transactions on {@code date}.
     *
     * @param date
     *            the date of transaction stored
     * @requires {@code date} is not null
     * @return number of transactions on {@code date}
     */
    int numberOfTransactions(String date);

    /**
     * returns sum of all negative values of transactions on {@code date}.
     *
     * @param date
     *            the date of transaction stored
     * @requires {@code date} is not null
     * @return sum of all negative values on {@code date}
     */
    double getExpense(String date);

    /**
     * returns sum of all positive values of transactions on {@code date}.
     *
     * @param date
     *            the date of transaction stored
     * @requires {@code date} is not null
     * @return sum of all positive values on {@code date}
     */
    double getIncome(String date);

    /**
     * Gives an array that contains all categories of the money spent on
     * {@code date} with {@code value}.
     *
     * @param date
     *            the date of transaction stored
     * @param value
     *            the amount of money
     * @return array that contains all the categories with amount {@code value}
     *         on {@code date}
     * @requires {@code date} is not null
     * @ensures Gives an array that contains all categories of the money spent
     *          on {@code date} with {@code value}.
     */
    String[] getCategory(String date, double value);

    /**
     * Returns total amount of money recoded in this.budget.
     *
     * @return total amount of values stored in this.budget
     * @ensures totalAmountRecorded = total amount of money that was stored in
     *          this.budget
     */
    double totalAmountRecorded();

    /**
     * Returns if the total amount of money recoded in this.budget is negative.
     *
     * @return true if total amount of values stored in this.budget is negative
     * @ensures isInDebt = total amount of money that was stored in this.budget
     *          < 0
     */
    boolean isInDebt();

}
