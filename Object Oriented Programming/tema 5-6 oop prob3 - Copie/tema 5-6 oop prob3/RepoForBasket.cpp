#include "RepoForBasket.h"
#include<iostream>

//constructor
RepoForBasket::RepoForBasket():shoppingSum {0}
{
}

//returns the total sum
float RepoForBasket::get_total()
{
	return this->shoppingSum;
}
//adds the price to the total sum
void RepoForBasket::add_to_total(float price)
{
	this->shoppingSum += price;
}


//returns an element from position pos
WeddingDress * RepoForBasket::get_elem_from_pos(int pos)
{
	return &this->repo.at(pos);
}



//adds the elem dress to the vector
void RepoForBasket::add(WeddingDress & dress)
{
	this->repo.push_back(dress);
}
//returns the size of the vector
int RepoForBasket::get_size()
{
	return this->repo.size();
}

//destructor
RepoForBasket::~RepoForBasket()
{
}
