package rooliebanking;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.roolie.RulesEngine;
import rooliebanking.rule.BankingRuleArgs;

/**
 * 规则处理的主要流程
 * <p>1.加载规则文件</p>
 * <p>2.初始化处理数据</p>
 * <p>3.逐条记录按照规则处理</p>
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Log.msg("** Roolie Banking Sample **");

        // Get the config file as an InputStream


//        InputStream is = Main.class.getClassLoader().getResourceAsStream(
//                "rooliebanking/roolie-config.xml");
        InputStream is = new BufferedInputStream(new FileInputStream(new File("src/main/java/rooliebanking/roolie-config.xml")));

        // Create RulesEngine instance
        RulesEngine rules = new RulesEngine(is);

        // Create some rule arguments (aka "Facts") to test for some users
        List<BankingRuleArgs> bankingRuleArgsList = createRuleArgsToTest();

        // See if rules pass for each BankingRuleArgs created.
        for (BankingRuleArgs ruleArgs : bankingRuleArgsList) {
            msg("\n* Evaluating 'userCanWithdrawFunds' rule for " + ruleArgs.getUser());
            boolean canWithdrawFunds =
                    rules.passesRule("userCanWithdrawFunds", ruleArgs);

            msg("User can withdraw funds? " + canWithdrawFunds);
            if (canWithdrawFunds) {
                withdrawFunds(ruleArgs);
            } else {
                msg(ruleArgs.getUser() + " cannot withdraw $"
                        + ruleArgs.getRequestedWithdrawlAmount());
            }

            msg("\n* Evaluating 'userCanDepositFunds' rule for " + ruleArgs.getUser());
            boolean canDepositFunds =
                    rules.passesRule("userCanDepositFunds", ruleArgs);

            msg("User can deposit funds? " + canDepositFunds);
            if (canDepositFunds) {
                depositFunds(ruleArgs);
            } else {
                msg(ruleArgs.getUser() + " cannot deposit $"
                        + ruleArgs.getRequestedDepositAmount());
            }

        }

    }

    protected static void msg(String msg) {
        System.out.println(msg);
    }

    protected static void withdrawFunds(BankingRuleArgs ruleArgs) {
        msg("Withdrawing $"
                + ruleArgs.getRequestedWithdrawlAmount()
                + " from " + ruleArgs.getUser() + "'s account.");
    }

    private static void depositFunds(BankingRuleArgs ruleArgs) {
        msg("Depositing $"
                + ruleArgs.getRequestedDepositAmount()
                + " into " + ruleArgs.getUser() + "'s account.");
    }

    protected static List<BankingRuleArgs> createRuleArgsToTest() {
        List<BankingRuleArgs> ruleArgsList = new LinkedList<BankingRuleArgs>();

        // Oscar
        BankingRuleArgs oscarRuleArgs = new BankingRuleArgs();
        oscarRuleArgs.setUser("Oscar");
        oscarRuleArgs.setAccountBalance(100D);
        oscarRuleArgs.setRequestedWithdrawlAmount(20D);
        oscarRuleArgs.setWithdrawlAmountToday(200D);
        oscarRuleArgs.setRequestedDepositAmount(500D);
        ruleArgsList.add(oscarRuleArgs);

//        // Julia
//        BankingRuleArgs juliaRuleArgs = new BankingRuleArgs();
//        juliaRuleArgs.setUser("Julia");
//        juliaRuleArgs.setAccountBalance(100D);
//        juliaRuleArgs.setRequestedWithdrawlAmount(20D);
//        juliaRuleArgs.setWithdrawlAmountToday(200D);
//        juliaRuleArgs.setRequestedDepositAmount(30D);
//        ruleArgsList.add(juliaRuleArgs);
//
//        // Samuel
//        BankingRuleArgs samuelRuleArgs = new BankingRuleArgs();
//        samuelRuleArgs.setUser("Samuel");
//        samuelRuleArgs.setAccountBalance(50D);
//        samuelRuleArgs.setRequestedWithdrawlAmount(100D);
//        samuelRuleArgs.setWithdrawlAmountToday(200D);
//        samuelRuleArgs.setRequestedDepositAmount(99D);
//        ruleArgsList.add(samuelRuleArgs);

        return ruleArgsList;

    }

}
