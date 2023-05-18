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

import cs20models.FeedMessage;
import cs20models.FeedParser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cs20models.SQL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

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
    private class SetURL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ArrayList<FeedMessage> arr = new ArrayList<>(10000);
            try {
                arr = SQL.getAllArticles();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            FeedParser parser = new FeedParser(customFeedField.getText());
            try {
                SQL.addChannel(parser.readFeed());
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            rss.setURL(customFeedField.getText());
            ViewOutputs.addTo(customFeedField);
            try {
                rss.sendItemsToDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            addItems(rss.getItemCount(), arr);
        }

    }

    private class ClearField implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
//            if (!urlField.getText().equals("")) {
//                clear(urlField);
//            }
        }

    }

    private class ClearPanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            removeItems();
        }

    }

    private class SetURLASCNN implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                feeds = SQL.getAllArticles();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            rss.setURL("http://rss.cnn.com/rss/cnn_topstories.rss");
            try {
                rss.sendItemsToDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            addItems(rss.getItemCount(), feeds);
        }

    }

    private class SetURLASCTV implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                feeds = SQL.getAllArticles();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            rss.setURL("https://www.ctvnews.ca/rss/ctvnews-ca-top-stories-public-rss-1.822009");
            try {
                rss.sendItemsToDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            addItems(rss.getItemCount(), feeds);

        }

    }

    private class SetURLASCBC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ArrayList<FeedMessage> arr = new ArrayList<>();
            try {
                arr = SQL.getAllArticles();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            rss.setURL("https://www.cbc.ca/cmlink/rss-topstories");
            try {
                rss.sendItemsToDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (arr != null) {
                addItems(rss.getItemCount(), arr);
            }

        }

    }

    private class SetURLASGlobal implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            ArrayList<FeedMessage> arr = new ArrayList<>();
            try {
                arr = SQL.getAllArticles();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            rss.setURL("https://globalnews.ca/feed/");
              addItems(rss.getItemCount(), arr);
            try {
                rss.sendItemsToDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
                addItems(rss.getItemCount(), arr);
        }

    }

    private class OpenWebPage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

        }

    }

  private class LoadFromDatabase implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        ArrayList<FeedMessage> arr = new ArrayList<>();
        try {
            arr = SQL.getAllArticles();
            System.out.println(arr.get(1).getTitle() + " " + SQL.getResultSize());
            addItems(SQL.getResultSize(), arr);
        } catch (SQLException ex) {
            Logger.getLogger(ViewUserActions.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        this.loadFromDatabaseBtn.addActionListener(new LoadFromDatabase());
        this.clearBtn.addActionListener(new ClearPanel());
        this.ctvBtn.addActionListener(new SetURLASCTV());
        this.cnnBtn.addActionListener(new SetURLASCNN());
        this.cbcBtn.addActionListener(new SetURLASCBC());
        this.setUrlBtn.addActionListener(new SetURL());
        this.globalBtn.addActionListener(new SetURLASGlobal());
    }
}
