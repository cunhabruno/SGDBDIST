package databaseMgmt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Response {
	private int codRetorno;
	private String descricaoRetorno;

	public int getCodigoRetorno() {
		return codRetorno;
	}

	public void setCodigoRetorno(int codigoRetorno) {
		this.codRetorno = codigoRetorno;
	}

	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}

	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}

	public Response(int codigoRetorno) {
		super();
		this.codRetorno = codigoRetorno;
		switch (codigoRetorno) {
		case 0:
			this.descricaoRetorno = "Requisicao OK";
			break;
		case 1:
			this.descricaoRetorno = "Registro ja cadastrado";
			break;
		case 2:
			this.descricaoRetorno = "Erro de Relcionmaento";
			break;
		case 3:
			this.descricaoRetorno = "Servidor Indisponivel";
			break;
		case 4:
			this.descricaoRetorno = "Registro Nao Encontrado";
			break;
		case 5:
			this.descricaoRetorno = "Requisicao Invalida";
			break;
		default:
			this.descricaoRetorno = "WTF??";
			break;
		}
	}

	@Override
	public String toString() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
