/*
 * This file is more like a "read me" file than anything, but it is used
 * to gather all the view and controller classes into one to be provided
 * to the AppRunner.
 * 
 * What's visible to the user as the GUI is drawn in the DrawnView file.
 * This template is best used for programs that has only one single window.
 * If you want to create more windows or changable panels, you'll need to 
 * understand proper MVC design better.
 * 
 * The view classes send messages to model classes to get information
 * to display to the user on the screen.  Methods for displaying that data
 * are written in the ViewOutputs class.
 * 
 * The controller classes (like the ViewUserActions class) provides actions
 * that the user can trigger through the view classes.  Those user actions are 
 * written in the ViewUserActions class, and are wired into GUI elements there
 * as well.
 * 
 * Students are expected to modify DrawnView (by drawing the GUI there), and
 * the ViewOutputs and ViewUserActions classes.  Do NOT modify this 
 * CompleteViewController class file though.
 */

/*
 * WARNING: There is nothing in this file for students to edit or change.
 *          This file is intended to have no student-serviceable code.
 */
package cs20viewcontroller;

/**
 * CompleteViewController collates a several classes that together form the view
 * and controller
 *
 * The following is technical information for those interested, but students are
 * not expected to grasp the following.
 *
 * There are two main parts of a view: (1) the GUI elements the user sees, and
 * (2) a way for the view to output data from the model. These two parts are
 * separated into, respectively, DrawnView and ViewOutputs.
 *
 * Because a view needs to have access to all model classes it displays,
 * DrawnView extends AllModelsForView as a convenient way to have access to the
 * model classes listed in AllModelsForView as though they were data members.
 * Further, since part (2) of a view depends on part (1), ViewOutputs extends
 * DrawnView, which also provides a convenient way to have access to the GUI
 * elements in the DrawnView as well as the model classes listed in
 * AllModelsForView as though they were data members.
 *
 * Finally, because the controller needs to have access to all model and view
 * classes it controls, ViewUserActions extends ViewOutputs, giving
 * ViewUserActions convenient access to ViewOutputs, GUI elements in the
 * DrawnView, as well as the model classes listed in AllModelsForView, as though
 * they were all just data members.
 * 
 * So the class hierarchy looks like this (arrow points to subclass):
 * 
 * AllModelsForView -> DrawnView -> ViewOutputs -> ViewUserActions -> CompleteViewController
 *
 * This design is not best practice, but this is hopefully a better compromise
 * between OO design and an easier learning curve for young students.
 * 
 * @author cheng
 */
public class CompleteViewController extends ViewUserActions {
    // intentionally empty
}
