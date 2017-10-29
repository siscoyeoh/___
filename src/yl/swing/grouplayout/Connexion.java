package yl.swing.grouplayout;

import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class Connexion extends JFrame {
    private JButton button1;
    private JLabel label1, label2;
    private JTextField text1, text2;

    public Connexion(){

        setTitle("Connexion.");
        setSize(400,100);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //instanciation of composants.
        button1 = new JButton("se connecter");
        label1 = new JLabel("Identificateur");
        label2 = new JLabel("Mot de passe");
        text1 = new JTextField();
        text2 = new JTextField();

        // GroupLayout:
        GroupLayout glDisposition = new GroupLayout(getContentPane());
        getContentPane().setLayout(glDisposition);
        glDisposition.setAutoCreateGaps(true);
        glDisposition.setAutoCreateContainerGaps(true);

        glDisposition.setHorizontalGroup(
                glDisposition.createSequentialGroup()
                    .addGroup(glDisposition.createParallelGroup(LEADING)
                            .addComponent(label1)
                            .addComponent(label2))
                    .addGroup(glDisposition.createParallelGroup(LEADING)
                            .addComponent(text1)
                            .addComponent(text2))
                    .addComponent(button1)
        );

        glDisposition.setVerticalGroup(
                glDisposition.createSequentialGroup()
                        .addGroup(glDisposition.createParallelGroup(BASELINE)
                                .addComponent(label1)
                                .addComponent(text1))
                        .addGroup(glDisposition.createParallelGroup(LEADING)
                                .addComponent(label2)
                                .addComponent(text2)
                                .addComponent(button1))

        );
    }

    public static void main(String[] args){

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            "javax.swing.plaf.metal.MetalLookAndFeel");
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                Connexion layout = new Connexion();
                layout.setVisible(true);
            }
        });
    }
}
