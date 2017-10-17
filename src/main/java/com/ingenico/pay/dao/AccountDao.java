package com.ingenico.pay.dao;

import com.ingenico.pay.entity.AccountEntity;
import com.ingenico.pay.rest.AccountRest;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@Repository
public class AccountDao {

    private static final Map<Integer, AccountEntity> accountMap = new ConcurrentHashMap<Integer, AccountEntity>();


    public AccountEntity find(Integer id) {
        if (id != null && accountMap.containsKey(id)) {
            AccountEntity accountEntity = accountMap.get(id);
            return accountEntity;
        }
        return null;
    }

    public void save(AccountEntity accountEntity) {
        if (accountEntity != null && accountEntity.getId() != null) {
            accountMap.put(accountEntity.getId(), accountEntity);
        }
    }

    public void delete(Integer id) {
        if (id != null && accountMap.containsKey(id)) {
            accountMap.remove(id);
        }
    }

    public void update(AccountEntity accountEntity) {
        if (accountEntity != null && accountEntity.getId() != null && accountMap.containsKey(accountEntity.getId())) {
            accountMap.put(accountEntity.getId(),accountEntity);
        }
    }

}
