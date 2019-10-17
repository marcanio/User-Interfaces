
/**
 * Author: Eric Marcanio
 * This is a local server that is running a local chat application
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ChatServer {
	static Vector<ClientThread> list = new Vector<>(); //List of Clients
	public static String UserName;
	public static String Password = "Password";


	private static final int portNumber = 3000;

	public static void main(String[] args) throws IOException {

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 	
			
			System.out.println("Binding to port " + serverSocket + " Please wait...");
			
			while (true) { 		// Wait for a client

				Socket CSocket = serverSocket.accept(); // Blocking Call- Waits for client
				System.out.println("A new client is connected");

				DataInputStream In = new DataInputStream(CSocket.getInputStream()); 	//Get data from client
				DataOutputStream Out = new DataOutputStream(CSocket.getOutputStream()); //Send data to client
				
				Out.writeUTF(">Enter a Name: ");			//Ask the user to import a username
				UserName = In.readUTF();				//Store username into "UserName"
				
				String UserPassword = "";
				
				while(!UserPassword.equals(Password)) { //Loop until password is incorrect
					Out.writeUTF(">Enter a AccessCode: ");	//Ask the user to import a username
					UserPassword = In.readUTF();
					if(!UserPassword.equals(Password)) {
						Out.writeUTF("incorrect access code");
					}
					//System.out.println(UserPassword);
				}
				System.out.println(UserName+ " Has been added to the Server");
				
				//Promt the user that there Password was correct
				Out.writeUTF("Thanks " + UserName + " you have been added to socket: " + CSocket );
				
				ClientThread Clients = new ClientThread(UserName, In, Out);
				
				Thread Nextthread = new Thread(Clients);
				
				list.add(Clients);
				Nextthread.start();

			}

		} catch (IOException e) {
			System.out.println("Could not listen on port: 3000");
			System.exit(-1);
		}

	}

}
