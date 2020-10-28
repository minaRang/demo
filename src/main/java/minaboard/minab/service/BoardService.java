package minaboard.minab.service;

import minaboard.minab.domain.BoardDTO;

import java.util.List;

public interface BoardService {

    public boolean registerBoard(BoardDTO boardDTO);
    public BoardDTO getBoardDetail(Long idx);
    public boolean deleteBoard(Long idx);
    public List<BoardDTO> getBoardList();
}
