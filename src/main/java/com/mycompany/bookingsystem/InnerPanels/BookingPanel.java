/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.bookingsystem.InnerPanels;

import com.mycompany.bookingsystem.Helper.Helper;
import com.mycompany.bookingsystem.Helper.TableCellRenderer;
import com.mycompany.bookingsystem.Launcher;
import com.mycompany.bookingsystem.Models.Booking.Booking;
import com.mycompany.bookingsystem.Models.Booking.BookingDao;
import com.mycompany.bookingsystem.Models.BookingItem.BookingItem;
import com.mycompany.bookingsystem.Models.BookingItem.BookingItemDao;
import com.mycompany.bookingsystem.Models.Entity.Entity;
import com.mycompany.bookingsystem.Models.Entity.EntityDao;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TragicNet
 */
public class BookingPanel extends javax.swing.JPanel {
    
    private EntityDao entityDao;
    private BookingDao bookingDao;
    private List<Entity> entityList;
    private DefaultListModel<String> entityListModel;
    private final JFrame frame;
    private int lastSelectedType = -1;
    private Map<String,Integer> columnIndices;
    
    /**
     * Creates new form InventoryPanel
     */
    public BookingPanel(JFrame frame) {
        this.frame = frame;
        init();
    }
    
    private void init() {
        initComponents();
        
        entityDao = new EntityDao();
        bookingDao = new BookingDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        
        columnIndices =new HashMap<>();
        for(int i = 0; i < entityTable.getColumnCount(); i++) {
            columnIndices.put(entityTable.getColumnName(i),i);
        }
        
        try {
            List bookingList = bookingDao.select("select * from booking order by id desc limit 1");
            if(bookingList.size() > 0) {
                Booking booking = (Booking) (bookingList.get(0));
                billNumberLabel.setText(String.valueOf(booking.getId()));
            } else {
                billNumberLabel.setText("1");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        billDateLabel.setText(Helper.dateFormat.format(new Date()));
        
    }
    
    public void reset() {
        this.removeAll();
        
        init();
    }
    
    private void refreshEntityList() {
        entityListModel.removeAllElements();
        entityList.clear();
        int type = typeComboBox.getSelectedIndex() + 1;
        boolean bookingSlot = (durationComboBox.getSelectedIndex() == 0);
        boolean ac = acYesRadioButton.isSelected();
        try {
            String query = String.format("select * from entity where status <> -1 and type = '%d' and booking_slot = '%b' and ac = '%b' order by name collate nocase", type, bookingSlot, ac);
            entityList = (ArrayList<Entity>) entityDao.select(query);
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        for ( Entity e: entityList) {
            entityListModel.addElement(e.getName());
        }
    }
    
    private void resetEntitySelectionDialog() {
        durationLabel.setVisible(true);
        durationComboBox.setVisible(true);
        acLabel.setVisible(true);
        acYesRadioButton.setVisible(true);
        acNoRadioButton.setVisible(true);
        entityNameField.setText("");
        entityNameField.setEditable(false);
        quantitySpinner.setValue(0);
        quantitySpinner.setVisible(false);
        amountLabel.setText("Capacity");
        capacityLabel.setText("-");
        capacityLabel.setVisible(true);
        depositLabel.setText("-");
        availableLabel.setText("-");
        commentsField.setText("");
        commentsField.setEditable(false);
        
        durationLabel.setVisible(true);
        durationComboBox.setVisible(true);
        acLabel.setVisible(true);
        acYesRadioButton.setVisible(true);
        acNoRadioButton.setVisible(true);
        
        addButton.setText("Add");
        addButton.setActionCommand("ADD");
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

        customerTypeButtonGroup = new javax.swing.ButtonGroup();
        acButtonGroup = new javax.swing.ButtonGroup();
        entitySelectionDialog = new javax.swing.JDialog();
        addButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        entityNameField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentsField = new javax.swing.JTextArea();
        rateLabel = new javax.swing.JLabel();
        depositLabel = new javax.swing.JLabel();
        availableLabel = new javax.swing.JLabel();
        capacityLabel = new javax.swing.JLabel();
        optionsPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        acLabel = new javax.swing.JLabel();
        acYesRadioButton = new javax.swing.JRadioButton();
        acNoRadioButton = new javax.swing.JRadioButton();
        durationLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        durationComboBox = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        startDatePanel = new javax.swing.JPanel();
        startDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel10 = new javax.swing.JLabel();
        endDatePanel = new javax.swing.JPanel();
        endDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane4 = new javax.swing.JScrollPane();
        entityJList = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        tableContextMenu = new javax.swing.JPopupMenu();
        newMenuItem = new javax.swing.JMenuItem();
        editMenuItem = new javax.swing.JMenuItem();
        removeMenuItem = new javax.swing.JMenuItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        namePanel = new javax.swing.JPanel();
        nameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        addressPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressField = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        mobile1Panel = new javax.swing.JPanel();
        mobile1Field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        mobile2Panel = new javax.swing.JPanel();
        mobile2Field = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        emailPanel = new javax.swing.JPanel();
        emailField = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        eventDetailsPanel = new javax.swing.JPanel();
        eventDetailsField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        eventDetailsLabel = new javax.swing.JLabel();
        mobile1Label = new javax.swing.JLabel();
        mobile2Label = new javax.swing.JLabel();
        typePanel = new javax.swing.JPanel();
        memberRadioButton = new javax.swing.JRadioButton();
        nonMemberRadioButton = new javax.swing.JRadioButton();
        saleRadioButton = new javax.swing.JRadioButton();
        typeLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        billNumberPanel = new javax.swing.JPanel();
        billNumberLabel = new javax.swing.JLabel();
        billDatePanel = new javax.swing.JPanel();
        billDateLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        entityTableScrollPane = new javax.swing.JScrollPane();
        entityTable = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jPanel4 = new javax.swing.JPanel();
        bookButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        entitySelectionDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        entitySelectionDialog.setTitle("Select Entity");
        entitySelectionDialog.setBackground(new java.awt.Color(217, 217, 217));
        entitySelectionDialog.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        entitySelectionDialog.setModal(true);
        entitySelectionDialog.setName("EntitySelectionDialog"); // NOI18N
        entitySelectionDialog.setResizable(false);
        entitySelectionDialog.setSize(new java.awt.Dimension(0, 0));
        entitySelectionDialog.setType(java.awt.Window.Type.UTILITY);
        entitySelectionDialog.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                entitySelectionDialogWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        addButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addButton.setText("Add");
        addButton.setActionCommand("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        jPanel6Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel6Layout.columnWeights = new double[] {1.0};
        jPanel6Layout.rowWeights = new double[] {1.0};
        jPanel6.setLayout(jPanel6Layout);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Rate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Comments");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabel14, gridBagConstraints);

        amountLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        amountLabel.setText("Amount");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(amountLabel, gridBagConstraints);

        entityNameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entityNameField.setPreferredSize(new java.awt.Dimension(500, 31));
        entityNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entityNameFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(entityNameField, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("Deposit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabel20, gridBagConstraints);

        quantitySpinner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        quantitySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(quantitySpinner, ""));
        quantitySpinner.setPreferredSize(new java.awt.Dimension(100, 31));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(quantitySpinner, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Available:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabel18, gridBagConstraints);

        commentsField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        commentsField.setRows(5);
        commentsField.setPreferredSize(new java.awt.Dimension(500, 129));
        jScrollPane2.setViewportView(commentsField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane2, gridBagConstraints);

        rateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rateLabel.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel6.add(rateLabel, gridBagConstraints);

        depositLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        depositLabel.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(depositLabel, gridBagConstraints);

        availableLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        availableLabel.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(availableLabel, gridBagConstraints);

        capacityLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        capacityLabel.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(capacityLabel, gridBagConstraints);

        java.awt.GridBagLayout jPanel5Layout = new java.awt.GridBagLayout();
        jPanel5Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0, 20, 0};
        jPanel5Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel5Layout.columnWeights = new double[] {1.0};
        jPanel5Layout.rowWeights = new double[] {1.0};
        jPanel5.setLayout(jPanel5Layout);

        acLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        acLabel.setText("AC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(acLabel, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(acYesRadioButton, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(acNoRadioButton, gridBagConstraints);

        durationLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        durationLabel.setText("Duration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(durationLabel, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jLabel7, gridBagConstraints);

        typeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hall", "Room", "Space", "Equipment" }));
        typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel5.add(typeComboBox, gridBagConstraints);

        durationComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        durationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Full Day", "Half Day (Morning)", "Half Day (Evening)" }));
        durationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel5.add(durationComboBox, gridBagConstraints);

        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel5.add(searchButton, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel5.add(jLabel11, gridBagConstraints);

        startDatePanel.setOpaque(false);

        startDatePicker.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        startDatePicker.setFormats(Helper.dateFormat);
        startDatePicker.setPreferredSize(new java.awt.Dimension(200, 33));
        startDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startDatePickerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout startDatePanelLayout = new javax.swing.GroupLayout(startDatePanel);
        startDatePanel.setLayout(startDatePanelLayout);
        startDatePanelLayout.setHorizontalGroup(
            startDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(startDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );
        startDatePanelLayout.setVerticalGroup(
            startDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startDatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel5.add(startDatePanel, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel5.add(jLabel10, gridBagConstraints);

        endDatePanel.setOpaque(false);

        endDatePicker.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        endDatePicker.setFormats(Helper.dateFormat);
        endDatePicker.setPreferredSize(new java.awt.Dimension(200, 33));
        endDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endDatePickerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout endDatePanelLayout = new javax.swing.GroupLayout(endDatePanel);
        endDatePanel.setLayout(endDatePanelLayout);
        endDatePanelLayout.setHorizontalGroup(
            endDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endDatePanelLayout.createSequentialGroup()
                .addComponent(endDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );
        endDatePanelLayout.setVerticalGroup(
            endDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(endDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel5.add(endDatePanel, gridBagConstraints);

        jScrollPane4.setBorder(null);
        jScrollPane4.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(40, 75, 99), 2, true));
        jScrollPane4.setOpaque(false);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(300, 441));
        jScrollPane2.getViewport().setOpaque(false);

        entityJList.setBorder(null);
        entityJList.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entityJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        entityJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        entityJList.setOpaque(false);
        entityJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                entityJListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(entityJList);

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        java.awt.GridBagLayout jPanel7Layout = new java.awt.GridBagLayout();
        jPanel7Layout.columnWidths = new int[] {0, 20, 0};
        jPanel7Layout.rowHeights = new int[] {0, 10, 0};
        jPanel7.setLayout(jPanel7Layout);

        javax.swing.GroupLayout entitySelectionDialogLayout = new javax.swing.GroupLayout(entitySelectionDialog.getContentPane());
        entitySelectionDialog.getContentPane().setLayout(entitySelectionDialogLayout);
        entitySelectionDialogLayout.setHorizontalGroup(
            entitySelectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entitySelectionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entitySelectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        entitySelectionDialogLayout.setVerticalGroup(
            entitySelectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entitySelectionDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entitySelectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(entitySelectionDialogLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tableContextMenu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        tableContextMenu.add(newMenuItem);

        editMenuItem.setText("Edit");
        editMenuItem.setEnabled(false);
        editMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenuItemActionPerformed(evt);
            }
        });
        tableContextMenu.add(editMenuItem);

        removeMenuItem.setText("Remove");
        removeMenuItem.setEnabled(false);
        removeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMenuItemActionPerformed(evt);
            }
        });
        tableContextMenu.add(removeMenuItem);

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1250, 600));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel3.setPreferredSize(new java.awt.Dimension(0, 720));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        namePanel.setOpaque(false);

        nameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        javax.swing.GroupLayout namePanelLayout = new javax.swing.GroupLayout(namePanel);
        namePanel.setLayout(namePanelLayout);
        namePanelLayout.setHorizontalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        namePanelLayout.setVerticalGroup(
            namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(namePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(namePanel, gridBagConstraints);

        addressPanel.setOpaque(false);

        addressField.setColumns(20);
        addressField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addressField.setRows(5);
        jScrollPane1.setViewportView(addressField);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        javax.swing.GroupLayout addressPanelLayout = new javax.swing.GroupLayout(addressPanel);
        addressPanel.setLayout(addressPanelLayout);
        addressPanelLayout.setHorizontalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        addressPanelLayout.setVerticalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(addressPanel, gridBagConstraints);

        mobile1Panel.setOpaque(false);

        mobile1Field.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mobile1Field.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        mobile1Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile1FieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        javax.swing.GroupLayout mobile1PanelLayout = new javax.swing.GroupLayout(mobile1Panel);
        mobile1Panel.setLayout(mobile1PanelLayout);
        mobile1PanelLayout.setHorizontalGroup(
            mobile1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mobile1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mobile1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mobile1PanelLayout.setVerticalGroup(
            mobile1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mobile1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mobile1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mobile1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(mobile1Panel, gridBagConstraints);

        mobile2Panel.setOpaque(false);

        mobile2Field.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mobile2Field.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        mobile2Field.setPreferredSize(new java.awt.Dimension(202, 31));
        mobile2Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile2FieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mobile2PanelLayout = new javax.swing.GroupLayout(mobile2Panel);
        mobile2Panel.setLayout(mobile2PanelLayout);
        mobile2PanelLayout.setHorizontalGroup(
            mobile2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mobile2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mobile2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mobile2PanelLayout.setVerticalGroup(
            mobile2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mobile2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mobile2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(mobile2Panel, gridBagConstraints);

        emailPanel.setOpaque(false);

        emailField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout emailPanelLayout = new javax.swing.GroupLayout(emailPanel);
        emailPanel.setLayout(emailPanelLayout);
        emailPanelLayout.setHorizontalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        emailPanelLayout.setVerticalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(emailPanel, gridBagConstraints);

        eventDetailsPanel.setOpaque(false);

        eventDetailsField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        eventDetailsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventDetailsFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        javax.swing.GroupLayout eventDetailsPanelLayout = new javax.swing.GroupLayout(eventDetailsPanel);
        eventDetailsPanel.setLayout(eventDetailsPanelLayout);
        eventDetailsPanelLayout.setHorizontalGroup(
            eventDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eventDetailsField, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        eventDetailsPanelLayout.setVerticalGroup(
            eventDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventDetailsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(eventDetailsPanel, gridBagConstraints);

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nameLabel.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(nameLabel, gridBagConstraints);

        addressLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addressLabel.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(addressLabel, gridBagConstraints);

        emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        emailLabel.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(emailLabel, gridBagConstraints);

        eventDetailsLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        eventDetailsLabel.setText("Event Details");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(eventDetailsLabel, gridBagConstraints);

        mobile1Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mobile1Label.setText("Mobile 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(mobile1Label, gridBagConstraints);

        mobile2Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mobile2Label.setText("Mobile 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 38);
        jPanel1.add(mobile2Label, gridBagConstraints);

        typePanel.setOpaque(false);

        customerTypeButtonGroup.add(memberRadioButton);
        memberRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        memberRadioButton.setSelected(true);
        memberRadioButton.setText("Member");
        memberRadioButton.setActionCommand("1");
        memberRadioButton.setName("memberRadioButton"); // NOI18N
        memberRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberRadioButtonActionPerformed(evt);
            }
        });

        customerTypeButtonGroup.add(nonMemberRadioButton);
        nonMemberRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nonMemberRadioButton.setText("Non-Member");
        nonMemberRadioButton.setActionCommand("0");
        nonMemberRadioButton.setName("nonMemberRadioButton"); // NOI18N
        nonMemberRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonMemberRadioButtonActionPerformed(evt);
            }
        });

        customerTypeButtonGroup.add(saleRadioButton);
        saleRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saleRadioButton.setText("Sale/Exhibition");
        saleRadioButton.setActionCommand("-1");
        saleRadioButton.setName("equipmentRadioButton"); // NOI18N
        saleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout typePanelLayout = new javax.swing.GroupLayout(typePanel);
        typePanel.setLayout(typePanelLayout);
        typePanelLayout.setHorizontalGroup(
            typePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(memberRadioButton)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(nonMemberRadioButton)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(saleRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        typePanelLayout.setVerticalGroup(
            typePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(typePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(typePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memberRadioButton)
                    .addComponent(nonMemberRadioButton)
                    .addComponent(saleRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel1.add(typePanel, gridBagConstraints);

        typeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        typeLabel.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(typeLabel, gridBagConstraints);

        jPanel2.setOpaque(false);
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 20, 0};
        jPanel2Layout.rowHeights = new int[] {0, 10, 0, 10, 0};
        jPanel2.setLayout(jPanel2Layout);

        billNumberPanel.setOpaque(false);

        billNumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout billNumberPanelLayout = new javax.swing.GroupLayout(billNumberPanel);
        billNumberPanel.setLayout(billNumberPanelLayout);
        billNumberPanelLayout.setHorizontalGroup(
            billNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billNumberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billNumberLabel)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        billNumberPanelLayout.setVerticalGroup(
            billNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billNumberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(billNumberPanel, gridBagConstraints);

        billDatePanel.setOpaque(false);

        billDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout billDatePanelLayout = new javax.swing.GroupLayout(billDatePanel);
        billDatePanel.setLayout(billDatePanelLayout);
        billDatePanelLayout.setHorizontalGroup(
            billDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billDateLabel)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        billDatePanelLayout.setVerticalGroup(
            billDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(billDatePanel, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Bill Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Bill Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(jLabel8, gridBagConstraints);

        entityTableScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(40, 75, 99), 2, true));
        entityTableScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entityTableScrollPaneMouseClicked(evt);
            }
        });

        entityTable.setBackground(new java.awt.Color(217, 217, 217));
        entityTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        entityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No", "Name", "Start Date", "End Date", "Qty", "Rate", "Deposit", "Total Rate", "Total Deposit", "type", "duration", "ac", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        entityTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        entityTable.setDefaultRenderer(Object.class, new TableCellRenderer());
        entityTable.setRowHeight(24);
        entityTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        entityTable.getTableHeader().setResizingAllowed(false);
        entityTable.getTableHeader().setReorderingAllowed(false);
        entityTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entityTableMouseClicked(evt);
            }
        });
        entityTableScrollPane.setViewportView(entityTable);
        if (entityTable.getColumnModel().getColumnCount() > 0) {
            entityTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            entityTable.getColumnModel().getColumn(0).setMaxWidth(50);
            entityTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            entityTable.getColumnModel().getColumn(2).setMaxWidth(100);
            entityTable.getColumnModel().getColumn(2).setCellRenderer(null);
            entityTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            entityTable.getColumnModel().getColumn(3).setMaxWidth(100);
            entityTable.getColumnModel().getColumn(3).setCellRenderer(null);
            entityTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            entityTable.getColumnModel().getColumn(4).setMaxWidth(50);
            entityTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            entityTable.getColumnModel().getColumn(5).setMaxWidth(100);
            entityTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            entityTable.getColumnModel().getColumn(6).setMaxWidth(100);
            entityTable.getColumnModel().getColumn(7).setPreferredWidth(100);
            entityTable.getColumnModel().getColumn(7).setMaxWidth(100);
            entityTable.getColumnModel().getColumn(8).setPreferredWidth(100);
            entityTable.getColumnModel().getColumn(8).setMaxWidth(100);
            entityTable.getColumnModel().getColumn(9).setMinWidth(0);
            entityTable.getColumnModel().getColumn(9).setMaxWidth(0);
            entityTable.getColumnModel().getColumn(10).setMinWidth(0);
            entityTable.getColumnModel().getColumn(10).setMaxWidth(0);
            entityTable.getColumnModel().getColumn(11).setMinWidth(0);
            entityTable.getColumnModel().getColumn(11).setMaxWidth(0);
            entityTable.getColumnModel().getColumn(12).setMinWidth(0);
            entityTable.getColumnModel().getColumn(12).setMaxWidth(0);
        }

        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 20, 0, 20, 0};
        jPanel4Layout.rowHeights = new int[] {0, 10, 0, 10, 0};
        jPanel4.setLayout(jPanel4Layout);

        bookButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bookButton.setText("Book");
        bookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(bookButton, gridBagConstraints);

        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(clearButton, gridBagConstraints);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(entityTableScrollPane)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(entityTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel3);

        add(jScrollPane3);
    }// </editor-fold>//GEN-END:initComponents

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if(!Helper.isValidText(entityNameField.getText())) {
            System.out.println("Not Valid");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) entityTable.getModel();
        int id = entityList.get(entityJList.getSelectedIndex()).getId();
        int srNo = model.getRowCount() + 1;
        String name = entityNameField.getText();
        int rate = Integer.parseInt(rateLabel.getText());
        int deposit = Integer.parseInt(depositLabel.getText());
        int type = typeComboBox.getSelectedIndex();
        int qty = (type == 3 ? (int) quantitySpinner.getValue() : 1);
        String startDate = Helper.dateFormat.format(startDatePicker.getDate());
        String endDate = Helper.dateFormat.format(endDatePicker.getDate());
        int total_rate = rate * qty;
        int total_deposit = deposit * qty;
        int duration = durationComboBox.getSelectedIndex();
        boolean ac = acYesRadioButton.isSelected();
        
        Object[] data = new Object[]{srNo, name, startDate, endDate, qty, rate, deposit, total_rate, total_deposit, type, duration, ac, id};
        
        if(addButton.getActionCommand().equals("ADD")) {
            model.addRow(data);
        } else if(addButton.getActionCommand().equals("UPDATE")) {
            int r = entityTable.getSelectedRow();
            for(int i = 0; i < entityTable.getColumnCount(); i++)
                model.setValueAt(data[i], r, i);
        }
        
        entitySelectionDialog.dispose();
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void entitySelectionDialogWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_entitySelectionDialogWindowGainedFocus
        frame.toFront();
    }//GEN-LAST:event_entitySelectionDialogWindowGainedFocus

    private void mobile1FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile1FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile1FieldActionPerformed

    private void mobile2FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile2FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile2FieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void eventDetailsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventDetailsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventDetailsFieldActionPerformed

    private void saleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleRadioButtonActionPerformed
        customerTypeButtonListener();
    }//GEN-LAST:event_saleRadioButtonActionPerformed

    private void nonMemberRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonMemberRadioButtonActionPerformed
        customerTypeButtonListener();
    }//GEN-LAST:event_nonMemberRadioButtonActionPerformed

    private void memberRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberRadioButtonActionPerformed
        customerTypeButtonListener();
    }//GEN-LAST:event_memberRadioButtonActionPerformed

    private void entityTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entityTableMouseClicked
        switch(evt.getButton()) {
            case MouseEvent.BUTTON1:
                if (evt.getClickCount() == 2) {
                    int row = entityTable.getSelectedRow();
                    entitySelectionDialog.pack();
                    entitySelectionDialog.setLocationRelativeTo(null);
                    entitySelectionDialog.setVisible(true);
                }
                break;
            case MouseEvent.BUTTON3:
                int r = entityTable.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < entityTable.getRowCount()) {
                    entityTable.setRowSelectionInterval(r, r);
                    editMenuItem.setEnabled(true);
                    removeMenuItem.setEnabled(true);
                } else {
                    entityTable.clearSelection();
                    editMenuItem.setEnabled(false);
                    removeMenuItem.setEnabled(false);
                }
                tableContextMenu.show(entityTable, evt.getX(), evt.getY());
                break;
        }
    }//GEN-LAST:event_entityTableMouseClicked

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        typeComboBox.setSelectedIndex(0);
        durationComboBox.setSelectedIndex(0);
        acYesRadioButton.setSelected(true);
        resetEntitySelectionDialog();
        
        entitySelectionDialog.pack();
        entitySelectionDialog.setLocationRelativeTo(null);
        entitySelectionDialog.setVisible(true);
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void acYesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acYesRadioButtonActionPerformed
        
    }//GEN-LAST:event_acYesRadioButtonActionPerformed

    private void acNoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acNoRadioButtonActionPerformed
        
    }//GEN-LAST:event_acNoRadioButtonActionPerformed

    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboBoxActionPerformed
        durationLabel.setVisible(true);
        durationComboBox.setVisible(true);
        acLabel.setVisible(true);
        acYesRadioButton.setVisible(true);
        acNoRadioButton.setVisible(true);
        entityNameField.setText("");
        entityNameField.setEditable(false);
        quantitySpinner.setValue(0);
        quantitySpinner.setVisible(false);
        amountLabel.setText("Capacity");
        capacityLabel.setText("-");
        capacityLabel.setVisible(true);
        depositLabel.setText("-");
        availableLabel.setText("-");
        commentsField.setText("");
        commentsField.setEditable(false);
        
        entityListModel.removeAllElements();
        entityList.clear();
        
        int type = typeComboBox.getSelectedIndex();
        switch (type) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                acLabel.setVisible(false);
                acYesRadioButton.setVisible(false);
                acNoRadioButton.setVisible(false);
                break;
            case 3:
                amountLabel.setText("Quantity");
                quantitySpinner.setVisible(true);
                capacityLabel.setVisible(false);
                durationLabel.setVisible(false);
                durationComboBox.setVisible(false);
                acLabel.setVisible(false);
                acYesRadioButton.setVisible(false);
                acNoRadioButton.setVisible(false);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_typeComboBoxActionPerformed

    private void editMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuItemActionPerformed
        resetEntitySelectionDialog();
        int r = entityTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) entityTable.getModel();
        
        addButton.setText("Update");
        addButton.setActionCommand("UPDATE");
        
        typeComboBox.setSelectedIndex((int) model.getValueAt(r, columnIndices.get("type")));
        durationComboBox.setSelectedIndex((int) model.getValueAt(r, columnIndices.get("duration")));
        if((boolean) model.getValueAt(r, columnIndices.get("ac"))) {
            acYesRadioButton.setSelected(true);
        } else {
            acNoRadioButton.setSelected(true);
        }
        
        searchButton.doClick();
        int id = (int) model.getValueAt(r, columnIndices.get("id"));
        for(int i = 0; i < entityList.size(); i++) {
            if(entityList.get(i).getId() == id) {
                entityJList.setSelectedIndex(i);
                entityJList.ensureIndexIsVisible(entityJList.getSelectedIndex());
                break;
            }
        }
        
        if(model.getValueAt(r, columnIndices.get("type")).equals(3)) {
            quantitySpinner.setValue(model.getValueAt(r, columnIndices.get("Qty")));
        }
        
        try {
            startDatePicker.setDate(Helper.dateFormat.parse((String) model.getValueAt(r, columnIndices.get("Start Date"))));
            endDatePicker.setDate(Helper.dateFormat.parse((String) model.getValueAt(r, columnIndices.get("End Date"))));
        } catch (ParseException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        entitySelectionDialog.pack();
        entitySelectionDialog.setLocationRelativeTo(null);
        entitySelectionDialog.setVisible(true);
    }//GEN-LAST:event_editMenuItemActionPerformed

    private void removeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMenuItemActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Confirm Remove?",
            String.valueOf(JOptionPane.WARNING_MESSAGE),JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel) entityTable.getModel();
            model.removeRow(entityTable.getSelectedRow());
            for(int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }
        }
    }//GEN-LAST:event_removeMenuItemActionPerformed

    private void entityTableScrollPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entityTableScrollPaneMouseClicked
        entityTable.clearSelection();
        editMenuItem.setEnabled(false);
        removeMenuItem.setEnabled(false);
        if (evt.getButton() == MouseEvent.BUTTON3) {
            tableContextMenu.show(entityTableScrollPane, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_entityTableScrollPaneMouseClicked

    private void entityNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entityNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entityNameFieldActionPerformed

    private void entityJListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_entityJListValueChanged
        int i = entityJList.getSelectedIndex();

        if(i >= 0) {
            Entity entity = entityList.get(i);
            int type = entity.getType();
            
            entityNameField.setText(entity.getName());
            
            if(type == 4) {
                quantitySpinner.setValue(1);
            } else {
                capacityLabel.setText(String.valueOf(entity.getAmount()));
            }
            
            int customerType = Integer.parseInt(customerTypeButtonGroup.getSelection().getActionCommand());
            if(customerType == 1) {
                rateLabel.setText(String.valueOf(entity.getMemberRate()));
                depositLabel.setText(String.valueOf(entity.getMemberDeposit()));
            } else if(customerType == 0) {
                rateLabel.setText(String.valueOf(entity.getNonMemberRate()));
                depositLabel.setText(String.valueOf(entity.getNonMemberDeposit()));
            } else if(customerType == -1) {
                rateLabel.setText(String.valueOf(entity.getSaleRate()));
                depositLabel.setText(String.valueOf(entity.getSaleDeposit()));
            }
            
            availableLabel.setText(String.valueOf(0));
            commentsField.setText(entity.getComments());
        }
    }//GEN-LAST:event_entityJListValueChanged

    private void durationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationComboBoxActionPerformed

    }//GEN-LAST:event_durationComboBoxActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        
        if(startDatePicker.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Invalid Start Date!");
            startDatePicker.requestFocus();
            return;
        }
        
        if(endDatePicker.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Invalid End Date!");
            endDatePicker.requestFocus();
            return;
        }
        
        refreshEntityList();
        entityJList.setSelectedIndex(0);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void bookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookButtonActionPerformed
        if(!Helper.isValidText(nameField.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Name!");
            nameField.requestFocus();
            return;
        }
        
        if(!Helper.isValidPhone(mobile1Field.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Mobile 1!");
            mobile1Field.requestFocus();
            return;
        }
        
        if(!mobile2Field.getText().equals("") && !Helper.isValidPhone(mobile2Field.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Mobile 2!");
            mobile2Field.requestFocus();
            return;
        }
        
        if(!Helper.isValidText(eventDetailsField.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Event Details!");
            eventDetailsField.requestFocus();
            return;
        }
        
        if(entityTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Entity Selected! Select at least 1");
            return;
        }
        
        Booking booking = new Booking();
        
        booking.setBillNumber(billNumberLabel.getText());
        booking.setBillDate(new Date());
        booking.setType(Integer.parseInt(customerTypeButtonGroup.getSelection().getActionCommand()));
        booking.setName(nameField.getText());
        booking.setAddress(addressField.getText());
        booking.setMobile1(mobile1Field.getText());
        booking.setMobile2(mobile2Field.getText());
        booking.setAddress(addressField.getText());
        booking.setEventDetails(eventDetailsField.getText());
        booking.setTotalRate(0);
        booking.setTotalDeposit(0);
        booking.setStatus(0);
        
        int totalRate = 0, totalDeposit = 0;
        
        try {
            bookingDao.add(booking);
            BookingItemDao bookingItemDao = new BookingItemDao();
            BookingItem bookingItem;
            
            for(int r = 0; r < entityTable.getRowCount(); r++) {
                bookingItem = new BookingItem();
                bookingItem.setBookingId(booking.getId());
                bookingItem.setEntityId((int) entityTable.getValueAt(r, columnIndices.get("id")));
                bookingItem.setQuantity((int) entityTable.getValueAt(r, columnIndices.get("Qty")));
                
                try {
                    bookingItem.setStartDate(Helper.dateFormat.parse((String) entityTable.getValueAt(r, columnIndices.get("Start Date"))));
                    bookingItem.setEndDate(Helper.dateFormat.parse((String) entityTable.getValueAt(r, columnIndices.get("End Date"))));
                } catch (ParseException ex) {
                    Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                bookingItemDao.add(bookingItem);
                
                totalRate += (int) entityTable.getValueAt(r, columnIndices.get("Total Rate"));
                totalDeposit += (int) entityTable.getValueAt(r, columnIndices.get("Total Deposit"));
            }
            
            booking.setTotalRate(totalRate);
            booking.setTotalDeposit(totalDeposit);
            bookingDao.update(booking);
            
            JOptionPane.showMessageDialog(null, "Booking Registered!");
        } catch (SQLException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bookButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        this.reset();
        this.revalidate();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void endDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDatePickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endDatePickerActionPerformed

    private void startDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDatePickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startDatePickerActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup acButtonGroup;
    private javax.swing.JLabel acLabel;
    private javax.swing.JRadioButton acNoRadioButton;
    private javax.swing.JRadioButton acYesRadioButton;
    private javax.swing.JButton addButton;
    private javax.swing.JTextArea addressField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JPanel addressPanel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel availableLabel;
    private javax.swing.JLabel billDateLabel;
    private javax.swing.JPanel billDatePanel;
    private javax.swing.JLabel billNumberLabel;
    private javax.swing.JPanel billNumberPanel;
    private javax.swing.JButton bookButton;
    private javax.swing.JLabel capacityLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextArea commentsField;
    private javax.swing.ButtonGroup customerTypeButtonGroup;
    private javax.swing.JLabel depositLabel;
    private javax.swing.JComboBox<String> durationComboBox;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JMenuItem editMenuItem;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPanel emailPanel;
    private javax.swing.JPanel endDatePanel;
    private org.jdesktop.swingx.JXDatePicker endDatePicker;
    private javax.swing.JList<String> entityJList;
    private javax.swing.JTextField entityNameField;
    private javax.swing.JDialog entitySelectionDialog;
    private javax.swing.JTable entityTable;
    private javax.swing.JScrollPane entityTableScrollPane;
    private javax.swing.JTextField eventDetailsField;
    private javax.swing.JLabel eventDetailsLabel;
    private javax.swing.JPanel eventDetailsPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton memberRadioButton;
    private javax.swing.JTextField mobile1Field;
    private javax.swing.JLabel mobile1Label;
    private javax.swing.JPanel mobile1Panel;
    private javax.swing.JTextField mobile2Field;
    private javax.swing.JLabel mobile2Label;
    private javax.swing.JPanel mobile2Panel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel namePanel;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JRadioButton nonMemberRadioButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JLabel rateLabel;
    private javax.swing.JMenuItem removeMenuItem;
    private javax.swing.JRadioButton saleRadioButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel startDatePanel;
    private org.jdesktop.swingx.JXDatePicker startDatePicker;
    private javax.swing.JPopupMenu tableContextMenu;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JPanel typePanel;
    // End of variables declaration//GEN-END:variables

    private void customerTypeButtonListener() {
        int customerType = Integer.parseInt(customerTypeButtonGroup.getSelection().getActionCommand());
        
        if(lastSelectedType == customerType) {
            return;
        }
        
        lastSelectedType = customerType;
        
        DefaultTableModel model = (DefaultTableModel) entityTable.getModel();
        
        int id, rate = 0, deposit = 0, total_rate = 0, total_deposit = 0;
        Entity entity;
        for(int r = 0; r < entityTable.getRowCount(); r++) {
            id = (int) model.getValueAt(r, columnIndices.get("id"));
            try {
                entity = entityDao.get(id);
                if(customerType == 1) {
                    rate = entity.getMemberRate();
                    deposit = entity.getMemberDeposit();
                } else if(customerType == 0) {
                    rate = entity.getNonMemberRate();
                    deposit = entity.getNonMemberDeposit();
                } else if(customerType == -1) {
                    rate = entity.getSaleRate();
                    deposit = entity.getSaleDeposit();
                }

                model.setValueAt(rate, r, 3);
                model.setValueAt(deposit, r, 4);
                model.setValueAt(total_rate, r, 5);
                model.setValueAt(total_deposit, r, 6);
            } catch (SQLException ex) {
                Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
