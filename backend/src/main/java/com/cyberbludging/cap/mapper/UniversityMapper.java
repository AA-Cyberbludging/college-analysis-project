package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.University;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UniversityMapper {
        @Select("SELECT * FROM university" +
                " WHERE utype = #{type}")
        University getUniversityBytype(String type);

        @Select("SELECT * FROM university" +
                " WHERE pname = #{name}")
        University getUniversityBypName(String name);

        @Select("SELECT * FROM university" +
                " WHERE uname = #{name}")
        University getUniversityByName(String name);

        @Select("SELECT * FROM university" +
                " WHERE uid = #{id}")
        University getUniversityByID(Integer id);

        @Select("SELECT * FROM MPS" +
                " WHERE uid = #{id}")
        MinimumPassingScore getMPSByID(Integer id);

        /*@Select("SELECT * FROM university" +
                "natural join MPS" +
                " WHERE year = 2022 and subject = #{subject} and umps < #{score} and pname = #{name}")
        University getUniversityByStudentInfo(Integer score,String subject,String name);*/

        @Select("select pname as 省份 ,count(*) as 数量"+
        "from university GROUP BY pname = #{name} ORDER BY ucount")
        Integer getUniversityCountBypName(String name);

        @Select("select upopularity"+
                "from university GROUP BY upopularity = #{popularity} ORDER BY upopularity")
        Integer getUniversityCountBypName(Double popularity);
}
