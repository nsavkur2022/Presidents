import javax.swing.*;
import java.awt.*;

public class JTitle extends JLabel {

    public JTitle(String text) {
        super(text);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(new Font("微软雅黑", Font.BOLD, 24));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    }
}