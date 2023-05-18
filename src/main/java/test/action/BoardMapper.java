package test.action;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardMapper {
    List<Map> list(Map mapData);

    @Select("SELECT COUNT(*) FROM BOARD")
    public int getCnt();
}
