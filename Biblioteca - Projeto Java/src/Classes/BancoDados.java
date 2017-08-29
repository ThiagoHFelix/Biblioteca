
package Classes;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLTimeoutException;

/**
 *
 * @author Thiago Henrique Felix
 * 
 * // Classe responsavel por criar, manter e fechar a conecção com o gerenciador de banco de dados firebird até o momento.
 */
public class BancoDados {
    
    private Connection firebirdConnection;
   //private String host;
    private String user;
    private String pass;
    private String url;
    private String driver;
    private boolean isConnected=false;
    
   /**
    *  Construtor padrão
     *
     * @param user Usuário do gerenciador de banco de dados a ser usado
     * @param pass Senha do usuário do gerenciador de banco de dados
     * @param url Caminho até o banco de dados 
     * @param driver Driver do banco de dados
     * 
    */ 
   public BancoDados( String user, String pass, String url, String driver){  
    
   // this.host = host;
    this.user = user;
    this.pass = pass;
    this.url = url;
    this.driver = driver;
   // this.portNumber = portNumber;
    
 }//CONSTRUTOR PADRÃO     
   
   /**
    *  Retorna o pacote onde se localiza o driver do firebird.
    * @return Retorna a string do driver
    */
   public static String getFirebirdDriver(){
       return "org.firebirdsql.jdbc.FBDriver";
   }//GET FIREBIRD DRIVER
  
   /**
    * Retorna o pacote onde se localiza o driver do firebird
    * @return Retorna a string do driver 
    */
   public static String getMySQLDriver(){
   return "com.mysql.jdbc.Driver";
   }//GET MYSQL DRIVER
   
   
   /**
    *  Cria conecção com o banco de dados
    */
   public void conect()
    {
   
    //  String portNumber = "3050";
    //  String url;     
    //  url = "jdbc:firebirdsql:"+this.host+"/"+portNumber+":"+this.database;
    // Class.forName("org.firebirdsql.jdbc.FBDriver");
       
        try{
           
        Class.forName(driver);
        this.firebirdConnection = DriverManager.getConnection(url,user, pass);
        isConnected = true;
       
       
       }//Try
       catch(SQLTimeoutException sqlEx){
        
            //TODO Fazer o tratamento correto da exception
        System.out.println("Desculpe, time out exception");
        
        
        }//Catch
        catch(ExceptionInInitializerError exIni){
        
           //TODO Fazer o tratamento correto da exception 
            
            
        }//Catch       
        catch (Exception ex) {

            //XXX Tratamento de todas as exception em geral 
            System.out.println(ex.getMessage());
            System.out.println(" Erro ao conectar com o banco de dados \n Error: "+ex.getMessage());

        }//Catch

   }//Conect
     
     /**
      * Método informa se há uma conecção ativa no momento.
      * @return true caso esteja conectado.
      */
     public boolean isConnected(){ return isConnected; }// IS connected  
   
     
     /**
      * Método disconecta do gerenciador de banco de dados.
      * @throws SQLException Banco de dados não esta conectado 
      */
     public void disconnect() throws SQLException
     {
         
     if(isConnected)
          firebirdConnection.close();
     else 
        throw new SQLException("O banco de dados não está conectado.");
    
      }//Disconnect
     
     
     /**
      * 
      * @param Query Código DML a ser executado no gerenciador de banco de dados
      * @return ResultSet caso operação bem sucedida, null caso contrario
      * @throws SQLException SQLException Retorna uma SQLException em caso de erro 
      */
     public ResultSet executarDML(String Query) throws SQLException
     {
     
      Statement st;
      ResultSet rs;

          st = firebirdConnection.createStatement();
          rs = st.executeQuery(Query);
          return rs;  

     }//Executar
     
     
     /**
      * 
      * @param Query Código DDL a ser executado no gerenciador de banco de dados  
      * @return Retorna a quantidade de Tuplas afetadas 
      * @throws SQLException Retorna uma SQLException em caso de erro 
      */
     public int executarDDL(String Query) throws SQLException{
     
         Statement st;
      
         st = firebirdConnection.createStatement();
         return  st.executeUpdate(Query);
     
     }//ExecutarDDL
     
     
     //GETERS E SETERS 
     
    public String getUser() {
        return user;
    }//getuser

    public void setUser(String user) {
        this.user = user;
    }//setuser

    public String getPass() {
        return pass;
    }//getpass

    public void setPass(String pass) {
        this.pass = pass;
    }//setpass

    public String getUrl() {
        return url;
    }//geturl   

    public void setUrl(String url) {
        this.url = url;
    }//seturl

    public String getDriver() {
        return driver;
    }//getdriver

    public void setDriver(String driver) {
        this.driver = driver;
    }//setdriver
    
}//Class 
