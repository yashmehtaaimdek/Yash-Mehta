package persistence;

import utility.*;
import java.sql.*;

public class CrudImplementation implements CrudInterface {
	
	DatabaseConnection instance = null;
	private Connection con = null;
	public Statement stmt = null;
	/*
		private void establishConnection() {
			//INSIDE SELECT METHOD
			try {
				instance = DatabaseConnection.getInstance();
				con = instance.getConnection();
				stmt = con.createStatement(); 
				
			} catch (SQLException e) {
				System.out.println("Database Connection Establishment Failed : " + e.getMessage());
			}	
		}
	*/
	
	public void select() throws SQLException {
		try {
			instance = DatabaseConnection.getInstance();
			con = instance.getConnection();
			stmt = con.createStatement();
			
			ResultSet selectQ = stmt.executeQuery("SELECT * FROM \"Doctor\"");
			System.out.println();
			while(selectQ.next())
			{
				int id = selectQ.getInt("Doctor_id");
		        String  name = selectQ.getString("Doctor_name");
		        boolean status  = selectQ.getBoolean("Is_available");
		        String specialization   = selectQ.getString("Specialization");
		        long contact = selectQ.getLong("Contact_number");
		            
		        System.out.println( "ID = " + id );
		        System.out.println( "NAME = " + name );
		        if(status == true ) {
		        	System.out.println( "Status = Available " );	
		        }
		        else {
		        	System.out.println( "Status = Not available " );
		        }
		        System.out.println( "Specialization = " + specialization );
		        System.out.println( "Contact = " + contact );
		        System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Database Connection Establishment Failed : " + e.getMessage());
			System.out.println("Failed To Fetch Records : " + e.getMessage());
		}
	}
	
	public void insert() throws SQLException {
		try {
			System.out.println("Insert Query = INSERT INTO \"Doctor\" (\"Doctor_id\", \"Doctor_name\", \"Is_available\", \"Specialization\", \"Contact_number\") VALUES (6, 'Jay', false, 'ENT', 8456521213) ");
			int insertQ1 = stmt.executeUpdate("INSERT INTO \"Doctor\" (\"Doctor_id\", \"Doctor_name\", \"Is_available\", \"Specialization\", \"Contact_number\") VALUES (6, 'Jay', false, 'ENT', 8456521213)");	
			System.out.println("Records affected = "+insertQ1);  
			System.out.println();
			   
			System.out.println("Insert Query = INSERT INTO \"Doctor\" (\"Doctor_id\", \"Doctor_name\", \"Is_available\", \"Specialization\", \"Contact_number\") VALUES (7, 'Priyank', true, 'BDS', 7408321216) ");
			int insertQ2 = stmt.executeUpdate("INSERT INTO \"Doctor\" (\"Doctor_id\", \"Doctor_name\", \"Is_available\", \"Specialization\", \"Contact_number\") VALUES (7, 'Priyank', true, 'BDS', 7408321216)");
			System.out.println("Records affected = "+insertQ2); 
			System.out.println();
		} catch (SQLException e) {
			System.out.println("Failed To Insert Records : " + e.getMessage());
		}
	}
	
	public void update() throws SQLException {
		try {
			System.out.println("Update Query = UPDATE \"Doctor\" SET \"Doctor_name\" = 'Jay' WHERE  \"Doctor_id\" = '7'");
			int updateQ = stmt.executeUpdate("UPDATE \"Doctor\" SET \"Doctor_name\" = 'Jay' WHERE  \"Doctor_id\" = '7'");
			System.out.println("Records affected = "+updateQ);
		    System.out.println();
		} catch (SQLException e) {
			System.out.println("Failed To Update Records : " + e.getMessage());
		}
	}
	
	public void delete() throws SQLException {
		try {
			System.out.println("Delete Query = DELETE FROM \"Doctor\" WHERE \"Doctor_id\" = 7");
			int deleteQ = stmt.executeUpdate("DELETE FROM \"Doctor\" WHERE \"Doctor_id\" = 7");		
			System.out.println("Records affected = "+deleteQ);
		    System.out.println();	
			
		} catch (SQLException e) {
			System.out.println("Failed To Update Records : " + e.getMessage());
		}
	}

}
