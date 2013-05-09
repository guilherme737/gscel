package br.com.gscel;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnTabChangeListener, TabContentFactory {

	private final String CATEGORIA = "livro";
	
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);
        
        TabSpec tab1 = tabHost.newTabSpec("Tab 1");
        tab1.setIndicator("Membros", getResources().getDrawable(R.drawable.membros));
        tab1.setContent(new Intent(this, ListaMembrosActivity.class));
        //tab1.setContent(this);
        tabHost.addTab(tab1);
        
        TabSpec tab2 = tabHost.newTabSpec("Tab 2");
        tab2.setIndicator("Relatório", getResources().getDrawable(R.drawable.relatorio));
        tab2.setContent(new Intent(this, ListaMesesRelatorioActivity.class));
        tabHost.addTab(tab2);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public View createTabContent(String tabId) {
		TextView tv = new TextView(this);
		tv.setText("Utilizando uma factory para criar a aba:" + tabId);		
		return tv;
	}


	@Override
	public void onTabChanged(String tabId) {
		Log.i(CATEGORIA, "Trocou aba: " + tabId);
		
	}
    
}
