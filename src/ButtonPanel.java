import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public JButton prev, next;

    public ButtonPanel() {
        super();
        setLayout(new GridLayout(1, 2));

        prev = new JButton("Previous");
        next = new JButton("Next");

        add(prev);
        add(next);
    }
}