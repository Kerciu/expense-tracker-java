package com.financeapp.test.guitest;


import com.financeapp.frontend.app.addframe.panels.AmountPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class AmountPanelTest {

    private AmountPanel panel;

    @BeforeEach
    public void setUp() {
        panel = new AmountPanel(800);
    }

    @Test
    public void testAmountPanelInitialization() {
        assertNotNull(panel.getAmountEnteringTextField());
        assertEquals("", panel.getAmountEnteringTextField().getText());
    }

    @Test
    public void testClearMethod() {
        panel.getAmountEnteringTextField().setText("100");
        panel.clear();
        assertEquals("", panel.getAmountEnteringTextField().getText());
    }
}