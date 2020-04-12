import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Objects;

public class PresidentFrame extends JFrame implements ActionListener, ItemListener {
    private PresidentPanel presidentPanel;
    private JComboBox<Object> list;
    private ButtonPanel buttons;
    private Names names;

    public PresidentFrame() throws IOException {
        super("The Former Presidents of the United States of America");
        setLayout(new BorderLayout());

        names = new Names();
        list = new JComboBox<>(names.toArray());
        list.addItemListener(this);
        add(list, BorderLayout.NORTH);

        presidentPanel = new PresidentPanel(names.get(0));
        add(presidentPanel, BorderLayout.CENTER);

        buttons = new ButtonPanel();
        add(buttons, BorderLayout.SOUTH);

        buttons.prev.addActionListener(this);
        buttons.next.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1215, 577);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons.prev) {
            try {
                remove(presidentPanel);
                presidentPanel = new PresidentPanel(names.get(names.indexOf(presidentPanel.name) - 1));
                add(presidentPanel, BorderLayout.CENTER);
                repaint();
                setSize(getWidth() + 1, getHeight() + 1);
                setSize(getWidth() - 1, getHeight() - 1);
            } catch (Exception ex) {
                //george washington
            }
        } else {
            try {
                remove(presidentPanel);
                presidentPanel = new PresidentPanel(names.get(names.indexOf(presidentPanel.name) + 1));
                add(presidentPanel, BorderLayout.CENTER);
                repaint();
                setSize(getWidth() + 1, getHeight() + 1);
                setSize(getWidth() - 1, getHeight() - 1);
            } catch (Exception ex) {
                //latest president to leave office
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            remove(presidentPanel);
            presidentPanel = new PresidentPanel((String) Objects.requireNonNull(list.getSelectedItem()));
            add(presidentPanel, BorderLayout.CENTER);
            repaint();
            setSize(getWidth() + 1, getHeight() + 1);
            setSize(getWidth() - 1, getHeight() - 1);
        } catch (Exception ex) {
            //failed
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        new PresidentFrame();
    }
}