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

public class EditPage extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField ProductTypeField;
    private JTextField descriptionField;
    private JTextField imageField;
    private JTextField registerDateField;
    private JButton addButton;
    private JButton goBackButton;
    private JSpinner costSpinner;
    private JTextField IdField;
    private JButton deleteButton;

    private Product product;

    public EditPage(Product product){
        setContentPane(mainPanel);
        this.product = product;
        setMinimumSize(new Dimension(500,500));

        initButtons();
        initFields();

        setVisible(true);
    }

    private void initFields(){
        IdField.setEditable(false);
        IdField.setText(String.valueOf(product.getID()));
        titleField.setText(product.getTitle());
        ProductTypeField.setText(product.getProductType());
        descriptionField.setText(product.getDescription());
        imageField.setText(product.getImage());
        costSpinner.setValue(product.getCost());
        registerDateField.setText(Main.DATE_FORMAT.format(product.getRegisterDate()));
    }

    private void initButtons(){

        addButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String title = titleField.getText();

                        if(title.isEmpty() || title.length() > 100){
                            DialogUtil.showError("Имя не введено или слишком длинное");
                            return;
                        }

                        String ProductType = ProductTypeField.getText();

                        if(ProductType.isEmpty() || ProductType.length() > 100){
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
                            DialogUtil.showError("Дата не введена или введена некорректно");
                            return;
                        }

                        product.setTitle(title);
                        product.setProductType(ProductType);
                        product.setDescription(description);
                        product.setImage(image);
                        product.setCost(cost);
                        product.setRegisterDate(registerDate);


                        try {
                            Manager.updateProduct(product);
                            DialogUtil.showInfo("Продукт " + product.getTitle() + " обновлён");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        dispose();
                        new MainPage();

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

        deleteButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(JOptionPane.showConfirmDialog(null,"Вы уверены, что хотите удалить этот продукт?","Подтверждение",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            try {
                                Manager.deleteProduct(product);
                                dispose();
                                new MainPage();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }return;
                    }
                }
        );
    }
}
