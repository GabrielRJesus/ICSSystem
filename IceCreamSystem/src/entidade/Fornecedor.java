package entidade;

import DAO.FornecedorDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Fornecedor extends Pessoa{
    private String cnpj;
    private String ie;
    private String razaosocial;
    private String responsavel;
    private int status; //situação
    private Date inicioAtiv;
    private Date fimAtiv;
    private String ramoAtiv;
    private String observacoes;
    
    private static Fornecedor forSelecionado;

    public Fornecedor() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getInicioAtiv() {
        return inicioAtiv;
    }

    public void setInicioAtiv(Date inicioAtiv) {
        this.inicioAtiv = inicioAtiv;
    }

    public Date getFimAtiv() {
        return fimAtiv;
    }

    public void setFimAtiv(Date fimAtiv) {
        this.fimAtiv = fimAtiv;
    }

    public String getRamoAtiv() {
        return ramoAtiv;
    }

    public void setRamoAtiv(String ramoAtiv) {
        this.ramoAtiv = ramoAtiv;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public static Fornecedor getForSelecionado() {
        return forSelecionado;
    }

    public static void setForSelecionado(Fornecedor forSelecionado) {
        Fornecedor.forSelecionado = forSelecionado;
    }
    
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new FornecedorDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new FornecedorDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    public int delete(Connection con) throws EntidadeException{
        try{
            return new FornecedorDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    public Fornecedor select(Connection con) throws EntidadeException{
        try{
            return new FornecedorDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Fornecedor> listaFor(Connection con) throws EntidadeException{
        try{
            return new FornecedorDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    
}
