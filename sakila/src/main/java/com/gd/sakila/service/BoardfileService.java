package com.gd.sakila.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gd.sakila.mapper.BoardfileMapper;
import com.gd.sakila.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅
@Service // 붙어있어야 객체가 만들어짐
@Transactional // spring에 트랜잭션기능이 있다. 어떤 메서드를 실행하다가 에러가뜨면 그 메서드가 있는 서비스 롤백
public class BoardfileService {
	
	@Autowired private BoardfileMapper boardfileMapper;
	
	// 추가 메서드
	public int addBoardfileOne(MultipartFile multipartFile, int boardId) {
		// 파라미터 디버깅
		log.debug("addBoardfileOne 파라미터 값(multipartFile) : " + multipartFile.toString());
		log.debug("addBoardfileOne 파라미터 값(boardId) : " + boardId);
		
		// 1) 물리적 파일 저장
		File temp = new File(""); // 빈 파일 위치는 프로젝트 위치
		// 프로젝트 경로
		String path = temp.getAbsolutePath(); // 빈 파일 위치
		// 확장자
		int p = multipartFile.getOriginalFilename().lastIndexOf(".");
		String ext = multipartFile.getOriginalFilename().substring(p);
		// 확장자를 제외한 파일 이름
		String prename = UUID.randomUUID().toString().replace("-", "");
		
		File file = new File(path + "\\src\\main\\webapp\\resource\\" + prename + ext);
		try {
			multipartFile.transferTo(file); // multipart 안의 파일을 빈 file에 복사
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		// 2) DB 저장
		Boardfile boardfile = new Boardfile();
		boardfile.setBoardId(boardId);
		boardfile.setBoardfileName(prename+ext);
		boardfile.setBoardfileSize(multipartFile.getSize());
		boardfile.setBoardfileType(multipartFile.getContentType());
		
		int insertRow = boardfileMapper.insertBoardfile(boardfile);
		log.debug("addBoardfileOne의 insertRow : " + insertRow);
		
		return insertRow;
	}
	
	// 삭제 메서드
	public int removeBoardfileOne(Boardfile boardfile) {
		// 파라미터 디버깅
		log.debug("removeBoardfileOne 파라미터 값(boardfileId) : " + boardfile.toString());
		
		// 1) 물리적 파일 삭제
		File temp = new File(""); // 빈 파일 위치는 프로젝트 위치
		String path = temp.getAbsolutePath(); // 빈 파일 위치
		File file = new File(path + "\\src\\main\\webapp\\resource\\" + boardfile.getBoardfileName());
		
		// 파일이 존재하면
		if(file.exists()) {
			log.debug("removeBoardfileOne 파일 존재 -> 삭제");
			file.delete();
		}
		
		// 2) DB 삭제
		int boardfileRow = boardfileMapper.deleteBoardfileOne(boardfile.getBoardfileId());
		log.debug("removeBoardfileOne DB 삭제 : " + boardfileRow);
		
		return boardfileRow;
	}
}
