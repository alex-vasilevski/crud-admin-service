package com.mangement.converters;

import com.mangement.dto.PersonalInformation;
import com.mangement.domain.PersonalInformationEntity;
import org.springframework.stereotype.Component;

@Component("personalInformationConverter")
public class PersonalInformationConverter extends AbstractGenericConverter<PersonalInformationEntity, PersonalInformation> {
    @Override
    protected PersonalInformation toDto(PersonalInformationEntity personalInformationEntity) {
        return new PersonalInformation(
                personalInformationEntity.getName(),
                personalInformationEntity.getLastName(),
                personalInformationEntity.getBirthDay(),
                personalInformationEntity.getAge(),
                personalInformationEntity.getStartDay(),
                personalInformationEntity.getEmailAddress());
    }

    @Override
    protected PersonalInformationEntity toEntity(PersonalInformation personalInformation) {
        return new PersonalInformationEntity(
                personalInformation.name(),
                personalInformation.lastName(),
                personalInformation.birthDay(),
                personalInformation.age(),
                personalInformation.startDay(),
                personalInformation.emailAddress());
    }
}
