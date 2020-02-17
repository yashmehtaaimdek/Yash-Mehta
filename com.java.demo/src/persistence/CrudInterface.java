package persistence;

import java.sql.SQLException; 


public interface CrudInterface {
	public void select() throws SQLException;
	public void insert() throws SQLException;
	public void update() throws SQLException;
	public void delete() throws SQLException;

}
