package minaboard.minab.mapper;

import minaboard.minab.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

//mybatis는 mapper를 지정해주면 xml mapper에서 메서드의 이름과 이치하는 sql문 찾아 실행
//매퍼 영역은 sql쿼리를 호출하는 것이 전부.
@Mapper
@Component
public interface BoardMapper {
    //게시글 생성하는 insert쿼리 호출메서드
    public int insertBoard(BoardDTO boardDTO);

    //하나의 게시글 조회하는 select 쿼리 호출 메서드
    //where 값으로 idx받는다
    public BoardDTO selectBoardDetail(Long idx);

    //게시글 수정하는 update 쿼리 호출 메서드
    public int updateBoard(BoardDTO boardDTO);

    //실제 데이터 삭제하지 않고 상태값이 n로 지정된 데이터만 노출하게한다.
    public int deleteBoard(Long idx);

    //게시물 목록 조회하는 select쿼리 호출.  제네릭타입
    public List<BoardDTO> selectBoardList();

    //삭제여부가 N으로 지정된 게시글의 개수를 조회하는 select쿼리 호출. 페이징 처리 진행하며 사용
    public int selectBoardTotalCount();

}
