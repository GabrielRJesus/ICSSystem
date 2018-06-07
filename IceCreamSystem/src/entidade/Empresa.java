package entidade;

import DAO.EmpresaDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Blob;
import java.sql.Connection;
import java.util.Date;


public class Empresa {
    
    private Integer codigo;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String ie;
    private String telefone;
    private Blob logo;
    private Date dataFundação;

    public Empresa() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataFundação() {
        return dataFundação;
    }

    public void setDataFundação(Date dataFundação) {
        this.dataFundação = dataFundação;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new EmpresaDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new EmpresaDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public Empresa select(Connection con) throws EntidadeException{
        try{
            return new EmpresaDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
