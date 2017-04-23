package com.swapi.dto;

import java.io.Serializable;

/**
 * This class gets and sets film titles
 * 
 * @author rakes
 *
 */
public class Films implements Serializable {

	private static final long serialVersionUID = 1L;
	private String strTitle;

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

}
