package entidade;

/**
 *
 * @author gabri
 */
public class Cidade {
    private Integer codigo;
    private String nome;
    private Estado estado;

    public Cidade() {
        this.estado = new Estado();
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}
