package com.deepsingh44.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.deepsingh44.model.Book;

public class BookDao {
	private static BookDao bookDao = new BookDao();

	// singleton design pattern
	private BookDao() {
	}

	public static BookDao getBookDao() {
		return bookDao;
	}

	public int addBook(Book book) {
		int i = 0;
		try (Connection con = Dao.getConnection();) {
			PreparedStatement ps = con.prepareStatement("insert into books values(default,?,?,?,?)");
			ps.setString(1, book.getName());
			ps.setFloat(2, book.getPrice());
			ps.setString(3, book.getDate());
			ps.setString(4, book.getImage());
			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try (Connection con = Dao.getConnection();) {
			PreparedStatement ps = con.prepareStatement("select * from books");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setName(rs.getString(2));
				book.setPrice(rs.getFloat(3));
				book.setDate(rs.getString(4));
				book.setImage(rs.getString(5));
				books.add(book);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return books;
	}

	public List<Book> searchByName(String item) {
		List<Book> books = new ArrayList<Book>();
		try (Connection con = Dao.getConnection();) {
			PreparedStatement ps = con.prepareStatement("select * from books where name like ?");
			ps.setString(1, item + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setName(rs.getString(2));
				book.setPrice(rs.getFloat(3));
				book.setDate(rs.getString(4));
				book.setImage(rs.getString(5));
				books.add(book);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return books;
	}

}
