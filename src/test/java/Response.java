public class Response{
	private String color;
	private Integer year;
	private String name;
	private Integer id;
	private String pantone_value;

	public Response() {
	}

	public Response(String color, int year, String name, int id, String pantone_value) {
		this.color = color;
		this.year = year;
		this.name = name;
		this.id = id;
		this.pantone_value = pantone_value;
	}

	public String getColor(){
		return color;
	}

	public Integer getYear(){
		return year;
	}

	public String getName(){
		return name;
	}

	public Integer getId(){
		return id;
	}

	public String getPantone_value(){
		return pantone_value;
	}
}
