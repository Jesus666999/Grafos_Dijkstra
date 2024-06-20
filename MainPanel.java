//Reynoso Garcia Jesus Salvador 22310400
package Principal;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MainPanel extends javax.swing.JFrame {

    static Grafo grafo = new Grafo();

    Graphics g;

    public MainPanel() {
        initComponents();
        g = Panel.getGraphics();
        this.setLocationRelativeTo(null);
        this.setTitle("Grafos: Dijsktra Visual");
    }

    public void paintNode(int mX, int mY, String nombre) {
        g.drawOval(mX, mY, 50, 50);
        g.drawString(nombre, mX + 25, mY + 25);
    }

    public void paintLink(Nodo nodo1, Nodo nodo2) {
        int n1x, n1y, n2x, n2y;
        n1x = nodo1.getCoord().x;
        n1y = nodo1.getCoord().y;

        ArrayList<Link> links = nodo1.getLista().getLinks();
        for (Link link : links) {
            n2x = link.getDestino().getCoord().x;
            n2y = link.getDestino().getCoord().y;
            g.drawLine(n1x, n1y, n2x, n2y);
            g.drawString(Integer.toString(link.getCosto()), ((n1x + n2x) / 2), ((n1y + n2y) / 2));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        lblInstruccion = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        mnCrear = new javax.swing.JMenu();
        btnNewLink = new javax.swing.JMenuItem();
        mnLimpiar = new javax.swing.JMenu();
        btnLimpiar = new javax.swing.JMenuItem();
        mnIniciar = new javax.swing.JMenu();
        btnStart = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Panel.setForeground(new java.awt.Color(60, 63, 65));
        Panel.setPreferredSize(new java.awt.Dimension(750, 575));
        Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelMouseClicked(evt);
            }
        });
        Panel.setLayout(new java.awt.BorderLayout());

        lblInstruccion.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 18)); // NOI18N
        lblInstruccion.setText("Haga click en cualquier parte para crear un nuevo nodo");

        mnCrear.setText("Enlace");

        btnNewLink.setText("Nuevo");
        btnNewLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewLinkActionPerformed(evt);
            }
        });
        mnCrear.add(btnNewLink);

        Menu.add(mnCrear);

        mnLimpiar.setText("Reiniciar");

        btnLimpiar.setText("Limpiar Grafo");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        mnLimpiar.add(btnLimpiar);

        Menu.add(mnLimpiar);

        mnIniciar.setText("Iniciar");

        btnStart.setText("Dijsktra");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        mnIniciar.add(btnStart);

        Menu.add(mnIniciar);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lblInstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblInstruccion)
                .addGap(1, 1, 1)
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMouseClicked
        String nombreNodo = JOptionPane.showInputDialog("Ingrese el nombre del nodo");
        while (nombreNodo == null){
            nombreNodo = JOptionPane.showInputDialog("Error: nombre erroneo. Ingrese el nombre del nodo");
        }
        Point coords = new Point(evt.getX() + 25, evt.getY() + 25);
        Nodo nodo = new Nodo(nombreNodo, coords);
        grafo.addNode(nodo);
        paintNode(evt.getX(), evt.getY(), nodo.getDato());
    }//GEN-LAST:event_PanelMouseClicked

    private void btnNewLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewLinkActionPerformed
        String n1 = JOptionPane.showInputDialog("Ingrese el nombre del primer nodo:");
        String n2 = JOptionPane.showInputDialog("Ingrese el nombre del segundo nodo:");
        Nodo nodo1 = grafo.getNode(n1);
        Nodo nodo2 = grafo.getNode(n2);
        if (nodo1 == null || nodo2 == null) {
            JOptionPane.showMessageDialog(rootPane, "Error: Uno de los nodos no existe");
        } else {
            grafo.newLink(nodo1, nodo2);
            paintLink(nodo1, nodo2);
        }
    }//GEN-LAST:event_btnNewLinkActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        Nodo node = grafo.getNode(JOptionPane.showInputDialog("Ingrese el nombre del nodo origen"));
        if (node == null) {
            JOptionPane.showMessageDialog(rootPane, "El nodo no existe");
        } else {
            g = grafo.dijkstra(grafo, node, g);
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        grafo = new Grafo();
        g.clearRect(0, 0, Panel.getWidth(), Panel.getHeight());
    }//GEN-LAST:event_btnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Menu;
    private javax.swing.JPanel Panel;
    private javax.swing.JMenuItem btnLimpiar;
    private javax.swing.JMenuItem btnNewLink;
    private javax.swing.JMenuItem btnStart;
    private javax.swing.JLabel lblInstruccion;
    private javax.swing.JMenu mnCrear;
    private javax.swing.JMenu mnIniciar;
    private javax.swing.JMenu mnLimpiar;
    // End of variables declaration//GEN-END:variables
}
