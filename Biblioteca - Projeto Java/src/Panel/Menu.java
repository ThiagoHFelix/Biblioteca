package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Panel que contém itens de menu
 *
 * @author Thiago Henrique Felix
 */
public class Menu extends JPanel {

    private JLabel jlSair, jlHome, jlEmprestimo, jlAdd, jlConfig;
    private Janela.JanelaMenu frame;
    private GridLayout gridLayout;
    private Panel.Home panelHome;
    private Panel.Adicionar panelAdicionar;
    private Panel.Emprestimo panelEmprestimo;
    private Panel.Config panelConfig;

    /**
     *
     * @param nameUser Nome do usuário que fez o login.
     * @param frame JFrame a utilizar esta classe
     */
    public Menu(Janela.JanelaMenu frame) {

        this.frame = frame;
        instanciaComponentes();
        addComponentes();
        setComponentes();
        eventos();

    }//Menu

    /**
     * Instancia os componentes do objeto.
     */
    private void instanciaComponentes() {

        jlHome = new JLabel("");
        jlEmprestimo = new JLabel("");
        gridLayout = new GridLayout(0, 1);
        jlAdd = new JLabel("");
        jlConfig = new JLabel("");
        jlSair = new JLabel("");

    }//Instancia Componentes

    /**
     * Adiciona os componentes ao objeto.
     */
    private void addComponentes() {

        add(jlHome);
        add(jlEmprestimo);
        add(jlAdd);
        add(jlConfig);
        add(jlSair);

    }//Add Componentes

    /**
     * Altera as propriedades dos componentes atual do objeto.
     */
    private void setComponentes() {

        setLayout(new GridLayout(1, 0));

        //JLabel jlHome 
        jlHome.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Icons/house.png"));
        jlHome.setHorizontalAlignment(SwingConstants.CENTER);
        jlHome.setMinimumSize(new Dimension(100, 100));

        //JLabel jlEmprestimo
        jlEmprestimo.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Icons/emprestimo.png"));
        jlEmprestimo.setText("");
        jlEmprestimo.setHorizontalAlignment(SwingConstants.CENTER);

        //JLabel jlAdd
        jlAdd.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Icons/add.png"));
        jlAdd.setText("");
        jlAdd.setHorizontalAlignment(SwingConstants.CENTER);

        //JLabel jlConfig
        jlConfig.setHorizontalAlignment(JLabel.CENTER);
        jlConfig.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Icons/settings.png"));

        //JLabel jlSair
        jlSair.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Icons/logout.png"));
        jlSair.setHorizontalAlignment(SwingConstants.CENTER);

    }//Set Componentes

    /**
     * Adiciona eventos aos componentes.
     */
    private void eventos() {

        //<editor-fold defaultstate="collapsed" desc="JLabel jlHome">
        jlHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                panelHome.setVisible(true);
            panelConfig.setVisible(false);
                panelEmprestimo.setVisible(false);
                panelAdicionar.setVisible(false);
            }//Mouse Clicked

            @Override
            public void mousePressed(MouseEvent me) {

                jlHome.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 3, true));

            }//Mouse Pressed

            @Override
            public void mouseReleased(MouseEvent me) {

                jlHome.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Relesead

            @Override
            public void mouseEntered(MouseEvent me) {

                jlHome.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Entered

            @Override
            public void mouseExited(MouseEvent me) {

                jlHome.setBorder(null);

            }//Mouse Exited

        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JLabel jlAdd">
        jlAdd.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                panelConfig.setVisible(false);
                panelHome.setVisible(false);
                panelEmprestimo.setVisible(false);
                panelAdicionar.setVisible(true);

            }//Mouse Clicked

            @Override
            public void mousePressed(MouseEvent me) {

                jlAdd.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 3, true));

            }//Mouse Pressed

            @Override
            public void mouseReleased(MouseEvent me) {

                jlAdd.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Relesead

            @Override
            public void mouseEntered(MouseEvent me) {

                jlAdd.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Entered

            @Override
            public void mouseExited(MouseEvent me) {

                jlAdd.setBorder(null);

            }//Mouse Exited

        });

        jlAdd.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                jlAdd.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));
            }

            @Override
            public void focusLost(FocusEvent fe) {

                jlAdd.setBorder(null);

            }

        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JLabel jlEmprestimo">
        jlEmprestimo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                panelHome.setVisible(false);
                panelEmprestimo.setVisible(true);
                panelAdicionar.setVisible(false);
                panelConfig.setVisible(false);
            }//Mouse Clicked

            @Override
            public void mousePressed(MouseEvent me) {

                jlEmprestimo.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 3, true));

            }//Mouse Pressed

            @Override
            public void mouseReleased(MouseEvent me) {

                jlEmprestimo.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Relesead

            @Override
            public void mouseEntered(MouseEvent me) {

                jlEmprestimo.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Entered

            @Override
            public void mouseExited(MouseEvent me) {

                jlEmprestimo.setBorder(null);

            }//Mouse Exited

        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JLabel jlConfig">
        jlConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                panelHome.setVisible(false);
                panelEmprestimo.setVisible(false);
                panelAdicionar.setVisible(false);
                panelConfig.setVisible(true);

            }//Mouse Clicked

            @Override
            public void mousePressed(MouseEvent me) {

                jlConfig.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 3, true));

            }//Mouse Pressed

            @Override
            public void mouseReleased(MouseEvent me) {

                jlConfig.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Relesead

            @Override
            public void mouseEntered(MouseEvent me) {

                jlConfig.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Entered

            @Override
            public void mouseExited(MouseEvent me) {

                jlConfig.setBorder(null);

            }//Mouse Exited

        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JLabel jlSair">
        jlSair.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {

                frame.confirmacao();

            }//Mouse Clicked

            @Override
            public void mousePressed(MouseEvent me) {

                jlSair.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY, 3, true));

            }//Mouse Pressed

            @Override
            public void mouseReleased(MouseEvent me) {

                jlSair.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Relesead

            @Override
            public void mouseEntered(MouseEvent me) {

                jlSair.setBorder(new javax.swing.border.LineBorder(Color.LIGHT_GRAY));

            }//Mouse Entered

            @Override
            public void mouseExited(MouseEvent me) {

                jlSair.setBorder(null);

            }//Mouse Exited

        });
//</editor-fold>

    }//Eventos

    /**
     * Passa os conteiners do Menu para este conteiner
     *
     * @param home Pagina inicial
     * @param adicionar Pagina para adicionar novos itens 
     * @param emprestimo Pagina para fazer um novo emprestimo
     * @param config Pagina para configuração da aplicação 
     */
    public void setConteiners(Panel.Home home, Panel.Adicionar adicionar, Panel.Emprestimo emprestimo, Panel.Config config) {

        panelHome = home;
        panelAdicionar = adicionar;
        panelEmprestimo = emprestimo;
        panelConfig = config;

    }//Get Conteriners

}//Class
