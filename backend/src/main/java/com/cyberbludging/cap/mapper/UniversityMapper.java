package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.EnrollmentPlan;
import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import io.swagger.models.auth.In;
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
       @Select("SELECT uid, uname, pname, utype, upopularity FROM university")
       List<UniversityDTO> getAllUniversity();
       //获取大学数量
       @Select("SELECT COUNT(*) FROM university")
       Integer getUniversityCount();
        //获取各省份大学数量
        @Select("select * from province ")
        List<Province> getUniversityCountBypName();
        //获取省份数量
        @Select("SELECT COUNT(*) FROM province")
        Integer getProvinceCount();
        //获取某大学招生计划
        @Select("SELECT * FROM enrollment_plan " +
                "natural join mps " +
                "natural join university " +
                " WHERE uname = #{name}")
        List<EnrollmentPlan> getEnrollmentPlanByuName(String name);
        //获取所有大学各专业录取分数线
        @Select("SELECT * FROM mps " +
                "natural join university " )
        List<MinimumPassingScore> getMPS();
        //获取某大学各专业录取分数线及位次
        @Select("SELECT * FROM mps " +
                "natural join university " +
                " WHERE uname = #{name}")
        List<MinimumPassingScore> getMPSByuName(String name);
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
