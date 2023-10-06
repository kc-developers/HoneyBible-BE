package com.kwangchun.honeybible.Controller;

import com.kwangchun.honeybible.Service.ReadcheckService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/readcheck")
@RequiredArgsConstructor
public class ReadcheckController {
    private final Logger logger = LoggerFactory.getLogger(ReadcheckController.class);
    
    private final ReadcheckService readcheckService;

    @GetMapping("/all")
    public String getReadchecks() {
        logger.info("[GET] all readcheck");

        String readchecks = readcheckService.getReadchecks();
        logger.info("- all readchecks : {}", readchecks);

        return readchecks;
    }
    
    @GetMapping("/{readNum}")
    public String getReadcheck(@PathVariable String readNum) {
        logger.info("[GET] readcheck");

        String readcheck = readcheckService.getReadcheck(readNum);
        logger.info("- readcheck info : {}", readcheck);

        return readcheck;
    }

}
