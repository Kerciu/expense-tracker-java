package com.financeapp.frontend.app.generateReport.panels;

import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ReportTypePanel extends JPanel {
    private String extensionName;
    private JCheckBox checkBox;
    private JTextField fileNameTextField;

    public ReportTypePanel(String extensionName, int width)
    {
        this.extensionName = extensionName;

        arrangeLayOutAndAddComponents(width);
    }

    private void arrangeLayOutAndAddComponents(int width)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Checkbox
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        add(createFileToGenerateCheckBox(), gbc);

        // Label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createFileExtensionLabel(width), gbc);

        // TextArea
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createFileNameTextField(width), gbc);
    }

    private JTextField createFileNameTextField(int width)
    {
        fileNameTextField = UIComponentFactory.createTextField(
                20, 20, (width - 30), 30, 20, false
        );
        return fileNameTextField;
    }

    private JCheckBox createFileToGenerateCheckBox()
    {
        checkBox = UIComponentFactory.createCheckBox(
                "", 5, 35, 15, 15, 0, false
        );
        checkBox.addItemListener(createCheckBoxItemListener());
        return checkBox;
    }

    private ItemListener createCheckBoxItemListener()
    {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (checkBox.isSelected()) {
                    fileNameTextField.setEditable(true);
                }
                else {
                    fileNameTextField.setEditable(false);
                    fileNameTextField.setText("");
                }
            }
        };
    }

    private JLabel createFileExtensionLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Generate " + extensionName +" file",
                20, 0, (width - 30), 30, 24,
                SwingConstants.LEFT
        );
    }

    public String getExtensionName() {
        return extensionName;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }
}
