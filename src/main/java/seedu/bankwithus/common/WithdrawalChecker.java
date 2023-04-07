package seedu.bankwithus.common;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class WithdrawalChecker {
    private String totalAmtWithdrawn;
    private LocalDate lastWithdrawnDate;
    private String withdrawalLimit;
    private Logger logger = Logger.getLogger("Foo");

    /**
     * Instantiates withdrawalChecker without any amount withdrawn.
     */
    public WithdrawalChecker() {
        logger.log(Level.FINE, "Default WithdrawalChecker contructor called");
        this.totalAmtWithdrawn = "0";
        logger.log(Level.FINE, "totalAmtWithdrawn set to " + totalAmtWithdrawn);
    }

    /**
     * Instantiates withdrawalChecker with amount withdrawn and 
     * last known withdrawal date.
     * @param totalAmtWithdrawn amount withdrawn
     * @param lastWithdrawnDate last known withdrawal date
     */
    public WithdrawalChecker(String totalAmtWithdrawn, LocalDate lastWithdrawnDate) {
        logger.log(Level.FINE, "WithdrawalChecker contructer with set withdrawals called");
        this.totalAmtWithdrawn = totalAmtWithdrawn;
        this.lastWithdrawnDate = lastWithdrawnDate;
        logger.log(Level.FINE, "totalAntWithdrawn set to " + totalAmtWithdrawn);
        logger.log(Level.FINE, "lastWithdrawnDate set to " + lastWithdrawnDate.toString());
    }

    public String getTotalAmtWithdrawn() {
        return this.totalAmtWithdrawn;
    }

    //@@author tyuyang
    /**
     * Updates the totalAmtWithdrawn attribute. In general, if the current month is
     * the same as the last withdrawn month, then add to the total amount withdrawn,
     * if not, then reset the total amount withdrawn.
     * 
     * @param withdrawal the amount that was just withdrawn
     */
    public void updateTotalAmtWithdrawn(BigDecimal withdrawal) {
        logger.log(Level.FINE, "updateTotalAmtWithdrawn in WithdrawalChecker called");
        LocalDate currentDate = LocalDate.now();
        DecimalFormat df = new DecimalFormat("#.##");
        logger.log(Level.FINE, "Checking if account has withdrawal history");
        if (lastWithdrawnDate == null) {
            logger.log(Level.FINE, "No withdrawal history found, setting values now");
            lastWithdrawnDate = currentDate;
            String formatted = df.format(withdrawal);
            totalAmtWithdrawn = String.valueOf(formatted);
            return;
        }
        assert totalAmtWithdrawn != null;
        if (lastWithdrawnDate.getMonth() == currentDate.getMonth() && 
                lastWithdrawnDate.getYear() == currentDate.getYear()) {
            logger.log(Level.FINE, "Previous withdrawal in the same month, adding to total");
            String formatted = df.format(new BigDecimal(totalAmtWithdrawn).add(withdrawal));
            totalAmtWithdrawn = String.valueOf(formatted);
        } else {
            logger.log(Level.FINE, "Previous withdrawal in previous months, setting to new value");
            String formatted = df.format(withdrawal);
            totalAmtWithdrawn = String.valueOf(formatted);
        }
        lastWithdrawnDate = currentDate;
    }

    public String getWithdrawalLimit() {
        return this.withdrawalLimit;
    }

    public void setWithdrawalLimit(float withdrawalLimit) {
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(withdrawalLimit);
        this.withdrawalLimit = String.valueOf(formatted);
    }

    /**
     * Checks if the amount withdrawn will exceed the withdrawal limit.
     * 
     * @param withdrawAmount the amount withdrawn
     * @return true if will exceed, false otherwise
     */
    public boolean willExceedWithdrawalLimit(float withdrawAmount) {
        if (totalAmtWithdrawn.isBlank() || withdrawalLimit == null) {
            return false;
        }
        float totalAmtWithdrawnFloat = Float.parseFloat(totalAmtWithdrawn);
        float withdrawalLimitFloat = Float.parseFloat(withdrawalLimit);
        return ((totalAmtWithdrawnFloat + withdrawAmount) > withdrawalLimitFloat);
    }

    @Override
    public String toString() {
        String lastWithdrawnDateString;
        if (lastWithdrawnDate == null) {
            lastWithdrawnDateString = " ";
        } else {
            lastWithdrawnDateString = lastWithdrawnDate.toString();
        }
        String withdrawalLimitString;
        if (withdrawalLimit == null) {
            withdrawalLimitString = " ";
        } else {
            withdrawalLimitString = withdrawalLimit;
        }
        return totalAmtWithdrawn + ";" + lastWithdrawnDateString + ";" + withdrawalLimitString;
    }
}
