package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.entity.Checkout;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CheckoutService {
    Checkout createCheckout(Checkout checkout);
}
