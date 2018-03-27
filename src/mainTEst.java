import java.io.IOException;

public class mainTEst {

	public static void main(String[] args) {
		Aluno a1 = new Aluno(3, "23");
		Turma t1 = new Turma(1, "Java");
		Turma t2 = new Turma(2, "SO");
		t1.adicionaAluno(a1);
		a1.adicionaTurma(t2);
		a1.adicionaTurma(t1);
		AlunosTabela alunos = new AlunosTabela();
		TurmasTabela turmas = new TurmasTabela();
		alunos.adicionaAluno(a1);
		System.out.println(a1);
		System.out.println(t1);
		DatabaseMgmt dataBaseMgmt = new DatabaseMgmt();
		try {
			dataBaseMgmt.adicionaTurma(turmas, t1);
			dataBaseMgmt.adicionaTurma(turmas, t2);
			//dataBaseMgmt.excluiTurma(2);
			dataBaseMgmt.adicionaAluno(alunos, new Aluno(4, "Matheus"));
			dataBaseMgmt.adicionaAluno(alunos, new Aluno(5, "Vitor"));
			//dataBaseMgmt.excluiAlunos(3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
