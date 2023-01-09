/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingsystem.Helper;

import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author TragicNet
 */
public class ToolTipHeader extends JTableHeader {
    
    String[] toolTips;

    public ToolTipHeader(TableColumnModel columnModel, String[] toolTips) {
        super(columnModel);
        this.toolTips = toolTips;
    }
    
    @Override
    public String getToolTipText(MouseEvent e) {
        int col = columnAtPoint(e.getPoint());
        int modelCol = getTable().convertColumnIndexToModel(col);
        String retStr;
        try {
            retStr = toolTips[modelCol];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
            retStr = "";
        }
        if (retStr.length() < 1) {
            retStr = super.getToolTipText(e);
        }
        return retStr;
    }
    
    public void setToolTipStrings(String[] toolTips) {
       this.toolTips = toolTips;
    }
    
}