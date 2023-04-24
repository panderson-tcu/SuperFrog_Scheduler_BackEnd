package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SFSToSuperFrogStudentDtoConverter;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/appearances")
public class AppearanceController {
    private final AppearanceService appearanceService;
    private SFSToSuperFrogStudentDtoConverter appearanceToAppearanceDtoConverter;


    public AppearanceController(AppearanceService appearanceService) {
        this.appearanceService = appearanceService;
    }

    @GetMapping("/{E_id}")
    public Result getAppearanceById(@PathVariable String E_id) {
        Appearance appearance = this.appearanceService.getAppearanceById(E_id);
        return new Result(true, StatusCode.SUCCESS, "Find appearance Success", appearance);
    }

    @GetMapping("api/v1/appearances")
    public  <AppearanceDto> Result findAllAppearance() {
            List<Appearance> foundAppearances = this.appearanceService.findAll();
            //Convert foundAppearance to a list of appearanceDtos
            List<AppearanceDto> appearanceDtos = foundAppearances.stream()
                    .map(foundAppearance ->
                            this.appearanceToAppearanceDtoConverter.convert(foundAppearance))
                    .collect(Collectors.toList());

            return new Result(true, StatusCode.SUCCESS, "find all success", appearanceDtos);
    }
}
