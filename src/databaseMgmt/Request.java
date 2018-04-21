package databaseMgmt;

import java.io.IOException;

import alunoServer.Aluno;
import turmaServer.Turma;

public class Request {
	DatabaseMgmt data = new DatabaseMgmt();
	public String handleRequest(String requestStr) throws IOException {
		if(requestStr.equals("/alunos")) {
			return data.getAllAlunos();
		} else if(requestStr.equals("/turmas")) {
			return data.getAllTurmas();
		} else if(requestStr.matches("\\/incluiTurma\\/[0-9]\\/[a-zA-Z0-9\\s]+$")) {
			int idTurma = Integer.parseInt(requestStr.split("/")[2]);
			String nometurma = requestStr.split("/")[3];
			int response = data.adicionaTurma(new Turma(idTurma, nometurma));
			return new Response(response).toString();
			
		} else if(requestStr.matches("\\/incluiAluno\\/[0-9]\\/[a-zA-Z0-9\\s]+\\/[0-9]+(\\,*[0-9])*$")) {
			int idAluno = Integer.parseInt(requestStr.split("/")[2]);
			String nomeAluno = requestStr.split("/")[3];
			String turmasStr = requestStr.split("/")[4];
			String turmasArr[] = turmasStr.split(",");
			Aluno novoAluno = new Aluno(idAluno, nomeAluno);
			novoAluno.adicionaTurma(turmasArr);
			int response = data.adicionaAluno(novoAluno);
			return new Response(response).toString();
			
		} else if(requestStr.matches("\\/turma\\/[0-9]+$")) {
			int idTurma = Integer.parseInt(requestStr.split("/")[2]);
			String response = data.buscaTurma(idTurma);
			return response;
		} else if(requestStr.matches("\\/aluno\\/[0-9]+$")) {
			int idAluno = Integer.parseInt(requestStr.split("/")[2]);
			String response = data.buscaAluno(idAluno);
			return response;
		} else if (requestStr.matches("\\/apagaAluno\\/[0-9]+$")) {
			int idAluno = Integer.parseInt(requestStr.split("/")[2]);
			String response = data.excluiAluno(idAluno);
			return response;
		} else if (requestStr.matches("\\/apagaTurma\\/[0-9]+$")) {
			int idTurma = Integer.parseInt(requestStr.split("/")[2]);
			String response = data.excluiTurma(idTurma);
			return response;
		}
		
		return new Response(5).toString();
	}
}
