package com.adepuu.freshGoodiesBackend.cart.service;

import com.adepuu.freshGoodiesBackend.cart.entity.Cart;
import com.adepuu.freshGoodiesBackend.cart.entity.CartItem;

import java.util.List;

public interface CartService {
  // Create a new cart item
  CartItem addCartItem(Long cartId, Long productId, int quantity);

  // Retrieve all cart items
  List<CartItem> getAllCartItems(Long cartId);

  // Update the quantity of an existing cart item
  CartItem updateCartItem(Long cartId, Long itemId, int quantity);

  // Delete a cart item
  void deleteCartItem(Long cartId, Long itemId);

  // Retrieve a cart by its ID
  Cart getCartById(Long cartId);

  // Create a new cart
  Cart createCart();

  // Delete a cart
  void deleteCart(Long cartId);
}