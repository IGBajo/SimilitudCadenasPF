package algoritmos;

import javax.swing.JOptionPane;

public class Levenshtein {

	public String nombre="Levenshtein";
	
	public static  Double similitud;
	
    public Levenshtein() {
        super();
    }
    
    
    public boolean comprobar(String _nombre) {
    	if (nombre.equals(_nombre)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    private static int minimo(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public double calcularSimilitudLevenshtein(String str1, String str2) {
    	
    	double maximo=Math.max(str1.length(), str2.length());
    	double distancia= (double) calcularDistanciaLevenshtein(str1.toCharArray(),str2.toCharArray());
    	similitud=(1-(distancia/maximo));
    	return similitud;
    	
    }
    
    
    private static int calcularDistanciaLevenshtein(char [] str1, char [] str2) {
        int [][]distancia = new int[str1.length+1][str2.length+1];

        for(int i=0;i<=str1.length;i++){
               distancia[i][0]=i;
        }
        for(int j=0;j<=str2.length;j++){
               distancia[0][j]=j;
        }
        for(int i=1;i<=str1.length;i++){
           for(int j=1;j<=str2.length;j++){ 
                 distancia[i][j]= minimo(distancia[i-1][j]+1,
                                       distancia[i][j-1]+1,
                                       distancia[i-1][j-1]+
                                       ((str1[i-1]==str2[j-1])?0:1));
           }
        }
        
        return distancia[str1.length][str2.length];
        
   }
    
}
