package cz.lundegaard.backend.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.time.Instant;
import java.util.UUID;

/**
 * Generic Error common object which is returned to a user from
 * exception handler.
 */
@Getter
@ToString
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Contains a short message about what happend.
     */
    private final String message;

    /**
     * Contains an explanation of what happened and why.
     */
    private final Object detail;

    /**
     * Randomly generated UUID or Sleuth Trace Id which is also visible in logs.
     */
    private final String traceId;

    /**
     * Error timestamp.
     */
    private final Instant timestamp = Instant.now();

    /**
     * MDC constant, see {@link brave.propagation.B3Propagation#TRACE_ID_NAME}
     */
    private static final String SLEUTH_TRACE_ID_MDC = "X-B3-TraceId";

    /**
     * If UUID is not specified it tries to obtain Sleuth Trace Id from {@link MDC}.
     * If trace id is null or empty, it generates {@link UUID} instead.
     *
     * @param message
     * @param detail
     */
    public ErrorResponse(String message, Object detail) {
        this(message, detail, StringUtils.isNotBlank(MDC.get(SLEUTH_TRACE_ID_MDC))
                ? MDC.get(SLEUTH_TRACE_ID_MDC)
                : UUID.randomUUID().toString()
        );
    }

}
