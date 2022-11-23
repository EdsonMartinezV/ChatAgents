package Agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Edson Martínez
 */

public class TransmitterAgent extends Agent{
    ACLMessage message;
    Scanner scanner = new Scanner(System.in);
    String messageText;
    
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel contactLabel = new JLabel();
    String contactName = "Bravo";
    JTextArea chatTextArea = new JTextArea();
    JTextField newMessageTextField = new JTextField();
    JButton sendButton = new JButton("Enviar");
    JScrollPane chatPane = new JScrollPane(chatTextArea);

    Font redHat = new Font("Red Hat Display", 0, 18);
    Font redHatChat = new Font("Red Hat Display", 0, 12);
    Color darkBlue = new Color(9, 28, 49);
    Color gray = new Color(200, 200, 210);
    Color darkGray = new Color(41, 49, 69);
    
    @Override
    public void setup(){
        initUI();
        message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID(("Bravo"),AID.ISLOCALNAME));
        
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMessage = receive();
                if(receivedMessage != null){
                    printReceivedMessage(receivedMessage.getContent());
                    //ACLMessage responseMessage = receivedMessage.createReply();
                }else{
                    block();
                }
            }
        });
    }
    
    public void initUI(){
        frame.setBounds(373, 290, 400, 500);
        frame.setTitle(getLocalName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        initComponents();
        initListeners();
    }
    
    private void initComponents(){        
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        contactLabel.setText("  " + contactName);
        contactLabel.setFont(redHat);
        contactLabel.setOpaque(true);
        contactLabel.setBackground(darkGray);
        contactLabel.setForeground(gray);
        contactLabel.setBounds(0, 0, 400, 50);
        
        chatPane.setBounds(0, 50, 400, 441);
        chatPane.setFont(redHatChat);
        chatPane.setBackground(gray);
        chatPane.setForeground(darkGray);
        
        chatTextArea.setBounds(0, 50, 400, 441);
        chatTextArea.setFont(redHatChat);
        chatTextArea.setBackground(gray);
        chatTextArea.setForeground(darkGray);
        
        newMessageTextField.setText("Escribe aqui");
        newMessageTextField.setFont(redHat);
        newMessageTextField.setBounds(0, 442, 300, 30);
        
        sendButton.setBounds(300, 442, 100, 30);
        sendButton.setBackground(darkGray);
        sendButton.setForeground(gray);
        sendButton.setFont(redHat);
        
        panel.add(contactLabel);
        panel.add(newMessageTextField);
        panel.add(sendButton);
        panel.add(chatPane);
    }
    
    private void initListeners(){
        sendButton.addActionListener((ActionEvent e) -> {
            if(chatTextArea.getText().equals("")){
                chatTextArea.setText("Tu: " + getMessage());
            }else{
                chatTextArea.setText(chatTextArea.getText() + "\nTu: " + getMessage());
            }
            message.setContent(getMessage());
            send(message);
        });
    }
    
    public String getMessage(){
        return newMessageTextField.getText();
    }
    
    public void printReceivedMessage(String message){
        if(chatTextArea.getText().equals("")){
            chatTextArea.setText(contactName + ": " + message);
        }else{
            chatTextArea.setText(chatTextArea.getText() + "\n" + contactName + ": " + message);
        }
    }
}
