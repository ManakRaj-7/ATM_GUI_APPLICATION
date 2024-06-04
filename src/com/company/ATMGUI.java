package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {
    private BankAccount account;
    private JFrame frame;
    private JTextField amountField;
    private JLabel balanceLabel;

    public ATMGUI(BankAccount account) {
        this.account = account;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        balanceLabel = new JLabel("Balance: $" + account.getBalance());
        balanceLabel.setBounds(20, 20, 200, 25);
        frame.add(balanceLabel);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 60, 80, 25);
        frame.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(100, 60, 165, 25);
        frame.add(amountField);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(20, 100, 150, 25);
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        frame.add(checkBalanceButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(180, 100, 100, 25);
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        frame.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(290, 100, 100, 25);
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        frame.add(withdrawButton);

        frame.setVisible(true);
    }

    private void checkBalance() {
        balanceLabel.setText("Balance: $" + account.getBalance());
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            checkBalance();
            JOptionPane.showMessageDialog(frame, "Deposited: $" + amount);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                checkBalance();
                JOptionPane.showMessageDialog(frame, "Withdrawn: $" + amount);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid or insufficient amount for withdrawal.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        new ATMGUI(account);
    }
}
