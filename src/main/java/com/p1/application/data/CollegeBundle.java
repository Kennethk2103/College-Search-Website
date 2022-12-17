package com.p1.application.data;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

/**
 * The Class CollegeBundle.
 */
@Component
public class CollegeBundle {
    
    /** The List. */
    LinkedList<College> List;

	/**
	 * Instantiates a new college bundle.
	 */
	public CollegeBundle() {
		List = new LinkedList<College>();
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public LinkedList<College> getList() {
		return List;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(LinkedList<College> list) {
		List = list;
	}
    
}
