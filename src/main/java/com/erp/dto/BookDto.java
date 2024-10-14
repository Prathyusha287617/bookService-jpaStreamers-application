package com.erp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private long id;

    @NotBlank(message = "Book name is required")
    private String bookname;

    @NotBlank(message = "Author name is required")
    private String authorname;

    @Min(value = 1, message = "Book price should be greater than 0")
    private long bookprice;

    @NotBlank(message = "Book genre is required")
    private String bookgenre;

    @NotBlank(message = "Availability status is required")
    private String availability;

    @Min(value = 0, message = "Availability count cannot be negative")
    private long availabilitycount;

    @Min(value = 0, message = "Discount price should not be negative")
    private long discountprice;


        @NotBlank(message = "Customer name is required")
        private String custname;

        @NotBlank(message = "Email is required")
        private String custemail;

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 to 15 digits")
        private String custphoneNumber;

        @Min(value = 0, message = "Books purchased cannot be negative")
        private long booksPurchased;
    @NotBlank(message = "Customer name is required")
    private String salesperson;

}
