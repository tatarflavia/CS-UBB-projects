package domain.validator;

import exception.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
