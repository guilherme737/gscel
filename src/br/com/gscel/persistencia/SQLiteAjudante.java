package br.com.gscel.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.gscel.entidade.Membro;
import br.com.gscel.entidade.Relatorio;

public class SQLiteAjudante extends SQLiteOpenHelper {

	private static final String BANCO = "gscel.db";
	
	private static final int VERSION = 2;
	
	private static final String TAG = "SQLiteAjudante";
	
	
	public SQLiteAjudante(Context context) {
		super(context, BANCO, null, VERSION);
	
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL(Membro.CREATE_TABLE_MEMBRO);
		db.execSQL(Relatorio.CREATE_TABLE_RELATORIO);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(TAG, "Atualizando o banco de dados da versão " + oldVersion
				+ " para " + newVersion
				+ ", todos os dados serão perdidos!");
		db.execSQL("DROP TABLE IF EXISTS " + Membro.MEMBRO_TAB);
		db.execSQL("DROP TABLE IF EXISTS " + Relatorio.RELATORIO_TAB);
		onCreate(db);
	}
	
	
	
	
	
}
