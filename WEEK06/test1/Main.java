import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
		String name = "";
		String readFile = "";
		System.out.println("찾으실 학생의 이름 : ");

		name = scanner.nextLine();
      
		Maker maker = new Maker();
        maker.Maker(name);
		maker.searchStudent();

    }
}