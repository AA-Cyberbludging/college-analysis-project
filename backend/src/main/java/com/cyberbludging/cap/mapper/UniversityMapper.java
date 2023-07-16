package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.University;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UniversityMapper {
       //获取学校列表
       @Select("SELECT * FROM university")
       University getAllUniversity(String name);
        //获取各省份大学数量
        @Select("select pname as province ,count(*) as ucount"+
                "from university GROUP BY pname = #{name} ORDER BY ucount")
        Integer getUniversityCountBypName(String name);
        //获取某大学招生计划
        @Select("SELECT * FROM enrollment_plan" +
                "natural join mps" +
                "natural join university" +
                " WHERE uname = #{name}")
        University getEnrollmentPlanByuName(String name);
        //获取某大学各专业录取分数线及位次
        @Select("SELECT * FROM mps" +
                "natural join university" +
                " WHERE uname = #{name}")
        University getMPSByuName(String name);
        //获取某大学的所有信息（男女比率，就业率，升学率，国内就业率等）
        @Select("SELECT * FROM university" +
                " WHERE uname = #{name}")
        University getUniversityByName(String name);
//************************后面的先预备着****************************//
        @Select("SELECT * FROM university" +
                " WHERE pname = #{name}")
        University getUniversityBypName(String name);

        @Select("SELECT * FROM university" +
                " WHERE uid = #{id}")
        University getUniversityByID(Integer id);

        @Select("SELECT * FROM MPS" +
                " WHERE uid = #{id}")
        MinimumPassingScore getMPSByID(Integer id);

        @Select("SELECT * FROM university" +
                "natural join MPS" +
                " WHERE year = 2022 and subject = #{subject} and umps < #{score} and pname = #{name}")
        University getUniversityByStudentInfo(Integer score,String subject,String name);

        @Select("select upopularity"+
                "from university GROUP BY upopularity = #{popularity} ORDER BY upopularity")
        Integer getUniversityCountBypName(Double popularity);
        @Select("SELECT * FROM university" +
                " WHERE utype = #{type}")
        University getUniversityBytype(String type);

}
