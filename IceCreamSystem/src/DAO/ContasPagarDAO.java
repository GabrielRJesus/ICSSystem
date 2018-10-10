package DAO;

import entidade.Compra;
import entidade.ContasPagar;
import entidade.TipoDespesas;
import entidade.TipoPagamento;
import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContasPagarDAO implements GenericDAO<ContasPagar>{
    
    private String insert = "insert into contas_pagar(con_data, con_valor, con_dtpgto, con_valorpago,  com_codigo, tpc_codigo) values(?,?,?,?,?,?)";
    private String update = "update contas_pagar set con_data = ?, con_valor = ?, con_dtpgto = ?, con_valorpago = ?,  com_codigo = ?, tpc_codigo = ? where con_codigo = ?";
    private String select = "select * from contas_pagar c inner join tipo_conta tc on tc.tpc_codigo = c.tpc_codigo inner join tppagamento_cpagar tp on tp.con_codigo = c.con_coigo";
    private String delete = "delete from contas_pagar where con_codigo = ?";
    
    @Override
    public int insert(ContasPagar obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(insert);
                ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                ps.setDouble(++cont, obj.getValor());
                if(obj.getDtpgto()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtpgto().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setDouble(++cont, obj.getValorpago());
                if(obj.getCompra()!=null && obj.getCompra().getCodigo()!=null && obj.getCompra().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCompra().getCodigo());
                else
                    ps.setNull(++cont, java.sql.Types.INTEGER);
                ps.setInt(++cont, obj.getTpd().getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(ContasPagar obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(update);
                ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                ps.setDouble(++cont, obj.getValor());
                if(obj.getDtpgto()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtpgto().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setDouble(++cont, obj.getValorpago());
                if(obj.getCompra()!=null && obj.getCompra().getCodigo()!=null && obj.getCompra().getCodigo()!=0)
                    ps.setInt(++cont, obj.getCompra().getCodigo());
                else
                    ps.setNull(++cont, java.sql.Types.INTEGER);
                ps.setInt(++cont, obj.getTpd().getCodigo());
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
    public int delete(ContasPagar obj, Connection con) throws DAOException {
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
    public ContasPagar select(ContasPagar obj, Connection con) throws DAOException {
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=null && obj.getCodigo()!=0){
                select += " where c.con_codigo = ?";
                ultimo = true;
            }
            if(obj.getTpd()!=null && obj.getTpd().getCodigo()!=null && obj.getTpd().getCodigo()!=0){
                if(ultimo)
                    select+= " and tc.tpc_codigo = ?";
                else{
                    select+=" where tc.tpc_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj.getData()!=null){
                if(ultimo)
                    select+=" and c.con_data between ? and ?";
                else
                    select+=" where c.con_data between ? and ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                     ps.setInt(++cont, obj.getCodigo());
                if(obj.getTpd()!=null && obj.getTpd().getCodigo()!=null && obj.getTpd().getCodigo()!=0)
                    ps.setInt(++cont, obj.getTpd().getCodigo());
                if(obj.getData()!=null){
                    ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                    ps.setDate(++cont, new java.sql.Date(new Date().getTime()));
                }
                rs = ps.executeQuery();
                while(rs.next()){
                    ContasPagar cp = new ContasPagar();
                    TipoDespesas td = new TipoDespesas();
                    TipoPagamento tp = new TipoPagamento();
                    Compra c = new Compra();
                    td.setCodigo(rs.getInt("tc.tpc_codigo"));
                    td.setDescricao(rs.getString("tc.tpc_descricao"));
                    cp.setCodigo(rs.getInt("c.con_codigo"));
                    cp.setData(rs.getDate("c.con_data"));
                    cp.setValor(rs.getDouble("c.con_valor"));
                    cp.setDtpgto(rs.getDate("c.con_dtpgto"));
                    cp.setValorpago(rs.getDouble("c.con_valorpago"));
                    cp.setTpd(td);
                    c.setCodigo(rs.getInt("c.com_codigo"));
                    cp.setCompra(c); // fazer o select
                    return cp;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
        return null;
    }
    
    public List<ContasPagar> lista(ContasPagar obj, Connection con) throws DAOException{
        List<ContasPagar> lista = new ArrayList<>();
        if(con!=null){
            PreparedStatement ps = null;
            int cont = 0;
            ResultSet rs = null;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=null && obj.getCodigo()!=0){
                select += " where c.con_codigo = ?";
                ultimo = true;
            }
            if(obj.getTpd()!=null && obj.getTpd().getCodigo()!=null && obj.getTpd().getCodigo()!=0){
                if(ultimo)
                    select+= " and tc.tpc_codigo = ?";
                else{
                    select+=" where tc.tpc_codigo = ?";
                    ultimo = true;
                }
            }
            if(obj.getData()!=null){
                if(ultimo)
                    select+=" and c.con_data between ? and ?";
                else
                    select+=" where c.con_data between ? and ?";
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                     ps.setInt(++cont, obj.getCodigo());
                if(obj.getTpd()!=null && obj.getTpd().getCodigo()!=null && obj.getTpd().getCodigo()!=0)
                    ps.setInt(++cont, obj.getTpd().getCodigo());
                if(obj.getData()!=null){
                    ps.setDate(++cont, new java.sql.Date(obj.getData().getTime()));
                    ps.setDate(++cont, new java.sql.Date(new Date().getTime()));
                }
                rs = ps.executeQuery();
                while(rs.next()){
                    ContasPagar cp = new ContasPagar();
                    TipoDespesas td = new TipoDespesas();
                    TipoPagamento tp = new TipoPagamento();
                    Compra c = new Compra();
                    td.setCodigo(rs.getInt("tc.tpc_codigo"));
                    td.setDescricao(rs.getString("tc.tpc_descricao"));
                    cp.setCodigo(rs.getInt("c.con_codigo"));
                    cp.setData(rs.getDate("c.con_data"));
                    cp.setValor(rs.getDouble("c.con_valor"));
                    cp.setDtpgto(rs.getDate("c.con_dtpgto"));
                    cp.setValorpago(rs.getDouble("c.con_valorpago"));
                    cp.setTpd(td);
                    c.setCodigo(rs.getInt("c.com_codigo"));
                    cp.setCompra(c); // fazer o select
                    lista.add(cp);
                }
                return lista;
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
    
}
