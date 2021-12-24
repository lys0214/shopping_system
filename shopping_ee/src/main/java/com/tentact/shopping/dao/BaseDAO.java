package com.tentact.shopping.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tentact.shopping.util.ConnDB;

public abstract class BaseDAO {
	// 写入txt
	// List<String> 表示写的字符串  boolean flag表示是否覆盖的标记
	public boolean writeTxt(File file,List<String> listStr,boolean flag) {
		// 写入txt
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			// 是否覆盖
			fw = new FileWriter(file,flag);
			bw = new BufferedWriter(fw);
			// 根据flag来判断操作
			if(flag) {
				if(listStr.size()==1) {
					// 注册
					bw.newLine();
					bw.write(listStr.get(0));
				}else {
					for (int i = 0; i < listStr.size(); i++) {
						bw.write(listStr.get(i));
						bw.newLine();
					}
				}
			}else {
				// 循环集合 写入内容
				// 最后一行没空行
				for (int i = 0; i < listStr.size()-1; i++) {
					bw.write(listStr.get(i));
					bw.newLine();
				}
				// 最后一行
				bw.write(listStr.get(listStr.size()-1));
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bw!=null) {
					bw.flush();
					bw.close();
				}
				if(fw!=null) {
					fw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	// 读取txt
	public List<String> readTxt(File file){
		// 读取txt的数据
		FileReader fr = null;
		BufferedReader br = null;
		// 定义集合 收集读取到的字符串
		List<String> listStr = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str = br.readLine();
			while(str!=null) {
				// 收集读取到的内容
				listStr.add(str);
				str = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(br!=null) {
					br.close();
				}
				if(fr!=null) {
					fr.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listStr;
	}
	// 共通的增删改方法
		public int update(String sql,Object[] arr) {
			Connection conn = ConnDB.getConnection();
			PreparedStatement pst = null;
			try {
				pst = conn.prepareStatement(sql);
				//对占位符赋值
				for (int i = 0; i < arr.length; i++) {
					pst.setObject(i+1, arr[i]);
				}
				int row = pst.executeUpdate();
				return row;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnDB.closeDB(null, pst, conn);
			}
			return 0;
		}
		// 共通查询
		public <T>T select(String sql,Object[] arr){
			Connection conn = ConnDB.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			T t = null;
			try {
				pst= conn.prepareStatement(sql);
				for (int i = 0; i < arr.length; i++) {
					pst.setObject(i+1, arr[i]);
				}
				rs = pst.executeQuery();
				// 对结果集的收集
				if(rs.next()) {
					// 收集对象的属性
					t = this.rowMapper(rs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnDB.closeDB(null, pst, conn);
			}
			return t;
		}
		// 查询结果集合
		public <T> List<T> selectAll(String sql,Object[] arr){
			List<T> list = new ArrayList<>();
			Connection conn = ConnDB.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			try {
				pst= conn.prepareStatement(sql);
				for (int i = 0; i < arr.length; i++) {
					pst.setObject(i+1, arr[i]);
				}
				rs = pst.executeQuery();
				T t = null;
				// 对结果集的收集
				while(rs.next()) {
					// 收集对象的属性
					t = this.rowMapper(rs);
					// 添加到集合
					list.add(t);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnDB.closeDB(null, pst, conn);
			}
			return list;
		}
		// 定义方法  每个查询的结果集获取对应的属性
		public  abstract <T> T rowMapper(ResultSet rs) ;
}
