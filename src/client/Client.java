package client;
import java.io.*;
import java.net.*;

/**
 *
 * @author Ngozi
 */
public class Client implements Runnable{
  
  Socket socket;
  InputStream in;
  OutputStream out;
  userinterface.StandardIO myUI;  
  servermessagehandler.ServerMessageHandler servMsg;
  int port;
  byte srmsg;
  byte srcmd;
  boolean isconnected = false;
  
  public Client(userinterface.StandardIO u){
    in = null;
    out = null;
    myUI= u;
    servMsg  = new servermessagehandler.ServerMessageHandler(this);
  }
  
  public void makeConnection(){
      
    if (!isconnected){
      connect("localhost",5555);
      Thread clithread = new Thread(this);
      clithread.start();
    }
    else
      sendUImsg("Client connected");
  }
  
  public void connect(String id, int port) {  
    try {
      InetAddress ad = InetAddress.getByName(id);
      socket = new Socket(ad, port);
      in = socket.getInputStream();
      out = socket.getOutputStream();
      isconnected = true;
      sendUImsg("Client connected!\n");
      
    } catch (Exception e) {
      System.err.println("Cannot connect to server");
    }
  }
  
  public byte getMsg(){
    byte msg = (byte)-1;
    try{
      msg = (byte)in.read();
    }
    catch(IOException i){
      System.err.println("Client disconnected");
    }
    finally{
      return msg;
    }
  }
  
  
  public void sendUImsg(String msg){
      myUI.display(msg);
  }
  
  public void sendCmd(byte c) {
    try {
      out.write(c);
    } 
    catch (IOException e) {System.err.println("Client disconnected");}
    catch(NullPointerException ne){}
  }
  
  public void disconnect() {
    try {
      isconnected = false;
      socket.close();
      this.in = null;
      this.out = null;
      
    } catch (Exception e) {
      System.err.println("Cannot close stream/client socket; exiting.");
      System.exit(1);
    }
  }
  
  @Override
  public void run() {
    while (true) {
      if (isconnected) {
        servMsg.execute();
      }
    }
  }
  
  public int getPort()
  {
    return port;
  }
  
  public void setPort(int newport)
  {
    port = newport;
  }
  
  
}
