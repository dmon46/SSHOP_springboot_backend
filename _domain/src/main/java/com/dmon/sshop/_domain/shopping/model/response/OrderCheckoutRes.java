package com.dmon.sshop._domain.shopping.model.response;

import com.dmon.sshop._domain.shopping.model.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCheckoutRes {

    ArrayList<Order> orders = new ArrayList<>();
    SummaryRes summary = new SummaryRes();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SummaryRes {

        float subtotal = 0;
        float shippingFee = 0;
        float discount = 0;
        float shippingDiscount = 0;
        float total = 0;
    }

}
