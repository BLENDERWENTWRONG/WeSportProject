package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    static String url="jdbc:mysql://localhost:3306/esprit";
    static String user="root";
    static String pwd="";
    private static DataSource data;
    private Connection con ;
    private DataSource(){
        try {
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println(con);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public Connection getCon(){
      return con;
    }
    public static DataSource getInstance(){
        if (data == null)
        {
            data = new DataSource();
        }
        return data;
    }

}