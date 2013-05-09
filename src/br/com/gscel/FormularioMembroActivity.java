package br.com.gscel;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import br.com.gscel.entidade.Membro;
import br.com.gscel.persistencia.MembroDAO;
import br.com.gscel.util.Utils;

public class FormularioMembroActivity extends Activity {

	static final int DATE_DIALOG_ID = 999;
	
	private Membro membro = new Membro();
	
	ArrayAdapter<String> adapter;
    String numbers[] = { "Membro", "Frequentador Assíduo"};
	
	private Button dataNascimentoBtn;
	
	private int year;
	private int month;
	private int day;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulariomembro);
        
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
    	month = c.get(Calendar.MONTH);
    	day = c.get(Calendar.DAY_OF_MONTH);
    	
    	dataNascimentoBtn = (Button) findViewById(R.id.dataNascimentoBtn);
    	dataNascimentoBtn.setText(new StringBuilder().append(day).append(" de ").append(Utils.obterMesPorExtenso(month + 1)).append(" de ").append(year));
        
        
    	SharedPreferences preferences = getSharedPreferences("membroSelecionado", MODE_PRIVATE);
		final int posicao = preferences.getInt("posicao", -1);
		
		if(posicao != -1){
			MembroDAO dao = new MembroDAO(this);
			membro = dao.getMembroById(posicao + 1);
			dao.close();
			
			((EditText)findViewById(R.id.nomeEdt)).setText(membro.getNome());
			((Button)findViewById(R.id.dataNascimentoBtn)).setText(membro.getDataNascimento());
			((EditText)findViewById(R.id.telefoneEdt)).setText(membro.getTelefone());
//			((Spinner)findViewById(R.id.encargoSpn)).setText(membro.getEncargo());
			((Button) findViewById(R.id.gravarBtn)).setText("Alterar");
			
		}
        
        this.addListenerOnDataNascimentoBtn();
        
        
        Spinner encargoSpn = (Spinner) findViewById(R.id.encargoSpn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        encargoSpn.setAdapter(adapter);
        
        
        Button gravarBtn = (Button) findViewById(R.id.gravarBtn);
        gravarBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
				EditText nome = (EditText) findViewById(R.id.nomeEdt);
				EditText telefone = (EditText) findViewById(R.id.telefoneEdt);
				Button dataNascimento = (Button) findViewById(R.id.dataNascimentoBtn);
				Spinner encargo = (Spinner) findViewById(R.id.encargoSpn);
				
				membro.setNome(nome.getEditableText().toString());
				membro.setTelefone(telefone.getEditableText().toString());
				membro.setDataNascimento(dataNascimento.getText().toString());
				membro.setEncargo(encargo.getSelectedItem().toString());
				
				MembroDAO membroDAO = new MembroDAO(FormularioMembroActivity.this);
				
				if(posicao != -1){
					membro.setId(posicao+1);
					membroDAO.alterar(membro);
				}else{					
					membroDAO.inserir(membro);
				}
				
				membroDAO.close();
				
				finish();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void addListenerOnDataNascimentoBtn() {

    	dataNascimentoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}

		});

	}
    
    @Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month, day);
		}
		return null;
	}
    
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			dataNascimentoBtn.setText(new StringBuilder().append(day).append(" de ").append(Utils.obterMesPorExtenso(month + 1)).append(" de ").append(year)
					.append(" "));

		}
	};
	
	
	
    
}
