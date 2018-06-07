package control;

import entidade.Empresa;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;
import sql.Banco;
import util.Erro;

public class EmpresaControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public Empresa retornaEmpresa()throws ControlException{
        try{
            return new Empresa().select(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public int gravaEmpresa(Integer codigo, String nome, String razao, String cnpj, String ie, String telefone, Date data) throws ControlException{
        Empresa emp = new Empresa();
        Erro e = new Erro();
        
        if(nome==null || nome.isEmpty())
            e.add("Preencha o campo nome fantasia");
        if(nome.length()>45)
            e.add("Campo nome fantasia possui muitos caracteres");
        if(razao==null || razao.isEmpty())
            e.add("Preencha o campo razão social");
        if(razao.length()>45)
            e.add("Campo razão social possui muitos caracteres");
        if(cnpj==null || cnpj.isEmpty())
            e.add("Preencha o campo cnpj");
        if(cnpj.length()>20)
            e.add("Campo cnpj possui muitos caracteres");
        if(ie==null || ie.isEmpty())
            e.add("Preencha o campo ie");
        if(ie.length()>20)
            e.add("Campo ie possui muitos caracteres");
        if(telefone==null || telefone.isEmpty())
            e.add("Preencha o campo telefone");
        if(telefone.length()>14)
            e.add("Campo telefone possui muitos caracteres");
        if(data==null)
            e.add("Preencha o campo data");
        
        if(!e.isTemErro()){
            emp.setNome(nome);
            emp.setRazaoSocial(razao);
            emp.setCnpj(cnpj);
            emp.setIe(ie);
            emp.setTelefone(telefone);
            emp.setDataFundação(data);
            if(codigo!=0 && codigo!=null)
                emp.setCodigo(codigo);
            try{
                if(emp.getCodigo()!=null && emp.getCodigo()!=0)
                    return emp.update(con);
                else
                    return emp.insert(con);
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
}
