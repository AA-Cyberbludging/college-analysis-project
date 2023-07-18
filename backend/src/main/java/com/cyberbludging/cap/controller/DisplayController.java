package com.cyberbludging.cap.controller;


import com.cyberbludging.cap.entity.*;
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
    private AuthenticationService authenticationService;
    private UniCompareService uniCompareService;

    //private UniRecommendationService uniRecommendationService;

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


    /*
    @GetMapping("/{SearchByProvince}")
    @ApiOperation("按地区和类别查找大学")
    public List<UniversityDTO> getUniversityBypNameAndType(@PathVariable String pname, String type){
        return displayInfoService.rankByPopularity();
    }
    */


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

/*
    @GetMapping("/{UniCompare}")
    @ApiOperation("展示两所学校的对比")
    public List<University> UniCompareService(@PathVariable String uname1, String uname2){
        return uniCompareService.getRate(uname1,uname2);
    }
*/


/*
    @GetMapping("/{enrollmentDistribution}")
    @ApiOperation("展示招生分布")
    public showEnrollment(){
        return analyseInfoService.showEnrollment();
    }
*/

    /*
    @GetMapping("/{ByPosition}/recommend")
    @ApiOperation("按位置推荐")
    public List<University> recommend_ByPosition(@PathVariable ArrayList<University> uni, Province province){
        return uniRecommendationService.recommend_ByPosition(uni,province);
    }




    @GetMapping("/{ByScore}/recommend")
    @ApiOperation("按成绩推荐")
    public List<University> recommend_ByGrade(@PathVariable Integer score, Integer range){
        return uniRecommendationService.recommend_ByGrade(score,range);
    }



    @GetMapping("/{ByRank}/recommend")
    @ApiOperation("按大学排名推荐")
    public List<University> recommend_ByRank(@PathVariable ArrayList<University> uni){
        return uniRecommendationService.recommend_ByRank(uni);
    }


    @GetMapping("/{comprehensive}/recommend")
    @ApiOperation("综合推荐")
    public List<University> recommend_comprehensive(@PathVariable Integer score, String provinceName, String major, String subject){
        return uniRecommendationService.recommend_comprehensive(score,provinceName,major,subject);
    }
    */
}
