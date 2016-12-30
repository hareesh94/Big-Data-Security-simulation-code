package project1;

import java.util.*;
import java.io.*;
import java.net.*;


public class DeffieHellmanSend
{
    private static String message;
 DatagramSocket theSocket = null;
 int serverPort = 9099;
 Scanner sc;
static int x2,n,q,y2,b;
 DatagramPacket theSendPacket;
 DatagramPacket theReceivedPacket;
 InetAddress theServerAddress;
 byte[] outBuffer;
 byte[] inBuffer;

 public DeffieHellmanSend()
 {

  try
  {
   theSocket = new DatagramSocket();

   // but if you want to connect to your remote server, then alter the 
//theServer address below
   InetAddress theServer = InetAddress.getLocalHost();
   theSocket.connect(theServer,serverPort);

   System.out.println("Requested user");
  }
  catch (SocketException ExceSocket)
  {
   System.out.println("Socket creation error:"
+ExceSocket.getMessage());
  }
  catch (UnknownHostException ExceHost)
  {
   System.out.println("Socket host unknown : "+ExceHost.getMessage());
  }
 }

 public void keyGen()
 {
  sc=new Scanner(System.in);

  System.out.print("Enter n=");
  n=sc.nextInt();

  System.out.print("Enter q=");
  q=sc.nextInt();


  System.out.print("Enter x2=");
  x2=sc.nextInt();

  y2= (int) ((Math.pow(n,x2)) % q);
  System.out.println("Computed key of requested user "+y2);
 
 }

 public void send()
 {
  // the place to store the sending and receiving data
  inBuffer = new byte[500];
  outBuffer = new byte[50];

  try
  {
    message=y2+"";
  // System.out.println("private key:"+message);
   outBuffer = message.getBytes();
   // the server details
   theServerAddress = theSocket.getLocalAddress();

   // build up a packet to send to the server
   theSendPacket = new DatagramPacket(outBuffer, outBuffer.length, 
theServerAddress, serverPort);
   // send the data
   theSocket.send(theSendPacket);


  }
  catch (IOException ExceIO)
  {
   System.out.println("Client getting data error : "+ExceIO.getMessage
());
  }

 }


 public void receive()
 {
  try
  {
   // get the servers response within this packet
   theReceivedPacket = new DatagramPacket(inBuffer, inBuffer.length);
   theSocket.receive(theReceivedPacket);

   // the server response is...

    message = new String(theReceivedPacket.getData
(),0,theReceivedPacket.getLength());

   System.out.println("Computed key of Big data owner : "+message);
b=Integer.parseInt(message);
  
  }
  catch(IOException | NumberFormatException e)
  {
   System.out.println("E1"+e);
  }


 }

 
}
