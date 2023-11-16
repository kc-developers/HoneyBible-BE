package com.kwangchun.honeybible.Controller;

import com.kwangchun.honeybible.Service.ReadcheckService;
import com.kwangchun.honeybible.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Readcheck", description = "TB_READCHECK 테이블에 대한 API")
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
    @GetMapping("/{ttolae}/{name}/{phoneNumber}/{dateKey}")
    public String getReadcheck(@PathVariable String ttolae, @PathVariable String name, @PathVariable String phoneNumber, @PathVariable String dateKey) {
        logger.info("[GET] readcheck");

        String memberNum = userService.getMemberNum(ttolae, name, phoneNumber);
        String readcheck = readcheckService.getReadcheck(memberNum, dateKey);
        logger.info("- readcheck info : {}", readcheck);

        return readcheck;
    }

    @Operation(summary = "읽은 날짜 수", description = "주어진 날짜를 포함하여 해당 유저가 그동안 몇 번 읽었는지 계산한다.")
    @GetMapping("/dateReadBefore/{ttolae}/{name}/{phoneNumber}/{dateKey}")
    public String getDateReadBefore(@PathVariable String ttolae, @PathVariable String name, @PathVariable String phoneNumber, @PathVariable String dateKey) {
        logger.info("[GET] dateReadBefore");

        String memberNum = userService.getMemberNum(ttolae, name, phoneNumber);
        String dateReadBefore = readcheckService.getDateReadBefore(memberNum, dateKey);
        logger.info("- dateReadBefore : {}", dateReadBefore);

        return dateReadBefore;
    }
}
