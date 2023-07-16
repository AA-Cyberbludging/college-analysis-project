package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DisplayInfoService {

    @Autowired
    private UniversityMapper universityMapper;

    List<UniversityDTO> universityDOT = new ArrayList<UniversityDTO>();
    List<Province> province = new ArrayList<Province>();


  /*按大学名查找大学所有信息
  *返回University
   */
    public University searchByuName(String uname){

        return universityMapper.getUniversityByName(uname);
    }

    /*按地区和类型查找大学
    *
     */
    public List<UniversityDTO> getUniversityBypNameAndType(String pname, String type){

        universityDOT = universityMapper.getUniversityByNameAndType(pname,type);

        return  universityDOT;
    }


    /*展示大学热度排名
    返回排序后的UniversityDTO列表
    */
    public List<UniversityDTO> rankByPopularity(){

        universityDOT = universityMapper.getUniversityOrderByPopularity();
        return  universityDOT;

    }

    /*展示各省份大学数量排名
    返回排序后的Province列表
     */
    public List<Province> rankByProvince(){

        province = universityMapper.getUniversityCountBypName();
        return province;

    }
}
