package br.com.gscel;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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

public class MainActivity extends Activity {

	static final int DATE_DIALOG_ID = 999;
	
	private Membro membro;
	
	ArrayAdapter<String> adapter;
    String numbers[] = { "Membro", "Frequentador Assíduo"};
	
	private Button dataNascimentoBtn;
	
	private int year;
	private int month;
	private int day;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
    	month = c.get(Calendar.MONTH);
    	day = c.get(Calendar.DAY_OF_MONTH);
    	
    	dataNascimentoBtn = (Button) findViewById(R.id.dataNascimentoBtn);
    	dataNascimentoBtn.setText(new StringBuilder().append(day).append(" de ").append(obterMesPorExtenso(month + 1)).append(" de ").append(year));
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
				membro.setDataNascimento(dataNascimento.getEditableText().toString());
				membro.setEncargo(encargo.getSelectedItem().toString());
				
				MembroDAO membroDAO = new MembroDAO(MainActivity.this);
				
				membroDAO.inserir(membro);
				
				membroDAO.close();
				
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
			
			

			// set selected date into textview
			dataNascimentoBtn.setText(new StringBuilder().append(day).append(" de ").append(obterMesPorExtenso(month + 1)).append(" de ").append(year)
					.append(" "));

			// set selected date into datepicker also
			//dpResult.init(year, month, day, null);

		}
	};
	
	
	private String obterMesPorExtenso(int mes) {
		
		switch (mes) {
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Março";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";

		default:
			return "N/C";
			
		}
	}
    
}
