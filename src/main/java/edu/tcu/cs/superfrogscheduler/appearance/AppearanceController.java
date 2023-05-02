package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceDtoToAppearanceConverter;
import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceToAppearanceDtoConverter;

import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.user.SchedulerUser;
import edu.tcu.cs.superfrogscheduler.user.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/appearances")
public class AppearanceController {
    private final AppearanceService appearanceService;

    private final AppearanceRepository appearanceRepository;

    private final AppearanceDtoToAppearanceConverter appearanceDtoToAppearanceConverter;

    private final AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter;

    private final UserService userService;


    public AppearanceController(AppearanceService appearanceService, AppearanceRepository appearanceRepository, AppearanceDtoToAppearanceConverter appearanceDtoToAppearanceConverter, AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter, UserService userService) {
        this.appearanceService = appearanceService;
        this.appearanceRepository = appearanceRepository;
        this.appearanceDtoToAppearanceConverter = appearanceDtoToAppearanceConverter;
        this.appearanceToAppearanceDtoConverter = appearanceToAppearanceDtoConverter;
        this.userService = userService;
    }

    @GetMapping("/{E_id}")
    public Result getAppearanceByAppearanceId(@PathVariable Integer E_id) {
        Appearance appearance = this.appearanceService.getAppearanceByAppearanceId(E_id);
        return new Result(true, StatusCode.SUCCESS, "Find appearance Success", appearanceToAppearanceDtoConverter.convert(appearance));
    }

    @PutMapping("/{E_id}")
    public Result updateAppearanceRequest(@PathVariable Integer E_id, @Valid @RequestBody AppearanceDto appearanceDto) {
        Appearance update = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);
        Appearance updatedAppearanceRequest = this.appearanceService.update(E_id, update);
        AppearanceDto updatedAppearanceRequestDto = this.appearanceToAppearanceDtoConverter.convert(updatedAppearanceRequest);

        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedAppearanceRequestDto);
    }

    //Find a list of apperances by student id
    @GetMapping("/findByStudentId/{SFS_id}")
    public Result getAppearancesByStudentId(@PathVariable Integer SFS_id) {
        List<Appearance> appearances = this.appearanceService.getAppearancesByStudentId(SFS_id);

        List<AppearanceDto> appearanceDtos = appearances
                .stream()
                .map(this.appearanceToAppearanceDtoConverter::convert)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Find appearance Success", appearanceDtos);
    }


    //UC 1: Customer requests a SuperFrog appearance
    @PostMapping
    public Result addAppearance(@Valid @RequestBody AppearanceDto appearanceDto){
        // Convert artifactDto to artifact
        Appearance newAppearance = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);
        Appearance savedAppearance = this.appearanceService.save(newAppearance);
        AppearanceDto savedAppearanceDto = this.appearanceToAppearanceDtoConverter.convert(savedAppearance);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedAppearanceDto) ;
    }




}
