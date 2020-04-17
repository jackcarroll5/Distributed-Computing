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
   //int code;
    EchoClient2 client2 = new EchoClient2();

   EchoServerThread(MyStreamSocket myDataSocket) {
      this.myDataSocket = myDataSocket;
   }
   
   public void run( ) {
      boolean done = false;
      String message;
      try {
         while (!done) {
             message = myDataSocket.receiveMessage();
/**/         System.out.println("\nProtocol Message received: " + message);

             if ((message.trim()).equals (endMessage)){
                 client2.messageArray.clear();
                //Session over; close the data socket.
/**/            System.out.println("Session over.");
                myDataSocket.close();
                done = true;
             } //end if
             else {
                // Now send the echo to the requestor
                myDataSocket.sendMessage(message);
                client2.messageArray.add(message);
                System.out.println("\nMessage List sent by client: " + client2.messageArray.toString() + "\n");
             } //end else
          } //end while !done
        }// end try
		
        catch (Exception ex) {
           System.out.println("Exception caught in thread: " + ex);
        } // end catch
   } //end run
} //end class 
