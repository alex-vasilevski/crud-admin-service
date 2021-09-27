package com.mangement.transformers;

import com.mangement.dto.PersonalInformation;
import com.mangement.domain.PersonalInformationEntity;

public class PersonalInformationTransformer implements Transformer<PersonalInformationEntity, PersonalInformation> {
    @Override
    public PersonalInformation toDto(PersonalInformationEntity personalInformationEntity) {
        return null;
    }

    @Override
    public PersonalInformationEntity toEntity(PersonalInformation personalInformation) {
        return null;
    }
}
