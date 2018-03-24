import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Turma {
	private int idTurma;
	private String nomeTurma;
	private ArrayList<Aluno> alunos;
	
	public Turma(int idTurma, String nomeTurma) {
		super();
		this.idTurma = idTurma;
		this.nomeTurma = nomeTurma;
		alunos = new ArrayList<Aluno>();
	}
	
	public Turma() {
		super();
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public void adicionaAluno(Aluno aluno) {
		Aluno alunoAux = new Aluno();
		alunoAux.setIdAluno(aluno.getIdAluno());
		alunoAux.setNomeAluno(aluno.getNomeAluno());
		alunos.add(alunoAux);
	}
	
	@Override
	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(this);
	}
}
