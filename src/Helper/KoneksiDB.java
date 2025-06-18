/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class KoneksiDB {
    public static Connection getConnection(){
        MysqlDataSource data = new MysqlDataSource();
        data.setDatabaseName("dbsistem");
        data.setUser("root");
        data.setPassword("");
        try {
            Connection con = data.getConnection();
            System.out.println("koneksi berhasil");
            return con;
        } catch(SQLException e) {
            System.out.println("tidak connect");
            return null;
        }
    }
}
