

package Classes;


import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.Writer;


/**
 *
 * @author Thiago Henrique Felix
 * Classe responsavel por todos os registros do sistema 
 */
public class RegistroLog {
    
    private String nomeArquivo;
    private String caminhoArquivo;    
    
    /**
     * Método construtor padrão 
     * @param nomeArquivo Nome do arquivo de log 
     * @throws IOException Retorna uma exception do tipo io caso não consiga pegar o caminho da pasta do sistema 
     */
    public RegistroLog(String nomeArquivo) throws IOException {
    
    this.nomeArquivo = nomeArquivo; 
    
    try
    {
        caminhoArquivo = new File(".").getCanonicalPath();
    
    }
    catch(IOException ioEx)
    {    
       throw new IOException("Erro ao peger o caminho padrão da pasta padrão. " +ioEx.getMessage());
    
    }//Catch    
    
    }//Registro Log 
    
    
    /**
     * Construtor parâmetrizado 
     * @param nomeArquivo
     * @param caminhoArquivo 
     */
    public RegistroLog(String nomeArquivo, String caminhoArquivo){
        
        this.nomeArquivo = nomeArquivo;
        this.caminhoArquivo = caminhoArquivo;
    
    }//Registro Log 
    
    /**
     * Registra o log em um arquivo, se o arquivo não existir será criado um
     * @param texto Texto que será escrito no arquivo log
     * @exception É disparada quando ocorrer qualquer tipo de erro ao criar ou modificar o arquivo.
     */
    public void registraLog(String texto) throws IOException
    {
        
        BufferedWriter arquivoLog;
        
    try
    {
        
         arquivoLog = new BufferedWriter(new FileWriter(caminhoArquivo + "/"+nomeArquivo ));
         
         arquivoLog.append("\n ================================================================================================= \n ");
         
         arquivoLog.append(texto);
   
         arquivoLog.close();
    
    }//Try
    catch(IOException ex)
    {
    
      throw new IOException("Erro ao criar ou modificar o arquivo. \n Error: "+ex.getMessage());
    
    }//Catch
    
    
    }//Registra Log 
    
}//Class
