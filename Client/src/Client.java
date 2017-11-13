import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private static Scanner scan;

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Program clienta");
		Socket fromserver = null;
		System.out.println("Prosze wpisac port: ");
		scan = new Scanner(System.in);
		int port = scan.nextInt();
		fromserver = new Socket("localhost",port);
		BufferedReader in  = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
	    PrintWriter    out = new PrintWriter(fromserver.getOutputStream(),true);
	    System.out.println("Wpisz dane do przeslania serverowi: ");
	    BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
	    String fuser,fserver;
	    while ((fuser = inu.readLine())!=null) {
	      out.println(fuser);
	      fserver = in.readLine();
	      System.out.println(fserver);
	      if (fuser.equalsIgnoreCase("close")) break;
	      if (fuser.equalsIgnoreCase("exit")) break;
	    }

	    out.close();
	    in.close();
	    inu.close();
	    fromserver.close();
	  }

}
