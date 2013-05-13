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
import br.com.gscel.ListaMesesRelatorioActivity.SemanaRelatorioAjudante;
import br.com.gscel.R;

public class ListaMesAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<MesAjudante> lstMesAjudante;

	public ListaMesAdapter(Context context, ArrayList<MesAjudante> groups) {
		this.context = context;
		this.lstMesAjudante = groups;
	}

	public void addItem(SemanaRelatorioAjudante item, MesAjudante mesAjudante) {
		if (!lstMesAjudante.contains(mesAjudante)) {
			lstMesAjudante.add(mesAjudante);
		}
		int index = lstMesAjudante.indexOf(mesAjudante);
		List<SemanaRelatorioAjudante> ch = lstMesAjudante.get(index).getLstSemanas();
		ch.add(item);
		lstMesAjudante.get(index).setLstSemanas(ch);
	}

	public Object getChild(int groupPosition, int childPosition) {
		List<SemanaRelatorioAjudante> chList = lstMesAjudante.get(groupPosition).getLstSemanas();
		return chList.get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {
		SemanaRelatorioAjudante child = (SemanaRelatorioAjudante) getChild(groupPosition, childPosition);

		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = infalInflater.inflate(R.layout.lista_mes_relatorio_child_item, null);
		}

		TextView tv = (TextView) view.findViewById(R.id.semanaTxt);		
		tv.setText(child.getData());
		
		TextView detalheTxt = (TextView) view.findViewById(R.id.detalheTxt);		
		detalheTxt.setText(child.getDetalhe());

		return view;
	}

	public int getChildrenCount(int groupPosition) {
		List<SemanaRelatorioAjudante> chList = lstMesAjudante.get(groupPosition).getLstSemanas();

		return chList.size();

	}

	public Object getGroup(int groupPosition) {
		return lstMesAjudante.get(groupPosition);
	}

	public int getGroupCount() {
		return lstMesAjudante.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isLastChild, View view,
			ViewGroup parent) {
		MesAjudante group = (MesAjudante) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.lista_mes_relatorio_group_item, null);
		}
		TextView tv = (TextView) view.findViewById(R.id.mesTxt);
		tv.setText(group.getDescricao());
		return view;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

}
