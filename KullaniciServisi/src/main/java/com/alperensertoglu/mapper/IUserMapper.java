package com.alperensertoglu.mapper;

import com.alperensertoglu.dto.request.UserSaveRequestDto;
import com.alperensertoglu.dto.response.UserRegisterResponseDto;
import com.alperensertoglu.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toUser(final UserSaveRequestDto dto);

    UserRegisterResponseDto toResponseDto(final User user);

}
