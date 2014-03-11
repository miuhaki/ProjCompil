package Compil;

import java.util.HashMap;

public class TabIdent
{
	private HashMap<String,Ident> table;
	
	public TabIdent(int taille)
	{
		table = new HashMap<String,Ident>(taille);
	}
	
	public Ident chercherIdent(String clef)
	{
		return table.get(clef);
	}
	
	public boolean existeIdent(String clef)
	{
		return table.containsKey(clef);
	}
	
	public void rangeIdent(String clef, Ident id)
	{
		table.put(clef, id);
	}
}
