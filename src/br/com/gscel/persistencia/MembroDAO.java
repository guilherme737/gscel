package br.com.gscel.persistencia;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.gscel.entidade.Membro;

public class MembroDAO extends SQLiteAjudante {
	
	private static final String[] COLUNAS = {"id", "nome", "datanascimento", "telefone", "encargo"};

	public MembroDAO(Context context) {
		super(context);

	}

	public void inserir(Membro membro) {
		ContentValues valores = new ContentValues();
		valores.put("nome", membro.getNome());
		valores.put("dataNascimento", membro.getDataNascimento());
		valores.put("telefone", membro.getTelefone());
		valores.put("encargo", membro.getEncargo());

		getWritableDatabase().insert(Membro.MEMBRO_TAB, null, valores);
	}

	public List<Membro> getLista() {
		Cursor c = getWritableDatabase().query(Membro.MEMBRO_TAB, COLUNAS, null, null,
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
	
	public Membro getMembroById(int posicao) {
		Cursor c = getWritableDatabase().query(Membro.MEMBRO_TAB, COLUNAS, "id=?", new String[]{"" + posicao}, null, null, null);
		c.moveToFirst();
		
		Membro m = new Membro();
		m.setId(c.getInt(0));		
		m.setNome(c.getString(1));
		m.setDataNascimento(c.getString(2));
		m.setTelefone(c.getString(3));
		m.setEncargo(c.getString(4));
		
		c.close();
		return m;
	}

	public void alterar(Membro membro) {
		ContentValues values = new ContentValues();
		values.put("nome", membro.getNome());
		values.put("dataNascimento", membro.getDataNascimento());
		values.put("telefone", membro.getTelefone());
		values.put("encargo", membro.getEncargo());
		
		getWritableDatabase().update(Membro.MEMBRO_TAB, values, "id=?", new String[]{"" + membro.getId()});
		
	}
	
}
