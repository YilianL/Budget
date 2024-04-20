import java.util.ArrayList;

/**
 * implementation of secondary method and object method.
 */
public abstract class BudgetSecondary implements Budget {

    @Override
    public String toString() {
        Budget dummy = this.newInstance();
        // default heading
        String budget = "Date,Category,Value\n";
        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            //add a transaction at a time
            budget += t.date() + "," + t.category() + "," + t.value() + "\n";
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);
        return budget;
    }

    @Override
    public boolean equals(Object obj) {
        // Aliasing
        if (obj == this) {
            return true;
        }
        // Null
        if (obj == null) {
            return false;
        }
        // Wrong type
        if (!(obj instanceof Budget)) {
            return false;
        }

        boolean equal = true;
        Budget budget = (Budget) obj;
        Budget dummy = budget.newInstance();

        // Wrong length
        if (budget.numberOfTransactions() != this.numberOfTransactions()) {
            return false;
        }

        // Check every item
        while (budget.numberOfTransactions() > 0) {
            Transaction t = budget.removeAnyTransaction();
            dummy.addTransaction(t.date(), t.value(), t.category());
            if (!this.containsTransaction(t.date(), t.value(), t.category())) {
                equal = false;
            }
        }
        budget.transferFrom(dummy);

        return equal;
    }

    @Override
    public final void removeTransaction(String date, double value,
            String category) {
        assert date != null : "Violation of: date is null";
        assert category != null : "Violation of: date is null";
        assert this.containsTransaction(date, value,
                category) : "the transaction is not in the budget";
        assert this.numberOfTransactions() > 0 : "number of transactions is 0";

        Budget dummy = this.newInstance();
        boolean removed = false;
        final double error = 0.0001;

        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();

            // if the transaction is the same as the given info
            // and no transaction has been removed
            if (!removed && (t.date().equals(date)
                    && Math.abs(t.value() - value) < error
                    && t.category().equals(category))) {
                removed = true;
            } else {
                dummy.addTransaction(t.date(), t.value(), t.category());
            }
        }
        this.transferFrom(dummy);
    }

    @Override
    public final Transaction removeAnyTransaction(String date) {

        assert date != null : "Violation of: date is null";
        assert this.numberOfTransactions() > 0 : "number of transactions is 0";
        assert this.hasTransactions(
                date) : "There are no transactions on the date";

        Budget dummy = this.newInstance();
        Budget one = this.newInstance();
        boolean removed = false;

        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();

            // if the transaction has the same date as date
            // and no transaction has been removed
            if (!removed && t.date().equals(date)) {
                removed = true;
                one.addTransaction(t.date(), t.value(), t.category());
            } else {
                dummy.addTransaction(t.date(), t.value(), t.category());
            }
        }
        this.transferFrom(dummy);
        return one.removeAnyTransaction();
    }

    @Override
    public final boolean hasTransactions(String date) {

        assert date != null : "Violation of: date is null";

        int tNumber = 0;
        Budget dummy = this.newInstance();

        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            if (t.date().equals(date)) {
                tNumber += 1;
            }
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);
        return (tNumber != 0);
    }

    @Override
    public final int numberOfTransactions(String date) {

        assert date != null : "Violation of: date is null";

        int tNumber = 0;
        Budget dummy = this.newInstance();

        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            if (t.date().equals(date)) {
                tNumber += 1;
            }
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);
        return tNumber;
    }

    @Override
    public final double getExpense(String date) {

        assert date != null : "Violation of: date is null";

        double expense = 0;
        Budget dummy = this.newInstance();
        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            if (t.date().equals(date) && t.value() < 0) {
                expense += t.value();
            }
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);
        return Math.abs(expense);
    }

    @Override
    public final double getIncome(String date) {

        assert date != null : "Violation of: date is null";

        double income = 0;
        Budget dummy = this.newInstance();
        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            if (t.date().equals(date) && t.value() > 0) {
                income += t.value();
            }
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);
        return income;
    }

    @Override
    public final String[] getCategory(String date, double value) {

        assert date != null : "Violation of: date is null";

        ArrayList<String> category = new ArrayList<String>();
        Budget dummy = this.newInstance();

        //add qualified category into array list
        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            if (t.date().equals(date) && t.value() == value) {
                category.add(t.category());
            }
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);

        // convert array list of category into array
        String[] items = new String[category.size()];
        for (int i = 0; i < category.size(); i++) {
            items[i] = category.get(i);
        }
        return items;
    }

    @Override
    public final double totalAmountRecorded() {

        double total = 0;
        Budget dummy = this.newInstance();

        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            total += t.value();
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);

        return total;
    }

    @Override
    public final boolean isInDebt() {

        double total = 0;
        Budget dummy = this.newInstance();

        while (this.numberOfTransactions() > 0) {
            Budget.Transaction t = this.removeAnyTransaction();
            total += t.value();
            dummy.addTransaction(t.date(), t.value(), t.category());
        }
        this.transferFrom(dummy);

        return (total < 0);
    }

}
