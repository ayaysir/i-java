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

import prj.ijava.dto.Comment;
import prj.ijava.dto.NavInfo;
import prj.ijava.dto.Qna;
import prj.ijava.dto.Reply;

public class QnaDAO {

	private Connection getConnection()
			throws SQLException, NamingException {

		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/oracle");
		return ds.getConnection();

	}


	public List<Qna> getListByRange(int startIndex, int endIndex) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from (SELECT q.*, v.nickname, ROW_NUMBER() OVER(ORDER BY writed_date DESC) AS NUM FROM qna q, member_adv v where v.email = q.writer_email) s where (s.num between ? and ?)";

		List<Qna> list = new ArrayList<>();

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, startIndex);
			pstat.setInt(i++, endIndex);		

			ResultSet rs = pstat.executeQuery();

			while(rs.next()) {
				long seq = rs.getLong(1);
				String title = rs.getString(2);
				String ctMain = rs.getString(3);
				String ctFunction = rs.getString(4);
				String ctResult = rs.getString(5);
				String ctEtc = rs.getString(6);
				String writerEmail = rs.getString(7);
				String writedDate = rs.getDate(8).toString();
				int readCount = rs.getInt(9);
				int likeCount = rs.getInt(10);
				String nickname = rs.getString(11);
				list.add(new Qna(seq, title, ctMain, ctFunction, ctResult, ctEtc, writerEmail, writedDate, readCount, likeCount, nickname));
			}

			return list;


		} catch(SQLException e) {
			e.printStackTrace();			
			throw e;
		} finally {
			con.close();
		}

	}

	public Qna getOneQna(long readSeq) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select q.*, nickname from qna q, member_adv v where v.email = q.writer_email and q.seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			pstat.setLong(1, readSeq);

			/*
			 * 	SEQ          NOT NULL NUMBER        
				TITLE        NOT NULL VARCHAR2(100) 
				CT_MAIN               CLOB          
				CT_FUNCTION           CLOB          
				CT_RESULT             CLOB          
				CT_ETC                CLOB          
				WRITER_EMAIL NOT NULL VARCHAR2(100) 
				WRITED_DATE  NOT NULL DATE          
				LIKE_COUNT            NUMBER        
				READ_COUNT            NUMBER   
			 */

			ResultSet rs = pstat.executeQuery();

			Qna q = null;
			if(rs.next()) {				
				long seq = rs.getLong(1);
				String title = rs.getString(2);
				String ctMain = rs.getString(3);
				String ctFunction = rs.getString(4);
				String ctResult = rs.getString(5);
				String ctEtc = rs.getString(6);
				String writerEmail = rs.getString(7);
				String writedDate = rs.getString(8);
				int likeCount = rs.getInt(9);
				int readCount = rs.getInt(10);
				String nickname = rs.getString(11);
				q = new Qna(seq, title, ctMain, ctFunction, ctResult, ctEtc, writerEmail, writedDate, likeCount, readCount, nickname);
			}

			return q;


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

		String sql = "update qna set read_count = ? where seq = ?";

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
	public int insertAnQna(Qna q) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "insert into qna values(q_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate, default, default)";

		/*	SEQ          NOT NULL NUMBER        
			TITLE        NOT NULL VARCHAR2(100) 
			CT_MAIN               CLOB          
			CT_FUNCTION           CLOB          
			CT_RESULT             CLOB          
			CT_ETC                CLOB          
			WRITER_EMAIL NOT NULL VARCHAR2(100) 
			WRITED_DATE  NOT NULL DATE          
			LIKE_COUNT            NUMBER        
			READ_COUNT            NUMBER        
		 */

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setString(i++, q.getTitle());
			pstat.setString(i++, q.getCtMain());
			pstat.setString(i++, q.getCtFunction());
			pstat.setString(i++, q.getCtResult());
			pstat.setString(i++, q.getCtEtc());
			pstat.setString(i++, q.getWriterEmail());

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
	public int insertAnReply(Reply r) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "insert into qna_reply values(qr_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate, default)";

		/*
		 * REP_SEQ      NOT NULL NUMBER        
		QNA_SEQ      NOT NULL NUMBER        
		CT_MAIN               CLOB          
		CT_FUNCTION           CLOB          
		CT_RESULT             CLOB          
		CT_ETC                CLOB          
		WRITER_EMAIL NOT NULL VARCHAR2(100) 
		WRITED_DATE  NOT NULL DATE          
		LIKE_COUNT            NUMBER      
		 */

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, r.getQnaSeq());
			pstat.setString(i++, r.getCtMain());
			pstat.setString(i++, r.getCtFunction());
			pstat.setString(i++, r.getCtResult());
			pstat.setString(i++, r.getCtEtc());
			pstat.setString(i++, r.getWriterEmail());

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

	public int getQnaCount() throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "select count(*) from qna";		

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{	

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
	public List<NavInfo> getPageNavInfo(int recordCountPerPage, int naviCountPerPage, int currentPage) throws SQLException, NamingException {
		Connection con = this.getConnection();

		try
		{
			//시작
			int pageTotalCount = 0; // 전체가 몇 페이지로 구성될것인지?
			int recordTotalCount = this.getQnaCount();

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

		String sql = "insert into qna_comment values(qc_seq.nextval, ?, ?, ?, sysdate)";


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

		String sql = "select * from QNA_COMMENT where QNA_SEQ = ? order by WRITED_DATE";

		List<Comment> list = new ArrayList<>();

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{

			pstat.setLong(1, seq);
			/* comment_seq number primary key,
			    board_seq number not null,
			    contents varchar(400),
			    writer_email varchar(100) not null,
			    writed_date date not null
			 */

			ResultSet rs = pstat.executeQuery();

			while(rs.next()) {
				long commentSeq = rs.getLong(1);
				long qnaSeq = rs.getLong(2);
				String contents = rs.getString(3);
				String writerEmail = rs.getString(4);
				String writedDate = rs.getString(5);

				list.add(new Comment(commentSeq, qnaSeq, contents, writerEmail, writedDate));
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
	public int deleteAnQna(int seq, String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "delete from qna where seq=? and writer_email = ?";


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

	// UPDATE
	public int deleteAnReply(int repSeq, String email) throws SQLException, NamingException {
		Connection con = this.getConnection();

		String sql = "delete from qna_reply where rep_seq=? and writer_email = ?";


		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, repSeq);
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

	public List<Integer> getCommentCounts(int startIndex, int endIndex) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select (select count(*) from qna_comment group by qna_seq having qna_seq = s.seq) from (SELECT q.*, ROW_NUMBER() OVER(ORDER BY q.seq DESC) AS NUM FROM qna q) s where (s.num between ? and ?)";


		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, startIndex);
			pstat.setInt(i++, endIndex);

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

	public List<Reply> getReplies(long seq) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from qna_reply where qna_seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);

			ResultSet rs = pstat.executeQuery();
			List<Reply> list = new ArrayList<Reply>();

			while(rs.next())
			{
				long repSeq = rs.getLong(1);
				long qnaSeq = rs.getLong(2);
				String ctMain = rs.getString(3);
				String ctFunction = rs.getString(4);
				String ctResult = rs.getString(5);
				String ctEtc = rs.getString(6);
				String writerEmail = rs.getString(7);
				String writedDate = rs.getString(8);
				int likeCount = rs.getInt(9);
				list.add(new Reply(repSeq, qnaSeq, ctMain, ctFunction, ctResult, ctEtc, writerEmail, writedDate, likeCount));
			}
			return list;


		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	public int getReplyCount(long seq) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select count(*) from qna_reply where qna_seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);

			ResultSet rs = pstat.executeQuery();

			int count = 0;
			if(rs.next())
			{
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



	// 라이크 0: 라이크 수치 수정
	public int updateLikeCount(long seq, int count) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "update qna set like_count = ? where seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, count);
			pstat.setLong(i++, seq);

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

	// 라이크 1: 테이블에 기존 흔적이 있나 찾기
	public boolean isThereLike(long seq, String email) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from qna_like_status where qna_seq=? and email=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);
			pstat.setString(i++, email);

			int result = pstat.executeUpdate();

			return (result == 1 ? true : false);


		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	// 라이크 2-1: 없다면 새로 기록 추가하기
	public int insertAnLikeStatus(long seq, String email) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "insert into qna_like_status values(?, ?)";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);
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

	// 라이크 2-2: 있다면 기록 지우기
	public int deleteAnLikeStatus(long seq, String email) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "delete from qna_like_status where qna_seq=? and email=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);
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

	// =================== 리플 라이크 ====================================


	// 리플 라이크 0: 라이크 수치 수정
	public int updateReplyLikeCount(long seq, int count) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "update qna_reply set like_count = ? where rep_seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setInt(i++, count);
			pstat.setLong(i++, seq);

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

	public int getReplyLikeCount(long seq) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select like_count from qna_reply where rep_seq = ?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);

			ResultSet rs = pstat.executeQuery();

			int likeCount = 0;
			if(rs.next())
				likeCount = rs.getInt(1);

			return likeCount;


		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	// 리플 라이크 1: 테이블에 기존 흔적이 있나 찾기
	public boolean isThereReplyLike(long seq, String email) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "select * from qrp_like_status where rep_seq=? and email=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);
			pstat.setString(i++, email);

			int result = pstat.executeUpdate();

			return (result == 1 ? true : false);


		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}

	// 리플 라이크 2-1: 없다면 새로 기록 추가하기
	public int insertAnReplyLikeStatus(long seq, String email) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "insert into qrp_like_status values(?, ?)";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);
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

	// 리플 라이크 2-2: 있다면 기록 지우기
	public int deleteAnReplyLikeStatus(long seq, String email) throws SQLException, NamingException {

		Connection con = this.getConnection();

		String sql = "delete from qrp_like_status where rep_seq=? and email=?";

		try(PreparedStatement pstat = con.prepareStatement(sql);)
		{
			int i = 1;
			pstat.setLong(i++, seq);
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

}

