package com.gd.sakila.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gd.sakila.mapper.BoardMapper;
import com.gd.sakila.vo.Board;
import com.gd.sakila.vo.PageParam;

@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	// boardOne 출력 메서드
	public Map<String,Object> getBoardOne(int boardId) {
		return boardMapper.selectBoardOne(boardId);
	}
	
	// board 추가 메서드
	public int addBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	// boardList 출력 메서드
	public Map<String,Object> getBoardList(int currentPage, int rowPerPage, String searchWord){

		// beginRow
		// 컨트롤러와 dao에서 준비함(서비스, 서비스에서 하는일 + 트랜잭션처리)
		// 컨트롤러에서 넘어온 매개값들(파라미터)을 가공하여 dao에 전달
		int beginRow = (currentPage - 1) * rowPerPage;
		PageParam pageParam = new PageParam();
		pageParam.setBeginRow(beginRow);
		pageParam.setRowPerPage(rowPerPage);
		pageParam.setSearchWord(searchWord);
		
		// dao 호출
		List<Board> boardList = boardMapper.selectBoardList(pageParam); // Page
		int boardTotal = boardMapper.selectBoardTotal(searchWord); // searchWord
		
		// dao의 반환값을 가공
/*		int lastPage = boardTotal/rowPerPage;
		if(boardTotal % rowPerPage != 0) {
			lastPage += 1;
		}*/
		int lastPage = (int)(Math.ceil((double)boardTotal/rowPerPage));
		
		// 디버깅
		System.out.println("beginRow : " + beginRow);
		System.out.println("lastPage : " + lastPage);
		System.out.println("boardTotal : " + boardTotal);
		System.out.println("boardList : " + boardList.toString());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardList", boardList);
/*		map.put("boardTotal", boardTotal); */
		map.put("lastPage", lastPage);
		
		// 디버깅
		System.out.println("map : " + map.toString());
		
		return map;
	}
}
