package br.com.gscel.persistencia;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.gscel.entidade.Membro;

public class MembroDAO extends SQLiteOpenHelper {

	private static final String TABELA = "Membro";
	private static final int VERSION = 1;
	
	private static final String[] COLUNAS = {"id", "nome", "datanascimento", "telefone", "encargo"};

	public MembroDAO(Context context) {
		super(context, TABELA, null, VERSION);

	}

	public void inserir(Membro membro) {
		ContentValues valores = new ContentValues();
		valores.put("nome", membro.getNome());
		valores.put("dataNascimento", membro.getDataNascimento());
		valores.put("telefone", membro.getTelefone());
		valores.put("encargo", membro.getEncargo());

		getWritableDatabase().insert(TABELA, null, valores);
	}

	public List<Membro> getLista() {
		Cursor c = getWritableDatabase().query(TABELA, COLUNAS, null, null,
				null, null, null);
		List<Membro> lista = new ArrayList<Membro>();
		while (c.moveToNext()) {
			Membro membro = new Membro();
			membro.setId(c.getInt(0));
			membro.setNome(c.getString(1));
			membro.setDataNascimento(c.getString(2));
			membro.setTelefone(c.getString(3));
			membro.setEncargo(c.getString(4));			

			lista.add(membro);
		}
		c.close();

		return lista;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + TABELA 
				+ "( id INTEGER PRIMARY KEY,"
				+ " nome TEXT UNIQUE NOT NULL,"
				+ " datanascimento TEXT,"
				+ " telefone TEXT,"
				+ " encargo TEXT" + ");";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + MembroDAO.TABELA);
		this.onCreate(db);
	}

}
