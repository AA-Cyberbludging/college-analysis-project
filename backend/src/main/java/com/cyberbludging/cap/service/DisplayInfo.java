package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.University;

import java.util.ArrayList;

public class DisplayInfo implements Filter {


    public DisplayInfo(){
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
    public void show_popularity(ArrayList<University> uni){

    }

    /*展示各省份大学数量排名*/
    public void show_province(ArrayList<University> uni){

    }


}
