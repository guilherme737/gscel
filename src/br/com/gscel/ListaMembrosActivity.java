package br.com.gscel;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import br.com.gscel.entidade.Membro;
import br.com.gscel.persistencia.MembroDAO;

public class ListaMembrosActivity extends Activity {

	private ListView lista;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listamembros);
		lista = (ListView) findViewById(R.id.listamembros);
		
		MembroDAO membroDAO = new MembroDAO(this);
        List<Membro> lstMembros = membroDAO.getLista();
        membroDAO.close(); 
        
        ArrayAdapter<Membro> adapter = new ArrayAdapter<Membro>(this,android.R.layout.simple_list_item_1, lstMembros);
        lista.setAdapter(adapter);
       
        lista.setClickable(true);
        lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view, int posicao,long id) {
				//Toast.makeText(ListaAlunos.this, "Vc clicou certo", Toast.LENGTH_LONG).show();
				SharedPreferences preferences = getSharedPreferences("membroSelecionado", MODE_PRIVATE);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putInt("posicao", posicao);
				editor.commit();
				
				Intent intent = new Intent(ListaMembrosActivity.this, FormularioMembroActivity.class);
				startActivity(intent);
			}
        	 
		});		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.clear();
		
		MenuItem novo = menu.add(0,0,0,"Novo Membro");
    	novo.setIcon(R.drawable.novo);
    	
//    	MenuItem sincronizar = menu.add(0,1,0,"Sincronizar");
//    	sincronizar.setIcon(R.drawable.novo);
    	
    	
    	return super.onCreateOptionsMenu(menu);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
		if(item.getItemId() == 0){
			SharedPreferences preferences = getSharedPreferences("membroSelecionado", MODE_PRIVATE);
    		SharedPreferences.Editor editor = preferences.edit();
    		editor.remove("posicao");
    		editor.commit();
    		startActivity(new Intent(ListaMembrosActivity.this, FormularioMembroActivity.class));
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
    protected void onResume() {
    	super.onResume();
    	carregaLista();
    }
    
    private void carregaLista() {
		MembroDAO dao = new MembroDAO(this);
		List<Membro> alunos = dao.getLista();
		dao.close();
		
		LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final List<View> linhas = new ArrayList<View>();
		
		for(int i = 0; i< alunos.size(); i++){
			Membro aluno = alunos.get(i);
			View view = vi.inflate(R.layout.item, null);
			
			LinearLayout ll = (LinearLayout) view.findViewById(R.id.fundo);
			
			if(i%2 == 0){
				ll.setBackgroundColor(0x77DDEEFF);
			}else{
				ll.setBackgroundColor(0x77F0F0F0);
			}
			
			ImageView imagem = (ImageView) view.findViewById(R.id.foto);
			
			//if(aluno.getFoto() == null){
				imagem.setImageResource(R.drawable.noimage);
			/*}else{
				try{
					FileInputStream stream = new FileInputStream(aluno.getFoto());
					Bitmap bmp = BitmapFactory.decodeStream(stream);
					stream.close();
					imagem.setImageBitmap(bmp);
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			*/
			TextView texto = (TextView) view.findViewById(R.id.nome);
			texto.setText(aluno.getNome());
			texto.setTextColor(Color.BLACK);
			
			TextView encargo = (TextView) view.findViewById(R.id.encargo);
			encargo.setText(aluno.getDataNascimento());
			encargo.setTextColor(Color.BLACK);
			
			linhas.add(view);		
		}
		
		lista.setAdapter(new ArrayAdapter<View>(this, android.R.layout.simple_list_item_1,linhas){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				return linhas.get(position)/*super.getView(position, convertView, parent)*/;
			}
		});
		
	}

}
