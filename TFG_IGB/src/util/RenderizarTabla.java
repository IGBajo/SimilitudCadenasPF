package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class RenderizarTabla extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
      
	  super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
	  

	  if ("tCasos".equals(table.getName())){
	      if ( "1".equals(table.getValueAt(row, column)) ){
	          //this.setOpaque(true);
	          //this.setBackground(Color.GREEN);
	          this.setForeground(Color.GREEN);
	       } else {
	     	  if ("0".equals(table.getValueAt(row, column))) {
	               this.setForeground(Color.RED);
	     	  }else {
	               this.setForeground(Color.BLACK);
	     	  }
	       }
	      
	      if (column>=0 && column<2) {
	    	  this.setFont(this.getFont().deriveFont(Font.BOLD));
	      }
	  }
	  
	  if ("tTotales".equals(table.getName()) ){
		  
		  if (column>0) {
			  boolean mayor=true;
			  boolean menor=true;
			  double valor1=0.0;
			  double valor2=0.0;

			  valor1=Double.parseDouble(table.getValueAt(row,column).toString().replace(",", ".").replace("%", ""));

			  for (int j=2; j<table.getColumnCount();j++) {
				  
				  valor2=Double.parseDouble(table.getValueAt(row,j).toString().replace(",", ".").replace("%", ""));
				  
				  if ( valor1 < valor2){
					  mayor=false;
				  }
				  if (valor1 > valor2){
					  menor=false;
				  }
			  }

			  if (row==0) {
			      if (mayor){
			    	  this.setForeground(Color.RED);
			       } else {
			     	  if (menor) {
			               this.setForeground(Color.GREEN);
			     	  }else {
			               this.setForeground(Color.BLACK);
			     	  }
			       }
			  }else {
			      if (mayor){
			    	  this.setForeground(Color.GREEN);
			       } else {
			     	  if (menor) {
			               this.setForeground(Color.RED);
			     	  }else {
			               this.setForeground(Color.BLACK);
			     	  }
			       }
			  }
		  }else {
			  this.setFont(this.getFont().deriveFont(Font.BOLD));
		  }

	  }
	  
      return this;
   }
	
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 250)
	            width=250;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}	
	
	
	
	
}