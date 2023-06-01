package cs20viewcontroller;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Browser extends JPanel {

    int urlcount = 0;
    public JScrollPane scrollPane;
    final private JEditorPane browserPane;

    public Browser() {
        setLayout(new BorderLayout());

        browserPane = new JEditorPane();
        browserPane.setEditable(false);
        browserPane.setContentType("text/html");
        scrollPane = new JScrollPane(browserPane);
        add(scrollPane, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    public void loadURL(String url) throws IOException {
        browserPane.setPage(url);
    }
}
