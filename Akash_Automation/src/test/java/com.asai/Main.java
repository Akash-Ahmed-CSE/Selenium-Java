package com.asai;

public class Main {
    private static void loadGroupTransaction() {
        GroupTransaction gt = new GroupTransaction();
        gt.start("admin", "Test@123", "83", "26832");
    }

    private static void loadLoanDisbursement() {
        LoanDisbursement ld = new LoanDisbursement();
        ld.start("13106",
                "Test@123",
                "91",
                "28783",
                "1523",
                "31191",
                "24/05/2023",
                "10000",
                "5001");
    }

    public static void main(String[] args) {
        // load test on group transaction
//        loadGroupTransaction();

        // load test on loan disbursement
        loadLoanDisbursement();
    }
}