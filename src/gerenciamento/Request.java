package gerenciamento;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Request {
	public String handleRequest(String requestString) throws IOException, ClassNotFoundException {

		if (requestString.startsWith("/incluiAluno")) {
			Socket alunosSocket = new Socket("localhost", 2222);

			PrintWriter outAlunos = new PrintWriter(alunosSocket.getOutputStream(), true);
			Scanner inAlunos = new Scanner(alunosSocket.getInputStream());
			
			outAlunos.println(requestString);
			outAlunos.flush();
			
			String inAlunosStr = "";
			while(inAlunos.hasNext()) {
				inAlunosStr += inAlunos.nextLine() + '\n';	
			}
			
			outAlunos.close();
			inAlunos.close();
			alunosSocket.close();
			
			return inAlunosStr;
		}
		
		return "";
	}
}
