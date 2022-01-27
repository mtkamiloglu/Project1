
public class Student implements Comparable<Student>{

	int id;
	String name;
	int duration;
	double minRate;
	
	public Student(int id, String name, int duration, double minRate) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.minRate = minRate;
	}

	@Override
	public int compareTo(Student other) {
		if(this.id<other.id) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
