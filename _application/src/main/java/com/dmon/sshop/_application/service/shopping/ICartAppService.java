package com.dmon.sshop._application.service.shopping;

import com.dmon.sshop._domain.shopping.model.entity.Cart;
import com.dmon.sshop._domain.shopping.model.entity.CartItem;
import com.dmon.sshop._domain.shopping.model.request.CartItemAddReq;
import com.dmon.sshop._domain.shopping.model.request.CartItemEditReq;

import java.util.ArrayList;

public interface ICartAppService {
    //CREATE//
    CartItem addToCart(CartItemAddReq cartItemAddReq);

    //READ//
    Object get();

    //UPDATE//
    CartItem editQuantity(CartItemEditReq cartItemEditReq);

    //DELETE//
    Object removeFromCart(String cartItemIds);
}
