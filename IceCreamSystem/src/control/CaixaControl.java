package control;

import entidade.Caixa;
import entidade.Funcionario;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import sql.Banco;

public class CaixaControl {
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int abrirCaixa(Funcionario f, double valor, Date data, String periodo) throws ControlException{
        Caixa c = new Caixa();
        c.setAbertura(data);
        c.setPeriodoA(periodo);
        c.setTroco(valor);
        c.setFunc(f);
        c.setFechamento(null);
        c.setPeriodoB(null);
        try{
            return c.abrirCaixa(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public int fecharCaixa(Funcionario f, double valor, Date data, String periodo) throws ControlException, EntidadeException{
        Caixa c = new Caixa();
        c = c.retornaAberto(con);
        c.setTrocoFinal(valor);
        c.setFunc(f);
        c.setFechamento(data);
        c.setPeriodoB(periodo);
        try{
            return c.fecharCaixa(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public Caixa retornaCaixaAberto() throws ControlException{
        Caixa c = new Caixa();
        try{
            return c.retornaAberto(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
}
