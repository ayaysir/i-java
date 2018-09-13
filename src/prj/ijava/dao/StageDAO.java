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

import prj.ijava.dto.Code;
import prj.ijava.dto.Comment;
import prj.ijava.dto.NavInfo;
import prj.ijava.dto.Reply;
import prj.ijava.dto.Stage;

public class StageDAO {

	private Connection getConnection()
			throws SQLException, NamingException {

		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/oracle");
		return ds.getConnection();

	}


	public List<Stage> getAllList() throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from stage order by abs_order";

		List<Stage> list = new ArrayList<>();

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			ResultSet rs = pstat.executeQuery();
			
			/*SEQ          NOT NULL NUMBER        
			ABS_ORDER             NUMBER        
			TITLE        NOT NULL VARCHAR2(100) 
			DESCRIPTION           CLOB          
			ANS_MAIN              CLOB          
			ANS_FUNCTION          CLOB          
			ANS_RESULT            CLOB          
			ANSFIELD              NUMBER        
			ANSTYPE               NUMBER        
			GIVE_POINT            NUMBER        
			STAGE_COLOR  NOT NULL NUMBER    */

			while(rs.next()) {
				int seq = rs.getInt(1);
				int absOrder = rs.getInt(2);
				String title = rs.getString(3);
				String description = rs.getString(4);
				String ansMain = rs.getString(5);
				String ansFunction = rs.getString(6);
				String ansResult = rs.getString(7);
				int ansField = rs.getInt(8);
				int ansType = rs.getInt(9);
				int givePoint = rs.getInt(10);
				int stageColor = rs.getInt(11);
				list.add(new Stage(seq, absOrder, title, description, ansMain, ansFunction, ansResult, ansField, ansType, givePoint, stageColor));
			}

			return list;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}

	// UPDATE
	public int insertAnStage(Stage st) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "insert into stage values(st_seq.nextval, (select nvl(max(abs_order),0) + 1 from stage), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		/*	SEQ          NOT NULL NUMBER        
			ABS_ORDER             NUMBER        
			TITLE        NOT NULL VARCHAR2(100) 
			DESCRIPTION           CLOB          
			ANS_MAIN              CLOB          
			ANS_FUNCTION          CLOB          
			ANS_RESULT            CLOB          
			ANSFIELD              NUMBER        
			ANSTYPE               NUMBER        
			GIVE_POINT            NUMBER        
			STAGE_COLOR  NOT NULL NUMBER    
		 */

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			// pstat.setInt(i++, st.getAbsOrder());
			pstat.setString(i++, st.getTitle());
			pstat.setString(i++, st.getDescription());
			pstat.setString(i++, st.getAnsMain());
			pstat.setString(i++, st.getAnsFunction());
			pstat.setString(i++, st.getAnsResult());
			pstat.setInt(i++, st.getAnsField());
			pstat.setInt(i++, st.getAnsType());
			pstat.setInt(i++, st.getGivePoint());
			pstat.setInt(i++, st.getStageColor());

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
	
	public String getAnColorMarkup(int seq) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		String sql = "select markup from stage_color_markup where stage_color_index = (select stage_color from stage where seq = ?)";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setInt(1, seq);
			
			ResultSet rs = pstat.executeQuery();		

			String markup = "";
			if(rs.next()) {
				markup = rs.getString(1);
			}

			return markup;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}
	}
	
	public Stage getAnStage(int absOrder) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		String sql = "select * from stage where abs_order = ?";
		
		/*
		SEQ          NOT NULL NUMBER        
		ABS_ORDER             NUMBER        
		TITLE        NOT NULL VARCHAR2(100) 
		DESCRIPTION           CLOB          
		ANS_MAIN              CLOB          
		ANS_FUNCTION          CLOB          
		ANS_RESULT            CLOB          
		ANSFIELD              NUMBER        
		ANSTYPE               NUMBER        
		GIVE_POINT            NUMBER        
		STAGE_COLOR  NOT NULL NUMBER    
			 */

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setInt(1, absOrder);
			
			ResultSet rs = pstat.executeQuery();		

			Stage st = new Stage();
			
			while(rs.next()) {
				int i = 1;
				st.setSeq(rs.getInt(i++));
				st.setAbsOrder(rs.getInt(i++));
				st.setTitle(rs.getString(i++));
				st.setDescription(rs.getString(i++));
				st.setAnsMain(rs.getString(i++));
				st.setAnsFunction(rs.getString(i++));
				st.setAnsResult(rs.getString(i++));
				st.setAnsField(rs.getInt(i++));
				st.setAnsType(rs.getInt(i++));
				st.setGivePoint(rs.getInt(i++));
				st.setStageColor(rs.getInt(i++));
				
			}

			return st;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}
	}
	
	public int getStageCount() throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		String sql = "select count(*) from stage";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			
			ResultSet rs = pstat.executeQuery();		
			
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);				
			}

			return count;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}
	}
	
	// UPDATE
		public int insertAnStageStatus(String email, int stoppedStage) throws SQLException, NamingException {
			Connection con = this.getConnection();

			String sql = "insert into stage_progress_status values(?, ?)";
			
			try(PreparedStatement pstat = con.prepareStatement(sql);)
			{
				int i = 1;
				pstat.setString(i++, email);
				pstat.setInt(i++, stoppedStage);
				
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
		
		public int updateAnStageStatus(String email, int stoppedStage) throws SQLException, NamingException {
			Connection con = this.getConnection();

			String sql = "update stage_progress_status set stopped_stage = ? where email = ?";
			
			try(PreparedStatement pstat = con.prepareStatement(sql);)
			{
				int i = 1;
				pstat.setInt(i++, stoppedStage);
				pstat.setString(i++, email);			
				
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
		
		public boolean isStageStatusExist(String email) throws SQLException, NamingException {
			Connection con = this.getConnection();

			String sql = "select * from stage_progress_status where email = ?";
			
			try(PreparedStatement pstat = con.prepareStatement(sql);)
			{
				int i = 1;
				pstat.setString(i++, email);
				
				int result = pstat.executeUpdate();
				
				boolean isExist = false;
				if(result == 1)	isExist = true;	
				
				return isExist;

			} catch(SQLException e) {
				e.printStackTrace();
				
				throw e;
			} finally {
				con.close();
			}
		}
		
		public int getAnStageStatus(String email) throws SQLException, NamingException {
			Connection con = this.getConnection();

			String sql = "select * from stage_progress_status where email = ?";
			
			try(PreparedStatement pstat = con.prepareStatement(sql);)
			{
				int i = 1;
				pstat.setString(i++, email);
				
				ResultSet rs = pstat.executeQuery();
				
				int status = 1;
				if(rs.next()) {
					status = rs.getInt(2);
				}
				
				return status;

			} catch(SQLException e) {
				e.printStackTrace();
				
				throw e;
			} finally {
				con.close();
			}
		}


}

