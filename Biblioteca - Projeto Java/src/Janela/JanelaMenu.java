package Janela;

import Classes.BancoDados;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import Panel.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComponent;

/**
 *
 * @author Thiago Henrique Felix
 */
public class JanelaMenu extends JFrame {

    private final JFrame estaJanela = this, login;/* Variavel com o a localização do atual objeto */
    private Classes.BancoDados banco;
    private GridBagLayout layout;
    private GridBagConstraints gbcMenu, gbcItemMenu;

    //XXX Painel menu
    private Menu panelMenu;

    //XXX Paineis do Menu
    private Home panelHome;
    private Emprestimo panelEmprestimo;
    private Adicionar panelAdicionar;
    private Config  panelConfig;
    
    /**
     * Construtor padrão da class Menu
     *
     * @param titulo Titulo do JFrame
     * @param nameUser Nome do usuário que fez o login
     * @param Login JFrame anterior responsavel pelo login
     */
    public JanelaMenu(String titulo, String nameUser, JFrame Login) {

        this.login = Login;
        inicializaComponentes();
        setComponentes(titulo, nameUser);
        addComponentes();
        eventos();

    }//Menu

    /**
     * Método onde esta todos os eventos relacionados ao atual JFrame
     */
    private void eventos() {

        //<editor-fold defaultstate="collapsed" desc="JFrame Window">
//JFrame Window
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent we) {

                //Seto o frame para maximizado
               // estaJanela.setExtendedState(JFrame.MAXIMIZED_BOTH);
               

            }//Windows Opened

            @Override
            public void windowClosing(WindowEvent we) {

                confirmacao();

            }//Windows Opened

        });
        //</editor-fold>     

    }//Eventos

    /**
     * Cria uma nova instância dos componentes
     */
    private void inicializaComponentes() {
        
        conectaBanco();
        panelHome = new Home(banco);
        panelEmprestimo = new Emprestimo();
        panelAdicionar = new Adicionar();
        panelMenu = new Menu(this);
        layout = new GridBagLayout();
        gbcMenu = new GridBagConstraints();
        gbcItemMenu = new GridBagConstraints();
        panelConfig  = new Config();

    }//Inicializa Componentes 

    /**
     * Adiciona componentes a um conteiner
     */
    private void addComponentes() {

        //JFrame
        this.add(panelMenu, gbcMenu);
        this.add(panelHome, gbcItemMenu);
        this.add(panelEmprestimo, gbcItemMenu);
        this.add(panelAdicionar,gbcItemMenu );
        this.add(panelConfig, gbcItemMenu);

    }//Add Componentes 

    /**
     * Altera as propriedades dos objetos
     *
     * @param titulo Titulo do frame
     */
    private void setComponentes(String titulo, String nameUser) {

        
        //GridBagConstraints gbcMenu 
        gbcMenu.anchor = GridBagConstraints.NORTHWEST;
        gbcMenu.fill =  GridBagConstraints.HORIZONTAL;  
        gbcMenu.insets = new java.awt.Insets(0, 0, 1, 0);
        gbcMenu.weightx = 1;
        gbcMenu.weighty = 0;
        gbcMenu.gridy = 0;
        
        //GridBagConstraints gbcItemMenu
        gbcItemMenu.gridy = 1;
        gbcItemMenu.weightx = 20;
        gbcItemMenu.weighty = 1;
        gbcItemMenu.fill = GridBagConstraints.BOTH;
        
        
        //JFrame 
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setIconImage(new ImageIcon(System.getProperty("user.dir") + "/Icons/book.png").getImage());
        this.setLayout(layout);
        this.setSize(900,600);
        this.setMinimumSize(new Dimension(800,600) );
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        //XXX O Frame somente fica visivel após a validação 
        this.setVisible(true);
        this.setTitle(titulo + " - User: "+nameUser);
        

        
        //Menu panelMenu
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setPreferredSize(new Dimension(100, 100));
        panelMenu.setMinimumSize(new Dimension(600,100));
        panelMenu.setMaximumSize(new Dimension(600,100));
        panelMenu.setConteiners(panelHome, panelAdicionar, panelEmprestimo, panelConfig);
        
        //Home panelHome
        panelHome.setBackground(Color.WHITE);
        
        
        //Emprestimo panelEmprestimo
        panelEmprestimo.setVisible(false);
       

        //Adicionar panelAdicionar
        panelAdicionar.setVisible(false);
        
        //Config panelConfig 
        panelConfig.setVisible(false);
        

    }//Set Componentes 

    /**
     * Confirma com o usuário se deve fazer o logout
     */
    public void confirmacao() {

        int resposta = JOptionPane.showConfirmDialog(estaJanela, "Deseja realmente fazer logout ? ");

        if (resposta == JOptionPane.YES_OPTION) {
            dispose();
            new JanelaMain().setVisible(true);
            try {

                banco.disconnect();

            }//Try
            catch (java.sql.SQLException ex) {

                System.out.println(ex.getMessage());

            }//Catch

        }//If

    }//Confirmação de logoff

    /**
     * Responsavel por fazer a conecção da classe Menu com o banco de dados
     */
    private void conectaBanco() {

        if (System.getProperty("os.name").equals("Linux")) {
            banco = new BancoDados("SYSDBA", "masterkey", "jdbc:firebirdsql:localhost/3050:/home/thiagofelix/Banco de dados/bancoBibliotecaFirebird.fdb", BancoDados.getFirebirdDriver());
        } else {
            banco = new BancoDados("root", "masterkey", "jdbc:mysql://localhost/biblioteca_banco", BancoDados.getMySQLDriver());
        }

        banco.conect();

        //XXX Se o banco de dados não estiver conectado
        if (!banco.isConnected()) {
            JOptionPane.showMessageDialog(null, "Desculpe, ocorreu uma falha ao conectar com a base de dados, contate o administrador se o erro persistir");

            //FIXME Não esta funcionando 
            dispose();//Fecho a janela porque não há conexão com o banco de dados 

        }//IF
        else {
            login.dispose();
        }
    }//Conecta Banco

}//Class
