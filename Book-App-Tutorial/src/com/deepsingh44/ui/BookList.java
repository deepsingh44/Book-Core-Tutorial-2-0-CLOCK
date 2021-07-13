package com.deepsingh44.ui;

import java.awt.EventQueue;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.deepsingh44.dao.BookDao;
import com.deepsingh44.model.Book;
import com.deepsingh44.utility.Util;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class BookList extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookList frame = new BookList();
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
	public BookList() {
		setClosable(true);
		setIconifiable(true);
		setTitle("Book List");
		setSize(641, 518);

		BookDao bookDao = BookDao.getBookDao();
		books = bookDao.getAllBooks();

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		String[] cols = { "ID", "NAME", "PRICE", "DATE" };
		DefaultTableModel dt = new DefaultTableModel(cols, 0);
		table = new JTable(dt);
		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(10, 116, 607, 361);
		panel.add(jp);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 22, 607, 83);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JComboBox comboBox = new JComboBox();

		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select Sorting", "By Name", "By Price" }));
		comboBox.setBounds(452, 50, 145, 22);
		panel_1.add(comboBox);

		new Thread() {
			public void run() {
				setDataInTable();
			}
		}.start();

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String type = e.getItem().toString();

					switch (type) {

					case "By Name":
						Collections.sort(books, (o1, o2) -> o1.getName().compareTo(o2.getName()));
						setDataInTable();
						break;
					case "By Price":
						Collections.sort(books, (o1, o2) -> (o1.getPrice() > o2.getPrice()) ? 1
								: (o1.getPrice() < o2.getPrice()) ? -1 : 0);
						setDataInTable();
						break;
					}

				}
			}
		});

	}

	private List<Book> books;

	private void setDataInTable() {

		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		if (dt.getRowCount() > 0) {
			for (int i = dt.getRowCount() - 1; i >= 0; i--) {
				dt.removeRow(i);
			}
		}
		books.stream().forEach(
				book -> dt.addRow(new Object[] { book.getId(), book.getName(), book.getPrice(), book.getDate() }));
	}
}
