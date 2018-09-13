package prj.ijava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import prj.ijava.dto.MemberAdv;

public class MemberAdvDAO {

	private Connection con = null;

	private Connection getConnection()
			throws SQLException, NamingException {

		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/oracle");
		return ds.getConnection();

	}

	// UPDATE
	public int updateAnAdvInfo(MemberAdv memberAdv) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		/*
		 *  email varchar(100) references member(email),
		    nickname varchar(15) unique,
		    photo_originalfn varchar(200),
		    photo_sysfn varchar(200)
		 */

		String sql = "update MEMBER_ADV set nickname=?, photo_url=? where email=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setString(i++, memberAdv.getNickname());
			pstat.setString(i++, memberAdv.getPhotoUrl());
			pstat.setString(i++, memberAdv.getEmail());

			int result = pstat.executeUpdate();
			
			con.commit();

			return result;

		} catch(SQLException e) {
			con.rollback();
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}
	
	// UPDATE
	public int insertAnAdvDummyInfo(String email) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		/*
		 *  email varchar(100) references member(email),
		    nickname varchar(15) unique,
		    photo_originalfn varchar(200),
		    photo_sysfn varchar(200)
		 */

		String sql = "insert into member_adv values(?, '닉네임' || nicknum_seq.nextval, '', '', "
				+ "'https://dummyimage.com/300x300/a33fa3/ffffff' || chr(38) || 'text=' || '닉네임', 1)";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);

			int result = pstat.executeUpdate();
			
			con.commit();

			return result;

		} catch(SQLException e) {
			con.rollback();
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}
	
	public boolean isNicknameDuplicated(String nickname) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select nickname from member_adv where nickname=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, nickname);
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
	
	public String getMemberNick(String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select nickname from member_adv v, member m where (v.email = m.email) and (m.email=?)";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			
			ResultSet rs = pstat.executeQuery();		
			
			String nickname = "";
			if(rs.next()) {
				nickname = rs.getString(1);
			}
			
			return nickname;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public String getMemberPhoto(String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select photo_url from member_adv v, member m where (v.email = m.email) and (m.email=?)";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			
			ResultSet rs = pstat.executeQuery();		
			
			String photo64 = "";
			if(rs.next()) {
				photo64 = rs.getString(1);
			}
			
			return photo64;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public String getMemberGradeName(String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select * from (select g.grade_name from member_grade g, member_adv a where g.LEVEL_NUM <= a.MEMBER_LEVEL and a.EMAIL = ? order by level_num desc) where rownum = 1";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			
			ResultSet rs = pstat.executeQuery();		
			
			String gradeName = "";
			if(rs.next()) {
				gradeName = rs.getString(1);
				System.out.println("@@" + gradeName);
			}
			
			return gradeName;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public int getMemberLevel(String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select member_level from member_adv where EMAIL = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			
			ResultSet rs = pstat.executeQuery();		
			
			int memberLevel = 0;
			if(rs.next()) {
				memberLevel = rs.getInt(1);
				System.out.println("@@" + memberLevel);
			}
			
			return memberLevel;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public String getLevelMarkup(String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select * from (select g.level_markup1 || a.MEMBER_LEVEL || g.level_markup2 from member_grade g, member_adv a where g.LEVEL_NUM <= a.MEMBER_LEVEL and a.EMAIL = ? order by level_num desc) where rownum = 1";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			
			ResultSet rs = pstat.executeQuery();		
			
			String levelMarkup = "";
			if(rs.next()) {
				levelMarkup = rs.getString(1);			
			}
			
			return levelMarkup;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
	
	public String getLevelMarkup(String email, String addQuote) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select * from (select g.level_markup1 || '" + addQuote + "' || a.MEMBER_LEVEL || g.level_markup2 from member_grade g, member_adv a where g.LEVEL_NUM <= a.MEMBER_LEVEL and a.EMAIL = ? order by level_num desc) where rownum = 1";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, email);
			
			ResultSet rs = pstat.executeQuery();		
			
			String levelMarkup = "";
			if(rs.next()) {
				levelMarkup = rs.getString(1);			
			}
			
			return levelMarkup;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
}

