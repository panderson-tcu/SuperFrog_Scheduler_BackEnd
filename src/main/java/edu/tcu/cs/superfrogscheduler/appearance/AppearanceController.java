package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceDtoToAppearanceConverter;
import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceToAppearanceDtoConverter;

import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import edu.tcu.cs.superfrogscheduler.user.SchedulerUser;
import edu.tcu.cs.superfrogscheduler.user.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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



    //UC 1: Customer requests a SuperFrog appearance - status: PENDING
    @PostMapping("/customer")
    public Result customerAddAppearance(@Valid @RequestBody AppearanceDto appearanceDto){
        // Convert artifactDto to artifact
        Appearance newAppearance = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);
        Appearance savedAppearance = this.appearanceService.save(newAppearance);
        AppearanceDto savedAppearanceDto = this.appearanceToAppearanceDtoConverter.convert(savedAppearance);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedAppearanceDto) ;
    }

    //UC 2: Customer edits request details
    //UC 3: Customer cancels an appearance request -> 'soft-delete' -> Change status to CANCELLED
    @PutMapping("/customer/{E_id}")
    public Result customerUpdateAppearanceRequest(@PathVariable Integer E_id, @Valid @RequestBody AppearanceDto appearanceDto) {
        Appearance update = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);
        Appearance updatedAppearanceRequest = this.appearanceService.update(E_id, update);
        AppearanceDto updatedAppearanceRequestDto = this.appearanceToAppearanceDtoConverter.convert(updatedAppearanceRequest);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedAppearanceRequestDto);
    }


    //UC 4: Spirit Director approves/ rejects an appearance request
    //UC 8: Spirit Director edits basic information of a request
    @PutMapping("/admin/{E_id}")
    public Result adminUpdateAppearanceRequest(@PathVariable Integer E_id, @Valid @RequestBody AppearanceDto appearanceDto) {
        Appearance update = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);
        Appearance updatedAppearanceRequest = this.appearanceService.update(E_id, update);
        AppearanceDto updatedAppearanceRequestDto = this.appearanceToAppearanceDtoConverter.convert(updatedAppearanceRequest);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedAppearanceRequestDto);
    }

    //UC 5: Spirit Director requests a SuperFrog for TCU events
    @PostMapping("/admin")
    public Result adminAddAppearance(@Valid @RequestBody AppearanceDto appearanceDto){
        // Convert artifactDto to artifact
        Appearance newAppearance = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);
        Appearance savedAppearance = this.appearanceService.save(newAppearance);
        AppearanceDto savedAppearanceDto = this.appearanceToAppearanceDtoConverter.convert(savedAppearance);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedAppearanceDto) ;
    }

    //UC 6: Spirit Director/ SuperFrog Student finds appearance requests
    @PostMapping("/admin/search_requests")
    public Result searchAppearancesByMainCriteria(@Valid @RequestBody AppearanceDto appearanceDto){

        Appearance appearance = this.appearanceDtoToAppearanceConverter.convert(appearanceDto);

        List<Appearance> appearances = this.appearanceService
                .findAppearanceRequest(appearance.getEventTitle(), appearance.getC_firstName(), appearance.getC_lastName(), appearance.getStatus());

        List<AppearanceDto> appearanceDtos = appearances
                .stream()
                .map(this.appearanceToAppearanceDtoConverter::convert)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find students success", appearanceDtos);
    }

    //UC 7: Spirit Director/ SuperFrog Student views an appearance request
    @GetMapping("/admin/{E_id}")
    public Result getAppearanceByAppearanceId(@PathVariable Integer E_id) {
        Appearance appearance = this.appearanceService.getAppearanceByAppearanceId(E_id);
        return new Result(true, StatusCode.SUCCESS, "Find appearance Success", appearanceToAppearanceDtoConverter.convert(appearance));
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

    @GetMapping("get_all")
    public Result findAllAppearance() {
        List<Appearance> foundAppearances = this.appearanceService.findAll();
        //Convert found appearance to a list of appearanceDtos
        List<AppearanceDto> appearanceDtos = foundAppearances.stream()
                .map(this.appearanceToAppearanceDtoConverter::convert)/*foundAppearance -> this.appearanceToAppearanceDtoConverter.convert(foundAppearance)*/
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find All Success", appearanceDtos);
    }





}
