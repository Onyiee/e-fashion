package com.houseofo.util;

import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.model.Dress;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DressMapper {
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateDressFromDto(DressDto dressDto,
                            @MappingTarget Dress dress);
}
