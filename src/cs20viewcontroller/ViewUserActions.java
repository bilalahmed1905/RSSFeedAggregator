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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import cs20models.FeedParser;
import cs20models.Feed;
import cs20models.FeedMessage;

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
            if (sql.createTable()) {
            rss.setURL(customFeedField.getText());
            ViewOutputs.addTo(customFeedField);
            rss.getItems();
            addItems(rss.getItemCount());
            }
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
            articleList.remove(parentPanel);
//            DrawnView.revalidate();
//            n==.repaint();
        }

    }

    private class SetURLASCNN implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            rss.setURL("http://rss.cnn.com/rss/cnn_topstories.rss");
            rss.getItems();
            addItems(rss.getItemCount());
        }

    }

    private class SetURLASCTV implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            rss.setURL("https://www.ctvnews.ca/rss/ctvnews-ca-top-stories-public-rss-1.822009");
            rss.getItems();
            addItems(rss.getItemCount());

        }

    }

    private class SetURLASCBC implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            rss.setURL("https://www.cbc.ca/cmlink/rss-topstories");
            rss.getItems();
            addItems(rss.getItemCount());

        }

    }
    
    private class SetURLASGlobal implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            rss.setURL("https://globalnews.ca/feed/");
            rss.getItems();
            addItems(rss.getItemCount());

        }

    }

    private class OpenWebPage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

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
//        this.setUrlBtn.addActionListener(new SetURL());
        this.clearBtn.addActionListener(new ClearPanel());
        this.ctvBtn.addActionListener(new SetURLASCTV());
        this.cnnBtn.addActionListener(new SetURLASCNN());
        this.cbcBtn.addActionListener(new SetURLASCBC());
        this.setUrlBtn.addActionListener(new SetURL());
        this.globalBtn.addActionListener(new SetURLASGlobal());
    }
}
