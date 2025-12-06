package com.fin.Traxpenses.services;

import com.fin.Traxpenses.dto.UserCreateDTO;
import com.fin.Traxpenses.exceptions.ExpenseOpsException;
import com.fin.Traxpenses.exceptions.UserOpsException;
import com.fin.Traxpenses.models.User;
import com.fin.Traxpenses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Fetch all users
     * @return List of Users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Fetch user by id
     * @param id
     */
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ExpenseOpsException("No user found with this id", HttpStatus.NOT_FOUND));
    }

    /**
     * Fetch user by number
     * @param number
     * @return User or Exception
     */
    public User getUserByNumber(String number) {
        return userRepository.findByNumber(number).orElseThrow(() -> new ExpenseOpsException("No user found with this number", HttpStatus.NOT_FOUND));
    }

    /**
     * Create a new user
     * @param userCreateDTO
     */
    public void createUser(UserCreateDTO userCreateDTO) {
        if(userExists(userCreateDTO.getNumber())) throw new UserOpsException("User already exists", HttpStatus.CONFLICT);
        User user = new User();
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setNumber(userCreateDTO.getNumber());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setCreated_At(LocalDate.now());
        user.setUpdated_At(LocalDate.now());
        userRepository.save(user);
    }

    /**
     * Delete a user
     * @param id
     */
    public void deleteUser(UUID id) {
        User user = getUserById(id);
        user.setActive(false);
        userRepository.save(user);
    }

    /**
     * Check is user with same number exists
     * @param number
     * @return Boolean -> user exists with the same number
     */
    public boolean userExists(String number) {
        return userRepository.findByNumber(number).isPresent();
    }

}
