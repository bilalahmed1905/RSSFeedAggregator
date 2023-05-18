package cs20viewcontroller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class Browser extends JFrame implements ActionListener {

    private JEditorPane pane;
    private String url;

    public Browser() {
        super("Swing HTML Browser");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pane = new JEditorPane();
        pane.setEditable(false);
        add(new JScrollPane(pane));
        setSize(new Dimension(600, 600));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            pane.setPage(this.url);
        } catch (IOException t) {
        }
    }

    public void loadURL(String url) {
         try {
            pane.setPage(url);
        } catch (IOException t) {
        }
    }
    
    public static void main(String[] args) {
     Browser b = new Browser();
     b.loadURL("https://cbc.ca");
     b.setVisible(true);
    }
}
