package com.mangement.api.transformers.internal;

import com.mangement.api.dto.PersonalInformation;
import com.mangement.api.transformers.spi.Transformer;
import com.mangement.store.domain.personel.PersonalInformationEntity;

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
