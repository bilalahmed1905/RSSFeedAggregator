package cs20viewcontroller;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class Browser extends JFrame implements ActionListener, HyperlinkListener {

    private JTextField addressBar;
    private JEditorPane pane;
    private String url;

    public Browser(String url) {
        super("Swing HTML Browser");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addressBar = new JTextField();
        addressBar.addActionListener(this);
        pane = new JEditorPane();
        pane.setEditable(false);
        pane.addHyperlinkListener(this);
        this.url = url;
        add(addressBar, BorderLayout.NORTH);
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

    @Override
    public void hyperlinkUpdate(HyperlinkEvent evt) {
        if (evt.getEventType() != HyperlinkEvent.EventType.ACTIVATED) {
            return;
        }
        JEditorPane srcPane = (JEditorPane) evt.getSource();
        if (evt instanceof HTMLFrameHyperlinkEvent) {
            HTMLDocument doc = (HTMLDocument) pane.getDocument();
            doc.processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent) evt);
        } else {
            String url = evt.getURL().toString();
            addressBar.setText(url);
            try {
                pane.setPage(url);
            } catch (IOException t) {
            }
        }
    }

    public static void main(String args[]) {
       
    }
}
