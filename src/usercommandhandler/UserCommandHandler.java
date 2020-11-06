/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usercommandhandler;

/**
 *
 * @author murta
 */
public class UserCommandHandler {

    userinterface.StandardIO myUI;
    client.Client user;

    public UserCommandHandler(userinterface.StandardIO ui, client.Client me) {
        myUI = ui;
        user = me;
    }

    public void execute(String command) {
        switch (Integer.parseInt(command)) {

            case 1:
                user.sendCmd((byte) 'q');
                user.disconnect();
                myUI.display("Quiting program by User command.");
                System.exit(-1);
                break;

            case 2:
                user.makeConnection();
                break;

            case 3:
                user.sendCmd((byte) 'd');
                user.disconnect();
                break;

            case 4:
                user.sendCmd((byte) 't');
                break;

            default:
                break;
        }
    }

}
