package com.precompiler.model.mm;

import com.precompiler.model.kmm.KMMTransaction;
import com.precompiler.utils.CategoryUtils;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


/**
 * @author Richard Li
 */

public class MMTransaction {
    public MMTransaction(@NonNull String account, @NonNull KMMTransaction kmmTransaction) {
        this.account = account;
        this.kmmTransaction = kmmTransaction;
    }

    public String getDate() {
        Objects.requireNonNull(kmmTransaction.getDate());
        return kmmTransaction.getDate().replace("-", ".");
    }

    public String getAccount() {
        return account;
    }

    public String getType() {
        Objects.requireNonNull(kmmTransaction.getAmount());
        BigDecimal amount = new BigDecimal(kmmTransaction.getAmount());
        if (amount.signum() >= 0) {
            // income
            return "Income";
        } else {
            if (CategoryUtils.contains(kmmTransaction.getAccountOrCategory())) {
                // expense
                return "Expense";
            } else {
                // money transfer
                return "Transfer-Out";
            }
        }
    }

    public String getMainCategory() {
        Objects.requireNonNull(kmmTransaction.getAccountOrCategory());
        if ("Transfer-Out".equals(getType())) {
            return kmmTransaction.getAccountOrCategory();
        } else {
            return CategoryUtils.parseCategoryString(kmmTransaction.getAccountOrCategory()).get(0);
        }
    }

    public String getSubCategory() {
        Objects.requireNonNull(kmmTransaction.getAccountOrCategory());
        if ("Transfer-Out".equals(getType())) {
            return "";
        } else {
            List<String> categories = CategoryUtils.parseCategoryString(kmmTransaction.getAccountOrCategory());
            if (categories.size() >= 2) {
                return categories.get(1);
            } else {
                return "";
            }
        }
    }

    public String getContents() {
        return kmmTransaction.getMemo() == null ? "" : kmmTransaction.getMemo();
    }

    public String getAmount() {
        Objects.requireNonNull(kmmTransaction.getAmount());
        return new BigDecimal(kmmTransaction.getAmount()).abs().toPlainString();
    }

    public String getDetails() {
        return "";
    }

    private final KMMTransaction kmmTransaction;
    private final String account;
}
