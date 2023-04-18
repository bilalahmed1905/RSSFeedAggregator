/*
 * The classes representing the model is organized into the cs20models package.
 * 
 * These are classes that represent (i.e. "models") the underlying data needed
 * for your program to run; as well, these classes model what can be done to
 * the data (e.g. an Elevator class may model an object with information of 
 * which floor the car is on, and contains methods for moving the car to 
 * another floor.  There may be other classes to help model other aspects of
 * the problem or situation too).
 * 
 * Model classes shouldn't know anything about the View or Controller classes.
 * It's a "pure" representation of the underlying problem or situation your
 * program solves or handles.
 * 
 * The Model classes are manipulated by the Controller classes.  Data stored
 * in Model classes are displayed whenever the View classes decides to do so.
 * So Model classes are passive (active Model classes would need some kind of
 * event or notification system, which you are not expected to know how to use).
 * 
 * The View and Controller classes are in the cs20viewcontroller package.
 * 
 * A sample model class is provided in the form of the DeepThoughtModel.
 * Go ahead and modify that class.
 * 
 * You can also make more classes in the cs20models package to better model
 * the problem or situation for your program.  But if you do, you will need
 * to list them below, inside the AllModelsForView class.  That's to inform
 * the View and Controller classes that these models are available to be used
 * by them.
 */
package cs20models;

/**
 * List out every model class you create here that will be directly used by your
 * GUI (i.e. the View and Controller classes) as instance variables.
 *
 * The list is in the form of a public instance variable assigned with the class
 * constructed as your GUI requires.
 *
 * HINT: Chances are, if you have a "container" class that contains a hundred
 * (or whatever amount) of other individual objects, you'll just need to list
 * the "container" class and not the individual objects.
 *
 * @author cheng
 */
public class AllModelsForView extends javax.swing.JFrame {

    public FeedMessage rss = new FeedMessage();
}
