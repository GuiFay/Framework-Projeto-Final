/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import View.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

/**
 *
 * @author Guilherme
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TelaInicial gui = new TelaInicial();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Framework DAS");
        gui.setVisible(true);
    }
}
