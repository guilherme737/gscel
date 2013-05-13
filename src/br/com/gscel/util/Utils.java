package br.com.gscel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

	public static String obterMesPorExtenso(int mes) {
		
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
	
	public static String obterDataPorExtenso(int dia, int mes, int ano) {
		
		Calendar c = Calendar.getInstance(Locale.getDefault());
		c.set(ano, mes, dia);		
		
		DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", Locale.getDefault()); 
		Date hoje = c.getTime();  
		
		return dfmt.format(hoje);
	}
}
