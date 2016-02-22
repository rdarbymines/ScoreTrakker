
public class Student implements Comparable<Student> {
	String name;
	int score;
	public Student(String sName, int sScore) {
		name = sName;
		score = sScore;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	@Override
	public int compareTo(Student arg0) {
		if (score >= arg0.getScore())
			return 1;
		return 0;
	}
	
	public String toString() {
		return name + " " + score;
	}
}
