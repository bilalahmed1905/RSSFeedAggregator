package cs20viewcontroller;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Browser extends JPanel {
    int urlcount =0;
    
    final private JEditorPane browserPane;
    
    public Browser() {
        setLayout(new BorderLayout());

        browserPane = new JEditorPane();
        browserPane.setEditable(false);
        browserPane.setContentType("text/html");

        JScrollPane scrollPane = new JScrollPane(browserPane);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadURL(String url) {
        try {
            urlcount++;
            System.out.println("URL:" + url + "count: "+urlcount);
            browserPane.setPage(url);
            
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
