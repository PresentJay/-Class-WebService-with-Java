import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {
        try {

            String url = "jdbc:mysql://localhost/presentjdb?user=presentj&password=1234&serverTimezone=UTC&useSSL=false";
            Connection connection = DriverManager.getConnection(url);

            Statement statement = connection.createStatement();

            // statement.execute("insert into address value('정현재','울산광역시')");
            // statement.execute("update address set address='부산광역시' where name='정현재'");
            // statement.execute("delete from address where name='이성계'");

            String query = "select * from address";
            ResultSet result = statement.executeQuery(query);

            // System.out.println(statement.getUpdateCount());
            // 쿼리 적용여부 확인

            while (result.next()) {
                System.out.println(result.getString("name") + ":" + result.getString("address"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}