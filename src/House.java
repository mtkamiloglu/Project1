
public class House implements Comparable<House>{

	int id;
	int duration;
	double rate;
	Student student;
	
	public House(int id, int duration, double rate) {
		this.id = id;
		this.duration = duration;
		this.rate = rate;
	}
	
	public int compareTo(House other) {
		if(this.duration<other.duration) {
			return -1;
		}
		else if(this.duration==other.duration) {
			if(this.id<other.id) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else {
			return 1;
		}
	}
	
	public void locate(Student student) {
		this.student = student;
		this.duration = student.duration;
	}
	
	public boolean isFree() {
		if(this.duration==0) {
			this.student = null;
			return true;
		}
		else {
			return false;
		}
	}
}
