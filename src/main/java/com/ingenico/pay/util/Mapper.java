package com.ingenico.pay.util;

import com.ingenico.pay.dto.AccountDto;
import com.ingenico.pay.entity.AccountEntity;
import org.springframework.stereotype.Service;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@Service
public class Mapper {



    public AccountEntity getEntity (AccountDto accountDto) {

        if(accountDto == null) return null;

        AccountEntity entity = new AccountEntity();
        entity.setId(accountDto.getId());
        entity.setName(accountDto.getName());
        entity.setBalance(accountDto.getBalance());

        return entity;
    }

    public AccountDto getDto (AccountEntity accountEntity) {

        if(accountEntity == null) return null;

        AccountDto dto = new AccountDto();
        dto.setId(accountEntity.getId());
        dto.setName(accountEntity.getName());
        dto.setBalance(accountEntity.getBalance());

        return dto;
    }
}
