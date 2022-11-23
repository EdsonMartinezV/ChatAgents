/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Edson Martínez
 */
public class ChatFrame extends JFrame{
    Font redHat = new Font("Red Hat Display", Font.BOLD, 14);
    JButton button = new JButton();
    
    public ChatFrame(String title, int locationX){
        setBounds(locationX, 290, 400, 500);
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        initComponents();
    }
    
    private void initComponents(){
        JPanel panel = new JPanel();
        JLabel contactLabel = new JLabel();
        String contactName = (this.getTitle().equals("Bravo")) ? "Alfa" : "Bravo";
        
        contactLabel.setText(contactName);
        contactLabel.setFont(redHat);
        contactLabel.setBounds(10, 10, 100, 100);
        
        this.getContentPane().add(panel);
        panel.add(contactLabel);
    }
}
