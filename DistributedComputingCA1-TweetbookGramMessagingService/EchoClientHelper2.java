import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 * @author M. L. Liu
 */

public class EchoClientHelper2 {

   static final String endMessage = ".";
   private MyStreamSocket mySocket;
   private InetAddress serverHost;
   private int serverPort;
   InputStreamReader is = new InputStreamReader(System.in);
   BufferedReader br = new BufferedReader(is);
   public String message;

   EchoClientHelper2(String hostName,
                     String portNum) throws SocketException,
                     UnknownHostException, IOException {
  	   this.serverHost = InetAddress.getByName(hostName);
  		this.serverPort = Integer.parseInt(portNum);
      //Instantiates a stream-mode socket and wait for a connection.
   	this.mySocket = new MyStreamSocket(this.serverHost,
         this.serverPort); 
/**/  System.out.println("Connection request made");
   } // end constructor

	/*Create Protocol Messages for 4 methods - Code for 4 types of messages based 
	on this method*/
   public String getEcho(String message) throws SocketException,
      IOException{     
      String echo = "";    
      mySocket.sendMessage(message);
	   // now receive the echo
      echo = mySocket.receiveMessage();
      return echo;
   } // end getEcho
   
   public String login() throws SocketException,
      IOException{

      String userNameInput;
      String pword;
      String username = "JACK";
      String password = "password";
      String clientEcho;

      System.out.println("\nPlease enter your username:");
      userNameInput = br.readLine();

      System.out.println("Please enter your password:");
      pword = br.readLine();

      if(userNameInput.equals(username) && pword.equals(password))
      {
       System.out.println("Login successful! Welcome to Tweetbookgram");
       getEcho("Login successful! 100 LOGIN " + userNameInput + " " + pword + " 200 OK");
      }
      else
      {
         System.out.println("Login was unsuccessful. No username or password was entered.");
         clientEcho = getEcho("201 ERROR! Login to Tweetbookgram failed.");
         System.out.println("Message returned to client: " + clientEcho);
         System.out.println("\nMessaging system shut down due to invalid username / password \nPlease try again and restart this client application!");
         System.exit(0);
      }
      return "";
   }
   
   public String logout(String message) throws SocketException,
      IOException{     
      String echo = "";    
      mySocket.sendMessage(message);
	   // now receive the echo
      echo = mySocket.receiveMessage();
      return echo;
   } 
   
   public String upload(String message) throws SocketException,
      IOException{     
      String echo = "";    
      mySocket.sendMessage(message);
	   // now receive the echo
      echo = mySocket.receiveMessage();
      return echo;
   } 
   
   public String download(String message) throws SocketException,
      IOException{     
      String echo = "";    
      mySocket.sendMessage(message);
	   // now receive the echo
      echo = mySocket.receiveMessage();
      return echo;
   } 

   public void done() throws SocketException,
                              IOException{
      mySocket.sendMessage(endMessage);
      mySocket.close();
   } // end done 
} //end class
