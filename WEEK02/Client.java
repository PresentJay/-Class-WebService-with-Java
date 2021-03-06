package REPORTS.WEEK02;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Client {

    // 나이를 계산하기 위한 현재 시간을 define한 변수
    public static final int currentYear = 2020;
    // Server로부터 받은 버퍼데이터를 구조화하여 저장해두는 변수
    public static ArrayList<String[]> core = new ArrayList<String[]>();

    public static void main(String args[]) throws Exception {
        // 소켓 생성
        Socket socket = new Socket("localhost", 1000);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Server로부터 데이터를 받는 구간
            String str;
            while ((str = in.readLine()) != null) {
                // readLine으로 한 줄씩 데이터를 받았을 때
                // 해당 데이터가 "이름-주민번호앞/주민번호뒤"의 구성이어서
                // -를 /로 치환한 뒤, /문자를 기준으로 split하여 배열을 만들었습니다.
                String[] tmp = str.replace("-", "/").split("/");

                // 그리고 배열의 첫 번째 원소는 이름(변경필요x), 두 번째 원소는 나이
                tmp[1] = extractAge(tmp[1]);
                // 세 번째 원소는 성별이 파싱되도록 구성했습니다.
                // 각각 기능은 함수화하여 구현했습니다.
                tmp[2] = extractSex(tmp[2]);

                // 각 원소에 데이터 파싱이 끝나면, core에 데이터를 추가합니다.
                core.add(tmp);
            }

            // 매개변수가 없는 해당 함수는, core 전체를 출력합니다.
            printByMatching();

            // 이후 이름 매칭을 통해 출력하는 작업을 원하는 만큼 반복합니다.
            while (infiniteRequest())

                socket.close();
        } catch (IOException e) {
            System.out.println("Client : Input/Output error.");
            e.getStackTrace();
        }
    }

    // 정확한 나이 계산을 위해, 20세기 2자리 수와 21세기 2자리 수를 구별했습니다.
    public static int extractYear(int i) {
        return i >= 30 ? i + 1900 : i + 2000;
    }

    // String[]배열 데이터이기 때문에, 연도를 계산한 후, 다시 toString을 리턴하는 함수입니다.
    public static String extractAge(String s) {
        return Integer.toString(currentYear - extractYear(Integer.parseInt(s.substring(0, 2))));
    }

    // 주민번호 뒷자리를 인자로 받아, 홀수의 경우 남자, 짝수의 경우 여자로 출력합니다.
    public static String extractSex(String s) {
        return Integer.parseInt(s) % 2 == 1 ? "male" : "female";
    }

    // 매개변수가 있는(키값을 받은) 출력함수로, core에서 매칭값을 찾으면 출력, 아닌 경우 true를 반환합니다.
    public static boolean printByMatching(String s) {
        for (String[] i : core)
            if (i[0].equals(s)) {
                System.out.println(" *탐색 결과 : " + i[0] + ", " + i[1] + ", " + i[2] + "\n");
                return false;
            }
        return true;
    }

    // 매개변수가 없는 출력함수로, core 전체를 출력합니다.
    public static void printByMatching() {
        for (String[] i : core)
            System.out.println(i[0] + ", " + i[1] + ", " + i[2]);
    }

    // 사용자가 원하는만큼 반복하여 printByMatching을 시도해볼 수 있는 함수로
    // exit을 입력하지 않는다면 계속 반복할 수 있습니다.
    // 그 외를 입력한 경우, 매칭 결과를 확인할 수 있습니다.
    public static boolean infiniteRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.print(" *출력을 원하는 이름을 입력하세요\n(종료를 원할 시 exit입력)\n\n >> ");
        String message = sc.nextLine();
        sc.close();
        if (message.equals("exit")) {
            return false;
        } else {
            if (printByMatching(message))
                System.out.println(message + "의 이름과 맞는 정보가 없습니다.\n\n * * *\n");
            return true;
        }

    }
}