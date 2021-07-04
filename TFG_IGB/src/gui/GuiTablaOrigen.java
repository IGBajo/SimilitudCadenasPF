package gui;

import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import util.CargarXLS;
import util.DatosAplicacion;

import javax.swing.event.InternalFrameEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class GuiTablaOrigen extends JInternalFrame implements InternalFrameListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static String pro;

	private CargarXLS cargarOrigenXLS;
	private CargarXLS cargarComprobacionXLS;
	private JButton btnCargarFichero;
	private JTable tableOrigen;
	private JScrollPane scrollPane;
	
	private JPanel panel_cPrincipales;
	private JLabel eId;
	private JLabel eApellido1;
	private JLabel eApellido2;
	private JLabel eNombre;
	private JLabel eFecNac;
	private JComboBox<String> comboID;
	private JComboBox<String> comboApellido1;
	private JComboBox<String> comboApellido2;
	private JComboBox<String> comboNombre;
	private JComboBox<String> comboFecNac;
	
	private JPanel panel_cSecundarios;
	private JLabel eDni;
	private JLabel eCivitas;
	private JLabel eDireccion;
	private JLabel eProvincia;
	private JLabel eMunicipio;
	private JComboBox<String> comboDni;
	private JComboBox<String> comboCivitas;
	private JComboBox<String> comboDireccion;
	private JComboBox<String> comboCodPro;
	private JComboBox<String> comboCodMun;
	private JButton btnCargarFicheroComprobacion;
	private JTable tableComprobacion;
	private JScrollPane scrollPane_TablaComprobacion;
	
	//private DatosAplicacion datos;
	
	private JTable tDatos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GuiTablaOrigen frame = new GuiTablaOrigen();
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
	public GuiTablaOrigen() {
		
		//datos= new DatosAplicacion();

		tDatos = new JTable();
		
		addInternalFrameListener(this);
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
		
		pro="pro"; 
		

		setTitle("Seleccion Tabla Origen");
		setResizable(true);
		setBounds(100, 100, 1400, 900);
		getContentPane().setLayout(null);
		
		CargarComponentes();

	}
	
	private void CargarComponentes() {
		
		// panel para agrupar Campos Principales
		panel_cPrincipales = new JPanel();
		panel_cPrincipales.setBorder(new TitledBorder(null, "Campos Principales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_cPrincipales.setBounds(171, 456, 491, 181);
		getContentPane().add(panel_cPrincipales);
		panel_cPrincipales.setLayout(null);
		
		eId = new JLabel("ID Persona");
		eId.setBounds(43, 32, 135, 16);
		panel_cPrincipales.add(eId);
		
		comboID = new JComboBox();
		comboID.setBounds(190, 28, 258, 27);
		panel_cPrincipales.add(comboID);
		
		eApellido1 = new JLabel("Primer Apellido");
		eApellido1.setBounds(43, 60, 135, 16);
		panel_cPrincipales.add(eApellido1);
		
		eApellido2 = new JLabel("Segundo Apellido");
		eApellido2.setBounds(43, 88, 135, 16);
		panel_cPrincipales.add(eApellido2);
		
		eNombre = new JLabel("Nombre");
		eNombre.setBounds(43, 116, 135, 16);
		panel_cPrincipales.add(eNombre);
		
		eFecNac = new JLabel("Fecha de Nacimiento");
		eFecNac.setBounds(43, 144, 135, 16);
		panel_cPrincipales.add(eFecNac);
		
		comboApellido1 = new JComboBox();
		comboApellido1.setBounds(190, 56, 258, 27);
		panel_cPrincipales.add(comboApellido1);
		
		comboApellido2 = new JComboBox();
		comboApellido2.setBounds(190, 84, 258, 27);
		panel_cPrincipales.add(comboApellido2);
		
		comboNombre = new JComboBox();
		comboNombre.setBounds(190, 112, 258, 27);
		panel_cPrincipales.add(comboNombre);
		
		comboFecNac = new JComboBox();
		comboFecNac.setBounds(190, 140, 258, 27);
		panel_cPrincipales.add(comboFecNac);
		
		// Panel con los campos Secundarios
		panel_cSecundarios = new JPanel();
		panel_cSecundarios.setLayout(null);
		panel_cSecundarios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Campos Secundarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_cSecundarios.setBounds(763, 456, 491, 181);
		getContentPane().add(panel_cSecundarios);
		
		eDni = new JLabel("DNI NIE");
		eDni.setBounds(43, 32, 135, 16);
		panel_cSecundarios.add(eDni);
		
		comboDni = new JComboBox();
		comboDni.setBounds(190, 28, 258, 27);
		panel_cSecundarios.add(comboDni);
		
		eCivitas = new JLabel("Tarjeta Sanitaria");
		eCivitas.setBounds(43, 60, 135, 16);
		panel_cSecundarios.add(eCivitas);
		
		eDireccion = new JLabel("Direccion");
		eDireccion.setBounds(43, 88, 135, 16);
		panel_cSecundarios.add(eDireccion);
		
		eProvincia = new JLabel("Cod Provincia");
		eProvincia.setBounds(43, 116, 135, 16);
		panel_cSecundarios.add(eProvincia);
		
		eMunicipio = new JLabel("Cod Municipio");
		eMunicipio.setBounds(43, 144, 135, 16);
		panel_cSecundarios.add(eMunicipio);
		
		comboCivitas = new JComboBox();
		comboCivitas.setBounds(190, 56, 258, 27);
		panel_cSecundarios.add(comboCivitas);
		
		comboDireccion = new JComboBox();
		comboDireccion.setBounds(190, 84, 258, 27);
		panel_cSecundarios.add(comboDireccion);
		
		comboCodPro = new JComboBox();
		comboCodPro.setBounds(190, 112, 258, 27);
		panel_cSecundarios.add(comboCodPro);
		
		comboCodMun = new JComboBox();
		comboCodMun.setBounds(190, 140, 258, 27);
		panel_cSecundarios.add(comboCodMun);
		
		
		// boton buscar el archivo
		btnCargarFichero = new JButton("Cargar Fichero Origen de Datos");
		btnCargarFichero.addActionListener(this);
		btnCargarFichero.setBounds(28, 17, 221, 29);
		getContentPane().add(btnCargarFichero);
		
		//Panel scroll que almacena la tabla
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 58, 1369, 342);
		getContentPane().add(scrollPane);
		
		
		// crea tabla de datos origen
		tableOrigen = new JTable();
		scrollPane.setViewportView(tableOrigen);
		
		tableOrigen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOrigen.setCellSelectionEnabled(true);
		tableOrigen.setColumnSelectionAllowed(true);
		tableOrigen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// rellenar tabla de datos de origen
		cargarOrigenXLS = new CargarXLS();
		cargarOrigenXLS.setJtable(tableOrigen);
		
		
		// boton buscar el archivo de comprobacion
		btnCargarFicheroComprobacion = new JButton("Cargar Fichero Comprobación");
		btnCargarFicheroComprobacion.addActionListener(this);
		btnCargarFicheroComprobacion.setBounds(28, 736, 221, 29);
		getContentPane().add(btnCargarFicheroComprobacion);
		
		scrollPane_TablaComprobacion = new JScrollPane();
		scrollPane_TablaComprobacion.setBounds(323, 711, 339, 90);
		getContentPane().add(scrollPane_TablaComprobacion);
		
		tableComprobacion = new JTable();
		scrollPane_TablaComprobacion.setViewportView(tableComprobacion);
		
		tableComprobacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableComprobacion.setCellSelectionEnabled(true);
		tableComprobacion.setColumnSelectionAllowed(true);
		tableComprobacion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// rellenar tabla de datos de origen
		cargarComprobacionXLS = new CargarXLS();
		cargarComprobacionXLS.setJtable(tableComprobacion);
		
		
		//cargar las tablas con valores por defecto
		
		cargarTablasporDefecto();
		
	}

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
		pro=null;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCargarFicheroComprobacion) {
			actionPerformedBtnCargarFicheroComprobacion(e);
		}
		if (e.getSource() == btnCargarFichero) {
			actionPerformedBtnCargarFichero(e);
		}
	}
	
	//Accion al pulsar el boton de buscar datos de origen
	protected void actionPerformedBtnCargarFichero(ActionEvent e) {
		JFileChooser examinar = new JFileChooser();
		boolean columna_seleccion=true;
		FileNameExtensionFilter filtroAbrir = new FileNameExtensionFilter("Archivos excel", "xls", "xlsx");
		examinar.setFileFilter(filtroAbrir);
		int opcion=examinar.showOpenDialog(btnCargarFichero);
		File archivoExcel=null;
		if (opcion== JFileChooser.APPROVE_OPTION){
			archivoExcel=examinar.getSelectedFile().getAbsoluteFile();
			//JOptionPane.showMessageDialog(null, "La ruta es "+archivoExcel);
			try {
				cargarOrigenXLS.readXLSX(archivoExcel,columna_seleccion);
				inicializaCombos();
				
			}catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error al leer el fichero excel. Error: "+ex);
			}
		}
		
	}
	
	//Accion al pulsar el boton de buscar datos de origen
	protected void cargarTablasporDefecto() {

		File archivoExcelOrigen = new File ("/Users/Mac/Desktop/DataSet.xlsx");
		File archivoExcelComprobados = new File ("/Users/Mac/Desktop/DataSetComprobacion.xlsx");
		
		try {
			cargarOrigenXLS.readXLSX(archivoExcelOrigen,true);
			cargarComprobacionXLS.readXLSX(archivoExcelComprobados,false);
			inicializaCombos();
			
		}catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al leer el fichero excel. Error: "+ex);
		}
		
	}
	
	private void inicializaCombos() {
		
		//Campos Principales
		comboID.addItem(" ");
		comboApellido1.addItem(" ");
		comboApellido2.addItem(" ");
		comboNombre.addItem(" ");
		comboFecNac.addItem(" ");
		//Campos Secundarios
		comboDni.addItem(" ");
		comboCivitas.addItem(" ");
		comboDireccion.addItem(" ");
		comboCodPro.addItem(" ");
		comboCodMun.addItem(" ");
		
		for (int c = 0; c < tableOrigen.getColumnCount(); c++) {
			//Campos Principales
			comboID.addItem(tableOrigen.getColumnName(c));
			comboApellido1.addItem(tableOrigen.getColumnName(c));
			comboApellido2.addItem(tableOrigen.getColumnName(c));
			comboNombre.addItem(tableOrigen.getColumnName(c));
			comboFecNac.addItem(tableOrigen.getColumnName(c));
			//Campos Secundarios
			comboDni.addItem(tableOrigen.getColumnName(c));
			comboCivitas.addItem(tableOrigen.getColumnName(c));
			comboDireccion.addItem(tableOrigen.getColumnName(c));
			comboCodPro.addItem(tableOrigen.getColumnName(c));
			comboCodMun.addItem(tableOrigen.getColumnName(c));
		}
		
		//Campos Principales
		comboID.setSelectedItem("ID");
		comboApellido1.setSelectedItem("APE1");
		comboApellido2.setSelectedItem("APE2");
		comboNombre.setSelectedItem("NOMBRE");
		comboFecNac.setSelectedItem("FECHA_NACIMIENTO");
		//Campos Secundarios
		comboDni.setSelectedItem("DNI_NIE");
		comboCivitas.setSelectedItem("CIPCIVITAS");
		comboDireccion.setSelectedItem("DIRECCION");
		comboCodPro.setSelectedItem("PROVINCIA");
		comboCodMun.setSelectedItem("MUNICIPIO");
			
	}
	
	
	protected void actionPerformedBtnCargarFicheroComprobacion(ActionEvent e) {
		JFileChooser examinar = new JFileChooser();
		FileNameExtensionFilter filtroAbrir = new FileNameExtensionFilter("Archivos excel", "xls", "xlsx");
		boolean columna_seleccion=false;
		examinar.setFileFilter(filtroAbrir);
		int opcion=examinar.showOpenDialog(btnCargarFicheroComprobacion);
		File archivoExcel=null;
		if (opcion== JFileChooser.APPROVE_OPTION){
			archivoExcel=examinar.getSelectedFile().getAbsoluteFile();
			//JOptionPane.showMessageDialog(null, "La ruta es "+archivoExcel);
			try {
				cargarComprobacionXLS.readXLSX(archivoExcel, columna_seleccion);
				
			}catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error al leer el fichero excel de comprobación. Error: "+ex);
			}
		}
	}
	
	public JTable getTableOrigen() {
		return tableOrigen;
	}
	
	public JTable getTableComprobacion() {
		return tableComprobacion;
	}
	
	public void setTableOrigen(JTable table) {
		tableOrigen=table;
	}
	
	public void setTableComparacion(JTable table) {
		tableComprobacion=table;
	}
	
	public JTable getTDatos(){
		
		
		DefaultTableModel modeloOrigen = (DefaultTableModel)tableOrigen.getModel();
		DefaultTableModel modelDatos = new DefaultTableModel(){
		        @Override
		        public Class getColumnClass(int column) {
		    	     switch (column) {
		    	     case 0:
		    	      return Boolean.class;
		    	     default:
		    	      return Object.class;
		    	     }
		        }
	        };
			

	    modelDatos.addColumn("ANALIZAR"); 	// 0
	    modelDatos.addColumn("ID"); 		// 1
	    modelDatos.addColumn("APE1"); 		// 2
	    modelDatos.addColumn("APE2"); 		// 3
	    modelDatos.addColumn("NOMBRE");		// 4
	    modelDatos.addColumn("FECNAC");		// 5
	    modelDatos.addColumn("DNI");		// 6
	    modelDatos.addColumn("CIVITAS");	// 7
	    modelDatos.addColumn("DIRECCION");	// 8
	    modelDatos.addColumn("CODPRO");		// 9
	    modelDatos.addColumn("CODMUN");		// 10
	    //modelDatos.addColumn("ERROR");		// 11
	    

	    for (int i = 0; i < modeloOrigen.getRowCount(); i++) { 
	    	Object[] fila = new Object[11];
	    	fila[0] = modeloOrigen.getValueAt(i, 0);
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboID.getSelectedItem()))==null) {
	    		fila[1] = "";
	    	}else {
	    		fila[1] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboID.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboApellido1.getSelectedItem()))==null) {
	    		fila[2] = "";
	    	}else {
	    		fila[2] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboApellido1.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboApellido2.getSelectedItem()))==null) {
	    		fila[3] = "";
	    	}else {
	    		fila[3] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboApellido2.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboNombre.getSelectedItem()))==null) {
	    		fila[4] = "";
	    	}else {
	    		fila[4] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboNombre.getSelectedItem()));
	    	}

	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboFecNac.getSelectedItem()))==null) {
	    		fila[5] = "";
	    	}else {
	    		fila[5] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboFecNac.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboDni.getSelectedItem()))==null) {
	    		fila[6] = "";
	    	}else {
	    		fila[6] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboDni.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboCivitas.getSelectedItem()))==null) {
	    		fila[7] = "";
	    	}else {
	    		fila[7] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboCivitas.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboDireccion.getSelectedItem()))==null) {
	    		fila[8] = "";
	    	}else {
	    		fila[8] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboDireccion.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboCodPro.getSelectedItem()))==null) {
	    		fila[9] = "";
	    	}else {
	    		fila[9] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboCodPro.getSelectedItem()));
	    	}
	    	
	    	if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboCodMun.getSelectedItem()))==null) {
	    		fila[10] = "";
	    	}else {
	    		fila[10] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn((String)comboCodMun.getSelectedItem()));
	    	}
	    	
	    	/*if (modeloOrigen.getValueAt(i, modeloOrigen.findColumn("ERROR"))==null) {
	    		fila[11] = "";
	    	}else {
	    		fila[11] = modeloOrigen.getValueAt(i, modeloOrigen.findColumn("ERROR"));
	    	}*/
	    	modelDatos.addRow(fila);
	    }
	   
		tDatos=new JTable (modelDatos);
		
		/*for (int i = 0; i <= 3; i++) {
			for (int j = 0; j < tDatos.getColumnCount(); j++) { 
				JOptionPane.showMessageDialog(null, String.valueOf(tDatos.getValueAt(i,j))); 
			}
		}*/
		
		return tDatos;
		
	}
	
}
