package cs20viewcontroller;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.undo.UndoManager;

/**
 * Write methods in this class for displaying data in the DrawnView.
 *
 * You can use all the public instance variables you defined in AllModelsForView
 * and DrawnView as though they were part of this class! This is due to the
 * magic of subclassing (i.e. using the extends keyword).
 *
 * The methods for displaying data in the DrawnView are written as methods in
 * this class.
 *
 * Make sure to use these methods in the ViewUserActions class though, or else
 * they will be defined but never used!
 *
 * @author cheng
 */
public class ViewOutputs extends DrawnView {

    public static void addTo(JTextField txtField) {
        JPopupMenu popup = new JPopupMenu();
        UndoManager undoManager = new UndoManager();
        txtField.getDocument().addUndoableEditListener(undoManager);
        Action copyAction = new AbstractAction("Copy") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.copy();
            }
        };

        Action cutAction = new AbstractAction("Cut") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.cut();
            }
        };

        Action pasteAction = new AbstractAction("Paste") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.paste();
            }
        };

        Action selectAllAction = new AbstractAction("Select All") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtField.selectAll();
            }
        };

//        popup.add (undoAction);
        popup.addSeparator();
        popup.add(cutAction);
        popup.add(copyAction);
        popup.add(pasteAction);
        popup.addSeparator();
        popup.add(selectAllAction);

        txtField.setComponentPopupMenu(popup);
    }

    public static void loadImage() throws IOException {
        BufferedImage img = ImageIO.read(new File(
                "/Users/bilalahmed/Downloads/chienpao.png"));
        ImageIcon icon = new ImageIcon(img);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(img.getHeight(), img.getWidth());
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean setCopyPaste() {
        ViewOutputs.addTo(urlField);
        return true;
    }

    public void setHyperlink() {
        urlLabel.setForeground(Color.BLUE.darker());
        urlLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI(rss.getSubArticleLinkatIndex(rss.getHeadlineNum())));

                } catch (IOException | URISyntaxException e1) {
                }
            }
        });
    }

    public boolean setClickable() {
        setHyperlink();
        return true;
    }

    public void clear(JTextField field) {
        field.setText("");
    }

    boolean x = setCopyPaste();
    boolean clickable = setClickable();
}
