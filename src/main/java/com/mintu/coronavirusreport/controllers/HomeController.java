package com.mintu.coronavirusreport.controllers;

import com.mintu.coronavirusreport.models.LocationStats;
import com.mintu.coronavirusreport.services.CoronaVirusServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusServiceData coronaVirusServiceData;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusServiceData.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDate).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
