/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author chain
 */
public class program extends javax.swing.JFrame {

    /**
     * Creates new form programmain
     */
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private String username;
    private String firstname;
    private ArrayList<ArrayList<subject>> TableSubjects = new ArrayList<ArrayList<subject>>();

    public program(String username) {
        initComponents();
        this.username = username;
        this.firstname = connect.getFirstnameFromUsername(username);
        hi.setText("Hi , " + this.firstname);
        con = connect.ConnectDB();
        showDataToTable("subjects", "`ID`,`subject`", Tbsubname);
        showDataToTable("professor", "`professor`", Tbprofessor);
        showDataToTableSubject(0, Tbsubject);
        showDataToCombo("subjects", "subject", Combosubject);
        showDataToCombo("professor", "professor", Comboprofessor);
        addTimeTocombo(Combotimelec1, LocalTime.of(6, 0), LocalTime.of(23, 30), 30);
        addTimeTocombo(Combotimelab1, LocalTime.of(6, 0), LocalTime.of(23, 30), 30);
    }

    private program() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void showDataToTableSubject(int tableNO, JTable table) {
        String code = "select `ID`,`subject`,`credit`,`G.Lec`, `day(g.lec)`, "
                + "`timeS(g.lec)`, `timeE(g.lec)`, `G.Lab`, `day(g.lab)`, "
                + "`timeS(g.lab)`, `timeE(g.lab)`, `professor` from `"
                + this.firstname + "` where `tableNO` = " + tableNO;
        try {
            ps = con.prepareStatement(code);
            rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
            setColumnWidths(table, 30);
        } catch (SQLException ex) {
            Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showDataToTableSubject2(int tableNO, JTable table) {
        String code = "select `subject`,`credit`,`G.Lec`, `day(g.lec)`, "
                + "`timeS(g.lec)`, `timeE(g.lec)`, `G.Lab`, `day(g.lab)`, "
                + "`timeS(g.lab)`, `timeE(g.lab)`, `professor` from `"
                + this.firstname + "` where `tableNO` = " + tableNO;
        try {
            ps = con.prepareStatement(code);
            rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void setColumnWidths(JTable table, int... widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            } else {
                break;
            }
        }
    }

    private void showDataToTable(String data, String column, JTable table) {
        String code = "select " + column + " from `" + data + "`";
        try {
            ps = con.prepareStatement(code);
            rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showDataToCombo(String data, String column, JComboBox combo) {
        String code = "select `" + column + "` from `" + data + "`";
        try {
            ps = con.prepareStatement(code);
            rs = ps.executeQuery();
            combo.removeAllItems();
            while (rs.next()) {
                combo.addItem(rs.getString(column));
            }
        } catch (SQLException ex) {
            Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addDataToTable(String[] data, String table, String tablecolumn, String values) {
        String code = "INSERT INTO `" + table + "`(" + tablecolumn + ") VALUES (" + values + ")";
        try {
            ps = con.prepareStatement(code);
            for (int i = 0; i < data.length; i++) {
                ps.setString(i + 1, data[i]);
            }
            if (ps.executeUpdate() == 0) {
                JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*private void refreshData(List<?> list, Query query) {
    list.clear();
    list.addAll(query.getResultList());
    }*/
    private void deleteData(String data, String tablename, String tablecolum) {
        String code = "DELETE FROM `" + tablename + "` WHERE " + tablecolum + " = " + data;
        try {
            ps = con.prepareStatement(code);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteData2() {
        String code = "DELETE FROM `" + this.firstname + "` WHERE `tableNO` != 0";
        try {
            ps = con.prepareStatement(code);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*private void addTimeToArrayList(ArrayList listTime, LocalTime Tstart, LocalTime Tend, int lenght){
    listTime.clear();
    listTime.add(Tstart);
    while (Tstart.plusMinutes(lenght).compareTo(Tend) <= 0) {
    listTime.add(Tstart.plusMinutes(lenght));
    if (Tstart.plusMinutes(lenght).compareTo(Tend) == 0) {
    break;
    }
    Tstart = Tstart.plusMinutes(lenght);
    }
    }*/
    private void addTimeTocombo(JComboBox combo, LocalTime Tstart, LocalTime Tend, int lenght) {
        combo.removeAllItems();
        combo.addItem(Tstart);
        while (Tstart.plusMinutes(lenght).compareTo(Tend) <= 0) {
            combo.addItem(Tstart.plusMinutes(lenght));
            if (Tstart.plusMinutes(lenght).compareTo(Tend) == 0) {
                break;
            }
            Tstart = Tstart.plusMinutes(lenght);
        }
    }

    private void addTimeTocomboBetween(JComboBox combo, LocalTime Tstart, LocalTime Tend, LocalTime Tbe1, LocalTime Tbe2, int lenght) {
        combo.removeAllItems();
        if (Tstart.compareTo(Tbe1) != 0) {
            combo.addItem(Tstart);
        }
        while (Tstart.plusMinutes(lenght).compareTo(Tend) <= 0) {
            if (!(Tstart.plusMinutes(lenght).compareTo(Tbe1) >= 0 && Tstart.plusMinutes(lenght).compareTo(Tbe2) <= 0)) {
                combo.addItem(Tstart.plusMinutes(lenght));
            }
            if (Tstart.plusMinutes(lenght).compareTo(Tend) == 0) {
                break;
            }
            Tstart = Tstart.plusMinutes(lenght);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        hi = new javax.swing.JLabel();
        REMSubject1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TFsubname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        AddSubname = new javax.swing.JButton();
        REMSubname = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tbsubname = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tbprofessor = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        TFprofessor = new javax.swing.JTextField();
        Addprofessor = new javax.swing.JButton();
        REMprofessor = new javax.swing.JButton();
        TFid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbsubject = new javax.swing.JTable();
        Combosubject = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Comboprofessor = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        TFlec = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ComboDaylec = new javax.swing.JComboBox<>();
        TFlab = new javax.swing.JTextField();
        ComboDaylab = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Combotimelec1 = new javax.swing.JComboBox<>();
        AddSubject = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        Combotimelec2 = new javax.swing.JComboBox<>();
        Combotimelab1 = new javax.swing.JComboBox<>();
        Combotimelab2 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        REMSubject = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        Btgeneratetable = new javax.swing.JButton();
        TFcredit = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        TFtotalcredit = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        RESetid = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tbsubgenerate = new javax.swing.JTable();
        Combosubgenerate = new javax.swing.JComboBox<>();
        timetable1 = new Project.timetable();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        hi.setFont(new java.awt.Font("Kanit", 2, 24)); // NOI18N
        hi.setForeground(new java.awt.Color(255, 255, 255));
        hi.setText(" name");

        REMSubject1.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        REMSubject1.setText("Log out");
        REMSubject1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        REMSubject1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMSubject1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hi, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(REMSubject1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(REMSubject1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Subject name");

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Professor");

        TFsubname.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        TFsubname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFsubnameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Subject name");

        AddSubname.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        AddSubname.setText("ADD");
        AddSubname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSubnameActionPerformed(evt);
            }
        });

        REMSubname.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        REMSubname.setText("REMOVE");
        REMSubname.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        REMSubname.setEnabled(false);
        REMSubname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMSubnameActionPerformed(evt);
            }
        });

        Tbsubname.setAutoCreateRowSorter(true);
        Tbsubname.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        Tbsubname.setAlignmentY(5.0F);
        Tbsubname.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tbsubname.setRowHeight(24);
        Tbsubname.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Tbsubname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbsubnameMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Tbsubname);

        Tbprofessor.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        Tbprofessor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tbprofessor.setRowHeight(24);
        Tbprofessor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Tbprofessor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbprofessorMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Tbprofessor);

        jLabel4.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Name");

        TFprofessor.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        TFprofessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFprofessorActionPerformed(evt);
            }
        });

        Addprofessor.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Addprofessor.setText("ADD");
        Addprofessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddprofessorActionPerformed(evt);
            }
        });

        REMprofessor.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        REMprofessor.setText("REMOVE");
        REMprofessor.setEnabled(false);
        REMprofessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMprofessorActionPerformed(evt);
            }
        });

        TFid.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        TFid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFidActionPerformed(evt);
            }
        });
        TFid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFidKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(REMprofessor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(REMSubname, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(46, 46, 46)
                                .addComponent(TFid, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddSubname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(TFsubname, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Addprofessor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TFprofessor))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(REMSubname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFsubname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSubname))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(REMprofessor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFprofessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Addprofessor)
                        .addGap(0, 289, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ADD", jPanel3);

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        Tbsubject.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Tbsubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tbsubject.setRowHeight(21);
        Tbsubject.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Tbsubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbsubjectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tbsubject);

        Combosubject.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Subject name");

        jLabel7.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Professor");

        Comboprofessor.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        Comboprofessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboprofessorActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("G.Lec");

        TFlec.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        TFlec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFlec.setEnabled(false);
        TFlec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFlecKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Day(g.lec)");

        ComboDaylec.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        ComboDaylec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }));
        ComboDaylec.setEnabled(false);
        ComboDaylec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboDaylecActionPerformed(evt);
            }
        });

        TFlab.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        TFlab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFlab.setEnabled(false);
        TFlab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFlabKeyTyped(evt);
            }
        });

        ComboDaylab.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        ComboDaylab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }));
        ComboDaylab.setEnabled(false);
        ComboDaylab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboDaylabActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("G.Lab");

        jLabel11.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Day(g.lab)");

        jLabel12.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Time(g.lec)");

        Combotimelec1.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        Combotimelec1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Combotimelec1.setEnabled(false);
        Combotimelec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combotimelec1ActionPerformed(evt);
            }
        });

        AddSubject.setFont(new java.awt.Font("Leelawadee UI", 0, 24)); // NOI18N
        AddSubject.setText("ADD");
        AddSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSubjectActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("to");

        Combotimelec2.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        Combotimelec2.setEnabled(false);

        Combotimelab1.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        Combotimelab1.setEnabled(false);
        Combotimelab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combotimelab1ActionPerformed(evt);
            }
        });

        Combotimelab2.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        Combotimelab2.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Time(g.lab)");

        jLabel16.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("to");

        jCheckBox1.setToolTipText("");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        REMSubject.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        REMSubject.setText("REMOVE");
        REMSubject.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        REMSubject.setEnabled(false);
        REMSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REMSubjectActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Subject ");

        Btgeneratetable.setFont(new java.awt.Font("Leelawadee UI", 0, 24)); // NOI18N
        Btgeneratetable.setText("generate");
        Btgeneratetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtgeneratetableActionPerformed(evt);
            }
        });

        TFcredit.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        TFcredit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFcredit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFcreditKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Credit");

        TFtotalcredit.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        TFtotalcredit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFtotalcredit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFtotalcreditKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Total credits");

        RESetid.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        RESetid.setText("Reset ID");
        RESetid.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        RESetid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESetidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Comboprofessor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(Combosubject, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(TFcredit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(Btgeneratetable, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TFlec, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ComboDaylec, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Combotimelec1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Combotimelec2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(73, 73, 73)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TFlab, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ComboDaylab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(Combotimelab1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Combotimelab2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(55, 55, 55))))))
                            .addComponent(AddSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RESetid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFtotalcredit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(REMSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(REMSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TFtotalcredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RESetid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox1)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TFlec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ComboDaylec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Combotimelec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Combotimelec2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Combosubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFcredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Comboprofessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TFlab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboDaylab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Combotimelab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Combotimelab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btgeneratetable, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        jTabbedPane1.addTab("Manage", jPanel4);

        Tbsubgenerate.setFont(new java.awt.Font("Leelawadee UI", 0, 17)); // NOI18N
        Tbsubgenerate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Tbsubgenerate.setRowHeight(21);
        Tbsubgenerate.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(Tbsubgenerate);

        Combosubgenerate.setFont(new java.awt.Font("Leelawadee UI", 0, 15)); // NOI18N
        Combosubgenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombosubgenerateActionPerformed(evt);
            }
        });

        timetable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timetable1MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Table No.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Combosubgenerate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(timetable1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timetable1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Combosubgenerate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TFsubnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFsubnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFsubnameActionPerformed

    private void AddSubnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSubnameActionPerformed

        //subjects c = new subjects();
        //ent.persist(c);
        //subjectsList.add(c);
        //int row = subjectsList.size();
        //Tbsubject.setRowSelectionInterval(row, row);
        if (!TFsubname.getText().equals("") && !TFid.getText().equals("")) {
            String[] d = {TFid.getText(), TFsubname.getText()};
            addDataToTable(d, "subjects", "`ID`,`subject`", "?,?");
            showDataToTable("subjects", "`ID`,`subject`", Tbsubname);
            showDataToCombo("subjects", "subject", Combosubject);
            TFsubname.setText("");
            TFid.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
        }
    }//GEN-LAST:event_AddSubnameActionPerformed

    private void REMSubnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMSubnameActionPerformed
        REMSubname.setEnabled(false);
        String selectId = Tbsubname.getValueAt(Tbsubname.getSelectedRow(), 0).toString();
        deleteData(selectId, "subjects", "ID");
        showDataToTable("subjects", "`ID`,`subject`", Tbsubname);
        showDataToCombo("subjects", "subject", Combosubject);
    }//GEN-LAST:event_REMSubnameActionPerformed

    private void TFprofessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFprofessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFprofessorActionPerformed

    private void AddprofessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddprofessorActionPerformed
        if (!TFprofessor.getText().equals("")) {
            String[] d = {TFprofessor.getText()};
            addDataToTable(d, "professor", "`professor`", "?");
            showDataToTable("professor", "`professor`", Tbprofessor);
            showDataToCombo("professor", "professor", Comboprofessor);
            TFprofessor.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
        }
    }//GEN-LAST:event_AddprofessorActionPerformed

    private void REMprofessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMprofessorActionPerformed
        REMprofessor.setEnabled(false);
        String selectdata = Tbprofessor.getValueAt(Tbprofessor.getSelectedRow(), 0).toString();
        deleteData("'" + selectdata + "'", "professor", "professor");
        showDataToTable("professor", "`professor`", Tbprofessor);
        showDataToCombo("professor", "professor", Comboprofessor);
    }//GEN-LAST:event_REMprofessorActionPerformed

    private void TFidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFidActionPerformed

    private void TFidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFidKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_TFidKeyTyped

    private void TbsubnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbsubnameMouseClicked
        REMSubname.setEnabled(true);
    }//GEN-LAST:event_TbsubnameMouseClicked

    private void TbprofessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbprofessorMouseClicked
        REMprofessor.setEnabled(true);
    }//GEN-LAST:event_TbprofessorMouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        REMSubname.setEnabled(false);
        REMprofessor.setEnabled(false);
        Tbsubname.clearSelection();
        Tbprofessor.clearSelection();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void AddSubjectToTableNumber() {
        String code1 = "INSERT INTO `" + firstname + "`(`tableNO`, `subject`, `credit`, `G.Lec`, "
                + "`day(g.lec)`, `timeS(g.lec)`, `timeE(g.lec)`, `G.Lab`, "
                + "`day(g.lab)`, `timeS(g.lab)`, `timeE(g.lab)`, `professor`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String code2 = "INSERT INTO `" + firstname + "`(`tableNO`, `subject`, `credit`, `G.Lec`, "
                + "`day(g.lec)`, `timeS(g.lec)`, `timeE(g.lec)`, `professor`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        String code3 = "INSERT INTO `" + firstname + "`(`tableNO`, `subject`, `credit`, `G.Lab`, "
                + "`day(g.lab)`, `timeS(g.lab)`, `timeE(g.lab)`, `professor`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        int n = 1;
        for (ArrayList<subject> subtable : this.TableSubjects) {
            for (subject sub : subtable) {
                if (sub.getDaylec() != null && sub.getDaylab() != null) {
                    try {
                        ps = con.prepareStatement(code1);
                        ps.setInt(1, n);
                        ps.setString(2, sub.getSubjectname());
                        ps.setInt(3, sub.getCredit());
                        ps.setInt(4, sub.getGlec());
                        ps.setString(5, sub.getDaylec());
                        ps.setTime(6, Time.valueOf(sub.getTimelec1()));
                        ps.setTime(7, Time.valueOf(sub.getTimelec2()));
                        ps.setInt(8, sub.getGlab());
                        ps.setString(9, sub.getDaylab());
                        ps.setTime(10, Time.valueOf(sub.getTimelab1()));
                        ps.setTime(11, Time.valueOf(sub.getTimelab2()));
                        ps.setString(12, sub.getProfessor());
                        if (ps.executeUpdate() == 0) {
                            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (sub.getDaylec() != null) {
                    try {
                        ps = con.prepareStatement(code2);
                        ps.setInt(1, n);
                        ps.setString(2, sub.getSubjectname());
                        ps.setInt(3, sub.getCredit());
                        ps.setInt(4, sub.getGlec());
                        ps.setString(5, sub.getDaylec());
                        ps.setTime(6, Time.valueOf(sub.getTimelec1()));
                        ps.setTime(7, Time.valueOf(sub.getTimelec2()));
                        ps.setString(8, sub.getProfessor());
                        if (ps.executeUpdate() == 0) {
                            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (sub.getDaylab() != null) {
                    try {
                        ps = con.prepareStatement(code3);
                        ps.setInt(1, n);
                        ps.setString(2, sub.getSubjectname());
                        ps.setInt(3, sub.getCredit());
                        ps.setInt(4, sub.getGlab());
                        ps.setString(5, sub.getDaylab());
                        ps.setTime(6, Time.valueOf(sub.getTimelab1()));
                        ps.setTime(7, Time.valueOf(sub.getTimelab2()));
                        ps.setString(8, sub.getProfessor());
                        if (ps.executeUpdate() == 0) {
                            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            n++;
        }
    }
    private void AddSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSubjectActionPerformed
        HashMap<String, Integer> mapcredit = new HashMap<String, Integer>();
        int sum = 0;
        if (Tbsubject.getRowCount() > 0) {
            for (int i = 0; i < Tbsubject.getRowCount(); i++) {
                mapcredit.put(Tbsubject.getValueAt(i, 1).toString(), (Integer) Tbsubject.getValueAt(i, 2));
            }
            for (Map.Entry credit : mapcredit.entrySet()) {
                sum += (Integer) credit.getValue();
            }
        }

        String code1 = "INSERT INTO `" + firstname + "`(`tableNO`, `subject`, `credit`, `G.Lec`, "
                + "`day(g.lec)`, `timeS(g.lec)`, `timeE(g.lec)`, `G.Lab`, "
                + "`day(g.lab)`, `timeS(g.lab)`, `timeE(g.lab)`, `professor`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String code2 = "INSERT INTO `" + firstname + "`(`tableNO`, `subject`, `credit`, `G.Lec`, "
                + "`day(g.lec)`, `timeS(g.lec)`, `timeE(g.lec)`, `professor`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        String code3 = "INSERT INTO `" + firstname + "`(`tableNO`, `subject`, `credit`, `G.Lab`, "
                + "`day(g.lab)`, `timeS(g.lab)`, `timeE(g.lab)`, `professor`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        String subname = Combosubject.getSelectedItem().toString();
        String professor = Comboprofessor.getSelectedItem().toString();

        LocalTime Timelec1, Timelec2, Timelab1, Timelab2;
        int Glec, Glab, credit,totalcredit;
        String daylec, daylab;
        if (!(TFtotalcredit.getText().equals("")) && !(TFcredit.getText().equals(""))) {
            credit = Integer.parseInt(TFcredit.getText());
            totalcredit = Integer.parseInt(TFtotalcredit.getText());
            if (credit + sum <= totalcredit) {
                if (jCheckBox1.isSelected() && jCheckBox2.isSelected()) {
                    if (!(TFlec.getText().equals("")) && !(TFlab.getText().equals(""))) {
                        if (Combotimelec1.isEnabled() && Combotimelab1.isEnabled()) {
                            if (ComboDaylec.getSelectedIndex() == ComboDaylab.getSelectedIndex()) {
                                Timelec1 = LocalTime.parse(Combotimelec1.getSelectedItem().toString());
                                Timelec2 = LocalTime.parse(Combotimelec2.getSelectedItem().toString());
                                Timelab1 = LocalTime.parse(Combotimelab1.getSelectedItem().toString());
                                Timelab2 = LocalTime.parse(Combotimelab2.getSelectedItem().toString());
                                Glec = Integer.parseInt(TFlec.getText());
                                Glab = Integer.parseInt(TFlab.getText());
                                daylec = ComboDaylec.getItemAt(ComboDaylec.getSelectedIndex());
                                daylab = ComboDaylab.getItemAt(ComboDaylab.getSelectedIndex());
                                if ((Timelec1.compareTo(Timelab1) <= 0 && Timelec2.compareTo(Timelab1) <= 0)
                                        || ((Timelec1.compareTo(Timelab2) >= 0 && Timelec2.compareTo(Timelab2) >= 0))) {
                                    try {
                                        ps = con.prepareStatement(code1);
                                        ps.setInt(1, 0);
                                        ps.setString(2, subname);
                                        ps.setInt(3, credit);
                                        ps.setInt(4, Glec);
                                        ps.setString(5, daylec);
                                        ps.setTime(6, Time.valueOf(Timelec1));
                                        ps.setTime(7, Time.valueOf(Timelec2));
                                        ps.setInt(8, Glab);
                                        ps.setString(9, daylab);
                                        ps.setTime(10, Time.valueOf(Timelab1));
                                        ps.setTime(11, Time.valueOf(Timelab2));
                                        ps.setString(12, professor);
                                        if (ps.executeUpdate() == 0) {
                                            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                                }
                            } else {
                                Timelec1 = LocalTime.parse(Combotimelec1.getSelectedItem().toString());
                                Timelec2 = LocalTime.parse(Combotimelec2.getSelectedItem().toString());
                                Timelab1 = LocalTime.parse(Combotimelab1.getSelectedItem().toString());
                                Timelab2 = LocalTime.parse(Combotimelab2.getSelectedItem().toString());
                                Glec = Integer.parseInt(TFlec.getText());
                                Glab = Integer.parseInt(TFlab.getText());
                                daylec = ComboDaylec.getItemAt(ComboDaylec.getSelectedIndex());
                                daylab = ComboDaylab.getItemAt(ComboDaylab.getSelectedIndex());

                                try {
                                    ps = con.prepareStatement(code1);
                                    ps.setInt(1, 0);
                                    ps.setString(2, subname);
                                    ps.setInt(3, credit);
                                    ps.setInt(4, Glec);
                                    ps.setString(5, daylec);
                                    ps.setTime(6, Time.valueOf(Timelec1));
                                    ps.setTime(7, Time.valueOf(Timelec2));
                                    ps.setInt(8, Glab);
                                    ps.setString(9, daylab);
                                    ps.setTime(10, Time.valueOf(Timelab1));
                                    ps.setTime(11, Time.valueOf(Timelab2));
                                    ps.setString(12, professor);
                                    if (ps.executeUpdate() == 0) {
                                        JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                    }

                } else if (jCheckBox1.isSelected()) {
                    if (Combotimelec1.isEnabled()) {
                        if (!(TFlec.getText().equals(""))) {
                            Timelec1 = LocalTime.parse(Combotimelec1.getSelectedItem().toString());
                            Timelec2 = LocalTime.parse(Combotimelec2.getSelectedItem().toString());
                            Glec = Integer.parseInt(TFlec.getText());
                            daylec = ComboDaylec.getItemAt(ComboDaylec.getSelectedIndex());
                            try {
                                ps = con.prepareStatement(code2);
                                ps.setInt(1, 0);
                                ps.setString(2, subname);
                                ps.setInt(3, credit);
                                ps.setInt(4, Glec);
                                ps.setString(5, daylec);
                                ps.setTime(6, Time.valueOf(Timelec1));
                                ps.setTime(7, Time.valueOf(Timelec2));
                                ps.setString(8, professor);
                                if (ps.executeUpdate() == 0) {
                                    JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                    }

                } else if (jCheckBox2.isSelected()) {
                    if (Combotimelab1.isEnabled()) {
                        if (!(TFlab.getText().equals(""))) {
                            Timelab1 = LocalTime.parse(Combotimelab1.getSelectedItem().toString());
                            Timelab2 = LocalTime.parse(Combotimelab2.getSelectedItem().toString());
                            Glab = Integer.parseInt(TFlab.getText());
                            daylab = ComboDaylab.getItemAt(ComboDaylab.getSelectedIndex());
                            try {
                                ps = con.prepareStatement(code3);
                                ps.setInt(1, 0);
                                ps.setString(2, subname);
                                ps.setInt(3, credit);
                                ps.setInt(4, Glab);
                                ps.setString(5, daylab);
                                ps.setTime(6, Time.valueOf(Timelab1));
                                ps.setTime(7, Time.valueOf(Timelab2));
                                ps.setString(8, professor);
                                if (ps.executeUpdate() == 0) {
                                    JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(program.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Check Your Information", "Error", 2);
        }

        showDataToTableSubject(0, Tbsubject);
    }//GEN-LAST:event_AddSubjectActionPerformed

    private void Combotimelec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combotimelec1ActionPerformed
        LocalTime time = LocalTime.parse(Combotimelec1.getSelectedItem().toString());
        addTimeTocombo(Combotimelec2, time.plusMinutes(30), LocalTime.of(23, 30), 30);
    }//GEN-LAST:event_Combotimelec1ActionPerformed

    private void ComboDaylecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboDaylecActionPerformed
        if (ComboDaylec.getSelectedIndex() > 0) {
            Combotimelec1.setEnabled(true);
            Combotimelec2.setEnabled(true);
        } else {
            Combotimelec1.setEnabled(false);
            Combotimelec2.setEnabled(false);
        }
    }//GEN-LAST:event_ComboDaylecActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (ComboDaylec.getSelectedIndex() > 0) {
            Combotimelec1.setEnabled(true);
            Combotimelec2.setEnabled(true);
        } else {
            Combotimelec1.setEnabled(false);
            Combotimelec2.setEnabled(false);
        }
        if (jCheckBox1.isSelected()) {
            TFlec.setEnabled(true);
            ComboDaylec.setEnabled(true);
        } else {
            Combotimelec1.setEnabled(false);
            Combotimelec2.setEnabled(false);
            TFlec.setEnabled(false);
            ComboDaylec.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (ComboDaylab.getSelectedIndex() > 0) {
            Combotimelab1.setEnabled(true);
            Combotimelab2.setEnabled(true);
        } else {
            Combotimelab1.setEnabled(false);
            Combotimelab2.setEnabled(false);
        }
        if (jCheckBox2.isSelected()) {
            TFlab.setEnabled(true);
            ComboDaylab.setEnabled(true);
        } else {
            Combotimelab1.setEnabled(false);
            Combotimelab2.setEnabled(false);
            TFlab.setEnabled(false);
            ComboDaylab.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void Combotimelab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combotimelab1ActionPerformed
        LocalTime time = LocalTime.parse(Combotimelab1.getSelectedItem().toString());
        addTimeTocombo(Combotimelab2, time.plusMinutes(30), LocalTime.of(23, 30), 30);
    }//GEN-LAST:event_Combotimelab1ActionPerformed

    private void TFlecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFlecKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_TFlecKeyTyped

    private void TFlabKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFlabKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_TFlabKeyTyped

    private void ComboDaylabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboDaylabActionPerformed
        if (ComboDaylab.getSelectedIndex() > 0) {
            Combotimelab1.setEnabled(true);
            Combotimelab2.setEnabled(true);
        } else {
            Combotimelab1.setEnabled(false);
            Combotimelab2.setEnabled(false);
        }
    }//GEN-LAST:event_ComboDaylabActionPerformed

    private void REMSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMSubjectActionPerformed
        REMSubject.setEnabled(false);
        String selectId = Tbsubject.getValueAt(Tbsubject.getSelectedRow(), 0).toString();
        deleteData(selectId, firstname, "ID");
        showDataToTableSubject(0, Tbsubject);
    }//GEN-LAST:event_REMSubjectActionPerformed

    private void TbsubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbsubjectMouseClicked
        REMSubject.setEnabled(true);
    }//GEN-LAST:event_TbsubjectMouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        REMSubject.setEnabled(false);
        Tbsubject.clearSelection();
        Tbsubgenerate.clearSelection();
    }//GEN-LAST:event_jPanel4MouseClicked

    private void BtgeneratetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtgeneratetableActionPerformed
        deleteData2();
        generateTable();
        addNumberTableToCombo();
        AddSubjectToTableNumber();
        showDataToTableSubject2(1, Tbsubgenerate);
        if(this.TableSubjects.size() > 0){
            showTimeTable(this.TableSubjects.get(0));
        }else{
            JOptionPane.showMessageDialog(null, "Can not generatetable", "Error", 2);
        }
        
    }//GEN-LAST:event_BtgeneratetableActionPerformed

    private void TFcreditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFcreditKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_TFcreditKeyTyped

    private void TFtotalcreditKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFtotalcreditKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE) || c == KeyEvent.VK_DELETE) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_TFtotalcreditKeyTyped

    private void CombosubgenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombosubgenerateActionPerformed
        if (Combosubgenerate.getSelectedIndex() < 0) {
            showTimeTable(this.TableSubjects.get(0));
            showDataToTableSubject2(1, Tbsubgenerate);
        } else {
            showTimeTable(this.TableSubjects.get(Combosubgenerate.getSelectedIndex()));
            showDataToTableSubject2(Combosubgenerate.getSelectedIndex() + 1, Tbsubgenerate);
        }


    }//GEN-LAST:event_CombosubgenerateActionPerformed

    private void ComboprofessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboprofessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboprofessorActionPerformed

    private void RESetidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESetidActionPerformed
        connect.resetID(firstname);
        showDataToTableSubject(0, Tbsubject);

    }//GEN-LAST:event_RESetidActionPerformed

    private void REMSubject1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMSubject1ActionPerformed
        login L = new login();
        L.setVisible(true);
        L.pack();
        L.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_REMSubject1ActionPerformed

    private void timetable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timetable1MouseClicked
        Tbsubject.clearSelection();
        Tbsubgenerate.clearSelection();
    }//GEN-LAST:event_timetable1MouseClicked
    private void showTimeTable(ArrayList<subject> listsubject) {
        timetable1.removeAllLabel();
        timetable1.setSubjects(listsubject);
        timetable1.addSubjectToTimetable();
        //timetable1.revalidate();
        timetable1.repaint();
    }

    private void generateTable() {
        this.TableSubjects.clear();
        ArrayList<subject> subjects = new ArrayList<subject>();
        for (int i = 0; i < Tbsubject.getRowCount(); i++) {
            String subjectname = Tbsubject.getValueAt(i, 1).toString();
            String professor = Tbsubject.getValueAt(i, 11).toString();
            int credit = Integer.parseInt(Tbsubject.getValueAt(i, 2).toString());
            String daylec, daylab;
            Integer Glec, Glab;
            LocalTime timelec1, timelec2, timelab1, timelab2;
            if (Tbsubject.getValueAt(i, 4) == null) {
                Glec = null;
                daylec = null;
                timelec1 = null;
                timelec2 = null;
            } else {
                Glec = Integer.parseInt(Tbsubject.getValueAt(i, 3).toString());
                daylec = Tbsubject.getValueAt(i, 4).toString();
                timelec1 = LocalTime.parse(Tbsubject.getValueAt(i, 5).toString());
                timelec2 = LocalTime.parse(Tbsubject.getValueAt(i, 6).toString());
            }
            if (Tbsubject.getValueAt(i, 8) == null) {
                Glab = null;
                daylab = null;
                timelab1 = null;
                timelab2 = null;
            } else {
                Glab = Integer.parseInt(Tbsubject.getValueAt(i, 7).toString());
                daylab = Tbsubject.getValueAt(i, 8).toString();
                timelab1 = LocalTime.parse(Tbsubject.getValueAt(i, 9).toString());
                timelab2 = LocalTime.parse(Tbsubject.getValueAt(i, 10).toString());
            }
            subjects.add(new subject(subjectname, credit, daylec, daylab, Glec, Glab, timelec1, timelec2, timelab1, timelab2, professor));
        }

        int key = 0;
        for (subject sub1 : subjects) {
            ArrayList<subject> list = new ArrayList<subject>();
            list.add(sub1);
            for (subject sub2 : subjects) {
                int check = 0;
                for (subject name : list) {
                    if (name.getSubjectname().equals(sub2.getSubjectname()) || comparetime(name, sub2) != 4) {
                        check = 1;
                    }
                }
                if (check == 1) {
                    continue;
                }
                if (comparetime(sub1, sub2) == 4) {
                    list.add(sub2);
                }
            }
            if (key == 0) {
                this.TableSubjects.add(list);
                key = 1;
            }
            ArrayList<ArrayList<subject>> Table = new ArrayList<ArrayList<subject>>(this.TableSubjects);
            for (ArrayList<subject> listtable : Table) {
                int n = 0;
                for (subject sub3 : listtable) {
                    for (subject sub4 : list) {
                        if (sub3.equals(sub4)) {
                            n += 1;
                        }
                    }
                }
                if (n != listtable.size()) {
                    this.TableSubjects.add(list);
                    break;
                }
            }
        }

        HashMap<String, ArrayList<subject>> mapsubject = new HashMap<String, ArrayList<subject>>();
        for (subject sub : subjects) {
            if (mapsubject.get(sub.getSubjectname()) == null) {
                mapsubject.put(sub.getSubjectname(), new ArrayList<subject>());
            }
            mapsubject.get(sub.getSubjectname()).add(sub);
        }
        ArrayList<ArrayList<subject>> TableSubjectsclone = new ArrayList<ArrayList<subject>>(this.TableSubjects);

        for (ArrayList<subject> sublist : TableSubjectsclone) {
            int num = 0;
            for (Map.Entry subname : mapsubject.entrySet()) {
                for (subject sub : sublist) {
                    if (subname.getKey().toString().equals(sub.getSubjectname())) {
                        num += 1;
                    }
                }
            }
            if (num != mapsubject.size()) {
                this.TableSubjects.remove(sublist);
            }

        }
    }

    private void addNumberTableToCombo() {
        Combosubgenerate.removeAllItems();
        for (int i = 0; i < this.TableSubjects.size(); i++) {
            Combosubgenerate.addItem(i + 1);
        }
    }

    private int comparetime(subject sub1, subject sub2) {
        int n = 0;
        if (!(sub1.equals(sub2))) {
            if (sub1.getDaylec() != null && sub2.getDaylec() != null) {
                if (sub1.getDaylec().equals(sub2.getDaylec())) {
                    if ((sub1.getTimelec1().compareTo(sub2.getTimelec1()) <= 0 && sub1.getTimelec2().compareTo(sub2.getTimelec1()) <= 0)
                            || (sub1.getTimelec1().compareTo(sub2.getTimelec2()) >= 0 && sub1.getTimelec2().compareTo(sub2.getTimelec2()) >= 0)) {
                        n++;
                    }
                } else {
                    n++;
                }
            } else {
                n++;
            }
            if (sub1.getDaylec() != null && sub2.getDaylab() != null) {
                if (sub1.getDaylec().equals(sub2.getDaylab())) {
                    if ((sub1.getTimelec1().compareTo(sub2.getTimelab1()) <= 0 && sub1.getTimelec2().compareTo(sub2.getTimelab1()) <= 0)
                            || (sub1.getTimelec1().compareTo(sub2.getTimelab2()) >= 0 && sub1.getTimelec2().compareTo(sub2.getTimelab2()) >= 0)) {
                        n++;
                    }
                } else {
                    n++;
                }
            } else {
                n++;
            }
            if (sub1.getDaylab() != null && sub2.getDaylec() != null) {
                if (sub1.getDaylab().equals(sub2.getDaylec())) {
                    if ((sub1.getTimelab1().compareTo(sub2.getTimelec1()) <= 0 && sub1.getTimelab2().compareTo(sub2.getTimelec1()) <= 0)
                            || (sub1.getTimelab1().compareTo(sub2.getTimelec2()) >= 0 && sub1.getTimelab2().compareTo(sub2.getTimelec2()) >= 0)) {
                        n++;
                    }
                } else {
                    n++;
                }
            } else {
                n++;
            }
            if (sub1.getDaylab() != null && sub2.getDaylab() != null) {
                if (sub1.getDaylab().equals(sub2.getDaylab())) {
                    if ((sub1.getTimelab1().compareTo(sub2.getTimelab1()) <= 0 && sub1.getTimelab2().compareTo(sub2.getTimelab1()) <= 0)
                            || (sub1.getTimelab1().compareTo(sub2.getTimelab2()) >= 0 && sub1.getTimelab2().compareTo(sub2.getTimelab2()) >= 0)) {
                        n++;
                    }
                } else {
                    n++;
                }
            } else {
                n++;
            }
        }
        return n;
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(program.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(program.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(program.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(program.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new program().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddSubject;
    private javax.swing.JButton AddSubname;
    private javax.swing.JButton Addprofessor;
    private javax.swing.JButton Btgeneratetable;
    private javax.swing.JComboBox<String> ComboDaylab;
    private javax.swing.JComboBox<String> ComboDaylec;
    private javax.swing.JComboBox<String> Comboprofessor;
    private javax.swing.JComboBox<Integer> Combosubgenerate;
    private javax.swing.JComboBox<String> Combosubject;
    private javax.swing.JComboBox<LocalTime> Combotimelab1;
    private javax.swing.JComboBox<LocalTime> Combotimelab2;
    private javax.swing.JComboBox<LocalTime> Combotimelec1;
    private javax.swing.JComboBox<LocalTime> Combotimelec2;
    private javax.swing.JButton REMSubject;
    private javax.swing.JButton REMSubject1;
    private javax.swing.JButton REMSubname;
    private javax.swing.JButton REMprofessor;
    private javax.swing.JButton RESetid;
    private javax.swing.JTextField TFcredit;
    private javax.swing.JTextField TFid;
    private javax.swing.JTextField TFlab;
    private javax.swing.JTextField TFlec;
    private javax.swing.JTextField TFprofessor;
    private javax.swing.JTextField TFsubname;
    private javax.swing.JTextField TFtotalcredit;
    private javax.swing.JTable Tbprofessor;
    private javax.swing.JTable Tbsubgenerate;
    private javax.swing.JTable Tbsubject;
    private javax.swing.JTable Tbsubname;
    private javax.swing.JLabel hi;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private Project.timetable timetable1;
    // End of variables declaration//GEN-END:variables
}
