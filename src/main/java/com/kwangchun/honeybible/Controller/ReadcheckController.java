package com.kwangchun.honeybible.Controller;

import com.kwangchun.honeybible.Service.ReadcheckService;
import com.kwangchun.honeybible.Service.UserService;
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
    private final UserService userService;

    @GetMapping("/all")
    public String getReadchecks() {
        logger.info("[GET] all readcheck");

        String readchecks = readcheckService.getReadchecks();
        logger.info("- all readchecks : {}", readchecks);

        return readchecks;
    }

    @Operation(summary = "읽기 여부 조회", description = "유저 별로 날짜 별로 읽기 여부를 조회한다.")

    @GetMapping("/{ttolae}/{name}/{dateKey}")
    public String getReadcheck(@PathVariable String ttolae, @PathVariable String name, @PathVariable String dateKey) {
        logger.info("[GET] readcheck");

        String memberNum = userService.getMemberNum(ttolae, name);
        String readcheck = readcheckService.getReadcheck(memberNum, dateKey);
        logger.info("- readcheck info : {}", readcheck);

        return readcheck;
    }

}
