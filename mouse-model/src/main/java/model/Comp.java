package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Comp {
	@Id
	@GeneratedValue
	private Long compId;
	private String compName;
	@OneToMany(mappedBy = "comp", targetEntity = Person.class)
	private List<Person> people;

	public Comp(String compName) {
		this.compName = compName;
	}

	public Comp() {

	}

	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
}
