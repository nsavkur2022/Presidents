import org.jsoup.Jsoup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PresidentPanel extends JPanel {

    public ImageComponent image;
    public String name, summary;

    public PresidentPanel(String name) throws IOException {
        this.name = name;

        URL whiteHouse = new URL("https://www.whitehouse.gov/about-the-white-house/presidents/" + name.replace(" ", "-"));
        BufferedReader site = new BufferedReader(new InputStreamReader(whiteHouse.openConnection().getInputStream()));

        String inputLine;
        StringBuilder page = new StringBuilder();
        while ((inputLine = site.readLine()) != null)
            page.append(inputLine);

        try {
            summary = Jsoup.parse(page.substring(page.indexOf("<!-- The Content -->"), page.indexOf("</p><p><em>")).replace("</p><p>", ",,")).text().replace(",,", "\n");
        } catch (Exception e) {
            summary = Jsoup.parse(page.substring(page.indexOf("<!-- The Content -->"), page.indexOf("</p><hr />")).replace("</p><p>", ",,")).text().replace(",,", "\n");
        }

        page = new StringBuilder(page.substring(page.indexOf("<picture>"), page.indexOf("</picture>")));
        URL webImage = new URL(page.substring(page.indexOf("=") + 2, page.indexOf("media") - 2).trim());
        BufferedImage image = ImageIO.read(webImage);
        this.image = new ImageComponent(image);

        setLayout(new BorderLayout());
        add(new JTitle(name), BorderLayout.NORTH);

        JTextArea summary = new JTextArea(this.summary);
        summary.setEditable(false);
        summary.setLineWrap(true);
        summary.setWrapStyleWord(true);
        summary.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        summary.setMargin( new Insets(5,5,5,5) );
        JScrollPane sp = new JScrollPane(summary);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(sp, BorderLayout.CENTER);
        add(this.image, BorderLayout.WEST);
    }

    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame("Pres");
        jFrame.add(new PresidentPanel("Calvin Coolidge"));
        jFrame.setVisible(true);
        jFrame.setSize(1000, 600);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}