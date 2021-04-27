#pragma once
#include "Repository.h"
#include "RepoForBasket.h"
#include "UndoAction.h"
#include "Validator.h"
#include <iostream>
#include <algorithm>
#include <memory>


using namespace std;

class Controller
{

private:
	Repository& repo;
	RepoForBasket& repoBasket;
	WeddingDressValidator& validate;
	std::vector<UndoAction*> undoActions;
	std::vector<UndoAction*> redoActions;

public:
	Controller(Repository& repo, RepoForBasket& repoBasket, WeddingDressValidator& valid);
	int size();
	int size_of_basket();
	float get_price();
	void add(int size, const std::string& colour, float price, int quantity, const std::string& photograph);
	void addToBasket(std::string photo);
	int get_number_of_dresses_after_size(int size);
	void remove(const std::string& photograph);
	void update(const std::string& photo, int size, float price, int quantity);
	void undo();
	void redo();
	Repository& get_repo();
	RepoForBasket& get_basket();
	std::vector<WeddingDress> sort_by_price();
	std::vector<WeddingDress> random_shuffle();
	
	~Controller();

};

