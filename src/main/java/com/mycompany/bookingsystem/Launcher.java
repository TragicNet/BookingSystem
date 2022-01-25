/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.bookingsystem;

import com.mycompany.bookingsystem.Helper.Helper;
import static com.mycompany.bookingsystem.Helper.Helper.setUIFont;
import com.mycompany.bookingsystem.Models.Entity.Entity;
import com.mycompany.bookingsystem.Models.Entity.EntityDao;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import org.sqlite.util.StringUtils;

/**
 *
 * @author TragicNet
 */
public class Launcher extends javax.swing.JFrame {

    private final String selectQuery[] = {
            "select * from entity where status <> -1 order by name collate nocase",
            "select * from entity  where status <> -1 and type = '1' order by name collate nocase",
            "select * from entity  where status <> -1 and type = '2' order by name collate nocase",
            "select * from entity  where status <> -1 and type = '3' order by name collate nocase",
            "select * from entity  where status <> -1 and type = '4' order by name collate nocase"
    };
    
    private EntityDao entityDao;
    private ArrayList<Entity> entityList;
    private DefaultListModel<String> entityListModel;
    private int selectedEntityId;
    private JRadioButton typeButtons[];
    private JRadioButton bookingSlotButtons[];
    private JRadioButton acButtons[];
    private JRadioButton statusButtons[];
    private CardLayout cardLayout;
    

    /**
     * Creates new form Launcher
     */
    public Launcher() {
        initComponents();

        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "bookingCard");
        mainPanel.add(reportsPanel, "reportsCard");
        mainPanel.add(inventoryPanel, "bookingCard");
      
        typeButtons = new JRadioButton[4];
        typeButtons[0] = hallRadioButton;
        typeButtons[1] = roomRadioButton;
        typeButtons[2] = spaceRadioButton;
        typeButtons[3] = equipmentRadioButton;
        
        bookingSlotButtons = new JRadioButton[2];
        bookingSlotButtons[1] = fullDayRadioButton;
        bookingSlotButtons[0] = halfDayRadioButton;
        
        acButtons = new JRadioButton[2];
        acButtons[1] = acYesRadioButton;
        acButtons[0] = halfDayRadioButton;
        
        statusButtons = new JRadioButton[2];
        statusButtons[1] = activeRadioButton;
        statusButtons[0] = inActiveRadioButton;
        
        bookingButton.doClick();
        typeButtons[0].doClick();
        
//        customizeComponents();
        
        entityDao = new EntityDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        refreshEntityList();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "bookingCard");
        mainPanel.add(reportsPanel, "reportsCard");
        mainPanel.add(inventoryPanel, "bookingCard");
      
        typeButtons = new JRadioButton[4];
        typeButtons[0] = hallRadioButton;
        typeButtons[1] = roomRadioButton;
        typeButtons[2] = spaceRadioButton;
        typeButtons[3] = equipmentRadioButton;
        
        bookingSlotButtons = new JRadioButton[2];
        bookingSlotButtons[1] = fullDayRadioButton;
        bookingSlotButtons[0] = halfDayRadioButton;
        
        acButtons = new JRadioButton[2];
        acButtons[1] = acYesRadioButton;
        acButtons[0] = halfDayRadioButton;
        
        statusButtons = new JRadioButton[2];
        statusButtons[1] = activeRadioButton;
        statusButtons[0] = inActiveRadioButton;
        
        bookingButton.doClick();
        typeButtons[0].doClick();
        
//        customizeComponents();
        
        entityDao = new EntityDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        refreshEntityList();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "bookingCard");
        mainPanel.add(reportsPanel, "reportsCard");
        mainPanel.add(inventoryPanel, "bookingCard");
      
        typeButtons = new JRadioButton[4];
        typeButtons[0] = hallRadioButton;
        typeButtons[1] = roomRadioButton;
        typeButtons[2] = spaceRadioButton;
        typeButtons[3] = equipmentRadioButton;
        
        bookingSlotButtons = new JRadioButton[2];
        bookingSlotButtons[1] = fullDayRadioButton;
        bookingSlotButtons[0] = halfDayRadioButton;
        
        acButtons = new JRadioButton[2];
        acButtons[1] = acYesRadioButton;
        acButtons[0] = halfDayRadioButton;
        
        statusButtons = new JRadioButton[2];
        statusButtons[1] = activeRadioButton;
        statusButtons[0] = inActiveRadioButton;
        
        bookingButton.doClick();
        typeButtons[0].doClick();
        
//        customizeComponents();
        
        entityDao = new EntityDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        refreshEntityList();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "bookingCard");
        mainPanel.add(reportsPanel, "reportsCard");
        mainPanel.add(inventoryPanel, "bookingCard");
      
        typeButtons = new JRadioButton[4];
        typeButtons[0] = hallRadioButton;
        typeButtons[1] = roomRadioButton;
        typeButtons[2] = spaceRadioButton;
        typeButtons[3] = equipmentRadioButton;
        
        bookingSlotButtons = new JRadioButton[2];
        bookingSlotButtons[1] = fullDayRadioButton;
        bookingSlotButtons[0] = halfDayRadioButton;
        
        acButtons = new JRadioButton[2];
        acButtons[1] = acYesRadioButton;
        acButtons[0] = halfDayRadioButton;
        
        statusButtons = new JRadioButton[2];
        statusButtons[1] = activeRadioButton;
        statusButtons[0] = inActiveRadioButton;
        
        bookingButton.doClick();
        typeButtons[0].doClick();
        
//        customizeComponents();
        
        entityDao = new EntityDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        refreshEntityList();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "bookingCard");
        mainPanel.add(reportsPanel, "reportsCard");
        mainPanel.add(inventoryPanel, "bookingCard");
      
        typeButtons = new JRadioButton[4];
        typeButtons[0] = hallRadioButton;
        typeButtons[1] = roomRadioButton;
        typeButtons[2] = spaceRadioButton;
        typeButtons[3] = equipmentRadioButton;
        
        bookingSlotButtons = new JRadioButton[2];
        bookingSlotButtons[1] = fullDayRadioButton;
        bookingSlotButtons[0] = halfDayRadioButton;
        
        acButtons = new JRadioButton[2];
        acButtons[1] = acYesRadioButton;
        acButtons[0] = halfDayRadioButton;
        
        statusButtons = new JRadioButton[2];
        statusButtons[1] = activeRadioButton;
        statusButtons[0] = inActiveRadioButton;
        
        bookingButton.doClick();
        typeButtons[0].doClick();
        
//        customizeComponents();
        
        entityDao = new EntityDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        refreshEntityList();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "bookingCard");
        mainPanel.add(reportsPanel, "reportsCard");
        mainPanel.add(inventoryPanel, "bookingCard");
      
        typeButtons = new JRadioButton[4];
        typeButtons[0] = hallRadioButton;
        typeButtons[1] = roomRadioButton;
        typeButtons[2] = spaceRadioButton;
        typeButtons[3] = equipmentRadioButton;
        
        bookingSlotButtons = new JRadioButton[2];
        bookingSlotButtons[1] = fullDayRadioButton;
        bookingSlotButtons[0] = halfDayRadioButton;
        
        acButtons = new JRadioButton[2];
        acButtons[1] = acYesRadioButton;
        acButtons[0] = halfDayRadioButton;
        
        statusButtons = new JRadioButton[2];
        statusButtons[1] = activeRadioButton;
        statusButtons[0] = inActiveRadioButton;
        
        bookingButton.doClick();
        typeButtons[0].doClick();
        
//        customizeComponents();
        
        entityDao = new EntityDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        refreshEntityList();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(bookingPanel, "bookingCard");
        mainPanel.add(reportsPanel, "reportsCard");
        mainPanel.add(inventoryPanel, "bookingCard");
      
        typeButtons = new JRadioButton[4];
        typeButtons[0] = hallRadioButton;
        typeButtons[1] = roomRadioButton;
        typeButtons[2] = spaceRadioButton;
        typeButtons[3] = equipmentRadioButton;
        
        bookingSlotButtons = new JRadioButton[2];
        bookingSlotButtons[1] = fullDayRadioButton;
        bookingSlotButtons[0] = halfDayRadioButton;
        
        acButtons = new JRadioButton[2];
        acButtons[1] = acYesRadioButton;
        acButtons[0] = halfDayRadioButton;
        
        statusButtons = new JRadioButton[2];
        statusButtons[1] = activeRadioButton;
        statusButtons[0] = inActiveRadioButton;
        
        bookingButton.doClick();
        typeButtons[0].doClick();
        
//        customizeComponents();
        
        entityDao = new EntityDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        refreshEntityList();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        typeButtonGroup = new javax.swing.ButtonGroup();
        acButtonGroup = new javax.swing.ButtonGroup();
        bookingSlotButtonGroup = new javax.swing.ButtonGroup();
        statusButtonGroup = new javax.swing.ButtonGroup();
        cardsButtonGroup = new javax.swing.ButtonGroup();
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
        inventoryPanel = new javax.swing.JPanel();
        formPanel = new javax.swing.JPanel();
        typePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        hallRadioButton = new javax.swing.JRadioButton();
        roomRadioButton = new javax.swing.JRadioButton();
        spaceRadioButton = new javax.swing.JRadioButton();
        equipmentRadioButton = new javax.swing.JRadioButton();
        namePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        bookingSlotPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        fullDayRadioButton = new javax.swing.JRadioButton();
        halfDayRadioButton = new javax.swing.JRadioButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 41), new java.awt.Dimension(0, 41), new java.awt.Dimension(32767, 41));
        acPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        acYesRadioButton = new javax.swing.JRadioButton();
        acNoRadioButton = new javax.swing.JRadioButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 41), new java.awt.Dimension(0, 41), new java.awt.Dimension(32767, 41));
        amountPanel = new javax.swing.JPanel();
        amountLabel = new javax.swing.JLabel();
        inUsePanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        availablePanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        amountSpinner = new javax.swing.JSpinner();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 41), new java.awt.Dimension(0, 41), new java.awt.Dimension(32767, 41));
        costPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        costSpinner = new javax.swing.JSpinner();
        chargePanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        chargeSpinner = new javax.swing.JSpinner();
        ratePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        memberRateSpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        nonMemberRateSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        saleRateSpinner = new javax.swing.JSpinner();
        depositPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        memberDepositSpinner = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        nonMemberDepositSpinner = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        saleDepositSpinner = new javax.swing.JSpinner();
        statusPanel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        activeRadioButton = new javax.swing.JRadioButton();
        inActiveRadioButton = new javax.swing.JRadioButton();
        commentsPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commentsField = new javax.swing.JTextArea();
        buttonsPanel = new javax.swing.JPanel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        saveButton = new javax.swing.JButton();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        clearButton = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        deleteButton = new javax.swing.JButton();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        entityListPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        entityJList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        entityComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Booking System");
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        setResizable(false);

        topPanel.setBackground(new java.awt.Color(255, 0, 0));
        topPanel.setMinimumSize(new java.awt.Dimension(396, 50));
        topPanel.setPreferredSize(new java.awt.Dimension(10, 70));
        topPanel.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hall Booking and Billing System");
        topPanel.add(jLabel4, new java.awt.GridBagConstraints());

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        sidePanel.setBackground(new java.awt.Color(0, 102, 153));
        sidePanel.setPreferredSize(new java.awt.Dimension(150, 456));
        sidePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 75, 20));

        cardsButtonGroup.add(bookingButton);
        bookingButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bookingButton.setForeground(new java.awt.Color(255, 255, 255));
        bookingButton.setText("Booking");
        bookingButton.setBorder(null);
        bookingButton.setBorderPainted(false);
        bookingButton.setContentAreaFilled(false);
        bookingButton.setFocusPainted(false);
        bookingButton.setPreferredSize(new java.awt.Dimension(200, 31));
        bookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingButtonActionPerformed(evt);
            }
        });
        sidePanel.add(bookingButton);

        cardsButtonGroup.add(billingButton);
        billingButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        billingButton.setForeground(new java.awt.Color(255, 255, 255));
        billingButton.setText("Billing");
        billingButton.setBorder(null);
        billingButton.setBorderPainted(false);
        billingButton.setContentAreaFilled(false);
        billingButton.setFocusPainted(false);
        billingButton.setPreferredSize(new java.awt.Dimension(200, 31));
        billingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billingButtonActionPerformed(evt);
            }
        });
        sidePanel.add(billingButton);

        cardsButtonGroup.add(reportsButton);
        reportsButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reportsButton.setForeground(new java.awt.Color(255, 255, 255));
        reportsButton.setText("Reports");
        reportsButton.setBorder(null);
        reportsButton.setBorderPainted(false);
        reportsButton.setContentAreaFilled(false);
        reportsButton.setFocusPainted(false);
        reportsButton.setPreferredSize(new java.awt.Dimension(200, 31));
        reportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsButtonActionPerformed(evt);
            }
        });
        sidePanel.add(reportsButton);

        cardsButtonGroup.add(inventoryButton);
        inventoryButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        inventoryButton.setForeground(new java.awt.Color(255, 255, 255));
        inventoryButton.setText("Inventory");
        inventoryButton.setBorder(null);
        inventoryButton.setBorderPainted(false);
        inventoryButton.setContentAreaFilled(false);
        inventoryButton.setFocusPainted(false);
        inventoryButton.setPreferredSize(new java.awt.Dimension(200, 31));
        inventoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryButtonActionPerformed(evt);
            }
        });
        sidePanel.add(inventoryButton);

        cardsButtonGroup.add(exitButton);
        exitButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Exit");
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setPreferredSize(new java.awt.Dimension(200, 31));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        sidePanel.add(exitButton);

        getContentPane().add(sidePanel, java.awt.BorderLayout.LINE_START);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.CardLayout());

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

        inventoryPanel.setOpaque(false);

        formPanel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        formPanel.setOpaque(false);
        formPanel.setPreferredSize(new java.awt.Dimension(900, 501));
        formPanel.setLayout(new java.awt.GridBagLayout());

        typePanel.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Type");

        typeButtonGroup.add(hallRadioButton);
        hallRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        hallRadioButton.setSelected(true);
        hallRadioButton.setText("Hall");
        hallRadioButton.setActionCommand("1");
        hallRadioButton.setName("hallRadioButton"); // NOI18N
        hallRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallRadioButtonActionPerformed(evt);
            }
        });

        typeButtonGroup.add(roomRadioButton);
        roomRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        roomRadioButton.setText("Room");
        roomRadioButton.setActionCommand("2");
        roomRadioButton.setName("roomRadioButton"); // NOI18N
        roomRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomRadioButtonActionPerformed(evt);
            }
        });

        typeButtonGroup.add(spaceRadioButton);
        spaceRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        spaceRadioButton.setText("Space");
        spaceRadioButton.setActionCommand("3");
        spaceRadioButton.setName("equipmentRadioButton"); // NOI18N
        spaceRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spaceRadioButtonActionPerformed(evt);
            }
        });

        typeButtonGroup.add(equipmentRadioButton);
        equipmentRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        equipmentRadioButton.setText("Equipment");
        equipmentRadioButton.setActionCommand("4");
        equipmentRadioButton.setName("equipmentRadioButton"); // NOI18N
        equipmentRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout typePanelLayout = new javax.swing.GroupLayout(typePanel);
        typePanel.setLayout(typePanelLayout);
        typePanelLayout.setHorizontalGroup(
            typePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(hallRadioButton)
                .addGap(18, 18, 18)
                .addComponent(roomRadioButton)
                .addGap(18, 18, 18)
                .addComponent(spaceRadioButton)
                .addGap(18, 18, 18)
                .addComponent(equipmentRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        typePanelLayout.setVerticalGroup(
            typePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(typePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(hallRadioButton)
                    .addComponent(roomRadioButton)
                    .addComponent(equipmentRadioButton)
                    .addComponent(spaceRadioButton))
                .addGap(4, 4, 4))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(typePanel, gridBagConstraints);

        namePanel.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Name");

        nameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nameField.setOpaque(true);
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout namePanelLayout = new javax.swing.GroupLayout(namePanel);
        namePanel.setLayout(namePanelLayout);
        namePanelLayout.setHorizontalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        namePanelLayout.setVerticalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(namePanel, gridBagConstraints);

        bookingSlotPanel.setOpaque(false);
        bookingSlotPanel.setPreferredSize(new java.awt.Dimension(334, 41));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Booking Slot");

        bookingSlotButtonGroup.add(fullDayRadioButton);
        fullDayRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fullDayRadioButton.setSelected(true);
        fullDayRadioButton.setText("Full Day");
        fullDayRadioButton.setActionCommand("true");
        fullDayRadioButton.setName("fullDayRadioButton"); // NOI18N
        fullDayRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullDayRadioButtonActionPerformed(evt);
            }
        });

        bookingSlotButtonGroup.add(halfDayRadioButton);
        halfDayRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        halfDayRadioButton.setText("Half Day");
        halfDayRadioButton.setActionCommand("false");
        halfDayRadioButton.setName("halfDayRadioButton"); // NOI18N
        halfDayRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                halfDayRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bookingSlotPanelLayout = new javax.swing.GroupLayout(bookingSlotPanel);
        bookingSlotPanel.setLayout(bookingSlotPanelLayout);
        bookingSlotPanelLayout.setHorizontalGroup(
            bookingSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingSlotPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(fullDayRadioButton)
                .addGap(18, 18, 18)
                .addComponent(halfDayRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bookingSlotPanelLayout.setVerticalGroup(
            bookingSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookingSlotPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bookingSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fullDayRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(halfDayRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(bookingSlotPanelLayout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(bookingSlotPanel, gridBagConstraints);

        acPanel.setOpaque(false);
        acPanel.setPreferredSize(new java.awt.Dimension(334, 41));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("AC");

        acButtonGroup.add(acYesRadioButton);
        acYesRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        acYesRadioButton.setSelected(true);
        acYesRadioButton.setText("Yes");
        acYesRadioButton.setActionCommand("true");
        acYesRadioButton.setName("fullDayRadioButton"); // NOI18N
        acYesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acYesRadioButtonActionPerformed(evt);
            }
        });

        acButtonGroup.add(acNoRadioButton);
        acNoRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        acNoRadioButton.setText("No");
        acNoRadioButton.setActionCommand("false");
        acNoRadioButton.setName("halfDayRadioButton"); // NOI18N
        acNoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acNoRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout acPanelLayout = new javax.swing.GroupLayout(acPanel);
        acPanel.setLayout(acPanelLayout);
        acPanelLayout.setHorizontalGroup(
            acPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(acYesRadioButton)
                .addGap(18, 18, 18)
                .addComponent(acNoRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        acPanelLayout.setVerticalGroup(
            acPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(acPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(acYesRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acNoRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(acPanelLayout.createSequentialGroup()
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(acPanel, gridBagConstraints);

        amountPanel.setOpaque(false);

        amountLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        amountLabel.setText("Amount");

        inUsePanel.setOpaque(false);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("0");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("In Use:");

        javax.swing.GroupLayout inUsePanelLayout = new javax.swing.GroupLayout(inUsePanel);
        inUsePanel.setLayout(inUsePanelLayout);
        inUsePanelLayout.setHorizontalGroup(
            inUsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inUsePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addContainerGap())
        );
        inUsePanelLayout.setVerticalGroup(
            inUsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inUsePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inUsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        availablePanel.setOpaque(false);
        availablePanel.setPreferredSize(new java.awt.Dimension(115, 31));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("0");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Available:");

        javax.swing.GroupLayout availablePanelLayout = new javax.swing.GroupLayout(availablePanel);
        availablePanel.setLayout(availablePanelLayout);
        availablePanelLayout.setHorizontalGroup(
            availablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, availablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        availablePanelLayout.setVerticalGroup(
            availablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(availablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        amountSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        amountSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        amountSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(amountSpinner, ""));

        javax.swing.GroupLayout amountPanelLayout = new javax.swing.GroupLayout(amountPanel);
        amountPanel.setLayout(amountPanelLayout);
        amountPanelLayout.setHorizontalGroup(
            amountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(amountLabel)
                .addGap(18, 18, 18)
                .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inUsePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(availablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        amountPanelLayout.setVerticalGroup(
            amountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(amountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(availablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(amountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(amountLabel)
                        .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inUsePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(amountPanelLayout.createSequentialGroup()
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(amountPanel, gridBagConstraints);

        costPanel.setOpaque(false);
        costPanel.setPreferredSize(new java.awt.Dimension(241, 41));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Cost Per Unit");

        costSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        costSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout costPanelLayout = new javax.swing.GroupLayout(costPanel);
        costPanel.setLayout(costPanelLayout);
        costPanelLayout.setHorizontalGroup(
            costPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(costSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        costPanelLayout.setVerticalGroup(
            costPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(costPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(costPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(costSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(costPanel, gridBagConstraints);
        amountPanel.setVisible(false);

        chargePanel.setOpaque(false);
        chargePanel.setPreferredSize(new java.awt.Dimension(264, 41));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Charge Per Unit");

        chargeSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chargeSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout chargePanelLayout = new javax.swing.GroupLayout(chargePanel);
        chargePanel.setLayout(chargePanelLayout);
        chargePanelLayout.setHorizontalGroup(
            chargePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chargePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(chargeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chargePanelLayout.setVerticalGroup(
            chargePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chargePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chargePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(chargeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(chargePanel, gridBagConstraints);
        amountPanel.setVisible(false);

        ratePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        ratePanel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ratePanel.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Member");

        memberRateSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        memberRateSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Non-Member");

        nonMemberRateSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nonMemberRateSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Sale");

        saleRateSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saleRateSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout ratePanelLayout = new javax.swing.GroupLayout(ratePanel);
        ratePanel.setLayout(ratePanelLayout);
        ratePanelLayout.setHorizontalGroup(
            ratePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(memberRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(nonMemberRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(saleRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ratePanelLayout.setVerticalGroup(
            ratePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratePanelLayout.createSequentialGroup()
                .addGroup(ratePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ratePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(saleRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ratePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(memberRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(nonMemberRateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(ratePanel, gridBagConstraints);

        depositPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Deposit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        depositPanel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        depositPanel.setOpaque(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Member");

        memberDepositSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        memberDepositSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Non-Member");

        nonMemberDepositSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nonMemberDepositSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Sale");

        saleDepositSpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saleDepositSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout depositPanelLayout = new javax.swing.GroupLayout(depositPanel);
        depositPanel.setLayout(depositPanelLayout);
        depositPanelLayout.setHorizontalGroup(
            depositPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(depositPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(memberDepositSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(nonMemberDepositSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(saleDepositSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        depositPanelLayout.setVerticalGroup(
            depositPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(depositPanelLayout.createSequentialGroup()
                .addGroup(depositPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(memberDepositSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(nonMemberDepositSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(saleDepositSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(depositPanel, gridBagConstraints);

        statusPanel.setOpaque(false);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Status");

        statusButtonGroup.add(activeRadioButton);
        activeRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        activeRadioButton.setSelected(true);
        activeRadioButton.setText("Active");
        activeRadioButton.setActionCommand("1");
        activeRadioButton.setName("fullDayRadioButton"); // NOI18N
        activeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeRadioButtonActionPerformed(evt);
            }
        });

        statusButtonGroup.add(inActiveRadioButton);
        inActiveRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        inActiveRadioButton.setText("Inactive");
        inActiveRadioButton.setActionCommand("0");
        inActiveRadioButton.setName("halfDayRadioButton"); // NOI18N
        inActiveRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inActiveRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(activeRadioButton)
                .addGap(18, 18, 18)
                .addComponent(inActiveRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(activeRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inActiveRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(statusPanel, gridBagConstraints);

        commentsPanel.setOpaque(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Comments");

        commentsField.setColumns(20);
        commentsField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        commentsField.setRows(5);
        jScrollPane1.setViewportView(commentsField);

        javax.swing.GroupLayout commentsPanelLayout = new javax.swing.GroupLayout(commentsPanel);
        commentsPanel.setLayout(commentsPanelLayout);
        commentsPanelLayout.setHorizontalGroup(
            commentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(commentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        commentsPanelLayout.setVerticalGroup(
            commentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(commentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(commentsPanel, gridBagConstraints);

        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new javax.swing.BoxLayout(buttonsPanel, javax.swing.BoxLayout.LINE_AXIS));
        buttonsPanel.add(filler5);

        saveButton.setBackground(new java.awt.Color(0, 204, 0));
        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(saveButton);
        buttonsPanel.add(filler6);

        clearButton.setBackground(new java.awt.Color(0, 0, 153));
        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton.setForeground(new java.awt.Color(255, 255, 255));
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(clearButton);
        buttonsPanel.add(filler7);

        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(filler8);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(buttonsPanel, gridBagConstraints);

        entityListPanel.setOpaque(false);
        entityListPanel.setPreferredSize(new java.awt.Dimension(250, 688));
        entityListPanel.setVerifyInputWhenFocusTarget(false);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane2.setOpaque(false);
        jScrollPane2.setViewportView(entityJList);
        jScrollPane2.getViewport().setOpaque(false);

        entityJList.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entityJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        entityJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        entityJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                entityJListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(entityJList);

        jPanel2.setOpaque(false);

        newButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        newButton.setText("New");
        newButton.setAlignmentX(0.5F);
        newButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setPreferredSize(new java.awt.Dimension(72, 6));
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        entityComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Hall", "Room", "Space", "Equipment" }));
        entityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entityComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entityComboBox)
                    .addComponent(newButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout entityListPanelLayout = new javax.swing.GroupLayout(entityListPanel);
        entityListPanel.setLayout(entityListPanelLayout);
        entityListPanelLayout.setHorizontalGroup(
            entityListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entityListPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(entityListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        entityListPanelLayout.setVerticalGroup(
            entityListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, entityListPanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entityListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1648, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
                    .addComponent(entityListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(inventoryPanel, "inventory");

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

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

    private void entityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entityComboBoxActionPerformed
        refreshEntityList();
    }//GEN-LAST:event_entityComboBoxActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        entityJList.clearSelection();
        clearButton.setEnabled(true);
        deleteButton.setEnabled(false);
        clearButton.doClick();
    }//GEN-LAST:event_newButtonActionPerformed

    private void entityJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_entityJListValueChanged
        int i = entityJList.getSelectedIndex();

        if(i >= 0) {
            Entity entity = entityList.get(i);
            selectedEntityId = entity.getId();
            nameField.setText(entity.getName());

            typeButtons[entity.getType() - 1].doClick();
            bookingSlotButtons[entity.getBookingSlot() ? 1 : 0].setSelected(true);
            acButtons[entity.getAc() ? 1 : 0].setSelected(true);
            amountSpinner.setValue(entity.getAmount());
            costSpinner.setValue(entity.getUnitCost());
            chargeSpinner.setValue(entity.getUnitCharge());
            memberRateSpinner.setValue(entity.getMemberRate());
            nonMemberRateSpinner.setValue(entity.getNonMemberRate());
            saleRateSpinner.setValue(entity.getSaleRate());
            memberDepositSpinner.setValue(entity.getMemberDeposit());
            nonMemberDepositSpinner.setValue(entity.getNonMemberDeposit());
            saleDepositSpinner.setValue(entity.getSaleDeposit());
            statusButtons[entity.getStatus()].setSelected(true);
            commentsField.setText(entity.getComments());

            clearButton.setEnabled(false);
            deleteButton.setEnabled(true);
        }
    }//GEN-LAST:event_entityJListValueChanged

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Confirm Delete?",
            String.valueOf(JOptionPane.WARNING_MESSAGE),JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.YES_OPTION){
            try {
                entityDao.delete(selectedEntityId);
                newButton.doClick();
                refreshEntityList();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        nameField.setText("");
        typeButtons[0].doClick();
        amountSpinner.setValue(1);
        acYesRadioButton.setSelected(true);
        fullDayRadioButton.setSelected(true);
        costSpinner.setValue(0);
        chargeSpinner.setValue(0);
        memberRateSpinner.setValue(0);
        nonMemberRateSpinner.setValue(0);
        saleRateSpinner.setValue(0);
        memberDepositSpinner.setValue(0);
        nonMemberDepositSpinner.setValue(0);
        saleDepositSpinner.setValue(0);
        activeRadioButton.setSelected(true);
        commentsField.setText("");
        nameField.requestFocus();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int index = entityJList.getSelectedIndex();

        if(!Helper.isValidText(nameField.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Name!");
            nameField.requestFocus();
            return;
        }

        Entity entity = new Entity();
        entity.setId(selectedEntityId);
        entity.setName(nameField.getText());
        entity.setType(Integer.parseInt(typeButtonGroup.getSelection().getActionCommand()));
        entity.setBookingSlot(fullDayRadioButton.isSelected());
        entity.setAc(acYesRadioButton.isSelected());
        entity.setAmount((Integer) amountSpinner.getValue());
        entity.setUnitCost((Integer) costSpinner.getValue());
        entity.setUnitCharge((Integer) chargeSpinner.getValue());
        entity.setBookingSlot(fullDayRadioButton.isSelected());
        entity.setMemberRate((int) memberRateSpinner.getValue());
        entity.setNonMemberRate((int) nonMemberRateSpinner.getValue());
        entity.setSaleRate((int) saleRateSpinner.getValue());
        entity.setMemberDeposit((int) memberDepositSpinner.getValue());
        entity.setNonMemberDeposit((int) nonMemberDepositSpinner.getValue());
        entity.setSaleDeposit((int) saleDepositSpinner.getValue());
        entity.setStatus(Integer.parseInt(statusButtonGroup.getSelection().getActionCommand()));
        entity.setComments(commentsField.getText());

        try {
            if(index == -1) {
                entityDao.add(entity);
                refreshEntityList();
                int newEntityIndex = -1;
                for(Entity e : entityList) {
                    newEntityIndex++;
                    if(e.getId() == entity.getId()) {
                        break;
                    }
                }
                entityJList.setSelectedIndex(newEntityIndex);
                entityJList.ensureIndexIsVisible(entityJList.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "Entity Inserted!");
            } else {
                entityDao.update(entity);
                refreshEntityList();
                entityJList.setSelectedIndex(index);
                entityJList.ensureIndexIsVisible(entityJList.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "Entity Updated!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void inActiveRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActiveRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inActiveRadioButtonActionPerformed

    private void activeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_activeRadioButtonActionPerformed

    private void acNoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acNoRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acNoRadioButtonActionPerformed

    private void acYesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acYesRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acYesRadioButtonActionPerformed

    private void halfDayRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_halfDayRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_halfDayRadioButtonActionPerformed

    private void fullDayRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullDayRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullDayRadioButtonActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void equipmentRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentRadioButtonActionPerformed
        amountLabel.setText("Quantity");
        amountPanel.setVisible(true);
        bookingSlotPanel.setVisible(false);
        acPanel.setVisible(false);
        inUsePanel.setVisible(true);
        availablePanel.setVisible(true);
        costPanel.setVisible(true);
        chargePanel.setVisible(true);
    }//GEN-LAST:event_equipmentRadioButtonActionPerformed

    private void spaceRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spaceRadioButtonActionPerformed
        amountLabel.setText("Capacity");
        amountPanel.setVisible(true);
        inUsePanel.setVisible(false);
        availablePanel.setVisible(false);
        bookingSlotPanel.setVisible(true);
        acPanel.setVisible(false);
        inUsePanel.setVisible(false);
        availablePanel.setVisible(false);
        costPanel.setVisible(false);
        chargePanel.setVisible(false);
    }//GEN-LAST:event_spaceRadioButtonActionPerformed

    private void roomRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomRadioButtonActionPerformed
        amountLabel.setText("Capacity");
        amountPanel.setVisible(true);
        bookingSlotPanel.setVisible(true);
        acPanel.setVisible(true);
        inUsePanel.setVisible(false);
        availablePanel.setVisible(false);
        costPanel.setVisible(false);
        chargePanel.setVisible(false);
    }//GEN-LAST:event_roomRadioButtonActionPerformed

    private void hallRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallRadioButtonActionPerformed
        amountLabel.setText("Capacity");
        amountPanel.setVisible(true);
        bookingSlotPanel.setVisible(true);
        acPanel.setVisible(true);
        inUsePanel.setVisible(false);
        availablePanel.setVisible(false);
        costPanel.setVisible(false);
        chargePanel.setVisible(false);
    }//GEN-LAST:event_hallRadioButtonActionPerformed

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
    private javax.swing.ButtonGroup acButtonGroup;
    private javax.swing.JRadioButton acNoRadioButton;
    private javax.swing.JPanel acPanel;
    private javax.swing.JRadioButton acYesRadioButton;
    private javax.swing.JRadioButton activeRadioButton;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JPanel amountPanel;
    private javax.swing.JSpinner amountSpinner;
    private javax.swing.JPanel availablePanel;
    private javax.swing.JToggleButton billingButton;
    private javax.swing.JToggleButton bookingButton;
    private javax.swing.JPanel bookingPanel;
    private javax.swing.ButtonGroup bookingSlotButtonGroup;
    private javax.swing.JPanel bookingSlotPanel;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.ButtonGroup cardsButtonGroup;
    private javax.swing.JPanel chargePanel;
    private javax.swing.JSpinner chargeSpinner;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextArea commentsField;
    private javax.swing.JPanel commentsPanel;
    private javax.swing.JPanel costPanel;
    private javax.swing.JSpinner costSpinner;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel depositPanel;
    private javax.swing.JComboBox<String> entityComboBox;
    private javax.swing.JList<String> entityJList;
    private javax.swing.JPanel entityListPanel;
    private javax.swing.JRadioButton equipmentRadioButton;
    private javax.swing.JToggleButton exitButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JPanel formPanel;
    private javax.swing.JRadioButton fullDayRadioButton;
    private javax.swing.JRadioButton halfDayRadioButton;
    private javax.swing.JRadioButton hallRadioButton;
    private javax.swing.JRadioButton inActiveRadioButton;
    private javax.swing.JPanel inUsePanel;
    private javax.swing.JToggleButton inventoryButton;
    private javax.swing.JPanel inventoryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner memberDepositSpinner;
    private javax.swing.JSpinner memberRateSpinner;
    private javax.swing.JTextField nameField;
    private javax.swing.JPanel namePanel;
    private javax.swing.JButton newButton;
    private javax.swing.JSpinner nonMemberDepositSpinner;
    private javax.swing.JSpinner nonMemberRateSpinner;
    private javax.swing.JPanel ratePanel;
    private javax.swing.JToggleButton reportsButton;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JRadioButton roomRadioButton;
    private javax.swing.JSpinner saleDepositSpinner;
    private javax.swing.JSpinner saleRateSpinner;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JRadioButton spaceRadioButton;
    private javax.swing.ButtonGroup statusButtonGroup;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.ButtonGroup typeButtonGroup;
    private javax.swing.JPanel typePanel;
    // End of variables declaration//GEN-END:variables

    private void refreshEntityList() {
        entityListModel.removeAllElements();
        entityList.clear();
        int typeIndex = entityComboBox.getSelectedIndex();
        try {
            entityList = (ArrayList<Entity>) entityDao.select(selectQuery[typeIndex]);
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(entityList);
        for ( Entity e: entityList) {
            entityListModel.addElement(e.getName());
        }
    }

    private void customizeComponents() {
        try {
            
            Image icon = ImageIO.read(getClass().getResource("/resources/plus-solid.png"));
            newButton.setIcon((Icon)icon.getScaledInstance(newButton.getWidth(), newButton.getHeight(), java.awt.Image.SCALE_SMOOTH));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
