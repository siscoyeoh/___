package yl.swing.grouplayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Convertisseur extends JFrame {
    private JLabel label1, label2;
    private JTextField text1;
    private JRadioButton radioButton1, radioButton2,radioButton3;
    private JButton button1, button2;

    public  Convertisseur(){

        setTitle("Convertisseur");
        setSize(450,150);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("URL du fichier");
        label2 = new JLabel("Format de la conversion");
        radioButton1 = new JRadioButton("MP4");
        radioButton2 = new JRadioButton("AVI");
        radioButton3 = new JRadioButton("MOV");
        button1 = new JButton("Continuer");
        button2 = new JButton("Telecharger");
        text1 = new JTextField();

        GroupLayout glDisposition = new GroupLayout(getContentPane());
        getContentPane().setLayout(glDisposition);
        glDisposition.setAutoCreateGaps(true);
        glDisposition.setAutoCreateContainerGaps(true);

        glDisposition.setHorizontalGroup(
                glDisposition.createSequentialGroup()
                    .addGroup(glDisposition.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(glDisposition.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(label1)
                                    .addComponent(text1)
                                    .addComponent(label2))
                            .addGroup(glDisposition.createSequentialGroup()
                                    .addComponent(radioButton1)
                                    .addComponent(radioButton2)
                                    .addComponent(radioButton3)))
                    .addGroup(glDisposition.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(button1)
                            .addComponent(button2))
        );

        glDisposition.setVerticalGroup(glDisposition.createSequentialGroup()
                .addComponent(label1)
                .addGroup(glDisposition.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(text1)
                        .addComponent(button1))
                .addComponent(label2)
                     .addGroup(glDisposition.createParallelGroup()
                             .addComponent(radioButton1)
                             .addComponent(radioButton2)
                             .addComponent(radioButton3)
                             .addComponent(button2))
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
                Convertisseur layout = new Convertisseur();
                layout.setVisible(true);
            }
        });
    }
}
