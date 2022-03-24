package nikVl.demoexPractice.util;

import javax.swing.*;
import java.awt.*;

public class baseForm extends JFrame{
    private JPanel mainPanel;

    public baseForm(){
        setContentPane(mainPanel);
        setVisible(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400,400));
    }
}
