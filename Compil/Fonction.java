package Compil;

import java.util.Stack;

public class Fonction {
	public Stack<String> foncts;
	public Stack<Integer> typesParam; // prends ses valeurs dans YakaConstants

	public Fonction(){
		foncts = new Stack<String>();
		typesParam = new Stack<Integer>();
	}

	public void empilerFonct(String name)
	{
		foncts.push(name);
	}
	
	public String depilerFonct()
	{
		return foncts.pop();
	}

	public String getFonctCourante()
	{
		return foncts.peek();
	}
	
	public void empilerParam(int type)
	{
		typesParam.push(type);
	}
	
	public void depilerParam()
	{
		typesParam.pop();
	}

	private static String typeToString(int type)
	{
			String t ="";
			switch(type)
			{
				case YakaConstants.ENTIER:
					t = "entier";
					break;
				case YakaConstants.BOOLEEN:
					t = "booleen";
					break;
				default:
					break;
			}
		return t;
	}

	public void verifieParams()
	{
		int temp1;
		int temp2;
		Stack<Integer> paramAttendus = Yaka.tabIdent.chercherFonc(foncts.peek()).getParam();
		int nbParamsAttendus = paramAttendus.size();
		while(!typesParam.empty() && nbParamsAttendus > 0)
		{
			nbParamsAttendus--; //pour l'utiliser comme index
			if((temp1 = typesParam.pop()) != (temp2 = paramAttendus.get(nbParamsAttendus))) {
				Yaka.afficherErreur("Parametre incorrect : "+typeToString(temp1)+" lu, "+typeToString(temp2)+" attendu");
			}
		}
		if(!typesParam.empty()) {
			Yaka.afficherErreur("Trop de parametres");
		}
		if(nbParamsAttendus > 0) { //si tout se passe bien il est � -1 � la fin de la boucle
			Yaka.afficherErreur("Parametre manquant");
		}
		typesParam.clear();
	}
}
