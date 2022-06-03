package com.company.screens;

import com.company.entities.Manager;
import com.company.utils.BaseForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPage extends BaseForm {
    private JPanel mainPanel;
    private JButton GoBackButton;
    private JTextArea ElementsList;

    public ListPage(){

        setContentPane(mainPanel);
        initElements();
    }

    void initElements(){

        ElementsList.add(new Component() {
        });

        GoBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainPage();
            }
        });


    }
}
