package cz.lundegaard.backend.exception;

import java.util.Optional;

public interface DetailAware {

    Optional<Object> getDetail();

}
