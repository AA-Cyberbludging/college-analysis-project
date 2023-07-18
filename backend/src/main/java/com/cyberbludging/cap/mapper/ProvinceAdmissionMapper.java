package com.cyberbludging.cap.mapper;

import com.cyberbludging.cap.entity.ProvinceAdmission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProvinceAdmissionMapper {
        @Select("SELECT * " +
                "FROM province_admission " +
                "WHERE year = #{year} "
        )
        List<ProvinceAdmission> getAdmissionLine(Integer year);
}
