package com.company.utils;
import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame{
    private JPanel mainPanel;

    public BaseForm(){
        setVisible(true);
        setContentPane(mainPanel);
        setSize(new Dimension(400,400));
        setLocationRelativeTo(null);
    }
}
