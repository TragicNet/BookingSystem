/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.UIManager;

/**
 *
 * @author TragicNet
 */
public class Helper {
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
    
    public static boolean isValidText(String name) {
        String regex = "^((?![\\^!@#$*~ <>?-]).)((?![\\^!@#$*~<>?]).)+((?![\\^!@#$*~ <>?]).)$";
  
        Pattern p = Pattern.compile(regex);
  
        if (name == null)
            return false;
        
        Matcher m = p.matcher(name);
  
        return m.matches();
    }
    
}
