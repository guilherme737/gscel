package br.com.gscel.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import br.com.gscel.ListaMesesRelatorioActivity.MesAjudante;
import br.com.gscel.R;

public class ListaMesAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<MesAjudante> groups;

	public ListaMesAdapter(Context context, ArrayList<MesAjudante> groups) {
		this.context = context;
		this.groups = groups;
	}
	
	public void addItem(String item, MesAjudante group) {
		if (!groups.contains(group)) {
			groups.add(group);
		}
		int index = groups.indexOf(group);
		List<String> ch = groups.get(index).getLstSemanas();
		ch.add(item);
		groups.get(index).setLstSemanas(ch);
	}
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		List<String> chList = groups.get(groupPosition).getLstSemanas();
		return chList.get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		String child = (String) getChild(groupPosition, childPosition);
		
		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = infalInflater.inflate(R.layout.lista_mes_relatorio_child_item, null);
		}
		
		TextView tv = (TextView) view.findViewById(R.id.semanaTxt);
		tv.setText(child);
		

		return view;
	}

	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		List<String> chList = groups.get(groupPosition).getLstSemanas();

		return chList.size();

	}

	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groups.get(groupPosition);
	}

	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groups.size();
	}

	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		MesAjudante group = (MesAjudante) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.lista_mes_relatorio_group_item, null);
		}
		TextView tv = (TextView) view.findViewById(R.id.mesTxt);
		tv.setText(group.getDescricao());
		// TODO Auto-generated method stub
		return view;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
