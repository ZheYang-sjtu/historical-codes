/* Last modified: 12/24/2016 by Zhe
This class represents an array of Huffman tree nodes.
The input is an int array. we want to encode this int array with Huffman code.


Zhe YANG
Ecole Centrale de Nantes
EI2 - Promotion 2015
eIMAGE - TA (Pseudo-compression JPEG)
Vendredi 17 janvier 2014

Fichier ChaineHuff.java
Fonction : Cette classe a comme attribut une chaine d'élément de Huffman.
           Il a pour paramètre un tableau de int, on obtient un ArrayList de noeuds d'un arbre de Huffman
*/

import java.util.*;

public class ChaineHuff {
	
	protected ArrayList<Huffman> chaine;
	public ChaineHuff(int [] c){
		chaine=new ArrayList<Huffman>(0);
		int i=0;
		int j=0;
		for(i=0;i<c.length;i++){
			
			for(j=0;j<chaine.size();j++){
				if(chaine.get(j).valeur().getVal()==c[i]){chaine.get(j).valeur().ajouteFre();break;} /*if a value already exists in a Huffman node, we simple add one to the node's frequency.*/
				
			}
			if(j==chaine.size()){
				
				Huffman e=new Huffman(new Donnee(1,c[i])); /*else, we create a new Huffman tree node.*/
				chaine.add(e);
				
			}
			
			
		}
		
		
		
	}
	
	public ArrayList<Huffman> getChaine(){
		return chaine;
	}
	
}