package com.deepsingh44.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import com.deepsingh44.dao.BookDao;
import com.deepsingh44.model.Book;
import com.deepsingh44.utility.Util;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Search extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Search() {
		setClosable(true);
		setIconifiable(true);
		setSize(641, 518);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(169, 32, 280, 30);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Search By Name");
		lblNewLabel.setBounds(34, 40, 96, 14);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Search");

		btnNewButton.setBounds(475, 36, 89, 23);
		panel.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = textField.getText();
				if (!item.isEmpty()) {

					List<Book> books= BookDao.getBookDao().searchByName(item);
					Util.warningMessage(Search.this, books.size()+"");
					
				} else {
					Util.warningMessage(Search.this, "Please enter name of book");
				}
			}
		});

	}

}
