/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
    
    public static List<String> getColumn(List<?> list, String name){
        List<String> result = new ArrayList<>();
        for (Object o : list) {
            try {
                result.add(o.getClass().getField(name).get(o).toString());
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
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
    
    public static boolean isValidTextWithNumberStart(String name) {
        String regex = "^[a-zA-z0-9](?!.* {2}).*[^ ]$";
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
