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
    private String insertA = "INSERT INTO caixa(cai_dataabertura, cai_periodoabertura, cai_periodofechamento, cai_valorInicial, pes_codigo, cai_datafechamento, cai_valorFinal,cai_motEntrada, cai_motSaida) " +
                             "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
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
                ps.setString(++cont, obj.getMotEntrada());
                ps.setString(++cont, obj.getMotSaida());
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
                    c.setMotEntrada(rs.getString("cai_motEntrada"));
                    c.setMotSaida(rs.getString("cai_motSaida"));
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
    
    public double valorReceber(Caixa obj, Connection con) throws DAOException{
        String sql = "select sum(mov_valor) from movimentacao_caixa where cai_codigo = ? and mov_descricao like 'Contas a Receber'";
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            
            try{
                ps = con.prepareStatement(sql);
                ps.setInt(++cont, obj.getCodigo());
                rs = ps.executeQuery();
                if(rs.next()){
                    double total = rs.getDouble("sum(mov_valor)");
                    return total;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return 0.0;
    }
    
    public double valorPagar(Caixa obj, Connection con) throws DAOException{
        String sql = "select sum(mov_valor) from movimentacao_caixa where cai_codigo = ? and mov_descricao not like 'Contas a Receber'";
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            
            try{
                ps = con.prepareStatement(sql);
                ps.setInt(++cont, obj.getCodigo());
                rs = ps.executeQuery();
                if(rs.next()){
                    double total = rs.getDouble("sum(mov_valor)");
                    return total;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return 0.0;
    }
    
    public double ultimoCaixa(Caixa obj, Connection con) throws DAOException{
        String sql = "select cai_valorFinal from caixa where cai_codigo = (select max(cai_codigo) from caixa)";
        if(con!=null){
            PreparedStatement ps = null;
            ResultSet rs = null;
            try{
                ps=con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    return rs.getDouble("cai_valorFinal");
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return -1;
    }
}
