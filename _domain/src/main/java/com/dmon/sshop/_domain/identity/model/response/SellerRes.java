package com.dmon.sshop._domain.identity.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerRes {
    String id;
    String shopName;
    String shopCode;
    String shopLogo;
    String contactEmail;
    String contactPhone;
    String businessType;
    String sellerType;
    String status;
}
