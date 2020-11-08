package minaboard.minab.service;

import minaboard.minab.domain.BoardDTO;
import minaboard.minab.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService  {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public boolean registerBoard(BoardDTO boardDTO) {
        int queryResult = 0;
        if(boardDTO.getIdx()==null){
            //idx가 null이면 autoIncrement 속성으로 idx가 자동 증가되어 게시물 생성
            queryResult = boardMapper.insertBoard(boardDTO);
        }else{
            //idx가 값이 있으면 게시물을 수정함
            queryResult = boardMapper.updateBoard(boardDTO);
        }
        //해당 메서드에서 호출한 쿼리가 정상실행될경우
        //쿼리를 실행한 횟수인 1이 저장됨
        return (queryResult ==1)?true : false;
    }

    @Override
    public BoardDTO getBoardDetail(Long idx) {
        //하나의 게시물 조회
        return boardMapper.selectBoardDetail(idx);
    }

    @Override
    public boolean deleteBoard(Long idx) {
        int queryResult=0;
        //특정게시물 조회, delete_yn의 N일경우(사용중인경우)에만 게시물 삭제
        //게시물이 없거나 Y값인경우 삭제실행 X,
        BoardDTO boardDTO = boardMapper.selectBoardDetail(idx);
        if(boardDTO != null && "N".equals(boardDTO.getDeleteYn())){
            queryResult = boardMapper.deleteBoard(idx);
        }
        return (queryResult == 1)?true:false;
    }

    @Override
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = Collections.emptyList();
        //NullPointException방지 위해 비어있는 리스트 선언
        int boardTotalCount = boardMapper.selectBoardTotalCount();
        //사용중인 전체 게시글 수 카운팅 결과 저장

        //사용중인 전체 게시물이 1개 이상이면 boardList에 selectBoardList 메서드 결과값 반환환
       if(boardTotalCount>0){
            boardList  = boardMapper.selectBoardList();
        }
        return boardList;
    }
}
