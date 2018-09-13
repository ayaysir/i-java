package prj.ijava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import prj.ijava.dto.BoardInfo;

public class BoardInfoDAO {

	private Connection getConnection()
			throws SQLException, NamingException {

		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/oracle");
		return ds.getConnection();

	}

	public BoardInfo getAnBoardInfo(String boardId) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from board_info where board_id=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, boardId);
		
			ResultSet rs = pstat.executeQuery();
			/*
			 * BOARD_ID     NOT NULL VARCHAR2(10) 
				BOARD_TITLE  NOT NULL VARCHAR2(30) 
				REC_PER_PAGE NOT NULL NUMBER       
				NAV_PER_PAGE NOT NULL NUMBER   
			 */
			BoardInfo bi = null;
			if(rs.next()) {
				String boardTitle = rs.getString(2);
				int recPerPage = rs.getInt(3);
				int navPerPage = rs.getInt(4);
				bi = new BoardInfo(boardId, boardTitle, recPerPage, navPerPage);
			}

			return bi;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}

	
}

