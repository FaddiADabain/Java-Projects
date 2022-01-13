public class Instructor 
{
	private int id;
	private String name;
	private Department department;
	
	Instructor(int id, String name, Department department)
	{
		this.id = id;
		this.name = name;
		this.department = department;
	}
	
	public Instructor() 
	{
	}

	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Department getDepartment()
	{
		return department;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDepartment(Department department)
	{
		this.department = department;
	}
}
