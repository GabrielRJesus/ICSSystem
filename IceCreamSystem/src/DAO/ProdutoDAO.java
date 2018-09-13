package DAO;

import entidade.BaixaManual;
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

public class ProdutoDAO implements GenericDAO<Produto>{
    
    private String insert = "insert into produto(prod_descricao, tpp_codigo, um_codigo, mar_codigo, prod_precobase, prod_margemlucro, prod_preco, prod_qtdemin, prod_estoque, prod_qtdeEmbalagem)"
            + " values(?,?,?,?,?,?,?,?,?,?)";
    private String update = "update produto set prod_descricao = ?, tpp_codigo = ?, um_codigo = ?, mar_codigo = ?, prod_precobase = ?, prod_margemlucro = ?,"
            + " prod_preco = ?, prod_qtdemin = ?, prod_estoque = ?, prod_qtdeEmbalagem = ? where prod_codigo = ?";
    private String ctrEstoque = "update produto set prod_estoque = ? where prod_codigo = ?";
    private String delete = "delete from produto where prod_codigo = ?";
    private String select = "select * from produto";
    
    //Baixa manual no estoque
    
    private String insertBaixa = "insert into baixa_manual(bm_motivo, bm_qtde, prod_codigo, bm_data, fun_codigo) values(?,?,?,?,?)";

    @Override
    public int insert(Produto obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getDescricao());
                ps.setInt(++cont, obj.getCprod().getCodigo());
                ps.setInt(++cont, obj.getUnimed().getCodigo());
                ps.setInt(++cont, obj.getMarca().getCodigo());
                ps.setDouble(++cont, obj.getPrecoBase());
                ps.setDouble(++cont, obj.getMargemLucro());
                ps.setDouble(++cont, obj.getPreco());
                ps.setInt(++cont, obj.getQtdeMin());
                ps.setInt(++cont, obj.getQtdeEstoque());
                ps.setString(++cont, obj.getQtdeEmbalagem());
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
                ps.setString(++cont, obj.getDescricao());
                ps.setInt(++cont, obj.getCprod().getCodigo());
                ps.setInt(++cont, obj.getUnimed().getCodigo());
                ps.setInt(++cont, obj.getMarca().getCodigo());
                ps.setDouble(++cont, obj.getPrecoBase());
                ps.setDouble(++cont, obj.getMargemLucro());
                ps.setDouble(++cont, obj.getPreco());
                ps.setInt(++cont, obj.getQtdeMin());
                ps.setInt(++cont, obj.getQtdeEstoque());
                ps.setString(++cont, obj.getQtdeEmbalagem());
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
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and prod_descricao like ?";
                else{
                    select+=" where prod_descricao like ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0){
                if(ultimo)
                    select+=" and tpp_codigo = ?";
                else{
                    select+=" where tpp_codigo = ?";
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
                else{
                    select+=" where prod_estoque = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getQtdeEmbalagem()!=null && !obj.getQtdeEmbalagem().isEmpty()){
                if(ultimo)
                    select+=" and prod_qtdeEmbalagem = ?";
                else
                    select+=" where prod_qtdeEmbalagem = ?";
            }
            
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCprod().getCodigo());
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
                if(obj!=null && obj.getQtdeEmbalagem()!=null && !obj.getQtdeEmbalagem().isEmpty())
                    ps.setString(++cont, obj.getQtdeEmbalagem());
                rs = ps.executeQuery();
                if(rs.next()){
                    Produto p = new Produto();
                    CategoriaProduto cat = new CategoriaProduto();
                    LoteProduto ltp = new LoteProduto();
                    UnidadeMedida um = new UnidadeMedida();
                    Marca m = new Marca();
                    p.setCodigo(rs.getInt("prod_codigo"));
                    p.setDescricao(rs.getString("prod_descricao"));
                    cat.setCodigo(rs.getInt("tpp_codigo"));
                    p.setCprod(cat.select(con));
                    um.setCodigo(rs.getInt("um_codigo"));
                    p.setUnimed(um.select(con));
                    m.setCodigo(rs.getInt("mar_codigo"));
                    p.setMarca(m.select(con));
                    p.setPrecoBase(rs.getDouble("prod_precobase"));
                    p.setMargemLucro(rs.getDouble("prod_margemlucro"));
                    p.setPreco(rs.getDouble("prod_preco"));
                    p.setQtdeMin(rs.getInt("prod_qtdemin"));
                    p.setQtdeEstoque(rs.getInt("prod_estoque"));
                    p.setQtdeEmbalagem(rs.getString("prod_qtdeEmbalagem"));
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
            
            if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty()){
                if(ultimo)
                    select+=" and prod_descricao like ?";
                else{
                    select+=" where prod_descricao like ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0){
                if(ultimo)
                    select+=" and tpp_codigo = ?";
                else{
                    select+=" where tpp_codigo = ?";
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
                else{
                    select+=" where prod_estoque = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getQtdeEmbalagem()!=null && !obj.getQtdeEmbalagem().isEmpty()){
                if(ultimo)
                    select+=" and prod_qtdeEmbalagem = ?";
                else{
                    select+=" where prod_qtdeEmbalagem = ?";
                }
            }
            
            
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getDescricao()!=null && !obj.getDescricao().isEmpty())
                    ps.setString(++cont, obj.getDescricao());
                if(obj!=null && obj.getCprod().getCodigo()!=null && obj.getCprod().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCprod().getCodigo());
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
                if(obj!=null && obj.getQtdeEmbalagem()!=null && !obj.getQtdeEmbalagem().isEmpty())
                    ps.setString(++cont, obj.getQtdeEmbalagem());
                rs = ps.executeQuery();
                while(rs.next()){
                    Produto p = new Produto();
                    CategoriaProduto cat = new CategoriaProduto();
                    LoteProduto ltp = new LoteProduto();
                    UnidadeMedida um = new UnidadeMedida();
                    Marca m = new Marca();
                    p.setCodigo(rs.getInt("prod_codigo"));
                    p.setDescricao(rs.getString("prod_descricao"));
                    cat.setCodigo(rs.getInt("tpp_codigo"));
                    p.setCprod(cat.select(con));
                    um.setCodigo(rs.getInt("um_codigo"));
                    p.setUnimed(um.select(con));
                    m.setCodigo(rs.getInt("mar_codigo"));
                    p.setMarca(m.select(con));
                    p.setPrecoBase(rs.getDouble("prod_precobase"));
                    p.setMargemLucro(rs.getDouble("prod_margemlucro"));
                    p.setPreco(rs.getDouble("prod_preco"));
                    p.setQtdeMin(rs.getInt("prod_qtdemin"));
                    p.setQtdeEstoque(rs.getInt("prod_estoque"));
                    p.setQtdeEmbalagem(rs.getString("prod_qtdeEmbalagem"));
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
    
    public int updateEstoque(Produto obj, Connection con) throws DAOException{
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(ctrEstoque);
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
    
    
    // Baixa Manual no Estoque
    public int insertBaixa(BaixaManual obj, Connection con) throws DAOException{
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(insertBaixa);
                ps.setString(++cont, obj.getMotivo());
                ps.setInt(++cont, obj.getQtde());
                ps.setInt(++cont, obj.getProd().getCodigo());
                ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                ps.setInt(++cont, obj.getFunc().getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
