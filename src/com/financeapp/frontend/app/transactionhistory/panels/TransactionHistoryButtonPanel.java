package com.financeapp.frontend.app.transactionhistory.panels;

import com.financeapp.frontend.app.MainFrame;
import com.financeapp.frontend.app.transactionhistory.TransactionHistoryFrame;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class TransactionHistoryButtonPanel extends JPanel{
    private TransactionHistoryFrame source;

    public TransactionHistoryButtonPanel(TransactionHistoryFrame source, int width) {
        this.source = source;

        setLayout(null);
        addButtons(width);
        add(new HistoryCard(new BigDecimal(50), "Expense", "Food", "I bought kebab and went clubbing after it"));
    }

    private void addButtons(int width)
    {
        add(createGoBackButton(width));
    }

    private JButton createGoBackButton(int width)
    {
        JButton button = UIComponentFactory.createButton(
                "Go Back", 10, 0, width - 20, 30, 25
        );
        button.addActionListener(createGoBackButtonActionListener());
        return button;
    }

    private ActionListener createGoBackButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                source.dispose();
                new MainFrame(source.getUser()).setVisible(true);
            }
        };
    }
}
