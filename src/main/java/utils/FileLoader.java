package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileLoader {
	public static ArrayList<String> loadFile(String path) {
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(
			new InputStreamReader(
				FileLoader.class.getClassLoader().getResourceAsStream(path)
			))) {
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			return list;
		} catch (Exception e) {
			System.err.println("Error reading file: " + e.getMessage());
			e.printStackTrace();
			return list;
		}
	}
	
}
