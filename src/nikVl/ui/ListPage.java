package nikVl.ui;

import nikVl.entities.Manager;
import nikVl.entities.Product;
import nikVl.utils.BaseForm;
import nikVl.utils.CustomTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ListPage extends BaseForm {
    private JPanel mainPanel;
    private JButton goBackButton;
    private JTable mainTable;
    private CustomTableModel<Product> model;

    public ListPage(){
        setContentPane(mainPanel);
        setVisible(true);
        setMinimumSize(new Dimension(800,700));

        initTable();
        initButton();
    }

    public void initTable(){
        try {
            model = new CustomTableModel<>(
                    Product.class,
                    Manager.selectAllFromProduct()
            );
            mainTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initButton(){
        goBackButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new MainPage();

                    }
                }
        );
    }

}
