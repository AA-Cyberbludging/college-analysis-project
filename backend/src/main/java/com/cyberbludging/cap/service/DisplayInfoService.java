package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import com.cyberbludging.cap.entity.dto.UpopularityDTO;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DisplayInfoService {

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

    /*按地区和类型查找大学
    *
     */
    /*
    public List<UniversityDTO> getUniversityBypNameAndType(String pname, String type){

        universityDOT = universityMapper.getUniversityByNameAndType(pname,type);

        return  universityDOT;
    }
    */


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
}
