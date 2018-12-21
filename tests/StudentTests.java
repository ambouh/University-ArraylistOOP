/*NAME: Andres Mbouh
 *COURSE: CMSC132 - 0203
 *
 *HONOR PLEDGE: I pledge on my honor that I have not given or received any 
 *unauthorized assistance on this assignment/examination. 
 *
 *PURPOSE: Tests all possibilities for cases not encountered in publicTest 
 **/
package tests;

import org.junit.*;

import university.Course;
import university.University;
import static org.junit.Assert.*;

public class StudentTests {

	// write your student tests in this class

	// Adds a course to the university
	@Test
	public void testPublic1() {
		University university = new University();

		university.addCourse("CMSC", 123, 10);
		int count = university.numCourses();
		assertTrue(count == 1);
	}

	// Makes sure different departments can have same course numbers
	@Test
	public void testPublic2() {
		University university = new University();

		university.addCourse("CMSC", 123, 10);
		university.addCourse("ANTH", 123, 10);

		int count = university.numCourses();
		assertTrue(count == 2);
	}

	// Tests if cancelCourse method removes a course passed in the parameters
	@Test
	public void testPublic3() {
		University university = new University();

		university.addCourse("CMSC", 123, 10);
		university.addCourse("ANTH", 123, 10);

		university.cancelCourse("CMSC", 123);

		int count = university.numCourses();
		assertTrue(count == 1);
	}

	// Tests to check if two different universities with same course can be
	// created without affecting either university.
	@Test
	public void testPublic4() {
		University university1 = new University();
		University university2 = new University();

		for (int var = 0; var < 5; var++) {
			university1.addCourse("CMSC", var, 10);
			university2.addCourse("CMSC", var, 10);
		}

		Course course1 = university1.getCourse("CMSC", 2);
		Course course2 = university2.getCourse("CMSC", 2);

		// check if each university have the same course
		assertTrue(course1.equals(course2));
	}

	/* Tests if you can cancel course and add the same course again */
	@Test
	public void testPublic5() {
		University university1 = new University();

		university1.addCourse("CMSC", 123, 10);

		Course course = new Course("CMSC", 123, 10);

		// Checks if course exists in university1
		assertTrue(course.equals(university1.getCourse("CMSC", 123)));

		university1.cancelCourse("CMSC", 123);

		// Asserts that course has been removed
		assertEquals(university1.getCourse("CMSC", 123), null);

		university1.addCourse("CMSC", 123, 10);

		// Checks if course exists in university1
		assertTrue(course.equals(university1.getCourse("CMSC", 123)));
	}

}
