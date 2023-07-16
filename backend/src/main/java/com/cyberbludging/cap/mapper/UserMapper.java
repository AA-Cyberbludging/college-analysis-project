package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT COUNT(*) FROM `user` " +
            " WHERE user_name = #{name} " +
            "   AND user_pswd = #{password} " +
            "   AND user_type = #{type}")
    Integer verifyUser(String name, String password, Integer type);

    @Insert("INSERT INTO `user`(user_name, user_pswd, user_type) VALUES (#{name}, #{password}, 1)")
    void registerUser(String name, String password);

    @Select("SELECT user_id FROM `user` WHERE user_name = #{name}")
    Integer getUserId(String name);

    @Select("SELECT user_id, user_name, user_score, pname, subject FROM `user` " +
            " WHERE user_id = #{id}")
    User getUserInfo(Integer id);

    @Update("UPDATE `user` " +
            "   SET user_name = #{name}, " +
            "       user_score = #{score}, " +
            "       pname = #{pname}, " +
            "       subject = #{subject} " +
            " WHERE user_id = #{id}")
    void updateUserInfo(Integer id, String name, Integer score, String pname, String subject);
}
