package DAO;

import entidade.ContasReceber;
import entidade.Venda;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContasReceberDAO implements GenericDAO<ContasReceber>{
    
    private String insert = "insert into contas_receber(conr_dtvencto, conr_valor, conr_vpago, conr_dtpgto, ven_codigo) values(?,?,?,?,?)";
    private String update = "update contas_receber set conr_dtvencto = ?, conr_valor = ?, conr_vpago = ?, conr_dtpgto = ?, ven_codigo = ? where conr_codigo = ?";
    private String delete = "delete from contas_receber where conr_codigo = ?";
    private String select = "select * from contas_receber";

    @Override
    public int insert(ContasReceber obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            int chave = -1;
            try{
                ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDate(++cont, new java.sql.Date(obj.getDtVencimento().getTime()));
                ps.setDouble(++cont, obj.getValor());
                ps.setDouble(++cont, obj.getValorPago());
                if(obj.getDtPagto()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtPagto().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setInt(++cont, obj.getVenda().getCodigo());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    chave = rs.getInt(1);
                }
                return chave;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(ContasReceber obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(update);
                ps.setDate(++cont, new java.sql.Date(obj.getDtVencimento().getTime()));
                ps.setDouble(++cont, obj.getValor());
                ps.setDouble(++cont, obj.getValorPago());
                if(obj.getDtPagto()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtPagto().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setInt(++cont, obj.getVenda().getCodigo());
                ps.setInt(++cont, obj.getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int delete(ContasReceber obj, Connection con) throws DAOException {
         if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(delete);
                ps.setInt(++cont, obj.getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public ContasReceber select(ContasReceber obj, Connection con) throws DAOException {
         if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where conr_codigo = ?";
                ultimo = true;
            }
            if(obj.getDtPagto()!=null){
                if(ultimo)
                    select+=" and conr_dtpgto = ?";
                else{
                    select+=" where conr_dtpgto = ?";
                    ultimo = true;
                }
            }
            if(obj.getDtVencimento()!=null){
                if(ultimo)
                    select+=" and conr_dtvencto = ?";
                else{
                    select+=" where conr_dtvencto = ?";
                    ultimo = true;
                }
            }
            if(obj.getVenda()!=null && obj.getVenda().getCodigo()!=null && obj.getVenda().getCodigo()!=0){
                if(ultimo)
                    select+=" and ven_codigo = ?";
                else{
                    select+=" where ven_codigo = ?";
                }
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj.getDtPagto()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtPagto().getTime()));
                if(obj.getDtVencimento()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtVencimento().getTime()));
                if(obj.getVenda()!=null && obj.getVenda().getCodigo()!=null && obj.getVenda().getCodigo()!=0)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtPagto().getTime()));
                rs = ps.executeQuery();
                
                if(rs.next()){
                    ContasReceber cr = new ContasReceber();
                    Venda v = new Venda();
                    cr.setCodigo(rs.getInt("conr_codigo"));
                    cr.setValor(rs.getDouble("conr_valor"));
                    cr.setValorPago(rs.getDouble("conr_vpago"));
                    cr.setDtVencimento(rs.getDate("conr_dtvncto"));                    
                    cr.setDtPagto(rs.getDate("conr_dtpgto"));
                    v.setCodigo(rs.getInt("ven_codigo"));
                    cr.setVenda(v.select(con));
                    return cr;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                 throw new DAOException(ex.getMessage());
             }
        }else{
            throw new DAOException("Erro na conexão!");
        }
         return null;
    }
    
    public List<ContasReceber> lista(ContasReceber obj, Connection con) throws DAOException {
        List<ContasReceber> lista = new ArrayList<>();
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where conr_codigo = ?";
                ultimo = true;
            }
            if(obj.getDtPagto()!=null){
                if(ultimo)
                    select+=" and conr_dtpgto = ?";
                else{
                    select+=" where conr_dtpgto = ?";
                    ultimo = true;
                }
            }
            if(obj.getDtVencimento()!=null){
                if(ultimo)
                    select+=" and conr_dtvencto = ?";
                else{
                    select+=" where conr_dtvencto = ?";
                    ultimo = true;
                }
            }
            if(obj.getVenda()!=null && obj.getVenda().getCodigo()!=null && obj.getVenda().getCodigo()!=0){
                if(ultimo)
                    select+=" and ven_codigo = ?";
                else{
                    select+=" where ven_codigo = ?";
                }
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj.getDtPagto()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtPagto().getTime()));
                if(obj.getDtVencimento()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtVencimento().getTime()));
                if(obj.getVenda()!=null && obj.getVenda().getCodigo()!=null && obj.getVenda().getCodigo()!=0)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtPagto().getTime()));
                rs = ps.executeQuery();
                
                while(rs.next()){
                    ContasReceber cr = new ContasReceber();
                    Venda v = new Venda();
                    cr.setCodigo(rs.getInt("conr_codigo"));
                    cr.setValor(rs.getDouble("conr_valor"));
                    cr.setValorPago(rs.getDouble("conr_vpago"));
                    cr.setDtVencimento(rs.getDate("conr_dtvncto"));                    
                    cr.setDtPagto(rs.getDate("conr_dtpgto"));
                    v.setCodigo(rs.getInt("ven_codigo"));
                    cr.setVenda(v.select(con));
                    lista.add(cr);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                 throw new DAOException(ex.getMessage());
             }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
}
