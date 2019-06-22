package gui;

import gestion.TamyNails;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author demaa
 */
public class NuevoProducto extends javax.swing.JDialog {

    private final Dimension normalSize;
    private final Dimension smallSize;

    public NuevoProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        normalSize = this.getSize();
        smallSize = new Dimension(360, 268);
        String userdir = System.getProperty("user.dir");
        File pathToFile = new File(userdir + "/resources/logo.jpg");
        try {
            Image image = ImageIO.read(pathToFile);
            this.setIconImage(image);
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoLBL = new javax.swing.JLabel();
        tipoTF = new javax.swing.JTextField();
        precioLBL = new javax.swing.JLabel();
        precioTF = new javax.swing.JTextField();
        guardarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        productoLBL = new javax.swing.JLabel();
        productoCB = new javax.swing.JComboBox<>();
        colorLBL = new javax.swing.JLabel();
        colorTF = new javax.swing.JTextField();
        efectoLBL = new javax.swing.JLabel();
        efectoTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tamy Nails - Nuevo producto");

        tipoLBL.setText("Tipo:");

        precioLBL.setText("Precio:");

        guardarButton.setText("Guardar");
        guardarButton.setToolTipText("");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setText("Añadir nuevo producto");

        productoLBL.setText("Producto:");

        productoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Esmalte", "Removedor", "Herramienta" }));
        productoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoCBActionPerformed(evt);
            }
        });

        colorLBL.setText("Color:");
        colorLBL.setMinimumSize(new java.awt.Dimension(0, 0));

        colorTF.setMinimumSize(new java.awt.Dimension(0, 0));

        efectoLBL.setText("Efecto:");
        efectoLBL.setMinimumSize(new java.awt.Dimension(0, 0));

        efectoTF.setMinimumSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(guardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(productoLBL)
                                .addGap(18, 18, 18)
                                .addComponent(productoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(efectoLBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(colorLBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(efectoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(colorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(tipoLBL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tipoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(precioLBL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precioTF, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productoLBL)
                    .addComponent(productoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoLBL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorLBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(efectoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(efectoLBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioLBL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        if (productoCB.getSelectedIndex() == 0) {
            //Guardar producto, tipo, color, efecto y precio
            String producto = "Esmalte";
            String tipo = tipoTF.getText();
            String color = colorTF.getText();
            String efecto = efectoTF.getText();
            String precio = precioTF.getText();
            TamyNails.agregarProducto(producto, tipo, color, efecto, precio);
        } else {
            //Guardar producto, tipo y precio
            String producto;
            if (productoCB.getSelectedIndex() == 1) {
                producto = "Removedor";
            } else {
                producto = "Herramienta";
            }
            String tipo = tipoTF.getText();
            String precio = precioTF.getText();
            TamyNails.agregarProducto(producto, tipo, precio);
        }
        this.dispose();
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void productoCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoCBActionPerformed
        if (productoCB.getSelectedIndex() == 0) {
            //Hacerlo más grande
            this.setSize(normalSize);
            this.efectoLBL.setVisible(true);
            this.efectoTF.setVisible(true);
            this.colorLBL.setVisible(true);
            this.colorTF.setVisible(true);
            System.out.println("Hacerlo más grande");
        } else {
            //Hacerlo normal
            this.setSize(smallSize);
            this.efectoLBL.setVisible(false);
            this.efectoTF.setVisible(false);
            this.colorLBL.setVisible(false);
            this.colorTF.setVisible(false);
            System.out.println("Hacerlo normal");
        }
    }//GEN-LAST:event_productoCBActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel colorLBL;
    private javax.swing.JTextField colorTF;
    private javax.swing.JLabel efectoLBL;
    private javax.swing.JTextField efectoTF;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel precioLBL;
    private javax.swing.JTextField precioTF;
    private javax.swing.JComboBox<String> productoCB;
    private javax.swing.JLabel productoLBL;
    private javax.swing.JLabel tipoLBL;
    private javax.swing.JTextField tipoTF;
    // End of variables declaration//GEN-END:variables
}
