package com.bolo.demo.dao.mapper;

import com.bolo.demo.dao.entitys.Fruit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 水果表Mapper
 */
@Repository
public interface FruitMapper {

    @Insert("INSERT INTO fruit (id, sci_name, type) value (#{id},#{sciName},#{type})")
    int insert(Fruit fruit);

    @Select("select * from fruit where id = #{id}")
    Fruit getById(int id);

    @Select("<script>" +
            "select * from fruit" +
            "   <where>" +
            "       <if test=\"id != null\">" +
            "          and id = #{id}" +
            "       </if>" +
            "       <if test=\"sciName != null and sciName != ''\">" +
            "          and sci_name = #{sciName}" +
            "       </if>" +
            "       <if test=\"type != null and type != ''\">" +
            "          and type = #{type}" +
            "       </if>" +
            "   </where>" +
            "</script>")
    List<Fruit> getByExample(Fruit example);
}
