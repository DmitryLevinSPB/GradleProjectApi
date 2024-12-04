public class TimeTestPojo{
	private String name;
	private String job;
	private String updatedAt;


	public TimeTestPojo(String name, String job) {
		this.name = name;
		this.job = job;
	}

	public String getName(){
		return name;
	}

	public String getJob(){
		return job;
	}

}
