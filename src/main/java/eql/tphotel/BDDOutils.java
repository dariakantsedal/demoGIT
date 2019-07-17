package eql.tphotel;

import java.io.File;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class BDDOutils {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
	private static final String USER = "username";
	private static final String PASSWORD = "password";

	private static IDataSet readDataSet(String path_to_file) throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(path_to_file));
	}

	public static void insertData(String path_to_file) throws Exception {
		IDataSet dataset = readDataSet(path_to_file);
		IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataset);
		databaseTester.onSetup();
		System.out.println("Données inserées de "+path_to_file);
	}
	
	public static void compareData(String table, String path_to_file, String...col) throws SQLException, Exception 
	{
		IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
		IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
		ITable actualTable =databaseDataSet.getTable(table);
		IDataSet expectedDataSet=readDataSet(path_to_file);
		ITable expectedTable =expectedDataSet.getTable(table);
		String tab[]= col;
		Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, tab);
		}

	public static void deleteAllData(String path_to_file) throws Exception {
		IDataSet dataset = readDataSet(path_to_file);
		IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.setDataSet(dataset);
		databaseTester.onSetup();
		System.out.println("Database nettoyé");
		} 
	
	public static void deleteCertainData(String path_to_file) throws Exception {
		IDataSet dataset = readDataSet(path_to_file);
		IDatabaseTester databaseTester = new JdbcDatabaseTester(DRIVER, JDBC_URL, USER, PASSWORD);
		databaseTester.setSetUpOperation(DatabaseOperation.DELETE);
		databaseTester.setDataSet(dataset);
		databaseTester.onSetup();
		System.out.println("Supprimé des certaines données par le fichier: "+path_to_file);
		} 

	


 }
