package project1;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class DeffieHellmanRcv {
    private static String message;

 DatagramSocket theSocket = null;

 int serverPort = 9099;
 Scanner sc;
static int x1,n,q,y1,a;

 DatagramPacket theRecievedPacket;
 DatagramPacket theSendPacket;
 InetAddress clientAddress;
 int clientPort;
 byte[] outBuffer;
 byte[] inBuffer;

 public DeffieHellmanRcv()
 {
  try {
   // create the server UDP end point
   theSocket = new DatagramSocket(serverPort);

   System.out.println("Big Data owner");
  } catch (SocketException ExceSocket)
  {
   System.out.println("Socket creation error : "+ 
ExceSocket.getMessage());
  }
 }

 public void keyGen()
 {

  sc=new Scanner(System.in);

  System.out.print("Enter n=");
  n=sc.nextInt();

  System.out.print("Enter q=");
  q=sc.nextInt();


  System.out.print("Enter x1=");
  x1=sc.nextInt();

  y1= (int) ((Math.pow(n,x1)) % q);
 System.out.println("computed key of Big data owner:"+y1);
  
 }

 public void send()
 {
  try
  {
    message =y1+"";
  
   
   outBuffer=message.getBytes();

 
   // send some data to the client
   theSendPacket = new DatagramPacket(outBuffer, outBuffer.length, 
clientAddress, clientPort);
   theSocket.send(theSendPacket);
  }

  catch (Exception e)
  {
   System.out.println("Error with client request : "+e);
  }
  // close the server socket


 }

 public void receive()
 {


  // create some space for the text to send and recieve data
  outBuffer = new byte[500];
  inBuffer = new byte[50];

  try
  {
   // create a place for the client to send data too
   theRecievedPacket = new DatagramPacket(inBuffer, inBuffer.length);

   // wait for a client to request a connection
   theSocket.receive(theRecievedPacket);
 

   // get the client details
   clientAddress = theRecievedPacket.getAddress();
   clientPort = theRecievedPacket.getPort();



    message = new String(theRecievedPacket.getData
(),0,theRecievedPacket.getLength());


   String sendData = message+"";
   System.out.println("Computed key of requested user: "+message);
    a=Integer.parseInt(message);
  
   outBuffer = sendData.getBytes();

  }
  catch(IOException | NumberFormatException e)
  {
   System.out.println("E2"+e);
  }

 }

    
   
}
