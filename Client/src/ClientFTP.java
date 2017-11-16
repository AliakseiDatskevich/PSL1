import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner; 

public class ClientFTP { 

private static Scanner scan, scan1; 

public static void main(String[] args) throws UnknownHostException, IOException { 
System.out.println("Program clienta"); 
Socket fromserver = null; 
System.out.println("Prosze wpisac port: "); 
scan = new Scanner(System.in); 
int port = scan.nextInt(); 
fromserver = new Socket("localhost",port); 
BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream())); 
PrintWriter out = new PrintWriter(fromserver.getOutputStream(),true); 
System.out.println("Wpisz co chcesz otrzymac od serwera: w konsoli klienta\n" 
+ "LIST - wyswitlic liste plikow katalogu serwera\n" 
+ "GET - pobrac plik o nazwie z serwera i zapisac w katalogu klienta\n" 
+ "SHOW - wyswietlic zawartosc pliku w katalogu serwera w konsoli klienta\n" 
+ "QUIT - zamyka klienta\n" 
+ "SHUTDOWN - zamyka serwer\n"); 
BufferedReader inu = new BufferedReader(new InputStreamReader(System.in)); 
String fuser,fserver; 
while ((fuser = inu.readLine())!=null) { 
out.println(fuser); 
fserver = in.readLine(); 
if (fserver.equalsIgnoreCase("Wpisz nazwe pliku:")) { 
String name = inu.readLine(); 
out.println("PLIK " + name); 

} 
System.out.println(fserver); 
if (fuser.equalsIgnoreCase("quit")) { 
break; 
} 
} 
out.close(); 
in.close(); 
inu.close(); 
fromserver.close(); 
} 
}