package com.p1.application.data;

import java.util.LinkedList;

import org.springframework.stereotype.Component;

@Component
public class CollegeBundle {
    LinkedList<College> List;

	public CollegeBundle() {
		List = new LinkedList<College>();
	}

	public LinkedList<College> getList() {
		return List;
	}

	public void setList(LinkedList<College> list) {
		List = list;
	}
    
}
