package com.cyberbludging.cap.controller;


import com.cyberbludging.cap.entity.*;
import com.cyberbludging.cap.entity.dto.RecommendDTO;
import com.cyberbludging.cap.entity.dto.UniversityDTO;
import com.cyberbludging.cap.entity.dto.UpopularityDTO;
import com.cyberbludging.cap.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "展示接口")
@RestController
@RequestMapping("/display")
@CrossOrigin

public class DisplayController {
    @Autowired
    private DisplayInfoService displayInfoService;

    @Autowired
    private ProvinceAdmissionService provinceAdmissionService;

    @Autowired
    private AnalyseInfoService analyseInfoService;

    @Autowired
    private UniRecommendationService uniRecommendationService;

    @GetMapping("/tendency")
    @ApiOperation("展示各省份录取趋势")
    public List<ProvinceAdmission> getAdmissionLine(){
        return provinceAdmissionService.getAllAdmissionLine();
    }

    @PostMapping("/{MinimumPassingScore}")
    @ApiOperation("展示最低录取分数线")
    public List<MinimumPassingScore> getMPSByuName(@PathVariable String uname ){
        return analyseInfoService.getMPSByuName(uname);
    }


    @GetMapping("/university")
    @ApiOperation("获取大学列表")
    public List<UniversityDTO> getAllUniversity() {
        return displayInfoService.getAllUniversity();
    }

    @GetMapping("/university/{id}")
    @ApiOperation("根据 ID 获取大学详细信息 (升学率，男女比例等)")
    public University getUniversityDetail(@PathVariable Integer id) {
        return displayInfoService.searchById(id);
    }

    @GetMapping("/university/popularity")
    @ApiOperation("获取大学热度排名")
    public List<UpopularityDTO> rankByPopularity(){
        return displayInfoService.rankByPopularity();
    }


    @GetMapping("/province")
    @ApiOperation("获取大学省份数量排名")
    public List<Province> rankByProvince(){
        return displayInfoService.rankByProvince();
    }

    @GetMapping("/university/{id}/admission")
    @ApiOperation("获取指定学校招生计划")
    public List<EnrollmentPlan> getUniversityAdmission(@PathVariable Integer id) {
        return analyseInfoService.getEnrollmentPlan(id);
    }

    @PostMapping("/recommend")
    @ApiOperation("获取大学推荐")
    public List<RecommendDTO> recommend(@RequestBody User user){
        return uniRecommendationService.recommend(user);
    }
}
