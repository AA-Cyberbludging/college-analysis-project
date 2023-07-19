package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.EnrollmentPlan;
import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.entity.dto.MPSDTO;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import com.cyberbludging.cap.entity.dto.UpopularityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UniversityMapper {
       //获取学校列表
       @Select("SELECT uid, uname, pname, utype, upopularity FROM university ")
        List<UniversityDTO> getAllUniversity();

        //获取各省份大学数量 TOP 5
        @Select("select * from province order by unum desc")
        List<Province> getUniversityCountBypName();
        //获取某大学招生计划
        @Select("SELECT university.uid, enrollment_plan.pname, subject, major, year, enrollment_num " +
                "FROM enrollment_plan join university " +
                "on enrollment_plan.uid = university.uid " +
                "where university.uid = #{id}")
        List<EnrollmentPlan> getEnrollmentPlanById(Integer id);
        //获取某大学各专业录取分数线及位次
        @Select("SELECT mps.pname, `year`, subject, major, umps, `rank` " +
                "FROM mps join university " +
                "on mps.uid = university.uid " +
                "where mps.uid = #{id}")
        List<MinimumPassingScore> getMPSById(Integer id);

        //获取近三年大学平均最低录取分及录取名次
        @Select("SELECT uid, AVG(umps) AS averagePassingScore, AVG(`rank`) AS averagePassingRank FROM " +
                "(SELECT * FROM mps WHERE subject = #{sub} AND pname = #{pname}) AS aa " +
                "GROUP BY uid" )
        List<MPSDTO> getAvg(String sub, String pname);

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

        /*
        @Select("SELECT * FROM university" +
                " WHERE utype = #{type}")
        University getUniversityBytype(String type);
        */
}
