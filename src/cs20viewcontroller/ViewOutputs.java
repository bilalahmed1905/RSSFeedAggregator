package cs20viewcontroller;

import cs20models.Channel;
import cs20models.Database;
import cs20models.FeedItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    private final Browser browser = new Browser();
    public String pageURL = "";
    private int readStatus;

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

    public void displayWebpage(String pageURL) throws IOException {
        browser.loadURL(pageURL);
        browserPanel.add(browser);
        browserPanel.setViewportView(browser);
        browserPanel.revalidate();
        browserPanel.repaint();
    }

    JPanel parentPanel = new JPanel();

    public void updateUI(JPanel panel) {
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    public void updateReadStatus(int r) {
        this.readStatus = r;
    }

    public void addItems(int numberOfItems, ArrayList<FeedItem> arr) {
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        for (int i = numberOfItems - 1; i > 0; i--) {
            String headline = arr.get(i).getTitle();
            String desc = arr.get(i).getDescription();
            String URL = arr.get(i).getURL();
            String date = arr.get(i).getDate();
            this.readStatus = arr.get(i).getItemReadStatus();
            JPanel itemPanel = new JPanel(new BorderLayout());
            JLabel headlineLabel = new JLabel(headline);
            JLabel dateLabel = new JLabel(date);
            Font title = new Font(Font.SANS_SERIF, Font.BOLD, 18);
            Font subtitle = new Font(Font.SERIF, Font.PLAIN, 13);
            headlineLabel.setFont(title);
            JLabel descLabel = new JLabel(desc);
            headlineLabel.setPreferredSize(new Dimension(100, 25));
            descLabel.setPreferredSize(new Dimension(100, 50));
            descLabel.setFont(subtitle);
            itemPanel.setBounds(150, 150, 100, 100);
            if (readStatus == 1) {
                itemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            } else if (readStatus == 0) {
                itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            }
            itemPanel.add(dateLabel, BorderLayout.NORTH);
            itemPanel.add(headlineLabel, BorderLayout.CENTER);
            itemPanel.add(descLabel, BorderLayout.SOUTH);

            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        pageURL = URL;
                        System.out.println(pageURL);
                        displayWebpage(pageURL);
                    } catch (IOException ex) {
                        Logger.getLogger(ViewOutputs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    try {
                        pageURL = URL;
                        updateUI(itemPanel);
                        Database.updateReadStatus(1, headline);
                        updateReadStatus(1);
                        if (readStatus == 1 && e.isConsumed() && itemPanel.getBorder().equals(BorderFactory.createLineBorder(Color.BLUE, 1))) {
                            updateUI(itemPanel);
                        } else if (readStatus == 0 && e.isConsumed() && itemPanel.getBorder().equals(BorderFactory.createLineBorder(Color.BLUE, 1))) {
                            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                        } else if (readStatus == 0 && e.isConsumed() && itemPanel.getBorder().equals(BorderFactory.createLineBorder(Color.BLACK, 1))) {
                            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                        } else if (readStatus == 1 && e.isConsumed() && itemPanel.getBorder().equals(BorderFactory.createLineBorder(Color.GRAY, 1))) {
                            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewOutputs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            parentPanel.add(Box.createVerticalStrut(8));
            parentPanel.add(itemPanel);

            parentPanel.revalidate();
            parentPanel.repaint();
        }
        articleList.setViewportView(parentPanel);
    }

    public void removeItem(JPanel p) {

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
    }

    public boolean setCopyPaste() {
        ViewOutputs.addTo(customFeedField);
        return true;
    }

    public void clear(JTextField field) {
        field.setText("");
    }
    List l = new List();

    public void showSubscribedChannels() {
        JFrame fr = new JFrame();
        fr.setTitle("Subscribed Channels");
        JPanel p = new JPanel();
        l.addKeyListener(new KeyAdapter() {
            String selectedChannel = l.getSelectedItem();

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("key pressed" + e.getKeyCode());
                // 8 is keycode for delete key
                if (e.getKeyCode() == 8) {
                    try {
                        if (selectedChannel.equals(l.getSelectedItem())) {
                            Database.removeChannel(selectedChannel);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewOutputs.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    l.repaint();
                    l.revalidate();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 8) {
                    l.remove(selectedChannel);
                    l.repaint();
                    l.revalidate();
                }
            }
        });

        JLabel sc = new JLabel("Subscribed Channel List");
        sc.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        p.add(sc);
        p.add(Box.createVerticalStrut(8));
        ArrayList<Channel> arr = new ArrayList<>();
        try {
            arr = Database.getAllChannels();
        } catch (SQLException ex) {
            Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < arr.size(); i++) {
            l.add(arr.get(i).getTitle());
        }
        fr.add(l);
        fr.setSize(600, 450);
        fr.setVisible(true);
    }

    public void fetchEveryInterval(long interval) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ArrayList<Channel> channels = new ArrayList<>();
                try {
                    channels = Database.getAllChannels();
                    for (int i = 0; i < channels.size(); i++) {
                        try {
                            rss.fetchAndStoreFeed(channels.get(i).getRSSLink());
                        } catch (NoSuchAlgorithmException | ParseException ex) {
                            Logger.getLogger(ViewOutputs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("Fetched at interval");
                } catch (SQLException ex) {
                    Logger.getLogger(ViewOutputs.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<FeedItem> arr = new ArrayList<>();
                try {
                    arr = Database.fetchArticles();
                    addItems(Database.getResultSize(), arr);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
                    showError("Warning!", "Could not retrieve articles from database...");
                }
            }
        };

        Timer timer = new Timer("Update from database");
        long delay = 0; // Initial delay before the first execution
        long period = interval; // Interval between subsequent executions

        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void showError(String title, String message) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = pane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public void showDialog(String title, String message) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.OK_OPTION);
        JDialog dialog = pane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    boolean x = setCopyPaste();

}
