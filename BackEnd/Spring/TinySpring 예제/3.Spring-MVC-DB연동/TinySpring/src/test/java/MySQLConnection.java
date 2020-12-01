import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml"})
public class MySQLConnection {

    @Inject
    private DataSource dataSource;

    @Test
    public void testConnection() throws Exception {
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
