package control;

import entidade.LoteProduto;
import entidade.Produto;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import sql.Banco;
import util.Erro;

public class LoteProdutoControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public List<LoteProduto> listaLotes(Integer codigo, String descricao, String numero, Date inicio, Date fim, int qtde, int codProd) throws ControlException{
        LoteProduto ltp = new LoteProduto();
        Erro e = new Erro();
        if(codigo!=0 && codigo!=null)
            ltp.setCodigo(codigo);
        ltp.setDescricao(descricao);
        ltp.setNumeroLote(numero);
        ltp.setQtdRemanescente(qtde);
        if(codProd!=0){
            Produto p = new Produto();
            p.setCodigo(codProd);
            ltp.setProd(p);
        }
        try{
            return ltp.listaLotePorProduto(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public void guardaSelecionado(Integer codigo, String descricao, String numero, Date data, int qtdt, int qtdr){
        LoteProduto ltp = new LoteProduto();
        ltp.setCodigo(codigo);
        ltp.setDescricao(descricao);
        ltp.setNumeroLote(numero);
        ltp.setValidade(data);
        ltp.setQtdeCompra(qtdt);
        ltp.setQtdRemanescente(qtdr);
        new LoteProduto().setLoteSelecionado(ltp);
    }
    
    public LoteProduto retornaSelecionado(){
        LoteProduto ltp = new LoteProduto();
        return ltp.getLoteSelecionado();
    }
    
    public int gravaLote(Integer codigo, String descricao, String numero, Date data, int qtdt, int qtdr) throws ControlException{
        LoteProduto lp = new LoteProduto();
        Erro e = new Erro();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha o campo descricao");
        if(descricao.length()>40)
            e.add("Campo descricao possui muitos caracteres");
        if(numero==null || numero.isEmpty())
            e.add("Preencha o campo numero");
        if(numero.length()>30)
            e.add("Campo numero possui muitos caracteres");
        if(data==null)
            e.add("Preencha o campo data");
        if(qtdt==0)
            e.add("Preencha o campo quantidade");
        
        if(!e.isTemErro()){
            lp.setDescricao(descricao);
            lp.setNumeroLote(numero);
            lp.setValidade(data);
            lp.setQtdeCompra(qtdt);
            lp.setQtdRemanescente(qtdt);
            if(codigo!=0 && codigo!=null){
                lp.setCodigo(codigo);
                lp.setQtdRemanescente(qtdr);
            }
            try{
                if(lp.getCodigo()!=null && lp.getCodigo()!=0)
                    return lp.update(con);
                else
                    return lp.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public int excluiLote(Integer codigo) throws ControlException{
        LoteProduto lp = new LoteProduto();
        Erro e = new Erro();
        if(codigo==null || codigo==0)
            e.add("Selecione um item para excluir");
        
        if(!e.isTemErro()){
            lp.setCodigo(codigo);
            try{
                return lp.delete(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
}
