package me.max.johnson.bes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DataManager {
	private static File db;
	
	public static void checkForDB() {
		db = new File(System.getProperty("user.dir") + "/" + "db.txt");
		if (!db.exists()) {
			try {
				JOptionPane.showMessageDialog(null, "No database found, database created.");
				db.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Failed to create database.");
			}
		}
	}
	
	public static List<Player> loadPlayers(){
		ArrayList<Player> loaded = new ArrayList<Player>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(db));
			String line;
			while((line = reader.readLine()) != null) {
				loaded.add(new Player(line));
			}
			reader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to load from database.");
			e.printStackTrace();
		}
		return loaded;
	}
	
	public static void savePlayers(List<Player> players) {
		try {
			db.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(db));
			for (Player player : players) {
				writer.write(player.toString());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to save to database.");

		}
	}
}
