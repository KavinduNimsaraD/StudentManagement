/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // 1. Singleton සඳහා අවශ්‍ය දේවල්
    private static DBConnection instance;
    private final Connection connection;
    
    // 2. Private Constructor (පිටින් objects හදන එක තහනම් කිරීම)
    private DBConnection() throws ClassNotFoundException, SQLException {
        // Driver එක හොයාගැනීම
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Database එකට Connect වීම
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db", "root", "0789");
    }
    
    // 3. DBConnection Object එක ගන්න තියෙන එකම දොර
    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
    
    // 4. අර හැදුව Connection එක (පාලම) එලියට දෙන Method එක
    public Connection getConnection() {
        return connection;
    }
}