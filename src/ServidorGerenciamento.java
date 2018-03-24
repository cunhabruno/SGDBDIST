import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorGerenciamento {

	public static void main(String[] args) {
		Request request = new Request();
		try {
			ServerSocket socketGerenciamento = new ServerSocket(2221);

			while (true) {
				Socket clientSocket = socketGerenciamento.accept();
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				Socket alunosSocket = new Socket("localhost", 2222);

				PrintWriter outAlunos = new PrintWriter(alunosSocket.getOutputStream(), true);
				ObjectInputStream inAlunos = new ObjectInputStream(alunosSocket.getInputStream());
				
				System.out.println(outAlunos.checkError());
				out.flush();
				outAlunos.flush();
				outAlunos.print(in.readLine());
				System.out.println(inAlunos.readUTF());
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
