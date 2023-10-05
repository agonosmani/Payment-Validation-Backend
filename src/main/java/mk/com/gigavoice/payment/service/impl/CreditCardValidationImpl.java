package mk.com.gigavoice.payment.service.impl;

import mk.com.gigavoice.payment.model.CreditCardDto;
import mk.com.gigavoice.payment.model.enumerations.CardType;
import mk.com.gigavoice.payment.service.CreditCardValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreditCardValidationImpl implements CreditCardValidation {
    @Override
    public Boolean validatePANContent(CreditCardDto creditCardDto) {
        return creditCardDto.getPANNumber().replace(" ", "").matches("[0-9]+");
    }

    @Override
    public Boolean validateExpiryDate(CreditCardDto creditCardDto) {
        int now_year = LocalDateTime.now().getYear();
        int now_month = LocalDateTime.now().getMonth().getValue();
        int card_year = creditCardDto.getExpiryDate().getYear();
        int card_month = creditCardDto.getExpiryDate().getMonth();

        return (now_year < card_year) || ((now_year == card_year) && (now_month < card_month));
    }

    @Override
    public Boolean validateCVVcode(CreditCardDto creditCardDto) {
        return creditCardDto.getCVV().length() == 3 && !creditCardDto.getCardType().equals(CardType.AMERICAN_EXPRESS)
                || creditCardDto.getCVV().length() == 4 && creditCardDto.getCardType().equals(CardType.AMERICAN_EXPRESS);
    }

    @Override
    public Boolean validatePANLength(CreditCardDto creditCardDto) {
        int PANLength = creditCardDto.getPANNumber().replace(" ", "").length();
        return PANLength >= 16 && PANLength <= 19;
    }

    @Override
    public Boolean validateLuhnAlgorithm(CreditCardDto creditCardDto) {
        boolean oddDigit = true;
        int sum = 0;

        for (Character c : creditCardDto.getPANNumber().replace(" ", "").toCharArray()) {
            int digit = Character.getNumericValue(c);
            int processedDigit = digit;

            if (oddDigit)
                if (digit * 2 >= 10)
                    processedDigit = (digit * 2) % 10 + (digit * 2) / 10;
                else
                    processedDigit = digit * 2;

            sum += processedDigit;
            oddDigit = !oddDigit;
        }

        return sum % 10 == 0;
    }
}
