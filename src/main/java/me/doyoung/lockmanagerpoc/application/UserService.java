package me.doyoung.lockmanagerpoc.application;

import lombok.RequiredArgsConstructor;
import me.doyoung.lockmanagerpoc.application.dto.UserDto;
import me.doyoung.lockmanagerpoc.domain.user.User;
import me.doyoung.lockmanagerpoc.infra.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto.Response saveUser(UserDto.SaveRequest request) {
        final User user = userRepository.save(request.toEntity());
        return UserDto.Response.from(user);
    }

}
