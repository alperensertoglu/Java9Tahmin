package com.alperensertoglu.mapper;

import com.alperensertoglu.dto.request.SaveCityRequestDto;
import com.alperensertoglu.dto.request.UpdateCityRequestDto;
import com.alperensertoglu.repository.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICityMapper {
    ICityMapper INSTANCE = Mappers.getMapper(ICityMapper.class);

    City toCityFromDto(final SaveCityRequestDto dto);

    City toCityUpdate(final UpdateCityRequestDto dto);
}
