/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.uteis;

import cadastrodeprodutoalternativo.control.ClienteControl;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class Conversor {
    
    public static String dataBancoParaUsuario( Date data){
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormatada.format(data);
    }
    
    public static Date dataUsuarioParaBanco( String data){
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
   
        try {
            //Converter de string para Data util
            java.util.Date dataUtil = dataFormatada.parse(data);
            
            //Converter de data util para data sql
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
            
            //Retorna data sql
            return dataSql;
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
        
    }
            
}
