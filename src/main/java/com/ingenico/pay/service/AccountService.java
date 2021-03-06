package com.ingenico.pay.service;

import com.ingenico.pay.dao.AccountDao;
import com.ingenico.pay.dto.AccountDto;
import com.ingenico.pay.entity.AccountEntity;
import com.ingenico.pay.exception.ApplicationException;
import com.ingenico.pay.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    Mapper mapper;

    /**
     * @param id for account
     * @return account entity
     * @throws ApplicationException.AccountNotFoundException
     */
    public AccountEntity findEntity(String id) {
        AccountEntity accountEntity = accountDao.find(id);
        if (accountEntity == null) {
            throw new ApplicationException.AccountNotFoundException(id);
        }
        return accountEntity;
    }

    /**
     * @param id for account
     * @return account dto
     * @throws ApplicationException.AccountNotFoundException
     */
    public AccountDto find(String id) {
        AccountEntity accountEntity = accountDao.find(id);
        if (accountEntity == null) {
            throw new ApplicationException.AccountNotFoundException(id);
        }
        AccountDto accountDto = mapper.getDto(accountEntity);
        return accountDto;
    }

    /**
     * @param name    name for account to create
     * @param balance initial balance
     * @return account dto
     */
    public AccountDto create(String name, double balance) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(name);
        accountEntity.setBalance(balance);

        accountDao.save(accountEntity);

        AccountDto accountDto = mapper.getDto(accountEntity);
        return accountDto;
    }

    /**
     * @param id for account to delete
     * @return deleted account as dto
     * @throws ApplicationException.AccountNotFoundException
     */
    public AccountDto delete(String id) {
        AccountEntity accountEntity = accountDao.find(id);
        if (accountEntity == null) {
            throw new ApplicationException.AccountNotFoundException(id);
        }
        AccountDto accountDto = mapper.getDto(accountEntity);

        accountDao.delete(id);

        return accountDto;
    }

    /**
     * @param id for account to validate existence
     * @throws ApplicationException.AccountNotFoundException
     */
    public void validateAccount(String id) {
        AccountEntity accountEntity = accountDao.find(id);
        if (accountEntity == null) {
            throw new ApplicationException.AccountNotFoundException(id);
        }
    }

    /**
     * @param id     for account to check if it has enough balance
     * @param amount that will deducted from the account balance
     * @throws ApplicationException.NoSufficientBalanceException
     */
    public void checkAccountHasSufficientBalance(String id, double amount) {
        AccountEntity accountEntity = accountDao.find(id);

        if (accountEntity.getBalance() < amount) {
            throw new ApplicationException.NoSufficientBalanceException(id, amount);
        }

    }

    /**
     * @return all account as dtos List
     */
    public List<AccountDto> findAll() {

        List<AccountEntity> accountEntities = accountDao.findAll();

        List<AccountDto> accountDaos = mapper.getDtos(accountEntities);
        return accountDaos;
    }


}
