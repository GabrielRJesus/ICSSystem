package control;

import entidade.*;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.Banco;

public class VendaControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravarVenda(int codigo,String comanda, Date data, int clicodigo, String cliente, String funcionario, String tipo, Date entrega, List<ItensVenda> lista, double total) throws ControlException, SQLException{
        Venda v = new Venda();
        Cliente c = new Cliente();
        Funcionario f = new Funcionario();
        TipoVenda tpv = new TipoVenda();
        int chave = 0, ret=0;
        if(codigo!=0)
            v.setCodigo(codigo);
        if(comanda!=null && !comanda.isEmpty())
            v.setComanda(comanda);
        v.setData(data);
        if(clicodigo!=0){
            c.setCodigo(codigo);
        }
        v.setCliNome(cliente);
        if(funcionario!=null && !funcionario.isEmpty())
            f.setNome(funcionario);
        if(tipo!=null && !tipo.isEmpty())
            tpv.setDescricao(tipo);
        v.setTotal(total);
        v.setLista(lista);
        if(entrega!=null)
            v.setEntrega(entrega);
        try{
            con.setAutoCommit(false);
            f = f.select(con);
            v.setFunc(f);
            if(c.getCodigo()!=null && c.getCodigo()!=0){
                c = c.select(con);
                v.setCli(c);
            }
            tpv = tpv.select(con);
            v.setTpv(tpv);
            v.setStatus("aberta");
            
            if(codigo!=0){
                v.deleteItens(con);
                ret =  v.update(con);
                return ret;
            }
            else{
                chave = v.insert(con);
                return chave;
            }
            
            
        }catch(EntidadeException ex){
            con.rollback();
            throw new ControlException(ex.getMessage());
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(VendaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.commit();
        if(chave>0)
            return chave;
        else
            return ret;
        
    }
    
    public int excluirVenda(int codigo) throws ControlException{
        Venda v = new Venda();
        v.setCodigo(codigo);
        try{
           return v.delete(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public void guardaSelecionada(Venda v){
        Venda.setVenSelecionada(v);
    }
    
    public Venda retornaSelecinada(){
        return Venda.getVenSelecionada();
    }
    
    public Venda seleciona(int codigo, String comanda, String status)throws ControlException{
        Venda v = new Venda();
        if(codigo!=0)
            v.setCodigo(codigo);
        if(comanda!=null && !comanda.isEmpty())
            v.setComanda(comanda);
        if(status!=null && !status.isEmpty())
            v.setStatus(status);
        try{
            return v.select(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public List<Venda> listaVendas(int codigo, String cliente, String status, Integer tipo)throws ControlException, EntidadeException{
        Venda v = new Venda();
        if(codigo!=0)
            v.setCodigo(codigo);
        if(cliente!=null && !cliente.isEmpty())
            v.setCliNome(cliente);
        if(status!=null && !status.isEmpty())
            v.setStatus(status);
        if(tipo!=null && tipo!=0){
            TipoVenda tv = new TipoVenda();
            tv.setCodigo(tipo);
            tv = tv.select(con);
            v.setTpv(tv);
        }
        try{
            return v.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
}
