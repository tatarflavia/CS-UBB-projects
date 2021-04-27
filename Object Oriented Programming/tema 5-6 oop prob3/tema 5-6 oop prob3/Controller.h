#pragma once
#include "Repository.h"
#include "RepoForBasket.h"
#include "Comparator.h"
#include "Validator.h"
#include <iostream>
class Controller
{

private:
	Repository* repo;
	RepoForBasket& repoBasket;
	WeddingDressValidator& validate;

public:
	Controller(Repository* repo,RepoForBasket& repoBasket, WeddingDressValidator& valid);
	int size();
	int size_of_basket();
	float get_price();
	void add(int size, const std::string& colour, float price, int quantity, const std::string& photograph);
	void addToBasket(WeddingDress & dress);
	int get_number_of_dresses_after_size(int size);
	void remove(const std::string& photograph);
	void update(const std::string& photo,  int size, float price, int quantity);
	Repository* get_repo();
	RepoForBasket& get_basket();
	~Controller();
	std::vector<WeddingDress> sort_by_size();
	std::vector<WeddingDress> sort_by_price();
private:
	std::vector<WeddingDress> Generic_sort(Comparator<WeddingDress>* compare,std::vector<WeddingDress> Repo);
};

