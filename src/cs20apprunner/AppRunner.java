/*
 * This project is a template of a Java GUI (Graphical User Interface) program.
 * It uses the Swing library, and a simplified "MVC" pattern structure,
 * but broken up into files that are more "single purpose" to enable a more
 * "straight through" procedural approach to what codes needs to be written
 * to create a custom GUI.
 *
 * "MVC" means "Model View Controller".  Read a bit more about it first:
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 * 
 * See especially:
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller#Component_interactions
 * 
 * AppRunner only creates a single CompleteViewController object to set up the GUI.
 * In fact, a series of subclass hierarchy means that the CompleteViewController object
 * automatically sets itself up with the necessary models, view, and controller.
 *
 * AppRunner also sets up GUI settings of the operating system to display your 
 * program using the "native" GUI "skin".
 * 
 * The "meat" of your program is created through the Java classes that are
 * representing the models, views, and controller, contained in the other
 * cs20models and cs20viewcontroller packages.
 */

/*
 * WARNING: There is nothing in this file for students to edit or change.
 *          This file is intended to have no student-serviceable code.
 */

package cs20apprunner;

import cs20viewcontroller.CompleteViewController;

/**
 *
 * @author cheng
 */
public class AppRunner {
    
    // WARNING: Do NOT modify or write any code in this file!
    //          This file is intended to have no student-serviceable code.
    // <editor-fold defaultstate="collapsed" desc="Template class - do NOT modify">
    private final static Runnable mainRunner = new Runnable() {

        @Override
        public void run() {
            CompleteViewController theMainView = new CompleteViewController();
            theMainView.setVisible(true);
        }
    };

    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CompleteViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompleteViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompleteViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompleteViewController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(AppRunner.mainRunner);
    } // </editor-fold>
}
