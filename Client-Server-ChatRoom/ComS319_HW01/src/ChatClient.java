
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	final static int PortNumber = 3000;
	
	public static void main(String[] args) throws IOException{
	
		Scanner UserInput = new Scanner(System.in); //This will read the users username and password
		
		Socket CSocket = new Socket("localhost", PortNumber);
		
		DataInputStream in = new DataInputStream(CSocket.getInputStream()); //Input from user
		DataOutputStream out = new DataOutputStream(CSocket.getOutputStream()); //Output to the user 
		
		Thread Write = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					String Data = UserInput.nextLine(); //Read the Messeage
					try {
						out.writeUTF(Data);		//Everything Goes to server
					}
					catch (IOException exception) {
						System.out.println("Client Side: Write");
					}
				}
			}
		});
		
		Thread Read = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						String Data = in.readUTF();	//Users Message
						System.out.println(Data);
					}
					// If that doesn't work, catch the error
					catch (IOException exception) {
						System.out.print("Client Side: Read");
					}
				}
			}
		});
		//Run Both threads
		Write.start();
		Read.start();
		
		
	}
	
	
	
	
}
