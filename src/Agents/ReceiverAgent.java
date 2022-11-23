package Agents;

import UI.ChatFrame;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;

/**
 *
 * @author Edson Martínez
 */

public class ReceiverAgent extends Agent{
    ChatFrame frame;
    
    @Override
    public void setup(){
        frame = new ChatFrame(getLocalName(), 1146);
        
        System.out.println("Hello! I'm " + getLocalName());
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMessage = receive();
                if(receivedMessage != null){
                    System.out.println("Bravo> Alfa says: "+ receivedMessage.getContent());
                    ACLMessage responseMessage = receivedMessage.createReply();
                    System.out.print("Bravo> To Alfa: ");
                    String responseText = new Scanner(System.in).nextLine();
                    if(responseText.equals("exit")){
                        System.out.println("Bravo> See you!\n---------------");
                        doDelete();
                        System.exit(0);
                    }

                    responseMessage.setContent(responseText);
                    send(responseMessage);
                }else{
                    block();
                }
            }
        });
    }
}
