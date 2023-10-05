package mk.com.gigavoice.payment.model;

import lombok.Data;
import mk.com.gigavoice.payment.model.enumerations.CardType;

@Data
public class CreditCardDto {
    ExpiryDateDto expiryDate;

    String PANNumber;

    CardType cardType;

    String CVV;

    public CreditCardDto(ExpiryDateDto expiryDate, String PANNumber, CardType cardType, String CVV) {
        this.expiryDate = expiryDate;
        this.PANNumber = PANNumber;
        this.cardType = cardType;
        this.CVV = CVV;
    }
}
