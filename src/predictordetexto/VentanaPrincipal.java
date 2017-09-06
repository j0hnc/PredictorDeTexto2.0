package predictordetexto;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author jjcerpa
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private final static String DIRECCION_ARCHIVO = System.getProperty("user.dir") + "\\src\\predictordetexto\\archivo.txt";
    public static Arbol arbol = new Arbol();

    public VentanaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        txtPalabra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                lblResultado.setText("");
                String palabra = txtPalabra.getText();
                if (palabra.length() > 0) {
                    buscarSugerencias(palabra);
                }
                if (lblResultado.getText().length() == 0) {
                    if (palabra.length() > 1) {
                        palabra = palabra.substring(0, palabra.length() - 1);
                        buscarSugerencias(palabra);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPalabra = new javax.swing.JTextField();
        lblResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Predictor de texto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblResultado)
                    .addComponent(txtPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblResultado)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Nodo buscarNodoUltimaLetra(Nodo n, String palabra, int i) {
        if (n == null) {
            return null;
        }
        
        if (n.letra == palabra.charAt(palabra.length() - 1)) { // ultima letra
            return n;
        }
        return buscarNodoUltimaLetra(n.lista.buscarNodo(palabra.charAt(i)), palabra, i + 1);
    }
    
    public Nodo buscarUltimo(Nodo nodo, String palabra, int i) {
        if (nodo == null) return null;
        if (nodo.lista.estaVacia()) return nodo;
        
        if (i == palabra.length() - 1 && nodo.letra == palabra.charAt(palabra.length() - 1)) {
            return nodo;
        }
        return buscarUltimo(nodo.lista.buscarNodo(palabra.charAt(i + 1)), palabra, i + 1);
    }
    
    public void buscarSugerencias(String palabra) {
        Nodo ultimo = buscarUltimo(arbol.raiz.lista.buscarNodo(palabra.charAt(0)), palabra, 0);        
        if (ultimo != null) {
            mostrarSugerencias(ultimo, palabra);
        }
    }

    public void mostrarSugerencias(Nodo nodo, String historia) {
        for (Nodo n : nodo.lista.lista_letras) {
            if (n.esPalabra) {
                lblResultado.setText(lblResultado.getText() + historia + n.letra + ", ");
            }

            if (!n.lista.estaVacia()) {
                mostrarSugerencias(n, historia + n.letra);
            }
        }
    }

    public static void main(String args[]) {

        BufferedReader br;
        String linea_actual;

        try {
            br = new BufferedReader(new FileReader(DIRECCION_ARCHIVO));
            while ((linea_actual = br.readLine()) != null) {
                arbol.insertarPalabra(arbol.raiz, linea_actual, 0);
            }
        } catch (IOException e) {
            System.out.println("/*error*/");
        }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTextField txtPalabra;
    // End of variables declaration//GEN-END:variables
}
