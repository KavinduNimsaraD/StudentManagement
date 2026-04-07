/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DBConnection;
import model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.util.ArrayList; // මේක උඩින්ම දාගන්න (Alt + Enter)
import java.sql.ResultSet;  // මේකත් උඩින්ම දාගන්න


public class StudentController {

    // 1. Student කෙනෙක්ව Database එකට Save කරන Method එක
    public boolean addStudent(Student student) throws SQLException, ClassNotFoundException {
        
        // 2. SQL Query එක ලියනවා (ප්‍රශ්නාර්ථ ලකුණු දාලා තියෙන්නේ පස්සේ අගයන් දාන්න)
        String sql = "INSERT INTO Student (name, age, contact) VALUES (?, ?, ?)";
        
        // 3. Database Connection එක ගන්නවා (අර අපි කලින් හදපු Singleton එකෙන්)
        Connection connection = DBConnection.getInstance().getConnection();
        
        // 4. PreparedStatement එක හදනවා (මේකෙන් තමයි Query එක DB එකට යවන්නේ)
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        // 5. ප්‍රශ්නාර්ථ ලකුණු වලට අගයන් දානවා (Student object එකෙන් අරගෙන)
        pstm.setString(1, student.getName());
        pstm.setInt(2, student.getAge());
        pstm.setString(3, student.getContact());
        
        // 6. Query එක Run කරනවා (වෙනස් වුණ පේළි ගාණ 0 ට වඩා වැඩි නම් Save වුණා කියලා හිතනවා)
        return pstm.executeUpdate() > 0;
    }
    
    
    
    
    public ArrayList<Student> getAllStudents() throws SQLException, ClassNotFoundException {
        
        // හිස් List එකක් හදාගන්නවා ළමයින්ව දාන්න
        ArrayList<Student> studentList = new ArrayList<>();
        
        // SQL Select Query එක (ඔක්කොම දත්ත ගන්න)
        String sql = "SELECT * FROM Student";
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        // ExecuteQuery එකෙන් කරන්නේ Database එකේ තියෙන දත්ත ටික ResultSet (ප්‍රතිඵල පෙට්ටියක්) විදියට අරන් එන එක
        ResultSet rst = pstm.executeQuery();
        
        // පෙට්ටියේ දත්ත තියෙනකම් එකින් එක අරන් Student Object එකක් හදලා List එකට දානවා
        while (rst.next()) {
            Student student = new Student();
            student.setStudentId(rst.getInt("student_id"));
            student.setName(rst.getString("name"));
            student.setAge(rst.getInt("age"));
            student.setContact(rst.getString("contact"));
            
            studentList.add(student);
        }
        
        return studentList; // අන්තිමට ළමයි පිරිච්ච List එක ආපහු දෙනවා
    }

    
    
   // 2. Student කෙනෙක්ගේ විස්තර Update (වෙනස්) කරන Method එක
    public boolean updateStudent(Student student) throws SQLException, ClassNotFoundException {
        
        String sql = "UPDATE Student SET name=?, age=?, contact=? WHERE student_id=?";
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        pstm.setString(1, student.getName());
        pstm.setInt(2, student.getAge());
        pstm.setString(3, student.getContact());
        pstm.setInt(4, student.getStudentId()); 
        
        return pstm.executeUpdate() > 0;
    }

    // 3. Student කෙනෙක්ව Database එකෙන් මකා දමන (Delete) Method එක
    public boolean deleteStudent(int studentId) throws SQLException, ClassNotFoundException {
        
        String sql = "DELETE FROM Student WHERE student_id=?";
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        pstm.setInt(1, studentId);
        
        return pstm.executeUpdate() > 0;
    }
    
}
