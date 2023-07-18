package com.cyberbludging.cap.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.cyberbludging.cap.mapper.ProvinceAdmissionMapper;
import com.cyberbludging.cap.entity.ProvinceAdmission;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ProvinceAdmissionService {
    @Autowired
    private ProvinceAdmissionMapper provinceAdmissionMapper;

    public List<ProvinceAdmission> getAllAdmissionLine()
    {
        List<ProvinceAdmission> dataOf2020;
        List<ProvinceAdmission> dataOf2021;
        List<ProvinceAdmission> dataOf2022;
        List<ProvinceAdmission> dataOf2023;
        dataOf2020 = provinceAdmissionMapper.getAdmissionLine(2020);
        dataOf2021 = provinceAdmissionMapper.getAdmissionLine(2021);
        dataOf2022 = provinceAdmissionMapper.getAdmissionLine(2022);
        dataOf2023 = provinceAdmissionMapper.getAdmissionLine(2023);

        List<ProvinceAdmission> province_admissions = new ArrayList<>();
        province_admissions.addAll(dataOf2020);
        province_admissions.addAll(dataOf2021);
        province_admissions.addAll(dataOf2022);
        province_admissions.addAll(dataOf2023);
        return province_admissions;
    }

}
