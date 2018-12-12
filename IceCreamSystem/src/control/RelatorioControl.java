package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import sql.Banco;

public class RelatorioControl {
    
    
    private void gerarRelatorio(String sql,String relat) throws SQLException{
    try
        { 
        //sql para obter os dados para o relatorio
          Banco conSing = Banco.getInstancia();
          Connection con = conSing.getConexao();
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery(sql);
          //implementação da interface JRDataSource para DataSource ResultSet
          JRResultSetDataSource jrRS;
          jrRS= new JRResultSetDataSource(rs);
          //chamando o relatório
          String jasperPrint =          
          JasperFillManager.fillReportToFile(relat,null, jrRS);
          JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
          viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
          viewer.setTitle("Relatório");//titulo do relatório
          viewer.setVisible(true);
        } catch (JRException erro){  
            erro.printStackTrace(); 
        }
    }
    
    public void geraRelatorioProduto(String produto, String marca, String tipo, boolean estoque) throws SQLException{
        String sql = "SELECT produto.`prod_codigo` AS produto_prod_codigo," +
        "     produto.`prod_descricao` AS produto_prod_descricao," +
        "     produto.`prod_qtdeEmbalagem` AS produto_prod_qtdeEmbalagem," +
        "     produto.`prod_preco` AS produto_prod_preco," +
        "     produto.`prod_estoque` AS produto_prod_estoque," +
        "     unidade_medida.`um_sigla` AS unidade_medida_um_sigla " +
        "FROM" +
        "     `unidade_medida` unidade_medida INNER JOIN `produto` produto ON unidade_medida.`um_codigo` = produto.`um_codigo` "+
        "     INNER JOIN `marca` m ON m.`mar_codigo` = produto.`mar_codigo` "+
        "     INNER JOIN `tipo_produto` tp ON tp.`tpp_codigo`= produto.`tpp_codigo` ";
        boolean ultimo = false;
        if(produto!=null && !produto.isEmpty()){
            sql+=" where produto.prod_descricao like '%"+produto+"%'";
            ultimo = true;
        }
        if(marca!=null && !marca.isEmpty()){
            if(ultimo)
                sql+=" and m.mar_nome like '%"+marca+"%'";
            else{
                sql+=" where m.mar_nome like '%"+marca+"%'";
            ultimo = true;
            }
        }
        if(tipo!=null && !tipo.isEmpty()){
            if(ultimo)
                sql+=" and tp.tpp_descricao like '%"+tipo+"%'";
            else{
                sql+=" where tp.tpp_descricao like '%"+tipo+"%'";
            ultimo = true;
            }
        }
        if(estoque){
            if(ultimo)
                sql+=" and (produto.prod_estoque = 0 and produto.prod_qtdemin <> 0)";
            else
                sql+=" where (produto.prod_estoque = 0 and produto.prod_qtdemin <> 0)";
        }
        gerarRelatorio(sql, "Relatorios//Produtos.jasper");
    }
    
    public void geraRelatorioDespesas(String dataI, String dataF, boolean vencidas) throws SQLException{
        Date data = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        String datas = fmt.format(data);
        String sql = "SELECT\n" +
        "     contas_pagar.`con_codigo` AS contas_pagar_con_codigo, " +
        "     contas_pagar.`con_data` AS contas_pagar_con_data, " +
        "     contas_pagar.`con_valor` AS contas_pagar_con_valor, " +
        "     contas_pagar.`com_codigo` AS contas_pagar_com_codigo, " +
        "     tipo_conta.`tpc_descricao` AS tipo_conta_tpc_descricao " +
        "FROM " +
        "     `tipo_conta` tipo_conta INNER JOIN `contas_pagar` contas_pagar ON tipo_conta.`tpc_codigo` = contas_pagar.`tpc_codigo` ";
        boolean ultimo = false;
        if(dataI!=null && !dataI.isEmpty()){
            dataI = dataI.substring(6,10)+"/"+dataI.substring(3,5)+"/"+dataI.substring(0,2);
            if(dataF!=null && !dataF.isEmpty()){
                dataF = dataF.substring(6,10)+"/"+dataF.substring(3,5)+"/"+dataF.substring(0,2);
                sql+=" WHERE contas_pagar.con_data between '"+dataI+"' and '"+dataF+"'";
                ultimo = true;
            }else{
                sql+=" WHERE contas_pagar.con_data = '"+dataI+"'";
                ultimo = true;
            }
        }
        if(vencidas){
            if(ultimo)
                sql+=" AND contas_pagar.con_data < '"+datas+"'";
            else{
                sql +=" WHERE contas_pagar.con_data < '"+datas+"'";
            }
        }
        gerarRelatorio(sql, "Relatorios//Despesas.jasper");
    }
    
    public void geraRelatorioVendas(String dataI, String dataF, boolean teste) throws SQLException{
        Date data = new Date();
        boolean ultimo = false;
        String sql = "SELECT\n" +
        "     venda.`ven_codigo` AS venda_ven_codigo, " +
        "     venda.`ven_comanda` AS venda_ven_comanda, " +
        "     venda.`ven_data` AS venda_ven_data, " +
        "     venda.`ven_total` AS venda_ven_total, " +
        "     venda.`tpv_codigo` AS venda_tpv_codigo, " +
        "     venda.`ven_cliNome` AS venda_ven_cliNome, " +
        "     venda.`funcionario_cli_codigo` AS venda_funcionario_cli_codigo, " +
        "     funcionario.`fun_login` AS funcionario_fun_login, " +
        "     tipo_venda.`tpv_descricao` AS tipo_venda_tpv_descricao " +
        "FROM " +
        "     `funcionario` funcionario INNER JOIN `venda` venda ON funcionario.`cli_codigo` = venda.`funcionario_cli_codigo` " +
        "     INNER JOIN `tipo_venda` tipo_venda ON venda.`tpv_codigo` = tipo_venda.`tpv_codigo`";
        if(teste){
            sql+= " WHERE venda.`ven_data` = '"+data+"'";
        }else{
            if(dataI!=null && !dataI.isEmpty() && dataF!=null && !dataF.isEmpty()){
                dataI = dataI.substring(6,10)+"/"+dataI.substring(3,5)+"/"+dataI.substring(0,2);
                dataF = dataF.substring(6,10)+"/"+dataF.substring(3,5)+"/"+dataF.substring(0,2);
                sql+= "WHERE venda.`ven_data` between '"+dataI+"' and '"+dataF+"'";
            }
        }
        gerarRelatorio(sql, "Relatorios//Vendas.jasper");
    }
    
    public void geraRelatorioEntregas(String rest) throws SQLException{
        Date data = new Date();
        String sql = "SELECT" +
        "     venda.`ven_codigo` AS venda_ven_codigo," +
        "     venda.`ven_total` AS venda_ven_total," +
        "     venda.`ven_dtentrega` AS venda_ven_dtentrega," +
        "     cliente.`cli_nome` AS cliente_cli_nome," +
        "     logradouro.`log_endereco` AS logradouro_log_endereco," +
        "     logradouro.`log_numero` AS logradouro_log_numero " +
        "FROM" +
        "     `cliente` cliente INNER JOIN `venda` venda ON cliente.`cli_codigo` = venda.`cliente_cli_codigo`" +
        "     INNER JOIN `logradouro` logradouro ON cliente.`log_codigo` = logradouro.`log_codigo`";
        if(rest.equals("Dia")){
            sql+=" where venda.`ven_dtentrega`= '"+data+"'";
        }
        if(rest.equals("Proximas")){
            sql += " WHERE venda.`ven_dtentrega >= '"+data+"' and venda.`ven_status = 'aberta'";
        }
        if(rest.equals("Efetuadas")){
            sql += " WHERE venda.`ven_dtentrega is not null and venda.`ven_status = 'fechada'";
        }
        
        gerarRelatorio(sql, "Relatorios//Entregas.jasper");
    }
    
    public void gerarRelatorioFornecedor(String nome, String cnpj, String cidade) throws SQLException{
        String sql = "SELECT" +
        "     cidade.`cid_nome` AS cidade_cid_nome," +
        "     fornecedor.`for_codigo` AS fornecedor_for_codigo," +
        "     fornecedor.`for_nomeFantasia` AS fornecedor_for_nomeFantasia," +
        "     fornecedor.`for_cnpj` AS fornecedor_for_cnpj,\n" +
        "     fornecedor.`for_razaosocial` AS fornecedor_for_razaosocial," +
        "     fornecedor.`for_situacao` AS fornecedor_for_situacao," +
        "     fornecedor.`for_telefone` AS fornecedor_for_telefone," +
        "     estado.`est_sigla` AS estado_est_sigla," +
        "     logradouro.`log_endereco` AS logradouro_log_endereco," +
        "     logradouro.`log_numero` AS logradouro_log_numero " +
        "FROM" +
        "     `estado` estado INNER JOIN `cidade` cidade ON estado.`est_codigo` = cidade.`est_codig`" +
        "     INNER JOIN `logradouro` logradouro ON cidade.`cid_codigo` = logradouro.`cid_codigo`" +
        "     INNER JOIN `fornecedor` fornecedor ON logradouro.`log_codigo` = fornecedor.`log_codigo`";
        boolean ultimo = false;
        if(nome!=null && !nome.isEmpty()){
            sql+=" WHERE fornecedor.`for_nomeFantasia` like '%"+nome+"%'";
            ultimo = true;
        }
        if(cnpj!=null && !cnpj.isEmpty()){
            if(ultimo)
                sql+=" and fornecedor.`for_cnpj` = '"+cnpj+"'";
            else{
                sql+=" WHERE fornecedor.`for_cnpj` = '"+cnpj+"'";
                ultimo = true;
            }
        }
        if(cidade!=null && !cidade.isEmpty()){
            if(ultimo)
                sql+=" and cidade.`cid_nome`= '"+cidade+"'";
            else
                sql+=" WHERE cidade.`cid_nome`= '"+cidade+"'";
        }
        gerarRelatorio(sql, "Relatorios//Fornecedor.jasper");
    }
    
    public void geraRelatorioClientes(String nome, String cpf) throws SQLException{
        String sql = "SELECT " +
        "     cliente.`cli_codigo` AS cliente_cli_codigo," +
        "     cliente.`cli_nome` AS cliente_cli_nome," +
        "     cliente.`cli_celular` AS cliente_cli_celular," +
        "     cliente.`cli_dtnasc` AS cliente_cli_dtnasc," +
        "     cliente.`cli_sexo` AS cliente_cli_sexo," +
        "     cliente.`cli_telefone` AS cliente_cli_telefone," +
        "     cliente.`cli_email` AS cliente_cli_email," +
        "     logradouro.`log_endereco` AS logradouro_log_endereco," +
        "     logradouro.`log_numero` AS logradouro_log_numero," +
        "     cidade.`cid_nome` AS cidade_cid_nome," +
        "     estado.`est_sigla` AS estado_est_sigla " +
        "FROM" +
        "     `logradouro` logradouro INNER JOIN `cliente` cliente ON logradouro.`log_codigo` = cliente.`log_codigo`" +
        "     INNER JOIN `cidade` cidade ON logradouro.`cid_codigo` = cidade.`cid_codigo`" +
        "     INNER JOIN `estado` estado ON cidade.`est_codig` = estado.`est_codigo`";
        boolean ultimo = false;
        if(nome!=null && !nome.isEmpty()){
            sql +=" WHERE cliente.`cli_nome` like '%"+nome+"%'";
            ultimo = true;
        }
        if(cpf!=null && !cpf.isEmpty()){
            if(ultimo)
                sql+=" AND cliente.`cli_cpf`= '"+cpf+"'";
            else
               sql+=" WHERE cliente.`cli_cpf`= '"+cpf+"'"; 
        }
        
        gerarRelatorio(sql, "Relatorios//Clientes.jasper");
    }
    
    public void geraContasReceber(Boolean estado, String dataI, String dataF) throws SQLException{
        String sql = "SELECT " +
        "     contas_receber.`conr_codigo` AS contas_receber_conr_codigo," +
        "     contas_receber.`conr_valor` AS contas_receber_conr_valor," +
        "     venda.`ven_data` AS venda_ven_data," +
        "     venda.`ven_status` AS venda_ven_status," +
        "     venda.`tpv_codigo` AS venda_tpv_codigo," +
        "     venda.`funcionario_cli_codigo` AS venda_funcionario_cli_codigo," +
        "     tipo_venda.`tpv_descricao` AS tipo_venda_tpv_descricao," +
        "     funcionario.`fun_login` AS funcionario_fun_login" +
        "     FROM" +
        "     `venda` venda INNER JOIN `contas_receber` contas_receber ON venda.`ven_codigo` = contas_receber.`ven_codigo`" +
        "     INNER JOIN `tipo_venda` tipo_venda ON venda.`tpv_codigo` = tipo_venda.`tpv_codigo`" +
        "     INNER JOIN `funcionario` funcionario ON venda.`funcionario_cli_codigo` = funcionario.`cli_codigo`";
        boolean ultimo = false;
        if(dataI!=null && !dataI.isEmpty())
            dataI = dataI.substring(6,10)+"/"+dataI.substring(3,5)+"/"+dataI.substring(0,2);
        if(dataF!=null && !dataF.isEmpty())
            dataF = dataF.substring(6,10)+"/"+dataF.substring(3,5)+"/"+dataF.substring(0,2);
        if(estado!=null && estado == true){
            if(dataI!=null && !dataI.isEmpty() && dataF!=null && !dataF.isEmpty())
                sql+=" WHERE venda.`ven_data` between '"+dataI+"' and '"+dataF+"' and venda.`ven_status` = 'fechada'";
            else
                sql+=" WHERE venda.`ven_status` = 'fechada'";
        }
        if(estado!=null && estado == false){
            if(dataI!=null && !dataI.isEmpty() && dataF!=null && !dataF.isEmpty())
                sql+=" WHERE venda.`ven_data` between '"+dataI+"' and '"+dataF+"' and venda.`ven_status` = 'aberta'";
            else
                sql+=" WHERE venda.`ven_status` = 'aberta'";
        }
        if(estado == null){
            if(dataI!=null && !dataI.isEmpty() && dataF!=null && !dataF.isEmpty())
                sql+=" WHERE venda.`ven_data` between '"+dataI+"' and '"+dataF+"'";
        }
        
        gerarRelatorio(sql, "Relatorios//ContasReceber.jasper");
    }
    
    public void geraRelatorioCompras(Boolean estado, String dataI, String dataF) throws SQLException{
        String sql = "SELECT " +
        "     compra.`com_codigo` AS compra_com_codigo," +
        "     compra.`com_data` AS compra_com_data," +
        "     compra.`com_total` AS compra_com_total," +
        "     fornecedor.`for_nomeFantasia` AS fornecedor_for_nomeFantasia," +
        "     fornecedor.`for_cnpj` AS fornecedor_for_cnpj" +
        "     FROM" +
        "     `fornecedor` fornecedor INNER JOIN `compra` compra ON fornecedor.`for_codigo` = compra.`for_codigo`"+
        "      INNER JOIN contas_pagar contas ON contas.com_codigo = compra.`com_codigo`";
        boolean ultimo = false;
        if(dataI!=null && !dataI.isEmpty())
            dataI = dataI.substring(6,10)+"/"+dataI.substring(3,5)+"/"+dataI.substring(0,2);
        if(dataF!=null && !dataF.isEmpty())
            dataF = dataF.substring(6,10)+"/"+dataF.substring(3,5)+"/"+dataF.substring(0,2);
        if(estado!=null && estado == true){
            if(dataI!=null && !dataI.isEmpty() && dataF!=null && !dataF.isEmpty())
                sql+=" WHERE compra.`com_data` between '"+dataI+"' and '"+dataF+"' and contas.con_valorpago = contas.con_valor";
            else
                sql+=" WHERE contas.con_valorpago = contas.con_valor";
        }
        if(estado!=null && estado == false){
            if(dataI!=null && !dataI.isEmpty() && dataF!=null && !dataF.isEmpty())
                sql+=" WHERE compra.`com_data` between '"+dataI+"' and '"+dataF+"' and contas.con_valorpago < contas.con_valor";
            else
                sql+=" WHERE contas.con_valorpago < contas.con_valor";
        }
        if(estado == null){
            if(dataI!=null && !dataI.isEmpty() && dataF!=null && !dataF.isEmpty())
                sql+=" WHERE compra.`com_data` between '"+dataI+"' and '"+dataF+"'";
        }
        gerarRelatorio(sql, "Relatorios//Compras.jasper");
    }
    
}
