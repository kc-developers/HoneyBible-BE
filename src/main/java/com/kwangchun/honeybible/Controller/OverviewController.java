package com.kwangchun.honeybible.Controller;

import com.kwangchun.honeybible.Service.OverviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overview")
@RequiredArgsConstructor
public class OverviewController {
    private final Logger logger = LoggerFactory.getLogger(OverviewController.class);
    
    private final OverviewService overviewService;

    @GetMapping("/all")
    public String getOverviews() {
        logger.info("[GET] all overview");

        String overviews = overviewService.getOverviews();
        logger.info("- all overview : ", overviews);

        return overviews;
    }
    
    @GetMapping("/{viewNum}")
    public String getOverview(@PathVariable String viewNum) {
        logger.info("[GET] overview");

        String overview = overviewService.getOverview(viewNum);
        logger.info("- overview info : ", overview);

        return overview;
    }

}
