package entidade;

import DAO.VendaDAO;
import exception.DAOException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private Integer codigo;
    private String comanda;
    private double total;
    private Date data;
    private String status;
    private Date entrega;
    private TipoVenda tpv;
    private Cliente cli;
    private Funcionario func;
    private String cliNome;
    
    private List<ItensVenda> lista;
    
    private static Venda venSelecionada;
    
    public Venda() {
        tpv = new TipoVenda();
        cli = new Cliente();
        func = new Funcionario();
        lista = new ArrayList<>();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getComanda() {
        return comanda;
    }

    public void setComanda(String comanda) {
        this.comanda = comanda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public TipoVenda getTpv() {
        return tpv;
    }

    public void setTpv(TipoVenda tpv) {
        this.tpv = tpv;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public List<ItensVenda> getLista() {
        return lista;
    }

    public void setLista(List<ItensVenda> lista) {
        this.lista = lista;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public static Venda getVenSelecionada() {
        return venSelecionada;
    }

    public static void setVenSelecionada(Venda venSelecionada) {
        Venda.venSelecionada = venSelecionada;
    }
    
    public int insert(Connection con) throws EntidadeException{
        try{
            return new VendaDAO().insert(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int update(Connection con) throws EntidadeException{
        try{
            return new VendaDAO().update(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int delete(Connection con) throws EntidadeException{
        try{
            return new VendaDAO().delete(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public int deleteItens(Connection con) throws EntidadeException{
        try{
            return new VendaDAO().deleteItens(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public Venda select(Connection con) throws EntidadeException{
        try{
            return new VendaDAO().select(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
    public List<Venda> lista(Connection con) throws EntidadeException{
        try{
            return new VendaDAO().lista(this,con);
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
}
