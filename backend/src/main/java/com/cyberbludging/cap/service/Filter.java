package com.cyberbludging.cap.service;

import com.cyberbludging.cap.entity.University;

import java.util.*;

public interface Filter {

    static ArrayList<University> uni = new ArrayList<University>();


    /*筛选器
    obj作为筛选条件
     */
    public default void filter(String obj, ArrayList<University> uni){

    }

    /*
    选择一所或多所大学
     */
    public default void select(ArrayList<University> uni){

    }
}
