package edu.tcu.cs.superfrogscheduler.appearance;

import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceDtoToAppearanceConverter;
import edu.tcu.cs.superfrogscheduler.appearance.converter.AppearanceToAppearanceDtoConverter;

import edu.tcu.cs.superfrogscheduler.appearance.dto.AppearanceDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/appearances")
public class AppearanceController {
    private final AppearanceService appearanceService;

    private final AppearanceDtoToAppearanceConverter appearanceDtoToAppearanceConverter;

    private final AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter;




    public AppearanceController(AppearanceService appearanceService, AppearanceDtoToAppearanceConverter appearanceDtoToAppearanceConverter, AppearanceToAppearanceDtoConverter appearanceToAppearanceDtoConverter) {
        this.appearanceService = appearanceService;
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

}
