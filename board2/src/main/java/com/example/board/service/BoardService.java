package com.example.board.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.domain.entity.BoardEntity;
import com.example.board.domain.repository.BoardRepository;
import com.example.board.dto.BoardDto;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class BoardService {
	private BoardRepository boardRepository;

	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId(); // insert SQL 생성 > 실행
	}

	@Transactional
	public List<BoardDto> getBoardList() {
		List<BoardEntity> boardEntities = boardRepository.findAll( Sort.by(Sort.Direction.DESC, "id") );
		List<BoardDto> list = new ArrayList<>();
		for (BoardEntity boardEntity : boardEntities) {
			BoardDto boardDto = BoardDto.builder()
					.id(boardEntity.getId())
					.title(boardEntity.getTitle())
					.content(boardEntity.getContent())
					.writer(boardEntity.getWriter())
					.createdDate(boardEntity.getCreatedDate())
					.build();
			list.add(boardDto);
		}// for
		return list;
	}

	@Transactional
	public BoardDto getPost(Long id) {
		Optional<BoardEntity> optional = boardRepository.findById(id);
		BoardEntity b = optional.get();
		BoardDto boardDto = BoardDto.builder()
				.id(b.getId())
				.title(b.getTitle())
				.content(b.getContent())
				.writer(b.getWriter())
				.createdDate(b.getCreatedDate())
				.build();
		return boardDto;
	} // getBoardList

	@Transactional
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}

	
}// end
