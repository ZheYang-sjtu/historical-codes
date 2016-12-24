/* Last modified: 12/24/2016 by Zhe
This class represents a value-frequency couple.


Zhe YANG
Ecole Centrale de Nantes
EI2 - Promotion 2015
eIMAGE - TA (Pseudo-compression JPEG)
Vendredi 17 janvier 2014

Fichier Donnee.java
Fonction : Cette classe enregistre un tableau de int sous forme de couple de valeurs avec leur fréquence.
*/

class Donnee{
	
	
	protected int fre; /*frequency*/
	protected int val; /*value*/
	
	public Donnee(int f,int v){
		fre=f;
		val=v;
		
	}
	
	public Donnee(Donnee a){
		fre=a.getFre();
		val=a.getVal();
	}
	
	public int getFre(){
		return fre;
	}
	
	public int getVal(){
		return val;
	}
	
	public void ajouteFre(){
		fre++;
	}
	
	public void setVal(int v){
		val=v;
	}

}