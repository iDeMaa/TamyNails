package gui;

import gestion.TamyNails;
import gestion.Turno;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import recursos.Esmalte;
import recursos.Herramienta;
import recursos.Producto;
import recursos.Removedor;

/**
 *
 * @author demaa
 */
public class ListaTurnos extends javax.swing.JDialog {

    public ListaTurnos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ListaTurnos(java.awt.Frame parent, boolean modal, String nombreCliente) {
        super(parent, modal);
        initComponents();
        this.categoriasComboBox.setSelectedIndex(1);
        this.busquedaTextField.setText(nombreCliente);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        realizadoMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        verProductosMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        eliminarTurnoMenuItem1 = new javax.swing.JMenuItem();
        topPanel = new javax.swing.JPanel();
        busquedaTextField = new javax.swing.JTextField();
        categoriasComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buscarButton = new javax.swing.JButton();
        limpiarButton = new javax.swing.JButton();
        dataButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        turnosTable = new javax.swing.JTable();

        realizadoMenuItem.setText("Cambiar estado realizado");
        realizadoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizadoMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(realizadoMenuItem);
        jPopupMenu1.add(jSeparator1);

        verProductosMenuItem.setText("Ver productos usados");
        verProductosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verProductosMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(verProductosMenuItem);
        jPopupMenu1.add(jSeparator2);

        eliminarTurnoMenuItem1.setText("Eliminar turno");
        eliminarTurnoMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTurnoMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminarTurnoMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tamy Nails - Lista de turnos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        categoriasComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Número turno", "Cliente", "Descripción", "Fecha", "Hora", "Monto", "Realizado" }));

        jLabel1.setText("Categoría a buscar:");

        jLabel2.setText("Dato a buscar:");

        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar(evt);
            }
        });

        limpiarButton.setText("Limpiar");
        limpiarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarButtonActionPerformed(evt);
            }
        });

        dataButton.setText("<html><center>Obtener<br />datos</center></html>");
        dataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(categoriasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(busquedaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(buscarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(limpiarButton)
                .addGap(15, 15, 15)
                .addComponent(dataButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(limpiarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoriasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(busquedaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buscarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        turnosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número turno", "Cliente", "Descripción", "Fecha", "Hora", "Monto", "Realizado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        turnosTable.setComponentPopupMenu(jPopupMenu1);
        turnosTable.addMouseListener(new TableMouseListener(turnosTable));
        //turnosTable.setAutoCreateRowSorter(true);
        jScrollPane2.setViewportView(turnosTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void realizadoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizadoMenuItemActionPerformed
        //TODO: Marcar/Desmarcar como realizado un turno
        TamyNails.cambiarEstadoTurno((int) turnosTable.getValueAt(turnosTable.getSelectedRow(), 0));
        this.updateTable();
    }//GEN-LAST:event_realizadoMenuItemActionPerformed

    private void eliminarTurnoMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTurnoMenuItem1ActionPerformed
        TamyNails.eliminarTurno((int) turnosTable.getValueAt(turnosTable.getSelectedRow(), 0));
        this.updateTable();
    }//GEN-LAST:event_eliminarTurnoMenuItem1ActionPerformed

    private void buscar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar
        if (busquedaTextField.getText().length() == 0) {
            this.updateTable();
        } else {
            this.updateTable(TamyNails.buscarEnDB(categoriasComboBox.getSelectedIndex(), busquedaTextField.getText()));
        }
    }//GEN-LAST:event_buscar

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.buscarButton.doClick();
    }//GEN-LAST:event_formWindowOpened

    private void updateTable() {
        DefaultTableModel model = (DefaultTableModel) turnosTable.getModel();
        model.setRowCount(0);
        for (Turno turno : TamyNails.getListaTurnos()) {
            String nombre_cliente = turno.getCliente().getNombre() + " " + turno.getCliente().getApellido();
            model.addRow(new Object[]{turno.getId(), nombre_cliente, turno.getDescripcion(), turno.getFecha(), turno.getHora(), "$" + turno.getMonto(), turno.isRealizado()});
        }
    }

    private void updateTable(List<Turno> lista) {
        DefaultTableModel model = (DefaultTableModel) turnosTable.getModel();
        model.setRowCount(0);
        for (Turno turno : lista) {
            String nombre_cliente = turno.getCliente().getNombre() + " " + turno.getCliente().getApellido();
            model.addRow(new Object[]{turno.getId(), nombre_cliente, turno.getDescripcion(), turno.getFecha(), turno.getHora(), "$" + turno.getMonto(), turno.isRealizado()});
        }
    }

    private void limpiarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarButtonActionPerformed
        busquedaTextField.setText(null);
        updateTable();
    }//GEN-LAST:event_limpiarButtonActionPerformed

    private void dataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataButtonActionPerformed
        int[] result = TamyNails.obtenerDatosTurnos();
        String mensaje = "Cantidad de turnos totales: " + result[0] + "\n"
                + "Cantidad de turnos realizados: " + result[1] + "\n"
                + "Cantidad de turnos sin realizar: " + result[2] + "\n"
                + "Monto total ganado: $" + result[3];
        JOptionPane.showMessageDialog(null, mensaje, "Tamy Nails - Datos obtenidos", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_dataButtonActionPerformed

    private void verProductosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verProductosMenuItemActionPerformed
        // TODO add your handling code here:
        List<Producto> lista = TamyNails.obtenerProductosUsados((int) turnosTable.getValueAt(turnosTable.getSelectedRow(), 0));
        String mensaje = "";
        for(Producto producto : lista){
            switch(producto.getClass().getSimpleName()){
                case "Esmalte":
                    Esmalte esmalte = (Esmalte) producto;
                    mensaje += esmalte.getTipo() + " " + esmalte.getColor().toLowerCase() + "\n";
                    break;
                case "Herramienta":
                    Herramienta herramienta = (Herramienta) producto;
                    mensaje += herramienta.getTipo() + "\n";
                    break;
                case "Removedor":
                    Removedor removedor = (Removedor) producto;
                    mensaje += "Removedor "+removedor.getTipo().toLowerCase() + "\n";
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, mensaje, "Tamy Nails - Productos usados en turno #"+(int) turnosTable.getValueAt(turnosTable.getSelectedRow(), 0), JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_verProductosMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarButton;
    private javax.swing.JTextField busquedaTextField;
    private javax.swing.JComboBox<String> categoriasComboBox;
    private javax.swing.JButton dataButton;
    private javax.swing.JMenuItem eliminarTurnoMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JMenuItem realizadoMenuItem;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTable turnosTable;
    private javax.swing.JMenuItem verProductosMenuItem;
    // End of variables declaration//GEN-END:variables
}
