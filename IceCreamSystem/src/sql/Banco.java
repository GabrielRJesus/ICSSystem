package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Banco {
    
    private Connection conexao;
    private static Banco instancia;
    
    private Banco(){
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sql/config");

            Class.forName(bundle.getString("driver"));
            this.conexao = DriverManager.getConnection(
                    bundle.getString("url"),
                    bundle.getString("user"),
                    bundle.getString("pass")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Banco getInstancia(){
        if(instancia==null)
            instancia = new Banco();
        return instancia;
    }
    
    public Connection getConexao(){
        return conexao;
    }

    public static final void fechaTudo(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static final void fechaTudo(Statement st, Connection conn) {
        fechaTudo(null, st, conn);
    }

    public static final void fechaTudo(Connection conn) {
        fechaTudo(null, null, conn);
    }

}
