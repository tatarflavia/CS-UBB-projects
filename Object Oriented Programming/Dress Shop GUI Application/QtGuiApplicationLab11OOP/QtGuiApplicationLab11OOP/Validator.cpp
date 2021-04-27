#include "Validator.h"




ValidatorError::ValidatorError(std::string message) : message_to_show{ message }
{
}

const char * ValidatorError::what() const noexcept
{
	return this->message_to_show.c_str();
}

void WeddingDressValidator::validate(WeddingDress & dress)
{
	std::string errors;
	if (dress.get_colour().size() < 3)
		errors += std::string("The wedding dress size cannot be less than 3 characters!\n");
	if (dress.get_price() < 1000)
		errors += std::string("The wedding dress price has to be bigger than 1000!\n");
	if (dress.get_photograph().empty())
		errors += std::string("The wedding dress link can't be empty!\n");
	if (dress.get_quantity() < 1)
		errors += std::string("The wedding dress quantity has to be bigger than 0!\n");
	if (dress.get_size() % 2 != 0 || dress.get_size() < 36)
		errors += std::string("The wedding dress size has to be bigger than 36 and has to be an even number!\n");
	if (errors.size() > 0)
		throw ValidatorError(errors);
}

RepoError::RepoError(std::string message) :mess{ message }
{
}

const char * RepoError::what() const noexcept
{
	return this->mess.c_str();
}

InputDataError::InputDataError(std::string message) :message_to_show{ message }
{
}

const char * InputDataError::what() const noexcept
{
	return this->message_to_show.c_str();
}
