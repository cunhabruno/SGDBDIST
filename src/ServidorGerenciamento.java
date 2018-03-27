import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorGerenciamento {

	public static void main(String[] args) {
		Request request = new Request();
		try {
			ServerSocket socketGerenciamento = new ServerSocket(2221);

			while (true) {
				Socket clientSocket = socketGerenciamento.accept();
				
				PrintWriter out = new PrintWriter (clientSocket.getOutputStream());
				Scanner in = new Scanner(new InputStreamReader(clientSocket.getInputStream()));
				
				Socket alunosSocket = new Socket("localhost", 2222);

				PrintWriter outAlunos = new PrintWriter(alunosSocket.getOutputStream(), true);
				Scanner inAlunos = new Scanner(alunosSocket.getInputStream());
				
				String inClient = in.nextLine();
				System.out.println("in do client: " + inClient);
				outAlunos.println(inClient);
				outAlunos.flush();
				
				out.println("Test 2");
				out.flush();
				String inAlunosStr = "";
				while(inAlunos.hasNext()) {
					System.out.println(inAlunos.nextLine());	
				}
				outAlunos.close();
				inAlunos.close();
				alunosSocket.close();
				
				in.close();
				out.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
