package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends BaseFrame {
    public MainFrame(User user)
    {
        super("Finance Manager", user, 800, 600);
    }

    @Override
    protected void addGuiComponents() {
        add(createWelcomeMessageLabel());
        add(createCurrentBalanceLabel());
    }

    private JLabel createWelcomeMessageLabel()
    {
        String message = "<html>" + "<body style='text-align:center'>" +
                "<b>Welcome " + user.getUsername() +"!"+"</b><br>" +
                "What would you like to do?</body></html>";
        JLabel messageLabel = new JLabel(message);
        addWelcomeMessageAttributes(messageLabel);
        return messageLabel;
    }

    private JLabel createCurrentBalanceLabel()
    {
        JLabel balanceLabel = new JLabel("Current Balance");
        addBalanceLabelAttributes(balanceLabel);
        return balanceLabel;
    }

    private JTextField createCurrentBalanceTextField()
    {
        JTextField currentBalanceTextField = new JTextField(
                user.getBalance() + " "+getCurrentCurrency()
        );
        addCurrentBalanceTextFieldAttributes(currentBalanceTextField);
        return currentBalanceTextField;
    }

    private void addWelcomeMessageAttributes(JLabel messageLabel)
    {
        messageLabel.setBounds(0, 20, getWidth() - 10, 40);
        messageLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void addBalanceLabelAttributes(JLabel balanceLabel)
    {
        balanceLabel.setBounds(0, 80, getWidth() - 10, 30);
        balanceLabel.setFont(new Font("Dialog", Font.PLAIN, 22));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void addCurrentBalanceTextFieldAttributes(JTextField textField)
    {
        textField.setBounds(15, 120, getWidth() - 50, 40);
        textField.setFont(new Font("Dialog", Font.BOLD, 28));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private String getCurrentCurrency()
    {
        // for now, TODO fetch currency converter API
        return "USD";
    }
}
