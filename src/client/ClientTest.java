package client;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {

	public static void main(String[] args) {
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 2221);

			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			Scanner in = new Scanner(clientSocket.getInputStream());

			out.println("/incluiTurma/4/Banco");
			out.flush();
			while(in.hasNext()) {
				System.out.println(in.nextLine());	
			}
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException  e) {
			e.printStackTrace();
		}

	}

}
