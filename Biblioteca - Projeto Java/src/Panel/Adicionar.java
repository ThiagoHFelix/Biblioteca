


package Panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 *
 * @author Thiago Henrique Felix    
 */
public class Adicionar extends JPanel{
    
    private JButton addLivro, addMapa, addJogo, addGlobo, addDicionario, addEnciclopedia;
    
    
    /**
     * Construtor padrão
     */
    public Adicionar(){
    
    setLayout(new java.awt.GridLayout(3, 1));
        
    inicializarComponentes();
    addComponentes();
    setComponentes();
    eventos();
        
    
    }//Construtor Padrão
    
    /**
     * Todos os eventos deste JPanel
     */
    private void eventos(){
    
    addLivro.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
    
        
            
        }//Action Performed
    });
    
    
    
    
    }//Eventos
    
    /**
     * Inicializa os compoentes do objeto da classe ManterItens
     */
    private void inicializarComponentes(){
    
    addLivro = new JButton("AddLivro");
    addMapa = new JButton("AddMapa");
    addJogo = new JButton("AddJogo");
    addGlobo = new JButton("AddGlobo");
    addDicionario = new JButton("AddDicionario");
    addEnciclopedia = new JButton("AddEnciclopedia");
    
    }//Inicializar Componentes
    
    /**
     * Adiciona os compontes ao objeto da classe ManterItens
     */
    private void addComponentes(){
    
    add(addLivro);
    add(addMapa);
    add(addJogo);
    add(addGlobo);
    add(addDicionario);
    add(addEnciclopedia);
    
   }//Add Componentes
    
    /**
     * Seta os componentes do objeto da clesse ManterItens
     */
    private void setComponentes(){
    
    
        
    //JButton addLivro 
    addLivro.setFocusPainted(false);
    addLivro.setBackground(Color.WHITE);
    
    //JButton addMapa
    addMapa.setFocusPainted(false);
    
    //JButton addJogo
    addJogo.setFocusPainted(false);
    
    //JButton addGlobo
    addGlobo.setFocusPainted(false);
    
    //JButton addDicionario 
    addDicionario.setFocusPainted(false);
    
    //JButton addEnciclopedia 
    addEnciclopedia.setFocusPainted(false);
    
    
    }//Set Componentes
    
    
}//Class
