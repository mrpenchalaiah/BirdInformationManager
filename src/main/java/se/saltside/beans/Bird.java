package se.saltside.beans;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bird {

	@Id
	private String id;
	private String name;
	private String family;
	private List<String> continents;
	private Date added;
	private boolean visible;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public List<String> getContinents() {
		return continents;
	}
	public void setContinents(List<String> continents) {
		this.continents = continents;
	}
	
	public Date getAdded() {
		return added;
	}
	public void setAdded(Date added) {
		this.added = added;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	@Override
    public boolean equals(Object obj) {
		if(null == obj) {
			return false;
		}
		Bird bird = (Bird) obj;
		boolean isEquals = false;
		if(StringUtils.equalsIgnoreCase(name, bird.getName())) {
			if(StringUtils.equalsIgnoreCase(family, bird.getFamily())) {
				isEquals = true;
			}
		}
		return isEquals;
		
	}
	
	
}
