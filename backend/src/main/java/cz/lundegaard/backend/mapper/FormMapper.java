package cz.lundegaard.backend.mapper;

import cz.lundegaard.backend.domain.ContactFormType;
import cz.lundegaard.backend.domain.ContactUs;
import cz.lundegaard.backend.model.form.ContactFormTypeDto;
import cz.lundegaard.backend.model.form.ContactUsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FormMapper {

    @Mapping(source = "code", target = "code")
    ContactFormTypeDto fromDomain(ContactFormType contactFormType);

    @Mapping(target = "contactFormTypeCode", source = "contactFormTypeCode")
    @Mapping(target = "id", ignore = true)
    ContactUs toDomain(ContactUsDto contactFormDto);
}
