package gui;

import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import util.AnalizarRegistro;
import util.CargarXLS;
import util.DatosAplicacion;
import util.RenderizarTabla;
import util.exportarExcel;

import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class GuiAnalizar extends JInternalFrame implements InternalFrameListener, ActionListener {

	
	private static final long serialVersionUID = 1L;
	public static String ana;
	
	private static DatosAplicacion datos;
	private static HashSet<AnalizarRegistro> registros;
	private static HashSet<String> resultados;
	private JTable tCasuisticas_Aciertos;
	private JScrollPane scrollPaneTCasuisticas_Aciertos;
	private JLabel eTipo;
	private JLabel eMetrica;
	private JScrollPane scrollPaneTTotales;
	private JTable tTotales;
	private JScrollPane scrollPaneTCasuisticas_Tiempos;
	private JLabel lblTotales;
	private JTable tCasuisticas_Tiempos;
	private JButton btnBotonExportar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GuiEjecutar frame = new GuiEjecutar();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws PropertyVetoException 
	 */
	public GuiAnalizar() {
		
		datos= new DatosAplicacion();
		registros= new HashSet<AnalizarRegistro>();
		resultados= new HashSet<String>();
		
		addInternalFrameListener(this);
		
		ana="ana";
		setTitle("Analizar"); 
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		pack();
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(true);
		setBounds(100, 100, 1586, 900);
		getContentPane().setLayout(null);
		
		scrollPaneTCasuisticas_Aciertos = new JScrollPane();
		scrollPaneTCasuisticas_Aciertos.setBounds(55, 94, 1488, 205);
		getContentPane().add(scrollPaneTCasuisticas_Aciertos);
		
		tCasuisticas_Aciertos = new JTable();
		scrollPaneTCasuisticas_Aciertos.setViewportView(tCasuisticas_Aciertos);
		tCasuisticas_Aciertos.setName("tListado");
		
		tCasuisticas_Aciertos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tCasuisticas_Aciertos.setCellSelectionEnabled(true);
		tCasuisticas_Aciertos.setColumnSelectionAllowed(true);
		tCasuisticas_Aciertos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTCasuisticas_Tiempos = new JScrollPane();
		scrollPaneTCasuisticas_Tiempos.setBounds(55, 360, 1488, 205);
		getContentPane().add(scrollPaneTCasuisticas_Tiempos);
		
		tCasuisticas_Tiempos = new JTable();
		scrollPaneTCasuisticas_Tiempos.setViewportView(tCasuisticas_Tiempos);
		tCasuisticas_Tiempos.setName("tCasuisticas_Tiempos");
		
		tCasuisticas_Tiempos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tCasuisticas_Tiempos.setCellSelectionEnabled(true);
		tCasuisticas_Tiempos.setColumnSelectionAllowed(true);
		tCasuisticas_Tiempos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTTotales = new JScrollPane();
		scrollPaneTTotales.setBounds(55, 656, 1488, 84);
		getContentPane().add(scrollPaneTTotales);
		
		tTotales = new JTable();
		scrollPaneTTotales.setViewportView(tTotales);
		tTotales.setName("tTotales");
		
		tTotales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tTotales.setCellSelectionEnabled(true);
		tTotales.setColumnSelectionAllowed(true);
		tTotales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		eTipo = new JLabel("TIEMPOS");
		eTipo.setBounds(577, 332, 171, 16);
		getContentPane().add(eTipo);
		
		eMetrica = new JLabel("PORCENTAJE DE ACIERTOS");
		eMetrica.setBounds(563, 54, 397, 28);
		getContentPane().add(eMetrica);
		
		
		lblTotales = new JLabel("TOTALES");
		lblTotales.setBounds(563, 616, 273, 28);
		getContentPane().add(lblTotales);
		
		btnBotonExportar = new JButton("Exportar a Excel");
		btnBotonExportar.addActionListener(this);
		btnBotonExportar.setBounds(1398, 303, 145, 29);
		getContentPane().add(btnBotonExportar);
		
		inicializaCombos();

	}


	private void inicializaCombos() {
		
	
	}
	
	
	public void setDatos(DatosAplicacion d) {
		datos=d;
		//calcularTablas();
	}
	
	public void setRegistros(HashSet<AnalizarRegistro> r) {
		registros=r;
		//calcularTablas();
	}
	
	public void setResultados(HashSet<String> r) {
		resultados=r;
		//calcularTablas();
	}
	
	public void calcularTablas() {

		//tabla TOTALES
		
		DefaultTableModel modeloTotales = new DefaultTableModel();
		
		//modeloTotales=cargarResultadosTotales();
		modeloTotales=cargarResultadosTotalesCADENAS();
		
		tTotales.setModel(modeloTotales);
		//para ponerle tamaño a las columnas
		tTotales.getColumn(tTotales.getColumnName(0)).setPreferredWidth(100);
	  	for (int k = 1; k < tTotales.getColumnCount(); k++) { 
	  		tTotales.getColumn(tTotales.getColumnName(k)).setPreferredWidth(130);
	  	}
	  	
		modeloTotales.fireTableDataChanged();
		
		//renderizar la tabla
		tTotales.setDefaultRenderer(Object.class, new RenderizarTabla());
		tTotales.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		
		//tabla CASUISTICAS ACIERTOS

		DefaultTableModel modeloCasuistica_Aciertos = new DefaultTableModel();
		
		modeloCasuistica_Aciertos=cargarResultadosCasuisticasCADENAS('A');
		//modeloCasuistica_Aciertos=cargarResultadosCasuisticas('A');
		
		tCasuisticas_Aciertos.setModel(modeloCasuistica_Aciertos);
		//ponerle tamaño a las columnas
		tCasuisticas_Aciertos.getColumn(tCasuisticas_Aciertos.getColumnName(0)).setPreferredWidth(100);
	  	for (int k = 1; k < tCasuisticas_Aciertos.getColumnCount(); k++) { 
	  		tCasuisticas_Aciertos.getColumn(tCasuisticas_Aciertos.getColumnName(k)).setPreferredWidth(130);
	  	}
	  	
	  	modeloCasuistica_Aciertos.fireTableDataChanged();
	  	
	    //renderizar la tabla
	  	tCasuisticas_Aciertos.setDefaultRenderer(Object.class, new RenderizarTabla());
	  	tCasuisticas_Aciertos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	  	

		//tabla CASUISTICAS TIEMPOS

		DefaultTableModel modeloCasuistica_Tiempos = new DefaultTableModel();
		
		//modeloCasuistica_Tiempos=cargarResultadosCasuisticas('T');
		modeloCasuistica_Tiempos=cargarResultadosCasuisticasCADENAS('T');
		
		tCasuisticas_Tiempos.setModel(modeloCasuistica_Tiempos);
		//ponerle tamaño a las columnas
		tCasuisticas_Tiempos.getColumn(tCasuisticas_Tiempos.getColumnName(0)).setPreferredWidth(100);
	  	for (int k = 1; k < tCasuisticas_Tiempos.getColumnCount(); k++) { 
	  		tCasuisticas_Tiempos.getColumn(tCasuisticas_Tiempos.getColumnName(k)).setPreferredWidth(130);
	  	}
	  	
	  	modeloCasuistica_Tiempos.fireTableDataChanged();
	  	
	  	//renderizar la tabla
	  	tCasuisticas_Tiempos.setDefaultRenderer(Object.class, new RenderizarTabla());
	  	tCasuisticas_Tiempos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	  	
	}
	
	
	  public DefaultTableModel cargarResultadosCasuisticas(char Metrica) {
		  
			DefaultTableModel tableModel = new DefaultTableModel();
			List<String> casuisticas = new ArrayList<String>();
			casuisticas.add("NID");
			casuisticas.add("ERG");
			casuisticas.add("ABR");
			casuisticas.add("APO");
			casuisticas.add("OMI");
			casuisticas.add("PRE");
			casuisticas.add("DES");
			casuisticas.add("OTR");

			Object[] objeto_registro;

			DecimalFormat numeroFormateadoTiempos = new DecimalFormat("#0.000");
			DecimalFormat numeroFormateadoAciertos = new DecimalFormat("#0.00%");
			

			// primero las columnas
			tableModel.addColumn("ERROR");
			tableModel.addColumn("COUNT_REG");
		  	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
		  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
		  		tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase());
		  	}
		  	
		  	
		  	for (int k=0; k<casuisticas.size(); k++ ) {
			  	
		  		
				//unas veces guardará datos de tiempos y otras de aciertos
			  	double[]sum = new double[datos.getListaAlgoritmos().size()];
			  	
			  	int cuenta=0;
			  	for(AnalizarRegistro reg: registros){
			  		if (casuisticas.get(k).equals(reg.getError())) {
			  			cuenta+=1;
				  		for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
				  			if (Metrica=='A') {
				  				if (reg.getPersonaComprobada().getId()==reg.getAlgoritmos().get(i).getIdCandidatoPropuesto()) {
				  					sum[i]=sum[i]+1;
				  				}
				  			}else {
				  				sum[i]=sum[i]+reg.getAlgoritmos().get(i).getTiempo();
				  			}
				  		}
			  		}
			  	}

			  	if (cuenta >0) {
				  	objeto_registro = new Object[datos.getListaAlgoritmos().size()+2];
				  	objeto_registro[0]=casuisticas.get(k);
				  	objeto_registro[1]=cuenta;
				  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
				  		if (Metrica=='A') {
				  			objeto_registro[i+2]=numeroFormateadoAciertos.format(sum[i]/cuenta);
				  		}else {
				  			objeto_registro[i+2]=numeroFormateadoTiempos.format(sum[i]/1000000000);
				  		}
				  	}

				  	tableModel.addRow(objeto_registro);
			  	}
		  	}		  	
		  	return tableModel;
	    	
	  }
	
	  public DefaultTableModel cargarResultadosCasuisticasCADENAS(char Metrica) {
		  
			DefaultTableModel tableModel = new DefaultTableModel();
			List<String> casuisticas = new ArrayList<String>();
			casuisticas.add("NID");
			casuisticas.add("ERG");
			casuisticas.add("ABR");
			casuisticas.add("APO");
			casuisticas.add("OMI");
			casuisticas.add("PRE");
			casuisticas.add("DES");
			casuisticas.add("OTR");

			Object[] objeto_registro;

			DecimalFormat numeroFormateadoTiempos = new DecimalFormat("#0.000");
			DecimalFormat numeroFormateadoAciertos = new DecimalFormat("#0.00%");
			

			// primero las columnas
			tableModel.addColumn("ERROR");
			tableModel.addColumn("COUNT_REG");
		  	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
		  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
		  		tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase());
		  	}
		  	
		  	for (int k=0; k<casuisticas.size(); k++ ) {
			  	
		  		
				//unas veces guardará datos de tiempos y otras de aciertos
			  	double[]sum = new double[datos.getListaAlgoritmos().size()];
			  	
			  	int cuenta=0;
			  	for(String reg: resultados){
			  		
			  		String[] parts = reg.split(";");
			  		
			  		if (casuisticas.get(k).equals(parts[4])) {
			  			cuenta+=1;
				  		for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
				  			if (Metrica=='A') {
				  				if ("1".equals(parts[5+(2*i)])) {
				  					sum[i]=sum[i]+1;
				  				}
				  			}else {
				  				if (parts[5+(2*i)+1].toString()!=null) {
				  					sum[i]=sum[i]+Integer.parseInt(parts[5+(2*i)+1].toString());
				  				}
				  			}
				  		}
			  		}
			  	}

			  	if (cuenta >0) {
				  	objeto_registro = new Object[datos.getListaAlgoritmos().size()+2];
				  	objeto_registro[0]=casuisticas.get(k);
				  	objeto_registro[1]=cuenta;
				  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
				  		if (Metrica=='A') {
				  			objeto_registro[i+2]=numeroFormateadoAciertos.format(sum[i]/cuenta);
				  		}else {
				  			objeto_registro[i+2]=numeroFormateadoTiempos.format(sum[i]/1000000000);
				  		}
				  	}

				  	tableModel.addRow(objeto_registro);
			  	}
		  	}		  	
		  	return tableModel;
	    	
	  }
	  
	
	
	public DefaultTableModel cargarResultadosTotalesCADENAS() {
		  
		DefaultTableModel tableModel = new DefaultTableModel();
		
		
		DecimalFormat numeroFormateadoTiempos = new DecimalFormat("#0.000");
		DecimalFormat numeroFormateadoAciertos = new DecimalFormat("#0.00%");
		
		double aciertos;
		double tiempos;
		int cuenta;
		
		// primero las columnas
		tableModel.addColumn("METRICAS");
		tableModel.addColumn("COUNT_REG");
	  	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
	  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
	          tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase());
	  	}
	  	
	  	
	  	Object[] obj_tiempos = new Object[datos.getListaAlgoritmos().size()+2];
	  	Object[] obj_aciertos = new Object[datos.getListaAlgoritmos().size()+2];
	  	
	  	obj_tiempos[0] = "TIEMPOS (seg)";
	  	obj_aciertos[0] = "ACIERTO";
	  	
	  	cuenta=resultados.size();
	  	obj_tiempos[1] = cuenta;
	  	obj_aciertos[1] = cuenta;
	      
	  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
	  		aciertos=0;
	  		tiempos=0;
	  		cuenta=0;
		  	for(String reg: resultados){
		  		String[] parts = reg.split(";");
		  		cuenta+=1;
  				if ("1".equals(parts[5+(2*i)])) {
  					aciertos=aciertos+1;
  				}
  				if (parts[5+(2*i)+1].toString()!=null) {
  					tiempos=tiempos+Integer.parseInt(parts[5+(2*i)+1].toString());
  				}

		  	
		  	}
	  		obj_tiempos[i+2] =numeroFormateadoTiempos.format(tiempos/1000000000);
	  		obj_aciertos[i+2] =numeroFormateadoAciertos.format(aciertos/(double)cuenta);
	  	}
	  	
	  	tableModel.addRow(obj_tiempos);
	  	tableModel.addRow(obj_aciertos);
	  	
	  	return tableModel;
	    	
	}
	
	
	public DefaultTableModel cargarResultadosTotales() {
		  
		DefaultTableModel tableModel = new DefaultTableModel();
		
		
		DecimalFormat numeroFormateadoTiempos = new DecimalFormat("#0.000");
		DecimalFormat numeroFormateadoAciertos = new DecimalFormat("#0.00%");
		
		
		// primero las columnas
		tableModel.addColumn("METRICAS");
	  	//recorro los algoritmos y busco en resultadosSimilitudes, el que menos tenga por cada uno de ellos.
	  	for (int i=0; i<datos.getListaAlgoritmos().size(); i++ ) {
	          tableModel.addColumn(datos.getListaAlgoritmos().get(i).toUpperCase());
	  	}
	  	
	  	//Ahora las filas
	  	// como en este caso son totales, primero pondremos los tiempos y luego los aciertos
	  	
	  	Object[] obj_tiempos = new Object[datos.getListaAlgoritmosTiempos().size()+1];
	  	obj_tiempos[0] = "TIEMPOS (seg)";
	      
	  	for (int i=0; i<datos.getListaAlgoritmosTiempos().size(); i++ ) {	
	  		obj_tiempos[i+1] =numeroFormateadoTiempos.format(datos.getListaAlgoritmosTiempos().get(i));
	  	}
	  	
	  	Object[] obj_aciertos = new Object[datos.getListaAlgoritmosPorcentajeAcierto().size()+1];
	  	obj_aciertos[0] = "ACIERTO";
	      
	  	for (int j=0; j<datos.getListaAlgoritmosPorcentajeAcierto().size(); j++ ) {
	  		obj_aciertos[j+1] =numeroFormateadoAciertos.format(datos.getListaAlgoritmosPorcentajeAcierto().get(j)/100);
	  	}
	  	
	  	tableModel.addRow(obj_tiempos);
	  	tableModel.addRow(obj_aciertos);
	  	
	  	return tableModel;
	    	
	}
	
	
	
	
	/*public void setTTotales(JTable j) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo=(DefaultTableModel) j.getModel();
		tListado.setModel(modelo);
		modelo.fireTableDataChanged();
	}*/
	
	public void internalFrameActivated(InternalFrameEvent e) {
	}
	public void internalFrameClosed(InternalFrameEvent e) {
		if (e.getSource() == this) {
			internalFrameClosedThis(e);
		}
	}
	public void internalFrameClosing(InternalFrameEvent e) {
	}
	public void internalFrameDeactivated(InternalFrameEvent e) {
	}
	public void internalFrameDeiconified(InternalFrameEvent e) {
	}
	public void internalFrameIconified(InternalFrameEvent e) {
	}
	public void internalFrameOpened(InternalFrameEvent e) {
	}
	protected void internalFrameClosedThis(InternalFrameEvent e) {
		setVisible(false);
		ana=null;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBotonExportar) {
			actionPerformedBtnBotonExportar(e);
		}
	}
	protected void actionPerformedBtnBotonExportar(ActionEvent e) {
		
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
	    chooser.setFileFilter(filter);
	    chooser.setDialogTitle("Guardar archivo");
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	        String ruta = chooser.getSelectedFile().toString().concat(".xls");
	        try {
		        //creardirectorio();
		        List<JTable> tb = new ArrayList<JTable>();
		        tb.add(tCasuisticas_Aciertos);
		        tb.add(tCasuisticas_Tiempos);
		        tb.add(tTotales);
		        //-------------------
		        File excel = new File(ruta);
		        exportarExcel excelExporter = new exportarExcel(tb, excel); // String con directorio
		        if (excelExporter.export()) {
		            System.out.println("TABLAS EXPORTADOS CON EXITOS!");
		        }
		    }catch (Exception ex) {
		        ex.printStackTrace();
		    }
    
	    }
    
	}
}
