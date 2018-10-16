package control;

import entidade.*;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import sql.Banco;

public class VendaControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravarVenda(int codigo,String comanda, Date data, int clicodigo, String cliente, String funcionario, String tipo, Date entrega, List<ItensVenda> lista, double total) throws ControlException{
        Venda v = new Venda();
        Cliente c = new Cliente();
        Funcionario f = new Funcionario();
        TipoVenda tpv = new TipoVenda();
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
            f.setCodigo(codigo);
        if(tipo!=null && !tipo.isEmpty())
            tpv.setDescricao(tipo);
        v.setTotal(total);
        v.setLista(lista);
        if(entrega!=null)
            v.setEntrega(entrega);
        try{
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
                int ret =  v.update(con);
                for(int j = 0; j<Venda.getVenSelecionada().getLista().size(); j++){
                    if(Venda.getVenSelecionada().getLista().get(j).getQtde() > lista.get(j).getQtde()){
                        Produto p = lista.get(j).getProd();
                        int dif = Venda.getVenSelecionada().getLista().get(j).getQtde() - lista.get(j).getQtde();
                        p.setQtdeEstoque(p.getQtdeEstoque()+dif);
                        p.update(con);
                    }else{
                        Produto p = lista.get(j).getProd();
                        int dif = lista.get(j).getQtde() - Venda.getVenSelecionada().getLista().get(j).getQtde();
                        p.setQtdeEstoque(p.getQtdeEstoque()-dif);
                        p.update(con);
                    }
                }
                return ret;
            }
            else{
                int chave = v.insert(con);
                for(int i = 0; i<lista.size(); i++){
                    Produto p = new Produto();
                    p.setCodigo(lista.get(i).getProd().getCodigo());
                    p = p.select(con);
                    p.setQtdeEstoque(p.getQtdeEstoque()-lista.get(i).getQtde());
                    p.update(con);
                }
                return chave;
            }
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
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
    
}
