package SudentDB;

import java.util.ArrayList;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import CustomException.StudentNotFoundException;
import CustomSorting.SortStudentByAge;
import CustomSorting.SortStudentById;
import CustomSorting.SortStudentByMarks;
import CustomSorting.SortStudentByName;
import CustomException.InvalidChoiceException;

public class StudentManagementSystemImpl implements StudentManagementSystem {
	Scanner sc = new Scanner(System.in);
	Map<String, Student> db = new LinkedHashMap<String, Student>();

	@Override
	public void addStudent() {

		System.out.println("Enter Student name");
		String name = sc.next();
		System.out.println("Enter Student age");
		int age = sc.nextInt();
		System.out.println("Enter Student marks");
		int marks = sc.nextInt();
		Student s = new Student(name, age, marks);
		db.put(s.getId(), s);
		System.out.println("Student Record Inserted Sucessfully!");
		System.out.println("Student Id is:" + s.getId());

	}

	@Override
	public void displayStudent() {
		System.out.println("Enter Student Id");
		String id = sc.next();// String id=sc.next().String id=sc.next();
		id = id.toUpperCase();
		if (db.containsKey(id)) {
			Student s = db.get(id);
			System.out.println("Id: " + s.getId());
			System.out.println("Age: " + s.getAge());
			System.out.println("Name: " + s.getName());
			System.out.println("Marks: " + s.getMarks());
		} else {
			try {
				throw new StudentNotFoundException("Student with Id" + id + " is not Found!");
			} catch (StudentNotFoundException e) {
				e.getMessage();
			}
		}
	}

	@Override
	public void displayAllStudent() {
		if (db.size() != 0) {
			System.out.println("Student Records are as follows,");
			System.out.println("---------------------------------------------------");
			Set<String> k = db.keySet();
			for (String key : k) {
				System.out.println(db.get(key));
			}
		} else {
			try {
				throw new StudentNotFoundException("Student Database is Empty, Nothing to Display");
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeStudent() {
		System.out.println("Enter Student Id");
		String id = sc.next();
		id = id.toUpperCase();
		if (db.containsKey(id)) {
			db.remove(id);
			System.out.println("Student Record Deleted Sucessfully!");
		} else {
			try {
				throw new StudentNotFoundException("Student with Id\"+id+\" is not Found!");
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudent() {
		if (db.size() != 0) {
			System.out.println("Available Students Records: " + db.size());
			db.clear();
			System.out.println("All Students Records Deleted Sucessfully");
		} else {
			try {
				throw new StudentNotFoundException("Student Database is Empty, Nothing to Delete!");
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateStudent() {
		System.out.println("Enter the Student Id");
		String id = sc.next().toUpperCase();
		if (db.containsKey(id)) {
			Student s = db.get(id);

			System.out.println(
					"Enter\n-------------------------\n" + "1.To update Name\n2.To update Age\n3.To update Marks");
			int ch = sc.nextInt();
			switch (ch) {
			case 2: {
				System.out.println("Enter the Student age");
				int age = sc.nextInt();
				s.setAge(age);
				System.out.println("Age Updated Sucessfully");
				break;
			}
			case 1: {
				System.out.println("Enter the Student Name");
				String name = sc.next();
				s.setName(name);
				System.out.println("Name Updated Sucessfully");
				break;
			}
			case 3: {
				System.out.println("Enter the Student Marks");
				int marks = sc.nextInt();
				s.setMarks(marks);
				System.out.println("Marks Updated Sucessfully");
				break;
			}
			default:
				try {
					throw new InvalidChoiceException("Kindly enter the valid choice");
				} catch (InvalidChoiceException e) {
					System.out.println(e.getMessage());
				}

			}
		}

		else {
			try {
				throw new StudentNotFoundException("Student with Id " + id + " is not Found!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void countStudent() {
		System.out.println("Avaliable Student Records: " + db.size());
	}

	@Override
	public void sortStudent() {
		if (db.size() >= 2) {

			Set<String> s = db.keySet();
			List<Student> l = new ArrayList<Student>();
			for (String t : s) {
				l.add(db.get(t));
			}
			System.out.println("Enter your choice\n");
			System.out.println("--------------------------");
			System.out.println(
					"1.Sort Student By Id\n2.Sort Student By Name\n3.Sort Student By Age\n4.Sort Student By Marks");
			int ch = sc.nextInt();
			switch (ch) {
			case 1: {
				Collections.sort(l, new SortStudentById());
				display(l);
				break;
			}
			case 2: {
				Collections.sort(l, new SortStudentByName());
				display(l);
				break;
			}
			case 3: {
				Collections.sort(l, new SortStudentByAge());
				display(l);
				break;
			}
			case 4: {
				Collections.sort(l, new SortStudentByMarks());
				display(l);
				break;
			}

			default:
				try {
					throw new InvalidChoiceException("Kindly enter the valid choice");
				} catch (InvalidChoiceException e) {
					System.out.println(e.getMessage());
				}
			}

		} else {
			try {
				throw new StudentNotFoundException("No Sufficient Student Records to Sort");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentWithHighestMarks() {
		if (db.size() >= 2) {
			Set<String> t = db.keySet();
			List<Student> l = new ArrayList<Student>();
			for (String s : t) {
				l.add(db.get(s));
			}
			Collections.sort(l, new SortStudentByMarks());
			System.out.println("The Student Records are as follows,");
			System.out.println(l.get(l.size() - 1));
		} else {
			try {
				throw new StudentNotFoundException("No Sufficient Student Records to Sort");
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}
	}

	@Override
	public void getStudentWithLowesttMarks() {
		if (db.size() >= 2) {
			Set<String> t = db.keySet();
			List<Student> l = new ArrayList<Student>();
			for (String s : t) {
				l.add(db.get(s));
			}
			Collections.sort(l, new SortStudentByMarks());
			System.out.println("The Student Records are as follows,");
			System.out.println(l.get(0));
		} else {
			try {
				throw new StudentNotFoundException("No Sufficient Student Records to Sort");
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}
		}
	}

	private static void display(List<Student> l) {
		for (Student s : l) {
			System.out.println(s);
		}
	}

}
