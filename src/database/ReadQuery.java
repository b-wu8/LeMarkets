package database;

import java.sql.*;

/**
 * ReadQuery connects to the database, executes query, and returns the resultset.
 * @author bohan
 *
 */
public class ReadQuery {
	int count = 0;
	
	/**
	 * Pass in values to connect to database, returns result
	 * @param zip zipcode
	 * @param city city name
	 * @param state state name
	 * @param dis distance to look at
	 * @return string result string
	 * @throws SQLException SQL Exception
	 */
	public String read(String zip, String city, String state, int dis) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = 0;
		String url = "jdbc:mysql://localhost/markets?serverTimezone=UTC";
		String user = "root";
		String password = "";
		Connection connection = DriverManager.getConnection(url, user, password);
		String s = "";

		if (zip != "" && Integer.valueOf(zip) != 0) {
			String query = "SELECT * FROM markets WHERE zip=?";
			PreparedStatement p = connection.prepareStatement(query);
			p.setInt(1, Integer.valueOf(zip));
			ResultSet rs = p.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				System.out.println("no data");
				return s;
			}
			
			String query1 = "SELECT x, y FROM zips WHERE zip=?";
			PreparedStatement p1 = connection.prepareStatement(query1);
			p1.setInt(1, Integer.valueOf(zip));
			ResultSet rs1 = p1.executeQuery();
			rs1.next();
			double x = Double.valueOf(rs1.getString(1));
			double y = Double.valueOf(rs1.getString(2));

			String query2 = "SELECT * FROM markets WHERE zip<>?";
			PreparedStatement p2 = connection.prepareStatement(query2);
			p2.setInt(1, Integer.valueOf(zip));
			ResultSet rs2 = p2.executeQuery();
			while (rs2.next()) {
				double x1 = Double.valueOf(rs2.getDouble(26));
				double y1 = Double.valueOf(rs2.getDouble(27));
				if (distance(x,y,x1,y1) < dis) {
					s+=rs2.getString(2)+"   "+rs2.getString(4)+"   "+rs2.getString(5)+"   "+rs2.getString(7)+"   "+rs2.getString(9)+"   "+rs2.getString(10)
					+"   "+rs2.getString(11)+"   "+rs2.getString(12)+"   "+rs2.getString(13)+"   "+rs2.getString(14)+"   "
					+rs2.getString(15)+"   "+rs2.getString(16)+"   "+rs2.getString(17)+"   "+rs2.getString(18)+"   "
					+rs2.getString(19)+"   "+rs2.getString(20)+"   "+rs2.getString(24)+"   "+rs2.getString(25)+"<br>";
					count++;
				}
			}
			while (rs.next()) {
				s+=rs.getString(2)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(7)+"   "+rs.getString(9)+"   "+rs.getString(10)
				+"   "+rs.getString(11)+"   "+rs.getString(12)+"   "+rs.getString(13)+"   "+rs.getString(14)+"   "
				+rs.getString(15)+"   "+rs.getString(16)+"   "+rs.getString(17)+"   "+rs.getString(18)+"   "
				+rs.getString(19)+"   "+rs.getString(20)+"   "+rs.getString(24)+"   "+rs.getString(25)+"<br>";
				count++;
			}
			return s;
		}
		if (city != "" && state == "") {
			return "There are more than one "+city+" in United States...Please specify which state.<br>";
		}
		if (city == "" && state != "") {
			String query = "SELECT * FROM markets WHERE state=?";
			PreparedStatement p = connection.prepareStatement(query);
			p.setString(1, state);
			ResultSet rs = p.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				System.out.println("no data");
			}
			
			while (rs.next()) {
				s+=rs.getString(2)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(7)+"   "+rs.getString(9)+"   "+rs.getString(10)
				+"   "+rs.getString(11)+"   "+rs.getString(12)+"   "+rs.getString(13)+"   "+rs.getString(14)+"   "
				+rs.getString(15)+"   "+rs.getString(16)+"   "+rs.getString(17)+"   "+rs.getString(18)+"   "
				+rs.getString(19)+"   "+rs.getString(20)+"   "+rs.getString(24)+"   "+rs.getString(25)+"<br>";
				count++;
			}
			return s;
		}
		
		if (city != "" && state != "") {
			String query1 = "SELECT AVG(zips.x), AVG(zips.y) FROM zips, (SELECT * FROM states) AS states WHERE states.abb=zips.state AND zips.city=? AND states.state=?;";
			PreparedStatement p1 = connection.prepareStatement(query1);
			p1.setString(1, city);
			p1.setString(2, state);
			ResultSet rs1 = p1.executeQuery();
			
			rs1.next();
			if (rs1.isBeforeFirst()) {
				System.out.println("no data");
				return s;
			}
			double x = rs1.getDouble(1);
			double y = rs1.getDouble(2);

			String query2 = "SELECT * FROM markets";
			PreparedStatement p2 = connection.prepareStatement(query2);
			ResultSet rs2 = p2.executeQuery();

			if (!rs2.isBeforeFirst()) {
				System.out.println("no data");
				return s;
			}	
			while (rs2.next()) {
				double x1 = rs2.getDouble(26);
				double y1 = rs2.getDouble(27);
				if (distance(x,y,x1,y1) < dis) {
					s+=rs2.getString(2)+"   "+rs2.getString(4)+"   "+rs2.getString(5)+"   "+rs2.getString(7)+"   "+rs2.getString(9)+"   "+rs2.getString(10)
							+"   "+rs2.getString(11)+"   "+rs2.getString(12)+"   "+rs2.getString(13)+"   "+rs2.getString(14)+"   "
							+rs2.getString(15)+"   "+rs2.getString(16)+"   "+rs2.getString(17)+"   "+rs2.getString(18)+"   "
							+rs2.getString(19)+"   "+rs2.getString(20)+"   "+rs2.getString(24)+"   "+rs2.getString(25)+"<br>";
					count++;
				}
			}
			return s;
		}
		return s;
	}
	
	/**
	 * Calculate the distance between two geographical coordinates, calculated in miles
	 * @param lat1 latitude 1
	 * @param lon1 longitude 1
	 * @param lat2 latitude 2
	 * @param lon2 longitude 2
	 * @return distance distance in miles
	 */
	public double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;

			return dist;
		}
	}
	
	/**
	 * return the length of the result set
	 * @return count length of the result set
	 */
	public int getCount() {
		return count;
	}
	
	
}
