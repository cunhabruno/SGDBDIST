package turmaServer;
import java.util.ArrayList;
import java.util.Iterator;

public class TurmasTabela {
	private ArrayList<Turma> turmas = new ArrayList<Turma>();
	
	public void adicionaTurma (Turma turma) {
		turmas.add(turma);
	}
	
	public boolean checkTurmaExiste(int idTurma) {
		for(Turma turma : turmas) {
			if(turma.getIdTurma() == idTurma) {
				return true;
			}
		}
		return false;
	}
	
	public boolean excluiTurma(int idTurma) {
		Iterator<Turma> ite = turmas.iterator();
		while(ite.hasNext()) {
			if(ite.next().getIdTurma() == idTurma) {
				ite.remove();
				return true;
			}
		}
		return false;
	}
	
	public String getTurma(int idTurma) {
		Iterator<Turma> ite = turmas.iterator();
		Turma turma;
		while(ite.hasNext()) {
			turma = ite.next();
			if(turma.getIdTurma() == idTurma) {
				return turma.toString();
			}
		}
		return "";
	}
}
