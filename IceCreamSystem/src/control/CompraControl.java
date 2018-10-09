package control;

import entidade.*;
import entidade.ItensCompra;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.Banco;


public class CompraControl {
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public int gravarCompra(int codigo, String fornecedor, double valor, List<ItensCompra> lista) throws ControlException, SQLException{
        Compra c = new Compra();
        if(codigo!=0)
            c.setCodigo(codigo);
        
        if(fornecedor!=null && !fornecedor.isEmpty()){
            Fornecedor f = new Fornecedor();
            f.setNome(fornecedor);
            try{
                f = f.select(con);
                c.setForn(f);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        c.setValor(valor);
        c.setProdutosCompra(lista);
        try{
            con.setAutoCommit(false);
            int chave = c.insert(con);
            for(int i = 0; i<lista.size(); i++){
                int tot = lista.get(i).getP().getQtdeEstoque()+lista.get(i).getQtd();
                lista.get(i).getP().setQtdeEstoque(tot);
                lista.get(i).getP().updateEstoque(con);
            }
            ContasPagar cp = new ContasPagar();
            cp.setCompra(c);
            cp.setData(new Date());
            cp.setValor(valor);
            TipoDespesas tp = new TipoDespesas();
            tp.setCodigo(5); tp.setDescricao("Compras");
            cp.setTpd(tp);
            cp.insert(con);
            con.commit();
            return chave;
        }catch(EntidadeException ex){
            con.rollback();
            throw new ControlException(ex.getMessage());
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(CompraControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
