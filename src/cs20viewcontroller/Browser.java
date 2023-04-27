package cs20viewcontroller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bilalahmed
 */
public class Browser {

    public static void createWindow() {
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame) {
//        JPanel panel = new JPanel();
//        LayoutManager layout = new FlowLayout();
//        panel.setLayout(layout);
//
//        JEditorPane jEditorPane = new JEditorPane();
//        jEditorPane.setEditable(false);
//        try {
//            jEditorPane.setPage("http://www.google.com");
//        } catch (IOException e) {
//            jEditorPane.setContentType("text/html");
//            jEditorPane.setText("<html>Page not found.</html>");
//        }
//
//        JScrollPane jScrollPane = new JScrollPane(jEditorPane);
//        jScrollPane.setPreferredSize(new Dimension(540, 400));
//
//        panel.add(jScrollPane);
//        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
}
