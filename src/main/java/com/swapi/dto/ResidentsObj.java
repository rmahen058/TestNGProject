package com.swapi.dto;

import java.util.ArrayList;

public class ResidentsObj {
	private String rName;
	private int rHeight;
	private int rMass;
	private ArrayList<String> rFilms;
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public int getrHeight() {
		return rHeight;
	}
	public void setrHeight(int rHeight) {
		this.rHeight = rHeight;
	}
	public int getrMass() {
		return rMass;
	}
	public void setrMass(int rMass) {
		this.rMass = rMass;
	}
	public ArrayList<String> getrFilms() {
		return rFilms;
	}
	public void setrFilms(ArrayList<String> rFilms) {
		this.rFilms = rFilms;
	}
	
	

}
