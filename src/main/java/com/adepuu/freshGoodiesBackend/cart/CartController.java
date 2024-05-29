package com.adepuu.freshGoodiesBackend.cart;

import com.adepuu.freshGoodiesBackend.cart.entity.Cart;
import com.adepuu.freshGoodiesBackend.cart.entity.CartItem;
import com.adepuu.freshGoodiesBackend.cart.service.CartService;
import com.adepuu.freshGoodiesBackend.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Response<Cart>> createCart(@RequestBody CartItem cartItem) {
    Cart cart = cartService.createCart();
    cartService.addCartItem(cart.getId(), cartItem.getProductId(), cartItem.getQuantity());
    return Response.successfulResponse(HttpStatus.CREATED.value(), "Cart created", cart);
  }

  @GetMapping("/{cartId}")
  public ResponseEntity<Response<Cart>> getCartById(@PathVariable Long cartId) {
    Cart cart = cartService.getCartById(cartId);
    return Response.successfulResponse("Cart retrieved", cart);
  }

  @DeleteMapping("/{cartId}")
  public ResponseEntity<Response<Void>> deleteCart(@PathVariable Long cartId) {
    cartService.deleteCart(cartId);
    return Response.successfulResponse("Cart deleted");
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<Response<CartItem>> addCartItem(@PathVariable Long cartId, @RequestBody CartItem cartItem) {
    CartItem addedItem = cartService.addCartItem(cartId, cartItem.getProductId(), cartItem.getQuantity());
    return Response.successfulResponse(HttpStatus.CREATED.value(), "Cart item added", addedItem);
  }

  @GetMapping("/{cartId}/items")
  public ResponseEntity<Response<List<CartItem>>> getAllCartItems(@PathVariable Long cartId) {
    List<CartItem> items = cartService.getAllCartItems(cartId);
    return Response.successfulResponse("All cart items retrieved", items);
  }

  @PutMapping("/{cartId}/items/{itemId}")
  public ResponseEntity<Response<CartItem>> updateCartItem(@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody CartItem cartItem) {
    CartItem updatedItem = cartService.updateCartItem(cartId, itemId, cartItem.getQuantity());
    return Response.successfulResponse("Cart item updated", updatedItem);
  }

  @DeleteMapping("/{cartId}/items/{itemId}")
  public ResponseEntity<Response<Void>> deleteCartItem(@PathVariable Long cartId, @PathVariable Long itemId) {
    cartService.deleteCartItem(cartId, itemId);
    return Response.successfulResponse("Cart item deleted");
  }
}