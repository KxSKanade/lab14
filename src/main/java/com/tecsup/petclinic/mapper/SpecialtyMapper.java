package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.SpecialtyTO;
import com.tecsup.petclinic.entities.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);
    Specialty toSpecialty(SpecialtyTO specialtyTO);
    SpecialtyTO toSpecialtyTO(Specialty specialty);
    List<SpecialtyTO> toSpecialtyTOList(List<Specialty> specialties);
}
