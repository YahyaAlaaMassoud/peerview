/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


public class ConnectionInitiator {

    private static ConnectionInitiator instance = null;
    private ServerSocket socket;
    private String serverPassword;
    private Boolean connected = false;
 
    private ConnectionInitiator(String serverPassword) {
        socket = ServerSocket.getInstance();
        this.serverPassword = serverPassword;
    }
    
    public static ConnectionInitiator getInstance (String serverPassword) {
        if (instance == null) {
            instance = new ConnectionInitiator(serverPassword);
        }
        return instance;
    }
    
    public static ConnectionInitiator getInstance () {
        return instance;
    }
    

    public void initiateConnection() {
        Authenticator auth = new Authenticator(socket, serverPassword);
        System.out.print("ServerPassword" + serverPassword);
        while(!auth.isValid()) {
            socket.sendMessage("invalid");
        }   
        // auth valid
        socket.sendMessage("valid");
        socket.sendMessage(getWidth());
        socket.sendMessage(getHeight());
        connected = true;
    }
    
    String getWidth() {
        return "4";
    }
    String getHeight() {
        return "5";
    }
    
    public Boolean isConnected () {
        return connected;
    }
    
}