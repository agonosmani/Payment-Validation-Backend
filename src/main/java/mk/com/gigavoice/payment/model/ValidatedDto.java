package mk.com.gigavoice.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ValidatedDto {
    Boolean status;
    String text;

    public ValidatedDto(Boolean status, String text) {
        this.status = status;
        this.text = text;
    }
}
