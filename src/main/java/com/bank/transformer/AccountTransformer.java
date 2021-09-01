package com.bank.transformer;

import com.bank.domain.AccountEntity;
import com.bank.dto.Account;

public class AccountTransformer implements Transformer<AccountEntity, Account> {

    @Override
    public Account toDto(AccountEntity accountEntity) {
        return null;
    }

    @Override
    public AccountEntity toEntity(Account account) {
        return null;
    }
}
