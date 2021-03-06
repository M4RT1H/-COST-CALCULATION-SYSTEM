/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccappclient.view.materials;

/**
 *
 * @author Dennis
 */
public class EditMaterialDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditDialog
     */
    public EditMaterialDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MaterialNameLabel = new javax.swing.JLabel();
        MaterialPriceLabel = new javax.swing.JLabel();
        FormTitleLabel = new javax.swing.JLabel();
        MaterialNameField = new javax.swing.JTextField();
        MaterialPriceField = new javax.swing.JTextField();
        OKButton = new javax.swing.JButton();
        MaterialMeasureLabel = new javax.swing.JLabel();
        MaterialMeasureField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        MaterialNameLabel.setText("Название:");

        MaterialPriceLabel.setText("Цена:");

        FormTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        FormTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FormTitleLabel.setText("Данные о материале");

        MaterialNameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MaterialNameField.setToolTipText("");

        MaterialPriceField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        OKButton.setText("OK");
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });

        MaterialMeasureLabel.setText("Единицы измерения:");

        MaterialMeasureField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(MaterialNameLabel)
                            .addComponent(MaterialPriceLabel)
                            .addComponent(MaterialMeasureLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MaterialNameField)
                            .addComponent(MaterialPriceField)
                            .addComponent(MaterialMeasureField)))
                    .addComponent(FormTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FormTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaterialNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaterialNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaterialPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaterialPriceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaterialMeasureLabel)
                    .addComponent(MaterialMeasureField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(OKButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_OKButtonActionPerformed


    public String getMaterialName() {
        return MaterialNameField.getText();
    }

    public void setMaterialName(String MaterialName) {
        this.MaterialNameField.setText(MaterialName);
    }

    public String getMaterialPrice() {
        return MaterialPriceField.getText();
    }

    public void setMaterialPrice(String MaterialPrice) {
        this.MaterialPriceField.setText(MaterialPrice);
    }

    public String getMaterialMeasure() {
        return MaterialMeasureField.getText();
    }

    public void setMaterialMeasure(String MaterialMeasure) {
        this.MaterialMeasureField.setText(MaterialMeasure);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditMaterialDialog dialog = new EditMaterialDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FormTitleLabel;
    private javax.swing.JTextField MaterialMeasureField;
    private javax.swing.JLabel MaterialMeasureLabel;
    private javax.swing.JTextField MaterialNameField;
    private javax.swing.JLabel MaterialNameLabel;
    private javax.swing.JTextField MaterialPriceField;
    private javax.swing.JLabel MaterialPriceLabel;
    private javax.swing.JButton OKButton;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
