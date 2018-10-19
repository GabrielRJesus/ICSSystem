package DAO;

import entidade.*;
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

public class VendaDAO implements GenericDAO<Venda>{
    
    private String insert = "insert into venda(ven_comanda, ven_data, ven_total, ven_status, ven_dtentrega, tpv_codigo, cliente_cli_codigo, funcionario_cli_codigo, ven_cliNome) values(?,?,?,?,?,?,?,?,?)";
    private String insertl = "insert into itens_venda(itv_qtde, prod_codigo, ven_codigo, itv_valor) values(?,?,?,?)";
    
    private String delete = "delete from venda where ven_codigo = ?";
    private String deletel = "delete from itens_venda where ven_codigo = ?";
    
    private String select = "select * from venda";
    private String selectl = "select * from itens_venda where ven_codigo = ?";
    
    private String update = "update venda set ven_comanda = ?, ven_data = ?, ven_total = ?, ven_status = ?, ven_dtentrega = ?, tpv_codigo = ?, cliente_cli_codigo = ?, funcionario_cli_codigo = ?, ven_cliNome = ? where ven_codigo = ?";
    private String updatel = "update itens_venda set itv_qtde = ?, itv_valor = ? where ven_codigo = ? and prod_codigo = ?";
    
    @Override
    public int insert(Venda obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            int chave = -1;
            try{
                ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(++cont, obj.getComanda());
                ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                ps.setDouble(++cont, obj.getTotal());
                ps.setString(++cont, obj.getStatus());
                if(obj.getEntrega()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getEntrega().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setInt(++cont, obj.getTpv().getCodigo());
                if(obj.getCli()!=null && obj.getCli().getCodigo()!=null && obj.getCli().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCli().getCodigo());
                else
                    ps.setNull(++cont, java.sql.Types.INTEGER);
                ps.setInt(++cont, obj.getFunc().getCodigo());
                ps.setString(++cont, obj.getCliNome());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if(rs!=null && rs.next()){
                    chave = rs.getInt(1);
                }
                for(int i = 0; i<obj.getLista().size(); i++){
                    PreparedStatement ps2 = null;
                    int conta = 0;
                    ps2 = con.prepareStatement(insertl);
                    ps2.setInt(++conta, obj.getLista().get(i).getQtde());
                    ps2.setInt(++conta, obj.getLista().get(i).getProd().getCodigo());
                    ps2.setInt(++conta, chave);
                    ps2.setDouble(++conta, obj.getLista().get(i).getValor());
                    ps2.executeUpdate();
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
    public int update(Venda obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getComanda());
                ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                ps.setDouble(++cont, obj.getTotal());
                ps.setString(++cont, obj.getStatus());
                if(obj.getEntrega()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getEntrega().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setInt(++cont, obj.getTpv().getCodigo());
                 if(obj.getCli()!=null && obj.getCli().getCodigo()!=null && obj.getCli().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCli().getCodigo());
                else
                    ps.setNull(++cont, java.sql.Types.INTEGER);
                ps.setInt(++cont, obj.getFunc().getCodigo());
                ps.setString(++cont, obj.getCliNome());
                ps.setInt(++cont, obj.getCodigo());
                
                for(int i = 0; i<obj.getLista().size(); i++){
                    PreparedStatement ps2 = null;
                    int cont2 = 0;
                    ps2 = con.prepareStatement(insertl);
                    ps2.setInt(++cont2, obj.getLista().get(i).getQtde());
                    ps2.setInt(++cont2, obj.getLista().get(i).getProd().getCodigo());
                    ps2.setInt(++cont2, obj.getCodigo());
                    ps2.setDouble(++cont2, obj.getLista().get(i).getValor());
                    ps2.executeUpdate();
                }
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int delete(Venda obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null, ps2 = null;
            int cont = 0, cont2 = 0;
            try{
                ps2 = con.prepareStatement(deletel);
                ps2.setInt(++cont2, obj.getCodigo());
                ps2.executeUpdate();
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
    
    public int deleteItens(Venda obj, Connection con) throws DAOException{
        if(con!=null){
            PreparedStatement ps = null, ps2 = null;
            int cont = 0, cont2 = 0;
            try{
                ps2 = con.prepareStatement(deletel);
                ps2.setInt(++cont2, obj.getCodigo());
                return ps2.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public Venda select(Venda obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where ven_codigo = ?";
                ultimo = true;
            }
            if(obj.getComanda()!=null && !obj.getComanda().isEmpty()){
                if(ultimo)
                    select+=" and ven_comanda = ?";
                else{
                    select+=" where ven_comanda = ?";
                    ultimo = true;
                }
            }
            if(obj.getData()!=null){
                if(ultimo)
                    select+=" and ven_data = ?";
                else{
                    select+=" where ven_data = ?";
                    ultimo = true;
                }
            }
            if(obj.getFunc()!=null && obj.getFunc().getCodigo()!=null && obj.getFunc().getCodigo()!=0){
                if(ultimo)
                    select+=" and funcionario_cli_codigo = ?";
                else{
                    select+=" where funcionario_cli_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj.getStatus()!=null && !obj.getStatus().isEmpty()){
                if(ultimo)
                    select+=" and ven_status = ?";
                else{
                    select+=" where ven_status = ?";
                    ultimo = true;
                }
            }
            if(obj.getTpv()!=null && obj.getTpv().getCodigo()!=null && obj.getTpv().getCodigo()!=0){
                if(ultimo)
                    select +=" and tpv_codigo = ?";
                else
                    select+=" where tpv_codigo = ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj.getComanda()!=null && !obj.getComanda().isEmpty())
                    ps.setString(++cont, obj.getComanda());
                if(obj.getData()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                if(obj.getFunc()!=null && obj.getFunc().getCodigo()!=null && obj.getFunc().getCodigo()!=0)
                    ps.setInt(++cont, obj.getFunc().getCodigo());
                if(obj.getStatus()!=null && !obj.getStatus().isEmpty())
                    ps.setString(++cont, obj.getStatus());
                if(obj.getTpv()!=null && obj.getTpv().getCodigo()!=null && obj.getTpv().getCodigo()!=0)
                    ps.setInt(++cont, obj.getTpv().getCodigo());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    Venda v = new Venda();
                    Funcionario f = new Funcionario();
                    Cliente c = new Cliente();
                    TipoVenda tpv = new TipoVenda();
                    v.setCodigo(rs.getInt("ven_codigo"));
                    v.setComanda(rs.getString("ven_comanda"));
                    v.setData(rs.getDate("ven_data"));
                    v.setTotal(rs.getDouble("ven_total"));
                    v.setStatus(rs.getString("ven_status"));
                    v.setEntrega(rs.getDate("ven_dtentrega"));
                    tpv.setCodigo(rs.getInt("tpv_codigo"));
                    v.setTpv(tpv.select(con));
                    c.setCodigo(rs.getInt("cliente_cli_codigo"));
                    if(c.getCodigo()!=null && c.getCodigo()!=0)
                        v.setCli(c.select(con));
                    else
                        v.setCli(new Cliente());
                    f.setCodigo(rs.getInt("funcionario_cli_codigo"));
                    v.setFunc(f.select(con));
                    v.setCliNome(rs.getString("ven_cliNome"));
                    
                    PreparedStatement ps2 = null;
                    ResultSet rs2 = null;
                    int cont2 = 0;
                    ps2 = con.prepareStatement(selectl);
                    ps2.setInt(++cont2, v.getCodigo());
                    rs2 = ps2.executeQuery();
                    List<ItensVenda> lista = new ArrayList<>();
                    while(rs2.next()){
                        ItensVenda iv = new ItensVenda();
                        Produto p = new Produto();
                        iv.setQtde(rs2.getInt("itv_qtde"));
                        p.setCodigo(rs2.getInt("prod_codigo"));
                        iv.setProd(p.select(con));
                        iv.setValor(rs2.getDouble("itv_valor"));
                        lista.add(iv);
                    }
                    v.setLista(lista);
                    return v;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<Venda> lista(Venda obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            List<Venda> lista = new ArrayList<>();
            
            if(obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where ven_codigo = ?";
                ultimo = true;
            }
            if(obj.getComanda()!=null && !obj.getComanda().isEmpty()){
                if(ultimo)
                    select+=" and ven_comanda = ?";
                else{
                    select+=" where ven_comanda = ?";
                    ultimo = true;
                }
            }
            if(obj.getData()!=null){
                if(ultimo)
                    select+=" and ven_data = ?";
                else{
                    select+=" where ven_data = ?";
                    ultimo = true;
                }
            }
            if(obj.getFunc()!=null && obj.getFunc().getCodigo()!=null && obj.getFunc().getCodigo()!=0){
                if(ultimo)
                    select+=" and funcionario_cli_codigo = ?";
                else{
                    select+=" where funcionario_cli_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj.getStatus()!=null && !obj.getStatus().isEmpty()){
                if(ultimo)
                    select+=" and ven_status = ?";
                else{
                    select+=" where ven_status = ?";
                    ultimo = true;
                }
            }
            if(obj.getTpv()!=null && obj.getTpv().getCodigo()!=null && obj.getTpv().getCodigo()!=0){
                if(ultimo)
                    select +=" and tpv_codigo = ?";
                else
                    select+=" where tpv_codigo = ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj.getComanda()!=null && !obj.getComanda().isEmpty())
                    ps.setString(++cont, obj.getComanda());
                if(obj.getData()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                if(obj.getFunc()!=null && obj.getFunc().getCodigo()!=null && obj.getFunc().getCodigo()!=0)
                    ps.setInt(++cont, obj.getFunc().getCodigo());
                if(obj.getStatus()!=null && !obj.getStatus().isEmpty())
                    ps.setString(++cont, obj.getStatus());
                if(obj.getTpv()!=null && obj.getTpv().getCodigo()!=null && obj.getTpv().getCodigo()!=0)
                    ps.setInt(++cont, obj.getTpv().getCodigo());
                rs = ps.executeQuery();
                
                while(rs.next()){
                    Venda v = new Venda();
                    Funcionario f = new Funcionario();
                    Cliente c = new Cliente();
                    TipoVenda tpv = new TipoVenda();
                    v.setCodigo(rs.getInt("ven_codigo"));
                    v.setComanda(rs.getString("ven_comanda"));
                    v.setData(rs.getDate("ven_data"));
                    v.setTotal(rs.getDouble("ven_total"));
                    v.setStatus(rs.getString("ven_status"));
                    v.setEntrega(rs.getDate("ven_dtentrega"));
                    tpv.setCodigo(rs.getInt("tpv_codigo"));
                    v.setTpv(tpv.select(con));
                    c.setCodigo(rs.getInt("cliente_cli_codigo"));
                    if(c.getCodigo()!=null && c.getCodigo()!=0)
                        v.setCli(c.select(con));
                    else
                        v.setCli(new Cliente());
                    f.setCodigo(rs.getInt("funcionario_cli_codigo"));
                    v.setFunc(f.select(con));
                    v.setCliNome(rs.getString("ven_cliNome"));
                    
                    PreparedStatement ps2 = null;
                    ResultSet rs2 = null;
                    int cont2 = 0;
                    ps2 = con.prepareStatement(selectl);
                    ps2.setInt(++cont2, v.getCodigo());
                    rs2 = ps2.executeQuery();
                    List<ItensVenda> listaItens = new ArrayList<>();
                    while(rs2.next()){
                        ItensVenda iv = new ItensVenda();
                        Produto p = new Produto();
                        iv.setQtde(rs2.getInt("itv_qtde"));
                        p.setCodigo(rs2.getInt("prod_codigo"));
                        iv.setProd(p.select(con));
                        iv.setValor(rs2.getDouble("itv_valor"));
                        listaItens.add(iv);
                    }
                    v.setLista(listaItens);
                    lista.add(v);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            } catch (EntidadeException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
}
