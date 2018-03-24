import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.*;

public class Aluno implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idAluno;
	private String nomeAluno;
	private ArrayList<Turma> turmas;
	
	public Aluno(int idAluno, String nomeAluno) {
		super();
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		turmas = new ArrayList<Turma>();
	}
	
	public Aluno() {
		super();
	}
	
	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public void adicionaTurma(Turma turma) {
		Turma turmaAux = new Turma();
		turmaAux.setIdTurma(turma.getIdTurma());
		turmaAux.setNomeTurma(turma.getNomeTurma());
		turmas.add(turmaAux);
	}
	
	@Override
	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(this);
	}
}
