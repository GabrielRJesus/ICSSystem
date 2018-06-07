package util;

import control.PessoaControl;
import entidade.Funcionario;
import exception.ControlException;
import exception.EntidadeException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author gabri
 */
public class Validadores {
    
    private char[] numeros = {'1','2','3','4','5','6','7','8','9','0'};
    private char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
    private char[] alfabeto2 = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','1','2','3','4','5','6','7','8','9','0'};
    
    public boolean validacamposlogin(String login){
        int cont = 0;
        boolean achou = false;
        if(login.length()>10)
            return false;
        for(int i=0; i<login.length(); i++){
            for(int k=0; k<alfabeto.length; k++){
                if(login.charAt(i) == alfabeto[k] && achou == false){
                    cont++;
                    achou = true;
                } 
            }
            achou = false;
        }
        if(cont == login.length())
            return true;
        else
            return false;
    }
    
    public boolean validacampossenha(String senha){
        if(senha.length()>8)
            return false;
        else
            return true;
    }
    
    public boolean ValidaCPF(String CPF) {
        CPF = CPF.replace(".", "");
        CPF = CPF.replace("-", "");
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);            
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
    
    public String mascaratelefone(String telefone){
        String ddd;
        String ini;
        String fim;
        ddd = telefone.substring(0, 2);
        ini = telefone.substring(2,6);
        fim = telefone.substring(6, 10);
        return "("+ddd+")"+ini+"-"+fim;
    }
    
    public String mascaracelular(String cel){
        String ddd;
        String ini;
        String fim;
        ddd = cel.substring(0, 2);
        ini = cel.substring(2,7);
        fim = cel.substring(7, 11);
        return "("+ddd+")"+ini+"-"+fim;
    }
    
    public String mascaracpf(String cpf){
        String ini;
        String meio;
        String fim;
        String dig;
        ini = cpf.substring(0, 3);
        meio = cpf.substring(3, 6);
        fim = cpf.substring(6, 9);
        dig = cpf.substring(9, 11);
        return ini+"."+meio+"."+fim+"-"+dig;
    }
    
    public boolean validanome(String nome){
        int cont = 0;
        String nomeminusculo = nome.toLowerCase();
        boolean achou = false;
        if(nome.length()>80)
            return false;
        for(int i=0; i<nome.length(); i++){
            for(int k=0; k<alfabeto.length; k++){
                if(nomeminusculo.charAt(i) == alfabeto[k] && achou == false){
                    cont++;
                    achou = true;
                } 
            }
            achou = false;
        }
        if(cont == nome.length())
            return true;
        else
            return false;
    }
    
    public boolean validaendereco(String end){
        int cont = 0;
        boolean achou = false;
        String endminusculo = end.toLowerCase();
        if(end.length()>100)
            return false;
        for(int i=0; i<end.length(); i++){
            for(int k=0; k<alfabeto2.length; k++){
                if(endminusculo.charAt(i) == alfabeto2[k] && achou == false){
                    cont++;
                    achou = true;
                } 
            }
            achou = false;
        }
        if(cont == end.length())
            return true;
        else
            return false;
    }
    
    public boolean validatelefone(String telefone){
        boolean achou = false;
        int cont=0;
        if(telefone.length()>10)
            return false;
        for(int i=0; i<telefone.length(); i++){
            for(int k=0; k<alfabeto.length; k++){
                if(telefone.charAt(i) != alfabeto[k] && achou == false){
                    cont++;
                    achou = true;
                } 
            }
            achou = false;
        }
        if(cont == telefone.length())
            return true;
        else
            return false;
    }
    
    public boolean validaCelular(String cel){
        boolean achou = false;
        int cont=0;
        if(cel.length()>12)
            return false;
        for(int i=0; i<cel.length(); i++){
            for(int k=0; k<alfabeto.length; k++){
                if(cel.charAt(i) != alfabeto[k] && achou == false){
                    cont++;
                    achou = true;
                } 
            }
            achou = false;
        }
        if(cont == cel.length())
            return true;
        else
            return false;
    }
    
    public boolean validaemail(String email){
        if(email.length()>100){
            return false;
        }
        else
            return true;
    }
    
    public boolean logindisponivel(String login) throws ControlException, EntidadeException{
        PessoaControl pc = new PessoaControl();
        Funcionario f = new Funcionario();
        f = pc.verificalogin(login);
        if(f==null)
            return true;
        else
            return false;
    }
    
    public boolean validadtadmin(LocalDate data){
        LocalDate localdate1 = LocalDate.now();
        if(data.isBefore(localdate1))
            return true;
        else
            return false;
            
    }
    
    public boolean validadtdemis(LocalDate dtadmis, LocalDate dtdemis){
        LocalDate hoje = LocalDate.now();
        if(dtdemis.isAfter(hoje) && dtdemis.isBefore(dtadmis))
            return true;
        else
            return false;
    }
    
//    public boolean validaadm() throws ControlException{
//        FuncionarioControl fc = new FuncionarioControl();
//        List<Funcionario> lista = new ArrayList<>();
//        lista = fc.listaadm();
//        if(lista.size()>1)
//            return true;
//        else
//            return false;
//    }
    
    public boolean validaqtde(String valor){
        int cont = 0;
        boolean achou = false;
        for(int i=0; i<valor.length(); i++){
            for(int k=0; k<numeros.length; k++){
                if(valor.charAt(i) == numeros[k] && achou == false){
                    cont++;
                    achou = true;
                } 
            }
            achou = false;
        }
        if(cont == valor.length())
            return true;
        else
            return false;
    }
    
}
