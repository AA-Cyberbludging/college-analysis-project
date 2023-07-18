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

    public List<ProvinceAdmission> getalladmissionline()
    {
        List<ProvinceAdmission> dataof2020;
        List<ProvinceAdmission> dataof2021;
        List<ProvinceAdmission> dataof2022;
        List<ProvinceAdmission> dataof2023;
        dataof2020 = provinceAdmissionMapper.getadmissionLine(2020);
        dataof2021 = provinceAdmissionMapper.getadmissionLine(2021);
        dataof2022 = provinceAdmissionMapper.getadmissionLine(2022);
        dataof2023 = provinceAdmissionMapper.getadmissionLine(2023);

        List<ProvinceAdmission> province_admissions = new ArrayList<>();
        province_admissions.addAll(dataof2020);
        province_admissions.addAll(dataof2021);
        province_admissions.addAll(dataof2022);
        province_admissions.addAll(dataof2023);
        return province_admissions;
    }

}
