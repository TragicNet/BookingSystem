/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.bookingsystem;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author TragicNet
 */
public class Launcher extends javax.swing.JFrame {
    
    private CardLayout cardLayout;
    
    /**
     * Creates new form Launcher
     */
    public Launcher() {
        initComponents();

        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "booking");
        mainPanel.add(reportsPanel, "reports");
        mainPanel.add(inventoryPanel, "inventory");
        
        bookingButton.doClick();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardsButtonGroup = new javax.swing.ButtonGroup();
        ContainerPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        sidePanel = new javax.swing.JPanel();
        bookingButton = new javax.swing.JToggleButton();
        billingButton = new javax.swing.JToggleButton();
        reportsButton = new javax.swing.JToggleButton();
        inventoryButton = new javax.swing.JToggleButton();
        exitButton = new javax.swing.JToggleButton();
        mainPanel = new javax.swing.JPanel();
        bookingPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        reportsPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        inventoryPanel = new com.mycompany.bookingsystem.InnerPanels.InventoryPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Booking System");
        setBackground(new java.awt.Color(0, 0, 0));
        setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        setName("MainFrame"); // NOI18N
        setResizable(false);

        ContainerPanel.setBackground(new java.awt.Color(217, 217, 217));
        ContainerPanel.setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(60, 110, 113));
        topPanel.setMinimumSize(new java.awt.Dimension(396, 50));
        topPanel.setPreferredSize(new java.awt.Dimension(10, 70));
        topPanel.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hall Booking and Billing System");
        topPanel.add(jLabel4, new java.awt.GridBagConstraints());

        ContainerPanel.add(topPanel, java.awt.BorderLayout.PAGE_START);

        sidePanel.setBackground(new java.awt.Color(40, 75, 99));
        sidePanel.setPreferredSize(new java.awt.Dimension(150, 456));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 75, 0);
        flowLayout1.setAlignOnBaseline(true);
        sidePanel.setLayout(flowLayout1);

        bookingButton.setBackground(new java.awt.Color(217, 217, 217));
        cardsButtonGroup.add(bookingButton);
        bookingButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bookingButton.setForeground(new java.awt.Color(255, 255, 255));
        bookingButton.setText("Booking");
        bookingButton.setBorder(null);
        bookingButton.setBorderPainted(false);
        bookingButton.setContentAreaFilled(false);
        bookingButton.setFocusPainted(false);
        bookingButton.setPreferredSize(new java.awt.Dimension(200, 50));
        bookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingButtonActionPerformed(evt);
            }
        });
        sidePanel.add(bookingButton);

        billingButton.setBackground(new java.awt.Color(217, 217, 217));
        cardsButtonGroup.add(billingButton);
        billingButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        billingButton.setForeground(new java.awt.Color(255, 255, 255));
        billingButton.setText("Billing");
        billingButton.setBorder(null);
        billingButton.setBorderPainted(false);
        billingButton.setContentAreaFilled(false);
        billingButton.setFocusPainted(false);
        billingButton.setPreferredSize(new java.awt.Dimension(200, 50));
        billingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billingButtonActionPerformed(evt);
            }
        });
        sidePanel.add(billingButton);

        reportsButton.setBackground(new java.awt.Color(217, 217, 217));
        cardsButtonGroup.add(reportsButton);
        reportsButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reportsButton.setForeground(new java.awt.Color(255, 255, 255));
        reportsButton.setText("Reports");
        reportsButton.setBorder(null);
        reportsButton.setBorderPainted(false);
        reportsButton.setContentAreaFilled(false);
        reportsButton.setFocusPainted(false);
        reportsButton.setPreferredSize(new java.awt.Dimension(200, 50));
        reportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsButtonActionPerformed(evt);
            }
        });
        sidePanel.add(reportsButton);

        inventoryButton.setBackground(new java.awt.Color(217, 217, 217));
        cardsButtonGroup.add(inventoryButton);
        inventoryButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        inventoryButton.setForeground(new java.awt.Color(255, 255, 255));
        inventoryButton.setText("Inventory");
        inventoryButton.setBorder(null);
        inventoryButton.setBorderPainted(false);
        inventoryButton.setContentAreaFilled(false);
        inventoryButton.setFocusPainted(false);
        inventoryButton.setPreferredSize(new java.awt.Dimension(200, 50));
        inventoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryButtonActionPerformed(evt);
            }
        });
        sidePanel.add(inventoryButton);

        exitButton.setBackground(new java.awt.Color(217, 217, 217));
        cardsButtonGroup.add(exitButton);
        exitButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Exit");
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setPreferredSize(new java.awt.Dimension(200, 50));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        sidePanel.add(exitButton);

        ContainerPanel.add(sidePanel, java.awt.BorderLayout.LINE_START);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.CardLayout());

        bookingPanel.setBackground(new java.awt.Color(217, 217, 217));
        bookingPanel.setOpaque(false);
        bookingPanel.setPreferredSize(new java.awt.Dimension(1395, 700));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Booking");

        javax.swing.GroupLayout bookingPanelLayout = new javax.swing.GroupLayout(bookingPanel);
        bookingPanel.setLayout(bookingPanelLayout);
        bookingPanelLayout.setHorizontalGroup(
            bookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(1898, Short.MAX_VALUE))
        );
        bookingPanelLayout.setVerticalGroup(
            bookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(1084, Short.MAX_VALUE))
        );

        mainPanel.add(bookingPanel, "booking");

        reportsPanel.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Reports");

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(1902, Short.MAX_VALUE))
        );
        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(1084, Short.MAX_VALUE))
        );

        mainPanel.add(reportsPanel, "reports");
        mainPanel.add(inventoryPanel, "inventory");

        ContainerPanel.add(mainPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(ContainerPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bookingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingButtonActionPerformed
        selectCardButton();
        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "booking");
    }//GEN-LAST:event_bookingButtonActionPerformed

    private void reportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsButtonActionPerformed
        selectCardButton();
        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "reports");
    }//GEN-LAST:event_reportsButtonActionPerformed

    private void inventoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryButtonActionPerformed
        selectCardButton();
        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "inventory");
    }//GEN-LAST:event_inventoryButtonActionPerformed

    private void billingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billingButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButtonActionPerformed

    private void selectCardButton() {
        for (Enumeration<AbstractButton> buttons = cardsButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                button.setForeground(Color.black);
                button.setOpaque(true);
            } else {
                button.setForeground(Color.white);
                button.setOpaque(false);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            Font defaultFont = UIManager.getDefaults().getFont("RadioButton.font");
//            setUIFont(new FontUIResource(new Font(defaultFont.getName(), defaultFont.getStyle(), 30)));
//            UIManager.put("Label.font", new Font(defaultFont.getName(), defaultFont.getStyle(), 18));
//            UIManager.put("RadioButton.font", new Font(defaultFont.getName(), defaultFont.getStyle(), 18));
//            UIManager.put("Border.font", new Font(defaultFont.getName(), defaultFont.getStyle(), 18));
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Launcher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Launcher frame = new Launcher();
                int WIDTH = 900, HEIGHT = 600;
                frame.setSize(WIDTH, HEIGHT);
                frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);

                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContainerPanel;
    private javax.swing.JToggleButton billingButton;
    private javax.swing.JToggleButton bookingButton;
    private javax.swing.JPanel bookingPanel;
    private javax.swing.ButtonGroup cardsButtonGroup;
    private javax.swing.JToggleButton exitButton;
    private javax.swing.JToggleButton inventoryButton;
    private com.mycompany.bookingsystem.InnerPanels.InventoryPanel inventoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToggleButton reportsButton;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables


}
