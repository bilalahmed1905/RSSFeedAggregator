package cs20viewcontroller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class OldBrowser extends JFrame implements ActionListener {

   // private JTextField addressBar;
    private JEditorPane pane;
    private String url;

    public OldBrowser(String url) {
        super("Swing HTML Browser");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // addressBar = new JTextField();
       // addressBar.addActionListener(this);
        pane = new JEditorPane();
        pane.setEditable(false);
        this.url = url;
       // add(addressBar, BorderLayout.NORTH);
        add(new JScrollPane(pane));
        setSize(new Dimension(600, 600));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String url = this.url;
        try {
            pane.setPage(url);
        } catch (IOException t) {
        }
    }

    public void loadURL(String url) {
         try {
            pane.setPage(url);
        } catch (IOException t) {
        }
    }
  
    
    public static void main(String args[]) {
//       Browser b = new Browser("");
//       //String url = "https://www.cnn.com/business/live-news/fox-news-dominion-trial-04-18-23/index.html";
//       b.loadURL ( "https://www.cnn.com/business/live-news/fox-news-dominion-trial-04-18-23/index.html");
//     //  b.addressBar.v
//       b.setVisible(true);


    }
}
