import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageComponent extends JLabel {

    public ImageComponent(String fileName) {
        setIcon( new ImageIcon(fileName));
    }

    public ImageComponent(BufferedImage image) {
        setIcon(new ImageIcon(image));
    }
}