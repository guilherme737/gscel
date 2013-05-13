package br.com.gscel.persistencia;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.gscel.entidade.Relatorio;

public class RelatorioDAO extends SQLiteAjudante {

	
	private static final String[] COLUNAS = {"id", "dataReuniao", "membros", "frequentadoresAssiduos", "visitantes", "dia", "mes", "ano"};
	
	public RelatorioDAO(Context context) {
		super(context);		
	}

	
	public void inserir(Relatorio relatorio) {
		ContentValues valores = new ContentValues();
		valores.put("dataReuniao", relatorio.getDataReuniao());
		valores.put("membros", relatorio.getMembros());
		valores.put("frequentadoresAssiduos", relatorio.getFrequentadoresAssiduos());
		valores.put("visitantes", relatorio.getVisitantes());
		valores.put("dia", relatorio.getDia());
		valores.put("mes", relatorio.getMes());
		valores.put("ano", relatorio.getAno());

		getWritableDatabase().insert(Relatorio.RELATORIO_TAB, null, valores);
	}

	public List<Relatorio> getLista() {
		Cursor c = getWritableDatabase().query(Relatorio.RELATORIO_TAB, COLUNAS, null, null,
				null, null, null);
		List<Relatorio> lista = new ArrayList<Relatorio>();
		while (c.moveToNext()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setId(c.getInt(0));
			relatorio.setDataReuniao(c.getString(1));
			relatorio.setMembros(c.getInt(2));
			relatorio.setFrequentadoresAssiduos(c.getInt(3));
			relatorio.setVisitantes(c.getInt(4));
			relatorio.setDia(c.getInt(5));
			relatorio.setMes(c.getInt(6));
			relatorio.setAno(c.getInt(7));

			lista.add(relatorio);
		}
		c.close();

		return lista;

	}
	
	
	public Relatorio getRelatorioById(int posicao) {
		Cursor c = getWritableDatabase().query(Relatorio.RELATORIO_TAB, COLUNAS, "id=?", new String[]{"" + posicao}, null, null, null);
		c.moveToFirst();
		
		Relatorio r = new Relatorio();
		r.setId(c.getInt(0));		
		r.setDataReuniao(c.getString(1));
		r.setMembros(c.getInt(2));
		r.setFrequentadoresAssiduos(c.getInt(3));
		r.setVisitantes(c.getInt(4));
		
		c.close();
		return r;
	}
	
	public List<Relatorio> getRelatorioPorAnoEMes(int ano, int mes) {
		Cursor c = getWritableDatabase().query(Relatorio.RELATORIO_TAB, COLUNAS, "ano=? AND mes=?", new String[]{"" + ano, "" + mes}, null, null, "dia");
		//c.moveToFirst();
		
		List<Relatorio> lista = new ArrayList<Relatorio>();
		while (c.moveToNext()) {
			Relatorio relatorio = new Relatorio();
			relatorio.setId(c.getInt(0));
			relatorio.setDataReuniao(c.getString(1));
			relatorio.setMembros(c.getInt(2));
			relatorio.setFrequentadoresAssiduos(c.getInt(3));
			relatorio.setVisitantes(c.getInt(4));
			relatorio.setDia(c.getInt(5));
			relatorio.setMes(c.getInt(6));
			relatorio.setAno(c.getInt(7));

			lista.add(relatorio);
		}
		
		c.close();
		return lista;
	}

	public void alterar(Relatorio relatorio) {
		ContentValues values = new ContentValues();
		values.put("dataReuniao", relatorio.getDataReuniao());
		values.put("membros", relatorio.getMembros());
		values.put("frequentadoresAssiduos", relatorio.getFrequentadoresAssiduos());
		values.put("visitantes", relatorio.getVisitantes());
		
		getWritableDatabase().update(Relatorio.RELATORIO_TAB, values, "id=?", new String[]{"" + relatorio.getId()});
		
	}
	
}
