package Pojo;

public class DataItem {
	private String last_name;
	private Integer id;
	private String avatar;
	private String first_name;
	private String email;

	public DataItem() {
	}

	public DataItem(String last_name, int id, String avatar, String first_name, String email) {
		this.last_name = last_name;
		this.id = id;
		this.avatar = avatar;
		this.first_name = first_name;
		this.email = email;
	}


	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
