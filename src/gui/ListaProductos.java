package gui;

import entidades.Cliente;
import gestion.TamyNails;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import recursos.Esmalte;
import recursos.Herramienta;
import recursos.Producto;
import recursos.Removedor;

/**
 *
 * @author demaa
 */
public class ListaProductos extends javax.swing.JDialog {

    private int tablesOpened = 0;

    public ListaProductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarDatos();
        this.jScrollPane2.setVisible(false);
        this.jScrollPane3.setVisible(false);
        this.jScrollPane4.setVisible(false);
        this.setSize(157, 530);
        String userdir = System.getProperty("user.dir");
        File pathToFile = new File(userdir + "/resources/logo.jpg");
        try {
            Image image = ImageIO.read(pathToFile);
            this.setIconImage(image);
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarDatos() {
        DefaultTableModel modelE = (DefaultTableModel) esmaltesTable.getModel();
        DefaultTableModel modelR = (DefaultTableModel) removedoresTable.getModel();
        DefaultTableModel modelH = (DefaultTableModel) herramientasTable.getModel();
        modelE.setRowCount(0);
        modelR.setRowCount(0);
        modelH.setRowCount(0);
        for (Producto producto : TamyNails.getListaProductos()) {
            switch (producto.getClass().getSimpleName()) {
                case "Esmalte":
                    Esmalte esmalte = (Esmalte) producto;
                    modelE.addRow(new Object[]{esmalte.getTipo(), esmalte.getColor(), esmalte.getEfecto(), "$" + esmalte.getPrecio(), esmalte.getCantidad()});
                    break;
                case "Herramienta":
                    Herramienta herramienta = (Herramienta) producto;
                    modelH.addRow(new Object[]{herramienta.getTipo(), "$" + herramienta.getPrecio(), herramienta.getCantidad()});
                    break;
                case "Removedor":
                    Removedor removedor = (Removedor) producto;
                    modelR.addRow(new Object[]{removedor.getTipo(), "$" + removedor.getPrecio(), removedor.getCantidad()});
                    break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        addProductButton = new javax.swing.JButton();
        herramientasButton = new javax.swing.JToggleButton();
        removedoresButton = new javax.swing.JToggleButton();
        esmaltesButton = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        esmaltesTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        herramientasTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        removedoresTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tamy Nails - Lista de productos");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addProductButton.setText("<html><center>Añadir<br />producto</center></html>");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        herramientasButton.setText("Herramientas");
        herramientasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                herramientasButtonActionPerformed(evt);
            }
        });

        removedoresButton.setText("Removedores");
        removedoresButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedoresButtonActionPerformed(evt);
            }
        });

        esmaltesButton.setText("Esmaltes");
        esmaltesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esmaltesButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setMinimumSize(new java.awt.Dimension(0, 0));

        esmaltesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de esmalte", "Color", "Efecto", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(esmaltesTable);

        herramientasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de herramienta", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(herramientasTable);

        removedoresTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de removedor", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(removedoresTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(9, 9, 9))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removedoresButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(esmaltesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(herramientasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(esmaltesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removedoresButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(herramientasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cambiarTamanio() {
        if (this.tablesOpened == 0) {
            this.setSize(157, 530);
        } else {
            //Cambiar.
            this.setSize(800 + tablesOpened, 600);
        }
    }

    private void esmaltesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esmaltesButtonActionPerformed
        if (esmaltesButton.isSelected()) {
            this.tablesOpened++;
            this.jScrollPane2.setVisible(true);
        } else {
            this.tablesOpened--;
            this.jScrollPane2.setVisible(false);
        }
        cambiarTamanio();
    }//GEN-LAST:event_esmaltesButtonActionPerformed

    private void removedoresButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedoresButtonActionPerformed
        if (removedoresButton.isSelected()) {
            this.tablesOpened++;
            this.jScrollPane4.setVisible(true);
        } else {
            this.tablesOpened--;
            this.jScrollPane4.setVisible(false);
        }
        cambiarTamanio();
    }//GEN-LAST:event_removedoresButtonActionPerformed

    private void herramientasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_herramientasButtonActionPerformed
        if (herramientasButton.isSelected()) {
            this.tablesOpened++;
            this.jScrollPane3.setVisible(true);
        } else {
            this.tablesOpened--;
            this.jScrollPane3.setVisible(false);
        }
        cambiarTamanio();
    }//GEN-LAST:event_herramientasButtonActionPerformed

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed
        NuevoProducto nuevoProducto = new NuevoProducto(null, true);
        nuevoProducto.setVisible(true);
    }//GEN-LAST:event_addProductButtonActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        this.cargarDatos();
    }//GEN-LAST:event_formWindowGainedFocus


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductButton;
    private javax.swing.JToggleButton esmaltesButton;
    private javax.swing.JTable esmaltesTable;
    private javax.swing.JToggleButton herramientasButton;
    private javax.swing.JTable herramientasTable;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToggleButton removedoresButton;
    private javax.swing.JTable removedoresTable;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
