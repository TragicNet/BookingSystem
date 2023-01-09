/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.bookingsystem.InnerPanels;

import com.mycompany.bookingsystem.Helper.Helper;
import static com.mycompany.bookingsystem.Helper.Helper.dateFormat;
import static com.mycompany.bookingsystem.Helper.Helper.getColumn;
import static com.mycompany.bookingsystem.Helper.Helper.setUIFont;
import com.mycompany.bookingsystem.Helper.TableCellRenderer;
import com.mycompany.bookingsystem.Helper.ToolTipHeader;
import com.mycompany.bookingsystem.Launcher;
import com.mycompany.bookingsystem.Models.Booking.Booking;
import com.mycompany.bookingsystem.Models.Booking.BookingDao;
import com.mycompany.bookingsystem.Models.BookingItem.BookingItem;
import com.mycompany.bookingsystem.Models.BookingItem.BookingItemDao;
import com.mycompany.bookingsystem.Models.Entity.Entity;
import com.mycompany.bookingsystem.Models.Entity.EntityDao;
import com.mycompany.bookingsystem.Models.EventType.EventType;
import com.mycompany.bookingsystem.Models.EventType.EventTypeDao;
import com.mycompany.bookingsystem.Models.Settings.Settings;
import com.spire.xls.FileFormat;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author TragicNet
 */
public class BookingPanel extends javax.swing.JPanel {
//    Workbook w;
    private EntityDao entityDao;
    private BookingDao bookingDao;
    private EventTypeDao eventTypeDao;
    private List<Entity> entityList;
    private List<Booking> bookingList;
    private List<EventType> eventTypeList;
    private DefaultListModel<String> entityListModel;
    private DefaultComboBoxModel<String> eventTypeModel;
    private final JFrame frame;
    private int lastSelectedType = -1;
    private static int selectedId = -1;
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
        eventTypeDao = new EventTypeDao();

        try {
            entityList = (ArrayList<Entity>) entityDao.getAll();
            eventTypeList = (ArrayList<EventType>) eventTypeDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        entityListModel = new DefaultListModel<>();
        entityJList.setModel(entityListModel);
        
        eventTypeModel = new DefaultComboBoxModel<>(getColumn(eventTypeList, "name").toArray(new String[0]));
        eventTypeSelect.setModel(eventTypeModel);
        
        columnIndices = new HashMap<>();
        for(int i = 0; i < bookingItemTable.getColumnCount(); i++) {
            columnIndices.put(bookingItemTable.getColumnName(i), i);
        }
        
        String[] toolTips = { "SrNo", "Name", "Start Date", "End Date", "Quantity", "Rate", "Deposit", "Total Rate", "Total Deposit", "Type", "Duration", "AC", "id" };
        bookingItemTable.setTableHeader(new ToolTipHeader(bookingItemTable.getColumnModel(), toolTips));
        
        refreshBookingList();
        resetForm();
    }

    private void resetForm() {
        if(bookingList != null) {
            try {
                if(selectedId != -1) {
                    billNumberEnd.setValue(selectedId);
                } else {
                    List allBookingList = bookingDao.select("select * from booking order by id desc");
                    Booking booking = (Booking) (allBookingList.get(0));
                    billNumberEnd.setValue(booking.getId() + 1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            billNumberEnd.setValue(1);
        }
        billDatePicker.setDate(new Date());
        bookButton.setText("Book");
        nameField.setText("");
        addressField.setText("");
        mobile1Field.setText("");
        mobile2Field.setText("");
        emailField.setText("");
        eventTypeSelect.setSelectedIndex(-1);
        DefaultTableModel model = (DefaultTableModel) bookingItemTable.getModel();
        model.setRowCount(0);
        
        startDatePicker.setDate(new Date());
        endDatePicker.setDate(new Date());
        
        printButton.setEnabled(false);
        deleteButton.setEnabled(false);
        
        memberRadioButton.doClick();
        nameField.requestFocus();
    }
    
    public void reset() {
        this.removeAll();
        
        init();
    }
    
    public void focusIfNew() {
        if(nameField.getText().equals(""))
            nameField.requestFocus();
    }
    
    private void refreshBookingList() {
        DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();
        if(bookingList != null) {
            bookingList.clear();    
            model.setRowCount(0);
        }
        try {
//            bookingList = bookingDao.select("select * from booking where status <> -1 order by id desc limit 1");
            bookingList = bookingDao.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 1;
        for(Booking b : bookingList) {
            Object[] data = new Object[]{i++, b.getName(), b.getId()};
            model.addRow(data);
        }
    }
    
    private void refreshEntityList() {
        entityListModel.removeAllElements();
        entityList.clear();
        int type = typeComboBox.getSelectedIndex() + 1;
        int bookingSlot = durationComboBox.getSelectedIndex();
        boolean fullDay = (bookingSlot == 0);
        boolean ac = acYesRadioButton.isSelected();
        String startDate = Helper.sqlDateFormat.format(startDatePicker.getDate());
        String endDate = Helper.sqlDateFormat.format(endDatePicker.getDate());
        
        try {
            String dateQuery = 
                    (type!=4 ? 
                    "and id not in (" + 
                    "select entity_id FROM booking_item WHERE" +
                    "(select status FROM booking WHERE id = booking_id) <> -1 AND" +
                    "(start_date <= '%4$s' AND end_date >= '%4$s' AND bookingSlot == '%6$d')OR " +
                    "(start_date <= '%5$s' AND end_date >= '%5$s' AND bookingSlot == '%6$d')OR " +
                    "(start_date >= '%4$s' AND end_date <= '%5$s' AND bookingSlot == '%6$d')) "
                    : "");
            String query = String.format("select * from entity e where status <> -1 " +
                    "and type = '%1$d' and booking_slot = '%2$b' and ac = '%3$b' " +
                    dateQuery + "order by name collate nocase", type, fullDay, ac, startDate, endDate, bookingSlot);
            entityList = (ArrayList<Entity>) entityDao.select(query);
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

        for ( Entity e: entityList) {
            entityListModel.addElement(e.getName());
        }
        
        entityJList.setSelectedIndex(0);
    }
    
    private void resetEntitySelectionDialog() {
        durationLabel.setVisible(true);
        durationComboBox.setVisible(true);
        acLabel.setVisible(true);
        acYesRadioButton.setVisible(true);
        acNoRadioButton.setVisible(true);
        entityNameField.setText("");
        quantitySpinner.setValue(0);
        quantitySpinner.setVisible(false);
        amountLabel.setText("Capacity");
        capacityLabel.setText("-");
        capacityLabel.setVisible(true);
        depositLabel.setText("-");
        availableLabel.setText("-");
        commentsField.setText("");
        
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
        jLabel20 = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        rateLabel = new javax.swing.JLabel();
        depositLabel = new javax.swing.JLabel();
        availableLabel = new javax.swing.JLabel();
        capacityLabel = new javax.swing.JLabel();
        entityNameField = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        commentsField = new javax.swing.JLabel();
        optionsPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        acLabel = new javax.swing.JLabel();
        acYesRadioButton = new javax.swing.JRadioButton();
        acNoRadioButton = new javax.swing.JRadioButton();
        durationLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        durationComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        startDatePanel = new javax.swing.JPanel();
        startDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel10 = new javax.swing.JLabel();
        endDatePanel = new javax.swing.JPanel();
        endDatePicker = new org.jdesktop.swingx.JXDatePicker();
        searchButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        entityJList = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        tableContextMenu = new javax.swing.JPopupMenu();
        newMenuItem = new javax.swing.JMenuItem();
        editMenuItem = new javax.swing.JMenuItem();
        removeMenuItem = new javax.swing.JMenuItem();
        bookingListPanel = new javax.swing.JPanel();
        newFormButton = new javax.swing.JButton();
        bookingTableScrollPane = new javax.swing.JScrollPane();
        bookingTable = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bookingFormPanel = new javax.swing.JPanel();
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
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        mobile1Label = new javax.swing.JLabel();
        mobile2Label = new javax.swing.JLabel();
        typePanel = new javax.swing.JPanel();
        memberRadioButton = new javax.swing.JRadioButton();
        nonMemberRadioButton = new javax.swing.JRadioButton();
        saleRadioButton = new javax.swing.JRadioButton();
        typeLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        billNumberPanel = new javax.swing.JPanel();
        billNumberStart = new javax.swing.JLabel();
        billNumberEnd = new javax.swing.JSpinner();
        billDatePanel = new javax.swing.JPanel();
        billDatePicker = new org.jdesktop.swingx.JXDatePicker();
        billNumberLabel = new javax.swing.JLabel();
        billDateLabel = new javax.swing.JLabel();
        eventDetailsPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        eventTypeSelect = new javax.swing.JComboBox<>();
        eventTypeLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        clearButton = new javax.swing.JButton();
        bookButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        bookingItemTableScrollPane = new javax.swing.JScrollPane();
        bookingItemTable = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        entitySelectionDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        entitySelectionDialog.setTitle("Select Item");
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
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Science-Plus2-Math-icon.png"))); // NOI18N
        addButton.setText("Add");
        addButton.setActionCommand("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0};
        jPanel6Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0};
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

        entityNameField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        entityNameField.setText("\"\"\"\"\"\"\"\"\"\"\"\"\"");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(entityNameField, gridBagConstraints);

        jScrollPane2.setBorder(null);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 100));
        jScrollPane2.setViewportView(null);

        commentsField.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        commentsField.setText("\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"");
        commentsField.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        commentsField.setAlignmentX(0.5F);
        jScrollPane2.setViewportView(commentsField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        jPanel6.add(jScrollPane2, gridBagConstraints);

        java.awt.GridBagLayout jPanel5Layout = new java.awt.GridBagLayout();
        jPanel5Layout.columnWidths = new int[] {0, 20, 0, 20, 0, 20, 0};
        jPanel5Layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        jPanel5Layout.columnWeights = new double[] {1.0};
        jPanel5Layout.rowWeights = new double[] {1.0};
        jPanel5.setLayout(jPanel5Layout);

        acLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        acLabel.setText("AC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(acNoRadioButton, gridBagConstraints);

        durationLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        durationLabel.setText("Duration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(durationLabel, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(durationComboBox, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("End Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
            .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(startDatePanel, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Start Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
                .addComponent(endDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(endDatePanel, gridBagConstraints);

        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/01-refresh-icon.png"))); // NOI18N
        searchButton.setPreferredSize(new java.awt.Dimension(32, 32));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel5.add(searchButton, gridBagConstraints);

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
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
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
        setPreferredSize(new java.awt.Dimension(1232, 600));

        bookingListPanel.setOpaque(false);

        newFormButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        newFormButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Document-New-icon.png"))); // NOI18N
        newFormButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFormButtonActionPerformed(evt);
            }
        });

        bookingTableScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(40, 75, 99), 2, true));
        bookingTableScrollPane.setOpaque(false);
        bookingTableScrollPane.setPreferredSize(new java.awt.Dimension(454, 300));
        bookingTableScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingTableScrollPaneMouseClicked(evt);
            }
        });

        bookingTable.setAutoCreateRowSorter(true);
        bookingTable.setBackground(new java.awt.Color(245, 245, 245));
        bookingTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No", "Name", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookingTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bookingTable.setRowHeight(24);
        bookingTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookingTable.getTableHeader().setResizingAllowed(false);
        bookingTable.getTableHeader().setReorderingAllowed(false);
        bookingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingTableMouseClicked(evt);
            }
        });
        bookingTableScrollPane.setViewportView(bookingTable);
        if (bookingTable.getColumnModel().getColumnCount() > 0) {
            bookingTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            bookingTable.getColumnModel().getColumn(0).setMaxWidth(50);
            bookingTable.getColumnModel().getColumn(2).setMinWidth(0);
            bookingTable.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/01-refresh-icon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bookingListPanelLayout = new javax.swing.GroupLayout(bookingListPanel);
        bookingListPanel.setLayout(bookingListPanelLayout);
        bookingListPanelLayout.setHorizontalGroup(
            bookingListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookingListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookingListPanelLayout.createSequentialGroup()
                        .addComponent(bookingTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bookingListPanelLayout.createSequentialGroup()
                        .addComponent(newFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        bookingListPanelLayout.setVerticalGroup(
            bookingListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookingListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFormButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookingTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setOpaque(false);

        bookingFormPanel.setOpaque(false);

        jPanel1.setOpaque(false);
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

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
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(namePanel, gridBagConstraints);

        addressPanel.setOpaque(false);

        addressField.setColumns(20);
        addressField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addressField.setRows(3);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(addressPanel, gridBagConstraints);

        mobile1Panel.setOpaque(false);

        mobile1Field.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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
                .addComponent(mobile1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(mobile1Panel, gridBagConstraints);

        mobile2Panel.setOpaque(false);

        mobile2Field.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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
                .addComponent(mobile2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
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
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(emailPanel, gridBagConstraints);

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        nameLabel.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(nameLabel, gridBagConstraints);

        addressLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addressLabel.setText("Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(addressLabel, gridBagConstraints);

        emailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        emailLabel.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(emailLabel, gridBagConstraints);

        mobile1Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mobile1Label.setText("Mobile 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(mobile1Label, gridBagConstraints);

        mobile2Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mobile2Label.setText("Mobile 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 0);
        jPanel1.add(mobile2Label, gridBagConstraints);

        typePanel.setOpaque(false);

        customerTypeButtonGroup.add(memberRadioButton);
        memberRadioButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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
                .addContainerGap())
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(typePanel, gridBagConstraints);

        typeLabel.setFont(typeLabel.getFont().deriveFont(typeLabel.getFont().getStyle() | java.awt.Font.BOLD));
        typeLabel.setText("Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(typeLabel, gridBagConstraints);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        billNumberPanel.setOpaque(false);
        billNumberPanel.setPreferredSize(new java.awt.Dimension(226, 43));

        billNumberStart.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        billNumberStart.setText("SDP: 21-22/");

        billNumberEnd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        billNumberEnd.setModel(new javax.swing.SpinnerNumberModel(1, 1, 999, 1));
        billNumberEnd.setEditor(new JSpinner.NumberEditor(billNumberEnd, "000"));

        javax.swing.GroupLayout billNumberPanelLayout = new javax.swing.GroupLayout(billNumberPanel);
        billNumberPanel.setLayout(billNumberPanelLayout);
        billNumberPanelLayout.setHorizontalGroup(
            billNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billNumberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billNumberStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(billNumberEnd)
                .addGap(20, 20, 20))
        );
        billNumberPanelLayout.setVerticalGroup(
            billNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billNumberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(billNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(billNumberStart)
                    .addComponent(billNumberEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(billNumberPanel, gridBagConstraints);

        billDatePanel.setOpaque(false);
        billDatePanel.setPreferredSize(new java.awt.Dimension(226, 43));

        billDatePicker.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        billDatePicker.setFormats(Helper.dateFormat);
        billDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billDatePickerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout billDatePanelLayout = new javax.swing.GroupLayout(billDatePanel);
        billDatePanel.setLayout(billDatePanelLayout);
        billDatePanelLayout.setHorizontalGroup(
            billDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        billDatePanelLayout.setVerticalGroup(
            billDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billDatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(billDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel2.add(billDatePanel, gridBagConstraints);

        billNumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        billNumberLabel.setText("Bill Number");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(billNumberLabel, gridBagConstraints);

        billDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        billDateLabel.setText("Bill Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(billDateLabel, gridBagConstraints);

        eventDetailsPanel.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        eventTypeSelect.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        eventTypeSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout eventDetailsPanelLayout = new javax.swing.GroupLayout(eventDetailsPanel);
        eventDetailsPanel.setLayout(eventDetailsPanelLayout);
        eventDetailsPanelLayout.setHorizontalGroup(
            eventDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eventTypeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        eventDetailsPanelLayout.setVerticalGroup(
            eventDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(eventTypeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel2.add(eventDetailsPanel, gridBagConstraints);

        eventTypeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        eventTypeLabel.setText("Event Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(eventTypeLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/cancel-icon.png"))); // NOI18N
        clearButton.setText("Clear");
        clearButton.setPreferredSize(new java.awt.Dimension(105, 32));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(clearButton, gridBagConstraints);

        bookButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bookButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/save-icon.png"))); // NOI18N
        bookButton.setText("Book");
        bookButton.setPreferredSize(new java.awt.Dimension(105, 32));
        bookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(bookButton, gridBagConstraints);

        printButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/printer-icon.png"))); // NOI18N
        printButton.setText("Print");
        printButton.setPreferredSize(new java.awt.Dimension(105, 32));
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(printButton, gridBagConstraints);

        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Editing-Delete-icon.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.setPreferredSize(new java.awt.Dimension(105, 32));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel3.add(deleteButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jPanel3, gridBagConstraints);

        bookingItemTableScrollPane.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(40, 75, 99), 2, true));
        bookingItemTableScrollPane.setOpaque(false);
        bookingItemTableScrollPane.setPreferredSize(new java.awt.Dimension(454, 300));
        bookingItemTableScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingItemTableScrollPaneMouseClicked(evt);
            }
        });

        bookingItemTable.setBackground(new java.awt.Color(245, 245, 245));
        bookingItemTable.setModel(new javax.swing.table.DefaultTableModel(
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
        bookingItemTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bookingItemTable.setDefaultRenderer(Object.class, new TableCellRenderer());
        bookingItemTable.setOpaque(false);
        bookingItemTable.setRowHeight(24);
        bookingItemTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookingItemTable.getTableHeader().setResizingAllowed(false);
        bookingItemTable.getTableHeader().setReorderingAllowed(false);
        bookingItemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingItemTableMouseClicked(evt);
            }
        });
        bookingItemTableScrollPane.setViewportView(bookingItemTable);
        if (bookingItemTable.getColumnModel().getColumnCount() > 0) {
            bookingItemTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            bookingItemTable.getColumnModel().getColumn(0).setMaxWidth(50);
            bookingItemTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            bookingItemTable.getColumnModel().getColumn(2).setMaxWidth(100);
            bookingItemTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            bookingItemTable.getColumnModel().getColumn(3).setMaxWidth(100);
            bookingItemTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            bookingItemTable.getColumnModel().getColumn(4).setMaxWidth(50);
            bookingItemTable.getColumnModel().getColumn(5).setPreferredWidth(70);
            bookingItemTable.getColumnModel().getColumn(5).setMaxWidth(70);
            bookingItemTable.getColumnModel().getColumn(6).setPreferredWidth(70);
            bookingItemTable.getColumnModel().getColumn(6).setMaxWidth(70);
            bookingItemTable.getColumnModel().getColumn(7).setPreferredWidth(100);
            bookingItemTable.getColumnModel().getColumn(7).setMaxWidth(100);
            bookingItemTable.getColumnModel().getColumn(8).setPreferredWidth(100);
            bookingItemTable.getColumnModel().getColumn(8).setMaxWidth(100);
            bookingItemTable.getColumnModel().getColumn(9).setMinWidth(0);
            bookingItemTable.getColumnModel().getColumn(9).setMaxWidth(0);
            bookingItemTable.getColumnModel().getColumn(10).setMinWidth(0);
            bookingItemTable.getColumnModel().getColumn(10).setMaxWidth(0);
            bookingItemTable.getColumnModel().getColumn(11).setMinWidth(0);
            bookingItemTable.getColumnModel().getColumn(11).setMaxWidth(0);
            bookingItemTable.getColumnModel().getColumn(12).setMinWidth(0);
            bookingItemTable.getColumnModel().getColumn(12).setMaxWidth(0);
        }

        javax.swing.GroupLayout bookingFormPanelLayout = new javax.swing.GroupLayout(bookingFormPanel);
        bookingFormPanel.setLayout(bookingFormPanelLayout);
        bookingFormPanelLayout.setHorizontalGroup(
            bookingFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bookingFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookingFormPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bookingFormPanelLayout.createSequentialGroup()
                        .addComponent(bookingItemTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );
        bookingFormPanelLayout.setVerticalGroup(
            bookingFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookingItemTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(bookingFormPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bookingListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookingListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        newMenuItem();
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void newMenuItem() {
        typeComboBox.setSelectedIndex(0);
        durationComboBox.setSelectedIndex(0);
        acYesRadioButton.setSelected(true);
//        resetEntitySelectionDialog();
        
        entityJList.setSelectedIndex(0);
        
        entitySelectionDialog.pack();
        entitySelectionDialog.setLocationRelativeTo(null);
        entitySelectionDialog.setVisible(true);
        
    }

    private void editMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuItemActionPerformed
        editMenuItem();
    }//GEN-LAST:event_editMenuItemActionPerformed

    private void editMenuItem() {
        resetEntitySelectionDialog();
        int r = bookingItemTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) bookingItemTable.getModel();
        
        addButton.setText("Update");
        addButton.setActionCommand("UPDATE");
        
        System.out.println(model.getValueAt(r, columnIndices.get("type")));
        typeComboBox.setSelectedIndex((int) model.getValueAt(r, columnIndices.get("type")) - 1);
        durationComboBox.setSelectedIndex((int) model.getValueAt(r, columnIndices.get("duration")));
        if((boolean) model.getValueAt(r, columnIndices.get("ac"))) {
            acYesRadioButton.setSelected(true);
        } else {
            acNoRadioButton.setSelected(true);
        }
        
//        searchButton.doClick();
        
        durationComboBox.setSelectedIndex((int) model.getValueAt(r, columnIndices.get("duration")));
        
        try {
            startDatePicker.setDate(Helper.dateFormat.parse((String) model.getValueAt(r, columnIndices.get("Start Date"))));
            endDatePicker.setDate(Helper.dateFormat.parse((String) model.getValueAt(r, columnIndices.get("End Date"))));
        } catch (ParseException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int id = (int) model.getValueAt(r, columnIndices.get("id"));
        for(int i = 0; i < entityList.size(); i++) {
            if(entityList.get(i).getId() == id) {
                entityJList.setSelectedIndex(i);
                entityJList.ensureIndexIsVisible(entityJList.getSelectedIndex());
                break;
            }
        }
        
        if(model.getValueAt(r, columnIndices.get("type")).equals(4)) {
            quantitySpinner.setValue(model.getValueAt(r, columnIndices.get("Qty")));
//            System.out.println(model.getValueAt(r, columnIndices.get("duration")));
            
        }
        
        entitySelectionDialog.pack();
        entitySelectionDialog.setLocationRelativeTo(null);
        entitySelectionDialog.setVisible(true);
    }

    private void removeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMenuItemActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "Confirm Remove?",
            String.valueOf(JOptionPane.WARNING_MESSAGE),JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel) bookingItemTable.getModel();
            model.removeRow(bookingItemTable.getSelectedRow());
            for(int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0);
            }
        }
    }//GEN-LAST:event_removeMenuItemActionPerformed

    private void newFormButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFormButtonActionPerformed
        bookingTable.clearSelection();
        selectedId = -1;
        clearButton.setEnabled(true);
        resetForm();
    }//GEN-LAST:event_newFormButtonActionPerformed

    private void bookingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingTableMouseClicked
        switch(evt.getButton()) {
            case MouseEvent.BUTTON1:
                if (evt.getClickCount() == 2) {
                    resetForm();
                    int row = bookingTable.getSelectedRow();

                    DefaultTableModel bookingTableModel = (DefaultTableModel) bookingTable.getModel();
                    selectedId = (int) bookingTableModel.getValueAt(bookingTable.getSelectedRow(), 2);
                    Booking booking = bookingList.get(row);
                    
                    String[] billNumberParts = booking.getBillNumber().split("/");
                    billNumberStart.setText(billNumberParts[0] + '/');
                    billNumberEnd.setValue(Integer.parseInt(billNumberParts[1]));
                    billDatePicker.setDate(booking.getBillDate());
                    switch(booking.getType()) {
                        case 1:
                            memberRadioButton.setSelected(true);
                            break;
                        case 0:
                            nonMemberRadioButton.setSelected(true);
                            break;
                        case -1:
                            saleRadioButton.setSelected(true);
                            break;
                    }
                    nameField.setText(booking.getName());
                    addressField.setText(booking.getAddress());
                    mobile1Field.setText(booking.getMobile1());
                    mobile2Field.setText(booking.getMobile2());
                    emailField.setText(booking.getEmail());
                    eventTypeSelect.setEditable(true);
                    eventTypeSelect.setSelectedItem(booking.getEventDetails());
                    eventTypeSelect.setEditable(false);
                    
                    try {
                        BookingItemDao bookingItemDao = new BookingItemDao(booking.getId());
                        EntityDao entityDao = new EntityDao();
                        DefaultTableModel bookingItemTableModel = (DefaultTableModel) bookingItemTable.getModel();
                        ArrayList<BookingItem> bookingItemList = (ArrayList<BookingItem>) bookingItemDao.getAll();
                        int i = 1;
                        for(BookingItem item : bookingItemList) {
                            int id = item.getEntityId();
                            Entity entity = entityDao.get(id);
                            int srNo = i++;
                            String name = entity.getName();
                            int bookingType = booking.getType();
                            int type = entity.getType();
                            int rate;
                            int deposit;
                            switch(bookingType) {
                                case 1:
                                    rate = entity.getMemberRate();
                                    deposit = entity.getMemberDeposit();
                                    break;
                                case -1:
                                    rate = entity.getSaleRate();
                                    deposit = entity.getSaleDeposit();
                                    break;
                                default:
                                    rate = entity.getNonMemberRate();
                                    deposit = entity.getNonMemberDeposit();
                                    break;
                            }
                            int qty = item.getQuantity();
                            String startDate = Helper.dateFormat.format(item.getStartDate());
                            String endDate = Helper.dateFormat.format(item.getEndDate());
                            int total_rate = rate * qty;
                            int total_deposit = deposit * qty;
                            int duration = item.getBookingSlot();
                            boolean ac = entity.getAc();

                            Object[] data = new Object[]{srNo, name, startDate, endDate, qty, rate, deposit, total_rate, total_deposit, type, duration, ac, id};

                            bookingItemTableModel.addRow(data);
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    bookButton.setText("Save");
                    clearButton.setEnabled(false);
                    printButton.setEnabled(true);
                    deleteButton.setEnabled(true);

                }
                break;
        }
    }//GEN-LAST:event_bookingTableMouseClicked

    private void bookingTableScrollPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingTableScrollPaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingTableScrollPaneMouseClicked

    private void bookingItemTableScrollPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingItemTableScrollPaneMouseClicked
        bookingItemTable.clearSelection();
        editMenuItem.setEnabled(false);
        removeMenuItem.setEnabled(false);
        if(evt.getButton() == MouseEvent.BUTTON1) {
            if(evt.getClickCount() == 2) {
                newMenuItem();
            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            tableContextMenu.show(bookingItemTableScrollPane, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_bookingItemTableScrollPaneMouseClicked

    private void bookingItemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingItemTableMouseClicked
        switch(evt.getButton()) {
            case MouseEvent.BUTTON1:
                if (evt.getClickCount() == 2) {
                    editMenuItem();
                }
                break;
            case MouseEvent.BUTTON3:
                int r = bookingItemTable.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < bookingItemTable.getRowCount()) {
                    bookingItemTable.setRowSelectionInterval(r, r);
                    editMenuItem.setEnabled(true);
                    removeMenuItem.setEnabled(true);
                } else {
                    bookingItemTable.clearSelection();
                    editMenuItem.setEnabled(false);
                    removeMenuItem.setEnabled(false);
                }
                tableContextMenu.show(bookingItemTable, evt.getX(), evt.getY());
                break;
        }
    }//GEN-LAST:event_bookingItemTableMouseClicked

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        printBill();
    }//GEN-LAST:event_printButtonActionPerformed

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

        if(eventTypeSelect.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Invalid Event Type!");
            eventTypeSelect.requestFocus();
            return;
        }

        if(bookingItemTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Entity Selected! Select at least 1");
            return;
        }

        Booking booking = new Booking();

        booking.setBillNumber(billNumberStart.getText() + String.format("%03d", billNumberEnd.getValue()));
        booking.setBillDate(billDatePicker.getDate());
        booking.setType(Integer.parseInt(customerTypeButtonGroup.getSelection().getActionCommand()));
        booking.setName(nameField.getText());
        booking.setAddress(addressField.getText());
        booking.setMobile1(mobile1Field.getText());
        booking.setMobile2(mobile2Field.getText());
        booking.setEmail(emailField.getText());
        booking.setEventDetails((String) eventTypeSelect.getSelectedItem());
        booking.setTotalRate(0);
        booking.setTotalDeposit(0);
        booking.setStatus(0);

        int totalRate = 0, totalDeposit = 0;

        try {
            if(selectedId == -1)
                bookingDao.add(booking);
            else {
                booking.setId(selectedId);
            }
            BookingItemDao bookingItemDao = new BookingItemDao(booking.getId());
            bookingItemDao.delete(booking.getId());
            BookingItem bookingItem;

            for(int r = 0; r < bookingItemTable.getRowCount(); r++) {
                bookingItem = new BookingItem();
                bookingItem.setBookingId(booking.getId());
                bookingItem.setEntityId((int) bookingItemTable.getValueAt(r, columnIndices.get("id")));
                bookingItem.setQuantity((int) bookingItemTable.getValueAt(r, columnIndices.get("Qty")));
                bookingItem.setBookingSlot((int) bookingItemTable.getValueAt(r, columnIndices.get("duration")));

                try {
                    bookingItem.setStartDate(Helper.dateFormat.parse((String) bookingItemTable.getValueAt(r, columnIndices.get("Start Date"))));
                    bookingItem.setEndDate(Helper.dateFormat.parse((String) bookingItemTable.getValueAt(r, columnIndices.get("End Date"))));
                } catch (ParseException ex) {
                    Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                bookingItemDao.add(bookingItem);

                totalRate += (int) bookingItemTable.getValueAt(r, columnIndices.get("Total Rate"));
                totalDeposit += (int) bookingItemTable.getValueAt(r, columnIndices.get("Total Deposit"));
            }

            booking.setTotalRate(totalRate);
            booking.setTotalDeposit(totalDeposit);
            bookingDao.update(booking);

            //            JOptionPane.showMessageDialog(null, "Booking Registered!");
            printButton.setEnabled(true);
            int result = JOptionPane.showConfirmDialog(frame, "Booking Registered! Do You want to Print?", null,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            generateBill();
            if(result == JOptionPane.YES_OPTION){
                printBill();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bookButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();
        selectedId = -1;
        
        
        if(bookingList != null && bookingTable.getSelectedRow() != -1) {
            selectedId = (int) model.getValueAt(bookingTable.getSelectedRow(), 2);
        }
        this.reset();
//        this.revalidate();
        
//        if(selectedId != -1) {
//            bookingTable.setRowSelectionInterval(selectedId, selectedId);
//            billNumberEnd.setValue(selectedId);
//            
//        }
        
    }//GEN-LAST:event_clearButtonActionPerformed

    private void saleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleRadioButtonActionPerformed
        customerTypeButtonListener();
    }//GEN-LAST:event_saleRadioButtonActionPerformed

    private void nonMemberRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonMemberRadioButtonActionPerformed
        customerTypeButtonListener();
    }//GEN-LAST:event_nonMemberRadioButtonActionPerformed

    private void memberRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberRadioButtonActionPerformed
        customerTypeButtonListener();
    }//GEN-LAST:event_memberRadioButtonActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void mobile2FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile2FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile2FieldActionPerformed

    private void mobile1FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile1FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile1FieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshBookingList();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
         int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete the booking?", "Delete Booking", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            int row = bookingTable.getSelectedRow();
            DefaultTableModel bookingTableModel = (DefaultTableModel) bookingTable.getModel();
            selectedId = (int) bookingTableModel.getValueAt(bookingTable.getSelectedRow(), 2);
            Booking booking = bookingList.get(row);
            
            try {
                BookingDao bookingDao = new BookingDao();
                bookingDao.delete(booking.getId());
            } catch (SQLException ex) {
                Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();
            selectedId = -1;


            if(bookingList != null && bookingTable.getSelectedRow() != -1) {
                selectedId = (int) model.getValueAt(bookingTable.getSelectedRow(), 2);
            }
            this.reset();
            
        }
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void billDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billDatePickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billDatePickerActionPerformed

    private void entitySelectionDialogWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_entitySelectionDialogWindowGainedFocus
        frame.toFront();
    }//GEN-LAST:event_entitySelectionDialogWindowGainedFocus

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
            switch (customerType) {
                case 1:
                rateLabel.setText(String.valueOf(entity.getMemberRate()));
                depositLabel.setText(String.valueOf(entity.getMemberDeposit()));
                break;
                case 0:
                rateLabel.setText(String.valueOf(entity.getNonMemberRate()));
                depositLabel.setText(String.valueOf(entity.getNonMemberDeposit()));
                break;
                case -1:
                rateLabel.setText(String.valueOf(entity.getSaleRate()));
                depositLabel.setText(String.valueOf(entity.getSaleDeposit()));
                break;
                default:
                break;
            }

            availableLabel.setText(String.valueOf(0));
            commentsField.setText(entity.getComments());
        }
    }//GEN-LAST:event_entityJListValueChanged

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

    private void endDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endDatePickerActionPerformed
        refreshEntityList();
    }//GEN-LAST:event_endDatePickerActionPerformed

    private void startDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startDatePickerActionPerformed
        refreshEntityList();
    }//GEN-LAST:event_startDatePickerActionPerformed

    private void durationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationComboBoxActionPerformed
        refreshEntityList();
    }//GEN-LAST:event_durationComboBoxActionPerformed

    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboBoxActionPerformed
        durationLabel.setVisible(true);
        durationComboBox.setVisible(true);
        acLabel.setVisible(true);
        acYesRadioButton.setVisible(true);
        acNoRadioButton.setVisible(true);
        entityNameField.setText("");
        quantitySpinner.setValue(0);
        quantitySpinner.setVisible(false);
        amountLabel.setText("Capacity");
        capacityLabel.setText("-");
        capacityLabel.setVisible(true);
        depositLabel.setText("-");
        availableLabel.setText("-");
        commentsField.setText("");

//        entityListModel.removeAllElements();
//        entityList.clear();
        
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
//            durationLabel.setVisible(false);
//            durationComboBox.setVisible(false);
            acLabel.setVisible(false);
            acYesRadioButton.setVisible(false);
            acNoRadioButton.setVisible(false);
            break;
            default:
            break;
        }
        
        refreshEntityList();
        
    }//GEN-LAST:event_typeComboBoxActionPerformed

    private void acNoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acNoRadioButtonActionPerformed
        refreshEntityList();
    }//GEN-LAST:event_acNoRadioButtonActionPerformed

    private void acYesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acYesRadioButtonActionPerformed
        refreshEntityList();
    }//GEN-LAST:event_acYesRadioButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if(!Helper.isValidText(entityNameField.getText())) {
            System.out.println("Not Valid");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) bookingItemTable.getModel();
        int id = entityList.get(entityJList.getSelectedIndex()).getId();
        int srNo = model.getRowCount() + 1;
        String name = entityNameField.getText();
        int rate = Integer.parseInt(rateLabel.getText());
        int deposit = Integer.parseInt(depositLabel.getText());
        int type = typeComboBox.getSelectedIndex() + 1;
        int qty = (type == 4 ? (int) quantitySpinner.getValue() : 1);
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
            int r = bookingItemTable.getSelectedRow();
            for(int i = 0; i < bookingItemTable.getColumnCount(); i++)
            model.setValueAt(data[i], r, i);
        }

        entitySelectionDialog.dispose();

    }//GEN-LAST:event_addButtonActionPerformed

    private void generateBill() {
        File tempFile;
        URL url = ClassLoader.getSystemClassLoader().getResource("res/હોલ બીલ.xlsx");
        System.out.println(url + "\nlength: " + url.toString().length());
        String billTemplatePath = getClass().getClassLoader().getResource("res/NewExcelFile.xls").toString();
        
        String newFileName = "data/NewExcelFile.xlsx";
        System.out.println(billTemplatePath + "\nlength: " + billTemplatePath.length());
        //        addressField.setText(path);

        try {
            tempFile = File.createTempFile("bill", ".xls");
            tempFile.deleteOnExit();

            try (BufferedWriter out = new BufferedWriter (new FileWriter(tempFile))) {
                out.write("Hello World!");
                System.out.println("temporary file created:");
            }

        //            Path temp = Files.createTempFile("resource-", ".ext");
        //            Files.copy(getClass().getResourceAsStream("resource.ext"), temp, StandardCopyOption.REPLACE_EXISTING);
        //            FileInputStream input = new FileInputStream(temp.toFile());

        // FileAsStream()
        //            POIFSFileSystem fs;
        //            fs = new POIFSFileSystem(getClass().getClassLoader().getResourceAsStream("res/હોલ બીલ.xlsx"));
        OPCPackage fs = OPCPackage.open(getClass().getClassLoader().getResourceAsStream("res/હોલ બીલ.xlsx"));
        Workbook workbook = WorkbookFactory.create(fs);

        Sheet sheet = workbook.getSheetAt(0);
        //Retrieve the row and check for null
        //            Row row = sheet.getRow(6);
        //Update the value of cell
        Cell cell;
        cell = sheet.getRow(6).getCell(0);
        cell.setCellValue(cell.getStringCellValue() + " " + nameField.getText());

        cell = sheet.getRow(0).getCell(4);
        cell.setCellValue(cell.getStringCellValue() + String.format("%03d", billNumberEnd.getValue()));
        
        cell = sheet.getRow(1).getCell(4);
        cell.setCellValue(cell.getStringCellValue() + Helper.dateFormat.format(billDatePicker.getDate()));
        
        DefaultTableModel model = (DefaultTableModel) bookingItemTable.getModel();
        System.out.println(model.getValueAt(2, columnIndices.get("Total Rate")));
        for(int i = 0, j = 0; i < model.getRowCount(); i++) {
            if(j == 0 && i > 49) {
                j = 4;
            } else if(j == 4 && i > 49 + 24) {
                // TODO
                break;
            }
            cell = sheet.getRow(9 + i).getCell(j + 0);
            cell.setCellValue((String) model.getValueAt(i, columnIndices.get("Name")));
            
            cell = sheet.getRow(9 + i).getCell(j + 1);
            cell.setCellValue(String.valueOf(model.getValueAt(i, columnIndices.get("Qty"))));
            
            cell = sheet.getRow(9 + i).getCell(j + 2);
            cell.setCellValue(String.valueOf(model.getValueAt(i, columnIndices.get("Rate"))));
            
            cell = sheet.getRow(9 + i).getCell(j + 3);
            cell.setCellValue(String.valueOf(model.getValueAt(i, columnIndices.get("Total Rate"))));
            
        }


        //            newFileName = "હોલ બીલ.xlsx";

        try (final FileOutputStream fileOut = new FileOutputStream(newFileName)) {
        //                workbook.write(fileOut);
        workbook.write(new FileOutputStream(tempFile));

        com.spire.xls.Workbook spireBook = new com.spire.xls.Workbook();
        spireBook.loadFromFile(tempFile.getAbsolutePath());
        spireBook.getConverterSetting().setSheetFitToPage(true);
        
        spireBook.saveToFile(getBillPath(), FileFormat.PDF);

//        printBill();

        workbook.close();
        }catch (Exception ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Your excel file has been generated!");


        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printBill() {
        String src = getBillPath();
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        
        try {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(src);
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
            }
            PDDocument document = PDDocument.load(new File(src));
            printerJob.setPageable(new PDFPageable(document));
        } catch (IOException ex) {
            Logger.getLogger(BookingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException exc) {
                System.out.println(exc);
            }
        }
    }

    private String getBillPath() {
        String billNumber = (billNumberStart.getText().split(" ")[1] + String.format("%03d", billNumberEnd.getValue())).replace('/', '-');
        String src = "data/" + billNumber + ".pdf";
        return src;
    }
    
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
    private org.jdesktop.swingx.JXDatePicker billDatePicker;
    private javax.swing.JSpinner billNumberEnd;
    private javax.swing.JLabel billNumberLabel;
    private javax.swing.JPanel billNumberPanel;
    private javax.swing.JLabel billNumberStart;
    private javax.swing.JButton bookButton;
    private javax.swing.JPanel bookingFormPanel;
    private javax.swing.JTable bookingItemTable;
    private javax.swing.JScrollPane bookingItemTableScrollPane;
    private javax.swing.JPanel bookingListPanel;
    private javax.swing.JTable bookingTable;
    private javax.swing.JScrollPane bookingTableScrollPane;
    private javax.swing.JLabel capacityLabel;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel commentsField;
    private javax.swing.ButtonGroup customerTypeButtonGroup;
    private javax.swing.JButton deleteButton;
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
    private javax.swing.JLabel entityNameField;
    private javax.swing.JDialog entitySelectionDialog;
    private javax.swing.JPanel eventDetailsPanel;
    private javax.swing.JLabel eventTypeLabel;
    private javax.swing.JComboBox<String> eventTypeSelect;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
    private javax.swing.JButton newFormButton;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JRadioButton nonMemberRadioButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JButton printButton;
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
        
        DefaultTableModel model = (DefaultTableModel) bookingItemTable.getModel();
        
        int id, rate = 0, deposit = 0, total_rate = 0, total_deposit = 0;
        Entity entity;
        for(int r = 0; r < bookingItemTable.getRowCount(); r++) {
            id = (int) model.getValueAt(r, columnIndices.get("id"));
            try {
                entity = entityDao.get(id);
                switch (customerType) {
                    case 1:
                        rate = entity.getMemberRate();
                        deposit = entity.getMemberDeposit();
                        break;
                    case 0:
                        rate = entity.getNonMemberRate();
                        deposit = entity.getNonMemberDeposit();
                        break;
                    case -1:
                        rate = entity.getSaleRate();
                        deposit = entity.getSaleDeposit();
                        break;
                    default:
                        break;
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
