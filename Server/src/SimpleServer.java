import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleServer {

	public static void main(String[] args) throws IOException {
		System.out.println("Program server:\n");
		ServerSocket servers = null;
		Socket fromclient = null;
		BufferedReader in;
		PrintWriter    out;
		
		try {
			servers = new ServerSocket(7);
		} 
		catch (IOException e) {
			System.out.println("Couldn't listen to port 7");
			System.exit(-1);
		}
		
		try {
			System.out.print("Waiting for a client...\n");
			fromclient= servers.accept();
			System.out.println("Client connected\n");
		}
		catch (IOException e) {
			System.out.println("Can't accept");
			System.exit(-1);
		}
	 
	
		in = new BufferedReader(new InputStreamReader(fromclient.getInputStream())); 
		out = new PrintWriter(fromclient.getOutputStream(),true);
		 
		String input;
	
		System.out.println("Wait for messages");
		while ((input = in.readLine()) != null) {
			if(input.equalsIgnoreCase("LIST")) {
				File f = new File("C:\\Users\\ealidat\\Downloads\\");
				ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
				out.println("Folder conatin: " + names);
				System.out.println(names);
			}
//			else if(input.equalsIgnoreCase("GET")) {
//				 
//			}
//			else if(input.equalsIgnoreCase("SHOW")) {	
//				
//			}
//			else if(input.equalsIgnoreCase("QUIT")) {
//				
//			}
//			else if(input.equalsIgnoreCase("SHOTDOWN")) {	
//				
//			}
//			else {	 
//			}
			out.close();
			in.close();
			fromclient.close();
			servers.close();
		}
	}
}
