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

public class ContaPagarControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int lancaContapagar(Integer codigo, Date data, double valor, TipoDespesas td)throws ControlException{
        ContasPagar cp = new ContasPagar();
        if(codigo!=null && codigo!=0)
            cp.setCodigo(codigo);
        cp.setData(data);
        cp.setValor(valor);
        cp.setParcela(0);
        cp.setTpd(td);
        try{
            if(codigo!=null && codigo!=0)
                return cp.update(con);
            else
                return cp.insert(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public List<ContasPagar> listaContas(Integer codigo, Date data, TipoDespesas td) throws ControlException{
        ContasPagar cp = new ContasPagar();
        if(codigo!=null && codigo!=0)
            cp.setCodigo(codigo);
        if(data!=null)
            cp.setData(data);
        try{
            return cp.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public ContasPagar seleciona(int codigo) throws ControlException{
        ContasPagar cp = new ContasPagar();
        cp.setCodigo(codigo);
        try{
            return cp.select(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public void guardaSelecionado(ContasPagar cp){
        ContasPagar.setCpSelecionada(cp);
    }
    
    public ContasPagar retornaSelecionada(ContasPagar cp) throws ControlException{
        return ContasPagar.getCpSelecionada();
    }
    
    public int quitarContaPagar(ContasPagar cp, List<TPaagamentoPagar> lista) throws ControlException, SQLException{
        Caixa caixa = new Caixa();
        double total = 0;
        for(int i=0; i<lista.size(); i++){
            total += lista.get(i).getValor();
        }
        cp.setDtpgto(new Date());
        cp.setValorpago(total);
        caixa = new CaixaControl().retornaCaixaAberto();
        if(caixa!=null && caixa.getCodigo()!=null && caixa.getCodigo()!=0){
            Movimentacao m = new Movimentacao();
            m.setCaixa(caixa);
            m.setCp(cp);
            m.setData(new Date());
            m.setDescricao("Contas a Pagar");
            m.setValor(total);
            try{
                con.setAutoCommit(false);
                m.insert(con);
                cp.update(con);
                for(int j =0; j<lista.size();j++){
                    lista.get(j).insert(cp.getCodigo(), con);
                }
                con.commit();
                
            } catch (SQLException ex) {
                con.rollback();
                Logger.getLogger(ContaPagarControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch(EntidadeException ex){
                con.rollback();
                throw new ControlException(ex.getMessage());
            }
            return 1;
        }else{
            return 0;
        }
    }       
}
