import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
PrintWriter out; 

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
DataOutputStream dout; 

System.out.println("Wait for messages"); 
while ((input = in.readLine()) != null) { 
if(input.equalsIgnoreCase("list")) { 
File f = new File("D:\\Git\\"); 
ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list())); 
out.println("Folder conatin: " + names); 
} 

else if (input.equalsIgnoreCase("show")) { 
out.println("Wpisz nazwe pliku:"); 
} 
else if (input.startsWith("PLIK") == true) { 
System.out.println(input); 
String filename = input.substring(5); 
System.out.println(filename); 
// File f=new File("D:\\Git\\" + filename); 
InputStream input1 = new BufferedInputStream(new FileInputStream("D:\\Git\\" + filename)); 
byte[] buffer = new byte[8192]; 

try { 
for (int length = 0; (length = input1.read(buffer)) != -1;) { 
System.out.write(buffer, 0, length); 
} 
} finally { 
input1.close(); 
} 
// FileOutputStream fout=new FileOutputStream(f); 
// int ch; 
// String temp; 
// if(f.exists()) 
// { 
// out.println("File Already Exists"); 
// } 
// else 
// { 
// out.println("SendFile"); 
// } 
// do 
// { 
// temp=din.readUTF(); 
// ch=Integer.parseInt(temp); 
// if(ch!=-1) 
// { 
// fout.write(ch); 
// } 
// }while(ch!=-1); 
// fout.close(); 
// dout.writeUTF("File Send Successfully"); 
} 
else if (input.equalsIgnoreCase("shutdown")) break; 
// out.println("Dane wyslane klientem: "+input); 
// System.out.println(input); 
} 
out.close(); 
in.close(); 
fromclient.close(); 
servers.close(); 
} 
}