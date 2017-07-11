import java.io.File;
import java.sql.*;

/**
 * Created by Adi on 08-Jul-17.
 */
public class DBHandler {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://localhost/t";
    String user = "";
    String password = "";



    public File getPhotoFromDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM posts ORDER BY id DESC LIMIT 1;");

            while(rs.next())
            {
                System.out.println(rs.getString("Colomn_Name"));//or getString(1) for coloumn 1 etc
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }


        return new File("fake file");
    }
}
