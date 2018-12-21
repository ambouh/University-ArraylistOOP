/*NAME: Andres Mbouh
 *COURSE: CMSC132 - 0203
 *
 *HONOR PLEDGE: I pledge on my honor that I have not given or received any 
 *unauthorized assistance on this assignment/examination. 
 *
 *PURPOSE: This helper class simulates a course that stores names of students 
 *in arrayList, its course number, course capacity and department
 **/
package university;

import java.util.ArrayList;

public class Course {

	// FIELDS
	private int courseNum, maxSeats;
	private String department;
	ArrayList<String> numSeats;

	// CONSTRUCTOR
	public Course(String depart, int number, int numSeats) {

		this.department = depart;
		this.courseNum = number;
		this.maxSeats = numSeats;
		this.numSeats = new ArrayList<String>();
	}

	// returns the current total number of students in course
	public int getNumSeats() {
		return numSeats.size();
	}

	// returns the course capacity of the course
	public int getMaxSeats() {
		return maxSeats;
	}

	/*
	 * nameExist checks in numSeats for name. If there's a match, returns true.
	 * If there's no match, returns false.
	 */
	public boolean nameExist(String name) {
		return numSeats.contains(name);
	}

	/*
	 * equals compares this course with an object to see if they are equal. if
	 * object isn't null and is of Course class. And both courses department and
	 * course number are same it returns true. if not, returns false
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Course)) {
			return false;
		}

		Course course = (Course) obj;

		return (this.department.equals(course.department))
				&& (this.courseNum == course.courseNum);
	}
}
