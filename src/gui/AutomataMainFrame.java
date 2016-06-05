/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import automata.Alphabet;
import automata.Automaton;
import automata.Symbol;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;

/**
 *
 * @author andrzej
 */
public class AutomataMainFrame extends javax.swing.JFrame {

	// --- konstruktor
	
	/**
	 * Creates new form AutomataMainFrame
	 */
	public AutomataMainFrame() {
		initComponents();
		automatons = new DefaultListModel<Automaton>();
		automatonList.setModel(automatons);
		setNewAutomatonLabel();
	}
	
	// --- metody prywatne
	
	private void setNewAutomatonLabel(){
		String res;
		if(automatonNum < UPPERCASE.length()){
			res = UPPERCASE.substring(automatonNum,automatonNum+1);
		} else {
			res = "Z" + (automatonNum - UPPERCASE.length() + 1);
		}
		++automatonNum;
		newAutomatonLabel.setText(res + "=");
	}
	
	private String getAutomatonLabel(Automaton a){
		return a.getName().substring(0, a.getName().indexOf("="));
	}
	
	private void addNewAutomaton(Automaton a, String desc){
		a.setAlphabet(alphabet);
		a.setName(newAutomatonLabel.getText() + desc);
		automatons.addElement(a);
		setNewAutomatonLabel();
	}
	
	// --- kod GUI (wygenerowany)

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regExTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        automatonList = new javax.swing.JList<>();
        createAutomatonButton = new javax.swing.JButton();
        newAutomatonLabel = new javax.swing.JLabel();
        sumButton = new javax.swing.JButton();
        determinizeButton = new javax.swing.JButton();
        acceptsPanel = new javax.swing.JPanel();
        acceptsTextField = new javax.swing.JTextField();
        acceptsButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automaty");
        setLocation(new java.awt.Point(0, 0));

        regExTextField.setToolTipText("Wpisz wyrażenie regularne, z którego chcesz stworzyć automat.");
        regExTextField.setName("regExTextField"); // NOI18N
        regExTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regExTextFieldActionPerformed(evt);
            }
        });

        outputTextArea.setEditable(false);
        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        outputTextArea.setName(""); // NOI18N
        jScrollPane1.setViewportView(outputTextArea);

        automatonList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                automatonListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(automatonList);

        createAutomatonButton.setText("Stwórz");
        createAutomatonButton.setToolTipText("Tworzy automat z podanego obok wyrażenia regularnego.");
        createAutomatonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAutomatonButtonActionPerformed(evt);
            }
        });

        sumButton.setText("Suma");
        sumButton.setToolTipText("Tworzy automat będący sumą zaznaczonych automatów.");
        sumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumButtonActionPerformed(evt);
            }
        });

        determinizeButton.setText("Determinizacja");
        determinizeButton.setToolTipText("Determinizuje każdy zaznaczony automat (dla każdego powstaje nowy).");
        determinizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                determinizeButtonActionPerformed(evt);
            }
        });

        acceptsTextField.setToolTipText("Wpisz wyraz (zawierający tylko litery z alfabetu).");
        acceptsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptsTextFieldActionPerformed(evt);
            }
        });

        acceptsButton.setText("Akceptuje?");
        acceptsButton.setToolTipText("Testuje czy zaznaczone automaty akceptują wpisane obok słowo.");
        acceptsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout acceptsPanelLayout = new javax.swing.GroupLayout(acceptsPanel);
        acceptsPanel.setLayout(acceptsPanelLayout);
        acceptsPanelLayout.setHorizontalGroup(
            acceptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptsPanelLayout.createSequentialGroup()
                .addComponent(acceptsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acceptsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        acceptsPanelLayout.setVerticalGroup(
            acceptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(acceptsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acceptsButton))
        );

        deleteButton.setText("Usuń");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(newAutomatonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regExTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createAutomatonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sumButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(determinizeButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(acceptsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(regExTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(createAutomatonButton))
                            .addComponent(newAutomatonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sumButton)
                            .addComponent(determinizeButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acceptsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	Action createAutomatonAction = new AbstractAction()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			String regEx = regExTextField.getText();
			outputTextArea.setText("Tworzenie automatu z wyrażenia regularnego:\n>> " + regEx);
			try{
				Automaton a = new Automaton(regEx);
				addNewAutomaton(a, regEx);
				regExTextField.setText("");
				outputTextArea.setText(outputTextArea.getText() + "\n\nStworzono!");
			} catch(IllegalArgumentException e){
				outputTextArea.setText(outputTextArea.getText() + "\n\nBłąd: nieprawidłowe wyrażenie regularne!");
			}
		}
	};
	
	Action acceptsAction = new AbstractAction()
	{
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt)
		{
			List<Symbol> res = new LinkedList<Symbol>();
			String word = acceptsTextField.getText();
			final int len = word.length();
			for (int i = 0; i < len; i++) {
				res.add(new Symbol(String.valueOf(word.charAt(i))));
			}
			outputTextArea.setText("");
			for(Automaton a : automatonList.getSelectedValuesList()){
				try{
					outputTextArea.append("Czy " + a.getName() + " akceptuje \"" + word + "\"?...");
					outputTextArea.append(" " + (a.accepts(res) ? "TAK" : "NIE") + "\n");
				} catch(Exception e){
					outputTextArea.append("błąd!\n");
					System.out.println(e);
				}
			}
		}
	};
	
    private void createAutomatonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAutomatonButtonActionPerformed
		createAutomatonAction.actionPerformed(evt);
    }//GEN-LAST:event_createAutomatonButtonActionPerformed

    private void automatonListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_automatonListValueChanged
		StringBuilder text = new StringBuilder();
		for(Automaton a : automatonList.getSelectedValuesList()){
			text.append(a.getName());
			text.append("\n--------------------\n");
			text.append(a.getPrint());
			text.append("====================\n\n");
		}
		outputTextArea.setText(text.toString());
    }//GEN-LAST:event_automatonListValueChanged

    private void sumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumButtonActionPerformed
		if(automatonList.getSelectedValuesList().size() < 2)
			return;

		StringBuilder text = new StringBuilder();
		text.append("Tworzenie sumy automatów:\n");
		for(Automaton a : automatonList.getSelectedValuesList()){
			text.append(" > " + a.getName() + "\n");
		}
		outputTextArea.setText(text.toString());
		
		try{
			boolean first = true;
			Automaton res = null;
			String name = "";
			for(Automaton a : automatonList.getSelectedValuesList()){
				if(first){
					res = a;
					first = false;
					name = getAutomatonLabel(a);
				} else {
					res = res.sum(a);
					name = name + "+" + getAutomatonLabel(a);
				}
			}
			addNewAutomaton(res, name);
			outputTextArea.append("\nStworzono!");
		} catch(Exception e){
			outputTextArea.append("\nBłąd!");
			System.out.println(e);
		}
    }//GEN-LAST:event_sumButtonActionPerformed

    private void determinizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_determinizeButtonActionPerformed
		outputTextArea.setText("");
		for(Automaton a : automatonList.getSelectedValuesList()){
			try{
				Automaton aDFA = a.determinize();
				addNewAutomaton(aDFA, "determ(" + getAutomatonLabel(a) + ")");
				outputTextArea.append("zdeterminizowano automat " + a.getName() + "\n");
			} catch(Exception e){
				outputTextArea.append("błąd determinizacji automatu " + a.getName() + "\n");
				System.out.println(e);
			}
		}
    }//GEN-LAST:event_determinizeButtonActionPerformed

    private void acceptsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptsButtonActionPerformed
		acceptsAction.actionPerformed(evt);
    }//GEN-LAST:event_acceptsButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
		int[] selectedIndices = automatonList.getSelectedIndices();
		for (int i = selectedIndices.length-1; i >=0; --i) {
			automatons.removeElementAt(selectedIndices[i]);
		}
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void regExTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regExTextFieldActionPerformed
        createAutomatonAction.actionPerformed(evt);
    }//GEN-LAST:event_regExTextFieldActionPerformed

    private void acceptsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptsTextFieldActionPerformed
        acceptsAction.actionPerformed(evt);
    }//GEN-LAST:event_acceptsTextFieldActionPerformed

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
			java.util.logging.Logger.getLogger(AutomataMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AutomataMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AutomataMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AutomataMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AutomataMainFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptsButton;
    private javax.swing.JPanel acceptsPanel;
    private javax.swing.JTextField acceptsTextField;
    private javax.swing.JList<Automaton> automatonList;
    private javax.swing.JButton createAutomatonButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton determinizeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel newAutomatonLabel;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JTextField regExTextField;
    private javax.swing.JButton sumButton;
    // End of variables declaration//GEN-END:variables

	// --- pozostale zmienne
	
	private DefaultListModel<Automaton> automatons;
	private int automatonNum = 0;
	private Alphabet alphabet = new Alphabet();
	
	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
}