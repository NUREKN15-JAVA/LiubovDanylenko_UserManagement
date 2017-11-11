package ua.nure.kn156.danylenko.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.kn156.danylenko.User;
import ua.nure.kn156.danylenko.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {

		private MainFrame parent;
		private JPanel buttonPanel;
		private JPanel fieldPanel;
		private JButton okButton;
		private JLabel ageLabel;
		private JLabel lastNameField;
		private JLabel firstNameField;
		private User user;
		
		public DetailsPanel(MainFrame frame) {
			parent = frame;
			initialize();
		}

		private void initialize() {
			this.setName("detailsPanel"); //$NON-NLS-1$
			this.setLayout(new BorderLayout());
			this.add(getFieldPanel(), BorderLayout.NORTH);
			this.add(getButtonPanel(), BorderLayout.SOUTH);
		}

		private JPanel getButtonPanel() {
			if (buttonPanel == null) {
				buttonPanel = new JPanel();
				buttonPanel.add(getOkButton());
			}
			return buttonPanel;
		}

		private JButton getOkButton() {
			if (okButton == null) {
				okButton = new JButton();
				okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
				okButton.setName("okButton"); //$NON-NLS-1$
				okButton.setActionCommand("ok"); //$NON-NLS-1$
				okButton.addActionListener(this);
			}
			return okButton;
		}

		private JPanel getFieldPanel() {
			if (fieldPanel == null) {
				fieldPanel = new JPanel();
				fieldPanel.setLayout(new GridLayout(3, 2));
				addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameField()); //$NON-NLS-1$
				addLabeledField(fieldPanel, Messages.getString("AddPanel.last_name"), getLastNameField()); //$NON-NLS-1$
				addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getAgeLabel()); //$NON-NLS-1$
			}
			return fieldPanel;
		}

		private JLabel getAgeLabel() {
			if (ageLabel== null) {
				ageLabel = new JLabel();
				ageLabel.setName("ageLabel"); //$NON-NLS-1$
			}
			return ageLabel;
		}

		private JLabel getLastNameField() {
			if (lastNameField == null) {
				lastNameField = new JLabel();
				lastNameField.setName("lastNameField"); //$NON-NLS-1$
			}
			return lastNameField;
		}

		private void addLabeledField(JPanel panel, String labelText, JLabel textField) {
			JLabel label= new JLabel(labelText);
			label.setLabelFor(textField);
			panel.add(label);
			panel.add(textField);
			
			
		}

		private JLabel getFirstNameField() {
			if (firstNameField == null) {
				firstNameField = new JLabel();
				firstNameField.setName("firstNameField"); //$NON-NLS-1$
			}
			return firstNameField;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Messages.getString("DetailsPanel.0").equalsIgnoreCase(e.getActionCommand())) //$NON-NLS-1$
				this.setVisible(false);
			this.setVisible(false);
			parent.showBrowsePanel();
		}

		  public void setUser(User user) {
		        DateFormat format = DateFormat.getDateInstance();
		        this.user = user;
		        getFirstNameField().setText(user.getFirstName());
		        getLastNameField().setText(user.getLastName());
		        getAgeLabel().setText(String.valueOf(user.getAge()));
		    }

}
