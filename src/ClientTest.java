import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {

	public static void main(String[] args) {
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 2221);

			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(clientSocket.getInputStream()));
			Scanner in = new Scanner(clientSocket.getInputStream());

			out.println("/alunos");
			out.flush();
			System.out.println(in.nextLine());
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
