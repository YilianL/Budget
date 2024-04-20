import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;

/**
 * {@code budget} represented as a map with implementations of primary methods.
 *
 * @convention [this.size >= 0] and [this.map.size() >= 0].
 *
 * @correspondence [this.size records the number of BudgetTransaction stored in
 *                 this] and [this.budget records details of BudgetTransaction
 *                 including date, value category in this].
 */
public class Budget1L extends BudgetSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Inner class of BudgetTransaction.
     */
    private class BudgetTransaction implements Transaction {

        /**
         * date of Transaction.
         */
        private String date;
        /**
         * category of Transaction.
         */
        private String category;
        /**
         * value of Transaction.
         */
        private double value;

        /**
         * BudgetTransaction default constructor.
         */
        BudgetTransaction() {
            this.date = "";
            this.value = 0;
            this.category = "";

        }

        /**
         * BudgetTransaction constructor.
         *
         * @param date
         *            date of transaction
         * @param value
         *            value of transaction
         * @param category
         *            category of Transaction
         */
        BudgetTransaction(String date, double value, String category) {
            this.date = date;
            this.value = value;
            this.category = category;

        }

        @Override
        public String category() {
            return this.category;
        }

        @Override
        public String date() {
            return this.date;
        }

        @Override
        public double value() {
            return this.value;
        }

    }

    /**
     * Map with money and date.
     */

    private Map<String, Queue<Transaction>> budget;

    /**
     * number of Transactions included.
     */
    private int size;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.budget = new Map1L<>();
        this.size = 0;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Budget1L() {
        this.createNewRep();
    }

    /*
     * Standard methods (NOT DONE
     * YET!!!!)-------------------------------------------------------
     */

    @Override
    public final Budget1L newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Budget source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Budget1L : ""
                + "Violation of: source is of dynamic type Sequence3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Sequence3<?>,
         * and the ? must be T or the call would not have compiled.
         */
        Budget1L newBudget = (Budget1L) source;
        this.size = newBudget.size;
        this.budget = newBudget.budget;
        newBudget.createNewRep();

    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    //positive d represents income, and negative expense represent expense
    @Override
    public final void addTransaction(String t, double d, String s) {

        BudgetTransaction trans = new BudgetTransaction(t, d, s);
        if (this.budget.hasKey(t)) {
            this.budget.value(t).enqueue(trans);
        } else {
            Queue<Transaction> flow = new Queue1L<>();
            flow.enqueue(trans);
            this.budget.add(t, flow);

        }
        this.size++;
    }

    @Override
    public final Budget.Transaction removeAnyTransaction() {
        assert this.size > 0 : "Violation of: number of transaction stored bigger than 0";

        boolean removed = false;
        Budget.Transaction trans = new BudgetTransaction();
        while (!removed) {
            Map.Pair<String, Queue<Transaction>> flow = this.budget.removeAny();
            if (flow.value().length() > 0) {
                trans = flow.value().dequeue();
                this.size--;
                this.budget.add(flow.key(), flow.value());
                removed = true;
            }
        }
        return trans;

    }

    @Override
    public final boolean containsTransaction(String date, double value,
            String category) {
        final double error = 0.0001;
        boolean ifContains = false;
        if (this.budget.hasKey(date)) {
            for (Transaction t : this.budget.value(date)) {
                boolean contains = t.date().equals(date)
                        && (Math.abs(t.value() - value) < error)
                        && t.category().equals(category);
                if (contains) {
                    ifContains = true;
                }
            }
        }
        return ifContains;
    }

    @Override
    public final int numberOfTransactions() {
        return this.size;
    }

}
