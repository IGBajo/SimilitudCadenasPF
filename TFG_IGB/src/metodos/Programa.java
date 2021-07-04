package metodos;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import util.DatosAplicacion;
import util.PersonaFisica;
import util.ResultadoAlgoritmo;
import util.AnalizarRegistro;
import util.CasuisticasError;

public class Programa {

	
	private DatosAplicacion datos;

	//private List<AnalizarRegistro> Registros;
	
	private HashSet<AnalizarRegistro> Registros;
	
	private HashSet<String> Resultados;

	
	public Programa(DatosAplicacion _datos, JProgressBar _progressBar){
		inicializar(_datos);
		if (datos.getNormalizar()) {
			normalizar_datos();
		}
		calcular(_progressBar);
	}
	
	private void inicializar(DatosAplicacion _datos) {

		datos= new DatosAplicacion();
		
		Registros = new HashSet<AnalizarRegistro>();
		
		Resultados = new HashSet<String>();
		

		//cargar datos
		setDatos(_datos);
		
	}
	

	private void normalizar_datos() {
		
		// salto la primera columna por ser boolean y la ultima por hacer referencia al error
		for (int j=1;j<datos.getTOrigen().getColumnCount();j++) {
			for (int i=0;i<datos.getTOrigen().getRowCount();i++) {
				
				datos.getTOrigen().setValueAt(normalizar_cadena(datos.getTOrigen().getValueAt(i, j)),i,j);

			}
		}
	}
	
	private String normalizar_cadena(Object objeto) {
		
		String cOrigen=objeto.toString();

		
		cOrigen = cOrigen.replace('ñ', '\001');
		cOrigen = cOrigen.replace('Ñ', '\002');
		cOrigen = cOrigen.replace('-',' ');
		cOrigen = Normalizer.normalize(cOrigen, Normalizer.Form.NFD);
		cOrigen = cOrigen.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    //Volvemos las ñ a la cadena
		cOrigen = cOrigen.replace('\001', 'ñ');
		cOrigen = cOrigen.replace('\002', 'Ñ');
		
		cOrigen.toLowerCase();

		//QUitar blancos cuando haya mas de uno 
		cOrigen = cOrigen.replace("       "," ");
		cOrigen = cOrigen.replace("      "," ");
		cOrigen = cOrigen.replace("     "," ");
		cOrigen = cOrigen.replace("    "," ");
		cOrigen = cOrigen.replace("   "," ");
		cOrigen = cOrigen.replace("  "," ");
		
		cOrigen = cOrigen.replaceAll("[-+.^:,(ºª,)?]","");
		

		return cOrigen;
	}
	
	
	private void calcular(JProgressBar _progressBar) {
		
		switch (datos.getTipoRegistros()) {
        case "T":
            calcularTodosRESULTADOS(_progressBar);
            //calcularTodos();
            break;   
        case "I":
            calcularSoloPrimeroSeleccionado();
            break;
        case "S":
        	calcularSeleccionadosRESULTADOS();
            //calcularSeleccionados();
            break;   
        default:
        	JOptionPane.showMessageDialog(null, "No hay dato de Tipos de Registros");
            break;
		}
		
		calcularTiempos();
		calcularPorcentajeAcierto();
		
		//cargarResultados();
		
		//JOptionPane.showMessageDialog(null, "Proceso Finalizado");
		
		
	}
	
    public DatosAplicacion getDatos() {
        return datos;
    }

    public void setDatos(DatosAplicacion _datos) {
    	datos = _datos;
    }

    
    public void setRegistros(HashSet<AnalizarRegistro> _registros) {
    	Registros = _registros;
    }
    
    public HashSet<AnalizarRegistro> getRegistros(){
    	return Registros;
    }
    
    public HashSet<String> getResultados(){
    	return Resultados;
    }
	
	private void calcularTodos() {
		
		//for (int i=0; i<datos.getTOrigen().getRowCount(); i++){
		for (int i=0; i<1000; i++){
			
			if (!((datos.getSoloErroneos()) && !(personaConError(i)))) {
				Registros.add(calcularUnRegistro(i));
			}
		}
	}
	
	private void calcularTodosRESULTADOS(JProgressBar _progressBar) {
		
		int proceso;
		int proceso_anterior;
		
		int total_registros=datos.getTOrigen().getRowCount();
		_progressBar.setValue(0);
		proceso_anterior=0;
		for (int i=0; i<total_registros; i++){

			proceso=(int)((i*100)/total_registros);
			if (proceso>proceso_anterior) {
				_progressBar.setValue(proceso);
				_progressBar.repaint();
				proceso_anterior=proceso;
			}
			
			if (!((datos.getSoloErroneos()) && !(personaConError(i)))) {
				calcularUnRegistroRESULTADO(i);
			}
		}
	}
	
	private void calcularSoloPrimeroSeleccionado() {
		
		int i=1;
		AnalizarRegistro registro;
		boolean encontrado=false;
		while (i<datos.getTOrigen().getRowCount() && !encontrado) {
			if (datos.getTOrigen().getValueAt(i,0).toString()=="true") {
				encontrado=true;
			}else{
				i+=1;	
			}
		}
		
		if (encontrado) {
			registro= new AnalizarRegistro();
			registro=calcularUnRegistro(i);
			if (registro!=null) {
				Registros.add(registro);
			}
		}else {
			// si no encuentro marcado, cojo el primero sin marcar
			registro= new AnalizarRegistro();
			registro=calcularUnRegistro(0);
			if (registro!=null) {
				Registros.add(registro);
			}
		}

	}
	
	private void calcularSeleccionados() {
		
		AnalizarRegistro registro;
		
		for (int i=0; i<datos.getTOrigen().getRowCount(); i++){
			if (datos.getTOrigen().getValueAt(i,0).toString()=="true") {
				registro= new AnalizarRegistro();
				registro=calcularUnRegistro(i);
				if (registro!=null) {
					Registros.add(registro);
				}
				registro=null;
			}
		}
	}
	
	private void calcularSeleccionadosRESULTADOS() {
		

		
		for (int i=0; i<datos.getTOrigen().getRowCount(); i++){
			if (datos.getTOrigen().getValueAt(i,0).toString()=="true") {
				calcularUnRegistroRESULTADO(i);
			}
		}
	}
	
	
	private AnalizarRegistro calcularUnRegistro(int index) {
		
		
		PersonaFisica p1 = new PersonaFisica();
		PersonaFisica p2 = new PersonaFisica();
		PersonaFisica p3 = new PersonaFisica();
		ResultadoAlgoritmo algoritmo_aux;
		
		AnalizarRegistro registro_aux;
		
		Boolean analizarRegistro=true;
		String e="";
		
		//CalculoSimilitud calculo;
		
		//List<CalculoSimilitud> ResultadosSimilitudes = new ArrayList<CalculoSimilitud>();

		//cargamos la persona a analizar
		p1=cargarPersonaFisica(index);
		
		// BUSCAMOS SI LA PERSONA SE ANALIZA O NO SEGUN LO QUE SE HAYA SELECCIONADO EN SOLO_REGISTROS_CON_ERRORES
		//if ((datos.getSoloErroneos()) && !(personaConErrorID(p1.getId()))) {
		//		analizarRegistro=false;
		//}
		
		if (analizarRegistro) {

			registro_aux = new AnalizarRegistro();
			
			registro_aux.setPersonaAnalizada(p1);
			//cargamos la persona que se sabe que conincide con ella
			p3=cargarPersonaFisicaComprobada(p1.getId());
			registro_aux.setPersonaComprobada(p3);
			//cargamos la condicion
			registro_aux.setCondicion(datos.getTipoCondicion());
			//cargamos la casuistica de error que tiene el registro de la persona analizada
			
			e=cargarError(registro_aux.getPersonaAnalizada().getId());
			
			if ("".equals(e)) { //si no viene dato en la tabla vemos si somos capaces de averiguarlo
				
				CasuisticasError casuistica = new CasuisticasError(p1, p3);
				e=casuistica.dameError_apriori();
			}
			registro_aux.setError(e);
	
			//cargamos datos adicionales
			registro_aux.setDatosAdicionales(datos.getListaSecundarios());
			//cargamos la lista de algoritmos
			
			
			for (int j=0; j<datos.getListaAlgoritmos().size(); j++ ) {
				
				algoritmo_aux=new ResultadoAlgoritmo(datos.getListaAlgoritmos().get(j), datos.getTipoCondicion(), datos.getListaSecundarios());

				//algoritmo_aux.inicializar(datos.getListaAlgoritmos().get(j), datos.getTipoCondicion(), datos.getListaSecundarios()); // nombre del algoritmo y Condicion
				
				for (int i=0; i<datos.getTOrigen().getRowCount(); i++ ) {
					
					if (i!=index) {
						//para no comprobar consigo mismo
						
						p2=cargarPersonaFisica(i);
						
						// solo compruebo contra registros correctos
						//if (personaCorrecta(p2.getId())) {
							try {
								algoritmo_aux.calcularSimilitudes( p1,  p2);
							}catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Error bucle del calculo. Error: "+ex);
							}
						//}
					}
				}
				algoritmo_aux.calcularCandidatoYTiempo(p3.getId());
				
				registro_aux.getAlgoritmos().add(algoritmo_aux);
				
				algoritmo_aux=null;

			}
			
			//p1=null;
			//p2=null;
			//algoritmo_aux=null;
			try {
				return registro_aux;
			}finally {
				registro_aux=null;
			}
			
			
		}else {
			
			return null;
			
		}
	}
	
	private void calcularUnRegistroRESULTADO(int index) {
		
		
		PersonaFisica p1 = new PersonaFisica();
		PersonaFisica p2 = new PersonaFisica();
		PersonaFisica p3 = new PersonaFisica();
		ResultadoAlgoritmo algoritmo_aux;
		

		List<ResultadoAlgoritmo> algoritmos = new ArrayList<ResultadoAlgoritmo>();

		String e="";
		
		p1=cargarPersonaFisica(index);
		
		p3=cargarPersonaFisicaComprobada(p1.getId());
		
		if (p3==null){
			JOptionPane.showMessageDialog(null, "Error bucle del calculo. Error: "+p1.getId());
		}

		e=cargarError(p1.getId());
		
		if ("".equals(e)) { //si no viene dato en la tabla vemos si somos capaces de averiguarlo
			
			CasuisticasError casuistica = new CasuisticasError(p1, p3);
			e=casuistica.dameError_apriori();
			casuistica=null;
		}

		//cargamos la lista de algoritmos
		
		if (p1!=null && p3!=null) {
			

			for (int j=0; j<datos.getListaAlgoritmos().size(); j++ ) {
				
				algoritmo_aux=new ResultadoAlgoritmo(datos.getListaAlgoritmos().get(j), datos.getTipoCondicion(), datos.getListaSecundarios());
				
				for (int i=0; i<datos.getTOrigen().getRowCount(); i++ ) {
					
					if (i!=index) {
						//para no comprobar consigo mismo
						
						p2=cargarPersonaFisica(i);
						
						try {
							algoritmo_aux.calcularSimilitudes(p1,p2);
						}catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Error bucle del calculo. Error: "+ex);
						}
					}
				}
				algoritmo_aux.calcularCandidatoYTiempo(p3.getId());
				
				algoritmos.add(algoritmo_aux);
				
				algoritmo_aux=null;
	
			}
	
			//cargarUnResultado(registro_aux);
			String objeto_registro="";
			
			try{
				
		  		objeto_registro=""+p1.getId();
		  		objeto_registro=objeto_registro+";"+p1.getApellido1()+" "+p1.getApellido2()+", "+p1.getNombre();
		  		objeto_registro=objeto_registro+";"+p1.getFecNac();
		  		objeto_registro=objeto_registro+";"+p3.getId();
		  		objeto_registro=objeto_registro+";"+e;
		  		
		  		for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
		  			
		  				if (p3.getId()==algoritmos.get(i).getIdCandidatoPropuesto()) {
		  					objeto_registro=objeto_registro+";"+"1";
		  				}else {
		  					objeto_registro=objeto_registro+";"+"0";
		  				}
		  				objeto_registro=objeto_registro+";"+algoritmos.get(i).getTiempo();
		
		  		}
		  		
		  		Resultados.add(objeto_registro);
		  		
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error bucle del calculo. ID:"+p1.getId()+" Error: "+ex);
			}
		
		}
  		algoritmos=null;
			
	}
	
	private void cargarUnResultado(AnalizarRegistro reg) {
		
		String objeto_registro="";
		
  		objeto_registro=""+reg.getPersonaAnalizada().getId();
  		objeto_registro=objeto_registro+";"+reg.getPersonaAnalizada().getApellido1()+" "+reg.getPersonaAnalizada().getApellido2()+", "+reg.getPersonaAnalizada().getNombre();
  		objeto_registro=objeto_registro+";"+reg.getPersonaAnalizada().getFecNac();
  		objeto_registro=objeto_registro+";"+reg.getPersonaComprobada().getId();
  		objeto_registro=objeto_registro+";"+reg.getError();
  		
  		for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
  			
  				if (reg.getPersonaComprobada().getId()==reg.getAlgoritmos().get(i).getIdCandidatoPropuesto()) {
  					objeto_registro=objeto_registro+";"+"1";
  				}else {
  					objeto_registro="0";
  				}
  				objeto_registro=objeto_registro+";"+reg.getAlgoritmos().get(i).getTiempo();

  		}
  		
  		Resultados.add(objeto_registro);

	}
	
	
	
	/*private ResultadoPersona calcularUnCaso(int registro) {
		
		ResultadoPersona rp= new ResultadoPersona();
		
		PersonaFisica p1 = new PersonaFisica();
		PersonaFisica p2 = new PersonaFisica();
		
		CalculoSimilitud calculo;
		
		List<CalculoSimilitud> ResultadosDistancias = new ArrayList<CalculoSimilitud>();
		
		//cargamos la persona a analizar
		p1=cargarPersonaFisica(registro);
		rp.setPersonaAnalizada(p1);
		//cargamos la persona que se sabe que conincide con ella
		rp.setPersonaComprobada(cargarPersonaFisicaComprobada(rp.getPersonaAnalizada().getId()));
		//cargamos la condicion
		rp.setCondiciones(datos.getTipoCondicion());
		//cargamos la lista de algoritmos
		rp.setListaAlgoritmos(datos.getListaAlgoritmos());
		
		
		//calculamos la matriz de resultados 
		for (int i=0; i<datos.getTOrigen().getRowCount(); i++ ) {
			
			if (i!=registro) {
				//para no comprobar consigo mismo
				
				p2=cargarPersonaFisica(i);
				
				try {
					for (int j=0; j<datos.getListaAlgoritmos().size(); j++ ) {
						calculo=new CalculoSimilitud(p1, p2, datos.getTipoCondicion(), datos.getListaAlgoritmos().get(j));
						ResultadosDistancias.add(calculo);
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error bucle del calculo. Error: "+ex);
				}
			}
		}
		
		rp.setResultadosDistancias(ResultadosDistancias);
		
		rp.calcularCandidatos();
		
		return rp;
	}*/
	
	
	
	private PersonaFisica cargarPersonaFisicaComprobada (int id) {
		//devuelve la persona física que se ha comporbado que coincide con ID, de la tabla comprobaciones
		
		int i=0;
		int id_comprobado=0;
		boolean encontrado=false;
		
		int id_aux;
		
		while (i < datos.getTComprobacion().getRowCount() && !encontrado) {
			
			try {
				id_aux=Integer.parseInt(datos.getTComprobacion().getValueAt(i,0).toString());
				
				if (id_aux==id){
					encontrado=true;
					id_comprobado=Integer.parseInt(datos.getTComprobacion().getValueAt(i,1).toString());
				}
				i+=1;
			}catch (Exception ex) {
				i+=1;
				JOptionPane.showMessageDialog(null, "Error bucle del cargarPersonaFisicaComprobada. ID:"+id+" Error: "+ex);
			}

		}
		
		if (encontrado){
			return cargarPersonaFisicaID(id_comprobado);
		}else {
			
			i=0;
			while (i < datos.getTComprobacion().getRowCount() && !encontrado) {
				
				try {
	
					id_aux=Integer.parseInt(datos.getTComprobacion().getValueAt(i,1).toString());
					
					if (id_aux==id){
						encontrado=true;
						id_comprobado=Integer.parseInt(datos.getTComprobacion().getValueAt(i,0).toString());
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error bucle del cargarPersonaFisicaComprobada. ID:"+id+" Error: "+ex);
				}
				i+=1;
			}

			if (encontrado){
				return cargarPersonaFisicaID(id_comprobado);
			}else {
				return null;
			}
		}
	}
		
	private PersonaFisica cargarPersonaFisicaID (int id) {
	// buscar una persona fisica por su indentificados
		int i=0;
		boolean encontrado=false;
		
		while (i < datos.getTOrigen().getRowCount() && !encontrado) {
			
			try {
				if (Integer.parseInt(datos.getTOrigen().getValueAt(i,1).toString())==id){
					encontrado=true;
				}else {
					i+=1;
				}
				
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error bucle del cargarPersonaFisicaID. ID:"+id+" Error: "+ex);
			}	
			
		}
		
		if (encontrado){
			return cargarPersonaFisica(i);
		}else {
			return null;
		}
	}
	
	/*private String cargarError(int index) {
		
		String error="";
		Integer indice=index;

		
		try {
			
			error=datos.getTOrigen().getValueAt(index,11).toString();

		}catch( Exception e) {
			JOptionPane.showMessageDialog(null, "Error cargando la persona del registro: " + indice.toString());
		}
		
		if (error==null) {
			error="";
		}
		
		return error;
		
	}*/
	
	private Boolean personaConErrorID(int id) {
		//devuelve si una persona esta identificada con problemas, gracias a la TComprobacion
		
		int i=0;
		boolean encontrado=false;
		
		int id_aux;
		
		while (i < datos.getTComprobacion().getRowCount() && !encontrado) {
			
			try {
				id_aux=Integer.parseInt(datos.getTComprobacion().getValueAt(i,0).toString());
				
				if (id_aux==id){
					encontrado=true;
				}
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error bucle del personaConErrorID. ID:"+id+" Error: "+ex);
			}	
			i+=1;
		}
		
		return encontrado;
	}
	
	private Boolean personaConError(int index) {
		//devuelve si una persona esta identificada con problemas, gracias a la TComprobacion
		

		boolean encontrado=false;
		
		int i=0;
		
		
		int id=Integer.parseInt(datos.getTOrigen().getValueAt(index,1).toString());

		
		if (id>0) {
			
			while (i < datos.getTComprobacion().getRowCount() && !encontrado) {
				
				try {
					if (id==Integer.parseInt(datos.getTComprobacion().getValueAt(i,0).toString())){
						encontrado=true;
					}
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error bucle del personaConError. ID:"+id+" Error: "+ex);
				}	
				i+=1;
			}
		}
		

		
		return encontrado;
	}
	
	private Boolean personaCorrecta(int id) {
		//devuelve si una persona esta identificada como correcta, gracias a la TComprobacion
		
		int i=0;
		boolean encontrado=false;
		
		int id_aux;
		
		while (i < datos.getTComprobacion().getRowCount() && !encontrado) {
			
			try {
			
				id_aux=Integer.parseInt(datos.getTComprobacion().getValueAt(i,1).toString());
				
				if (id_aux==id){
					encontrado=true;
				}
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error bucle del personaCorrecta. ID:"+id+" Error: "+ex);
			}	
			i+=1;
		}
		
		return encontrado;
	}
	
	
	private String cargarError (int id) {
		//devuelve la persona física que se ha comporbado que coincide con ID, de la tabla comprobaciones
		
		int i=0;
		String error="";
		boolean encontrado=false;
		
		int id_aux;
		
		while (i < datos.getTComprobacion().getRowCount() && !encontrado) {
			
			try {
				
				id_aux=Integer.parseInt(datos.getTComprobacion().getValueAt(i,0).toString());
				
				if (id_aux==id){
					encontrado=true;
					error=datos.getTComprobacion().getValueAt(i,2).toString();
				}
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error bucle del cargarError. ID:"+id+" Error: "+ex);
			}
			i+=1;
		}
		
		if (error==null) {
			error="";
		}
		
		return error;
	}
	
	
	private PersonaFisica cargarPersonaFisica(int index) {
		
		PersonaFisica p = new PersonaFisica();
		Integer indice=index;

		
		try {
		
			p.setId(Integer.parseInt(datos.getTOrigen().getValueAt(index,1).toString()));
			p.setApellido1(datos.getTOrigen().getValueAt(index,2).toString());
			p.setApellido2(datos.getTOrigen().getValueAt(index,3).toString());
			p.setNombre(datos.getTOrigen().getValueAt(index,4).toString());
			p.setFecNac(datos.getTOrigen().getValueAt(index,5).toString());
			
			/*p.setDni(datos.getTOrigen().getValueAt(index,6).toString());
			p.setCip_civitas(datos.getTOrigen().getValueAt(index,7).toString());
			p.setDireccion(datos.getTOrigen().getValueAt(index,8).toString());
			
			if ((datos.getTOrigen().getValueAt(index,9)==null) || (" ".equals(datos.getTOrigen().getValueAt(index,9).toString())) || ("".equals(datos.getTOrigen().getValueAt(index,9).toString()))) {
				p.setProvincia(0);
			}else {
				p.setProvincia(Integer.parseInt(datos.getTOrigen().getValueAt(index,9).toString()));
			}
			if ((datos.getTOrigen().getValueAt(index,10)==null) || (" ".equals(datos.getTOrigen().getValueAt(index,10).toString())) || ("".equals(datos.getTOrigen().getValueAt(index,10).toString()))) {
				p.setMunicipio(0);
			}else {
				p.setMunicipio(Integer.parseInt(datos.getTOrigen().getValueAt(index,10).toString()));
			}
			
			if (p.getProvincia()!=0 && p.getMunicipio()!=0) {
				String provincia= String.format("%02d", p.getProvincia());
				String municipio= String.format("%03d", p.getMunicipio());
				p.setProvincia_municipio(provincia+municipio);
			}else {
				p.setProvincia_municipio("");
			}*/
			
		}catch( Exception e) {
			JOptionPane.showMessageDialog(null, "Error cargando la persona del registro: " + indice.toString());
		}
		
		return p;
		
	}
	
	
	  public void calcularTiempos() {
		  
			List<Double> listaTiempos = new ArrayList<Double>();
			
	    	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
	    	for (int k=0; k<datos.getListaAlgoritmos().size(); k++ ) {
	    		
	    		long tiempo=0l;
	    		
	    		for(AnalizarRegistro reg: Registros){
	    			tiempo+=reg.getAlgoritmos().get(k).getTiempo();
	    		}
	    		
	    		listaTiempos.add((double)(tiempo)/1000000000);
			}
			
    		datos.setListaAlgoritmosTiempos(listaTiempos);
	    	
	    }

	  public void calcularPorcentajeAcierto() {
		  
			List<Double> listaPorcentajeAcierto = new ArrayList<Double>();
			int num_registros;
			int aciertos;
			
	    	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
	    	for (int k=0; k<datos.getListaAlgoritmos().size(); k++ ) {
	    		
				num_registros=0;
				aciertos=0;
			
	    		for(AnalizarRegistro reg: Registros){
	    			if (reg.getPersonaComprobada().getId()==reg.getAlgoritmos().get(k).getIdCandidatoPropuesto()) {
		    			aciertos+=reg.getAlgoritmos().get(k).getAcierto();
	    			}
	    			num_registros+=1;
	    		}
	    		
	    		listaPorcentajeAcierto.add((double)(((double)aciertos/(double)num_registros)*100));
			}
			
    		datos.setListaAlgoritmosPorcentajeAcierto(listaPorcentajeAcierto);
	    	
	    }
	  
	  
	  
	  /*public void cargarResultados() {
		  
		//JTable tabla=new JTable();
		DefaultTableModel tableModel = new DefaultTableModel();
		  
		tableModel.addColumn("DATOS");
    	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
    	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
            tableModel.addColumn(datos.getListaAlgoritmos().get(i));
    	}
    	
    	Object[] obj = new Object[datos.getListaAlgoritmos().size()+1];
        obj[0] = "";
        
    	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
    		obj[i+1] =datos.getListaAlgoritmosTiempos();
    	}
    	
    	tableModel.addRow(obj);
    	
        datos.getTResultados().setModel(tableModel);
		
        datos.getTResultados().getColumn(datos.getTResultados().getColumnName(0)).setPreferredWidth(65);
    	for (int i = 1; i < tableModel.getColumnCount(); i++) { 
    		datos.getTResultados().getColumn(datos.getTResultados().getColumnName(i)).setPreferredWidth(200);
    	}
	    	
	  }*/
	
}
