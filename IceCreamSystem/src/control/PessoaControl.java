package control;

import entidade.Cidade;
import entidade.Cliente;
import entidade.Estado;
import entidade.Logradouro;
import exception.ControlException;
import exception.EntidadeException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sql.Banco;
import util.Erro;
import util.Validadores;

public class PessoaControl {
    
    Banco conSing = Banco.getInstancia();
    Connection con = conSing.getConexao();
    
    public Logradouro montaLogradouro(String endereco, String numero, String bairro, String cep, Cidade cidade) throws ControlException{
        Erro e = new Erro();
        Logradouro log = new Logradouro();
        int chave;
        
        if(endereco==null || endereco.isEmpty())
            e.add("Endereco incorreto!");
        if(numero==null || numero.isEmpty())
            e.add("Numero incorreto");
        if(bairro==null || bairro.isEmpty())
            e.add("Bairro incorreto");
        if(cep==null || cep.isEmpty())
            e.add("CEP incorreto");
        if(cidade==null || cidade.getNome()==null || cidade.getNome().isEmpty())
            e.add("Cidade incorrerta");
        
        if(!e.isTemErro()){
            if(endereco!=null && !endereco.isEmpty())
                log.setEndereco(endereco);
            if(numero!=null && !numero.isEmpty())
                log.setNumero(numero);
            if(bairro!=null && !bairro.isEmpty())
                log.setBairro(bairro);
            if(cep!=null && !cep.isEmpty())
                log.setCep(cep);
            if(cidade!=null && cidade.getNome()!=null && !cidade.getNome().isEmpty())
                log.setCidade(cidade);
            
           
            return log;
        }
        return null;
    }
    
    public int gravarCliente(Integer codigo, String nome, String cpf, String rg, String telefone, String celular, Date dataNasc, String sexo, String email, Logradouro log) throws ControlException, SQLException{
        Erro e = new Erro();
        Validadores v = new Validadores();
        
        if(nome==null || nome.isEmpty())
            e.add("Nome Incorreto!");
        if(!v.validanome(nome))
            e.add("Nome possui muitos caracteres ou caracteres invalidos");
        if(cpf==null || cpf.isEmpty())
            e.add("CPF incorreto");
        if(!v.ValidaCPF(cpf))
            e.add("CPF incorreto ou invalido");
        if(rg==null || rg.isEmpty())
            e.add("RG incorreto");
        if(telefone==null || telefone.isEmpty())
            e.add("Telefone Incorreto!");
        if(celular==null || celular.isEmpty())
            e.add("Celular incorreto");
        if(dataNasc==null)
            e.add("Data de Nascimento incorreto");
        if(sexo==null || sexo.isEmpty())
            e.add("Sexo Incorreto!");
        if(email==null || email.isEmpty())
            e.add("Email incorreto");
        
        if(!e.isTemErro()){
            Cliente c = new Cliente();
            if(codigo!=null && codigo!=0)
                c.setCodigo(codigo);
            c.setNome(nome);
            c.setCpf(cpf);
            c.setRg(rg);
            c.setTelefone(telefone);
            c.setCelular(celular);
            c.setDtNasc(dataNasc);
            c.setSexo(sexo.charAt(0));
            c.setEmail(email);
            try{
                con.setAutoCommit(false);
                int chave = log.insert(con);
                log.setCodigo(chave);
                c.setLogradouro(log);
                c.insert(con);
                con.commit();
                return 1;
            }catch(EntidadeException ex){
                con.rollback();
                throw new ControlException(ex.getMessage());
            }
        }
        
        return 0;
    }
    
    public List<Cidade> buscaCidades(Estado est) throws ControlException{
        try{
            Cidade c = new Cidade();
            c.setEstado(est);
            return c.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public List<Estado> buscaEstados() throws ControlException{
        try{
            return new Estado().lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
}
