package com.etiya.customerservice.service.requests.contactMedium;

import com.etiya.common.validators.annotations.EnumValidator;
import com.etiya.customerservice.domain.enums.ContactMediumType;
import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateContactInCustomerRequest {
    @NotBlank(message = Messages.ContactMediumTypeRequired)
    @EnumValidator(
            enumClass = ContactMediumType.class,
            message = Messages.ContactTypeShouldValid
    )
    private String type;

    @NotBlank
    @Size(max = 150, message = Messages.ContactMediumValueSize)
    private String value;

    private boolean isPrimary; // Frontend modelinizde 'primary' olarak geçiyor

    // Getters and Setters...

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public boolean isPrimary() { return isPrimary; }
    public void setPrimary(boolean primary) { isPrimary = primary; }
}
