package DAO;

import entidade.Caixa;
import entidade.Funcionario;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaixaDAO {
    private String insertA = "INSERT INTO caixa(cai_dataabertura, cai_periodoabertura, cai_periodofechamento, cai_valorInicial, pes_codigo, cai_datafechamento, cai_valorFinal) " +
                             "VALUES(?, ?, ?, ?, ?, ?, ?)";
    
    private String update = "UPDATE caixa SET cai_periodofechamento = ?, cai_valorFinal = ?, pes_codigo = ?, cai_datafechamento = ? WHERE cai_codigo = ?";
    private String selectA = "SELECT * from caixa where cai_datafechamento is null";
    
    public int abrirCaixa(Caixa obj, Connection con) throws DAOException{
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(insertA);
                ps.setDate(++cont, new java.sql.Date(obj.getAbertura().getTime()));
                ps.setString(++cont, obj.getPeriodoA());
                ps.setString(++cont, obj.getPeriodoB());
                ps.setDouble(++cont, obj.getTroco());
                ps.setInt(++cont, obj.getFunc().getCodigo());
                ps.setNull(++cont, java.sql.Types.DATE);
                ps.setNull(++cont, java.sql.Types.INTEGER);
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
            
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
    public int fecharCaixa(Caixa obj, Connection con) throws DAOException{
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getPeriodoB());
                ps.setDouble(++cont, obj.getTrocoFinal());
                ps.setInt(++cont, obj.getFunc().getCodigo());
                ps.setDate(++cont, new java.sql.Date(obj.getFechamento().getTime()));
                ps.setInt(++cont, obj.getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
            
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
    public Caixa retornaCaixa(Caixa obj, Connection con) throws DAOException, EntidadeException{
        if(con!=null){
            PreparedStatement ps = null;
            ResultSet rs = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(selectA);
                rs = ps.executeQuery();
                if(rs.next()){
                    Caixa c = new Caixa();
                    Funcionario f = new Funcionario();
                    c.setCodigo(rs.getInt("cai_codigo"));
                    c.setAbertura(rs.getDate("cai_dataabertura"));
                    c.setPeriodoA(rs.getString("cai_periodoabertura"));
                    c.setPeriodoB(rs.getString("cai_periodofechamento"));
                    c.setTroco(rs.getDouble("cai_valorInicial"));
                    f.setCodigo(rs.getInt("pes_codigo"));
                    c.setFunc(f.select(con));
                    c.setFechamento(rs.getDate("cai_datafechamento"));
                    c.setTrocoFinal(rs.getDouble("cai_valorFinal"));
                    return c;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
            
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
}
