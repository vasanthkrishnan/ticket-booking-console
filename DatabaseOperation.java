import java.sql.*;
import java.util.*;

public class DatabaseOperation {
    static final String DB_URL = "jdbc:mysql://localhost/sys";
    static final String USER = "root";
    static final String PASS = "REKHAjoy_2004";

    public Connection connectToDatabase()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public int executeUpdate(String sql, Object[] values)
    {
        int rowAffected = 0;
        try(Connection conn = connectToDatabase();
        PreparedStatement ps = conn.prepareStatement(sql))
        {
            for(int i = 0; i < values.length; i++)
            {
                ps.setObject(i + 1, values[i]);
            }
            rowAffected = ps.executeUpdate();
            return rowAffected;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Map<String, Object>> getRecords(String sql)
    {
            List<Map<String, Object>> records = new ArrayList<>();
            try(Connection conn = connectToDatabase();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery())
            {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for(int i = 0; i < columnCount; i++)
                    {
                        row.put(rsmd.getColumnName(i), rs.getObject(i));
                    }
                    records.add(row);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            return records;
    }
}
