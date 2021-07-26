package com.houseofo.data.dtos;

import com.houseofo.data.model.Size;
import com.houseofo.data.model.Type;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class DressDto {
    private String id;
    @NotNull(message = "You must enter a dress size.")
    private Size size;
    private String image;
    @NotNull(message = "You must enter a dress type.")
    private Type type;
    @NotBlank(message = "Dress name field must be filled. ")
    private String dressName;
    @NotNull(message = "You must enter a price for dress.")
    private BigDecimal price;
}
