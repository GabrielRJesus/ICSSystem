package entidade;

import java.util.Date;

/**
 *
 * @author gabri
 */
public class Fornecedor extends Pessoa{
    private String cnpj;
    private String ie;
    private String razaosocial;
    private String responsavel;
    private int status;
    private Date inicioAtiv;
    private Date fimAtiv;
    private String ramoAtiv;
    private String observacoes;

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
    
    
}
