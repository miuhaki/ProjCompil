package Compil;

import java.util.HashMap;
import java.util.Map.Entry;

public class TabIdent {
	
	private HashMap<String,Ident> table;
	
	public static class NoSuchKeyException extends Exception {
		public NoSuchKeyException(String clef){
			super("L'identifiant "+ clef + " n'existe pas");
		}
	}
	
	public static class KeyAlreadyThereException extends Exception {
		public KeyAlreadyThereException(String clef){
			super("L'identifiant " + clef + " est d�clar� plusieurs fois");
		}
	}
	
	public TabIdent()
	{
		table = new HashMap<String,Ident>();
	}
	
	public Ident chercherIdent(String clef) throws NoSuchKeyException
	{	
		Ident i = table.get(clef); 
		if (i == null){
			throw new NoSuchKeyException(clef);
		}
		return i;
	}
	
	public boolean existeIdent(String clef)
	{
		return table.containsKey(clef);
	}
	
	public void rangeIdent(String clef, Ident id) throws KeyAlreadyThereException
	{
		Ident i = table.get(clef); 
		if (i == null){
			throw new KeyAlreadyThereException(clef);
		}
		table.put(clef, id);
	}
	
	public int compteVariables(){
		int cpt=0;
		for(Entry<String, Ident> entry : table.entrySet()) {
			if(entry.getValue().estVar())
				cpt++;
		}
		return cpt;
	}
}
