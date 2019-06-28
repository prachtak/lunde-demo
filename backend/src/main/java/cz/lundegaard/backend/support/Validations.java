package cz.lundegaard.backend.support;

import cz.lundegaard.backend.exception.impl.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.support.WebExchangeBindException;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

/**
 * Validations helper.
 */
@Slf4j
@AllArgsConstructor(access = PRIVATE)
public class Validations {

    /**
     * An exception class implementing {@link org.springframework.validation.BindingResult}.
     */
    public static final Class<WebExchangeBindException> BINDING_RESULT_EXCEPTION = WebExchangeBindException.class;

    /**
     * Constructs {@link ValidationException} with detail object, which holds
     * field errors from a validated payload.
     *
     * @param e {@link WebExchangeBindException}
     * @return {@link ValidationException}
     */
    public static ValidationException validationException(WebExchangeBindException e) {
        var errorFields = e.getBindingResult().getFieldErrors().stream()
                .map(f -> "Field '" + f.getField() + "' " + f.getDefaultMessage() + ".")
                .collect(toList());
        log.debug("Request body error: {}", errorFields);
        return new ValidationException("Payload validation error occurred.", errorFields, e);
    }

}
