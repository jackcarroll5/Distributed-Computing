import java.io.*;
import java.util.Vector;
/**
 * This module is to be used with a concurrent Echo server.
 * Its run method carries out the logic of a client session.
 * @author M. L. Liu
 */

class EchoServerThread implements Runnable {
   static final String endMessage = ".";
   MyStreamSocket myDataSocket;
   int code;

   EchoServerThread(MyStreamSocket myDataSocket) {
      this.myDataSocket = myDataSocket;
   }
   
    // get code number from message
/*if(code == 100){ //Login
	// take the username from the message an put it in a vector/array (Server)
	Vector<String> usernameVector = new Vector<>();
	String username = " "
	
	usernameVector.add(username);
	
	return "200 OK";
	// return an OK message to the client e.g. 200 OK
}

     if(code == 101){ //Upload
	//take the message and store it in a different vector/array (Server)
	String message = " ";
	Vector<String> messageVector = new Vector<>();
	
	messageVector.add(message);
	
	return "200 OK";
	//Return 200 OK to client
	 }
	 
if(code == 102){ //Download
	// get a message from vector (Server) and return it to the client
	messageVector.get(message);
	
	
	return "200 OK";
}


if(code == 103){
	// delete username from first vector (Server)
	usernameVector.remove(username);
	
	
	return "200 OK";
	// return 200 OK
}*/
   
   public void run( ) {
      boolean done = false;
      String message;
      try {
         while (!done) {
             message = myDataSocket.receiveMessage();
/**/         System.out.println("Message received: " + message);

             if ((message.trim()).equals (endMessage)){
                //Session over; close the data socket.
/**/            System.out.println("Session over.");
                myDataSocket.close();
                done = true;
             } //end if
             else {
                // Now send the echo to the requestor
                myDataSocket.sendMessage(message);
             } //end else
          } //end while !done
        }// end try
		
        catch (Exception ex) {
           System.out.println("Exception caught in thread: " + ex);
        } // end catch
   } //end run
} //end class 
