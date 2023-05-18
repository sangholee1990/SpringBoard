package test.action;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//@Service
@Component
@Service(value = "boardService")
public class BoardService {

//    private final BoardMapper boardMapper;

//    @Resource(name = "boardMapper")

    @Autowired
    private BoardMapper boardMapper;

    @Resource(name = "sqlSession")
    private SqlSession session;

    //    public Map list(Map mapData) throws Exception {
    public Map list(Map mapData) {

        Map result = new HashMap();

        try {
            System.out.println("11111");
//            System.out.println(boardMapper.list(mapData));
            result.put("boardList", boardMapper.list(mapData));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

}
