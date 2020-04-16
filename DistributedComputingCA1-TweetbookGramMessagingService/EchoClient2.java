import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 * @author M. L. Liu
 */
public class EchoClient2 {
   static final String endMessage = ".";
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
            portNum = "7";          // default port number

         System.out.println("Hostname is: " + hostName + "\nPort number of message server is: " + portNum);
		
         EchoClientHelper2 helper = 
            new EchoClientHelper2(hostName, portNum);
         boolean done = false;
         String message;
		 String echo;

         helper.login();
	
         while (!done) {

            System.out.println("\nEnter a line to receive a response "
               + "from the Message server, or a single period to quit.");
            message = br.readLine();
			
            if ((message.trim()).equals(endMessage)){
               done = true;
               helper.done();			   
            }
            else {
               echo = helper.getEcho(message);
               System.out.println("Message echoed back: " + echo);
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace();
      } //end catch
   } //end main
} // end class
