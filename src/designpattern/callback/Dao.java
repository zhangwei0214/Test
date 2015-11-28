package designpattern.callback;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

/**
 * 用这种回调模式方便的把openConnection()和closeConnection()做了切片，从代码中剥离出来，使代码复用性更高，也更简洁 
 * @author Administrator
 * @see http://cuishen.iteye.com/blog/438428
 */
public class Dao {  
    private interface Callback {  
        Object doIt(Connection conn) throws SQLException;  
    }  
    private Object execute(Callback callback) throws SQLException {  
        Connection conn = openConnection(); // 开启数据库连接  
        try { return callback.doIt(conn); } // 执行具体操作并返回操作结果  
        finally { closeConnection(conn); } // 关闭数据库连接  
    }  
      
    public Object sqlQuery(final String sql) throws SQLException {  
        return execute(  
            new Callback() {  
                public Object doIt(Connection conn) throws SQLException {  
                    return conn.createStatement().executeQuery(sql);  
                }  
            }  
        );  
    }  
      
    public Connection openConnection() throws SQLException {  
        return DriverManager.getConnection("", null);  
    }  
    public void closeConnection(Connection conn) throws SQLException {  
        if(conn != null && !conn.isClosed()) {  
            conn.close();  
        }  
    }  
}
