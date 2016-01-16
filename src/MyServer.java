import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
 * Creates socket server where only two sockets can connect.
 * The first one to connect can send data and the second can receive.
 * Will fix to doesn't matter who joins first.
 */
public class MyServer {
 
 public static void main(String[] args){
	 
	 final int port = 8880;
	 // Socket server
	 ServerSocket serverSocket = null;
	 
	 // Send data socket
	 Socket socket1 = null;
	 
	 // Receive data socket
	 Socket socket2 = null;
	 
	 // Input and output stream
	 DataInputStream dataInputStream1 = null;
	 DataOutputStream dataOutputStream1 = null;
	 
	 // Reads data
	 BufferedReader br1 = null;
	 
	 // Sends data
	 PrintWriter pw1 = null;
	 
	 // Strings used for reading input stream strings
	 String msg1 = null;
	 String msg2 = null;
  
  try {
	  // Make server with given port
	  serverSocket = new ServerSocket(port);
	  System.out.println("Listening :" + Integer.toString(port));
  } catch (IOException e) {
	  e.printStackTrace();
  }
  
  
  while(true){
	  try {
		  // If socket1 is empty then accept socket
		  if(socket1 == null)
		  {
			  socket1 = serverSocket.accept();
			  System.out.println("First ip to enter: " + socket1.getInetAddress());
		  }
		  // If socket2 is empty then accept socket
		  if(socket2 == null)
		  {
			  socket2 = serverSocket.accept();
			  System.out.println("Second ip to enter: " + socket2.getInetAddress());
		  }
	  } catch (IOException e) {
		    e.printStackTrace();
		   }
	  
   try {

	   // Get input stream from sender
	  // dataInputStream1 = new DataInputStream(socket1.getInputStream());
	   
	   // Get output stream from receiver
	 //  dataOutputStream1 = new DataOutputStream(socket2.getOutputStream());
	   
	   // Print info
	   System.out.println("ip of socket1: " + socket1.getInetAddress());
	   System.out.println("ip of socket2: " + socket2.getInetAddress());
    
	   // Get input stream from sender(socket1)
	   br1 = new BufferedReader(new InputStreamReader(socket1.getInputStream())); 
	   
	   // Set output stream to receiver(socket2)
	   pw1 = new PrintWriter(socket2.getOutputStream(), true);
	    
	   // Set msg1 to first string sent(name)
	   msg1 = br1.readLine();
	   System.out.println(msg1);
	    
	   // Set msg2 to second string sent(birth date)
	   msg2 = br1.readLine();
	   System.out.println(msg2);
	    
	   // Send name to receiver(socket2)
	   pw1.write(msg1 + "\n");
	   pw1.flush();
    
	   // Send birth date to receiver(socket2)
	   pw1.write(msg2 + "\n");
	   pw1.flush();
    
   } catch (IOException e) {
    e.printStackTrace();
   }
 
  }
  
 }
  
}