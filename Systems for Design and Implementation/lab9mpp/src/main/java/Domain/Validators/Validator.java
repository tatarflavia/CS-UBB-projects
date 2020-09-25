package Domain.Validators;

import Exception.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
