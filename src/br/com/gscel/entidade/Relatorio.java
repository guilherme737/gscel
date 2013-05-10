package br.com.gscel.entidade;

public class Relatorio {

	
	public static final String RELATORIO_TAB = "relatorio";
	
	public static final String CREATE_TABLE_RELATORIO = "CREATE TABLE " + RELATORIO_TAB 
			+ "( id INTEGER PRIMARY KEY,"
			+ " dataReuniao TEXT UNIQUE NOT NULL,"
			+ " membros INTEGER,"
			+ " frequentadoresAssiduos INTEGER,"
			+ " visitantes INTEGER,"
			+ " dia INTEGER,"
			+ " mes INTEGER,"
			+ " ano INTEGER,"
			+ ");";
	
	private int id;	
	private String dataReuniao;
	private int membros;
	private int frequentadoresAssiduos;
	private int visitantes;
	private int dia;
	private int mes;
	private int ano;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataReuniao() {
		return dataReuniao;
	}

	public void setDataReuniao(String dataReuniao) {
		this.dataReuniao = dataReuniao;
	}

	public int getMembros() {
		return membros;
	}

	public void setMembros(int membros) {
		this.membros = membros;
	}

	public int getFrequentadoresAssiduos() {
		return frequentadoresAssiduos;
	}

	public void setFrequentadoresAssiduos(int frequentadoresAssiduos) {
		this.frequentadoresAssiduos = frequentadoresAssiduos;
	}

	public int getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(int visitantes) {
		this.visitantes = visitantes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	

}
