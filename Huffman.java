/* Last modified: 12/24/2016 by Zhe
This code implements a Huffman tree.
Important functions are constHuffman(), which constructs a Huffman tree, and codage(), which transforms the tree into Huffman code.


Zhe YANG
Ecole Centrale de Nantes
EI2 - Promotion 2015
eIMAGE - TA (Pseudo-compression JPEG)
Vendredi 17 janvier 2014

Fichier Huffman.java
Fonction : Cette classe construit un arbre de Huffman à partir d'un tableau de Code.
           Les fonctions importantes sont constHuffman(), qui construit cet arbre et codage(), qui transforme cet arbre en code de Huffman.
*/

import java.util.*;
class Huffman{
    protected Donnee valeur;
    protected Huffman filsGauche, filsDroit; /*left son, right son*/
    static int numFeuPass=0;

    public Donnee valeur() {
        return valeur;
    }
    public boolean existeFilsGauche() {
        return filsGauche != null;
    }
    public boolean existeFilsDroit() {
        return filsDroit != null;
    }
    public Huffman filsGauche() {
        return filsGauche;
    }
    public Huffman filsDroit() {
        return filsDroit;
    }
    public void affecterValeur(Donnee c) {
        valeur = c;
    }
    public void affecterFilsGauche(Huffman g) {
        filsGauche = g;
    }
    public void affecterFilsDroit(Huffman d) {
        filsDroit = d;
    }
    public boolean feuille() {
        return (filsDroit==null && filsGauche==null);
    }
    
   
    public Huffman(Donnee val) {           /*constructors*/
        valeur = val;
        filsGauche = filsDroit = null;
    }
    public Huffman(Donnee val, Huffman g, Huffman d) {
        valeur = val;
        filsGauche = g; filsDroit = d;
    }
  
    
    
    
   
    public int numFeuille(){                /*number of sons*/
    	if(this.feuille())return 1;
    	else return this.filsDroit().numFeuille()+this.filsGauche().numFeuille();
    }
    
    
	public static Huffman constHuffman(ArrayList <Huffman> array){
     	
     	
     	if(array.size()==1){return array.get(0);}
     	
     	int m=0,n=0,temp=array.get(0).valeur().getFre();
     	for(int i=0;i<array.size();i++){
     		
     		if(array.get(i).valeur().getFre()<temp){temp=array.get(i).valeur().getFre();m=i;} /*find min frequency node (as left son).*/
     	}
     	
		
     	Huffman fG = array.remove(m); /*delete this node from Huffman tree nodes array.*/
     	
     	
     	
     	
     	temp=array.get(0).valeur().getFre(); /*repeat above process, now we want to find the node to be the right son.*/
      	for(int i=0;i<array.size();i++){
     		
     		if(array.get(i).valeur().getFre()<temp){temp=array.get(i).valeur().getFre();n=i;}
     	}
     	
      	Huffman fD = array.remove(n); 
      	
      	Huffman cao = new Huffman(new Donnee(fG.valeur().getFre()+fD.valeur().getFre(),0));
      	
      	cao.affecterFilsDroit(fD);
      	cao.affecterFilsGauche(fG);
     	array.add(cao); /*put the new node into Huffman tree nodes array.*/
     	
     	
     	return constHuffman(array);
    }
    
    
    public void codage(ArrayList <Code> codeCh){
    	
    	
    	
    	if(this.existeFilsGauche()){
    		for(int i=numFeuPass;i<numFeuPass+this.filsGauche().numFeuille();i++){
    			
    			codeCh.get(i).ajouteZero(); /*set left son to be zero*/
    			
    		}
    		this.filsGauche().codage(codeCh);}
    	
    	
    	if(this.existeFilsDroit()){
    		for(int i=numFeuPass;i<numFeuPass+this.filsDroit().numFeuille();i++){
			    codeCh.get(i).ajouteUn(); /*set right son to be one*/
		    }
		    this.filsDroit().codage(codeCh);}
    	
    	if(this.feuille()){
    		codeCh.get(numFeuPass).setVal(this.valeur().getVal());
    		
    		numFeuPass++;
    	
    	    return;}
   
    }
 
 }
