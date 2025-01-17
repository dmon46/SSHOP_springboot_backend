package com.dmon.sshop._domain.shopping.factory;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.User;
import com.dmon.sshop._domain.shopping.model.entity.Cart;
import com.dmon.sshop._domain.shopping.model.entity.CartItem;

import java.util.ArrayList;

public class CartFactory {
    public static Cart createCart(String userId) {
        Cart cart = Cart.builder()
                .user(User.builder().id(userId).build())
                .items(new ArrayList<>())
                .count(0)
                .build();

        return cart;
    }

    public static CartItem editQuantity(CartItem cartItem) {
        cartItem.setQuantity(genQuantity(cartItem.getQuantity()));

        return cartItem;
    }

    public static CartItem addToCart(CartItem cartItem) {
        cartItem.setQuantity(genQuantity(cartItem.getQuantity()));

        cartItem.getCart().setCount(genCount(cartItem.getCart().getCount()));

        return cartItem;
    }

    private static int genQuantity(int quantity) {
        if (quantity < 0)
            throw new AppException(ErrorCode.CART__QUANTITY_MIN);

        return quantity;
    }

    private static int genCount(int count) {
        if (count >= 100)
            throw new AppException(ErrorCode.CART__COUNT_MAX);

        return ++count;
    }

}
