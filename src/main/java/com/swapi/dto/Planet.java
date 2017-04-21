package com.swapi.dto;

import java.util.List;

public class Planet {

	private int iPlanetId;
	private String strPlanetName;
	private long lDiameter;
	private long lPopulation;
	
	private List<Residents> objResidents;

	public int getiPlanetId() {
		return iPlanetId;
	}

	public void setiPlanetId(int iPlanetId) {
		this.iPlanetId = iPlanetId;
	}

	public String getStrPlanetName() {
		return strPlanetName;
	}

	public void setStrPlanetName(String strPlanetName) {
		this.strPlanetName = strPlanetName;
	}

	

	public long getlDiameter() {
		return lDiameter;
	}

	public void setlDiameter(long lDiameter) {
		this.lDiameter = lDiameter;
	}

	public long getlPopulation() {
		return lPopulation;
	}

	public void setlPopulation(long lPopulation) {
		this.lPopulation = lPopulation;
	}

	public List<Residents> getObjResidents() {
		return objResidents;
	}

	public void setObjResidents(List<Residents> objResidents) {
		this.objResidents = objResidents;
	}
	
	
}
