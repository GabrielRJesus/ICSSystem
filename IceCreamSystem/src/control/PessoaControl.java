package control;

import entidade.Cidade;
import entidade.Cliente;
import entidade.Estado;
import entidade.Funcionario;
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
                if(c.getCodigo()!=null && c.getCodigo()!=0)
                    c.update(con);
                else
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
    
    public int gravarFuncionario(Integer codigo, String nome, String cpf, String rg, String telefone, String celular, Date dataNasc, String sexo, String email, Logradouro log,
            String login, String senha, Date dtadm, Date dtdem, double salario, String cargo) throws ControlException, SQLException, EntidadeException{
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
        if(rg.length()>14)
            e.add("Campo RG possui muitos caracteres");
         if(telefone==null || telefone.isEmpty())
            e.add("telefone incorreto");
        if(telefone.length()>14)
            e.add("Campo telefone possui muitos caracteres");
        if(celular.length()>15)
            e.add("Campo celular possui muitos caracteres");
        if(sexo==null || sexo.isEmpty())
            e.add("Selecione um sexo!");
        if(email.length()>100)
            e.add("Campo email possui muitos caracteres");
        if(!v.logindisponivel(login))
            e.add("Login ja esta sendo utilizado!");
        if(login==null || login.isEmpty())
            e.add("login incorreto");
        if(login.length()>20)
            e.add("Campo login possui muitos caracteres");
        if(senha==null || senha.isEmpty())
            e.add("senha incorreto");
        if(senha.length()>14)
            e.add("Campo senha possui muitos caracteres");
        if(cargo==null || cargo.isEmpty())
            e.add("cargo incorreto");
        if(cargo.length()>14)
            e.add("Campo cargo possui muitos caracteres");
        if(!cargo.equalsIgnoreCase("Administrador") && !cargo.equalsIgnoreCase("Gerente") && !cargo.equalsIgnoreCase("Funcionario") )
            e.add("cargo incorreto");
        if(dtadm==null)
            e.add("Selecione a data de admissão!");
        
        
        if(!e.isTemErro()){
            Funcionario f = new Funcionario();
            if(codigo!=null && codigo!=0)
                f.setCodigo(codigo);
            f.setNome(nome);
            f.setCpf(cpf);
            f.setRg(rg);
            f.setTelefone(telefone);
            f.setCelular(celular);
            f.setDtNasc(dataNasc);
            f.setSexo(sexo.charAt(0));
            f.setEmail(email);
            f.setLogin(login);
            f.setSenha(senha);
            f.setDtAdmiss(dtadm);
            f.setDtDemiss(dtdem);
            f.setSalario(salario);
            f.setCargo(cargo);
            if(cargo.equalsIgnoreCase("Administrador"))
                f.setNivel(1);
            if(cargo.equalsIgnoreCase("Gerente"))
                f.setNivel(2);
            if(cargo.equalsIgnoreCase("Funcionario"))
                f.setNivel(3);
            try{
                con.setAutoCommit(false);
                int chave = log.insert(con);
                log.setCodigo(chave);
                f.setLogradouro(log);
                f.insert(con);
                con.commit();
                return 1;
            }catch(EntidadeException ex){
                con.rollback();
                throw new ControlException(ex.getMessage());
            }
        }
        return 0;
    }
    
    public Funcionario verificalogin(String login) throws EntidadeException{
        Funcionario f = new Funcionario();
        f.setLogin(login);
        f = f.select(con);
        return f;
    }
    
    public Funcionario verificaUsuario(String login, String senha) throws EntidadeException{
        Funcionario f = new Funcionario();
        f.setLogin(login);
        f.setSenha(senha);
        f = f.select(con);
        return f;
    }
    
    public void guardaSelecionado(Funcionario f){
        new Funcionario().setFuncLogado(f);
    }
    
    public void guardaBusca(Funcionario f){
        new Funcionario().setFuncSelecionado(f);
    }
    
    public Funcionario retornaFuncBusca(){
        return new  Funcionario().getFuncSelecionado();
    }
    
    public List<Funcionario> buscaFuncionario(Integer codigo, String nome, String cpf, String login,  String cargo, int nivel) throws ControlException{
        try{
            Funcionario f = new Funcionario();
            f.setCodigo(codigo);
            f.setNome(nome);
            f.setCpf(cpf);
            f.setLogin(login);
            f.setCargo(cargo);
            f.setNivel(nivel);
            return f.listaFunc(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public List<Cliente> buscaCliente(Integer codigo, String nome, String cpf) throws ControlException{
        try{
            Cliente c = new Cliente();
            c.setCodigo(codigo);
            c.setNome(nome);
            c.setCpf(cpf);
            return c.lista(con);
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());
        }
    }
    
    public void guardaBusca(Cliente c){
        new Cliente().setCliSelecionado(c);
    }
    
    public Cliente retornaCliBusca(){
        return new  Cliente().getCliSelecionado();
    }
}
