package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceDtoToAppearanceConverter;
import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceToAppearanceDtoConverter;

import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/appearances")
public class AppearanceController {
    private final AppearanceService appearanceService;

    private final AppearanceRepository appearanceRepository;

    private final AppearanceDtoToAppearanceConverter appearanceDtoToAppearanceConverter;

    private final AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter;




    public AppearanceController(AppearanceService appearanceService, AppearanceRepository appearanceRepository, AppearanceDtoToAppearanceConverter appearanceDtoToAppearanceConverter, AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter) {
        this.appearanceService = appearanceService;
        this.appearanceRepository = appearanceRepository;
        this.appearanceDtoToAppearanceConverter = appearanceDtoToAppearanceConverter;
        this.appearanceToAppearanceDtoConverter = appearanceToAppearanceDtoConverter;
    }

    @GetMapping("/{E_id}")
    public Result getAppearanceById(@PathVariable String E_id) {
        Appearance appearance = this.appearanceService.getAppearanceById(E_id);
        return new Result(true, StatusCode.SUCCESS, "Find appearance Success", appearanceToAppearanceDtoConverter.convert(appearance));
    }

    @PutMapping("/{E_id}")
    public Result updateAppearanceRequest(@PathVariable String E_id, @Valid @RequestBody AppearanceDto appearanceDto) {
        Appearance update = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);
        Appearance updatedAppearanceRequest = this.appearanceService.update(E_id, update);
        AppearanceDto updatedAppearanceRequestDto = this.appearanceToAppearanceDtoConverter.convert(updatedAppearanceRequest);

        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedAppearanceRequestDto);
    }

    //Find apperance by student id

    @GetMapping("/findByStudentId/{SFS_id}")
    public Result getAppearanceByStudentId(@PathVariable String SFS_id) {
        List<Appearance> appearances = this.appearanceService.getAppearanceByStudentId(SFS_id);

        List<AppearanceDto> appearanceDtos = appearances
                .stream()
                .map(this.appearanceToAppearanceDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Find appearance Success", appearanceDtos);
    }



}
