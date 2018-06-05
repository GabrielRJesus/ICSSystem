package DAO;

import entidade.CategoriaProduto;
import entidade.LoteProduto;
import entidade.Marca;
import entidade.Produto;
import entidade.UnidadeMedida;
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

public class ProdutoDAO implements GenericDAO<Produto>{
    
    private String insert = "insert into produto(tpp_codigo, ltp_codigo, um_codigo, mar_codigo, prod_precobase, prod_margemlucro, prod_preco, prod_qtdemin, prod_estoque)"
            + " values(?,?,?,?,?,?,?,?,?)";
    private String update = "update produto set tpp_codigo = ?, ltp_codigo = ?, um_codigo = ?, mar_codigo = ?, prod_precobae = ?, prod_margemlucro = ?,"
            + " prod_preco = ?, prod_qtdemin = ?, prod_estoque = ? where prod_codigo = ?";
    private String delete = "delete from produto where prod_codigo = ?";
    private String select = "select * from produto";

    @Override
    public int insert(Produto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setInt(++cont, obj.getCprod().getCodigo());
                ps.setInt(++cont, obj.getLprod().getCodigo());
                ps.setInt(++cont, obj.getUnimed().getCodigo());
                ps.setInt(++cont, obj.getMarca().getCodigo());
                ps.setDouble(++cont, obj.getPrecoBase());
                ps.setDouble(++cont, obj.getMargemLucro());
                ps.setDouble(++cont, obj.getPreco());
                ps.setInt(++cont, obj.getQtdeMin());
                ps.setInt(++cont, obj.getQtdeEstoque());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(Produto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setInt(++cont, obj.getCprod().getCodigo());
                ps.setInt(++cont, obj.getLprod().getCodigo());
                ps.setInt(++cont, obj.getUnimed().getCodigo());
                ps.setInt(++cont, obj.getMarca().getCodigo());
                ps.setDouble(++cont, obj.getPrecoBase());
                ps.setDouble(++cont, obj.getMargemLucro());
                ps.setDouble(++cont, obj.getPreco());
                ps.setInt(++cont, obj.getQtdeMin());
                ps.setInt(++cont, obj.getQtdeEstoque());
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
    public int delete(Produto obj, Connection con) throws DAOException {
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
    public Produto select(Produto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where prod_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0){
                if(ultimo)
                    select+=" and tpp_codigo = ?";
                else{
                    select+=" where tpp_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getLprod().getCodigo()!=null && obj.getLprod().getCodigo()!=0){
                if(ultimo)
                    select+=" and ltp_codigo = ?";
                else{
                    select+=" where ltp_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getUnimed().getCodigo()!=null && obj.getUnimed().getCodigo()!=0){
                if(ultimo)
                    select+=" and um_codigo = ?";
                else{
                    select+=" where um_codigo = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getMarca().getCodigo()!=null && obj.getMarca().getCodigo()!=0){
                if(ultimo)
                    select+=" and mar_codigo = ?";
                else{
                    select+=" where mar_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getPrecoBase()!=0){
                if(ultimo)
                    select+=" and prod_precobase = ?";
                else{
                    select+=" where prod_precobase = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getMargemLucro()!=0){
                if(ultimo)
                    select+=" and prod_margemlucro = ?";
                else{
                    select+=" where prod_margemlucro = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getPreco()!=0){
                if(ultimo)
                    select+=" and prod_preco = ?";
                else{
                    select+=" where prod_preco = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getQtdeMin()!=0){
                if(ultimo)
                    select+=" and prod_qtdemin = ?";
                else{
                    select+=" where prod_qtdemin = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getQtdeEstoque()!=0){
                if(ultimo)
                    select+=" and prod_estoque = ?";
                else
                    select+=" where prod_estoque = ?";
            }
            
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCprod().getCodigo());
                if(obj!=null && obj.getLprod().getCodigo()!=null && obj.getLprod().getCodigo()!=0)
                    ps.setInt(++cont, obj.getLprod().getCodigo());
                if(obj!=null && obj.getUnimed().getCodigo()!=null && obj.getUnimed().getCodigo()!=0)
                    ps.setInt(++cont, obj.getUnimed().getCodigo());
                if(obj!=null && obj.getMarca().getCodigo()!=null && obj.getMarca().getCodigo()!=0)
                    ps.setInt(++cont, obj.getMarca().getCodigo());
                if(obj!=null && obj.getPrecoBase()!=0)
                    ps.setDouble(++cont, obj.getPrecoBase());
                if(obj!=null && obj.getMargemLucro()!=0)
                    ps.setDouble(++cont, obj.getMargemLucro());
                if(obj!=null && obj.getPreco()!=0)
                    ps.setDouble(++cont, obj.getPreco());
                if(obj!=null && obj.getQtdeMin()!=0)
                    ps.setInt(++cont, obj.getQtdeMin());
                if(obj!=null && obj.getQtdeEstoque()!=0)
                    ps.setInt(++cont, obj.getQtdeEstoque());
                rs = ps.executeQuery();
                if(rs.next()){
                    Produto p = new Produto();
                    CategoriaProduto cat = new CategoriaProduto();
                    LoteProduto ltp = new LoteProduto();
                    UnidadeMedida um = new UnidadeMedida();
                    Marca m = new Marca();
                    p.setCodigo(rs.getInt("prod_codigo"));
                    cat.setCodigo(rs.getInt("tpp_codigo"));
                    p.setCprod(cat.select(con));
                    ltp.setCodigo(rs.getInt("ltp_codigo"));
                    p.setLprod(ltp.select(con));
                    um.setCodigo(rs.getInt("um_codigo"));
                    p.setUnimed(um.select(con));
                    m.setCodigo(rs.getInt("mar_codigo"));
                    p.setMarca(m.select(con));
                    p.setPrecoBase(rs.getDouble("prod_precobase"));
                    p.setMargemLucro(rs.getDouble("prod_margemlucro"));
                    p.setPreco(rs.getDouble("prod_preco"));
                    p.setQtdeMin(rs.getInt("prod_qtdemin"));
                    p.setQtdeEstoque(rs.getInt("prod_estoque"));
                    return p;
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
    
    public List<Produto> lista(Produto obj, Connection con) throws DAOException{
        List<Produto> lista = new ArrayList<>();PreparedStatement ps = null;
        ResultSet rs = null;
        boolean ultimo = false;
        int cont = 0;
        if(con!=null){
            
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where prod_codigo = ?";
                ultimo = true;
            }
            if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0){
                if(ultimo)
                    select+=" and tpp_codigo = ?";
                else{
                    select+=" where tpp_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getLprod().getCodigo()!=null && obj.getLprod().getCodigo()!=0){
                if(ultimo)
                    select+=" and ltp_codigo = ?";
                else{
                    select+=" where ltp_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getUnimed().getCodigo()!=null && obj.getUnimed().getCodigo()!=0){
                if(ultimo)
                    select+=" and um_codigo = ?";
                else{
                    select+=" where um_codigo = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getMarca().getCodigo()!=null && obj.getMarca().getCodigo()!=0){
                if(ultimo)
                    select+=" and mar_codigo = ?";
                else{
                    select+=" where mar_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getPrecoBase()!=0){
                if(ultimo)
                    select+=" and prod_precobase = ?";
                else{
                    select+=" where prod_precobase = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getMargemLucro()!=0){
                if(ultimo)
                    select+=" and prod_margemlucro = ?";
                else{
                    select+=" where prod_margemlucro = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getPreco()!=0){
                if(ultimo)
                    select+=" and prod_preco = ?";
                else{
                    select+=" where prod_preco = ?";
                    ultimo = true;
                }
            }
            if(obj!=null && obj.getQtdeMin()!=0){
                if(ultimo)
                    select+=" and prod_qtdemin = ?";
                else{
                    select+=" where prod_qtdemin = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getQtdeEstoque()!=0){
                if(ultimo)
                    select+=" and prod_estoque = ?";
                else
                    select+=" where prod_estoque = ?";
            }
            
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCprod().getCodigo());
                if(obj!=null && obj.getLprod().getCodigo()!=null && obj.getLprod().getCodigo()!=0)
                    ps.setInt(++cont, obj.getLprod().getCodigo());
                if(obj!=null && obj.getUnimed().getCodigo()!=null && obj.getUnimed().getCodigo()!=0)
                    ps.setInt(++cont, obj.getUnimed().getCodigo());
                if(obj!=null && obj.getMarca().getCodigo()!=null && obj.getMarca().getCodigo()!=0)
                    ps.setInt(++cont, obj.getMarca().getCodigo());
                if(obj!=null && obj.getPrecoBase()!=0)
                    ps.setDouble(++cont, obj.getPrecoBase());
                if(obj!=null && obj.getMargemLucro()!=0)
                    ps.setDouble(++cont, obj.getMargemLucro());
                if(obj!=null && obj.getPreco()!=0)
                    ps.setDouble(++cont, obj.getPreco());
                if(obj!=null && obj.getQtdeMin()!=0)
                    ps.setInt(++cont, obj.getQtdeMin());
                if(obj!=null && obj.getQtdeEstoque()!=0)
                    ps.setInt(++cont, obj.getQtdeEstoque());
                rs = ps.executeQuery();
                while(rs.next()){
                    Produto p = new Produto();
                    CategoriaProduto cat = new CategoriaProduto();
                    LoteProduto ltp = new LoteProduto();
                    UnidadeMedida um = new UnidadeMedida();
                    Marca m = new Marca();
                    p.setCodigo(rs.getInt("prod_codigo"));
                    cat.setCodigo(rs.getInt("tpp_codigo"));
                    p.setCprod(cat.select(con));
                    ltp.setCodigo(rs.getInt("ltp_codigo"));
                    p.setLprod(ltp.select(con));
                    um.setCodigo(rs.getInt("um_codigo"));
                    p.setUnimed(um.select(con));
                    m.setCodigo(rs.getInt("mar_codigo"));
                    p.setMarca(m.select(con));
                    p.setPrecoBase(rs.getDouble("prod_precobase"));
                    p.setMargemLucro(rs.getDouble("prod_margemlucro"));
                    p.setPreco(rs.getDouble("prod_preco"));
                    p.setQtdeMin(rs.getInt("prod_qtdemin"));
                    p.setQtdeEstoque(rs.getInt("prod_estoque"));
                    lista.add(p);
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
