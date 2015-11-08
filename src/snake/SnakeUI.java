package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

/**
 * Created by Emil on 2015-11-07.
 * Klass som visar algoritmen grafiskt.
 */
public class SnakeUI extends JPanel {
    private JPanel pnlCenter = new JPanel();
    private JPanel pnlSouth = new JPanel();
    private JTextPane textPane = new JTextPane();
    private GridLayout gridLayout;

    public SnakeUI(Brick[][] brickArray) {
        setLayout(new BorderLayout());
        pnlCenter.setPreferredSize(new Dimension(800,600));
        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth,BorderLayout.SOUTH);
        pnlSouth.setBackground(Color.WHITE);

        JFrame frame = new JFrame();
        frame.add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(600, 400));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }

    /**
     * Tar in den största vägen och skriver in det i en panel
     * @param size storleken på största vägen
     */
    public void setPnlSouth(int size, int pathsize) {
        pnlSouth.add(textPane);
        textPane.setText("Longest path it could find is "+size+" steps from " + pathsize + " different paths");
    }

    /**
     * Bygger uit genom att ta in brickarrayen och sätter olika färger
     * beroende på vilken egenskap varje brick har.
     * @param brickArray färdiga "snabbaste vägen" arrayen
     */
    public void buildUI(Brick[][] brickArray) {
        pnlCenter.removeAll();
        int nbrColums = brickArray.length;
        int nbrRows = brickArray[0].length;
        gridLayout = new GridLayout(nbrColums,nbrRows);
        pnlCenter.setLayout(gridLayout);

        for (int i = 0; i < brickArray.length; i++) {
            for (int j = 0; j < brickArray[i].length; j++) {

                if (brickArray[i][j].isObstacle()) {
                    JButton btnOb = new JButton();
                    btnOb.setBackground(Color.RED);
                    pnlCenter.add(btnOb);

                } else if (brickArray[i][j].isVisited()) {
                    Color color = new Color(0, (254 / ((brickArray[i][j].getOrder() + 1)/2 +1)), 0);
                    JButton btnVis = new JButton();
                    btnVis.setBackground(color);

                    if (((brickArray[i][j].getOrder()==0))) {
                        btnVis.setForeground(Color.DARK_GRAY);

                    } else {
                        btnVis.setForeground(Color.WHITE);
                    }
                    btnVis.setText(brickArray[i][j].getOrder() + "");
                    pnlCenter.add(btnVis);

                } else {
                    JButton btn = new JButton();
                    btn.setBackground(Color.LIGHT_GRAY);
                    btn.setText(brickArray[i][j].getOrder()+ "");
                    pnlCenter.add(btn);
                }
            }
        }
        pnlCenter.validate();
    }
}
