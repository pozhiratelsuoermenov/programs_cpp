import java.sql.*;
import java.util.Scanner;

public class ProgramZadanie1Excel {
	Connection co;
	Statement statmt;
	ResultSet resSet;

	public static void main (String ... args) {
		ProgramZadanie1Excel program = new ProgramZadanie1Excel();
		program.open();
		program.view();
		program.close();
		
	}
	//Connection
	void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			co = DriverManager.getConnection (
				"jdbc:sqlite:zadanie1.db");
			System.out.println("Connected");
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Insert
	void insertTarget() {
		Scanner scanner = new Scanner (System.in);
		System.out.println("Введите имя цели");
		String nameTarget = scanner.nextLine ();
		//nameTarget = nameTarget.replaceAll(" ", "%20");
		try {
			String query = "INSERT INTO 'Target' (name_target) " + 
			"VALUES (" + " '" + nameTarget + "' " + ")";
			statmt = co.createStatement();
			statmt.executeUpdate(query);

			System.out.println("Цель добавлена");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void view() {
		try {
			statmt = co.createStatement();
			int id;

			Scanner scanner = new Scanner (System.in);
			System.out.println("Введите номер документа");
			id = Integer.parseInt(scanner.nextLine ());

			resSet = statmt.executeQuery("SELECT * FROM documents WHERE id = " + id);

			while (resSet.next()) {
				int id_sql = resSet.getInt("id");
				String zagolovok = resSet.getString("zagolovok");
				String index = resSet.getString("index");
				//String name = resSet.getString("name");
				String name = new String(resSet.getString("name").getBytes("ISO-8859-1"), "cp866");
				String date = resSet.getString("date");
				String sheets = resSet.getString("sheets");
				String number = resSet.getString("number");
				String direction = resSet.getString("direction");
				String note = resSet.getString("note");
				String other = resSet.getString("other");

				//Выводим на экран
				System.out.println("id = " + id_sql);
				System.out.println("zagolovok = " + zagolovok);
				System.out.println("index = " + index);
				System.out.println("name = " + name);
				System.out.println("date = " + date);
				System.out.println("sheets = " + sheets);
				System.out.println("number = " + number);
				System.out.println("direction = " + direction);
				System.out.println("note = " + note);
				System.out.println("other = " + other);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Disconnect
	void close() {
		try {
			co.close();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}