package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            if(dataF!=null && !dataF.isEmpty()){
                sql+=" WHERE contas_pagar.con_data between '"+dataI+"' and '"+dataF+"'";
                ultimo = true;
            }else{
                
            }
        }
        gerarRelatorio(sql, "Relatorios//Despesas.jasper");
    }
    
}
