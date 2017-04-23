package com.swapi.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This class gets ans sets Resident attributes
 * 
 * @author rakes
 */
public class Residents implements Serializable {

	private static final long serialVersionUID = 1L;
	private String strName;
	private int iHeigt;
	private String iMass;

	private List<Films> objFilm;

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public int getiHeigt() {
		return iHeigt;
	}

	public void setiHeigt(int iHeigt) {
		this.iHeigt = iHeigt;
	}

	public String getiMass() {
		return iMass;
	}

	public void setiMass(String iMass) {
		this.iMass = iMass;
	}

	public List<Films> getObjFilm() {
		return objFilm;
	}

	public void setObjFilm(List<Films> objFilm) {
		this.objFilm = objFilm;
	}

}
