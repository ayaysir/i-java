package prj.ijava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import prj.ijava.dto.Member;
import prj.ijava.dto.MemberAdv;

public class MemberDAO {

	private Connection getConnection()
			throws SQLException, NamingException {

		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/oracle");
		return ds.getConnection();

	}

	// UPDATE
	public int insertAnMember(Member member) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		/*
		 * SEQ      NOT NULL NUMBER        
			EMAIL             VARCHAR2(100) 
			PASSWORD NOT NULL VARCHAR2(64)  
			TEL               VARCHAR2(20)  
			ZIPCODE           VARCHAR2(10)  
			ADDRESS1          VARCHAR2(200) 
			ADDRESS2          VARCHAR2(200) 
			BIRTHDAY          DATE       
		 */

		/*
		 * 포인트(long): point
		 * 
		 */

		String sql = "insert into member values(member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, default)";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, member.getEmail());
			pstat.setString(2, member.getPassword());
			pstat.setString(3, member.getTel());
			pstat.setString(4, member.getZipcode());
			pstat.setString(5, member.getAddress1());
			pstat.setString(6, member.getAddress2());
			pstat.setString(7, member.getBirthday());

			int result = pstat.executeUpdate();
			
			con.commit();

			return result;

		} catch(SQLException e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		} finally {
			con.close();
		}

	}
	
	public boolean isEmailDuplicated(String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select email from member where email=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			int result = pstat.executeUpdate();

			if(result == 1)	return true;
			else	return false;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
		
	}
	
	public int login(String email, String password) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select * from member where email=? and password=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			pstat.setString(2, password);
			
			int result = pstat.executeUpdate();			
			
			return result;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public Member getMember(String email, String password) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select * from member where email=? and password=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			pstat.setString(2, password);
			
			ResultSet rs = pstat.executeQuery();		
			Member member = new Member();
			
			/*  SEQ      NOT NULL NUMBER        
				EMAIL             VARCHAR2(100) 
				PASSWORD NOT NULL VARCHAR2(64)  
				TEL               VARCHAR2(20)  
				ZIPCODE           VARCHAR2(10)  
				ADDRESS1          VARCHAR2(200) 
				ADDRESS2          VARCHAR2(200) 
				BIRTHDAY          DATE       
			 */

			/* 포인트(long): point
			 */
			
			if(rs.next()) {
				member.setSeq(rs.getLong(1));
				member.setEmail(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setTel(rs.getString(4));
				member.setZipcode(rs.getString(5));
				member.setAddress1(rs.getString(6));
				member.setAddress2(rs.getString(7));
				member.setBirthday(rs.getDate(8).toString());
			}
			
			return member;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public long getMemberPoint(String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select point from member where email=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			
			ResultSet rs = pstat.executeQuery();		
			long result = -99;
			
			if(rs.next()) {
				result = rs.getLong(1);
			}
			
			return result;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public int updateMemberPoint(String email, int point) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		String sql = "update member set point = ? where email = ?";
		
		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			
			pstat.setInt(1, point);
			pstat.setString(2, email);
			
			int result = pstat.executeUpdate();
			
			return result;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}

	}
	
	public List<MemberAdv> getMemberPointRanking100() throws SQLException, NamingException 
	{
		Connection con = this.getConnection();

		String sql = "select v.nickname, v.photo_url, m.point, m.email from member m, member_adv v where m.email = v.email and member_level < 108 and rownum < 100 order by point desc";
		
		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			ResultSet rs = pstat.executeQuery();
			
			List<MemberAdv> list = new ArrayList<>();
			MemberAdv mv = null;
			while(rs.next()) {
				mv = new MemberAdv();
				mv.setNickname(rs.getString(1));
				mv.setPhotoUrl(rs.getString(2));
				mv.setPoint(rs.getLong(3));
				mv.setEmail(rs.getString(4));
				list.add(mv);
			}			
			return list;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
}

