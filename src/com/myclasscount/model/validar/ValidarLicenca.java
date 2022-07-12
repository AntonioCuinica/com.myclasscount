/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.model.validar;

import com.myclasscount.control.CtrlGeral;
import com.myclasscount.model.Licensa;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author CUINIC4
 */

public class ValidarLicenca {
        
    public static String gerarCondigoDeLicensa(int periodo){
        Calendar data=Calendar.getInstance();
        String dataActual=new SimpleDateFormat("ddMMyyyy").format(data.getTime());
        data.add(Calendar.MONTH,periodo);
        String dataFinal=new SimpleDateFormat("ddMMyyyy").format(data.getTime());
        String osName=System.getProperty("os.name");
        String osVersion=System.getProperty("os.version");
        String userName=System.getProperty("user.name");
        String osHome=System.getProperty("user.home");
        String cod=encryptar(osName,osVersion,dataFinal,userName,dataActual,osHome);
        System.out.println("Codigo normal: "+osName+""+osVersion+""+dataFinal+""+userName+""+dataActual+""+osHome);
        System.out.println("Codigo cifrado: "+cod);
        return cod;
    }
    
    public static String gerarCodigoLicenca2(String nomeSO,String versaoSO,String nomeUsuario,String dirHomeSO,int periodoMes){
        Calendar data=Calendar.getInstance();
        String dataActual=new SimpleDateFormat("ddMMyyyy").format(data.getTime());
        data.add(Calendar.MONTH,periodoMes);
        String dataFinal=new SimpleDateFormat("ddMMyyyy").format(data.getTime());
        String cod=encryptar(nomeSO,versaoSO,dataFinal,nomeUsuario,dataActual,dirHomeSO);
        return cod;
    }
    
    public static String encryptar(String ... args){
        String codigo="";
        for(String a:args){
            char array[]=a.toCharArray();
            for(int i=array.length-1;i>=0;i--){
                if(i%2==0){
                    codigo+=String.valueOf(array[i]).toLowerCase();
                }else{
                    codigo+=String.valueOf(array[i]).toUpperCase();
                }
            }
            codigo+="-";
        }
        return codigo.replace("s","?").replace("n","!").replace("2","*");
    }
    
    public static String[] decryptar(String codigo){
        if(!codigo.isEmpty()){
            String array[]=new String[size(codigo)];
            codigo=codigo.replace("?","s").replace("!","n").replace("*","2").toLowerCase();
            String ai[]=codigo.split("-");
            for(int i=0;i<ai.length;i++){
                char arr[]=ai[i].toCharArray();
                for(int j=arr.length-1;j>=0;j--){
                    try{
                        array[i]+=arr[j];
                    }catch(IndexOutOfBoundsException e ){

                    }
                }
                array[i]=array[i].replace("null","");
            }
            return array;
        }
        return null;
    }
    
    public static boolean verificarLicensa(){
        Object licensa[]=(Object[])CtrlGeral.retornarLicensa();
        String codigo[];
        try{
            codigo=decryptar(String.valueOf(licensa[0]));
        }catch(NullPointerException e){
            System.out.println("Erro, (verificarlicensa) licensa nao encontrada!");
            return false;
        }
        int mesInicial=Integer.parseInt(codigo[4].substring(2,4));
        int diaInicial=Integer.parseInt(codigo[4].substring(0,2));
        Calendar cal=Calendar.getInstance();
        //cal.add(Calendar.MONTH,1);
        //cal.add(Calendar.DAY_OF_MONTH,125);
        int mesActual=Integer.parseInt(new SimpleDateFormat("MM").format(cal.getTime()));
        int diaActual=Integer.parseInt(new SimpleDateFormat("dd").format(cal.getTime()));
        int mesFinal=Integer.parseInt(codigo[2].substring(2,4));
        int diaFinal=Integer.parseInt(codigo[4].substring(0,2));
        int periodo=Integer.parseInt(licensa[1].toString());
        
//        System.out.println("Mes inicial: "+mesInicial);
//        System.out.println("Dia inicial: "+diaInicial);
//        System.out.println("Mes Final: "+mesFinal);
//        System.out.println("Dia Final: "+mesFinal);
//        System.out.println("Mes actual: "+mesActual);
//        System.out.println("Dia actual: "+diaActual);
//        System.out.println("Periodo: "+periodo);
        
        int difA=Math.abs(mesActual-mesInicial);
        int difF=Math.abs(mesFinal-mesInicial);
        
        //System.out.println("difA: "+difA);
        //System.out.println("difF: "+difF);
        int diasRestantes=(difF*31+diaFinal)-(difA*31+diaActual);
        //System.out.println("dias restantes: "+diasRestantes);
        
        if(periodo>diasRestantes){
            return false;
        }
        else if(difA*31+Math.abs(diaFinal-diaActual)>periodo){
            Licensa li=new Licensa();
            licensa[1]=difA*31+Math.abs(diaFinal-diaActual);
            li.guardarObjecto(licensa);
        }else if(periodo<difF*31+diaFinal && periodo!=difA*31+Math.abs(diaFinal-diaActual)){
            Licensa li=new Licensa();
            licensa[1]=++periodo;
            li.guardarObjecto(licensa);
        }
        return true;
    }
    
    public static int size(String codigo){
        return codigo.split("-").length;
    }
    
    public static void main(String[] args) {
        CtrlGeral.gerarCodigoGravar(4);
        //Object obj[]=(Object[])CtrlGeral.retornarLicensa();
        //System.out.println("Retornado: "+obj[0]);
        //verificarLicensa();
    }
}
