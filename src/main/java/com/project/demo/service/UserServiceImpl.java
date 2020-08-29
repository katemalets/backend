package com.project.demo.service;

import com.project.demo.entity.AuthorityEnum;
import com.project.demo.repository.AuthorityRepository;
import com.project.demo.repository.UserRepository;
import com.project.demo.entity.Authority;
import com.project.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User blockUser(long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public User unblockUser(long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public User makeAdmin(long id) {
        User user = userRepository.findById(id).get();
//        Authority authority = authorityRepository.findByAuthority(AuthorityEnum.ROLE_ADMIN);
//        authority.setAuthority(AuthorityEnum.ROLE_ADMIN);
//        Set<Authority> authorities = user.getAuthorities();
//        authorities.add(authority);
//        user.setAuthorities(authorities);
        userRepository.save(user);
        return user;
    }
}
