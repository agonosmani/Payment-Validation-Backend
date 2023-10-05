package mk.com.gigavoice.payment.service;

import mk.com.gigavoice.payment.model.CreditCardDto;

public interface CreditCardValidation {
    Boolean validatePANContent(CreditCardDto creditCardDto);
    Boolean validatePANIfAmericanExpress(CreditCardDto creditCardDto);
    Boolean validateExpiryDate(CreditCardDto creditCardDto);
    Boolean validateCVVcode(CreditCardDto creditCardDto);
    Boolean validatePANLength(CreditCardDto creditCardDto);
    Boolean validateLuhnAlgorithm(CreditCardDto creditCardDto);
}
