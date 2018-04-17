package client;
import java.io.IOException;

import alunoServer.Aluno;
import alunoServer.AlunosTabela;
import databaseMgmt.DatabaseMgmt;
import turmaServer.Turma;
import turmaServer.TurmasTabela;

public class mainTEst {

	public static void main(String[] args) {
		Aluno a1 = new Aluno(3, "23");
		Turma t1 = new Turma(1, "Java");
		Turma t2 = new Turma(2, "SO");
		a1.adicionaTurma(new String[] {"1"});
		a1.adicionaTurma(new String[] {"2"});
		System.out.println(a1);
		System.out.println(t1);
		DatabaseMgmt dataBaseMgmt = new DatabaseMgmt();
		try {
			dataBaseMgmt.adicionaTurma(t1);
			dataBaseMgmt.adicionaTurma(t2);
			System.out.println(dataBaseMgmt.buscaTurma(2));
			//dataBaseMgmt.excluiTurma(2);
			dataBaseMgmt.adicionaAluno(new Aluno(4, "Matheus"));
			dataBaseMgmt.adicionaAluno(new Aluno(5, "Vitor"));
			//dataBaseMgmt.excluiAlunos(3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
