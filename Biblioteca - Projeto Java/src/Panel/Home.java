package Panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import Classes.BancoDados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Thiago Henrique Felix
 */
public class Home extends JPanel {

    //Variaveis Globais 
    private JLabel jlInfo, info;
    private JTextField jtPesquisar;
    private JButton jbLimpar;
    private DefaultTableModel modelTable;
    private JTable tabela;
    private JScrollPane scrollPane;
    private JComboBox cbEscolha;
    private String itensComboBox[];
    private JTextArea taInfo1, taInfo2, taInfo3, taInfo4, taInfo5, taInfo6; // Blocos com informações sobre o item
    private final BancoDados banco;
    private java.sql.ResultSet resultadoBanco;

    /**
     * Construtor padrão
     *
     * @param banco Classe com a conexão com o banco de dados.
     */
    public Home(BancoDados banco) {

        this.banco = banco;
        inicializaComponentes();
        setComponentes();
        addComponentes();
        eventos();

    }//Construtor padrão

    /**
     * Inicializa todos os componentes desta classe
     */
    private void inicializaComponentes() {

        jlInfo = new JLabel("");
        jtPesquisar = new JTextField();
        jbLimpar = new JButton("Cancelar");
        modelTable = new DefaultTableModel(new Object[]{"Código", "Titulo"}, 0);
        tabela = new JTable(modelTable);
        scrollPane = new JScrollPane(tabela);
        itensComboBox = new String[]{"", "Livro", "Mapa", "Jogo", "Globo", "Dicionario", "Enciclopedia"};
        cbEscolha = new JComboBox(itensComboBox);
        info = new JLabel("Informações ");
        taInfo1 = new JTextArea("");
        taInfo2 = new JTextArea("");
        taInfo3 = new JTextArea("");
        taInfo4 = new JTextArea("");
        taInfo5 = new JTextArea("");
        taInfo6 = new JTextArea("");

    }//Inicializa Componentes

    /**
     * Adiciona os componentes a este JPanel
     */
    private void addComponentes() {
       
        add(jtPesquisar, "spanx 5,growx, growy, align center center");
        add(jlInfo, "split 3, growx, growy, align center center");
        add(cbEscolha, " align right center, growy");
        add(jbLimpar);
        add(scrollPane, "spanx , gaptop 10, growx, align center center");
        add(info, "wrap");
        add(taInfo1, "align center center, spanx 4");
        add(taInfo2, "align center center, span ");
        add(taInfo3, "align center center, spanx 4");
        add(taInfo4, "align center center, span");
        add(taInfo5, "align center center, spanx 4");
        add(taInfo6, "align center center, span");

    }//Add Componentes

    /**
     * Configura todos os componentes
     */
    private void setComponentes() {

        setLayout(new MigLayout("wrap 6", "grow"));

        //JTextField jtPesquisar
        jtPesquisar.setHorizontalAlignment(JTextField.CENTER);

        //JTable tabela
        tabela.setEnabled(true);
        tabela.getColumnModel().getColumn(0).setMaxWidth(70);
        tabela.getColumnModel().getColumn(0).setMinWidth(70);

        //JLabel jlInfo
        jlInfo.setHorizontalAlignment(SwingConstants.CENTER);
        jlInfo.setForeground(Color.RED);
        jlInfo.setText("Escolha um item");
        jlInfo.setVisible(false);
        
        //JButton jbLimpar
        jbLimpar.setFocusPainted(false);

        //JTextArea taInfo1
        taInfo1.setLineWrap(true); // Pula de linha automaticamente

        //JTextField taInfo2
        taInfo1.setLineWrap(true);

        //JTextField taInfo3
        taInfo1.setLineWrap(true);

        //JTextField taInfo4
        taInfo1.setLineWrap(true);

        //JTextField taInfo5
        taInfo1.setLineWrap(true);

        //JTextField taInfo6
        taInfo1.setLineWrap(true);

    }//Set Componentes

    /**
     * Todos os eventos desta class
     */
    private void eventos() {

        //<editor-fold defaultstate="collapsed" desc="JComboBox cbEscolha">
        cbEscolha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                carregaDadosTabela("select * from " + cbEscolha.getSelectedItem().toString());
                validationBusca();

            }//ActionPerformed

        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JTable tabela">
        tabela.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {

                carregaInfo(cbEscolha.getSelectedItem().toString());

            }//Mouse Clicked

        });

//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JTextField jtPesquisar">
        jtPesquisar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent ke) {

                if (validationBusca()) {
                    carregaDadosTabela("select titulo from " + cbEscolha.getSelectedItem().toString() + " where titulo like '%" + jtPesquisar.getText() + "%'");

                }//IF

            }//Key Typed

        });//Key Typed
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="JButton jbLimpar">
        jbLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                jtPesquisar.setText("");
                cbEscolha.setSelectedIndex(0);
                jtPesquisar.setBorder(new LineBorder(Color.LIGHT_GRAY));
                jlInfo.setVisible(false);
                
            }//Action Listener
        });
//</editor-fold>

    }//Eventos 

    /**
     * Demonstra ao usuário caso haja algo de errado em relação ao jtPesquisar
     */
    private boolean validationBusca() {

        if (cbEscolha.getSelectedItem().toString().isEmpty()) {

            if (!jtPesquisar.getText().equals("")) {
                
                jtPesquisar.setBorder(new LineBorder(Color.red));
                jlInfo.setVisible(true);
                
            }//IF
            else {
                jtPesquisar.setBorder(new LineBorder(Color.LIGHT_GRAY));
                jlInfo.setVisible(false);
            }//ELSE

            return false;

        }//IF
        else {

            jtPesquisar.setBorder(new LineBorder(Color.LIGHT_GRAY));
            jlInfo.setVisible(false);
            return true;

        }//Else

    }//Validation Busca 

    /**
     * Carrega todos os da tabela livro na JTable
     */
    private void carregaDadosTabela(String type) {

        try {

            while (modelTable.getRowCount() > 0) {
                modelTable.removeRow(0);

            }//Try

            if (type.isEmpty()) {

                taInfo1.setText("");
                taInfo2.setText("");
                taInfo3.setText("");
                taInfo4.setText("");
                taInfo5.setText("");
                taInfo6.setText("");
                return;

            }//IF

            resultadoBanco = banco.executarDML(type);

            while (resultadoBanco.next()) {

                modelTable.addRow(new String[]{resultadoBanco.getString(1), resultadoBanco.getString("titulo")});

            }//While

        }//Try
        catch (java.sql.SQLException SQLEx) {

            System.out.println("Erro ao buscar dados no banco. \n" + SQLEx.getMessage());
            SQLEx.printStackTrace();

        }//Catch

    }//CarregaDadosTabela

    //FIXME O correto seria manipular objetos não tabelas do banco de dados direto. Arrumar isto.
    /**
     * Carrega informações do item da tebela nos blocos de informações.
     */
    private void carregaInfo(String table) {

        try {

            resultadoBanco = banco.executarDML("select * from " + table);

            if (resultadoBanco == null) {
                jtPesquisar.setBorder(new LineBorder(Color.red));
            }

            while (resultadoBanco.next()) {

                if (resultadoBanco.getString(1).equals(tabela.getValueAt(tabela.getSelectedRow(), 0).toString())) {

                    if (cbEscolha.getSelectedItem().toString().equals("Livro")) {

                        taInfo1.setText("Código: " + resultadoBanco.getString(1));
                        taInfo2.setText("Titulo: " + resultadoBanco.getString("titulo"));
                        taInfo3.setText("Autor: " + resultadoBanco.getString("autor"));
                        taInfo4.setText("Localização: " + resultadoBanco.getString("localizacao"));
                        taInfo5.setText("Editora: " + resultadoBanco.getString("editora"));
                        taInfo6.setText("");

                        break;

                    }//IF Livro

                    if (cbEscolha.getSelectedItem().toString().equals("Mapa")) {

                        taInfo1.setText("Código " + resultadoBanco.getString(1));
                        taInfo2.setText("Titulo: " + resultadoBanco.getString("titulo"));
                        taInfo3.setText("");
                        taInfo4.setText("");
                        taInfo5.setText("");
                        taInfo6.setText("");

                    }//IF MAPA      

                    if (cbEscolha.getSelectedItem().toString().equals("Jogo")) {

                        taInfo1.setText("");
                        taInfo2.setText("");
                        taInfo3.setText("");
                        taInfo4.setText("");
                        taInfo5.setText("");
                        taInfo6.setText("");

                    }//IF JOGO

                    if (cbEscolha.getSelectedItem().toString().equals("Globo")) {

                        taInfo1.setText("");
                        taInfo2.setText("");
                        taInfo3.setText("");
                        taInfo4.setText("");
                        taInfo5.setText("");
                        taInfo6.setText("");
                    }//IF GLOBO      

                    if (cbEscolha.getSelectedItem().toString().equals("Dicionario")) {
                        taInfo1.setText("");
                        taInfo2.setText("");
                        taInfo3.setText("");
                        taInfo4.setText("");
                        taInfo5.setText("");
                        taInfo6.setText("");
                    }//IF DICIONARIO      

                    if (cbEscolha.getSelectedItem().toString().equals("Enciclopedia")) {
                        taInfo1.setText("");
                        taInfo2.setText("");
                        taInfo3.setText("");
                        taInfo4.setText("");
                        taInfo5.setText("");
                        taInfo6.setText("");

                    }//IF ENCICLOPEDIA      

                }//IF LINHA CORRETA 

            }//While

        }//Try
        catch (java.sql.SQLException ex) {
            System.out.println(" Erro ao buscar dados na JTable. \nError: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Desculpe, ocorreu um erro interno, contate o administrador.");

        }//Catch

    }//Carrega informações

}//Home
