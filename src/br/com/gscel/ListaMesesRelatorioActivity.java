package br.com.gscel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import br.com.gscel.adapter.ListaMesAdapter;
import br.com.gscel.entidade.Relatorio;
import br.com.gscel.persistencia.RelatorioDAO;
import br.com.gscel.util.Utils;

public class ListaMesesRelatorioActivity extends Activity {

	private ListaMesAdapter listaMesAdapter;

    private ArrayList<MesAjudante> lstMesAjudante;

    private ExpandableListView expandList;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listamesesrelatorio);
		expandList = (ExpandableListView) findViewById(R.id.mesesListView);
        lstMesAjudante = obterListaMeses();
        listaMesAdapter = new ListaMesAdapter(ListaMesesRelatorioActivity.this, lstMesAjudante);
        expandList.setAdapter(listaMesAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuItem novo = menu.add(0,0,0,"Novo Relatório");
    	novo.setIcon(R.drawable.novo);
    	    	
    	return super.onCreateOptionsMenu(menu);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
		if(item.getItemId() == 0){
			SharedPreferences preferences = getSharedPreferences("relatorioSelecionado", MODE_PRIVATE);
    		SharedPreferences.Editor editor = preferences.edit();
    		editor.remove("posicao");
    		editor.commit();
    		startActivity(new Intent(ListaMesesRelatorioActivity.this, FormularioRelatorioActivity.class));
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public ArrayList<MesAjudante> obterListaMeses() {
		
		int ano = 2013;
		
		ArrayList<MesAjudante> lstMes = new ArrayList<ListaMesesRelatorioActivity.MesAjudante>();
		
		RelatorioDAO relatorioDAO = new RelatorioDAO(this);
		
		for (int i = 1; i <= 12; i++) {
			MesAjudante mes = new MesAjudante(i);
			mes.setLstSemanas(new ArrayList<SemanaRelatorioAjudante>());
			
			List<Relatorio> lstRelatorio = relatorioDAO.getRelatorioPorAnoEMes(ano, i);			
			for (Relatorio relatorio : lstRelatorio) {
				SemanaRelatorioAjudante semana = new SemanaRelatorioAjudante();
				semana.setData(Utils.obterDataPorExtenso(relatorio.getDia(), relatorio.getMes(), relatorio.getAno()));
				semana.setDetalhe("Membros - " + relatorio.getMembros() + " | Freq. Assíd. - " + relatorio.getFrequentadoresAssiduos() + " | Visitantes - " + relatorio.getVisitantes());
				mes.getLstSemanas().add(semana);
			}
			
			lstMes.add(mes);
		}
		
		return lstMes;
	}
	
	@Override
    protected void onResume() {
    	super.onResume();
    	carregaLista();
    }
    
    private void carregaLista() {
				
		lstMesAjudante = obterListaMeses();		
		listaMesAdapter = new ListaMesAdapter(ListaMesesRelatorioActivity.this, lstMesAjudante);
        expandList.setAdapter(listaMesAdapter);
		
	}


	public class MesAjudante {
		
		private String descricao;
		
		private List<SemanaRelatorioAjudante> lstSemanas;
		
		
		public MesAjudante(int mes) {
			setDescricao(Utils.obterMesPorExtenso(mes));
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public List<SemanaRelatorioAjudante> getLstSemanas() {
			if (lstSemanas == null) {
				lstSemanas = new ArrayList<SemanaRelatorioAjudante>();
			}
			return lstSemanas;
		}

		public void setLstSemanas(List<SemanaRelatorioAjudante> lstSemanas) {
			this.lstSemanas = lstSemanas;
		}
	}
	
	public class SemanaRelatorioAjudante {
		
		public String data;
		public String detalhe;
		
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public String getDetalhe() {
			return detalhe;
		}
		public void setDetalhe(String detalhe) {
			this.detalhe = detalhe;
		}
		
		
		
	}
	
	
	
	
}
