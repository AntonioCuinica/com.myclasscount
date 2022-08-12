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
        CifraVinegere cifra=new CifraVinegere();
        if(!codigo.isEmpty()){
            codigo=cifra.decifrar(codigo,System.getProperty("user.name"));
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
    
    public static class CifraVinegere {
        int tabela[][]=new int[26][26];

        public CifraVinegere(){
            preencherTabela();
        }

        public void preencherTabela(){
            int count=25;
            int j=0;
            for(int i=0;i<tabela.length;i++){
                while(count>=0){
                    if(j>25){
                        j=0;
                    }
                    if(i>0){
                        if(tabela[i-1][j]>=25){
                            tabela[i][j]=0;
                        }else{
                            tabela[i][j]=tabela[i-1][j]+1;
                        }
                    }else{
                        tabela[i][j]=j;
                    }
                    j++;
                    count--;
                }
                count=25;
            }
        }

        public String decifrar(String texto,String chave){
            String textoC="";
            texto=texto.toLowerCase();
            chave=chave.toLowerCase();
            char txt[]=texto.toCharArray();
            char ch[]=chave.toCharArray();
            int count=0;
            for(char t:txt){
                if(!(t<97 || t>122)){
                    int i=t%26;
                    int j=ch[count]%26;
                    if(i>=19){
                        i-=19;
                    }else if(i<=18){
                        i+=7;
                    }
                    if(j>=19){
                        j-=19;
                    }else if(j<=18){
                        j+=7;
                    }
                    if(++count==ch.length){
                        count=0;
                    }
                    for(int k=0;k<26;k++){
                        if(tabela[j][k]==i){
                            textoC+=(char)(tabela[0][k]+65);
                            break;
                        }
                    }
                }else{
                    textoC+=t;
                }
            }
            return textoC;
        }
    }
  
}
