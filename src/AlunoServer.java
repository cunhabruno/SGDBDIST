import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class AlunoServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(2222);

			DatabaseMgmt data = new DatabaseMgmt();
			while (true) {
				Socket clientSocketGerenciamento = server.accept();
				PrintWriter out = new PrintWriter(clientSocketGerenciamento.getOutputStream());
				Scanner in = new Scanner(new InputStreamReader(clientSocketGerenciamento.getInputStream()));
				System.out.println("Conexao requisitada! ");
				
				System.out.println(in.nextLine());
				
				out.println(data.getAllAlunos());
				out.flush();
				
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
