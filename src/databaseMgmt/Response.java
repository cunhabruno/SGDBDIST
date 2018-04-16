package databaseMgmt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Response {
	private int codigoRetorno;
	private String descricaoRetorno;

	public int getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}

	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}

	public Response(int codigoRetorno) {
		super();
		this.codigoRetorno = codigoRetorno;
		switch (codigoRetorno) {
		case 0:
			this.descricaoRetorno = "Requisição OK";
			break;
		case 1:
			this.descricaoRetorno = "Registro já cadastrado";
			break;
		case 2:
			this.descricaoRetorno = "Erro de Relcionmaento";
			break;
		case 3:
			this.descricaoRetorno = "Servidor Indisponível";
			break;
		case 4:
			this.descricaoRetorno = "Registro Não Encontrado";
			break;
		case 5:
			this.descricaoRetorno = "Requisição Inválida";
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
