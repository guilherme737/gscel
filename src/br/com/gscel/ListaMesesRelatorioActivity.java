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
		
		ArrayList<MesAjudante> lstMes = new ArrayList<ListaMesesRelatorioActivity.MesAjudante>();
		
		RelatorioDAO relatorioDAO = new RelatorioDAO(this);
		
		for (int i = 1; i <= 12; i++) {
			MesAjudante mes = new MesAjudante(i);
			lstMes.add(mes);
		}
		
		return lstMes;
	}

	public class MesAjudante {
		
		private String descricao;
		
		private List<String> lstSemanas;
		
		
		public MesAjudante(int mes) {
			setDescricao(Utils.obterMesPorExtenso(mes));
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public List<String> getLstSemanas() {
			if (lstSemanas == null) {
				lstSemanas = new ArrayList<String>();
			}
			return lstSemanas;
		}

		public void setLstSemanas(List<String> lstSemanas) {
			this.lstSemanas = lstSemanas;
		}
	}
	
	
}
