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

import java.util.List;

@Api(tags = "信息接口")
@RestController
@RequestMapping("/info")
@CrossOrigin
public class InfoController {
    @Autowired
    private InfoService infoService;

    @Autowired
    private UniRecommendationService uniRecommendationService;

    @GetMapping("/tendency")
    @ApiOperation("获取各省份录取趋势")
    public List<ProvinceAdmission> getAdmissionLine(){
        return infoService.getAllAdmissionLine();
    }

    @GetMapping("/university/{id}/mps")
    @ApiOperation("获取大学各专业最低录取分数线和位次")
    public List<MinimumPassingScore> getMPSById(@PathVariable Integer id){
        return infoService.getMpsById(id);
    }

    @GetMapping("/university")
    @ApiOperation("获取大学列表")
    public List<UniversityDTO> getAllUniversity() {
        return infoService.getAllUniversity();
    }

    @GetMapping("/university/{id}")
    @ApiOperation("根据 ID 获取大学详细信息 (升学率，男女比例等)")
    public University getUniversityDetail(@PathVariable Integer id) {
        return infoService.searchById(id);
    }

    @GetMapping("/university/popularity")
    @ApiOperation("获取大学热度排名")
    public List<UpopularityDTO> rankByPopularity(){
        return infoService.rankByPopularity();
    }


    @GetMapping("/province")
    @ApiOperation("获取大学省份数量排名")
    public List<Province> rankByProvince(){
        return infoService.rankByProvince();
    }

    @GetMapping("/university/{id}/admission")
    @ApiOperation("获取指定学校招生计划")
    public List<EnrollmentPlan> getUniversityAdmission(@PathVariable Integer id) {
        return infoService.getEnrollmentPlan(id);
    }

    @PostMapping("/recommend")
    @ApiOperation("获取大学推荐")
    public List<RecommendDTO> recommend(@RequestBody User user){
        return uniRecommendationService.recommend(user);
    }
}
