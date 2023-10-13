package ProjectPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientHandler(Socket ClientSocket) throws IOException{
		this.client = ClientSocket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
		out.flush();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true) {
				//Whatever command the client sends read in variable called request
				String request = in.readLine();
				if(request.contains("name"))
				{
					out.println( Server.getRandomName() );
				}
				else
				{
					out.println("type 'tell me a name' to get a random name");
				}
			}
			
		}catch(IOException e) {
			System.err.println("IO exception in client handler");
			System.err.println(e.getStackTrace());
		}finally {
		out.close();
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
