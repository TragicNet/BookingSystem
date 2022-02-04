/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Helper;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 * @author TragicNet
 */
public class Helper {
    
    public static SimpleDateFormat dateFormat = new SimpleDateFormat( "dd/MM/yy" );
    
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
    
    public static boolean regexTester(String string, String regex) {
        Pattern p = Pattern.compile(regex);
  
        if (string == null)
            return false;
        
        Matcher m = p.matcher(string);
  
        return m.matches();
    }
    
    public static boolean isValidText(String name) {
        String regex = "^[a-zA-z](?!.* {2}).*[^ ]$";
        return Helper.regexTester(name, regex);
    }
    
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9.! #$%&'*+/=? ^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\. [a-zA-Z0-9-]+)*$";
        return Helper.regexTester(email, regex);
    }
    
    public static boolean isValidPhone(String email) {
        String regex = "^\\d{10}$";
        return Helper.regexTester(email, regex);
    }
    
}
