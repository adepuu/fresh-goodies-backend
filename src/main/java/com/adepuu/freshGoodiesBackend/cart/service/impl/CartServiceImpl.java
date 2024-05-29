package com.adepuu.freshGoodiesBackend.cart.service.impl;

import com.adepuu.freshGoodiesBackend.cart.entity.Cart;
import com.adepuu.freshGoodiesBackend.cart.entity.CartItem;
import com.adepuu.freshGoodiesBackend.cart.repository.CartRepository;
import com.adepuu.freshGoodiesBackend.cart.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;

  public CartServiceImpl(CartRepository cartRepository) {
    this.cartRepository = cartRepository;
  }

  @Override
  @Transactional
  public CartItem addCartItem(Long cartId, Long productId, int quantity) {
    Cart cart = cartRepository.findActiveCartById(cartId);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found or has been deleted");
    }

    if (cart.getItems() == null) {
      cart.setItems(new ArrayList<>());
    }

    boolean itemExists = cart.getItems().stream()
            .anyMatch(item -> item.getProductId() == productId);

    if (itemExists) {
      throw new IllegalArgumentException("Item already exists in the cart. Use updateCartItem to change the quantity.");
    }

    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero for new items");
    }

    CartItem cartItem = new CartItem();
    cartItem.setProductId(productId);
    cartItem.setQuantity(quantity);
    cart.getItems().add(cartItem);

    cartRepository.save(cart);
    return cartItem;
  }

  @Override
  public List<CartItem> getAllCartItems(Long cartId) {
    Cart cart = cartRepository.findActiveCartById(cartId);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found or has been deleted");
    }
    return cart.getItems();
  }

  @Override
  @Transactional
  public CartItem updateCartItem(Long cartId, Long itemId, int quantityChange) {
    Cart cart = cartRepository.findActiveCartById(cartId);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found or has been deleted");
    }

    Optional<CartItem> cartItemOpt = cart.getItems().stream()
            .filter(item -> item.getId() == itemId)
            .findFirst();

    if (cartItemOpt.isEmpty()) {
      throw new IllegalArgumentException("CartItem not found");
    }

    CartItem cartItem = cartItemOpt.get();
    int newQuantity = cartItem.getQuantity() + quantityChange;
    if (newQuantity <= 0) {
      cart.getItems().remove(cartItem);
    } else {
      cartItem.setQuantity(newQuantity);
    }

    cartRepository.save(cart);
    return cartItem;
  }

  @Override
  @Transactional
  public void deleteCartItem(Long cartId, Long itemId) {
    Cart cart = cartRepository.findActiveCartById(cartId);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found or has been deleted");
    }
    cart.getItems().removeIf(item -> item.getId() == itemId);

    if (cart.getItems().isEmpty()) {
      cartRepository.deleteById(cartId);
    } else {
      cartRepository.save(cart);
    }
  }

  @Override
  public Cart getCartById(Long cartId) {
    return cartRepository.findActiveCartById(cartId);
  }

  @Override
  @Transactional
  public Cart createCart() {
    Cart cart = new Cart();
    cartRepository.save(cart);
    return cart;
  }

  @Override
  @Transactional
  public void deleteCart(Long cartId) {
    Cart cart = cartRepository.findActiveCartById(cartId);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found or has been deleted");
    }
    cartRepository.deleteById(cartId);
  }
}