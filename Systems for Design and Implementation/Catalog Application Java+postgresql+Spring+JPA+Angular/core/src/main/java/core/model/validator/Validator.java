package core.model.validator;

import core.exception.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
