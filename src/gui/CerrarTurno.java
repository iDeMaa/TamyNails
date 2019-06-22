package gui;

import gestion.TamyNails;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import recursos.Esmalte;
import recursos.Herramienta;
import recursos.Producto;
import recursos.Removedor;

/**
 *
 * @author demaa
 */
public class CerrarTurno extends javax.swing.JDialog {

    private final int idTurno;
    private String query = "";
    DefaultListModel listModel = new DefaultListModel();
    private List<Producto> listaProductos = new ArrayList<>();

    public CerrarTurno(java.awt.Frame parent, boolean modal, int idTurno) {
        super(parent, modal);
        initComponents();
        this.idTurno = idTurno;
        this.turnoNumLabel.setText(turnoNumLabel.getText() + idTurno);
        this.setTitle("Tamy Nails - Cerrando turno #" + idTurno);
        productoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Esmalte", "Removedor", "Herramienta"}));
        cargarDatos();
        query = "INSERT INTO productos_turnos (productos_id_producto, turnos_id_turno) VALUES ";
        jList1.setModel(listModel);
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

        turnoNumLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tipoProductoLbl = new javax.swing.JLabel();
        tipoProductoCB = new javax.swing.JComboBox<>();
        productoLbl = new javax.swing.JLabel();
        productoCB = new javax.swing.JComboBox<>();
        agregarProductoButton = new javax.swing.JButton();
        finalizarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        turnoNumLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        turnoNumLabel.setText("Cerrando turno #");
        turnoNumLabel.setToolTipText("");

        tipoProductoLbl.setText("Tipo de producto:");

        tipoProductoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Esmalte", "Removedor", "Herramienta" }));
        tipoProductoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoProductoCBActionPerformed(evt);
            }
        });

        productoLbl.setText("Producto:");

        agregarProductoButton.setText("Agregar producto");
        agregarProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoButtonActionPerformed(evt);
            }
        });

        finalizarButton.setText("Finalizar");
        finalizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarButtonActionPerformed(evt);
            }
        });

        jList1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Productos agregados:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tipoProductoLbl)
                            .addComponent(productoLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productoCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipoProductoCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregarProductoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addGap(105, 105, 105)
                        .addComponent(finalizarButton, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(turnoNumLabel)
                .addGap(147, 147, 147))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(turnoNumLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoProductoLbl)
                    .addComponent(tipoProductoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productoLbl)
                    .addComponent(productoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarProductoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finalizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tipoProductoCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoProductoCBActionPerformed
        cargarDatos();
    }//GEN-LAST:event_tipoProductoCBActionPerformed

    private void agregarProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoButtonActionPerformed
        if (productoCB.getItemAt(productoCB.getSelectedIndex()) != null) {
            String[] aux = productoCB.getItemAt(productoCB.getSelectedIndex()).replaceAll(" ", "").split("//");
            switch (tipoProductoCB.getSelectedIndex()) {
                case 0:

                    for (Producto producto : TamyNails.getListaProductos()) {
                        Esmalte e;
                        if (producto.getClass().getSimpleName().equalsIgnoreCase("Esmalte")) {
                            e = (Esmalte) producto;
                            System.out.println(aux[0] + aux[1] + aux[2]);
                            if (aux[0].equalsIgnoreCase(e.getTipo().replaceAll(" ", "")) && aux[1].equalsIgnoreCase(e.getColor().replaceAll(" ", "")) && aux[2].equalsIgnoreCase(e.getEfecto().replaceAll(" ", ""))) {
                                query += "(" + e.getId() + "," + idTurno + "),";
                                listModel.addElement(productoCB.getItemAt(productoCB.getSelectedIndex()));
                                this.listaProductos.add(e);
                            }
                        }
                    }
                    break;

                case 1:
                    for (Producto producto : TamyNails.getListaProductos()) {
                        Removedor r;
                        if (producto.getClass().getSimpleName().equalsIgnoreCase("Removedor")) {
                            r = (Removedor) producto;
                            if (aux[0].equalsIgnoreCase(r.getTipo().replaceAll(" ", ""))) {
                                query += "(" + r.getId() + "," + idTurno + "),";
                                listModel.addElement(productoCB.getItemAt(productoCB.getSelectedIndex()));
                                this.listaProductos.add(r);
                            }
                        }
                    }
                    break;
                case 2:
                    for (Producto producto : TamyNails.getListaProductos()) {
                        Herramienta h;
                        if (producto.getClass().getSimpleName().equalsIgnoreCase("Herramienta")) {
                            h = (Herramienta) producto;
                            if (aux[0].equalsIgnoreCase(h.getTipo().replaceAll(" ", ""))) {
                                query += "(" + h.getId() + "," + idTurno + "),";
                                listModel.addElement(productoCB.getItemAt(productoCB.getSelectedIndex()));
                                this.listaProductos.add(h);
                            }
                        }
                    }
                    break;
            }
            System.out.println(query);
        }
    }//GEN-LAST:event_agregarProductoButtonActionPerformed

    private void finalizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarButtonActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás segura que queres cerrar el turno? Asegurate de poner bien los productos usados", "¿Estás segura?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            StringBuilder q = new StringBuilder(query);
            q.setCharAt(q.length() - 1, ';');
            TamyNails.cambiarEstadoTurno(idTurno, q.toString(), listaProductos);
            this.dispose();
        }
    }//GEN-LAST:event_finalizarButtonActionPerformed

    private void cargarDatos() {
        List<String> nombres = new ArrayList<>();
        switch (tipoProductoCB.getSelectedIndex()) {
            case 0:
                nombres.clear();
                for (Producto producto : TamyNails.getListaProductos()) {
                    Esmalte e;
                    if (producto.getClass().getSimpleName().equalsIgnoreCase("Esmalte")) {
                        e = (Esmalte) producto;
                        nombres.add(e.getTipo() + " // " + e.getColor() + " // " + e.getEfecto());
                    }
                }
                productoCB.setModel(new javax.swing.DefaultComboBoxModel<>(nombres.toArray(new String[nombres.size()])));
                break;
            case 1:
                nombres.clear();
                for (Producto producto : TamyNails.getListaProductos()) {
                    Removedor r;
                    if (producto.getClass().getSimpleName().equalsIgnoreCase("Removedor")) {
                        r = (Removedor) producto;
                        nombres.add(r.getTipo());
                    }
                }
                productoCB.setModel(new javax.swing.DefaultComboBoxModel<>(nombres.toArray(new String[nombres.size()])));
                break;
            case 2:
                nombres.clear();
                for (Producto producto : TamyNails.getListaProductos()) {
                    Herramienta h;
                    if (producto.getClass().getSimpleName().equalsIgnoreCase("Herramienta")) {
                        h = (Herramienta) producto;
                        nombres.add(h.getTipo());
                    }
                }
                productoCB.setModel(new javax.swing.DefaultComboBoxModel<>(nombres.toArray(new String[nombres.size()])));
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarProductoButton;
    private javax.swing.JButton finalizarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> productoCB;
    private javax.swing.JLabel productoLbl;
    private javax.swing.JComboBox<String> tipoProductoCB;
    private javax.swing.JLabel tipoProductoLbl;
    private javax.swing.JLabel turnoNumLabel;
    // End of variables declaration//GEN-END:variables
}
