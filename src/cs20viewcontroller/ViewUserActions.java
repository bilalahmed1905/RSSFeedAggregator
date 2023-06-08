/*
 * The controller classes (like the ViewUserActions class) provides actions
 * that the user can trigger through the view classes.  Those actions are 
 * written in this class as private inner classes (i.e. classes 
 * declared inside another class).
 *
 * You can use all the public instance variables you defined in AllModelsForView, 
 * DrawnView, and ViewOutputs as though they were part of this class! This is 
 * due to the magic of subclassing (i.e. using the extends keyword).
 */
package cs20viewcontroller;

import cs20models.FeedItem;
import cs20models.FeedParser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cs20models.Database;
import cs20models.Channel;
import java.awt.Desktop;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import javax.swing.JFrame;

/**
 * ViewUserActions is a class that contains actions users can trigger.
 *
 * User actions are written as private inner classes that implements the
 * ActionListener interface. These actions must be "wired" into the DrawnView in
 * the ViewUserActions constructor, or else they won't be triggered by the user.
 *
 * Actions that the user can trigger are meant to manipulate some model classes
 * by sending messages to them to tell them to update their data members.
 *
 * Actions that the user can trigger can also be used to manipulate the GUI by
 * sending messages to the view classes (e.g. the DrawnView class) to tell them
 * to update themselves (e.g. to redraw themselves on the screen).
 *
 * @author cheng
 */
public class ViewUserActions extends ViewOutputs {

    /*
     * Step 1 of 2 for writing user actions.
     * -------------------------------------
     *
     * User actions are written as private inner classes that implement
     * ActionListener, and override the actionPerformed method.
     *
     * Use the following as a template for writting more user actions.
     */
    private class AddChannel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Channel channelInfo = null;
            FeedParser parser = null;
            try {
                String url = customFeedField.getText().trim();
                try {
                    parser = new FeedParser(url);
                    try {
                        channelInfo = parser.getRChannelInfo();
                    } catch (RuntimeException e) {
                        showError("Warning!", "Unable to retrieve Channel Info or \n The URL is not an RSS Feed");
                    }
                } catch (RuntimeException e) {
                    showError("Warning!", "Invalid URL");
                }
                try {
                    Database.addChannel(channelInfo);
                    rss.fetchAndStoreFeed(url);
                } catch (SQLException | NoSuchAlgorithmException | ParseException ex) {
                    Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
                    showError("Warning!", "An error has occurred \n Or you may already be subscribed to this feed");
                }
                ViewOutputs.addTo(customFeedField);
            } catch (URISyntaxException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
                showError("Warning!", "Unable to retrieve Channel Info or \n The URL is not an RSS Feed");
            }
        }

    }

    private class OpenInDefaultBrowser implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Desktop.getDesktop().browse(new URI(pageURL));
            } catch (IOException | URISyntaxException ex) {
                showError("Warning!", "Not a valid URL \n Or no article selected");
            }
        }

    }

    private class OpenInNewWindow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Browser b = new Browser();
            JFrame jf = new JFrame();
            try {
                b.loadURL(pageURL);
            } catch (IOException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
                showError("Warning!", "Not a valid URL \n Or no article selected");
            }
            jf.add(b);
            jf.setVisible(true);
            jf.setSize(800, 600);
            jf.repaint();
            jf.revalidate();
        }

    }

    private class ShowSubscribedChannels implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            showSubscribedChannels();
        }
    }

    private class Refresh implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
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
            } catch (SQLException ex) {
//                Logger.getLogger(ViewOutputs.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<FeedItem> arr = new ArrayList<>();
            try {
                arr = Database.fetchArticles();
                removeItems();
                addItems(arr.size(), arr);
            } catch (SQLException ex) {
//                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
                showError("Warning!", "Could not retrieve articles from database...");
            }

        }
    }

    private class FetchArticles implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
            fetchEveryInterval(210000);
            ArrayList<FeedItem> feedItems = new ArrayList<>();
            try {
                feedItems = Database.fetchArticles();
                addItems(Database.getResultSize(), feedItems);
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
                showError("Warning!", "Could not retrieve articles from database...");
            }
        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }

    }

    /*
     * ViewUserActions constructor used for wiring user actions to GUI elements
     */
    public ViewUserActions() {
        /*
         * Step 2 of 2 for writing user actions.
         * -------------------------------------
         *
         * Once a private inner class has been written for a user action, you
         * can wire it to a GUI element (i.e. one of GUI elements you drew in
         * the DrawnView class and which you gave a memorable public variable
         * name!).
         *
         * Use the following as a template for wiring more user actions.
         */
        this.refreshBtn.addActionListener(new Refresh());
        this.addChannelBtn.addActionListener(new AddChannel());
        this.openInBrowserBtn.addActionListener(new OpenInDefaultBrowser());
        this.addWindowListener(new FetchArticles());
        this.openInNewWinBtn.addActionListener(new OpenInNewWindow());
        this.feedMenu_openInBrowser.addActionListener(new OpenInDefaultBrowser());
        this.feedMenu_openInNewWin.addActionListener(new OpenInNewWindow());
        this.feedMenu_viewChannels.addActionListener(new ShowSubscribedChannels());
    }
}
