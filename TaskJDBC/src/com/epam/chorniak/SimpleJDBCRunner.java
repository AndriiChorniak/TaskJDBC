package com.epam.chorniak;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SimpleJDBCRunner {
	public static void connectCatalog(){

		//String url = "jdbc:mysql://localhost/filesystem";
				//Properties prop = new Properties();
				//prop.put("user", "roota");
				//prop.put("password", "pass");
				//prop.put("autoReconect", "true");
				//prop.put("characterEncoding", "UTF-8");
				//prop.put("useUnicode", "true");
				Connection cn = null;
				try {// 1����
					// cn=DriverManager.getConnection(url,prop);
					cn = ConnectorDB.getConnection();
					Statement st = null;
					try {// 2����
						st = cn.createStatement();
						ResultSet rs = null;
						try {// 3����
							rs = st.executeQuery("SELECT *FROM catalog");
							ArrayList<Catalog> lst = new ArrayList<>();
							while (rs.next()) {
								int id = rs.getInt(1);
								String parentCatalogOfCatalog = rs.getString(2);
								String nameOfCatalog = rs.getString(3);
								lst.add(new Catalog(id, parentCatalogOfCatalog,
										nameOfCatalog));
							}
							if (lst.size() > 0) {
								System.out.println(lst);
							} else {
								System.out.println("Not found");
							}
						} finally {// ��� �������� �����
							/*
							 * ������� ResultSet, ���� �� ��� ������ ��� ������
							 * ��������� �� ����� ������ �� ���� ������
							 */
							if (rs != null) {
								rs.close();
							} else {

								System.err.println("������ �� ����� ������ �� ��");
							}
						}
					} finally {
						/*
						 * ������� Statement, ���� �� ��� ������ ��� ������ ��������� ��
						 * ����� �������� Statement
						 */
						if (st != null) {
							st.close();
						} else {
							System.err.println("Statement �� ������");
						}
					}
				} catch (SQLException e) {// ��� ������� ����� try
					System.err.println("DB connection error: " + e);
					/*
					 * ����� ���� ��������� ������ SQLExeption
					 */
				} finally {
					/* * ������� Connection,���� �� ��� ������ */
				}
				if (cn != null) {
					try {
						cn.close();
					} catch (SQLException e) {// ��� ������� ����� try
						System.err.println("Connection close error: " + e);
					}
					CatalogDAO cd = new CatalogDAO();
					
				}

	}
	public static void connectFile(){
		//String url = "jdbc:mysql://localhost/filesystem";
		//Properties prop = new Properties();
		//prop.put("user", "roota");
		//prop.put("password", "pass");
		//prop.put("autoReconect", "true");
		//prop.put("characterEncoding", "UTF-8");
		//prop.put("useUnicode", "true");
		Connection cn = null;
		try {// 1����
			// cn=DriverManager.getConnection(url,prop);
			cn = ConnectorDB.getConnection();
			Statement st = null;
			try {// 2����
				st = cn.createStatement();
				ResultSet rs = null;
				try {// 3����
					rs = st.executeQuery("SELECT *FROM file");
					ArrayList<File> lst = new ArrayList<>();
					while (rs.next()) {
						int id = rs.getInt(1);
						String parentCatalogOfCatalog = rs.getString(2);
						String nameOfCatalog = rs.getString(3);
						int volume = rs.getInt(4);
						lst.add(new File(id, parentCatalogOfCatalog,
								nameOfCatalog, volume));
					}
					if (lst.size() > 0) {
						System.out.println(lst);
					} else {
						System.out.println("Not found");
					}
				} finally {// ��� �������� �����
					/*
					 * ������� ResultSet, ���� �� ��� ������ ��� ������
					 * ��������� �� ����� ������ �� ���� ������
					 */
					if (rs != null) {
						rs.close();
					} else {

						System.err.println("������ �� ����� ������ �� ��");
					}
				}
			} finally {
				/*
				 * ������� Statement, ���� �� ��� ������ ��� ������ ��������� ��
				 * ����� �������� Statement
				 */
				if (st != null) {
					st.close();
				} else {
					System.err.println("Statement �� ������");
				}
			}
		} catch (SQLException e) {// ��� ������� ����� try
			System.err.println("DB connection error: " + e);
			/*
			 * ����� ���� ��������� ������ SQLExeption
			 */
		} finally {
			/* * ������� Connection,���� �� ��� ������ */
		}
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {// ��� ������� ����� try
				System.err.println("Connection close error: " + e);
			}
		}
		FileDAO fd = new FileDAO();

	}

	public static void main(String[] args) {
		String connection="Catalog";
		switch(connection){
		case "Catalog":
			connectCatalog();
			break;
		case "File":
			connectFile();
			break;
		}
	}

}
