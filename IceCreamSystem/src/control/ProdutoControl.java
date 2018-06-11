package control;

import entidade.*;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.List;
import sql.Banco;
import util.Erro;

public class ProdutoControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public List<String> buscaMarcas()throws ControlException, EntidadeException{
        return new Marca().listaString(con);
    }
    
    public List<String> buscaUm()throws ControlException, EntidadeException{
        return new UnidadeMedida().listaUMString(con);
    }
    
    public List<String> buscaCategorias()throws ControlException, EntidadeException{
        return new CategoriaProduto().listaString(con);
    }
    
    public List<String> buscaLote()throws ControlException, EntidadeException{
        return new LoteProduto().listaString(con);
    }
    
    public int gravaProduto(Integer codigo, String descricao, String c, String lote, String ma, String um, double precobase, double preco, double margem, int qtde, int qtdmin)throws ControlException{

        Erro e = new Erro();
        CategoriaProduto cp = new CategoriaProduto();
        LoteProduto lp = new LoteProduto();
        Marca marca = new Marca();
        UnidadeMedida ume = new UnidadeMedida();
        
        if(descricao==null || descricao.isEmpty())
            e.add("Preencha a descrição do produto");
        if(descricao.length()>45)
            e.add("Descrição possui muitos caracteres");
        if(c==null)
            e.add("Selecione um tipo de produto");
        if(ma==null)
            e.add("Selecione uma marca para o produto");
        if(um==null)
            e.add("Selecione a unidade de medida do  produto");
        if(lote==null)
            e.add("Selecione o lote do produto");
        if(qtde<0)
            e.add("insira a quantidade do produto no estoque");
        if(qtdmin <0)
            e.add("insira uma quantidade minima para o produto no estoque");
        
        
        
        if(!e.isTemErro()){
            Produto p = new Produto();
            if(codigo!=null && codigo!=0)
                p.setCodigo(codigo);
            p.setDescricao(descricao);
            p.setPreco(preco);
            p.setPrecoBase(precobase);
            p.setMargemLucro(margem);
            p.setQtdeEstoque(qtde);
            p.setQtdeMin(qtdmin);
            try{
                marca.setNome(ma);
                ume.setAbreviacao(um);
                lp.setNumeroLote(lote);
                cp.setDescricao(c);
                cp = cp.select(con);
                p.setCprod(cp);
                p.setLprod(lp.select(con));
                p.setMarca(marca.select(con));
                p.setUnimed(ume.select(con));
                if(p.getCodigo()!=null && p.getCodigo()!=0)
                    return p.update(con);
                else
                    return p.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public int excluiProduto(Integer codigo) throws ControlException{
        Erro e = new Erro();
        if(codigo==null || codigo==0)
            e.add("Selecione um produto para excluir!");
        if(!e.isTemErro()){
            Produto p = new Produto();
            p.setCodigo(codigo);
            try{
                return p.delete(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public void guardaSelecionado(Produto p){
        new Produto().setProdSelecionado(p);
    }
    
    public Produto retornaSelecionado(){
        return new Produto().getProdSelecionado();
    }
    
    public List<Produto> listaProdutos(Integer codigo, String descricao) throws ControlException{
        Produto p = new Produto();
        p.setCodigo(codigo);
        p.setDescricao(descricao);
        try{
            return p.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
}
