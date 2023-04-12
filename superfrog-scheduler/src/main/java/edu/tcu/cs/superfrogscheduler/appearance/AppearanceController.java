package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/appearances")
public class AppearanceController {
    private final AppearanceService appearanceService;


    public AppearanceController(AppearanceService appearanceService) {
        this.appearanceService = appearanceService;
    }

    @GetMapping("/{E_id}")
    public Result getAppearanceById(@PathVariable String E_id) {
        Appearance appearance = this.appearanceService.getAppearanceById(E_id);
        return new Result(true, StatusCode.SUCCESS, "Find appearance Success", appearance);
    }

}
