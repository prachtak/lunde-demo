package cz.lundegaard.backend.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ContactUsDto {

    @NotBlank
    private String contactFormTypeCode;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String policyNumber;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String surname;

    @Size(max = 150)
    @NotBlank
    private String requestMessage;

}
