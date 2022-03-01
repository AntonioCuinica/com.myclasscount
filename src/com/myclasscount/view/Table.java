/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author CUINIC4
 */
public class Table extends JTable {
    private Object td[];
    private int size=0;
    private DefaultTableModel modelo;
    private String buttonName="";
    private Color buttonColor=Color.white;
    
    public Table(String colunas[]){
        size=colunas.length;
        modelo=(DefaultTableModel)(new DefaultTableModel(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        });
        
        modelo.setColumnIdentifiers(colunas);
        modelo.setRowCount(0);
        td=new Object[colunas.length];
        setModel(this.modelo);
        setDefaultRenderer(Object.class,new CellTableZebra());
        setHearderColor(Color.blue,Color.white);
        setShowVerticalLines(false);
        setRowHeight(40);
        setDragEnabled(false);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setBounds(0, 0,WIDTH,40);
    }
    
    public void setTableData(Object linhas[][]){
        for(int i=0;i<linhas.length;i++){
            for(int j=0;j<linhas[0].length;j++){
                this.td[j]=linhas[i][j];
            }
            this.modelo.addRow(this.td);
        }
    }
    public void setNullValues(){
        for(int i=0;i<this.size;i++){
            this.td[i]="null";
        }
        this.modelo.addRow(this.td);
    }
    
    public void setCellData(String data, int i,int j){
        this.modelo.setValueAt(data, i, i);
    }
    
    public void setCellButton(JTextField data,int j){
        TableColumn col=getColumnModel().getColumn(j);
        col.setCellEditor(new DefaultCellEditor(data));
        System.out.println(col);
    }
    
    public void setButton(String column,String txt,Color color){
        buttonName=txt;
        buttonColor=color;
        MyButtonT button=new MyButtonT(txt,color);
        getColumn(column).setCellRenderer(button);
    }
    
    public void addColumn(Object data){
        this.modelo.addColumn(data);
    }
    
    public void setTableRowAt(Object data[],int i){
        this.modelo.insertRow(i, data);
    }
    
    public void setTableRow(Object data[]){
        this.modelo.addRow(data);
    }
    
    public void removeRow(int i){
        this.modelo.removeRow(i);
    }
    
    public void setHearderColor(Color color1,Color color2){
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(color1);
        headerRenderer.setForeground(color2);

        for (int i = 0; i < this.getModel().getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
    
    public class CellTableZebra extends JLabel implements TableCellRenderer{
        public CellTableZebra(){
            setOpaque(true);
        }
       
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           //uando a celula recede o focu
            if(hasFocus){
                setBackground(Color.gray.darker());
                setForeground(Color.white);
            }
            //Quando a linha é clicada
            else if(isSelected){
                setBackground(Color.gray);
                setForeground(Color.white); 
            }
            //para linhas pares
            else if((row%2)==0){
                setBackground(Color.white);
                setForeground(Color.black);
            }
            //para linhas impares
            else{
                setBackground(Color.white);
                setForeground(Color.black);
            }
            
            if(value==null){
                setText("");
            }else{
                setText(value.toString());
            }
            return this;
        }
        
    }
    
    private  class MyButtonT extends JButton implements TableCellRenderer{
        
        private String txt;
        
        public MyButtonT(String txt,Color color){
            super(txt);
            setOpaque(true);
            setForeground(color);
            setBackground(Color.white);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            MyButtonT button=new MyButtonT(buttonName,buttonColor);
            if(value==null){
                button=this;
            }else if(value instanceof MyButtonT){
               button=(MyButtonT)value;
            }
            return button;
        }
    
    }
   
    
}
