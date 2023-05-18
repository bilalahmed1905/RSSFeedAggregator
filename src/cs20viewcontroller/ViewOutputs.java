package cs20viewcontroller;

import cs20models.FeedMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
        popup.addSeparator();
        popup.add(cutAction);
        popup.add(copyAction);
        popup.add(pasteAction);
        popup.addSeparator();
        popup.add(selectAllAction);
        
        txtField.setComponentPopupMenu(popup);
    }
    JPanel parentPanel = new JPanel();

    public void addItems(int numberOfItems, ArrayList<FeedMessage> arr) {
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < numberOfItems; i++) {
            String headline = arr.get(i).getTitle();
            String desc = arr.get(i).getDescription();
            String link = arr.get(i).getURL();
            JPanel itemPanel = new JPanel(new BorderLayout());
            JLabel headlineLabel = new JLabel(headline);
            Font title = new Font(Font.SERIF, Font.BOLD, 23);
            Font subtitle = new Font(Font.SERIF, Font.PLAIN, 14);
            headlineLabel.setFont(title);
            JLabel descLabel = new JLabel(desc);
            descLabel.setPreferredSize(new Dimension(500, 50));
            descLabel.setFont(subtitle);
            itemPanel.setBounds(150, 150, 100, 100);
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            itemPanel.add(headlineLabel, BorderLayout.NORTH);
            itemPanel.add(descLabel, BorderLayout.CENTER);
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI(link)); 
                    } catch (IOException | URISyntaxException ex) {
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                }
            });
            parentPanel.add(Box.createVerticalStrut(10));            
            parentPanel.add(itemPanel);
            parentPanel.revalidate();
            parentPanel.repaint();
        }
        articleList.setViewportView(parentPanel);
    }
    
    public void removeItems() {
        Component[] componentList = parentPanel.getComponents();
        int i = 0;
        for (Component c : componentList) {
            if (c instanceof Component) {
                parentPanel.remove(c);
            }
            i++;
        }
        
        parentPanel.revalidate();
        parentPanel.repaint();
        rss.clearLists();
    }
    
    public boolean setCopyPaste() {
        ViewOutputs.addTo(customFeedField);
        return true;
    }
    
    public void clear(JTextField field) {
        field.setText("");
    }
    boolean x = setCopyPaste();
}
