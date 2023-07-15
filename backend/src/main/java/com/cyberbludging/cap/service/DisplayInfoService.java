package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.MinimumPassingScore;
import com.cyberbludging.cap.entity.Province;
import com.cyberbludging.cap.entity.University;
import com.cyberbludging.cap.mapper.UniversityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DisplayInfoService {

    private UniversityMapper universityMapper;
    ArrayList<University> university = new ArrayList<University>();
    ArrayList<Province> province = new ArrayList<Province>();

    public DisplayInfoService(){
        //初始化uni
    }
    /*大学排序
    type参数决定 升序/降序
     */
    public ArrayList sort(int type, ArrayList arr){
        ArrayList sorted_arr = new ArrayList<>();
        return sorted_arr;
    }

    /*展示大学热度排名*/
    public void rankByPopularity(ArrayList<University> uni){

    }

    /*展示各省份大学数量排名*/
    public void rankByProvince(ArrayList<Province> pro){

    }
}
