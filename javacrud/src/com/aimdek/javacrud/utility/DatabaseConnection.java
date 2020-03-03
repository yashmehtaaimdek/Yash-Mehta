package com.aimdek.javacrud.utility;

import java.sql.*;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/employeedb";
    private String username = "root";
    private String password = "1234";
    /*
    private DatabaseConnection() {
    	
    }
    public static DatabaseConnection getInstance() {
    	if(instance==null) {
            instance= new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException {
    	if(connection == null || connection.isClosed()) {
    		try {
    			System.out.println("Inside get Connection");
    			Class.forName("com.mysql.jdbc.Driver"); 
    			connection = DriverManager.getConnection(url, username, password);
    		} catch (Exception e) {
    			 System.out.println(e.getMessage());
    		}
    		
    	}
    	return connection;
    }
    */
      
    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Inside get Connection");
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Inside get Connection Down");
        } catch (ClassNotFoundException e) {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
    	System.out.println("Inside Return Connection");
        return connection;
    }
    
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
}
