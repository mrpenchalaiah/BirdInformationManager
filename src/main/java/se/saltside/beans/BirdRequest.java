package se.saltside.beans;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BirdRequest {

	
	private String id;
	@NotNull(message="Bird name should not be empty")
	private String name;
	@NotNull(message="Bird family should not be empty")
	private String family;
	@Size(min=1,message="Contents should be atleast one")
	@NotNull(message="Bird information should be provided.")
	private List<String> continents;
	
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
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
