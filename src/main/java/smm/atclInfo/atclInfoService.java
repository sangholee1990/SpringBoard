package smm.atclInfo;

import controller.Action;
import controller.ActionForward;
import model.BoardDAO;
import model.BoardVO;
import service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class atclInfoService implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) {

        BoardDAO dao = new BoardDAO();
        Map<String, Object> requestParameterMap = BoardService.requestParameterMap(request);

        BoardVO boardAttr = dao.boardSelectOne(requestParameterMap);
        request.setAttribute("boardAttr", boardAttr);
    }
}
