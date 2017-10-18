package com.ingenico.pay.dao;

import com.ingenico.pay.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mohamedtantawy on 10/17/17.
 */
@Repository
public class AccountDao {

    private static final Map<String, AccountEntity> accountMap = new ConcurrentHashMap<String, AccountEntity>();


    public AccountEntity find(String id) {
        if (id != null && accountMap.containsKey(id)) {
            AccountEntity accountEntity = accountMap.get(id);
            return accountEntity;
        }
        return null;
    }

    public void save(AccountEntity accountEntity) {

        if (accountEntity != null && accountEntity.getId() == null) {
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();
            accountEntity.setId(id);
            accountMap.put(accountEntity.getId(), accountEntity);
        }
    }

    public void delete(String id) {
        if (id != null && accountMap.containsKey(id)) {
            accountMap.remove(id);
        }
    }

    public void update(AccountEntity accountEntity) {
        if (accountEntity != null && accountEntity.getId() != null && accountMap.containsKey(accountEntity.getId())) {
            accountMap.put(accountEntity.getId(),accountEntity);
        }
    }

    public List<AccountEntity> findAll() {

        List<AccountEntity> accountEntities = new ArrayList<AccountEntity>();
        Set<String> keyset = accountMap.keySet();
        for(String key : keyset) {
            accountEntities.add(accountMap.get(key));
        }

        return  accountEntities;
    }
}
