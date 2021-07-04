package util;

public class CasuisticasError {


	protected PersonaFisica p1;
	protected PersonaFisica p2;
	protected String error;
	protected String errorPorDefecto="OTR";
	
	  /*ER.- Tienen una errata.
		AB.- Contienen abreviaturas. 
		AP.- Diferente denominación o apodo.
		OM.- Los que tengan omisión de caracteres o palabras.
		PS.- Tienen prefijos o sufijos
		DO.- desorden de las subcadenas.
		NN.- Los que no tengan ninguna de las anteriores casuísticas.
	  */
	
	  /*NID.- Solo no coinciden los iddentificadores, pero los campos son identicos 
	    ERG.- Tienen una errata.
		ABR.- Contienen abreviaturas. 
		APO.- Diferente denominación o apodo.
		OMI.- Los que tengan omisión de caracteres o palabras.
		PRE.- Tienen prefijos o sufijos
		DES.- desorden de las subcadenas.
		OTR.- Los que no tengan ninguna de las anteriores casuísticas.
	  */
	
	public CasuisticasError(PersonaFisica a1, PersonaFisica a2){
		inicializar(a1, a2, errorPorDefecto);
	}
	
	private void inicializar(PersonaFisica a1, PersonaFisica a2, String e) {
		p1=a1;
		p2=a2;
		error=e;
	}
	
	// ERG.- Los que tengan omisión de caracteres o palabras.

	private Boolean casuisticaNID_apriori() {
		
		boolean valor=false;
		
		valor=casuisticaNID();
		
		if (!valor) {
			if ( p1.getApellido1().equals(p2.getApellido1()) && p1.getApellido2().equals(p2.getApellido2()) && p1.getNombre().equals(p2.getNombre()) && p1.getFecNac().equals(p2.getFecNac()) ) {
				valor=true;
			}
		}

		return valor;
	}
	
	private Boolean casuisticaNID() {
		return false;
	}
	
	// OMI.- Los que tengan omisión de caracteres o palabras.

	private Boolean casuisticaOMI_apriori() {
		
		boolean valor=false;
		
		valor=casuisticaOMI();
		
		/*if (!valor){
			if (p1.getApellido1().length()==p2.getApellido1().length() || p1.getApellido2().length()==p2.getApellido2().length() || p1.getNombre().length()==p2.getNombre().length()) {
				return true;
			}
		}*/
		return valor;
	}
	
	private Boolean casuisticaOMI() {
		if (p1.getApellido1()==null || p1.getApellido2()==null || p1.getNombre()==null || p1.getFecNac()==null || 
			"".equals(p1.getApellido1()) || "".equals(p1.getApellido2()) || "".equals(p1.getNombre()) || 
			" ".equals(p1.getApellido1()) || " ".equals(p1.getApellido2()) || " ".equals(p1.getNombre()) ){
			  return true;
		}
		return false;
	}
	
	
	// APO.- Diferente denominación o apodo.
	
	private Boolean casuisticaAPO_apriori() {
		return casuisticaAPO();
	}
	
	private Boolean casuisticaAPO() {
		String [] prefijos = {"JOSE", "PEPE", "QUIQUE", "PACO", "CONCHA", "MANOLO", "MAITE", "MARISA", "LUPE", "CHARO", "NACHO", "PACO"};
		boolean valor=false;
		for (int i=0; i<prefijos.length; i++) {
			if (p1.getNombre().indexOf(prefijos[i])>=0) {
				valor=true;
			}
		}
		return valor;
	}
	
	
	// PRE.- Tienen prefijos o sufijos
	
	private Boolean casuisticaPRE_apriori() {
		return casuisticaPRE();
	}
	
	private Boolean casuisticaPRE() {
		
		String [] prefijos = {"Dº", "Dª", "DON ", "DOÑA ", "SRA ", "SR ", "SRA.", "SR."};
		boolean valor=false;
		for (int i=0; i<prefijos.length; i++) {
			if (p1.getApellido1().indexOf(prefijos[i])>=0 || p1.getApellido2().indexOf(prefijos[i])>=0 || p1.getNombre().indexOf(prefijos[i])>=0) {
				valor=true;
			}
		}
		
		return valor;
	}
	
	// ABR.- Contienen abreviaturas. 
	
	private Boolean casuisticaABR_apriori() {
		return casuisticaABR();
	}
	
	
	private Boolean casuisticaABR() {
		
		String [] abreviaturas = {"ª", "º", ".", "/", "FDEZ", "GLEZ", "GCIA", "PZ"};
		boolean valor=false;
		for (int i=0; i<abreviaturas.length; i++) {
			if (p1.getApellido1().indexOf(abreviaturas[i])>=0 || p1.getApellido2().indexOf(abreviaturas[i])>=0 || p1.getNombre().indexOf(abreviaturas[i])>=0) {
				valor=true;
			}
		}
		
		//Comprobamos, si la longitud es igual a 1 en el nombre, sera porque es una abreviatura, por ejemplo M = Maria
		
		if (!valor && p1.getNombre().length()==1) {
			valor=true;
		}
		return valor;
	}
	
	
	// DES.- desorden de las subcadenas.
	
	private Boolean casuisticaDES_apriori() {
		
		boolean valor=false;
		
		valor=casuisticaDES();
		
		if (!valor) {
			if ( p1.getApellido1().equals(p2.getApellido2()) || p1.getApellido1().equals(p2.getNombre()) || 
				 p1.getApellido2().equals(p2.getApellido1()) || p1.getApellido2().equals(p2.getNombre()) ||
				 p1.getNombre().equals(p2.getApellido1()) || p1.getNombre().equals(p2.getApellido1()) ) {
				valor=true;
			}
		}
		
		return valor;
	}
	
	private Boolean casuisticaDES() {
		return false;
	}
	
	
	// ERG.- Tienen una errata gramatical
	
	private Boolean casuisticaERG_apriori() {
		
		boolean valor=false;
		
		valor=casuisticaERG();
		
		return valor;
	}
	

	private Boolean casuisticaERG() {
		return false;
	}
	
	
	

	public String dameError_apriori() {
    	
    	if (errorPorDefecto.equals(error) && casuisticaNID_apriori()) {
    		error="NID";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaOMI_apriori()) {
    		error="OMI";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaAPO_apriori()) {
    		error="APO";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaPRE_apriori()) {
    		error="PRE";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaABR_apriori()){
    		error="ABR";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaDES_apriori()) {
    		error="DES";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaERG_apriori()) {
    		error="ERG";
    	}

        return error;
    }
	
	public String dameError() {
    	
    	if (errorPorDefecto.equals(error) && casuisticaNID()) {
    		error="NID";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaOMI()) {
    		error="OMI";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaAPO()) {
    		error="APO";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaPRE()) {
    		error="PRE";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaABR()){
    		error="ABR";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaDES()) {
    		error="DES";
    	}
    	if (errorPorDefecto.equals(error) && casuisticaERG()) {
    		error="ERG";
    	}

        return error;
    }

}

