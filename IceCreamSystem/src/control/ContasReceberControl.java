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


public class ContasReceberControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravrConta(List<TPagamentoReceber> lista, double total) throws ControlException, SQLException{
        ContasReceber cr = new ContasReceber();
        Caixa caixa = new Caixa();
        Movimentacao m = new Movimentacao();
        cr.setListaRecebimentos(lista);
        cr.setVenda(Venda.getVenSelecionada());
        cr.setDtPagto(new Date());
        cr.setValor(Venda.getVenSelecionada().getTotal());
        if(Venda.getVenSelecionada().getEntrega()!=null)
            cr.setDtVencimento(Venda.getVenSelecionada().getEntrega());
        else
            cr.setDtVencimento(new Date());
        cr.setValorPago(total);
        
        try{
            con.setAutoCommit(false);
            caixa = caixa.retornaAberto(con);
            if(caixa!=null && caixa.getCodigo()!=null && caixa.getCodigo()!=0){
                m.setCaixa(caixa);
                m.setCr(cr);
                m.setData(new Date());
                m.setDescricao("Contas a Receber");
                m.setValor(total);
            }
            int chave = cr.insert(con);
            cr.setCodigo(chave);
            m.setCr(cr);
            m.insert(con);
            for(int j =0; j<lista.size();j++){
                lista.get(j).insert(cr.getCodigo(), con);
            }
            List<ItensVenda> listat = Venda.getVenSelecionada().getLista();
            for(int i = 0; i<listat.size(); i++){
                Produto p = new Produto();
                p = listat.get(i).getProd();
                p.setQtdeEstoque(p.getCodigo()-listat.get(i).getQtde());
                p.update(con);
            }
            con.commit();
            return chave;
        }catch(EntidadeException ex){
            con.rollback();
            throw new ControlException(ex.getMessage());
        } catch (SQLException ex) {
            con.rollback();
            throw new ControlException(ex.getMessage());
        }
    }
    
}
