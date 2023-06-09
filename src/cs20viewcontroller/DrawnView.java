/*
 * WARNING: There is nothing in this file for students to edit or change.
 *          This file is intended to have no student-serviceable code.
 *
 * "Draw" your GUI using the "Design" tab, and remember to give
 * them memorable variable names in the "Properties" panel
 * as well as make them public variables.
 * 
 * For convenience, you may want to set the default visibility of 
 * GUI elements to be public by going to:
 *    The NetBeans Preferences or Settings menu -> Miscellaneous -> GUI Builder
 * Then set Variables Modifier to public.
 */
package cs20viewcontroller;

import cs20models.AllModelsForView;

/**
 * WARNING: Do NOT modify or write any code in this file!
 *
 * @author cheng
 */
public class DrawnView extends AllModelsForView {
    // WARNING: Do NOT modify or write any code in this file!
    // <editor-fold defaultstate="collapsed" desc="Generated Code">

    public DrawnView() {
        initComponents();
    }// </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        note = new javax.swing.JLabel();
        articleList = new javax.swing.JScrollPane();
        customFeedField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        addChannelBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        browserPanel = new javax.swing.JScrollPane();
        openInBrowserBtn = new javax.swing.JButton();
        openInNewWinBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        feedMenu = new javax.swing.JMenu();
        feedMenu_viewChannels = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        feedMenu_openInBrowser = new javax.swing.JMenuItem();
        feedMenu_openInNewWin = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        customFeedField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customFeedFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Custom Feed URL");

        addChannelBtn.setBackground(new java.awt.Color(0, 153, 153));
        addChannelBtn.setText("Add Feed");
        addChannelBtn.setAutoscrolls(true);
        addChannelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChannelBtnActionPerformed(evt);
            }
        });

        refreshBtn.setText("Refresh");

        openInBrowserBtn.setText("Open In Browser");

        openInNewWinBtn.setText("Open in New Window");
        openInNewWinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openInNewWinBtnActionPerformed(evt);
            }
        });

        feedMenu.setText("Feed");

        feedMenu_viewChannels.setText("View Channels");
        feedMenu_viewChannels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedMenu_viewChannelsActionPerformed(evt);
            }
        });
        feedMenu.add(feedMenu_viewChannels);

        jMenuBar1.add(feedMenu);

        jMenu3.setText("Article");

        feedMenu_openInBrowser.setText("Open In Browser");
        feedMenu_openInBrowser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feedMenu_openInBrowserActionPerformed(evt);
            }
        });
        jMenu3.add(feedMenu_openInBrowser);

        feedMenu_openInNewWin.setText("Open In New Window");
        jMenu3.add(feedMenu_openInNewWin);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customFeedField, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addChannelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(articleList, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(openInBrowserBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(openInNewWinBtn)
                        .addGap(520, 520, 520))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(391, 391, 391)
                                .addComponent(note))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(browserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(openInBrowserBtn)
                            .addComponent(openInNewWinBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customFeedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addChannelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshBtn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(articleList, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addComponent(browserPanel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(note)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customFeedFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customFeedFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customFeedFieldActionPerformed

    private void addChannelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChannelBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addChannelBtnActionPerformed

    private void openInNewWinBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openInNewWinBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openInNewWinBtnActionPerformed

    private void feedMenu_viewChannelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedMenu_viewChannelsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feedMenu_viewChannelsActionPerformed

    private void feedMenu_openInBrowserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feedMenu_openInBrowserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feedMenu_openInBrowserActionPerformed
    // WARNING: Do NOT modify or write any code in this file!
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addChannelBtn;
    public javax.swing.JScrollPane articleList;
    public javax.swing.JScrollPane browserPanel;
    public javax.swing.JTextField customFeedField;
    public javax.swing.JMenu feedMenu;
    public javax.swing.JMenuItem feedMenu_openInBrowser;
    public javax.swing.JMenuItem feedMenu_openInNewWin;
    public javax.swing.JMenuItem feedMenu_viewChannels;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuBar jMenuBar2;
    public javax.swing.JLabel note;
    public javax.swing.JButton openInBrowserBtn;
    public javax.swing.JButton openInNewWinBtn;
    public javax.swing.JButton refreshBtn;
    // End of variables declaration//GEN-END:variables
}
