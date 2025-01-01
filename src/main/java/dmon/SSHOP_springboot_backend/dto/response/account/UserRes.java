package dmon.SSHOP_springboot_backend.dto.response.account;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import dmon.SSHOP_springboot_backend.enums.GenderEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRes {
    String id;
    String name;
    String photo;
    Date dob;
    GenderEnum gender;
}
