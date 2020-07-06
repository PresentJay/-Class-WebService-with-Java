
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Maker {
    ArrayList<Person> arr;
	Person person;
	public Maker()
	{
		arr = new ArrayList<Person>();
		person = new Person();
	}
	
	public void Maker(String name)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("C://Users//Jung Se In//Desktop//Person.txt"));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] words = line.split("/");
				String[] dataName = words[1].split("="); 
				
				if(dataName[1].equals(name))
				{
					String[] hakbun = words[0].split("=");
					String[] tel = words[2].split("=");
					String[] jumin = words[3].split("=");
					String[] address = words[4].split("=");
					
					person.setHakbun(hakbun[1]);
					person.setName(dataName[1]);
					person.setTel(tel[1]);
					person.setJumin(jumin[1]);
					person.setAddress(address[1]);
					arr.add(person);
					System.out.print(person.getName());
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void searchStudent()
	{
        arr.get(0).getJumin();
		String[] number = arr.get(0).getJumin().split("-");
        int agestr = age(number[0]);
		String gender = sex(number[1]);
        System.out.print("이름 : " + arr.get(0).getName() + ", 주민번호 : " + arr.get(0).getJumin() + ", 성별 : " + gender + ", 나이 :" + agestr);
	}
	public int age(String first)
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String birth = first.substring(0,2);
		int num = Integer.parseInt(birth);
		int age = (119-num + 1);
		if(age >= 100)
		{
			age = age - 100;
		}
		
		return age;
	}
	
	public String sex(String second)
	{	
		String str = second.substring(0,1);
		int num = Integer.parseInt(str);
		if(num == 1 || num == 3)
		{
			return "남자";
		}
		else	
		{
			return "여자";
		}
	}
}