package com.swapi.dto;

import java.io.Serializable;

public class Films implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strTitle;

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	
	
}
