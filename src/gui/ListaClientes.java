package gui;

import javax.swing.table.DefaultTableModel;
import entidades.Cliente;
import gestion.TamyNails;

/**
 *
 * @author demaa
 */
public class ListaClientes extends javax.swing.JDialog {

    public ListaClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        agregarTurnoMenuItem = new javax.swing.JMenuItem();
        verTurnosMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        eliminarMenuItem = new javax.swing.JMenuItem();
        topPanel = new javax.swing.JPanel();
        addClientButton = new javax.swing.JButton();
        verTurnosButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        clientesTable = new javax.swing.JTable();

        agregarTurnoMenuItem.setText("Agregar turno");
        agregarTurnoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarTurnoMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(agregarTurnoMenuItem);

        verTurnosMenuItem.setText("Ver turnos");
        verTurnosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verTurnosMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(verTurnosMenuItem);
        jPopupMenu1.add(jSeparator1);

        eliminarMenuItem.setText("Eliminar cliente");
        eliminarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminarMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tamy Nails - Lista de clientes");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addClientButton.setText("<html><center>Añadir<br />cliente</center></html>");
        addClientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientButtonActionPerformed(evt);
            }
        });

        verTurnosButton.setText("<html><center>Ver<br />turnos</center></html>");
        verTurnosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verTurnosButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verTurnosButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addClientButton, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(verTurnosButton, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        clientesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "N. Cliente", "Nombre", "Apellido", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clientesTable.setComponentPopupMenu(jPopupMenu1);
        clientesTable.addMouseListener(new TableMouseListener(clientesTable));
        clientesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientesTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(clientesTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
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

    //BOTÓN AÑADIR CLIENTE
    private void addClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientButtonActionPerformed
        NuevoCliente nc = new NuevoCliente(null, true);
        nc.setVisible(true);
    }//GEN-LAST:event_addClientButtonActionPerformed

    //BOTÓN VER TURNOS
    private void verTurnosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verTurnosButtonActionPerformed
        ListaTurnos listaTurnos = new ListaTurnos(null, true);
        listaTurnos.setVisible(true);
    }//GEN-LAST:event_verTurnosButtonActionPerformed

    private void clientesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesTableMouseClicked
        if (evt.getClickCount() > 1) {
            this.verTurnosMenuItemActionPerformed(new java.awt.event.ActionEvent(this, 0, "Ver turnos"));
        }
    }//GEN-LAST:event_clientesTableMouseClicked

    private void agregarTurnoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarTurnoMenuItemActionPerformed
        NuevoTurno nuevoTurno = new NuevoTurno(null, true, (int) clientesTable.getValueAt(clientesTable.getSelectedRow(), 0));
        nuevoTurno.setVisible(true);
    }//GEN-LAST:event_agregarTurnoMenuItemActionPerformed

    private void verTurnosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verTurnosMenuItemActionPerformed
        //TODO: Ver los turnos del cliente seleccionado al hacer click derecho en la tabla
        Cliente cliente = TamyNails.getListaClientes().get((int) clientesTable.getValueAt(clientesTable.getSelectedRow(), 0) - 1);
        ListaTurnos listaTurnos = new ListaTurnos(null, true, cliente.getNombre() + " " + cliente.getApellido());
        listaTurnos.setVisible(true);

    }//GEN-LAST:event_verTurnosMenuItemActionPerformed

    private void eliminarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarMenuItemActionPerformed
        TamyNails.eliminarCliente((int) clientesTable.getValueAt(clientesTable.getSelectedRow(), 0));
    }//GEN-LAST:event_eliminarMenuItemActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.updateTable();
    }//GEN-LAST:event_formWindowGainedFocus

    public void updateTable() {
        DefaultTableModel model = (DefaultTableModel) clientesTable.getModel();
        model.setRowCount(0); // "Limpio" la tabla así no me replica datos
        for (Cliente cliente : TamyNails.getListaClientes()) {
            model.addRow(new Object[]{cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono()});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClientButton;
    private javax.swing.JMenuItem agregarTurnoMenuItem;
    private javax.swing.JTable clientesTable;
    private javax.swing.JMenuItem eliminarMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel topPanel;
    private javax.swing.JButton verTurnosButton;
    private javax.swing.JMenuItem verTurnosMenuItem;
    // End of variables declaration//GEN-END:variables
}
