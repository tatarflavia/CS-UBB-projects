#pragma once
#include <iostream>
#include <string>
#include"WeddingDress.h"
class ValidatorError : public std::exception
{
private:
	std::string message_to_show;
public:
	ValidatorError(std::string message);
	const char* what() const noexcept override;
};

class InputDataError : public std::exception
{
private:
	std::string message_to_show;
public:
	InputDataError(std::string message);
	const char* what() const noexcept override;
};


class WeddingDressValidator
{
public:
	void validate(WeddingDress& dress);
};

class RepoError : public std::exception
{
private:
	std::string mess;
public:
	RepoError(std::string message);
	const char* what() const noexcept override;
};
