package nikVl.ui;

import nikVl.entities.Manager;
import nikVl.entities.Product;
import nikVl.utils.BaseForm;
import nikVl.utils.DialogUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainPage extends BaseForm {
    private JPanel mainPanel;
    private JButton goToListButton;
    private JButton goToAddButton;
    private JButton goToEditButton;

    public MainPage(){
        setContentPane(mainPanel);
        setVisible(true);

        initButton();
    }

    void initButton(){

        goToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ListPage();
            }
        });

        goToAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddPage();
            }
        });

        goToEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog(null,"Введите id продукта","Ввод",JOptionPane.QUESTION_MESSAGE);
                if(s == null){
                    return;
                }
                if(s.isEmpty()){
                    DialogUtil.showError("Введите ID");
                    return;
                }

                int id = -1;

                try{
                    id = Integer.parseInt(s);
                }catch (Exception exception){
                    DialogUtil.showError("Введён некоректный ID");
                    return;
                }

                Product product = null;

                try {
                    product = Manager.selectById(id);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if(product == null){
                    DialogUtil.showError("Продукта с таким ID не существует");
                    return;
                }

                dispose();
                new EditPage(product);
            }
        });

    }

}
