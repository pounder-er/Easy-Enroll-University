/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author chain
 */
public class register extends javax.swing.JFrame {
    /**
     * Creates new form register
     */
    public register() {
        initComponents();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        textlastname = new javax.swing.JLabel();
        textuser = new javax.swing.JLabel();
        textpass = new javax.swing.JLabel();
        textconpass = new javax.swing.JLabel();
        okre = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        textpass1 = new javax.swing.JLabel();
        TFlastname = new javax.swing.JTextField();
        TFuser = new javax.swing.JTextField();
        jPassword = new javax.swing.JPasswordField();
        jPasswordcon = new javax.swing.JPasswordField();
        massage = new javax.swing.JLabel();
        TFfirstname = new javax.swing.JTextField();
        textfirstname = new javax.swing.JLabel();
        massagefname = new javax.swing.JLabel();
        massagelname = new javax.swing.JLabel();
        massageusername = new javax.swing.JLabel();
        massagepass = new javax.swing.JLabel();
        massagepasscon = new javax.swing.JLabel();
        massagegender = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        textregistor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        textlastname.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        textlastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textlastname.setText("Lastname");
        textlastname.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        getContentPane().add(textlastname, gridBagConstraints);

        textuser.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        textuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textuser.setText("Username");
        textuser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        getContentPane().add(textuser, gridBagConstraints);

        textpass.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        textpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textpass.setText("Password");
        textpass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 21, 0, 0);
        getContentPane().add(textpass, gridBagConstraints);

        textconpass.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        textconpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textconpass.setText("Confirm Password");
        textconpass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 24, 0, 0);
        getContentPane().add(textconpass, gridBagConstraints);

        okre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        okre.setText("Ok");
        okre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        okre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        okre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 52, 13, 0);
        getContentPane().add(okre, gridBagConstraints);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jRadioButton1.setText("Male");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 5, 0, 0);
        getContentPane().add(jRadioButton1, gridBagConstraints);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jRadioButton2.setText("Female");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 4, 0, 0);
        getContentPane().add(jRadioButton2, gridBagConstraints);

        textpass1.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        textpass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textpass1.setText("Gender");
        textpass1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 0);
        getContentPane().add(textpass1, gridBagConstraints);

        TFlastname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 333;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        getContentPane().add(TFlastname, gridBagConstraints);

        TFuser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 333;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        getContentPane().add(TFuser, gridBagConstraints);

        jPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 333;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        getContentPane().add(jPassword, gridBagConstraints);

        jPasswordcon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 266;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 5, 0, 0);
        getContentPane().add(jPasswordcon, gridBagConstraints);

        massage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massage.setForeground(new java.awt.Color(255, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.ipadx = 488;
        gridBagConstraints.ipady = 49;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 24, 0, 0);
        getContentPane().add(massage, gridBagConstraints);

        TFfirstname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 333;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        getContentPane().add(TFfirstname, gridBagConstraints);

        textfirstname.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        textfirstname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textfirstname.setText("Firstname");
        textfirstname.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 25, 0, 0);
        getContentPane().add(textfirstname, gridBagConstraints);

        massagefname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massagefname.setForeground(new java.awt.Color(255, 0, 0));
        massagefname.setText(" ");
        massagefname.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 0);
        getContentPane().add(massagefname, gridBagConstraints);

        massagelname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massagelname.setForeground(new java.awt.Color(255, 0, 0));
        massagelname.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 0);
        getContentPane().add(massagelname, gridBagConstraints);

        massageusername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massageusername.setForeground(new java.awt.Color(255, 0, 0));
        massageusername.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 0);
        getContentPane().add(massageusername, gridBagConstraints);

        massagepass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massagepass.setForeground(new java.awt.Color(255, 0, 0));
        massagepass.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 0);
        getContentPane().add(massagepass, gridBagConstraints);

        massagepasscon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massagepasscon.setForeground(new java.awt.Color(255, 0, 0));
        massagepasscon.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 5, 0, 0);
        getContentPane().add(massagepasscon, gridBagConstraints);

        massagegender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        massagegender.setForeground(new java.awt.Color(255, 0, 0));
        massagegender.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 8, 0, 0);
        getContentPane().add(massagegender, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(16, 226, 133));

        textregistor.setFont(new java.awt.Font("Tw Cen MT", 0, 36)); // NOI18N
        textregistor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textregistor.setText("Register");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(textregistor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(textregistor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 25;
        gridBagConstraints.ipadx = 209;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    private void okreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okreActionPerformed
        int num = 0;
        String pass = String.valueOf(jPassword.getPassword());
        String passcon = String.valueOf(jPasswordcon.getPassword());
        if(pass.equals("")||passcon.equals("")){
            massagepass.setText("*");
            massagepasscon.setText("*");
        }
        else{
            massagepass.setText("");
            massagepasscon.setText("");
        }
        if(!pass.equals(passcon)){
            massage.setText("*Passwords do not match");
            massagepass.setText("*");
            massagepasscon.setText("*");
        }
        else{
            massage.setText("");
            num+=2;
        }
        if(jRadioButton1.isSelected() || jRadioButton2.isSelected()){
            massagegender.setText("");
            num++;
        }
        else{
            massagegender.setText("*");
        }
        if(TFfirstname.getText().equals("")){
            massagefname.setText("*");
        }
        else{
            massagefname.setText("");
            num++;
        }
        if(TFlastname.getText().equals("")){
            massagelname.setText("*");
        }
        else{
            massagelname.setText("");
            num++;
        }
        if(TFuser.getText().equals("")){
            massageusername.setText("*");
        }
        else{
            massageusername.setText("");
            num++;
        }
        if(num==6){
            PreparedStatement ps;
            String gender;
            Connection con = connect.ConnectDB();
            String firstname = TFfirstname.getText();
            String lastname = TFlastname.getText();
            String username = TFuser.getText();
            String code = "INSERT INTO `accounts`(`firstname`, `lastname`, `username`, `password`, `gender`) VALUES (?,?,?,?,?)";
            if(jRadioButton1.isSelected()){
                gender = "Male";
            }
            else{
                gender = "Female";
            }
            try {
                ps = con.prepareStatement(code);
                ps.setString(1, firstname);
                ps.setString(2, lastname);
                ps.setString(3, username);
                ps.setString(4, pass);
                ps.setString(5, gender);
                if(ps.executeUpdate() != 0){
                    ps.close();
                    connect.createTable(firstname);
                    System.out.println("Create table complete!!");
                    login ma = new login();
                    ma.setVisible(true);
                    ma.pack();
                    ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Check Your Information", "Error",2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }//GEN-LAST:event_okreActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TFfirstname;
    private javax.swing.JTextField TFlastname;
    private javax.swing.JTextField TFuser;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JPasswordField jPasswordcon;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel massage;
    private javax.swing.JLabel massagefname;
    private javax.swing.JLabel massagegender;
    private javax.swing.JLabel massagelname;
    private javax.swing.JLabel massagepass;
    private javax.swing.JLabel massagepasscon;
    private javax.swing.JLabel massageusername;
    private javax.swing.JButton okre;
    private javax.swing.JLabel textconpass;
    private javax.swing.JLabel textfirstname;
    private javax.swing.JLabel textlastname;
    private javax.swing.JLabel textpass;
    private javax.swing.JLabel textpass1;
    private javax.swing.JLabel textregistor;
    private javax.swing.JLabel textuser;
    // End of variables declaration//GEN-END:variables
}
