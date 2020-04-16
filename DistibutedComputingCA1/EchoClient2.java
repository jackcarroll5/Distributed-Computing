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
	  public String userNameInput;
	  public String pword;
	  
      try {
         System.out.println("Welcome to the Messaging Service Client.\n" +
            "What is the name of the Messaging Server host?");
			
         String hostName = br.readLine();
         if (hostName.length() == 0) // if user did not enter a name
            hostName = "localhost";  //   use the default host name
			
         System.out.println("What is the port number of the Message server host?");
		 
         String portNum = br.readLine();
         if (portNum.length() == 0)
            portNum = "7";          // default port number
		
         EchoClientHelper2 helper = 
            new EchoClientHelper2(hostName, portNum);
         boolean done = false;
         String message,loggingIn;
		 //String echo;
	
         while (!done) {
			 
			System.out.println("\nPlease enter your username:");
		    userNameInput = br.readLine();
			
			System.out.println("Please enter your password:");
		    pword = br.readLine();
			 
            System.out.println("\nClient has successfully been connected to the server.\n" +
			"Client hostname is: " + hostName + "\nPort is: " + portNum +  "\nEnter a line to receive a response "
               + "from the Message server, or a single period to quit.");
            message = br.readLine();
			
            if ((message.trim()).equals(endMessage)){
               done = true;
               helper.done();			   
            }
            else {
               //echo = helper.getEcho( );
			   loggingIn = helper.login(message);
			   System.out.println("Message echoed back: " + loggingIn);
               //System.out.println(echo);
            }
          } // end while
      } // end try  
      catch (Exception ex) {
         ex.printStackTrace();
      } //end catch
   } //end main
} // end class
