package mk.com.gigavoice.payment.web;

import mk.com.gigavoice.payment.model.CreditCardDto;
import mk.com.gigavoice.payment.model.ExpiryDateDto;
import mk.com.gigavoice.payment.model.ValidatedDto;
import mk.com.gigavoice.payment.model.enumerations.CardType;
import mk.com.gigavoice.payment.service.CreditCardValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentCheckingController {

    private final CreditCardValidation creditCardValidation;

    public PaymentCheckingController(CreditCardValidation creditCardValidation) {
        this.creditCardValidation = creditCardValidation;
    }

    @PostMapping("/validate")
    public ValidatedDto validateCreditCard(@RequestBody CreditCardDto creditCard) {

        if (!creditCardValidation.validatePANContent(creditCard))
            return new ValidatedDto(false, "PAN Number must contain only numbers and spaces!");

        if (!creditCardValidation.validatePANLength(creditCard))
            return new ValidatedDto(false, "PAN Number must contain between 16 and 19 numbers!");

        if (!creditCardValidation.validateLuhnAlgorithm(creditCard))
            return new ValidatedDto(false, "PAN Number must comply with Luhn's Algorithm!");

        if (!creditCardValidation.validateExpiryDate(creditCard))
            return new ValidatedDto(false, "Your Credit Card has an expired date!");

        if (!creditCardValidation.validateCVVcode(creditCard))
            if (creditCard.getCardType().equals(CardType.AMERICAN_EXPRESS))
                return new ValidatedDto(false, "Your Card is American Express. The CVV must contain exactly 4 numbers!");
            else
                return new ValidatedDto(false, "The CVV must contain exactly 3 numbers!");

        return new ValidatedDto(true, "Your Credit Card information is correct!");
    }

    @GetMapping("/card-types")
    public List<CardType> getAllCreditTypes() {
        return Arrays.asList(CardType.values());
    }


}
