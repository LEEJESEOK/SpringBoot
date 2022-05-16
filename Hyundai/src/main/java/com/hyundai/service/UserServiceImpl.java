package com.hyundai.service;

import com.hyundai.entity.User;
import com.hyundai.entity.UserRole;
import com.hyundai.entity.UserRoleSet;
import com.hyundai.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author LEE JESEOK
 */
@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signin(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.insertUser(user);

            UserRoleSet userRoleSet = new UserRoleSet();
            userRoleSet.setUserEmail(user.getEmail());
            userRoleSet.setRoleSet(UserRole.USER.name());
            userRepository.insertUserRoleSet(userRoleSet);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }

    @Override
    public User getUserInfoByEmail(String email) {
        try {
            return userRepository.selectUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    @Override
    public boolean isDuplicatedByEmail(String email) {
        return userRepository.selectUserByEmail(email) != null;
    }

    @Override
    public boolean updateUserInfo(User user) {
        try {
            user.setPassword(passwordEncoder.encode((user.getPassword())));

            userRepository.updateUser(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean updateUserPassword(String email, String password) {
        try {
            User user = userRepository.selectUserByEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.updateUser(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        try {
            userRepository.deleteUser(email);

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
