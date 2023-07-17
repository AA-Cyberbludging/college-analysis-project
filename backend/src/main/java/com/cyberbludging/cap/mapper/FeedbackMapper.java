package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.Feedback;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Mapper
@Repository
public interface FeedbackMapper {
    //显示所有反馈
    @Select("SELECT * FROM feedback ")
    List<Feedback> getAllFeedback();
    //增加反馈
    @Insert("INSERT INTO feedback (fb_id,user_id, feedback, time_slot) " +
            "VALUE (#{fb_id} ,#{userId}, #{feedback}, #{time}, 0)")
    void addFeedback(Integer feedbackId, Integer userId, String feedback, Time time);
    //删除一条反馈
    @Delete("DELETE FROM feedback " +
            "WHERE fb_id = #{feedbackID}" )
    void deleteFeedback(Integer feedbackId);
    //通过反馈编号完成一条反馈
    @Update("UPDATE feedback SET fb_deal = 1 " +
            "WHERE fb_id = #{feedbackID}")
    void finishFeedback(Integer feedbackId);
}
