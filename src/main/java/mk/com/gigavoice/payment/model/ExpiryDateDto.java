package mk.com.gigavoice.payment.model;

import lombok.Data;

import java.util.Date;

@Data
public class ExpiryDateDto {
    int year;
    int month;

    public ExpiryDateDto(int year, int month) {
        this.year = year;
        this.month = month;
    }
}
