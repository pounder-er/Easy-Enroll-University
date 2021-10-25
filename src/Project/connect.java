/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.sql.*;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author chain
 */
public class connect {

    private static Connection con;

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dataproject?useUnicode=true&characterEncoding=utf-8";
            Connection con = DriverManager.getConnection(url, "root", "");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFirstnameFromUsername(String username) {
        con = connect.ConnectDB();
        ResultSet rs;
        String code = "SELECT `firstname` FROM `accounts` WHERE `username` = '" + username + "'";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(code);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("firstname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void createTable(String name) {
        con = connect.ConnectDB();
        PreparedStatement ps;
        String code = "CREATE TABLE `dataproject`.`"+ name +"` ( `ID` INT(10) NOT NULL AUTO_INCREMENT ,"
                + " `tableNO` INT(10) NULL DEFAULT NULL , `subject` VARCHAR(80) NULL DEFAULT NULL ,"
                + " `credit` INT(5) NULL DEFAULT NULL,`G.Lec` INT(5) NULL DEFAULT NULL , `day(g.lec)` VARCHAR(3) NULL DEFAULT NULL , "
                + "`timeS(g.lec)` TIME(4) NULL DEFAULT NULL , `timeE(g.lec)` TIME(4) NULL DEFAULT NULL , "
                + "`G.Lab` INT(5) NULL DEFAULT NULL , `day(g.lab)` VARCHAR(3) NULL DEFAULT NULL , "
                + "`timeS(g.lab)` TIME(4) NULL DEFAULT NULL , `timeE(g.lab)` TIME(4) NULL DEFAULT NULL ,"
                + " `professor` VARCHAR(60) NULL DEFAULT NULL , PRIMARY KEY (`ID`))";
        try {
            ps = con.prepareStatement(code);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void resetID(String name) {
        con = connect.ConnectDB();
        Statement ps;
        String code1 = "ALTER IGNORE TABLE `" + name + "` DROP `ID`";
        String code2 = "ALTER TABLE `" + name + "` ADD ID INT( 10 ) UNSIGNED AUTO_INCREMENT PRIMARY KEY FIRST";
        try { 
            ps = con.createStatement();
            ps.executeUpdate(code1);
            ps.executeUpdate(code2);
            
        } catch (SQLException ex) {
            Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
