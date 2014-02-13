package dataProcess.domainModel;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity

public class Administrator{

	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	private Login login;
	
	public Administrator() {
		
	}
	
	public Administrator(Login login){
		this.login=login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}


}
