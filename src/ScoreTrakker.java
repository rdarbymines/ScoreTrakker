import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ScoreTrakker {
	ArrayList<Student> students;
	String[] files = {"scores.txt", "badscores.txt", "nofile.txt"};
	
	public ScoreTrakker() {
		students = new ArrayList<Student>();
	}
	
	public void addStudent(String line) throws Exception{
		String[] segments = line.split(" ");
		if (segments.length != 3)
			throw new Exception("Invalid Name or Score: input requires 3 arguments. " + segments.length + " are included in '" + line + "'");
		else {
			Student additional = new Student(segments[0] + " " + segments[1], Integer.parseInt(segments[2]));
			students.add(additional);
		}
			 
	}
	
	public void loadDataFromFile(String fileName) throws Exception, FileNotFoundException{
		String line = null;
		try { 
			FileReader reader = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(reader);
			while((line = buff.readLine()) != null) {
				addStudent(line);
				
			}
			buff.close();
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("Could not fine file: " + fileName);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			//makes more sense to put here 
		}
	}
	
	public void printInOrder() {
		ArrayList<Student> backupStudents = students;
		ArrayList<Student> sortedStudents = new ArrayList<Student>();
		System.out.println("Printing Student Info: " + backupStudents.size());
		while (backupStudents.size() > 0) {
			int index = 0;			
			for (int i = 0; i < backupStudents.size(); i++) {
				if (index != i && backupStudents.get(index).compareTo(backupStudents.get(i)) == 0)
					index = i;
			}
			sortedStudents.add(backupStudents.get(index));
			backupStudents.remove(index);
		}
		for (Iterator<Student> iter = sortedStudents.iterator(); iter.hasNext(); ) {
			Student currentStudent = iter.next();			
			System.out.println(currentStudent.toString());			
		}		
		
	}
	
	public void processFiles() {
		for (int i = 0; i < files.length; i++) {
			try {
				loadDataFromFile(files[i]);
				printInOrder();
			}
			catch (FileNotFoundException e) {
				System.out.println(e.getLocalizedMessage());
			} 
			catch (IOException e) {			
				e.printStackTrace();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
	}
	
	public static void main(String args[]) {
		ScoreTrakker tracker = new ScoreTrakker();
		System.out.println("Process Files");		
		tracker.processFiles();
	}
}
