package ua.nure.kn156.danylenko.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import javax.swing.JOptionPane;


import ua.nure.kn156.danylenko.User;
import ua.nure.kn156.danylenko.db.DatabaseException;
import ua.nure.kn156.danylenko.util.Messages;

public class EditPanel extends AddPanel {
	
	
	private User user;
	
	public EditPanel (MainFrame parent) {
		super(parent);
        setName("editPanel"); //$NON-NLS-1$
	}

	private void initialize() {
		this.setName("editPanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(),BorderLayout.SOUTH);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(user);
		if ("ok".equalsIgnoreCase(e.getActionCommand())){ //$NON-NLS-1$
		user.setFirstName(getFirstNameField().getText());
		user.setLastName(getLastNameField().getText());
		DateFormat format = DateFormat.getDateInstance();
		try {
			user.setDateOfBirth(format.parse(getDateOfBirthField().getText()));
		} catch (ParseException e1) {
			getDateOfBirthField().setBackground(Color.RED);
			e1.printStackTrace();
			return;
		}
		try {
			parent.getDao().update(user);
		} catch (DatabaseException e1) {
			 JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
                     JOptionPane.ERROR_MESSAGE);		} }
		
		else if (Messages.getString("EditPanel.0").equalsIgnoreCase(e.getActionCommand())) //$NON-NLS-1$
			this.setVisible(false);
		this.setVisible(false);
		parent.showBrowsePanel();
				
	}

	  public void setUser(User user) {
	        DateFormat format = DateFormat.getDateInstance();
	        this.user = user;
	        getFirstNameField().setText(user.getFirstName());
	        getLastNameField().setText(user.getLastName());
	        getDateOfBirthField().setText(format.format(user.getDateOfBirth()));
	    }

	
}
