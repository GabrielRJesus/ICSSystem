package DAO;

import entidade.Fornecedor;
import entidade.Logradouro;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO implements GenericDAO<Fornecedor>{
    private String insert = "insert into fornecedor(for_nomeFantasia, for_cnpj, for_ie, for_razaosocial, for_responsavel,"
            + " for_situacao, for_inicioAtividades, for_finalAtividades, for_observacoes, for_ramoAtividade, for_telefone, for_email, log_coigo)"
            + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String update = "update fornecedor set for_nomeFantasia = ?, for_npcj = ?, for_ie = ?, for_razaosocial = ?, for_responsavel = ?,"
            + " for_situacao = ?, for_inicioAtividades = ?, for_finalAtividades = ?, for_observacoes = ?, for_ramoAtividade = ?,"
            + " for_telefone = ?, for_email = ?, log_codigo = ? where for_codigo = ?";
    private String delete = "delete from fornecedor";
    private String select = "select * from fornecedor";

    @Override
    public int insert(Fornecedor obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getCnpj());
                ps.setString(++cont, obj.getIe());
                ps.setString(++cont, obj.getRazaosocial());
                ps.setString(++cont, obj.getResponsavel());
                ps.setInt(++cont, obj.getStatus());
                if(obj.getInicioAtiv()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getInicioAtiv().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                if(obj.getFimAtiv()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getFimAtiv().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setString(++cont, obj.getObservacoes());
                ps.setString(++cont, obj.getRamoAtiv());
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getEmail());
                ps.setInt(++cont, obj.getLogradouro().getCodigo());
                return ps.executeUpdate();
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }

    @Override
    public int update(Fornecedor obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        int cont = 0;
        if(con!=null){
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getCnpj());
                ps.setString(++cont, obj.getIe());
                ps.setString(++cont, obj.getRazaosocial());
                ps.setString(++cont, obj.getResponsavel());
                ps.setInt(++cont, obj.getStatus());
                if(obj.getInicioAtiv()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getInicioAtiv().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                if(obj.getFimAtiv()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getFimAtiv().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setString(++cont, obj.getObservacoes());
                ps.setString(++cont, obj.getRamoAtiv());
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getEmail());
                ps.setInt(++cont, obj.getLogradouro().getCodigo());
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
    public int delete(Fornecedor obj, Connection con) throws DAOException {
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
    public Fornecedor select(Fornecedor obj, Connection con) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where for_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty()){
                if(ultimo)
                    select+=" and for_nome like ?";
                else{
                    select +=" where for_nome like ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCnpj()!=null && !obj.getCnpj().isEmpty()){
                if(ultimo)
                    select+=" and for_cnpj = ?";
                else{
                    select +=" where for_cnpj = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getIe()!=null && !obj.getIe().isEmpty()){
                if(ultimo)
                    select+=" and for_ie = ?";
                else{
                    select +=" where for_ie = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getTelefone()!=null && !obj.getTelefone().isEmpty()){
                if(ultimo)
                    select+=" and for_telefone = ?";
                else{
                    select +=" where for_telefone = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getStatus()!=0){
                if(ultimo)
                    select+=" and for_status = ?";
                else{
                    select +=" where for_status = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getEmail()!=null && !obj.getEmail().isEmpty()){
                if(ultimo)
                    select+=" and for_email = ?";
                else{
                    select +=" where for_email = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0){
                if(ultimo)
                    select+=" and cli_email = ?";
                else{
                    select +=" where cli_email = ?";
                    ultimo = true;
                }
            }
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null & obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getNome()!=null & !obj.getNome().isEmpty())
                    ps.setString(++cont, obj.getNome());
                if(obj!=null && obj.getCnpj()!=null & !obj.getCnpj().isEmpty())
                    ps.setString(++cont, obj.getCnpj());
                if(obj!=null && obj.getIe()!=null & !obj.getIe().isEmpty())
                    ps.setString(++cont, obj.getIe());
                if(obj!=null && obj.getTelefone()!=null & !obj.getTelefone().isEmpty())
                    ps.setString(++cont, obj.getTelefone());
                if(obj!=null && obj.getStatus()!=0)
                    ps.setInt(++cont, obj.getStatus());
                if(obj!=null && obj.getEmail()!=null & !obj.getEmail().isEmpty())
                    ps.setString(++cont, obj.getEmail());
                if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0)
                    ps.setInt(++cont, obj.getLogradouro().getCodigo());
                rs = ps.executeQuery();
                if(rs.next()){
                    Fornecedor f = new Fornecedor();
                    Logradouro log = new Logradouro();
                    f.setCodigo(rs.getInt("for_codigo"));
                    f.setNome(rs.getString("for_nome"));
                    f.setCnpj(rs.getString("for_cnpj"));
                    f.setIe(rs.getString("for_ie"));
                    f.setRazaosocial(rs.getString("for_razaosocial"));
                    f.setResponsavel(rs.getString("for_responsavel"));
                    f.setStatus(rs.getInt("for_status"));
                    f.setInicioAtiv(rs.getDate("for_inicioAtividades"));
                    f.setFimAtiv(rs.getDate("for_fimAtividades"));
                    f.setObservacoes(rs.getString("for_observacoes"));
                    f.setRamoAtiv(rs.getString("for_ramoAtividade"));
                    f.setTelefone(rs.getString("for_telefone"));
                    f.setEmail(rs.getString("for_email"));
                    log.setCodigo(rs.getInt("log_codigo"));
                    f.setLogradouro(log.select(con));
                    return f;
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
    
    public List<Fornecedor> lista(Fornecedor obj, Connection con) throws DAOException {
        List<Fornecedor> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cont = 0;
        boolean ultimo = false;
        if(con!=null){
            if(obj!=null && obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where for_codigo = ?";
                ultimo = true;
            }
            
            if(obj!=null && obj.getNome()!=null && !obj.getNome().isEmpty()){
                if(ultimo)
                    select+=" and for_nome like ?";
                else{
                    select +=" where for_nome like ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getCnpj()!=null && !obj.getCnpj().isEmpty()){
                if(ultimo)
                    select+=" and for_cnpj = ?";
                else{
                    select +=" where for_cnpj = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getIe()!=null && !obj.getIe().isEmpty()){
                if(ultimo)
                    select+=" and for_ie = ?";
                else{
                    select +=" where for_ie = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getTelefone()!=null && !obj.getTelefone().isEmpty()){
                if(ultimo)
                    select+=" and for_telefone = ?";
                else{
                    select +=" where for_telefone = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getStatus()!=0){
                if(ultimo)
                    select+=" and for_status = ?";
                else{
                    select +=" where for_status = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getEmail()!=null && !obj.getEmail().isEmpty()){
                if(ultimo)
                    select+=" and for_email = ?";
                else{
                    select +=" where for_email = ?";
                    ultimo = true;
                }
            }
            
            if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0){
                if(ultimo)
                    select+=" and cli_email = ?";
                else{
                    select +=" where cli_email = ?";
                    ultimo = true;
                }
            }
            try{
                ps = con.prepareStatement(select);
                if(obj!=null && obj.getCodigo()!=null & obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj!=null && obj.getNome()!=null & !obj.getNome().isEmpty())
                    ps.setString(++cont, obj.getNome());
                if(obj!=null && obj.getCnpj()!=null & !obj.getCnpj().isEmpty())
                    ps.setString(++cont, obj.getCnpj());
                if(obj!=null && obj.getIe()!=null & !obj.getIe().isEmpty())
                    ps.setString(++cont, obj.getIe());
                if(obj!=null && obj.getTelefone()!=null & !obj.getTelefone().isEmpty())
                    ps.setString(++cont, obj.getTelefone());
                if(obj!=null && obj.getStatus()!=0)
                    ps.setInt(++cont, obj.getStatus());
                if(obj!=null && obj.getEmail()!=null & !obj.getEmail().isEmpty())
                    ps.setString(++cont, obj.getEmail());
                if(obj!=null && obj.getLogradouro().getCodigo()!=null && obj.getLogradouro().getCodigo()!=0)
                    ps.setInt(++cont, obj.getLogradouro().getCodigo());
                rs = ps.executeQuery();
                while(rs.next()){
                    Fornecedor f = new Fornecedor();
                    Logradouro log = new Logradouro();
                    f.setCodigo(rs.getInt("for_codigo"));
                    f.setNome(rs.getString("for_nome"));
                    f.setCnpj(rs.getString("for_cnpj"));
                    f.setIe(rs.getString("for_ie"));
                    f.setRazaosocial(rs.getString("for_razaosocial"));
                    f.setResponsavel(rs.getString("for_responsavel"));
                    f.setStatus(rs.getInt("for_status"));
                    f.setInicioAtiv(rs.getDate("for_inicioAtividades"));
                    f.setFimAtiv(rs.getDate("for_fimAtividades"));
                    f.setObservacoes(rs.getString("for_observacoes"));
                    f.setRamoAtiv(rs.getString("for_ramoAtividade"));
                    f.setTelefone(rs.getString("for_telefone"));
                    f.setEmail(rs.getString("for_email"));
                    log.setCodigo(rs.getInt("log_codigo"));
                    f.setLogradouro(log.select(con));
                    lista.add(f);
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
