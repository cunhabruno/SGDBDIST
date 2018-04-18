package alunoServer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class AlunosTabela implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	
	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void adicionaAluno (Aluno aluno) {
		alunos.add(aluno);
	}
	
	public boolean checkAlunoExiste(int idAluno) {
		for(Aluno aluno : alunos) {
			if(aluno.getIdAluno() == idAluno) {
				return true;
			}
		}
		return false;
	}
	
	public boolean excluiAluno(int idAluno) {
		Iterator<Aluno> ite = alunos.iterator();
		while(ite.hasNext()) {
			if(ite.next().getIdAluno() == idAluno) {
				ite.remove();
				return true;
			}
		}
		return false;
	}
	
	public String getAluno(int idAluno) {
		Iterator<Aluno> ite = alunos.iterator();
		Aluno aluno;
		while(ite.hasNext()) {
			aluno = ite.next();
			if(aluno.getIdAluno() == idAluno) {
				return aluno.toString();
			}
		}
		return "";
	}
	
	public Aluno getAlunoObj(int idAluno) {
		Iterator<Aluno> ite = alunos.iterator();
		Aluno aluno;
		while(ite.hasNext()) {
			aluno = ite.next();
			if(aluno.getIdAluno() == idAluno) {
				return aluno;
			}
		}
		return null;
	}
	
	public ArrayList<Aluno> getAlunosInTurma(int idTurma) {
		Iterator<Aluno> ite = alunos.iterator();
		Aluno aluno;
		ArrayList<Aluno> alunoArr = new ArrayList<Aluno>();
		while(ite.hasNext()) {
			aluno = ite.next();
			if(aluno.getTurmas().contains(idTurma)) {
				aluno.setTurmas(null);
				alunoArr.add(aluno);
			}
		}
		return alunoArr;
	}
	
	@Override
	public String toString() {
		
		return alunos.toString();
	}
	
}
