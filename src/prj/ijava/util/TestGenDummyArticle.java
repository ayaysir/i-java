package prj.ijava.util;

import prj.ijava.dao.BoardDAO;
import prj.ijava.dto.Article;

public class TestGenDummyArticle {

	public static void main(String[] args) {
		
		for (int i = 1; i <= 10; i++)
		{
		    for (int j = 1; j <= i; j++) {
		        System.out.print("*");
		    }
		   System.out.println();
		}
	}

}
