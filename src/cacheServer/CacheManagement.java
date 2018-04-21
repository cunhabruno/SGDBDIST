package cacheServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CacheManagement {
	private static ArrayList<CachedObject> cachedObjects = new ArrayList<CachedObject>();

	public ArrayList<CachedObject> getCachedObjects() {
		return cachedObjects;
	}

	public String handleRequest(String requestStr) {
		Iterator<CachedObject> ite = cachedObjects.iterator();
		CachedObject cacheData;
		if (requestStr.equals("/alunos") || requestStr.equals("/turmas") || requestStr.matches("\\/turma\\/[0-9]+$")
				|| requestStr.matches("\\/aluno\\/[0-9]+$")) {
			while (ite.hasNext()) {
				cacheData = ite.next();
				if (cacheData.getRequestName().equals(requestStr)) {
					if (cacheData.timedOut(10000)) {
						System.out.println("Removido");
						ite.remove();
						break;
					} else {
						System.out.println("Peguei no cache");
						return cacheData.getValue();
					}
				}
			}
			System.out.println("NAO Peguei no cache");
			String alunosData = this.makeRequestToGerenciador(requestStr);
			CachedObject newData = new CachedObject(requestStr, alunosData);
			newData.setAddedTime(System.currentTimeMillis());
			cachedObjects.add(newData);
			return alunosData;
		} else if (requestStr.matches("\\/apagaAluno\\/[0-9]+$")) {
			int idAluno = Integer.parseInt(requestStr.split("/")[2]);
			while (ite.hasNext()) {
				cacheData = ite.next();
				if (cacheData.getRequestName().equals("/aluno/" + idAluno)) {
					System.out.println("Removido por Exclusao");
					ite.remove();
				}
			}
			return this.makeRequestToGerenciador(requestStr);
		} else if (requestStr.matches("\\/apagaTurma\\/[0-9]+$")) {
			int idTurma = Integer.parseInt(requestStr.split("/")[2]);
			while (ite.hasNext()) {
				cacheData = ite.next();
				if (cacheData.getRequestName().equals("/turma/" + idTurma)) {
					System.out.println("Removido por Exclusao");
					ite.remove();
				}
			}
			return this.makeRequestToGerenciador(requestStr);
		}
		return this.makeRequestToGerenciador(requestStr);
	}

	private String makeRequestToGerenciador(String requestString) {
		try {
			Socket serverGerenciamento = new Socket("localhost", 2221);

			PrintWriter outGer = new PrintWriter(serverGerenciamento.getOutputStream(), true);
			Scanner inGer = new Scanner(serverGerenciamento.getInputStream());
			outGer.println(requestString);
			outGer.flush();

			String inGerStr = "";
			while (inGer.hasNext()) {
				inGerStr += inGer.nextLine() + '\n';
			}

			outGer.close();
			inGer.close();
			serverGerenciamento.close();

			return inGerStr;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
