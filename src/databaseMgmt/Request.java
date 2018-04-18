package databaseMgmt;

import java.io.IOException;

import alunoServer.Aluno;
import turmaServer.Turma;

public class Request {
	DatabaseMgmt data = new DatabaseMgmt();
	public String handleRequest(String requestStr) throws IOException {
		if(requestStr.equals("/alunos")) {
			return data.getAllAlunos();
			
		}else if(requestStr.startsWith("/incluiTurma")) {
			int idTurma = Integer.parseInt(requestStr.split("/")[2]);
			String nometurma = requestStr.split("/")[3];
			int response = data.adicionaTurma(new Turma(idTurma, nometurma));
			return new Response(response).toString();
			
		} else if(requestStr.startsWith("/incluiAluno")) {
			int idAluno = Integer.parseInt(requestStr.split("/")[2]);
			String nomeAluno = requestStr.split("/")[3];
			String turmasStr = requestStr.split("/")[4];
			String turmasArr[] = turmasStr.split(",");
			Aluno novoAluno = new Aluno(idAluno, nomeAluno);
			novoAluno.adicionaTurma(turmasArr);
			int response = data.adicionaAluno(novoAluno);
			return new Response(response).toString();
			
		} else if(requestStr.startsWith("/turma/")) {
			int idTurma = Integer.parseInt(requestStr.split("/")[2]);
			String response = data.buscaTurma(idTurma);
			return response;
		} else if(requestStr.startsWith("/aluno/")) {
			int idAluno = Integer.parseInt(requestStr.split("/")[2]);
			String response = data.buscaAluno(idAluno);
			return response;
		}
		
		return new Response(5).toString();
	}
}
