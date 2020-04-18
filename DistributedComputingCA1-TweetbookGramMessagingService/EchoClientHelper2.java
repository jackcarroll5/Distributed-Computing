import java.net.*;
import java.io.*;
import java.util.ArrayList;

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
       System.out.println("\nLogin successful! Welcome to Tweetbookgram");
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
   
   public String logout() throws SocketException,
      IOException{

      String choice;
      boolean done = false;

      System.out.print("\nAre you sure you want to quit the messaging service?: ");
      choice = br.readLine();

      if(choice.equals("yes"))
      {
         System.out.println("The user chose " + choice + "!\nUser has logged out of Tweetbookgram! See you again soon!");
         getEcho("103 LOGOUT 200 OK");
         done = true;
         done();
      }
      else if(choice.equals("no")){
         System.out.println("The user has not logged out!");
         return getEcho("203 NOLOGOUT");
      }
      return getEcho("103 LOGOUT 200 OK");
   } 
   
   public String upload(String message) throws SocketException,
      IOException{

      ArrayList<String> messageArray = new ArrayList<String>();

      if(message.length() > 0)
      {
         messageArray.add(message);
         System.out.println("\nThe message \n" + messageArray.toString() + " \nwritten by the user has been uploaded to the Server.");
         getEcho("101 UPLOAD " + messageArray.toString() + " 200 OK");
      }
      else
      {
         System.out.println("Upload failed! The message was null / empty! Please input a non-empty message!");
         getEcho("202 ERROR Uploading of message was unsuccessful!");
      }
      return messageArray.toString();
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
