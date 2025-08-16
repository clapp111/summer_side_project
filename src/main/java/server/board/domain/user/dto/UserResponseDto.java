package server.board.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import server.board.domain.user.entity.User;

import java.time.LocalDate;

@Getter
@Schema(description = "유저 정보 응답 DTO")
public class UserResponseDto {

    @Schema(description = "유저 ID", example = "1")
    private Long id;

    @Schema(description = "이메일", example = "appcenter17@inu.ac.kr")
    private String email;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "파트", example = "basic")
    private String part;

    @Schema(description = "기수", example = "17.5")
    private Double generation;

    @Schema(description = "가입일", example = "2025년 08월 15일")
    private String registrationDate;

    @Schema(description = "휴대폰 번호", example = "010-1234-5678")
    private String phoneNumber;

    @Builder
    private UserResponseDto(Long id, String email, String name, String part, Double generation,
                            LocalDate registrationDate, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.part = part;
        this.generation = generation;
        this.registrationDate = registrationDate.getYear() + "년 "
                + registrationDate.getMonth().getValue() + "월 "
                + registrationDate.getDayOfMonth() + "일";
        this.phoneNumber = phoneNumber;
    }

    public static UserResponseDto create(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .part(user.getPart() != null ? user.getPart().getLabel() : null)
                .generation(user.getGeneration())
                .registrationDate(user.getRegistrationDate())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}