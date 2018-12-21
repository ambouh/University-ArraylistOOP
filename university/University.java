/*NAME: Andres Mbouh
 *COURSE: CMSC132 - 0203
 *
 *HONOR PLEDGE: I pledge on my honor that I have not given or received any 
 *unauthorized assistance on this assignment/examination. 
 *
 *PURPOSE: This class simulates a virtual university which can add or cancel 
 *courses, along with registering students or provide a student with the 
 *option to drop the course or cancel all their registrations. 
 **/

package university;

import java.util.ArrayList;

public class University {

	// FIELDS
	ArrayList<Course> allCourses;
	private int numCourses;

	// CONSTRUCTOR
	public University() {
		allCourses = new ArrayList<Course>();
		numCourses = 0;
	}

	/*
	 * addCourse creates a temporary course (tempCourse), checks if tempCourse
	 * exists in the university. if the university doesn't have tempCourse, it
	 * adds tempCourse to current university and returns current university if
	 * tempCourse exists already, it returns current university with out any
	 * changes
	 */
	public University addCourse(String department, int number, int numSeats) {
		Course tempCourse = new Course(department, number, numSeats);
		if (!allCourses.contains(tempCourse)) {
			allCourses.add(tempCourse);
			numCourses++;
		}
		return this;
	}

	/*
	 * cancelCourse creates a course replica (courseReplica), uses it to check
	 * in allCourses for courseReplica. if there's a match, this course is
	 * removed from allCourses and return true. if there's no match, it returns
	 * false with out changes
	 */
	public boolean cancelCourse(String department, int number) {
		boolean isCanceled = false;
		Course courseReplica = new Course(department, number, 0);

		if (allCourses.contains(courseReplica)) {
			allCourses.remove(courseReplica);
			numCourses--;
			isCanceled = true;

		}
		return isCanceled;
	}

	/* numCourses returns the total number of courses in the current university
	 */
	public int numCourses() {
		return allCourses.size();
	}

	/*
	 * add adds a student to the course passed in parameter. first, it checks in
	 * allCourses for courseReplica. if exists, it checks if course has
	 * available seats. then checks if student has less than 5 courses. if it's
	 * all true it adds student and returns true. if any step is false, it
	 * returns false with out any changes
	 */
	public boolean add(String department, int number, String name) {
		Course courseReplica = new Course(department, number, 0);
		boolean added = false;

		if (allCourses.contains(courseReplica)) {

			int courseCurrSize = this.getCourse(courseReplica).numSeats.size();
			int courseMaxSize = this.getCourse(courseReplica).getMaxSeats();

			if (courseCurrSize < courseMaxSize) {

				int numRegCourses = numCoursesRegisteredFor(name);

				if (numRegCourses < 5) {
					if (!(this.getCourse(courseReplica).nameExist(name))) {
						this.getCourse(courseReplica).numSeats.add(name);
						added = true;
					}

				}

			}

		}

		return added;
	}

	/*
	 * numStudentsInCourse returns the total number of students in the passed in
	 * course.
	 */
	public int numStudentsInCourse(String department, int number) {
		int courseCurrSize = 0;
		Course courseChecker = getCourse(department, number);

		if (courseChecker != null) {

			// courseCurrSize calls getNumSeats for the number of students
			courseCurrSize = getCourse(courseChecker).getNumSeats();
		}
		return courseCurrSize;

	}

	/*
	 * isRegisteredForCourse registers a student in into passed in course. if
	 * course exists, name is added to course and returns true. if course
	 * doesn't exist, it returns false with out any changes
	 */
	public boolean isRegisteredForCourse(String department, int number,
			String name) {
		boolean isRegistered = false;
		Course courseChecker = getCourse(department, number);

		if (courseChecker != null) {
			isRegistered = courseChecker.nameExist(name);
		}

		return isRegistered;
	}

	/*
	 * numCoursesRegisteredFor counts the number of courses that a student with
	 * name is registered in and returns this number
	 */
	public int numCoursesRegisteredFor(String name) {
		int counter = 0;

		for (Course thisCourse : allCourses) {
			if (thisCourse.nameExist(name)) {
				counter++;
			}
		}

		return counter;
	}

	/*
	 * drop removes a student from a course. it calls isRegisteredForCourse to
	 * check for a student with name. if true, removes name from allCourses
	 * arraylist and returns true. if false, returns false without any changes
	 */
	public boolean drop(String department, int number, String name) {
		boolean isDropped = false;
		
		if (this.isRegisteredForCourse(department, number, name)) {
			this.getCourse(department, number).numSeats.remove(name);
			isDropped = true;
		}

		return isDropped;
	}

	/*
	 * cancelRegistration clears student with name from all his registered
	 * courses by iterating through allCourses and removing name as it exists
	 * return true if removed. and returns false, if student isn't registered at
	 * all
	 */
	public boolean cancelRegistration(String name) {
		boolean isCanceled = false;
		
		for (Course c : allCourses) {
			if (c.nameExist(name)) {
				isCanceled = c.numSeats.remove(name);
			}
		}

		return isCanceled;
	}

	/* HELPER METHODS */

	/*
	 * getCourse checks in allCourses for thisCourse. if there's a match, it
	 * returns thisCourse. if there's no match, returns null
	 */
	public Course getCourse(Course c) {

		for (Course thisCourse : allCourses) {
			if (thisCourse.equals(c)) {
				return thisCourse;
			}
		}

		return null;
	}

	/*
	 * getCourse-overload checks in allCourses using department and number. if
	 * there's a match, it returns thisCourse. if there's no match, returns null
	 */
	public Course getCourse(String department, int number) {

		Course courseChecker = new Course(department, number, 0);

		if (allCourses.contains(courseChecker)) {
			return getCourse(courseChecker);
		}
		
		return null;
	}

}
