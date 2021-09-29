package com.mangement.transformers;

import com.mangement.dto.PersonalInformation;
import com.mangement.domain.PersonalInformationEntity;
import org.springframework.stereotype.Component;

@Component("personalInformationConverter")
public class PersonalInformationConverter implements Converter<PersonalInformationEntity, PersonalInformation> {
    @Override
    public PersonalInformation toDto(PersonalInformationEntity personalInformationEntity) {
        return null;
    }

    @Override
    public PersonalInformationEntity toEntity(PersonalInformation personalInformation) {
        return null;
    }
}
