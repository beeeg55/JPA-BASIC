package com.example.board.domain.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // id bigint auto_increment primary key
	private Long id;
	@Column(length = 10, nullable = false) // writer varchar(10) not null
	private String writer;
	@Column(length = 100, nullable = false) // title varchar(100) not null
	private String title;
	@Column(columnDefinition = "TEXT", nullable = false) // content text not null
	private String content;
	@Builder
	public BoardEntity(Long id, String writer, String title, String content) {
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
}// end
