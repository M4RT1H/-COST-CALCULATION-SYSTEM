package ccappclient.view.company;


public class EditCompanyDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditDialog
     */
    public EditCompanyDialog(java.awt.Frame parent, boolean modal) {
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
        SocialChargesField = new javax.swing.JTextField();
        PercentLabel2 = new javax.swing.JLabel();
        ManufCostsField = new javax.swing.JTextField();
        PercentLabel3 = new javax.swing.JLabel();
        GenrunCostsField = new javax.swing.JTextField();
        PercentLabel4 = new javax.swing.JLabel();
        InsurCostsField = new javax.swing.JTextField();
        PercentLabel5 = new javax.swing.JLabel();
        CompanyNameLabel = new javax.swing.JLabel();
        VATLabel = new javax.swing.JLabel();
        CompanyLocationLabel = new javax.swing.JLabel();
        SocialChargesLabel = new javax.swing.JLabel();
        FormTitleLabel = new javax.swing.JLabel();
        ManufCostsLabel = new javax.swing.JLabel();
        CompanyNameField = new javax.swing.JTextField();
        GenrunCostsLabel = new javax.swing.JLabel();
        CompanyLocationField = new javax.swing.JTextField();
        InsurCostsLabel = new javax.swing.JLabel();
        VATField = new javax.swing.JTextField();
        PercentLabel1 = new javax.swing.JLabel();
        OKButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        SocialChargesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SocialChargesFieldActionPerformed(evt);
            }
        });

        PercentLabel2.setText("%");

        PercentLabel3.setText("%");

        PercentLabel4.setText("%");

        PercentLabel5.setText("%");

        CompanyNameLabel.setText("????????????????:");

        VATLabel.setText("??????:");

        CompanyLocationLabel.setText("??????????:");

        SocialChargesLabel.setText("???????????????????? ???? ??????. ??????????:");

        FormTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        FormTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FormTitleLabel.setText("???????????? ?? ??????????????????????");

        ManufCostsLabel.setText("??????????????????????????????. ??????????????:");

        CompanyNameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CompanyNameField.setToolTipText("");

        GenrunCostsLabel.setText("????????????????????????. ??????????????:");

        CompanyLocationField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CompanyLocationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompanyLocationFieldActionPerformed(evt);
            }
        });

        InsurCostsLabel.setText("????????. ??????????. ???? ????????. ??????????????:");

        VATField.setToolTipText("");

        PercentLabel1.setText("%");

        OKButton.setText("OK");
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CompanyNameLabel)
                    .addComponent(CompanyLocationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(VATLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SocialChargesLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ManufCostsLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(GenrunCostsLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(InsurCostsLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(VATField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SocialChargesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ManufCostsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GenrunCostsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(InsurCostsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PercentLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PercentLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PercentLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PercentLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PercentLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addComponent(CompanyNameField)
                    .addComponent(CompanyLocationField))
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FormTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {GenrunCostsField, InsurCostsField, ManufCostsField, SocialChargesField, VATField});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FormTitleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CompanyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CompanyNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CompanyLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CompanyLocationLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VATField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VATLabel)
                    .addComponent(PercentLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SocialChargesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SocialChargesLabel)
                    .addComponent(PercentLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ManufCostsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ManufCostsLabel)
                    .addComponent(PercentLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GenrunCostsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GenrunCostsLabel)
                    .addComponent(PercentLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InsurCostsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InsurCostsLabel)
                    .addComponent(PercentLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void SocialChargesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SocialChargesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SocialChargesFieldActionPerformed

    private void CompanyLocationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompanyLocationFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CompanyLocationFieldActionPerformed


    public String getCompanyName() {
        return CompanyNameField.getText();
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyNameField.setText(CompanyName);
    }

    public String getCompanyLocation() {
        return CompanyLocationField.getText();
    }

    public void setCompanyLocation(String CompanyLocation) {
        this.CompanyLocationField.setText(CompanyLocation);
    }

    public String getVAT() {
        return VATField.getText();
    }

    public void setVAT(String VAT) {
        this.VATField.setText(VAT);
    }

    public String getSocialCharges() {
        return SocialChargesField.getText();
    }

    public void setSocialCharges(String VAT) {
        this.SocialChargesField.setText(VAT);
    }

    public String getManufCosts() {
        return ManufCostsField.getText();
    }

    public void setManufCosts(String VAT) {
        this.ManufCostsField.setText(VAT);
    }

    public String getGenrunCosts() {
        return GenrunCostsField.getText();
    }

    public void setGenrunCosts(String VAT) {
        this.GenrunCostsField.setText(VAT);
    }

    public String getInsurCosts() {
        return InsurCostsField.getText();
    }

    public void setInsurCosts(String VAT) {
        this.InsurCostsField.setText(VAT);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditCompanyDialog dialog = new EditCompanyDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField CompanyLocationField;
    private javax.swing.JLabel CompanyLocationLabel;
    private javax.swing.JTextField CompanyNameField;
    private javax.swing.JLabel CompanyNameLabel;
    private javax.swing.JLabel FormTitleLabel;
    private javax.swing.JTextField GenrunCostsField;
    private javax.swing.JLabel GenrunCostsLabel;
    private javax.swing.JTextField InsurCostsField;
    private javax.swing.JLabel InsurCostsLabel;
    private javax.swing.JTextField ManufCostsField;
    private javax.swing.JLabel ManufCostsLabel;
    private javax.swing.JButton OKButton;
    private javax.swing.JLabel PercentLabel1;
    private javax.swing.JLabel PercentLabel2;
    private javax.swing.JLabel PercentLabel3;
    private javax.swing.JLabel PercentLabel4;
    private javax.swing.JLabel PercentLabel5;
    private javax.swing.JTextField SocialChargesField;
    private javax.swing.JLabel SocialChargesLabel;
    private javax.swing.JTextField VATField;
    private javax.swing.JLabel VATLabel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
