package Compil;

import java.io.OutputStream;

import Compil.Ecriture;

public class YVM
{	
	public static final int vrai = -1;
	public static final int faux = 0;
	
	protected OutputStream output;

	public YVM(String nomFic)
	{
		output=Ecriture.ouvrir(nomFic);
	}

	public void debutProg(){
		Ecriture.ecrireStringln(output, "entete");
	}

	/*
	 * Ne sert plus dans la partie 3 : Fonctions
	 */
	public void ouvrePrinc(){
		String texte = "ouvrePrinc "+Yaka.tabIdent.compteVariables()*2;
		Ecriture.ecrireStringln(output, texte);
	}

	public void lireConstOuVar(String nom) {
		Ident i = Yaka.tabIdent.chercherIdent(nom);
		if(i != null)
		{
			String texte = i.toYVM();
			Ecriture.ecrireStringln(output, texte);
		}
	}
	
	public void lireOp(Expression.Op op)
	{
		switch(op)
		{
		case SUP:
			lireSup();
			break;
		case INF:
			lireInf();
			break;
		case SUPEG:
			lireSupEg();
			break;
		case INFEG:
			lireInfEg();
			break;
		case EGAL:
			lireEg();
			break;
		case DIFF:
			lireDiff();
			break;
		case ADD:
			lireAdd();
			break;
		case SOUS:
			lireSous();
			break;
		case OU:
			lireOu();
			break;
		case MUL:
			lireMul();
			break;
		case DIV:
			lireDiv();
			break;
		case ET:
			lireEt();
			break;
		case NEG:
			lireNeg();
			break;
		case NON:
			lireNon();
			break;									
		}
	}

	public void lireImmediat(int i){
		String texte = "iconst "+i;
		Ecriture.ecrireStringln(output, texte);
	}
	
	public void affecter(String nom) {
		IdVal i = Yaka.tabIdent.chercherIdent(nom);
		Ecriture.ecrireStringln(output, "istore " + i.getValOuOffset());
	}

	public void lireAdd(){
		Ecriture.ecrireStringln(output, "iadd");
	}

	public void lireSous(){
		Ecriture.ecrireStringln(output, "isub");
	}

	public void lireMul(){
		Ecriture.ecrireStringln(output, "imul");
	}

	public void lireDiv(){
		Ecriture.ecrireStringln(output, "idiv");
	}

	public void lireOu(){
		Ecriture.ecrireStringln(output, "ior");
	}

	public void lireEt(){
		Ecriture.ecrireStringln(output, "iand");
	}

	public void lireNeg(){
		Ecriture.ecrireStringln(output, "ineg");
	}

	public void lireNon(){
		Ecriture.ecrireStringln(output, "inot");
	}

	public void lireInf(){
		Ecriture.ecrireStringln(output, "iinf");
	}

	public void lireInfEg(){
		Ecriture.ecrireStringln(output, "iinfegal");
	}

	public void lireSup(){
		Ecriture.ecrireStringln(output, "isup");
	}

	public void lireSupEg(){
		Ecriture.ecrireStringln(output, "isupegal");
	}

	public void lireEg(){
		Ecriture.ecrireStringln(output, "iegal");
	}

	public void lireDiff(){
		Ecriture.ecrireStringln(output, "idiff");
	}

	public void finProg(){
		Ecriture.ecrireStringln(output, "queue");
		Ecriture.fermer(output);
	}
	
	public void ecrireEnt() {
		Ecriture.ecrireStringln(output, "ecrireEnt");
	}
	
	public void ecrireBool() {
		Ecriture.ecrireStringln(output, "ecrireBool");
	}
	
	/**
	 * Choisit d'appeler ecrireEnt ou ecrireBool suivant le type de l'expression (en haut de la pile)
	 */
	
	public void ecrireExpr(){
		int type = Yaka.expression.dernierOperande();
		switch (type){
			case YakaConstants.BOOLEEN :
				ecrireBool();
				break;
			case YakaConstants.ENTIER :
				ecrireEnt();
				break;
			case YakaConstants.DEFAULT :
				System.out.println("Expression non valide.");
				break;
			default :
				System.out.println("Probleme de type d'expression. ");
				break ;		
		}
	}
	
	public void ecrireChaine(String s) {
		Ecriture.ecrireStringln(output, "ecrireChaine " + s);
	}
	
	public void aLaLigne() {
		Ecriture.ecrireStringln(output, "aLaLigne");
	}

	public void lireEnt(String id) {
		IdVal i = Yaka.tabIdent.chercherIdent(id) ;
		if(i.estVar()){
			Ecriture.ecrireStringln(output, "lireEnt " + i.getValOuOffset());
		}
		else{
			System.out.println("Affectation d'une nouvelle valeur a une constante :(");
		}
	}
	
	public void dtantQue() {
		Ecriture.ecrireString(output, "\nFAIRE");
		Ecriture.ecrireInt(output, Iteration.getEtiquette());
		Ecriture.ecrireStringln(output, ":");
	}
	
	public void ftantQue() {
		Ecriture.ecrireString(output, "goto FAIRE");
		Ecriture.ecrireInt(output, Iteration.getEtiquette());
		Ecriture.ecrireStringln(output, "\n");
		
		Ecriture.ecrireString(output, "\nFAIT");
		Ecriture.ecrireInt(output, Iteration.getEtiquette());
		Ecriture.ecrireStringln(output, ":");
	}
	public void condtantQue() {
		Ecriture.ecrireStringln(output, "iffaux FAIT" + Iteration.getEtiquette());
	}
	
	/*
	 * Fonctions de conditionelles 
	 */

	public void lireAlors(){
		Ecriture.ecrireStringln(output, "iffaux SINON" + Yaka.conditionnelle.nouvelleCond());
	}
	
	public void lireSinon(){
		Ecriture.ecrireStringln(output, "goto FSI" + Yaka.conditionnelle.getCondCourante());
		Ecriture.ecrireStringln(output, "\nSINON" + Yaka.conditionnelle.getCondCourante()+" :");
	}
	
	public void lireFSi(){
		Ecriture.ecrireStringln(output, "\nFSI" + Yaka.conditionnelle.finitCond()+" :");
	}
	
	/*
	 * Fonctions de ... Fonctions 
	 */
	public void lireDebutFonc(String nomFonc) {
		Ecriture.ecrireStringln(output, "");
		Ecriture.ecrireStringln(output, nomFonc+":");
		Ecriture.ecrireStringln(output, "ouvbloc "+(Yaka.tabIdent.compteVariables()-((nomFonc != "debut")?Yaka.tabIdent.getNbParam(nomFonc):0))*2);
	}
	public void lireFinFonc(int nbParam) {
		Ecriture.ecrireStringln(output, "fermebloc "+nbParam*2);
	}
	public void lireRetourne(int nbParam) {
		Ecriture.ecrireStringln(output, "ireturn "+(nbParam*2+4));
	}
	public void lireAppelDebut(){
		Ecriture.ecrireStringln(output, "reserveRetour");
	}
	public void lireAppelFin(){
		String nom = Yaka.fonction.depilerFonct();
		Ecriture.ecrireStringln(output, "call "+nom);
	}
}