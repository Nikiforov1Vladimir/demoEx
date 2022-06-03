package com.company.screens;

import com.company.utils.BaseForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends BaseForm{
    private JPanel mainPanel;
    private JButton ListButton;
    private JButton AddButton;
    private JButton EditButton;

    public MainPage(){
        setVisible(true);
        setContentPane(mainPanel);
        initButtons();
    }

    void initButtons(){
        ListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ListPage();
            }
        });
    }
}
