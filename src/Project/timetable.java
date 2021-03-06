/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.awt.Color;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author chain
 */
public class timetable extends javax.swing.JPanel {

    /**
     * Creates new form timetable
     */
    private ArrayList<subject> subjects;
    private ArrayList<JLabel> labellist = new ArrayList<JLabel>();
    private HashMap<String, Integer> map1 = new HashMap<String, Integer>();
    private HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();

    public timetable() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void setSubjects(ArrayList<subject> subjects) {
        this.subjects = subjects;
    }

    private void addMapDayTime() {
        this.map1.put("Mon", Labelmon.getY());
        this.map1.put("Tue", Labeltue.getY());
        this.map1.put("Wed", Labelwed.getY());
        this.map1.put("Thu", Labelthu.getY());
        this.map1.put("Fri", Labelfri.getY());
        this.map1.put("Sat", Labelsat.getY());
        this.map1.put("Sun", Labelsun.getY());
        this.map2.put(6, Label6.getX());
        this.map2.put(7, Label7.getX());
        this.map2.put(8, Label8.getX());
        this.map2.put(9, Label9.getX());
        this.map2.put(10, Label10.getX());
        this.map2.put(11, Label11.getX());
        this.map2.put(12, Label12.getX());
        this.map2.put(13, Label13.getX());
        this.map2.put(14, Label14.getX());
        this.map2.put(15, Label15.getX());
        this.map2.put(16, Label16.getX());
        this.map2.put(17, Label17.getX());
        this.map2.put(18, Label18.getX());
        this.map2.put(19, Label19.getX());
        this.map2.put(20, Label20.getX());
        this.map2.put(21, Label21.getX());
        this.map2.put(22, Label22.getX());
    }

    public void removeAllLabel() {
        for (JLabel Label : this.labellist) {
            remove(Label);
        }
    }

    public void addSubjectToTimetable() {
        labellist.clear();
        addMapDayTime();
        //this.labellist.clear();
        /*StyleContext context = new StyleContext();
        StyledDocument document = new DefaultStyledDocument(context);
        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
        try {
        document.insertString(document.getLength(), "java2s.com", style);
        } catch (BadLocationException ex) {
        Logger.getLogger(timetable.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        for (subject sub : subjects) {
            int n = 0;
            if (sub.getDaylec() != null) {
                //System.out.println("0");
                LocalTime timelecstart1 = sub.getTimelec1();
                LocalTime timelecend1 = sub.getTimelec2();
                int x1;
                int y1 = map1.get(sub.getDaylec());
                int width1;
                int heigh1 = Label6.getHeight();
                if (timelecstart1.getMinute() == 30) {
                    //System.out.println("1");
                    x1 = map2.get(timelecstart1.getHour()) + (Label6.getWidth() / 2);
                    if (timelecend1.getMinute() == 30) {
                        width1 = (map2.get(timelecend1.getHour()) + (Label6.getWidth() / 2)) - x1;
                        //System.out.println("2");
                    } else {
                        width1 = map2.get(timelecend1.getHour()) - x1;
                        //System.out.println("3");
                    }
                } else {
                    x1 = map2.get(timelecstart1.getHour());
                    //System.out.println("4");
                    if (timelecend1.getMinute() == 30) {
                        width1 = (map2.get(timelecend1.getHour()) + (Label6.getWidth() / 2)) - x1;
                        //System.out.println("5");
                    } else {
                        width1 = map2.get(timelecend1.getHour()) - x1;
                        //System.out.println("6");
                    }
                }
                JLabel label = new JLabel();
                label.setBackground(new Color(99,181,111));
                label.setForeground(new Color(255, 255, 255));
                label.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(255, 255, 255)));
                label.setFont(new java.awt.Font("Leelawadee UI", 0, 15));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setText(sub.getSubjectname());
                label.setBounds(x1, y1, width1, heigh1);
                label.setOpaque(true);
                label.setVisible(true);
                //System.out.printf("7 %d %d %d %d \n", x1, y1, width1, heigh1);
                labellist.add(label);
            }
            if (sub.getDaylab() != null) {
                /*JTextPane textpane = new JTextPane();
                JScrollPane jscrollpane = new JScrollPane();
                textpane.setFont(new java.awt.Font("Leelawadee UI", 0, 14));
                textpane.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                jscrollpane.setViewportView(textpane);*/
                LocalTime timelecstart2 = sub.getTimelab1();
                LocalTime timelecend2 = sub.getTimelab2();
                int x2;
                int y2 = map1.get(sub.getDaylab());
                int width2;
                int heigh2 = Label6.getHeight();
                if (timelecstart2.getMinute() == 30) {
                    x2 = map2.get(timelecstart2.getHour()) + (Label6.getWidth() / 2);
                    if (timelecend2.getMinute() == 30) {
                        width2 = (map2.get(timelecend2.getHour()) + (Label6.getWidth() / 2)) - x2;
                    } else {
                        width2 = map2.get(timelecend2.getHour()) - x2;
                    }
                } else {
                    x2 = map2.get(timelecstart2.getHour());
                    if (timelecend2.getMinute() == 30) {
                        width2 = (map2.get(timelecend2.getHour()) + (Label6.getWidth() / 2)) - x2;
                    } else {
                        width2 = map2.get(timelecend2.getHour()) - x2;
                    }
                }
                JLabel label = new JLabel();
                label.setBackground(new Color(99,181,111));
                label.setForeground(new Color(255, 255, 255));
                label.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(255, 255, 255)));
                label.setFont(new java.awt.Font("Leelawadee UI", 0, 15));
                label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                label.setText(sub.getSubjectname());
                label.setBounds(x2, y2, width2, heigh2);
                label.setOpaque(true);
                label.setVisible(true);
                labellist.add(label);
            }

        }
        for (JLabel Label : labellist) {
            add(Label);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Label6 = new javax.swing.JLabel();
        Label7 = new javax.swing.JLabel();
        Label8 = new javax.swing.JLabel();
        Label9 = new javax.swing.JLabel();
        Label10 = new javax.swing.JLabel();
        Label11 = new javax.swing.JLabel();
        Label12 = new javax.swing.JLabel();
        Label13 = new javax.swing.JLabel();
        Label14 = new javax.swing.JLabel();
        Label15 = new javax.swing.JLabel();
        Label16 = new javax.swing.JLabel();
        Label17 = new javax.swing.JLabel();
        Label18 = new javax.swing.JLabel();
        Label19 = new javax.swing.JLabel();
        Label20 = new javax.swing.JLabel();
        Label21 = new javax.swing.JLabel();
        Label22 = new javax.swing.JLabel();
        Labelmon = new javax.swing.JLabel();
        Labeltue = new javax.swing.JLabel();
        Labelwed = new javax.swing.JLabel();
        Labelthu = new javax.swing.JLabel();
        Labelfri = new javax.swing.JLabel();
        Labelsat = new javax.swing.JLabel();
        Labelsun = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        Label6.setBackground(new java.awt.Color(37, 202, 183));
        Label6.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label6.setForeground(new java.awt.Color(255, 255, 255));
        Label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label6.setText("<html><center>6:00</center><center>7:00</center></html>");
        Label6.setToolTipText("");
        Label6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label6.setOpaque(true);
        Label6.setPreferredSize(new java.awt.Dimension(30, 35));

        Label7.setBackground(new java.awt.Color(255, 255, 255));
        Label7.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label7.setText("<html><center>7:00</center><center>8:00</center></html>");
        Label7.setToolTipText("");
        Label7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label8.setBackground(new java.awt.Color(37, 202, 183));
        Label8.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label8.setForeground(new java.awt.Color(255, 255, 255));
        Label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label8.setText("<html><center>8:00</center><center>9:00</center></html>");
        Label8.setToolTipText("");
        Label8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label8.setOpaque(true);

        Label9.setBackground(new java.awt.Color(255, 255, 255));
        Label9.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label9.setText("<html><center>9:00</center><center>10:00</center></html>");
        Label9.setToolTipText("");
        Label9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label10.setBackground(new java.awt.Color(37, 202, 183));
        Label10.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label10.setForeground(new java.awt.Color(255, 255, 255));
        Label10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label10.setText("<html><center>10:00</center><center>11:00</center></html>");
        Label10.setToolTipText("");
        Label10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label10.setOpaque(true);

        Label11.setBackground(new java.awt.Color(255, 255, 255));
        Label11.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label11.setText("<html><center>11:00</center><center>12:00</center></html>");
        Label11.setToolTipText("");
        Label11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label12.setBackground(new java.awt.Color(37, 202, 183));
        Label12.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label12.setForeground(new java.awt.Color(255, 255, 255));
        Label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label12.setText("<html><center>12:00</center><center>13:00</center></html>");
        Label12.setToolTipText("");
        Label12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label12.setOpaque(true);

        Label13.setBackground(new java.awt.Color(255, 255, 255));
        Label13.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label13.setText("<html><center>13:00</center><center>14:00</center></html>");
        Label13.setToolTipText("");
        Label13.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label14.setBackground(new java.awt.Color(37, 202, 183));
        Label14.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label14.setForeground(new java.awt.Color(255, 255, 255));
        Label14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label14.setText("<html><center>14:00</center><center>15:00</center></html>");
        Label14.setToolTipText("");
        Label14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label14.setOpaque(true);

        Label15.setBackground(new java.awt.Color(255, 255, 255));
        Label15.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label15.setText("<html><center>15:00</center><center>16:00</center></html>");
        Label15.setToolTipText("");
        Label15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label16.setBackground(new java.awt.Color(37, 202, 183));
        Label16.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label16.setForeground(new java.awt.Color(255, 255, 255));
        Label16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label16.setText("<html><center>16:00</center><center>17:00</center></html>");
        Label16.setToolTipText("");
        Label16.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label16.setOpaque(true);

        Label17.setBackground(new java.awt.Color(255, 255, 255));
        Label17.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label17.setText("<html><center>17:00</center><center>18:00</center></html>");
        Label17.setToolTipText("");
        Label17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label18.setBackground(new java.awt.Color(37, 202, 183));
        Label18.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label18.setForeground(new java.awt.Color(255, 255, 255));
        Label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label18.setText("<html><center>18:00</center><center>19:00</center></html>");
        Label18.setToolTipText("");
        Label18.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label18.setOpaque(true);

        Label19.setBackground(new java.awt.Color(255, 255, 255));
        Label19.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label19.setText("<html><center>19:00</center><center>20:00</center></html>");
        Label19.setToolTipText("");
        Label19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label20.setBackground(new java.awt.Color(37, 202, 183));
        Label20.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label20.setForeground(new java.awt.Color(255, 255, 255));
        Label20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label20.setText("<html><center>20:00</center><center>21:00</center></html>");
        Label20.setToolTipText("");
        Label20.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label20.setOpaque(true);

        Label21.setBackground(new java.awt.Color(255, 255, 255));
        Label21.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label21.setText("<html><center>21:00</center><center>22:00</center></html>");
        Label21.setToolTipText("");
        Label21.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Label22.setBackground(new java.awt.Color(37, 202, 183));
        Label22.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Label22.setForeground(new java.awt.Color(255, 255, 255));
        Label22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label22.setText("<html><center>22:00</center><center>23:00</center></html>");
        Label22.setToolTipText("");
        Label22.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Label22.setOpaque(true);

        Labelmon.setBackground(new java.awt.Color(142, 5, 217));
        Labelmon.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Labelmon.setForeground(new java.awt.Color(255, 255, 255));
        Labelmon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labelmon.setText("Monday");
        Labelmon.setToolTipText("");
        Labelmon.setBorder(new javax.swing.border.MatteBorder(null));
        Labelmon.setOpaque(true);

        Labeltue.setBackground(new java.awt.Color(255, 255, 255));
        Labeltue.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Labeltue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labeltue.setText("Tuesday");
        Labeltue.setToolTipText("");
        Labeltue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Labelwed.setBackground(new java.awt.Color(142, 5, 217));
        Labelwed.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Labelwed.setForeground(new java.awt.Color(255, 255, 255));
        Labelwed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labelwed.setText("Wednesday");
        Labelwed.setToolTipText("");
        Labelwed.setBorder(new javax.swing.border.MatteBorder(null));
        Labelwed.setOpaque(true);

        Labelthu.setBackground(new java.awt.Color(255, 255, 255));
        Labelthu.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Labelthu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labelthu.setText("Thursday");
        Labelthu.setToolTipText("");
        Labelthu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Labelfri.setBackground(new java.awt.Color(142, 5, 217));
        Labelfri.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Labelfri.setForeground(new java.awt.Color(255, 255, 255));
        Labelfri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labelfri.setText("Friday");
        Labelfri.setToolTipText("");
        Labelfri.setBorder(new javax.swing.border.MatteBorder(null));
        Labelfri.setOpaque(true);

        Labelsat.setBackground(new java.awt.Color(255, 255, 255));
        Labelsat.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Labelsat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labelsat.setText("Saturday");
        Labelsat.setToolTipText("");
        Labelsat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Labelsun.setBackground(new java.awt.Color(142, 5, 217));
        Labelsun.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        Labelsun.setForeground(new java.awt.Color(255, 255, 255));
        Labelsun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Labelsun.setText("Sunday");
        Labelsun.setToolTipText("");
        Labelsun.setBorder(new javax.swing.border.MatteBorder(null));
        Labelsun.setOpaque(true);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Leelawadee UI", 0, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Day / Time");
        jLabel24.setToolTipText("");
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Leelawadee UI", 0, 20)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Timetable");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Labeltue, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Labelfri, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Labelsat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(Labelmon, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(275, 275, 275))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(Label6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(Label7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(Label8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(Label9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(Label10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(Label11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Label12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Label13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(Label14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label16, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label17, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label19, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label20, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label21, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Label22, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Labelwed, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Labelthu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(Labelsun, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(Labelmon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(Labeltue, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(Labelwed, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(Labelthu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(Labelfri, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(Labelsat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(Labelsun, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label10;
    private javax.swing.JLabel Label11;
    private javax.swing.JLabel Label12;
    private javax.swing.JLabel Label13;
    private javax.swing.JLabel Label14;
    private javax.swing.JLabel Label15;
    private javax.swing.JLabel Label16;
    private javax.swing.JLabel Label17;
    private javax.swing.JLabel Label18;
    private javax.swing.JLabel Label19;
    private javax.swing.JLabel Label20;
    private javax.swing.JLabel Label21;
    private javax.swing.JLabel Label22;
    private javax.swing.JLabel Label6;
    private javax.swing.JLabel Label7;
    private javax.swing.JLabel Label8;
    private javax.swing.JLabel Label9;
    private javax.swing.JLabel Labelfri;
    private javax.swing.JLabel Labelmon;
    private javax.swing.JLabel Labelsat;
    private javax.swing.JLabel Labelsun;
    private javax.swing.JLabel Labelthu;
    private javax.swing.JLabel Labeltue;
    private javax.swing.JLabel Labelwed;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    // End of variables declaration//GEN-END:variables
}
