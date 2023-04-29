package edu.tcu.cs.superfrogscheduler.user.converter;

import edu.tcu.cs.superfrogscheduler.user.SchedulerUser;
import edu.tcu.cs.superfrogscheduler.user.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<SchedulerUser, UserDto> {

    @Override
    public UserDto convert(SchedulerUser source) {
        // We are not setting password in DTO.
        final UserDto userDto = new UserDto(source.getId(),
                source.getUsername(),
                source.isEnabled(),
                source.getRoles());
        return userDto;
    }

}
