import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class ConnectDB {
	private static Connection myConnection;
	private String url="jdbc:mysql://localhost:3306/contact_book?useSSL=false";
	private String user="root";
	private String pass="root";	
		public ConnectDB() throws SQLException {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				 e.printStackTrace();
			}
		 ConnectDB.myConnection=DriverManager.getConnection(url,user,pass);
	}
	public static List<Contact> getList() throws SQLException {
		List<Contact> contacts=new ArrayList<Contact>();
		String sql="select * from contacts ORDER BY nume";
		Statement myStmt=myConnection.createStatement();
		ResultSet myRs=myStmt.executeQuery(sql);
		while (myRs.next()) {
			contacts.add(new Contact(myRs.getString("nume"),myRs.getString("prenume"),myRs.getString("phone"),
					myRs.getString("fix"),myRs.getString("email"),myRs.getString("adresa"),
					myRs.getString("oras"),myRs.getString("judet"),myRs.getString("zipcode")));
		}
		return contacts;
	}
	public static void addContact(String firstn,String lastn,String mobile,String fix,String email,String county,String city,String address,String zipcode) throws SQLException{
		String sql="INSERT INTO contacts (nume,prenume,phone,fix,email,adresa,oras,judet,zipcode) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement myStmt=myConnection.prepareStatement(sql);
		myStmt.setString(1,lastn);
		myStmt.setString(2,firstn);
		myStmt.setString(3,mobile);
		myStmt.setString(4,fix);
		myStmt.setString(5,email);
		myStmt.setString(6,county);
		myStmt.setString(7,city);
		myStmt.setString(8,address);
		myStmt.setString(9,zipcode);
		myStmt.executeUpdate();
		myStmt.close();
	}
	public static void editContact(String firstn,String lastn,String mobile,String fix,String email,String county,String city,String address,String zipcode, String old_phone) throws SQLException{
		String sql="UPDATE contacts set nume=?,prenume=?,phone=?,fix=?,email=?,adresa=?,oras=?,judet=?,zipcode=? WHERE phone=?";
		PreparedStatement myStmt=myConnection.prepareStatement(sql);
		myStmt.setString(1,lastn);
		myStmt.setString(2,firstn);
		myStmt.setString(3,mobile);
		myStmt.setString(4,fix);
		myStmt.setString(5,email);
		myStmt.setString(6,county);
		myStmt.setString(7,city);
		myStmt.setString(8,address);
		myStmt.setString(9,zipcode);
		myStmt.setString(10,old_phone);
		myStmt.executeUpdate();
		myStmt.close();
	}
	public static void deleteContact(String phone) throws SQLException {
		Statement myStmt=myConnection.createStatement();
		String sql="delete from contacts where phone='"+phone+"';";
		myStmt.executeUpdate(sql);
	}
}