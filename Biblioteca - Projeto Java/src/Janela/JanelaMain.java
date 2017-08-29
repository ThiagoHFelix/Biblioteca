package Janela;

//<editor-fold defaultstate="collapsed" desc="Importações ao projeto">
import Classes.BancoDados;
import Classes.RegistroLog;
import java.awt.Color;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
            
//com.jtattoo.plaf.smart.SmartLookAndFeel
//</editor-fold>
/**
 *
 * @author Thiago Henrique Felix
 */
public class JanelaMain extends JFrame {

    private JButton btIniciar, btMostrar, btCancelar;
    private JTextField tfID, tfSenha;
    private JPasswordField pfSenha;
    private BancoDados banco;
    // private RegistroLog logLogin;
    private JLabel lbAviso, jlLogotipo, loading, logo;
    private String userName;
    private final JFrame estaJanela = this;
    //Conection String

    /**
     * Construtor padrão da classe
     */
    public JanelaMain() {

        listLookAndFeel();

        setlookandfeel();
        /* XXX Decidir qual look and feel utilizar ou deixar a escolha do usuario.*/
        
        
        inicializaComponentes();
        addComponentes();
        setComponentes();
        eventos();

        tfID.requestFocus();// XXX Foco direcionado ao ID 

        conectaBanco(); // Conecta ao banco de dados  

    }//Construtor padrão

    //Metodo deve ser passado para a classe de banco de dados.
    // jdbc:firebirdsql:localhost/3050:C:/Banco de dados/LOGIN.FDB
    /**
     * Metodo responsável por estabelecer conexão com o banco de dados Caso o
     * Sistema operacional seja Linux o programa tentará se conectar ao
     * Firebird, se Windows será o MySQL.
     */
    private void conectaBanco() {

        if (System.getProperty("os.name").equals("Linux")) {
            banco = new BancoDados("SYSDBA", "masterkey", "jdbc:firebirdsql:localhost/3050:/home/thiagofelix/Banco de dados/bancoBibliotecaFirebird.fdb", BancoDados.getFirebirdDriver());
        } else {
            banco = new BancoDados("root", "masterkey", "jdbc:mysql://localhost/biblioteca_banco", BancoDados.getMySQLDriver());
        }

        banco.conect();

        if (banco.isConnected()) {
            System.out.println("Conectado com o banco de dados com sucesso.");
        }//If 
        else {
            lbAviso.setText("Desculpe, não foi possível conectar a base de dados.");
        }//Else

    }//Conecta Banco

    /**
     * Método responsável pela validação de dados no login
     *
     * @return Retorna true caso a lidação esteja correta, caso contrario
     * retorna false;
     */
    private boolean validaDadosLogin() {

        if (tfID.getText().trim().equals("")) {
            lbAviso.setText(" Campo  \"ID\" está em branco. ");
            return false;
        } else if (String.valueOf(pfSenha.getPassword()).trim().equals("")) {
            lbAviso.setText(" O campo \"SENHA\" está em branco. ");
            return false;
        }

        return true;

    }//Valida dados 

    /**
     * Método responsavel pela autenticação de usuário
     */
    private boolean verificaLogin() {

        //XXX Sem a validação dos campos, não há o porque conferir no banco de dados =D
        if (!validaDadosLogin()) {
            return false;
        }//IF

        try {
            ResultSet resultadoBanco = banco.executarDML("select * from login");

            while (resultadoBanco.next()) {

                if (tfID.getText().trim().toUpperCase().equals(resultadoBanco.getString("idlogin").toUpperCase())) {
                    if (String.valueOf(pfSenha.getPassword()).trim().equals(resultadoBanco.getString("senha"))) { // TODO Abrir uma nova janela menu após aprovar o login  

                        System.out.println(" Login bem sucedido. ");

                        ResultSet resultName = banco.executarDML("select * from administrador where codadministrador = " + resultadoBanco.getInt("codadm"));
                        resultName.next();//Movo o para o primeiro resultado 
                        userName = resultName.getString("nomeadministrador");
                        return true;

                    }//IF              
                }
            }//While

            lbAviso.setText("ID ou Senha incorreto ");

        }//Try
        catch (SQLException SQLex) {

            System.out.println("Erro ao buscar dados no banco \nError: " + SQLex.getMessage());

        }//Catch

        return false;

    }//Verifica Login

    /**
     * Método responsavel por todos os eventos
     */
    private void eventos() {

        //<editor-fold defaultstate="collapsed" desc="JFrame">
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {

                try {

                    banco.disconnect();
                    System.out.println("Desconectado do banco de dados com sucesso. ");

                }//Try
                catch (Exception ex) {

                    //XXX Banco de dados não estava conectador =D
                    System.out.println("O banco de dados não estava conectado. ");

                }//Catch
            }//Windows Closing
        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc=" btMostrar">
        btMostrar.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {

                pfSenha.setVisible(false);
                tfSenha.setText(String.valueOf(pfSenha.getPassword()));
                tfSenha.setVisible(true);

            }//Mouse pressed

            public void mouseReleased(MouseEvent me) {

                pfSenha.setText(tfSenha.getText());
                pfSenha.setVisible(true);
                tfSenha.setVisible(false);

            }//Mouse Released

        });
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" btCancelar">
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                tfID.setText("");
                tfSenha.setText("");
                pfSenha.setText("");
                lbAviso.setText("");

            }// Action Performed
        });
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="btIniciar">
        btIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciar();
            }
        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="pfSenha">
        pfSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciar();
            }
        });

        pfSenha.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent ke) {

                tfSenha.setText(String.valueOf(pfSenha.getPassword()));

            }//KEY TYPED

        });
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="tfID">
        tfID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                iniciar();
            }
        });

//</editor-fold>
    }//Eventos

    /**
     * Inicializa todos os componentes na memória
     */
    private void inicializaComponentes() {

        //<editor-fold defaultstate="collapsed" desc="Instância de objetos">
        btIniciar = new JButton("Iniciar");
        btCancelar = new JButton("Cancelar");
        tfID = new JTextField("");
        pfSenha = new JPasswordField("");
        btMostrar = new JButton("");
        tfSenha = new JTextField("");
        lbAviso = new JLabel("");
        jlLogotipo = new JLabel("");
        loading = new JLabel("");
        logo = new JLabel("");
//</editor-fold> 

    }//INICIALIZA COMPONENTES

    /**
     * Altera as propriedades dos componentes
     */
    private void setComponentes() {

        //<editor-fold defaultstate="collapsed" desc="JFrame">
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(System.getProperty("user.dir") + "/Icons/book.png").getImage());
        setSize(900, 700);
        setResizable(false);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null); // Realoca a janela para o centro da tela =D
        setTitle("Login - Centro de Controle da Biblioteca");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="TextFild ID ">
        tfID.setSize(280, 35);
        tfID.setLocation(290, 270);

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JTextField Senha">
        tfSenha.setSize(tfID.getSize());
        tfSenha.setLocation(tfID.getLocation().x, tfID.getLocation().y + 58);
        tfSenha.setVisible(false);
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" PasswordField Senha ">
        pfSenha.setSize(tfSenha.getSize());
        pfSenha.setLocation(tfSenha.getLocation());

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JLabel lbAviso">
        lbAviso.setHorizontalAlignment(SwingConstants.CENTER);
        lbAviso.setSize(pfSenha.getSize());
        lbAviso.setLocation(pfSenha.getLocation().x, pfSenha.getLocation().y + 28);
        lbAviso.setForeground(Color.RED);
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="JLabel Loading">
        loading.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Imagens/loadingCirculo.gif"));
        loading.setSize(140, 150);
        loading.setLocation(tfID.getLocation().x + 70, tfID.getLocation().y - 150);
        loading.setVisible(false);
        loading.setHorizontalAlignment(SwingConstants.CENTER);

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JLabel jlLogoTipo">
        jlLogotipo.setHorizontalAlignment(SwingConstants.CENTER);
        jlLogotipo.setLocation(480, 160);
        jlLogotipo.setSize(500, 500);
        jlLogotipo.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Imagens/source.gif"));
        jlLogotipo.setVisible(false);

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Button Iniciar">
        btIniciar.setSize(130, 35);
        btIniciar.setLocation(tfID.getLocation().x, 386);
        //  btIniciar.setBackground(Color.BLUE);
        // btIniciar.setForeground(Color.WHITE);
        btIniciar.setRequestFocusEnabled(false);//tiro o foco do botão ao ser clicado
        btIniciar.setRolloverEnabled(true);//Retiro o efeito quando o mouse é passado em cima do button
        btIniciar.setFocusTraversalKeysEnabled(true);
        btIniciar.setToolTipText("Logar no sistema");
        btIniciar.setFocusPainted(false);
        //btIniciar.setBorder(new LineBorder(Color.WHITE));

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Button Cancelar">
        btCancelar.setSize(btIniciar.getSize());
        btCancelar.setLocation(btIniciar.getLocation().x + 150, btIniciar.getLocation().y);
        // btCancelar.setBackground(Color.RED);
        //btCancelar.setForeground(Color.WHITE);
        btCancelar.setFocusPainted(false);
        btCancelar.setToolTipText("Cancelar login");
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Button Mostrar">
        btMostrar.setSize(40, 35);
        btMostrar.setLocation(pfSenha.getLocation().x + 290, pfSenha.getLocation().y);
        btMostrar.setRolloverEnabled(false);
        btMostrar.setRequestFocusEnabled(true);
        btMostrar.setBackground(Color.WHITE);
        btMostrar.setForeground(Color.BLUE);
        btMostrar.setFocusPainted(false);// Retiro o efeito da borda interior quando o button está selecionado
        btMostrar.setToolTipText("Pressione para mostrar a senha");
        btMostrar.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Icons/eye.png"));
        btMostrar.setBorder(null);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="JLabel Logo">
        logo.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Icons/book2.png"));
        logo.setSize(140, 150);
        logo.setLocation(tfID.getLocation().x + 70, tfID.getLocation().y - 150);
        logo.setVisible(true);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
//</editor-fold>

    }//SET COMPONENTES 

    /**
     * Adiciona componentes ao conteiner
     */
    private void addComponentes() {

        //<editor-fold defaultstate="collapsed" desc=" Componentes adicionados ao conteiner">
        this.add(btIniciar);
        this.add(tfID);
        this.add(lbAviso);
        this.add(pfSenha);
        this.add(btMostrar);
        this.add(tfSenha);
        this.add(btCancelar);
        this.add(loading);
        this.add(jlLogotipo);
        this.add(logo);
//</editor-fold>

    }//ADD COMPONENTES

    public static void main(String[] args) {

        new JanelaMain().show();

    }//Main adm 

    /**
     * Define o look and feel da aplicação. Como padrão deixar a aparencia do
     * sistema operacional
     */
    private void setlookandfeel() {

        String TEMA = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
        try {
  
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //Parece MAC com.jtattoo.plaf.mcwin.McWinLookAndFeel
            System.out.println("Sistema operacional: " + System.getProperty("os.name"));
            System.out.println("look and feel definido: " + UIManager.getLookAndFeel().getName());
            // com.seaglass.SeaGlassLookAndFeel
            // com.jtattoo.plaf.smart.SmartLookAndFeel

        }//Try
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {

            //FIXME Tratar a exception individualmente 
            System.out.println("Erro ao setar o look and feel. \n" + ex.getMessage());

        }//Catch

    }//SET LOOK AND FEEL

    private void iniciar() {

        //XXX Somente valida os dados se o banco de dados estiver conectado
        if (!banco.isConnected()) {
            return;
        }

        logo.setVisible(false);
        loading.setVisible(true);

        System.out.println("Inicializando a thread anônima. ");

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (validaDadosLogin() && verificaLogin()) {

                    loading.setVisible(false);
                    logo.setVisible(true);
                    new JanelaMenu("Menu - Centro de Controle da Biblioteca", userName, estaJanela);

                }//IF

                loading.setVisible(false);
                logo.setVisible(true);

            }//Run
        }).start();

    }//Iniciar

    /**
     * Faz uma lista de look and feel, disponivel no sistema operacional atual
     */
    private void listLookAndFeel() {

        System.out.println("Lista de look and feel disponivel.\nSistema Operacionals atual: " + System.getProperty("os.name") + "\n");

        for (javax.swing.UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

            System.out.println(info.getName() + "\t || +" + info.getClassName());

        }//For

        System.out.println("------------------------------------------------------------------\n\n");

    }//List look and feel

}//Class
