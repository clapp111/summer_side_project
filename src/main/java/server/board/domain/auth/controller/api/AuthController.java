package server.board.domain.auth.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.board.domain.auth.controller.docs.AuthControllerSpecification;
import server.board.domain.auth.dto.LoginRequestDto;
import server.board.domain.auth.service.AuthService;
import server.board.domain.user.dto.UserCreateRequestDto;
import server.board.domain.user.dto.UserResponseDto;
import server.board.global.jwt.JwtToken;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController implements AuthControllerSpecification {

    private final AuthService authService;

    // 로그인(/api/auth/sign-in)
    @PostMapping("/auth/sign-in")
    public ResponseEntity<JwtToken> signIn(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(loginRequestDto));
    }

    // 회원가입(/api/auth/sign-up)
    @PostMapping("/auth/sign-up")
    public ResponseEntity<UserResponseDto>  signUp(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(userCreateRequestDto));
    }
}
