/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author CUINIC4
 */

public class ChangeLookAndFeel {
    public static void changeLookAndFeel(int type){
        String style="Nimbus";
        switch(type){
            case 1:
                style="Nimbus";
            break;
            
            case 2:
                style="Metal";
            break;
            case 3:
                style="motif";
            break;
            
            default:
                style="motif";
            break;
        }
        try{
            for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if(style.equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e){
        
        }
    }
}
