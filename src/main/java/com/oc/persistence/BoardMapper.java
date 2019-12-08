package com.oc.persistence;

import java.util.List;

import com.oc.vo.BoardVO;

@MyMapper
public interface BoardMapper {

	List<BoardVO> listAll();
	
	BoardVO read(int bno);
	
	List<BoardVO> pagingList(int paging);
}
