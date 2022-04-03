package com.zowiac.service;


import com.zowiac.model.AuthorityEntity;
import com.zowiac.respository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<AuthorityEntity> findAll() {
        return getAuthorityRepository().findAll();
    }

    public List<AuthorityEntity> findAllWithPermittedPhone(String searchString) {

        if (searchString == null || searchString.length() < 3)
            return new ArrayList<>();

        List<AuthorityEntity> authorityEntities = findBySearchSting(searchString);

        authorityEntities.forEach(authorityEntity -> {
            if (!authorityEntity.isPhonePermitted())
                authorityEntity.setPhone("");
        });


        return authorityEntities;
    }

    public List<AuthorityEntity> findBySearchSting(String searchString) {
        String s = searchString;
        if (s != null) {
            s = "%" + searchString + "%";
            return getAuthorityRepository().findByPlzOrt(s);
        }
        return new ArrayList<>();
    }


    public void delete(Long id) {
        getAuthorityRepository().deleteById(id);
    }

    public AuthorityEntity save(AuthorityEntity authority) {
        getAuthorityRepository().saveAndFlush(authority);
        return authority;
    }

    public AuthorityRepository getAuthorityRepository() {
        return authorityRepository;
    }
}
