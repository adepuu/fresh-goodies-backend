package com.adepuu.freshGoodiesBackend.cart;

import com.adepuu.freshGoodiesBackend.cart.entity.Cart;
import com.adepuu.freshGoodiesBackend.cart.entity.CartItem;
import com.adepuu.freshGoodiesBackend.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @PostMapping
  public Cart createCart(@RequestBody CartItem cartItem) {
    Cart cart = cartService.createCart();
    cartService.addCartItem(cart.getId(), cartItem.getProductId(), cartItem.getQuantity());
    return cart;
  }

  @GetMapping("/{cartId}")
  public Cart getCartById(@PathVariable Long cartId) {
    return cartService.getCartById(cartId);
  }

  @DeleteMapping("/{cartId}")
  public void deleteCart(@PathVariable Long cartId) {
    cartService.deleteCart(cartId);
  }

  @PostMapping("/{cartId}/items")
  public CartItem addCartItem(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
    return cartService.addCartItem(cartId, cartItem.getProductId(), cartItem.getQuantity());
  }

  @GetMapping("/{cartId}/items")
  public List<CartItem> getAllCartItems(@PathVariable Long cartId) {
    return cartService.getAllCartItems(cartId);
  }

  @PutMapping("/{cartId}/items/{itemId}")
  public CartItem updateCartItem(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody CartItem cartItem) {
    return cartService.updateCartItem(cartId, itemId, cartItem.getQuantity());
  }

  @DeleteMapping("/{cartId}/items/{itemId}")
  public void deleteCartItem(@PathVariable Long cartId, @PathVariable Long itemId) {
    cartService.deleteCartItem(cartId, itemId);
  }
}