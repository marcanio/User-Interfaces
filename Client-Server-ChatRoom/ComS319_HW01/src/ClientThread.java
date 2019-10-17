import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class ClientThread implements Runnable{

	private String UserName; //Stores local UserName
	final DataInputStream in; //Users Messeage
	final DataOutputStream out;
	String Message;

	public ClientThread(String UserName, DataInputStream input, DataOutputStream output) {
		this.in = input;
		this.out = output;
		this.UserName = UserName;
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				Message = in.readUTF();	//Users messeage
				System.out.println(UserName + ": " + Message); //Print everyones messeage to the server
				
				for(ClientThread client : ChatServer.list) {
					if(client.UserName.equals(this.UserName)) {
						//System.out.println("Sent");
					}
					else {
						client.out.writeUTF(this.UserName + ": " + Message);
					}
				}
			}catch(IOException excep) {
				System.out.print("Client Thread");
			}
		}
		
	}
	
	
	
	
}