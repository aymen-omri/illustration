package com.illustration.illustrationproject.Services;

import com.illustration.illustrationproject.Requests.JwtToken;
import com.illustration.illustrationproject.Requests.LoginRequest;
import com.illustration.illustrationproject.Requests.RegisterRequest;
import com.illustration.illustrationproject.Requests.ResetPasswordRequest;
import com.illustration.illustrationproject.Requests.VerifTokenRequest;
import com.illustration.illustrationproject.models._User;

import java.util.List;

public interface UserService {

    void register(RegisterRequest registerRequest);

    JwtToken login(LoginRequest loginRequest);

    List<_User> getAll();

    _User getById(long id);

    _User getByUsername(String username);

    void disableEnableUser(long id);

    void updateUser(String username, _User user);

    void sendToken(String email);

    void verifToken(VerifTokenRequest request);

    void reset(ResetPasswordRequest resuest);

    void createAdmin(RegisterRequest registerRequest);
}
