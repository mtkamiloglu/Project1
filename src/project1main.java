import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class project1main {

	public static void main(String args[]) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		int maxDuration =  0;

		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<House> houses = new ArrayList<House>();
		ArrayList<Student> graduated = new ArrayList<Student>();

		while(in.hasNext()) {
			String data = in.nextLine();	
			String[] datas = data.split(" ");

			//Reading house data from input file
			if(datas[0].equals("h")) {
				int id = Integer.parseInt(datas[1]);
				int duration = Integer.parseInt(datas[2]);
				double rate = Double.parseDouble(datas[3]);
				houses.add(new House(id, duration, rate));

			}
			//Reading student data from input file
			else if(datas[0].equals("s")) {
				int id = Integer.parseInt(datas[1]);
				String name = datas[2];
				int duration = Integer.parseInt(datas[3]);
				double minRate = Double.parseDouble(datas[4]);
				if(duration==0) {
					graduated.add(new Student(id, name, duration, minRate));
				}
				else {
					students.add(new Student(id, name, duration, minRate));
				}
				if(duration>maxDuration) {
					maxDuration = duration;
				}
			}
		}

		//For each semester each loop
		for(int s = 0; s<maxDuration; s++) {
			Collections.sort(students, new StudentComparator());
			Collections.sort(houses, new HouseComparator());		
			ArrayList<Student> delStudents = new ArrayList<Student>();

			//Locating students to the houses 
			for(Student student : students) {

				for(House house : houses) {
					if(house.duration==0 && student.minRate <= house.rate) {
						house.duration = student.duration;
						delStudents.add(student);
						break;}
					else if(house.duration!=0) {
						break;
					}
					else {
						continue;}
				}

				student.duration = student.duration-1;
				if(student.duration == 0 && !delStudents.contains(student)) {
					graduated.add(student);
				}
				Collections.sort(houses, new HouseComparator());
			}

			// Deleting located students from the students list
			for(Student student : delStudents) {
				students.remove(student);
			}

			if(students.size()==0) {
				break;
			}

			//Add the graduated students to another list
			for(Student student : graduated) {
				students.remove(student);
			}


			//Decreasing houses' duration by 1 at the end of each semester
			for(int i = houses.size() - 1; i >= 0; i--) {
				if(houses.get(i).duration != 0) {
					houses.get(i).duration -=1;
				}
				else if(houses.get(i).duration==0) {
					break;
				}
			}
		}

		for(Student student : graduated) {
			students.add(student);
		}

		Collections.sort(students, new StudentComparator());
		Collections.sort(houses, new HouseComparator());

		for(int i=0; i<students.size(); i++) {
			out.println(students.get(i).name);
		}
	}		
}
