#pragma once
#include "DynamicVector.h"
#include <vector>
class RepoForBasket
{
private:
	std::vector<WeddingDress> repo;
	float shoppingSum;
public:
	RepoForBasket();
	float get_total();
	void add_to_total(float price);
	
	WeddingDress* get_all();
	WeddingDress* get_elem_from_pos(int pos);
	int get_size();
	void add(WeddingDress& dress);
	
	~RepoForBasket();
};

