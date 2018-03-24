import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest {

	public static void main(String[] args) {
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 2221);

			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			//BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

			out.println("/alunos");
			out.flush();
			System.out.println(in.readObject());
		       in.close();
		        out.close();
		        clientSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
