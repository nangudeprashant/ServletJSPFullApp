package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import databaseUtil.MySQLUtil;
import model.Student;

public class StudentDAO {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Student s = new Student();
	List<Student> studentList = new LinkedList<Student>();

	public StudentDAO() {
		Student s = new Student();
		s.setId(1);
		s.setName("Name1");
		s.setAddress("Address1");
		studentList.add(s);
		Student s1 = new Student();
		s1.setId(2);
		s1.setName("Name2");
		s1.setAddress("Address2");
		studentList.add(s1);
		Student s2 = new Student();
		s2.setId(3);
		s2.setName("Name3");
		s2.setAddress("Address3");
		studentList.add(s2);
		Student s3 = new Student();
		s3.setId(4);
		s3.setName("Name4");
		s3.setAddress("Address4");
		studentList.add(s3);
		Student s4 = new Student();
		s4.setId(5);
		s4.setName("Name5");
		s4.setAddress("Address5");
		studentList.add(s4);
	}

	public List<Student> getStudentList() {

		return this.studentList;
	}

	public Student getStudent(int id) {
		Student s = new Student();
		s.setId(id);
		s.setName("");
		s.setAddress("");
		if (this.studentList.contains(s)) {
			return this.studentList.get(this.studentList.indexOf(s));
		} else {
			return null;
		}
	}

	public boolean insertStudent(int id, String name, String address) {
		if (updateStudent(id, name, address)) {
			return true;
		} else {
			Student s = new Student();
			s.setId(id);
			s.setName(name);
			s.setAddress(address);
			this.studentList.add(s);
			return true;
		}
	}

	public boolean updateStudent(int id, String name, String address) {
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setAddress(address);
		if (this.studentList.contains(s)) {
			this.studentList.set(this.studentList.indexOf(s), s);
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteStudent(int id) {
		Student s = new Student();
		s.setId(id);
		s.setName("");
		s.setAddress("");
		if (this.studentList.contains(s)) {
			this.studentList.remove(this.studentList.indexOf(s));
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("finally")
	public List<Student> getDatabaseStudentList() {

		Statement stmt1=null;
		List<Student> studentList= new LinkedList<Student>();
		try {
			con = MySQLUtil.getConnetion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Creating statement...");
		try {
			stmt1 = con.createStatement();
			rs= stmt1.executeQuery("SELECT * FROM Student");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// STEP 5: Extract data from result set
		try {
			while (rs.next()) {
				// Retrieve by column name
				Student s1=new Student();
				s1.setId(rs.getInt("id"));
				s1.setName(rs.getString("name"));
				s1.setAddress(rs.getString("address"));
				studentList.add(s1);
				s1=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = null;
			return studentList;
		}

	}	
	
	@SuppressWarnings("finally")
	public Student getDatabaseStudent(int id) {

		try {
			con = MySQLUtil.getConnetion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Creating statement...");
		try {
			stmt = con.prepareStatement("SELECT id, name, address FROM Student where id=?");
			stmt.setInt(1, id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// STEP 5: Extract data from result set
		try {
			while (rs.next()) {
				// Retrieve by column name
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			stmt = null;
			return s;
		}

	}

	@SuppressWarnings("finally")
	public boolean insertDatabaseStudent(int id, String name, String address) throws SQLException{
		boolean result = false;
		try {
			con = MySQLUtil.getConnetion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		System.out.println("Creating statement...");
		try {
			stmt = con.prepareStatement("insert into student values(?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setString(3, name);
			int rowsEffected = stmt.executeUpdate();
			if (rowsEffected > 0) {
				result = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw e1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e;
			}
			s = null;
			stmt = null;
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean updateDatabaseStudent(int id, String name, String address) throws SQLException{
		boolean result = false;
		try {
			con = MySQLUtil.getConnetion();
		} catch (SQLException e) {
			throw e;
		}
		System.out.println("Creating statement...");
		try {
			stmt = con.prepareStatement("update student set name=? , address=? where id=? ");
			stmt.setString(1, name);
			stmt.setString(2, address);
			stmt.setInt(3, id);
			int rowsEffected = stmt.executeUpdate();
			if (rowsEffected > 0) {
				result = true;
			}
		} catch (SQLException e1) {
			throw e1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw e;
			}
			s = null;
			stmt = null;
			return result;
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteDatabaseStudent(int id) throws SQLException{
		boolean result = false;
		try {
			con = MySQLUtil.getConnetion();
		} catch (SQLException e) {
			throw e;
		}
		System.out.println("Creating statement...");
		try {
			stmt = con.prepareStatement("delete from student where id=? ");
			stmt.setInt(1, id);
			int rowsEffected = stmt.executeUpdate();
			if (rowsEffected > 0) {
				result = true;
			}
		} catch (SQLException e1) {
			throw e1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw e;
			}
			s = null;
			stmt = null;
			return result;
		}
	}

	public static void main(String[] args) {
		StudentDAO sutil = new StudentDAO();
		/*for (Student s : sutil.studentList) {
			System.out.println(s.toString());
		}

		Student s = sutil.getStudent(4);
		System.out.println("===================>" + s.toString());

		sutil.insertStudent(1, "Name11", "Address11");
		for (Student s2 : sutil.studentList) {
			System.out.println(s2.toString());
		}

		sutil.updateStudent(1, "newName1", "NewAddress1");
		for (Student s2 : sutil.studentList) {
			System.out.println(s2.toString());
		}

		sutil.deleteStudent(5);
		for (Student s1 : sutil.studentList) {
			System.out.println(s1.toString());
		}
*/
		//System.out.println(sutil.getDatabaseStudent(21).toString());
		/*if (sutil.insertDatabaseStudent(33, "Name32", "Address32")) {
			System.out.println("data inserted successfully....");
		}*/
		/*try {
			if (sutil.updateDatabaseStudent(35, "NewName32", "NewAddress32")) {
				System.out.println("data updated successfully....");
			}
			else {
				System.out.println("Error in data updation.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			if (sutil.deleteDatabaseStudent(32)) {
				System.out.println("data deleted successfully....");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		List<Student> list=sutil.getDatabaseStudentList();
		for (Student s : list) {
			System.out.println(s.toString());
		}
	}

}
