package util;

import java.awt.HeadlessException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @see https://www.jc-mouse.net/
 * @author mouse
 */
public class CargarXLS extends DefaultTableModel implements DropTargetListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jtable;
    private DefaultTableModel tableModel;
    protected DropTarget dropTarget;
    
    
    /**
     * Constructor de clase
     */
    public CargarXLS() {
    }

    public void setJtable(JTable jtable) {
        this.jtable = jtable;
        dropTarget = new DropTarget(jtable, this);    
        tableModel = new DefaultTableModel();
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {/*...*/
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {/*...*/
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {/*...*/
    }

    @Override
    public void dragExit(DropTargetEvent dte) {/*...*/
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            /* proporciona datos para operaciones de transferencia en swing */
            Transferable tr = dtde.getTransferable();
            boolean columna_seleccion=true;
            /* Devuelve una array de objetos DataFlavor */
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            if (flavors.length > 0) {
                /* Si existe una lista de objetos de archivo */
                if (flavors[0].isFlavorJavaFileListType()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    /* obtiene un List con los archivos arrastrados al componente */
                    List list = (List) tr.getTransferData(flavors[0]);
                    if (!list.isEmpty()) {
                        /* abre el primer archivo */
                        File file = new File(list.get(0).toString());
                        if (file.exists()) {
                            /* Si el archivo corresponde a un archivo excel *.xlsx */
                            if (file.getName().endsWith("xlsx")) {
                                readXLSX(file, true);
                            } else {
                                JOptionPane.showMessageDialog(null, "No es un archivo *.xlsx valido", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            System.err.println("error archivo no existe ");
                        }
                    }
                    dtde.dropComplete(true);
                    return;
                }
            }
            dtde.rejectDrop();
        } catch (UnsupportedFlavorException | IOException | HeadlessException ex) {
            System.err.println(ex.getMessage());
            dtde.rejectDrop();
        }
    }

    /**
     * Lee un archivo excel (Primera hoja)
     *
     * @param file Archivo excel
     */
    public void readXLSX(File file, boolean columna_check) throws IOException{      
        tableModel = new DefaultTableModel(){
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
        
        boolean cabecera=true;
        try {
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = wb.getSheetAt(0);//primeta hoja            
            Row row;
            Cell cell;

            //obtiene cantidad total de columnas con contenido
            int maxCol = 0;
            for (int a = 0; a <= sheet.getLastRowNum(); a++) {
                if(sheet.getRow(a)!=null){
                    if (sheet.getRow(a).getLastCellNum() > maxCol) {
                        maxCol = sheet.getRow(a).getLastCellNum();
                    }    
                }                
            }
            if (maxCol > 0) {
            	//recorre fila por fila

                //recorre fila por fila
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {

                    int index = 0;
                    row = rowIterator.next();

                    Object[] obj = new Object[row.getLastCellNum()+1];
                    Iterator<Cell> cellIterator = row.cellIterator();

                    // añadimos el check 
                    obj[index] = false;
                    index += 1;
                    
                    while (cellIterator.hasNext()) {
                        cell = cellIterator.next();
                        //contenido para celdas vacias
                        while (index < cell.getColumnIndex()) {
                            obj[index] = "";
                            index += 1;
                        }
                        //extrae contenido de archivo excel
                        switch (cell.getCellType()) {
                            case BOOLEAN:
                                obj[index] = cell.getBooleanCellValue();
                                break;
                            case NUMERIC:
                            	if (HSSFDateUtil.isCellDateFormatted(cell)){
                            		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                    String  s =  sdf.format(cell.getDateCellValue());
                            		obj[index] = s;
                            	}else {
                            		obj[index] = (int)cell.getNumericCellValue();
                            	}
                                break;
                            case STRING:
                                obj[index] = cell.getStringCellValue();
                                break;
                            case BLANK:
                                obj[index] = " ";
                                break;
                            case FORMULA:
                                obj[index] = cell.getCellFormula();
                                break;                           
                            default:
                                obj[index] = "";
                                break;
                        }                        
                        index += 1;
                    }
                    
                    // Si es la cabecera la guardamos como titulo de la columna
                    if (cabecera) {
                    	tableModel.addColumn("ANALIZAR");
                    	for (int i = 1; i <= maxCol; i++) { 
    	                    tableModel.addColumn(obj[i]);
                    	}
                    	cabecera=false;
                    }else {
                    	tableModel.addRow(obj);
                	}
                }
                jtable.setModel(tableModel);
                
                jtable.getColumn(jtable.getColumnName(0)).setPreferredWidth(65);
            	for (int i = 1; i < tableModel.getColumnCount(); i++) { 
                    jtable.getColumn(jtable.getColumnName(i)).setPreferredWidth(200);
            	}
            }else{
                JOptionPane.showMessageDialog(null, "Nada que importar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            // si no tiene que tener la columna de check la borramos
            if (!columna_check) {
            	jtable.removeColumn(jtable.getColumnModel().getColumn(0));
            }
        } catch (IOException ex) {
            System.err.println("" + ex.getMessage());
        }

    }
    

}//CargarXlsx:end
