package nikVl.utils;

import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame{
    private JPanel Panel;

    public BaseForm(){
        setTitle("Stalker Shop");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(Panel);
        setVisible(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400,400));

    }
}
