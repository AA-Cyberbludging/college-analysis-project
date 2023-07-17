package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.EnrollmentPlan;
import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import com.cyberbludging.cap.entity.dto.UpopularityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface UniversityMapper {
       //获取学校列表
       @Select("SELECT uid, uname, pname, utype, upopularity FROM university ")
        List<UniversityDTO> getAllUniversity();
       //通过地区和类型选择学校
        /*
        @Select("SELECT uid, uname, pname, utype, upopularity FROM university "+
                "WHERE pname = #{name} and utype = #{type}")
        List<UniversityDTO> getUniversityByNameAndType(String name,String type);
        */
        //获取各省份大学数量
        @Select("select * from province order by unum desc ")
        List<Province> getUniversityCountBypName();
        //获取某大学招生计划
        @Select("SELECT * FROM enrollment_plan " +
                "natural join mps " +
                "natural join university " +
                " WHERE uname = #{name}")
        List<EnrollmentPlan> getEnrollmentPlanByuName(String name);
        //获取某大学各专业录取分数线及位次
        @Select("SELECT * FROM mps " +
                "natural join university " +
                " WHERE uname = #{name}")
        List<MinimumPassingScore> getMPSByuName(String name);
        /*
        //获取某大学的所有信息
        @Select("SELECT * FROM university " +
                " WHERE uname = #{name}")
        University getUniversityByName(String name);
        */
        //获取某大学的男女比率，就业率，升学率，国内就业率, 简介
        @Select("SELECT sex_ratio, employ_rate, shipment_rate, enrollment_rate, uprofile FROM university " +
                " WHERE uname = #{name}")
        University getUniversityRateByName(String name);
        //按热度排序所有大学

        @Select("select uid, uname, upopularity from university " +
                " where upopularity is not null" +
                " order by upopularity desc ")
        List<UpopularityDTO> getUniversityOrderByPopularity();

//************************后面的先预备着****************************//
        /*
        @Select("SELECT * FROM university" +
                " WHERE pname = #{name}")
        University getUniversityBypName(String name);
        */

        @Select("SELECT * FROM university" +
                " WHERE uid = #{id}")
        University getUniversityByID(Integer id);

        @Select("SELECT * FROM MPS" +
                " WHERE uid = #{id}")
        MinimumPassingScore getMPSByID(Integer id);

        @Select("SELECT * FROM university " +
                "natural join MPS" +
                " WHERE year = 2022 and subject = #{subject} and umps < #{score} and pname = #{name}")
        University getUniversityByStudentInfo(Integer score,String subject,String name);

        @Select("SELECT * FROM university" +
                " WHERE utype = #{type}")
        University getUniversityBytype(String type);

}
