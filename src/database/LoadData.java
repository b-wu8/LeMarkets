package database;

import java.io.*;
import java.sql.*;
import com.opencsv.*;

/**
 * LoadData loads in the data from csv file into MySQL database.
 *	The class contains methods that does data ETL.
 * @author bohan
 *	
 */
public class LoadData {
	/**
	 * Check if a string is numeric
	 * @param s check if the input string is numeric
	 * @return boolean indicates if the input string is numeric
	 */
	public static boolean isNumeric(String s) {
		try {  
			int a = Integer.parseInt(s);  
			if (a > 0) {
			    return true;
			} else {
				return false;
			}
		} catch(NumberFormatException e){  
			return false;  
		}
	}
	
	/**
	 * Main class to read in the data from the csv file. The read in process is going to 
	 * take a while.
	 * @param argv command line argument, not implemented
	 * @throws IOException IOException
	 * @throws SQLException SQLException
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	public static void main(String[] argv) throws IOException, SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost/markets?serverTimezone=UTC";
		String user = "root";
		String password = "";

		System.out.println("Connecting to the database");
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println("Successfully connected to the database");
		Statement statement = connection.createStatement();
		
		// loads in data
		System.out.println("Preparing to load in data");
		CSVReader reader = new CSVReader(new FileReader("../data/markets.csv"));
		String[] line = null;
		line = reader.readNext();
		String name, web, address, city, county, state, session, abb;
		int id, zip;
		Double x = 0.0;
		Double y = 0.0;
		String credit, bake, cheese, crafts, flowers, eggs, seafood, herbs, vegetables, honey, jams, meat, wine, coffee, fruits, wildHarvest;
		int count = 0;
		while ((line = reader.readNext()) != null) {
			id = Integer.valueOf(line[0]);
			name = line[1].trim();
			if (name.contains("\"")) {
				name = name.replace("\"", "\'");
			}
			web = line[2];
			if (web.equals("")) {
				web = line[3];
				if (web.equals("")) {
					web = line[4];
					if (web.equals("")) {
						web = line[5];
					}
				}
			}
			address = line[7].trim();
			if (address.contains("\"")) {
				address = address.replace("\"", "\'");
			}
			if (address.equals("")) {
				continue;
			}
			city = line[8];
			county = line[9];
			state = line[10];
			if (line[11].equals("")) {
				zip = 00000;
			} else {
				if (!line[11].contains("-")) {
					if (isNumeric(line[11])) {
						zip = Integer.valueOf(line[11]);
					} else {
						zip = 00000;
					}
				} else {
					zip = Integer.valueOf(line[11].split("-")[0]);
				}
			}
			session = line[12];
			if (session.equals("")) {
				session = line[14];
				if (session.equals("")) {
					session = line[16];
					if (session.equals("")) {
						session = line[18];
					}
				}
			}
			if (!line[20].equals("")) {
				x = Double.valueOf(line[20]);
			}
			if (!line[21].equals("")) {
				y = Double.valueOf(line[21]);
			}
			credit = line[23];
			bake = line[29];
			cheese = line[30];
			crafts = line[31];
			flowers = line[32];
			eggs = line[33];
			seafood = line[34];
			herbs = line[35];
			vegetables = line[36];
			honey = line[37];
			jams = line[38];
			meat = line[40];
			wine = line[48];
			coffee = line[49];
			fruits = line[51];
			wildHarvest = line[57];
			String query = "INSERT INTO markets VALUES (\""+id+"\", \""+name+"\", \""+web+"\", \""+address+"\", \""+city+"\", \""+county+"\", '"+state+"', "+zip+", '"+session+"', '"+credit+"', '"+bake+"', '"+cheese+"', '"+crafts+"', '"+flowers+"', '"+eggs+"', '"+seafood+"', '"+herbs+"', '"+vegetables+"', '"+honey+"', '"+jams+"', '"+meat+"', '"+wine+"', '"+coffee+"', '"+fruits+"', '"+wildHarvest+"', "+x+", "+y+")";
			statement.executeUpdate(query);			
			count+=1;
		}
		reader.close();
		System.out.println("Successfully loaded in "+count+" rows of data, each row with 26 columns");
		System.out.println("Loading in zip codes data");
		CSVReader zipreader = new CSVReader(new FileReader("../data/zip_codes_states.csv"));
		line = zipreader.readNext();
		while ((line = zipreader.readNext()) != null) {
			zip = Integer.valueOf(line[0]);
			if (line[2].equals("")) {
				continue;
			}
			x = Double.valueOf(line[2]);
			y = Double.valueOf(line[1]);
			city = line[3];
			state = line[4];
			String query = "INSERT INTO zips VALUES("+zip+", "+x+", "+y+", \""+city+"\", \""+state+"\");";
			statement.executeUpdate(query);
		}
		zipreader.close();
		System.out.println("Successfully loaded in zip codes");	
		System.out.println("Loading in state names and abbreviations");
		CSVReader statereader = new CSVReader(new FileReader("../data/states.csv"));
		line = statereader.readNext();
		while ((line = statereader.readNext()) != null) {
			state = line[0];
			abb = line[1];
			String query = "INSERT INTO states VALUES(\""+state+"\", \""+abb+"\")";
			statement.executeUpdate(query);
		}
		statereader.close();
		System.out.println("Successfully loaded in states and abbreviations");
	}



}
