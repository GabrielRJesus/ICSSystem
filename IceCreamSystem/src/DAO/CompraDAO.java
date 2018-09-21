package DAO;

import entidade.Compra;
import entidade.Fornecedor;
import entidade.ItensCompra;
import entidade.Produto;
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

public class CompraDAO implements GenericDAO<Compra>{
    
    private String insert = "insert into compra(com_data, com_total, for_codigo) values(?,?,?)";
    private String delete = "delete from compra where com_codigo = ?";
    private String select = "select * from compra";
    
    private String insertic = "insert into itens_compra(com_codigo, prod_codigo, itc_qtde, itc_valor) values(?,?,?,?)";
    private String deleteic = "delete into itens_compra where com_codigo = ?";
    private String selectic = "select * from itens_compra where com_codigo = ?";
    
    @Override
    public int insert(Compra obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            int chave = -1;
            try{
                ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                ps.setDouble(++cont, obj.getValor());
                ps.setInt(++cont, obj.getForn().getCodigo());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    chave = rs.getInt(1);
                }
                if(chave > -1){
                    for(int i =0; i<obj.getProdutosCompra().size(); i++){
                        PreparedStatement ps2 = null;
                        cont = 0;
                        ps2 = con.prepareStatement(insertic);
                        ps2.setInt(++cont, chave);
                        ps2.setInt(++cont, obj.getProdutosCompra().get(i).getP().getCodigo());
                        ps2.setInt(++cont, obj.getProdutosCompra().get(i).getQtd());
                        ps2.setDouble(++cont, obj.getProdutosCompra().get(i).getValor());
                        ps2.executeUpdate();
                    }
                }
                return 1;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(Compra obj, Connection con) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Compra obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null, ps2 = null;
            int cont = 0;
            try{
                ps2 = con.prepareStatement(deleteic);
                ps2.setInt(++cont, obj.getCodigo());
                ps2.executeUpdate();
                
                cont = 0;
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
    public Compra select(Compra obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where com_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getData()!=null){
                if(ultimo)
                    select+=" and com_total = ?";
                else{
                    select+=" where com_total = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getValor()!=0){
                if(ultimo)
                    select+=" and com_valor = ?";
                else{
                    select+=" where com_valor = ?";
                    ultimo = true;
                }
            }
            
            if(obj.getForn()!=null && obj.getForn().getCodigo()!=null && obj.getForn().getCodigo()!=0){
                if(ultimo)
                    select+=" and for_codigo = ?";
                else
                    select+=" where for_codigo = ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj.getData()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                if(obj.getValor()!=0)
                    ps.setDouble(++cont, obj.getValor());
                if(obj.getForn()!=null && obj.getForn().getCodigo()!=null && obj.getForn().getCodigo()!=0)
                    ps.setInt(++cont, obj.getForn().getCodigo());
                rs = ps.executeQuery();
                if(rs.next()){
                    Compra c = new Compra();
                    Fornecedor f = new Fornecedor();
                    c.setCodigo(rs.getInt("com_codigo"));
                    c.setData(rs.getDate("com_data"));
                    c.setValor(rs.getDouble("com_total"));
                    f.setCodigo(rs.getInt("for_codigo"));
                    try {
                        c.setForn(f.select(con));
                    } catch (EntidadeException ex) {
                        throw new DAOException(ex.getMessage());
                    }
                    c.setProdutosCompra(listaItens(c, con));
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
    
    public List<Compra> lista(Compra obj, Connection con) throws DAOException{
        if(con!=null){
            List<Compra> lista = new ArrayList<>();
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where com_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getData()!=null){
                if(ultimo)
                    select+=" and com_total = ?";
                else{
                    select+=" where com_total = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getValor()!=0){
                if(ultimo)
                    select+=" and com_valor = ?";
                else{
                    select+=" where com_valor = ?";
                    ultimo = true;
                }
            }
            
            if(obj.getForn()!=null && obj.getForn().getCodigo()!=null && obj.getForn().getCodigo()!=0){
                if(ultimo)
                    select+=" and for_codigo = ?";
                else
                    select+=" where for_codigo = ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj.getData()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                if(obj.getValor()!=0)
                    ps.setDouble(++cont, obj.getValor());
                if(obj.getForn()!=null && obj.getForn().getCodigo()!=null && obj.getForn().getCodigo()!=0)
                    ps.setInt(++cont, obj.getForn().getCodigo());
                rs = ps.executeQuery();
                while(rs.next()){
                    Compra c = new Compra();
                    Fornecedor f = new Fornecedor();
                    c.setCodigo(rs.getInt("com_codigo"));
                    c.setData(rs.getDate("com_data"));
                    c.setValor(rs.getDouble("com_total"));
                    f.setCodigo(rs.getInt("for_codigo"));
                    try {
                        c.setForn(f.select(con));
                    } catch (EntidadeException ex) {
                        throw new DAOException(ex.getMessage());
                    }
                    c.setProdutosCompra(listaItens(c, con));
                    lista.add(c);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
    public List<ItensCompra> listaItens(Compra obj, Connection con) throws DAOException{
        List<ItensCompra> lista = new ArrayList<>();
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            try{
                ps = con.prepareStatement(selectic);
                ps.setInt(++cont, obj.getCodigo());
                rs = ps.executeQuery();
                while(rs.next()){
                    ItensCompra ic = new ItensCompra();
                    Produto p = new Produto();
                    ic.setQtd(rs.getInt("itc_qtde"));
                    ic.setValor(rs.getDouble("itc_valor"));
                    p.setCodigo(rs.getInt("prod_codigo"));
                    ic.setP(p.select(con));
                    lista.add(ic);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return lista;
    }
}
