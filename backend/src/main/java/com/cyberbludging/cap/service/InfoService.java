package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.*;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import com.cyberbludging.cap.entity.dto.UpopularityDTO;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InfoService {

    @Autowired
    private UniversityMapper universityMapper;

    public List<UniversityDTO> getAllUniversity() {
        return universityMapper.getAllUniversity();
    }
  /*按大学 ID 查找大学所有信息
  *返回University
   */
    public University searchById(Integer id){
        return universityMapper.getUniversityByID(id);
    }

    /*展示大学热度排名
    返回排序后的UniversityDTO列表
    */
    public List<UpopularityDTO> rankByPopularity(){
        return universityMapper.getUniversityOrderByPopularity();
    }

    /*展示各省份大学数量排名
    返回排序后的Province列表
     */
    public List<Province> rankByProvince(){
        return universityMapper.getUniversityCountBypName();
    }

    public List<ProvinceAdmission> getAllAdmissionLine()
    {
        List<ProvinceAdmission> dataOf2020;
        List<ProvinceAdmission> dataOf2021;
        List<ProvinceAdmission> dataOf2022;
        List<ProvinceAdmission> dataOf2023;
        dataOf2020 = universityMapper.getAdmissionLine(2020);
        dataOf2021 = universityMapper.getAdmissionLine(2021);
        dataOf2022 = universityMapper.getAdmissionLine(2022);
        dataOf2023 = universityMapper.getAdmissionLine(2023);

        List<ProvinceAdmission> province_admissions = new ArrayList<>();
        province_admissions.addAll(dataOf2020);
        province_admissions.addAll(dataOf2021);
        province_admissions.addAll(dataOf2022);
        province_admissions.addAll(dataOf2023);
        return province_admissions;
    }

    public List<MinimumPassingScore> getMpsById(Integer id) {
        return universityMapper.getMpsById(id);
    }

    /*返回某大学的招生计划
     *返回EnrollmentPlan列表
     */
    public List<EnrollmentPlan> getEnrollmentPlan(Integer id){
        return universityMapper.getEnrollmentPlanById(id);
    }
}
