package br.com.gscel.entidade;

import java.io.Serializable;

public class Membro implements Serializable {

	private static final long serialVersionUID = 3713955474723456027L;
	
	public static final String MEMBRO_TAB = "membro";
	
	public static final String CREATE_TABLE_MEMBRO = "CREATE TABLE " + MEMBRO_TAB 
			+ "( id INTEGER PRIMARY KEY,"
			+ " nome TEXT UNIQUE NOT NULL,"
			+ " datanascimento TEXT,"
			+ " telefone TEXT,"
			+ " encargo TEXT" + ");";

	private int id;
	private String nome;
	private String dataNascimento;
	private String telefone;
	private String encargo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEncargo() {
		return encargo;
	}

	public void setEncargo(String encargo) {
		this.encargo = encargo;
	}

}
