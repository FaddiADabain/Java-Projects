import java.util.*;
import java.io.*;

public class MainMenu 
{
	public static void main(String[] args) 
	{
		try {
			Scanner scan = new Scanner(System.in);
			int menu;
			File instructorFile = new File("Input instructor file location here");
			File departmentFile = new File("Input derpartment file location here");
			Scanner instructorInput = new Scanner(instructorFile);
			Scanner departmentInput = new Scanner(departmentFile);

			Instructor[] instructors = new Instructor[10];
			int instructorsCapacity = 1;
			Department[] departments = new Department[3];
			int departmentsCapacity = 1;

			do 
			{
				
				readDepartments(departments, departmentInput);
				readInstructors(instructors, instructorInput, departments);
				
				System.out.println("1. Get instructor information\r\n" + 
						"2. Insert a new instructor\r\n" +
						"3. Exit");

				menu = scan.nextInt();

				while(menu < 1 || menu > 3)
					menu = scan.nextInt();

				if(menu == 1)
				{
					System.out.print("\nEnter the instructor ID: ");
					int idInput = scan.nextInt();

					searchInstructorById(idInput, instructors);
				}
				else if (menu == 2)
				{
					if(instructorsCapacity == instructors.length)
						extendArray(instructors);

					if(departmentsCapacity == departments.length)
						extendArray(departments);

					if(createInstructor(instructors, departments, instructorsCapacity))
						instructorsCapacity++;
					else
						continue;
				}
			}
			while(menu != 3);

			instructorInput.close();
			departmentInput.close();
			scan.close();
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public static boolean createInstructor(Instructor[] instructors, Department[] departments, int instructorsCapacity)
	{
		File instructorFile = new File("C:\\Users\\dotty\\Downloads\\Java Project\\instructor.txt");
		try 
		{
			Scanner instructorInput = new Scanner(instructorFile);

			instructors[instructorsCapacity] = new Instructor();

			Scanner scan = new Scanner(System.in);
			System.out.print("\nEnter the instructor id: ");
			int inputId = scan.nextInt();

			if(!searchId(inputId, instructors))
				instructors[instructorsCapacity].setId(inputId);
			else
			{
				System.out.println("\nInstructor id already exists in the file.\n");
				instructorInput.close();
				return false;
			}

			scan.nextLine();

			System.out.print("\nEnter the instructor name: ");
			String name = scan.nextLine();
			instructors[instructorsCapacity].setName(name);


			System.out.print("\nEnter the affiliated department name: ");
			String inputDepartment = scan.next();
			inputDepartment = inputDepartment.toUpperCase();

			if(searchDepartment(inputDepartment, departments))
				instructors[instructorsCapacity].setDepartment(searchDepartmentByName(inputDepartment, departments));
			else
			{
				System.out.println("\nThe department doesnot exist and hence the instructor record cannot be added to the file.\n");
				instructorInput.close();
				return false;
			}

			FileWriter fw = new FileWriter(instructorFile, true);
			PrintWriter output = new PrintWriter(fw);
			output.println();
			output.print(inputId + "," + name + "," + instructors[instructorsCapacity].getDepartment().getName());
			output.close();

			instructorsCapacity++;
			System.out.println();
			
			instructorInput.close();
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static void readDepartments(Department[] departments, Scanner departmentInput)
	{
		int counter = 0;
		while(departmentInput.hasNextLine())
		{
			String line = departmentInput.nextLine();
			String[] fields = line.split(",");
			departments[counter] = new Department();
			departments[counter].setName(fields[0]);
			departments[counter].setLocation(fields[1]);
			departments[counter].setBudget(Double.parseDouble(fields[2]));
			counter++;
		}
	}
	
	public static void readInstructors(Instructor[] instructors, Scanner instructorInput, Department[] departments)
	{
		int counter = 0;
		while(instructorInput.hasNextLine())
		{
			String line = instructorInput.nextLine();
			String[] fields = line.split(",");
			instructors[counter] = new Instructor();
			instructors[counter].setId(Integer.parseInt(fields[0]));
			instructors[counter].setName(fields[1]);
			instructors[counter].setDepartment(searchDepartmentByName(fields[2], departments));
			counter++;
		}
	}

	public static void extendArray(Department[] departments)
	{
		Department[] newDepartments = new Department[departments.length + 1];
		System.arraycopy(departments, 0, newDepartments, 0, departments.length);
		departments = newDepartments;
	}

	public static void extendArray(Instructor[] instructors)
	{
		Instructor[] newInstructors = new Instructor[instructors.length  + 1];
		System.arraycopy(instructors, 0, newInstructors, 0, instructors.length);
		instructors = newInstructors;
	}

	public static boolean searchId(int id, Instructor[] instructors)
	{
		try {
			int location = -1;
			for(int i = 0; i < instructors.length; i++)
				if(id == instructors[i].getId())
				{
					location = i;
					break;
				}

			if(location > -1)
			{
				return true;
			}
		}
		catch(NullPointerException ex)
		{
			return false;
		}
		return false;
	}

	public static void searchInstructorById(int id, Instructor[] instructors)
	{
		try {
			int location = -1;
			for(int i = 0; i < instructors.length; i++)
				if(id == instructors[i].getId())
				{
					location = i;
					break;
				}

			if(location > -1)
			{
				System.out.println("\n" + instructors[location].getName() + " " + instructors[location].getDepartment().getName() + " " + instructors[location].getDepartment().getLocation() + "\n");
			}
		}
		catch(NullPointerException ex)
		{
			System.out.println("\nThe ID doesnot appear in the file.\n");
		}
	}

	public static Department searchDepartmentByName(String name, Department[] departments)
	{
		for(int i = 0; i < departments.length; i++)
		{
			if(name.equals(departments[i].getName()))
				return departments[i];
		}
		return null;
	}

	public static boolean searchDepartment(String name, Department[] departments)
	{
		for(int i = 0; i < departments.length; i++)
		{
			if(name.equals(departments[i].getName()))
				return true;
		}
		return false;
	}
}
