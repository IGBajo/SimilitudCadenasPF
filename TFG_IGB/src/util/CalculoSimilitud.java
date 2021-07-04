package util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

//import algoritmos.Jaccard;
//import algoritmos.Jaro;
//import algoritmos.JaroWinkler;
//import algoritmos.Levenshtein;
//import algoritmos.SmithWaterman;


import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Jaro;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWaterman;
import uk.ac.shef.wit.simmetrics.similaritymetrics.NeedlemanWunch;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Soundex;
import uk.ac.shef.wit.simmetrics.similaritymetrics.BlockDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.MongeElkan;

import uk.ac.shef.wit.simmetrics.similaritymetrics.OverlapCoefficient;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;

//import uk.ac.shef.wit.simmetrics.similaritymetrics.*;

public class CalculoSimilitud {

	
	//protected List<Integer> distancias;
	
	protected HashSet<Double> similitudes;
	
	
	protected int identificador1;
	protected int identificador2;
	
	protected List<String> cadenas1=new ArrayList<String>();
	protected List<String> cadenas2=new ArrayList<String>();
	
	protected long tiempo;
	
	//protected double distancia;
	protected double similitud;
	
	protected String condicion;
	
	protected String algoritmo;
	
	public CalculoSimilitud(PersonaFisica p1, PersonaFisica p2,String cond, String alg, List<String> secundarios){
		inicializar(p1, p2,cond, alg, secundarios);
	}
	
	public CalculoSimilitud(){
	}
	
	public void cargarDatos(PersonaFisica p1, PersonaFisica p2,String cond, String alg, List<String> secundarios) {
		inicializar(p1, p2,cond, alg, secundarios);
	}
	private void inicializar(PersonaFisica p1, PersonaFisica p2,String cond, String alg, List<String> secundarios) {
		
		String campo1;
		String campo2;

		condicion=cond;
		algoritmo=alg;
		//distancias = new ArrayList<Integer>();
		similitudes = new HashSet<Double>();
		
		identificador1=p1.getId();
		identificador2=p2.getId();

		cadenas1.clear();
		cadenas2.clear();
		
		if ("CON".equals(cond)) {
			campo1=p1.getApellido1()+p1.getApellido2()+p1.getNombre()+p1.getFecNac();
			campo2=p2.getApellido1()+p2.getApellido2()+p2.getNombre()+p2.getFecNac();
			cadenas1.add(campo1);
			cadenas2.add(campo2);
			
		}else if("SEP".equals(cond)) {
			
			cadenas1.add(p1.getApellido1());
			cadenas1.add(p1.getApellido2());
			cadenas1.add(p1.getNombre());
			cadenas1.add(p1.getFecNac());
			
			cadenas2.add(p2.getApellido1());
			cadenas2.add(p2.getApellido2());
			cadenas2.add(p2.getNombre());
			cadenas2.add(p2.getFecNac());
			
		}else if("NAA".equals(cond)) {
			campo1=p1.getNombre()+" "+p1.getApellido1()+" "+p1.getApellido2()+" "+p1.getFecNac();
			campo2=p2.getNombre()+" "+p2.getApellido1()+" "+p2.getApellido2()+" "+p2.getFecNac();
			cadenas1.add(campo1);
			cadenas2.add(campo2);
			
		}else if("AAN".equals(cond)) {
			campo1=p1.getApellido1()+" "+p1.getApellido2()+" "+p1.getNombre()+" "+p1.getFecNac();
			campo2=p2.getApellido1()+" "+p2.getApellido2()+" "+p2.getNombre()+" "+p2.getFecNac();
			cadenas1.add(campo1);
			cadenas2.add(campo2);
		}
		
		/*for(String str : secundarios)
		{		    
			switch (str) {
			
	        	case "DNI NIE":
	        		
	        		if (p1.getDni()==null) { cadenas1.add("");
	        		}else { cadenas1.add(p1.getDni()); }
	        		
	        		if (p2.getDni()==null) { cadenas2.add("");
	        		}else { cadenas2.add(p2.getDni()); }
	        		
	        		break;
	        		
	        	case "Dirección":
	        		
	        		if (p1.getDireccion()==null) { cadenas1.add("");
	        		}else { cadenas1.add(p1.getDireccion()); }
	        		
	        		if (p2.getDireccion()==null) { cadenas2.add("");
	        		}else { cadenas2.add(p2.getDireccion()); }
	        		
	        		break;
	        		
	        	case "Tarjeta Sanitaria":
	        		
	        		if (p1.getCip_civitas()==null) { cadenas1.add("");
	        		}else { cadenas1.add(p1.getCip_civitas()); }
	        		
	        		if (p2.getCip_civitas()==null) { cadenas2.add("");
	        		}else { cadenas2.add(p2.getCip_civitas()); }
	        		
	        		break;
	        		
	        	case "Provincia + Municipio":
	        		
	        		if (p1.getProvincia_municipio()==null) { cadenas1.add("") ;
	        		}else { cadenas1.add(p1.getProvincia_municipio()); }
	        		
	        		if (p2.getProvincia_municipio()==null) { cadenas2.add("") ;
	        		}else { cadenas2.add(p2.getProvincia_municipio()); }

	        		break;
	        		
		        default:
		            break;
				}
		}*/
			
		calcular();
		
		similitudMedia();

	}
	
	
	private void calcular() { 
		
		long tiempoInicial;
		long tiempoFinal;
		
    	tiempoInicial = System.nanoTime();
		switch (algoritmo) {
		
	        case "Levenshtein":
	        	Levenshtein algLevenshtein = new Levenshtein();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algLevenshtein.getSimilarity(cadenas1.get(j), cadenas2.get(j)));
	        		//similitudes.add(algLevenshtein.calcularSimilitudLevenshtein(cadenas1.get(j), cadenas2.get(j)));
				}
	        	algLevenshtein=null;
	        	break;
	        	
	        case "Jaro":
	        	Jaro algJaro = new Jaro();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algJaro.getSimilarity(cadenas1.get(j), cadenas2.get(j)));

				}
	        	algJaro=null;
	        	break;
	        
	        case "Jaro-Winkler":
	        	JaroWinkler algJaroWinkler = new JaroWinkler();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algJaroWinkler.getSimilarity(cadenas1.get(j), cadenas2.get(j)));

				}
	        	algJaroWinkler=null;
	        	break;
	        
	        case "Jaccard":
	        	JaccardSimilarity algJaccard = new JaccardSimilarity();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algJaccard.getSimilarity(cadenas1.get(j), cadenas2.get(j)));

				}
	        	algJaccard=null;
	        	break;
	        	
	        case "Smith-Waterman":
	        	SmithWaterman algSmithWaterman = new SmithWaterman();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algSmithWaterman.getSimilarity(cadenas1.get(j), cadenas2.get(j)));
				}
	        	algSmithWaterman=null;
	        	break;
	        
	        case "Needleman-Wunsch":
	        	NeedlemanWunch algNeedlemanWunch = new NeedlemanWunch();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algNeedlemanWunch.getSimilarity(cadenas1.get(j), cadenas2.get(j)));

				}
	        	algNeedlemanWunch=null;
	        	break;
	        
	        case "Soundex":
	        	Soundex algSoundexESP = new Soundex();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		
	        		
	        		//String c1=cadenaSoundexESP(cadenas1.get(j));
	        		//String c2=cadenaSoundexESP(cadenas2.get(j));
	        			
	        		//similitudes.add((double) algSoundexESP.getSimilarity(c1, c2));
	        		similitudes.add((double) algSoundexESP.getSimilarity(cadenas1.get(j), cadenas2.get(j)));

				}
	        	algSoundexESP=null;
	        	break;	
	        
	        case "BlockDistance":
	        	BlockDistance algBlockDistance = new BlockDistance();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algBlockDistance.getSimilarity(cadenas1.get(j), cadenas2.get(j)));

				}
	        	algBlockDistance=null;
	        	break;	
	        
	        case "Monge-Elkan":
	        	MongeElkan algMongeElkan = new MongeElkan();

	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algMongeElkan.getSimilarity(cadenas1.get(j), cadenas2.get(j)));
				}
	        	algMongeElkan=null;
	        	break;
	        	
	        case "Solapamiento":
	        	OverlapCoefficient algSolapamiento = new OverlapCoefficient();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algSolapamiento.getSimilarity(cadenas1.get(j), cadenas2.get(j)));
				}
	        	algSolapamiento=null;
	        	break;	
	        	
	        case "Coseno":
	        	CosineSimilarity algCosine = new CosineSimilarity();
	        	for (int j=0; j<cadenas1.size(); j++) {
	        		similitudes.add((double) algCosine.getSimilarity(cadenas1.get(j), cadenas2.get(j)));
				}
	        	algCosine=null;
	        	break;	
	        	
	        default:
	        	JOptionPane.showMessageDialog(null, "Algoritmo no encontrado");
	            break;
			}

    		tiempoFinal = System.nanoTime();
    		tiempo=tiempoFinal-tiempoInicial;
	}
	
	
	private void similitudMedia() { 
		
		double suma=0;
		int contar=0;
		for(Double sim: similitudes){
			suma+=sim;
			contar+=1;
		}
		
		similitud=suma/contar;
	}
	
	
	
	
	private String cadenaSoundexESP(Object objeto) {
		
		String cOrigen=objeto.toString();

		cOrigen = cOrigen.replace('-',' ');
		cOrigen = Normalizer.normalize(cOrigen, Normalizer.Form.NFD);
		cOrigen = cOrigen.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    //Volvemos las ñ a la cadena

		
		cOrigen.toLowerCase();
		
		cOrigen = cOrigen.replaceAll("[-+.^:,(ºª,)? ]",""); // OJO QUE QUITO TAMBIEN LOS BLANCOS
		
		cOrigen = cOrigen.replaceAll("[0-9]", "");

		// casos foneticos del castellano
		
		cOrigen = cOrigen.replace("B","V");
		cOrigen = cOrigen.replace("X","S");
		cOrigen = cOrigen.replace("Z","S");
		cOrigen = cOrigen.replace("Ñ","N");
		
		cOrigen = cOrigen.replace("CH", "V");
		cOrigen = cOrigen.replace("QU", "K");
		cOrigen = cOrigen.replace("LL", "J");
		cOrigen = cOrigen.replace("YA", "J");
		cOrigen = cOrigen.replace("YO", "J");
		cOrigen = cOrigen.replace("YE", "J");
		cOrigen = cOrigen.replace("YI", "J");
		cOrigen = cOrigen.replace("YU", "J");
		
		cOrigen = cOrigen.replace("CE", "S");
		cOrigen = cOrigen.replace("CI", "S");
		
		cOrigen = cOrigen.replace("GE", "JE");
		cOrigen = cOrigen.replace("GI", "JI");
		cOrigen = cOrigen.replace("NY", "N");
		cOrigen = cOrigen.replace("NH", "N");
		
		cOrigen = cOrigen.replace("CA", "KA");
		cOrigen = cOrigen.replace("CO", "KO");
		cOrigen = cOrigen.replace("CU", "KU");
		

		
		
		
		return cOrigen;
	}
	
	
}
