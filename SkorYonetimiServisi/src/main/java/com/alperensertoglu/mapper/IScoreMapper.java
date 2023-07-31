package com.alperensertoglu.mapper;

import com.alperensertoglu.rabbitmq.model.GetScoreModel;
import com.alperensertoglu.repository.entity.Skor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IScoreMapper {

    IScoreMapper INSTANCE = Mappers.getMapper(IScoreMapper.class);

    Skor toScore(final GetScoreModel model);


}
