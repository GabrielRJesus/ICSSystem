package DAO;

import entidade.LoteProduto;
import entidade.Produto;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoteProdutoDAO implements GenericDAO<LoteProduto>{
    
    private String insert = "insert into lote_produto(ltp_descricao, ltp_numero, ltp_validade, ltp_qtdelote, ltp_qtderemanes, prod_codigo) values(?,?,?,?,?,?)";
    private String update = "update lote_produto set ltp_descricao = ?, ltp_numero = ?, ltp_validade = ?, ltp_qtdelote = ?, ltp_qtderemanes = ?, prod_codigo = ? where ltp_codigo = ?";
    private String delete = "delete from lote_produto where ltp_codigo = ?";
    private String select = "select * from lote_produto";
    private String ctrEstoque = "update lote_produto set = ltp_qtderemanes = ? where ltp_codigo = ?";
    
    @Override
    public int insert(LoteProduto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getDescricao());
                ps.setString(++cont, obj.getNumeroLote());
                ps.setDate(++cont, new java.sql.Date(obj.getValidade().getTime()));
                ps.setInt(++cont, obj.getQtdeCompra());
                ps.setInt(++cont, obj.getQtdRemanescente());
                ps.setInt(++cont, obj.getProd().getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(LoteProduto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getDescricao());
                ps.setString(++cont, obj.getNumeroLote());
                ps.setDate(++cont, new java.sql.Date(obj.getValidade().getTime()));
                ps.setInt(++cont, obj.getQtdeCompra());
                ps.setInt(++cont, obj.getQtdRemanescente());
                ps.setInt(++cont, obj.getProd().getCodigo());
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
    public int delete(LoteProduto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
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
    public LoteProduto select(LoteProduto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where ltp_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and ltp_descricao like ?";
                else{
                    select+=" where ltp_descricao like ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getNumeroLote()!=null && !obj.getNumeroLote().isEmpty()){
                if(ultimo)
                    select+=" and ltp_numero like ?";
                else{
                    select+=" where ltp_numero like ?";
                    ultimo = true;
                }
            }
            
            if(obj.getProd()!=null && obj.getProd().getCodigo()!=null && obj.getProd().getCodigo()!=0){
                if(ultimo)
                    select+=" and prod_codigo = ?";
                else{
                    select+=" where prod_codigo = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getValidade()!=null){
                if(ultimo)
                    select+=" and ltp_validade = ?";
                else{
                    select+=" where ltp_validade = ?";
                    ultimo = true;
                }
            }
                
            if(obj!=null && obj.getQtdeCompra()!=0){
                if(ultimo)
                    select+=" and ltp_qtdelote = ?";
                else{
                    select+=" where tp_qtdelote = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getQtdRemanescente()!=0){
                if(ultimo)
                    select+=" and ltp_qtderemanes = ?";
                else
                    select+=" where tp_qtderemanes = ?";
            }    
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                if(obj!=null && obj.getNumeroLote()!=null && !obj.getNumeroLote().isEmpty())
                    ps.setString(++cont, obj.getNumeroLote());
                if(obj!=null && obj.getValidade()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getValidade().getTime()));
                if(obj!=null && obj.getQtdeCompra()!=0)
                    ps.setInt(++cont, obj.getQtdeCompra());
                if(obj!=null && obj.getQtdRemanescente()!=0)
                    ps.setInt(++cont, obj.getQtdRemanescente());
                if(obj.getProd()!=null && obj.getProd().getCodigo()!=null && obj.getProd().getCodigo()!=0)
                    ps.setInt(++cont, obj.getProd().getCodigo());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    LoteProduto lp = new LoteProduto();
                    Produto p = new Produto();
                    lp.setCodigo(rs.getInt("ltp_codigo"));
                    lp.setDescricao(rs.getString("ltp_descricao"));
                    lp.setNumeroLote(rs.getString("ltp_numero"));
                    lp.setValidade(rs.getDate("ltp_validade"));
                    lp.setQtdeCompra(rs.getInt("ltp_qtdelote"));
                    lp.setQtdRemanescente(rs.getInt("ltp_qtderemanes"));
                    p.setCodigo(rs.getInt("prod_codigo"));
                    lp.setProd(p.select(con));
                    return lp;
                }
                
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                Logger.getLogger(LoteProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public LoteProduto selectT(LoteProduto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        String sql = "select min(ltp_codigo), ltp_validade from lote_produto where prod_codigo = ? and ltp_qtderemanes > 0";
        if(con!=null){
            try{
                ps = con.prepareStatement(select);
                if(obj.getProd()!=null && obj.getProd().getCodigo()!=null && obj.getProd().getCodigo()!=0)
                    ps.setInt(++cont, obj.getProd().getCodigo());
                rs = ps.executeQuery();
                if(rs.next()){
                    LoteProduto lp = new LoteProduto();
                    Produto p = new Produto();
                    lp.setCodigo(rs.getInt("ltp_codigo"));
                    lp.setDescricao(rs.getString("ltp_descricao"));
                    lp.setNumeroLote(rs.getString("ltp_numero"));
                    lp.setValidade(rs.getDate("ltp_validade"));
                    lp.setQtdeCompra(rs.getInt("ltp_qtdelote"));
                    lp.setQtdRemanescente(rs.getInt("ltp_qtderemanes"));
                    p.setCodigo(rs.getInt("prod_codigo"));
                    lp.setProd(p.select(con));
                    return lp;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                Logger.getLogger(LoteProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<LoteProduto> lista(LoteProduto obj, Date inicio, Date fim, Connection con) throws DAOException, EntidadeException {
        List<LoteProduto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where ltp_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and ltp_descricao like ?";
                else{
                    select+=" where ltp_descricao like ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getNumeroLote()!=null && !obj.getNumeroLote().isEmpty()){
                if(ultimo)
                    select+=" and ltp_numero like ?";
                else{
                    select+=" where ltp_numero like ?";
                    ultimo = true;
                }
            }
            
            if(obj.getProd()!=null && obj.getProd().getCodigo()!=null && obj.getProd().getCodigo()!=0){
                if(ultimo)
                    select+=" and prod_codigo = ?";
                else{
                    select+=" where prod_codigo = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && (inicio!=null || fim!=null)){
                if(ultimo){
                    if(inicio!=null && fim==null)
                        select+=" and ltp_validade > ?";
                    if(inicio==null && fim!=null)
                        select+=" and ltp_validade < ?";
                    else
                        select+=" and ltp_validade between ? and ?";
                }
                else{
                    if(inicio!=null && fim==null)
                        select+=" where ltp_validade > ?";
                    if(inicio==null && fim!=null)  
                        select+=" where ltp_validade < ?";
                    else
                        select+=" where ltp_validade between ? and ?";
                    ultimo = true;
                }
            }
                
            if(obj!=null && obj.getQtdeCompra()!=0){
                if(ultimo)
                    select+=" and ltp_qtdelote = ?";
                else{
                    select+=" where tp_qtdelote = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getQtdRemanescente()!=0){
                if(ultimo)
                    select+=" and ltp_qtderemanes = ?";
                else
                    select+=" where tp_qtderemanes = ?";
            }    
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                if(obj!=null && obj.getNumeroLote()!=null && !obj.getNumeroLote().isEmpty())
                    ps.setString(++cont, obj.getNumeroLote());
                if(obj!=null && (inicio!=null || fim!=null)){
                    if(inicio!=null && fim==null)
                        ps.setDate(++cont, new java.sql.Date(inicio.getTime()));
                    if(inicio==null && fim!=null)
                        ps.setDate(++cont, new java.sql.Date(fim.getTime()));
                    else{
                        ps.setDate(++cont, new java.sql.Date(inicio.getTime()));
                        ps.setDate(++cont, new java.sql.Date(fim.getTime()));
                    }
                }
                if(obj!=null && obj.getQtdeCompra()!=0)
                    ps.setInt(++cont, obj.getQtdeCompra());
                if(obj!=null && obj.getQtdRemanescente()!=0)
                    ps.setInt(++cont, obj.getQtdRemanescente());
                if(obj.getProd()!=null && obj.getProd().getCodigo()!=null && obj.getProd().getCodigo()!=0)
                    ps.setInt(++cont, obj.getProd().getCodigo());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    LoteProduto lp = new LoteProduto();
                    Produto p = new Produto();
                    lp.setCodigo(rs.getInt("ltp_codigo"));
                    lp.setDescricao(rs.getString("ltp_descricao"));
                    lp.setNumeroLote(rs.getString("ltp_numero"));
                    lp.setValidade(rs.getDate("ltp_validade"));
                    lp.setQtdeCompra(rs.getInt("ltp_qtdelote"));
                    lp.setQtdRemanescente(rs.getInt("ltp_qtderemanes"));
                    p.setCodigo(rs.getInt("prod_codigo"));
                    lp.setProd(p.select(con));
                    lista.add(lp);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
        
    public List<LoteProduto> listaLotePorProduto(LoteProduto obj, Connection con) throws DAOException, EntidadeException {
        List<LoteProduto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where ltp_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and ltp_descricao like ?";
                else{
                    select+=" where ltp_descricao like ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getNumeroLote()!=null && !obj.getNumeroLote().isEmpty()){
                if(ultimo)
                    select+=" and ltp_numero like ?";
                else{
                    select+=" where ltp_numero like ?";
                    ultimo = true;
                }
            }
                
            if(obj!=null && obj.getQtdeCompra()!=0){
                if(ultimo)
                    select+=" and ltp_qtdelote = ?";
                else{
                    select+=" where ltp_qtdelote = ?";
                    ultimo = true;
                }
            }
            
            if(obj.getProd()!=null && obj.getProd().getCodigo()!=null && obj.getProd().getCodigo()!=0){
                if(ultimo)
                    select+=" and prod_codigo = ?";
                else{
                    select+=" where prod_codigo = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getQtdRemanescente()!=0){
                if(ultimo)
                    select+=" and ltp_qtderemanes = ?";
                else
                    select+=" where ltp_qtderemanes = ?";
            }    
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                if(obj!=null && obj.getNumeroLote()!=null && !obj.getNumeroLote().isEmpty())
                    ps.setString(++cont, obj.getNumeroLote());
                if(obj!=null && obj.getQtdeCompra()!=0)
                    ps.setInt(++cont, obj.getQtdeCompra());
                if(obj!=null && obj.getQtdRemanescente()!=0)
                    ps.setInt(++cont, obj.getQtdRemanescente());
                if(obj.getProd()!=null && obj.getProd().getCodigo()!=null && obj.getProd().getCodigo()!=0)
                    ps.setInt(++cont, obj.getProd().getCodigo());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    LoteProduto lp = new LoteProduto();
                    Produto p = new Produto();
                    lp.setCodigo(rs.getInt("ltp_codigo"));
                    lp.setDescricao(rs.getString("ltp_descricao"));
                    lp.setNumeroLote(rs.getString("ltp_numero"));
                    lp.setValidade(rs.getDate("ltp_validade"));
                    lp.setQtdeCompra(rs.getInt("ltp_qtdelote"));
                    lp.setQtdRemanescente(rs.getInt("ltp_qtderemanes"));
                    p.setCodigo(rs.getInt("prod_codigo"));
                    lp.setProd(p.select(con));
                    lista.add(lp);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
     public int ctrEstoque(LoteProduto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(ctrEstoque);
                ps.setInt(++cont, obj.getQtdRemanescente());
                ps.setInt(++cont, obj.getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
