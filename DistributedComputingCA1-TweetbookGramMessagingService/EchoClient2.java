import java.io.*;
import java.util.ArrayList;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient2 {
   static final String endMessage = ".";
   static final String logOutSymbol = "#";
   public static ArrayList<String> messageArray = new ArrayList<String>();
   public static void main(String[] args) {
	   
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);

	  
      try {
         System.out.println("Welcome to the Tweetbookgram Messaging Service Client.\n" +
            "What is the name of the Messaging Server host?");
			
         String hostName = br.readLine();
         if (hostName.length() == 0) // if user did not enter a name
            hostName = "localhost";  //   use the default host name
			
         System.out.println("What is the port number of the Message server host?");
         String portNum = br.readLine();

         if (portNum.length() == 0)
            portNum = "8";          // default port number

         System.out.println("Hostname is: " + hostName + "\nPort number of message server is: " + portNum);
		
         EchoClientHelper2 helper = 
            new EchoClientHelper2(hostName, portNum);
         boolean done = false;

         String echo;
         String message;

         helper.login();
	
         while (!done) {

            System.out.println("Enter a line to receive a response "
               + "from the Message server, or a single period to quit.");
            message = br.readLine();
			
            if ((message.trim()).equals(endMessage)){
               done = true;
               helper.done();
            }
            else if(message.equals(logOutSymbol))
            {
               System.out.println("\n");
               helper.logout();
            }
            else {
               echo = helper.upload(message);
               System.out.println("Message echoed back: " + echo);
               messageArray.add(echo);
               System.out.println("Messages sent to server: " + messageArray.toString());
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace();
      } //end catch
   } //end main
} // end class
