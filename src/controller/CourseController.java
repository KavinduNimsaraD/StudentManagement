/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DBConnection;
import model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList; 
import java.sql.ResultSet;

public class CourseController {

    // 1. Course එකක් Database එකට Save කරන Method එක
    public boolean addCourse(Course course) throws SQLException, ClassNotFoundException {
        
        // SQL Insert Query එක (මෙතන ප්‍රශ්නාර්ථ ලකුණු 2යි තියෙන්නේ)
        String sql = "INSERT INTO Course (course_name, duration) VALUES (?, ?)";
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        pstm.setString(1, course.getCourseName());
        pstm.setString(2, course.getDuration());
        
        return pstm.executeUpdate() > 0;
    }

    // 2. Course එකක් Update කරන Method එක
    public boolean updateCourse(Course course) throws SQLException, ClassNotFoundException {
        
        // SQL Update Query එක
        String sql = "UPDATE Course SET course_name=?, duration=? WHERE course_id=?";
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        pstm.setString(1, course.getCourseName());
        pstm.setString(2, course.getDuration());
        pstm.setInt(3, course.getCourseId()); // 3 වෙනි ප්‍රශ්නාර්ථ ලකුණ අයිති ID එකට
        
        return pstm.executeUpdate() > 0;
    }

    // 3. Course එකක් Delete කරන Method එක
    public boolean deleteCourse(int courseId) throws SQLException, ClassNotFoundException {
        
        // SQL Delete Query එක
        String sql = "DELETE FROM Course WHERE course_id=?";
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        pstm.setInt(1, courseId);
        
        return pstm.executeUpdate() > 0;
    }
    
    
    
    // 4. Database එකේ තියෙන Courses ඔක්කොම අරන් එන Method එක
    public ArrayList<Course> getAllCourses() throws SQLException, ClassNotFoundException {
        
        ArrayList<Course> courseList = new ArrayList<>();
        String sql = "SELECT * FROM Course";
        
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();
        
        while (rst.next()) {
            Course course = new Course();
            course.setCourseId(rst.getInt("course_id"));
            course.setCourseName(rst.getString("course_name"));
            course.setDuration(rst.getString("duration"));
            
            courseList.add(course);
        }
        
        return courseList;
    }
    
    
    
    
}