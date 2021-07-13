package com.deepsingh44.ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.deepsingh44.dao.BookDao;
import com.deepsingh44.model.Book;
import com.deepsingh44.utility.Util;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class AddBook extends JInternalFrame {
	private JTextField tname;
	private JTextField tprice;
	private JLabel lblNewLabel_3;
	private File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() {
		getContentPane().setBackground(new Color(255, 204, 153));
		setClosable(true);
		setIconifiable(true);
		setTitle("Add Book");
		setSize(622, 480);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 308, 428);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(Util
				.resize(new ImageIcon(AddBook.class.getResource("/com/deepsingh44/images/book_image.jpeg")), 308, 428));
		panel.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(328, 11, 268, 428);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Book Detail");
		lblNewLabel_1.setForeground(new Color(204, 0, 0));
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(38, 25, 173, 39);
		panel_1.add(lblNewLabel_1);

		tname = new JTextField();
		tname.setBounds(38, 209, 200, 31);
		panel_1.add(tname);
		tname.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Enter Book Name");
		lblNewLabel_2.setBounds(39, 191, 130, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Enter Book Price");
		lblNewLabel_2_1.setBounds(39, 251, 130, 14);
		panel_1.add(lblNewLabel_2_1);

		tprice = new JTextField();
		tprice.setColumns(10);
		tprice.setBounds(38, 269, 200, 31);
		panel_1.add(tprice);

		JLabel lblNewLabel_2_2 = new JLabel("Book Image");
		lblNewLabel_2_2.setBounds(39, 311, 130, 14);
		panel_1.add(lblNewLabel_2_2);

		JButton btnNewButton = new JButton("Browse");

		btnNewButton.setBounds(38, 325, 200, 23);
		panel_1.add(btnNewButton);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblNewLabel_3.setBounds(48, 75, 173, 105);
		panel_1.add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("Add Book");

		btnNewButton_1.setBounds(91, 394, 89, 23);
		panel_1.add(btnNewButton_1);

		// This is for Image preview
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(new FileNameExtensionFilter("Image File", "jpeg", "jpg", "png"));
				jfc.showOpenDialog(AddBook.this);
				file = jfc.getSelectedFile();
				lblNewLabel_3.setIcon(Util.resize(new ImageIcon(file.getAbsolutePath()), 173, 105));
			}
		});

		// This is for data storing
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (valid()) {

					// final code here
					Book book = new Book();
					book.setName(name);
					book.setPrice(Float.parseFloat(price));
					book.setDate(new Date(System.currentTimeMillis()).toString());
					book.setImage(uploadImage().getName());

					BookDao bd=BookDao.getBookDao();
					int i=bd.addBook(book);
					if(i>0) {
						Util.normalMessage(AddBook.this,"Book Added Successfully");
					}else {
						Util.warningMessage(AddBook.this,"Book Added Failed");
					}
					
				}
			}
		});

	}

	private File uploadImage() {
		File newfile = null;
		try {
			newfile = File.createTempFile("book", ".jpg", new File("E:\\bookimages"));
			FileOutputStream fo = new FileOutputStream(newfile);
			FileInputStream fi = new FileInputStream(file.getAbsolutePath());
			int i = 0;
			while ((i = fi.read()) != -1) {
				fo.write(i);
			}
			fo.close();
			fi.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}
		return newfile;
	}

	private String name, price;

	private boolean valid() {
		name = tname.getText();
		price = tprice.getText();
		if (name.isEmpty()) {
			Util.warningMessage(AddBook.this, "please enter book name");
			tname.requestFocus();
			return false;
		} else if (price.isEmpty()) {
			Util.warningMessage(AddBook.this, "please enter book price");
			tprice.requestFocus();
			return false;
		} else if (lblNewLabel_3.getIcon() == null) {
			Util.warningMessage(AddBook.this, "please upload an image");
			return false;
		} else {
			return true;
		}
	}

}
