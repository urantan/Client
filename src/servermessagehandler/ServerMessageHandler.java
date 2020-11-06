/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermessagehandler;

/**
 *
 * @author Ngozi
 */
public class ServerMessageHandler {
    
    client.Client user;
    
    public ServerMessageHandler(client.Client c){
   
    user = c;
    }
    
    
    public void execute(){
        
                boolean get = true;
                String result = "";
                while (result.length() < 8 ) {
                    byte res = user.getMsg();
                    if (res == (byte)-1)
                    {
                        result = "";
                        break;
                    }
                    result += (char) res;
                }
                
                if (!result.equals(""))
                   user.sendUImsg("Time is "+result);
    }
    
}
