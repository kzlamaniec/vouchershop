package pl.kzlamaniec.vouchershop.sales;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientData {
    @NotNull
    String firstname;
    @NotNull
    String lastname;
    @NotNull
    String email;
}
