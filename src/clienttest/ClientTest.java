/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttest;

//import java.io.*;
//import java.net.*;

/**
 *
 * @author Ngozi
 */
public class ClientTest {

    public static void main(String[] args) {
   
        userinterface.StandardIO myUI = new userinterface.StandardIO();
        client.Client me = new client.Client(myUI);
        usercommandhandler.UserCommandHandler myUserCommandHandler = new usercommandhandler.UserCommandHandler(myUI, me);
        myUI.setCommandHandler(myUserCommandHandler);
        Thread myUIthread = new Thread(myUI);
        myUIthread.start();   
        myUI.display("1:\tQuit\n"
                   + "2:\tConnect\n"
                   + "3:\tDisconnect\n"
                   + "4:\tTime\n");
                
}
    
}
