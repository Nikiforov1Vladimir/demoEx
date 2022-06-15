package nikVl.ui;

import nikVl.Main;
import nikVl.entities.Manager;
import nikVl.entities.Product;
import nikVl.utils.BaseForm;
import nikVl.utils.DialogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class AddPage extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField ProductTypeField;
    private JTextField descriptionField;
    private JTextField imageField;
    private JTextField registerDateField;
    private JButton addButton;
    private JButton goBackButton;
    private JSpinner costSpinner;

    public AddPage(){
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(500,500));

        initButtons();
        setVisible(true);
    }

    private void initButtons(){

        addButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String title = titleField.getText();

                        if(title.isEmpty() || title.length() > 50){
                            DialogUtil.showError("Имя не введено или слишком длинное");
                            return;
                        }

                        String ProductType = ProductTypeField.getText();

                        if(ProductType.isEmpty() || ProductType.length() > 50){
                            DialogUtil.showError("Тип продукта не введён или слишком длинный");
                            return;
                        }

                        String description = descriptionField.getText();

                        String image = imageField.getText();

                        int cost = (int) costSpinner.getNextValue();

                        if(cost == 0 || cost < 0){
                            System.out.println("Цена не может быть меньше либо равна нулю");
                            return;
                        }

                        Date registerDate = null;

                        try {
                            registerDate = Main.DATE_FORMAT.parse(registerDateField.getText());
                        } catch (ParseException ex) {
                            System.out.println("Дата не введена или введена некорректно");
                            return;
                        }

                        Product product = new Product(title, ProductType, description, image, cost, registerDate);


                        try {
                            Manager.insertIntoTable(product);
                            DialogUtil.showInfo("Продукт " + product.getTitle() + " добавлен");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }


                    }
                }
        );

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
