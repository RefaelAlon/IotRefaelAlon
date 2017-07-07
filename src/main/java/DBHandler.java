import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Adi on 07-Jul-17.
 */
public class DBHandler extends AbstractVerticle{

    @Override
    public void start() {

        try
        {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");

            String userName = "sa";
            String password = "password";
            String url = "jdbc:microsoft:sqlserver://localhost:1433"+";databaseName=AdventureWorks2008R2";
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement s1 = con.createStatement();
            ResultSet rs = s1.executeQuery("SELECT TOP 1 * FROM HumanResources.Employee");
            String[] result = new String[20];
            if(rs!=null){
                while (rs.next()){
                    for(int i = 0; i <result.length ;i++)
                    {
                        for(int j = 0; j <result.length;j++)
                        {
                            result[j]=rs.getString(i);
                            System.out.println(result[j]);
                        }
                    }
                }
            }

            vertx.eventBus().send(Constants.DB_ANSWER, result);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
