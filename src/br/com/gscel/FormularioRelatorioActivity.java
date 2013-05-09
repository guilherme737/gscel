package br.com.gscel;

import java.util.Calendar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.com.gscel.entidade.Relatorio;
import br.com.gscel.persistencia.MembroDAO;
import br.com.gscel.persistencia.RelatorioDAO;
import br.com.gscel.util.Utils;

public class FormularioRelatorioActivity extends Activity {
	
	static final int DATE_DIALOG_ID = 999;
	
	private Relatorio relatorio;

	private int year;
	private int month;
	private int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario_relatorio);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		Button dataReuniaoBtn = (Button) findViewById(R.id.dataCelulaBtn);
		dataReuniaoBtn.setText(new StringBuilder().append(day).append(" de ").append(Utils.obterMesPorExtenso(month + 1)).append(" de ").append(year));

		SharedPreferences preferences = getSharedPreferences(
				"membroSelecionado", MODE_PRIVATE);
		final int posicao = preferences.getInt("posicao", -1);

		if (posicao != -1) {
			RelatorioDAO dao = new RelatorioDAO(this);
			relatorio = dao.getRelatorioById(posicao + 1);
			dao.close();

			((Button) findViewById(R.id.dataCelulaBtn)).setText(relatorio.getDataReuniao());
			((EditText) findViewById(R.id.membroRelatorioEdt)).setText(relatorio.getMembros());
			((EditText) findViewById(R.id.faRelatorioEdt)).setText(relatorio.getFrequentadoresAssiduos());
			((EditText) findViewById(R.id.visitanteRelatorioEdt)).setText(relatorio.getVisitantes());

			((Button) findViewById(R.id.gravarBtn)).setText("Alterar");

		}

		this.addListenerOnDataReuniaoBtn(dataReuniaoBtn);
		
		Button gravarBtn = (Button) findViewById(R.id.gravarRelatorioBtn);
        gravarBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
				Button datareuniaoBtn = (Button) findViewById(R.id.dataCelulaBtn);
				EditText membrosEdt = (EditText) findViewById(R.id.membroRelatorioEdt);
				EditText frequentadoresAssiduosEdt = (EditText) findViewById(R.id.faRelatorioEdt);
				EditText visitantesEdt = (EditText) findViewById(R.id.visitanteRelatorioEdt);
				
				RelatorioDAO relatorioDAO = new RelatorioDAO(FormularioRelatorioActivity.this);
				
				if(posicao != -1){
					relatorio.setId(posicao+1);
					relatorioDAO.alterar(relatorio);
				}else{					
					relatorioDAO.inserir(relatorio);
				}
				
				relatorioDAO.close();
				
				finish();
			}
		});
	}

	public void addListenerOnDataReuniaoBtn(Button dataReuniaoBtn) {

		dataReuniaoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.formulario_relatorio, menu);
		return true;
	}

}
