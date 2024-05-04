package com.example.TMS.Service.JWT;

import com.example.TMS.Dto.ReqRes;
import com.example.TMS.Entity.Role;
import com.example.TMS.Entity.Users;
import com.example.TMS.Exception.InvalidEmailException;
import com.example.TMS.Exception.InvalidStudentIdException;
import com.example.TMS.Exception.UserExceptions.*;
import com.example.TMS.Repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserInterface userInterface;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registerRequest) {
        ReqRes resp = new ReqRes();

        try {

            Optional<Users> existingUser = userInterface.findByStudentId(registerRequest.getStudentId());
            if (existingUser.isPresent()) {
                throw new UserAlreadyExistsException("User with student ID " + registerRequest.getStudentId() + " already exists.");
            }

            // Validate the input data
            UserValidationUtils.validateEmail(registerRequest.getEmail());
            UserValidationUtils.validateStudentId(registerRequest.getStudentId());
            UserValidationUtils.validateNIC(registerRequest.getNic());
            UserValidationUtils.validatePassword(registerRequest.getPassword());
            UserValidationUtils.validateAcademicYear(registerRequest.getAcademicYr());
            UserValidationUtils.validateDOB(registerRequest.getDob());

            Users users = new Users();
            users.setEmail(registerRequest.getEmail());
            users.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            users.setRole(Role.USER);
            users.setStudentId(registerRequest.getStudentId());
            users.setName(registerRequest.getName());
            users.setDob(registerRequest.getDob());
            users.setAcademicYr(registerRequest.getAcademicYr());
            users.setNic(registerRequest.getNic());
            users.setAddress(registerRequest.getAddress());

            Users resultUsers = userInterface.save(users);

            if (resultUsers != null) {
                resp.setUser(resultUsers);
                resp.setMessage("User added successfully");
                resp.setStatusCode(200);
            }
        } catch (UserAlreadyExistsException e) {
            resp.setStatusCode(400); // Bad request
            resp.setError("User already exists. Please use a different student ID.");
        } catch (InvalidEmailException | InvalidStudentIdException | InvalidNICException | InvalidPasswordException |
                 InvalidAcademicYearException | InvalidDOBException e) {
            resp.setStatusCode(400);
            resp.setError(e.getMessage());
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError("An unexpected error occurred.");
        }

        return resp;
    }

    public ReqRes signIn(ReqRes signInRequest) {
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
            var user = userInterface.findByEmail(signInRequest.getEmail()).orElseThrow(
                    () ->
                            new InvalidEmailException("User with email " + signInRequest.getEmail() + " does not exist.")
            );

            System.out.println("USER IS "+user);

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24hrs");
            response.setMessage("Sign in successfully");

        } catch (InvalidEmailException e) {
            response.setStatusCode(404); // Not found
            response.setError(e.getMessage());

        } catch (org.springframework.security.core.AuthenticationException e) {
            response.setStatusCode(401); // Unauthorized
            response.setError("Incorrect password. Please try again.");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUserName(refreshTokenRequest.getToken());
        Users ourUser = userInterface.findByEmail(ourEmail).orElseThrow();
        if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), ourUser)) {
            var jwt = jwtUtils.generateToken(ourUser);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24hrs");
            response.setMessage("Refresh token successfully");
        }
        response.setStatusCode(500);
        return response;
    }
}
