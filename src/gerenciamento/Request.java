package gerenciamento;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Request {
	Socket alunosSocket;
	Socket turmasSocket;

	public void handleRequest(ObjectOutputStream out, BufferedReader in) throws IOException, ClassNotFoundException {
		String requestString = in.readLine();

		System.out.println(requestString);
		if (requestString.equals("/alunos")) {
			alunosSocket = new Socket("localhost", 2222);

			PrintWriter outAlunos = new PrintWriter(alunosSocket.getOutputStream(), true);
			BufferedReader inAlunos = new BufferedReader(new InputStreamReader(alunosSocket.getInputStream()));
			
			outAlunos.print(requestString);
			System.out.println(inAlunos.readLine());
			outAlunos.close();
			inAlunos.close();
			alunosSocket.close();
		}
	}
}
