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

import prj.ijava.dto.Article;
import prj.ijava.dto.Comment;
import prj.ijava.dto.NavInfo;

public class BoardDAO {

	private Connection getConnection()
			throws SQLException, NamingException {

		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/oracle");
		return ds.getConnection();

	}

	public List<Article> getAllArticlesList() throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select b.*, nickname from board b, member_adv v where v.email = b.writer_email order by writed_date desc";

		List<Article> list = new ArrayList<>();

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			/*
			 * BOARD_ID     NOT NULL VARCHAR2(10)   
				CATEGORY              VARCHAR2(20)   
				SEQ          NOT NULL NUMBER         
				TITLE        NOT NULL VARCHAR2(100)  
				CONTENTS              VARCHAR2(4000) 
				WRITER_EMAIL NOT NULL VARCHAR2(100)  
				WRITED_DATE  NOT NULL DATE           
				READ_COUNT            NUMBER  
			 */

			ResultSet rs = pstat.executeQuery();

			while(rs.next()) {
				String boardId = rs.getString(1);
				String category = rs.getString(2);
				long seq = rs.getLong(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				String writerEmail = rs.getString(6);
				String writedDate = rs.getDate(7).toString();
				long readCount = rs.getLong(8);
				list.add(new Article(boardId, category, seq, title, contents, writerEmail, writedDate, readCount, rs.getString(9)));
			}

			return list;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}

	public List<Article> getListByBoardId(String targetBoardId) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select b.*, nickname from board b, member_adv v where v.email = b.writer_email and b.board_id=? order by writed_date desc";

		List<Article> list = new ArrayList<>();

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setString(1, targetBoardId);

			ResultSet rs = pstat.executeQuery();

			while(rs.next()) {
				String boardId = rs.getString(1);
				String category = rs.getString(2);
				long seq = rs.getLong(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				String writerEmail = rs.getString(6);
				String writedDate = rs.getDate(7).toString();
				long readCount = rs.getLong(8);
				list.add(new Article(boardId, category, seq, title, contents, writerEmail, writedDate, readCount, rs.getString(9)));
			}

			return list;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}

	public List<Article> getListByBoardId(String targetBoardId, int startIndex, int endIndex) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from (SELECT b.*, v.nickname, ROW_NUMBER() OVER(ORDER BY writed_date DESC) AS NUM "
				+ "FROM board b, member_adv v where v.email = b.writer_email) s "
				+ "where (s.num between ? and ?) and "
				+ "board_id=?";

		List<Article> list = new ArrayList<>();

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, startIndex);
			pstat.setInt(i++, endIndex);
			pstat.setString(i++, targetBoardId);			

			ResultSet rs = pstat.executeQuery();

			while(rs.next()) {
				String boardId = rs.getString(1);
				String category = rs.getString(2);
				long seq = rs.getLong(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				String writerEmail = rs.getString(6);
				String writedDate = rs.getDate(7).toString();
				long readCount = rs.getLong(8);
				list.add(new Article(boardId, category, seq, title, contents, writerEmail, writedDate, readCount, rs.getString(9)));
			}

			return list;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}

	public Article getOneArticle(long readSeq) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select b.*, nickname from board b, member_adv v where v.email = b.writer_email and b.seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setLong(1, readSeq);

			/*
			 * BOARD_ID     NOT NULL VARCHAR2(10)   
				CATEGORY              VARCHAR2(20)   
				SEQ          NOT NULL NUMBER         
				TITLE        NOT NULL VARCHAR2(100)  
				CONTENTS              VARCHAR2(4000) 
				WRITER_EMAIL NOT NULL VARCHAR2(100)  
				WRITED_DATE  NOT NULL DATE           
				READ_COUNT            NUMBER  
			 */

			ResultSet rs = pstat.executeQuery();

			Article a = null;
			if(rs.next()) {
				String boardId = rs.getString(1);
				String category = rs.getString(2);
				long seq = rs.getLong(3);
				String title = rs.getString(4);
				String contents = rs.getString(5);
				String writerEmail = rs.getString(6);
				String writedDate = rs.getString(7);
				long readCount = rs.getLong(8);
				a = new Article(boardId, category, seq, title, contents, writerEmail, writedDate, readCount, rs.getString(9));
			}

			return a;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}


	}

	// UPDATE
	public int updateReadCount(long seq, long count) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "update board set read_count = ? where seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setLong(1, count);
			pstat.setLong(2, seq);

			/*
			 * BOARD_ID     NOT NULL VARCHAR2(10)   
				CATEGORY              VARCHAR2(20)   
				SEQ          NOT NULL NUMBER         
				TITLE        NOT NULL VARCHAR2(100)  
				CONTENTS              VARCHAR2(4000) 
				WRITER_EMAIL NOT NULL VARCHAR2(100)  
				WRITED_DATE  NOT NULL DATE           
				READ_COUNT            NUMBER  
			 */

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

	// UPDATE
	public int insertAnArticle(Article a) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "insert into board values(?, '', board_seq.nextval, ?, ?, ?, sysdate, default)";

		/*
		 * BOARD_ID     NOT NULL VARCHAR2(10)   
			CATEGORY              VARCHAR2(20)   
			SEQ          NOT NULL NUMBER         
			TITLE        NOT NULL VARCHAR2(100)  
			CONTENTS              VARCHAR2(4000) 
			WRITER_EMAIL NOT NULL VARCHAR2(100)  
			WRITED_DATE  NOT NULL DATE           
			READ_COUNT            NUMBER      
		 */

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setString(i++, a.getBoardId());
			pstat.setString(i++, a.getTitle());
			pstat.setString(i++, a.getContents());
			pstat.setString(i++, a.getWriterEmail());

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

	public int getBoardCount(String boardId) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select count(*) from board where board_id=?";		

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setString(i++, boardId);

			ResultSet rs = pstat.executeQuery();

			int result = 0;
			if(rs.next()) {
				result = rs.getInt(1);
			}
			return result;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	/** 
	 * @param boardId
	 * @param recordCountPerPage: // 한 페이지에 게시글이 몇개 보일건지
	 * @param naviCountPerPage: // 한 페이지에서 내비게이터가 몇 개씩 보일건지
	 * @param currentPage
	 * @throws SQLException
	 * @throws NamingException
	 */
	public List<NavInfo> getPageNavInfo(String boardId, int recordCountPerPage, int naviCountPerPage, int currentPage) throws SQLException, NamingException {
		Connection con = this.getConnection();

		try
		{
			//시작
			int pageTotalCount = 0; // 전체가 몇 페이지로 구성될것인지?
			int recordTotalCount = this.getBoardCount(boardId);

			System.out.println("@@: recordTotalCount: " + recordTotalCount);

			if(recordTotalCount % recordCountPerPage > 0) {
				pageTotalCount = recordTotalCount / recordCountPerPage + 1;
			} else {
				pageTotalCount = recordTotalCount / recordCountPerPage;
			}		

			System.out.println("@@: pageTotalCount: " + pageTotalCount);

			// 현재 페이지 검증용 코드
			if (currentPage < 1) {
				currentPage = 1;
			} else if (currentPage > pageTotalCount) {
				currentPage = pageTotalCount;
			}

			// 내비게이터가 시작, 끝나는 값?
			int startNavi =  ( (currentPage - 1) / naviCountPerPage ) * naviCountPerPage + 1;
			int endNavi = startNavi + (naviCountPerPage - 1);

			if (endNavi > pageTotalCount) {
				endNavi = pageTotalCount;
			}

			boolean needPrev = true;
			boolean needNext = true;

			if(startNavi == 1)	needPrev = false;
			if(endNavi == pageTotalCount)	needNext = false;

			List<NavInfo> output = new ArrayList<>();

			output.add(new NavInfo("&laquo;", String.valueOf(startNavi - 1), false, !needPrev));		    
			for (int s = startNavi; s <= endNavi; s++) {
				if( s == currentPage )
					output.add(new NavInfo(s+"", s+"", true, false));
				else
					output.add(new NavInfo(s+"", s+""));
			}
			output.add(new NavInfo("&raquo;", String.valueOf(endNavi + 1), false, !needNext));	

			return output;

		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	} 

	// UPDATE
	public int insertAnComment(Comment c) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "insert into board_comment values(b_comment_seq.nextval, ?, ?, ?, sysdate)";


		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, c.getBoardSeq());
			pstat.setString(i++, c.getContents());
			pstat.setString(i++, c.getWriterEmail());

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

	public List<Comment> getComments(long seq) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from BOARD_COMMENT where BOARD_SEQ = ? order by WRITED_DATE";

		List<Comment> list = new ArrayList<>();

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			
			pstat.setLong(1, seq);
			/*
			 * comment_seq number primary key,
			    board_seq number not null,
			    contents varchar(400),
			    writer_email varchar(100) not null,
			    writed_date date not null
			 */

			ResultSet rs = pstat.executeQuery();

			while(rs.next()) {
				long commentSeq = rs.getLong(1);
				long boardSeq = rs.getLong(2);
				String contents = rs.getString(3);
				String writerEmail = rs.getString(4);
				String writedDate = rs.getString(5);
				
				list.add(new Comment(commentSeq, boardSeq, contents, writerEmail, writedDate));
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
	public int deleteAnArticle(int seq, String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "delete from board where seq=? and writer_email = ?";


		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, seq);
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
	
	public List<Integer> getCommentCountsByBoardId(String targetBoardId, int startIndex, int endIndex) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		String sql = "select (select count(*) from board_comment group by board_seq having board_seq = s.seq) from (SELECT b.*, ROW_NUMBER() OVER(ORDER BY writed_date DESC) AS NUM FROM board b) s where (s.num between ? and ?) and board_id=?";


		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, startIndex);
			pstat.setInt(i++, endIndex);
			pstat.setString(i++, targetBoardId);
			
			ResultSet rs = pstat.executeQuery();
			List<Integer> list = new ArrayList<Integer>();
			
			while(rs.next())
			{
				int c = rs.getInt(1);
				list.add(c);
			}
			return list;


		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public List<Integer> getLevelsByBoardId(String targetBoardId, int startIndex, int endIndex) throws SQLException, NamingException {
		
		Connection con = this.getConnection();

		String sql = "select s.member_level from (SELECT v.MEMBER_LEVEL, b.*, ROW_NUMBER() OVER(ORDER BY writed_date DESC) AS NUM FROM board b, member_adv v where b.writer_email = v.email) s where (s.num between ? and ?) and board_id=?";


		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, startIndex);
			pstat.setInt(i++, endIndex);
			pstat.setString(i++, targetBoardId);
			
			ResultSet rs = pstat.executeQuery();
			List<Integer> list = new ArrayList<Integer>();
			
			while(rs.next())
			{
				int l = rs.getInt(1);
				list.add(l);
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

