import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

public class AlunoServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(2222);

			DatabaseMgmt data = new DatabaseMgmt();
			while (true) {
				Socket clientSocketGerenciamento = server.accept();
				ObjectOutputStream out = new ObjectOutputStream(clientSocketGerenciamento.getOutputStream());
				//ObjectInputStream in = new ObjectInputStream(clientSocketGerenciamento.getInputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocketGerenciamento.getInputStream()));
				System.out.println("Conexao requisitada! ");
				
				System.out.println(in.readLine().length());
				
				out.writeObject(data.getAllAlunos());
				
				in.close();
				out.close();
				clientSocketGerenciamento.close();
			}

		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
