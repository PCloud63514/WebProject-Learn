import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml"})
public class MySQLConnection {

    @Inject
    private DataSource dataSource;

    @Test
    public void testGetUserList() throws Exception {
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        String query = "select * from User";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {
            String id = resultSet.getString("name");
            String passwd = resultSet.getString("passwd");
            String name = resultSet.getString("name");
            int genderId = resultSet.getInt("genderId");

            System.out.println("=========================================");
            System.out.println("id:" + id);
            System.out.println("passwd:" + passwd);
            System.out.println("name:" + name);
            System.out.println("genderId:" + genderId);
        }

        resultSet.close();
        statement.close();
        conn.close();
    }
}
