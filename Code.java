/* Last modified: 12/24/2016 by Zhe
This class registers a value (int) and its Huffman code (string).


Zhe YANG
Ecole Centrale de Nantes
EI2 - Promotion 2015
eIMAGE - TA (Pseudo-compression JPEG)
Vendredi 17 janvier 2014

Fichier Code.java
Fonction : Cette classe a pour but d'enregistrer le codage sous forme d'un couple de valeurs avec son code de Huffman.
*/

import java.io.IOException;

public class Code{
	
	protected int val;
	protected String code;
	
	public Code(){
		val=0;
		code=null;
	}
	
	public Code(int i){
		val=i;
		code=null;
	}
	
	public Code(int i,String b){
		val=i;
		code=b;
	}
	
	
	public void ajouteUn(){
		code=code.concat("1");
	}
	public void ajouteZero(){
		code=code.concat("0");
	}
	
	public String getCode(){
		return code;
	}
	public void setVal(int v){
		val=v;
	}
	public int getVal(){
		return val;
	}
	public Code(String s1,String s2) throws IOException{
		
		int i=Integer.parseInt(s1);
		val=i;
		code=s2;
		
	}
}