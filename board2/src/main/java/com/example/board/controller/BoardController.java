package com.example.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.board.dto.BoardDto;
import com.example.board.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {
	private BoardService boardService;

	// localhost:8080/post, GET
	@GetMapping("/post")
	public String writeform(Model model) {
		model.addAttribute("title", "[ 게시판 글쓰기 ]");
		return "board/write.html";
	}

	// localhost:8080/post, POST
	@PostMapping("/post")
	public String write(BoardDto boardDto) {
		log.info("boardDto=" + boardDto);
		boardService.savePost(boardDto);
		return "board/temp.html";
	}

	// localhost:8080/, GET
	@GetMapping("/")
	public String list(Model model) {
		List<BoardDto> list = boardService.getBoardList();
		model.addAttribute("list", list);
		model.addAttribute("title", "[ 게시판 리스트 ]");
		return "board/list.html";
	}

	// localhost:8080/글번호, GET : 글 하나 (상세) 보기
	@GetMapping("/post/{no}")
	public String detail(@PathVariable("no") Long no, Model model) {
		BoardDto boardDto = boardService.getPost(no);
		log.info("boardDto=" + boardDto);
		model.addAttribute("b", boardDto);
		model.addAttribute("title", "[ 게시판 상세 ]");
		return "board/detail.html";
	}

	// localhost:8080/post/edit/글번호, GET : 글 수정 폼
	@GetMapping("/post/edit/{no}")
	public String edit(@PathVariable("no") Long id, Model model) {
		BoardDto boardDto = boardService.getPost(id);
		model.addAttribute("b", boardDto);
		model.addAttribute("title", "[ 게시판 수정 폼 ]");
		return "board/update.html";
	}
	
	@PutMapping("/post/edit/{no}")
	public String update(BoardDto boardDto) {
		boardService.savePost(boardDto);
		return "redirect:/";
	}
	
	@DeleteMapping("/post/{no}")
	public String delete(@PathVariable("no") Long id) {
		boardService.deletePost(id);
		return "redirect:/";
	}
	
}// end
