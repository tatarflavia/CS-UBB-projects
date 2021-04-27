#include "Repository.h"
#include<iostream>

//constructor
Repository::Repository()
{
}
//returns the pos where the elem with the photo photo is found
int Repository::get_pos(const std::string & photo)
{
	int pos = 0;
	for (auto i : this->repo)
	{
		if (i.get_photograph().find(photo) == 0)
			return pos;
		pos++;
	}
	return -1;
}

std::vector<WeddingDress> Repository::get_all()
{
	return this->repo;
}

//returns an element from position pos
WeddingDress * Repository::get_elem_from_pos(int pos)
{
	return &this->repo.at(pos);
}



//adds an elem to the dynamicVect
void Repository::add(WeddingDress & dress)
{	
	this->repo.push_back(dress);
}
//removes an elem from the vector
void Repository::remove(const std::string & photo)
{
	int pos = this->get_pos(photo);
	this->repo.erase(this->repo.begin() + pos);

}
//updates an elem from the vector
void Repository::update(const std::string & photo, int size, float price, int quantity)
{
	int pos = this->get_pos(photo);
	this->repo.at(pos).set_price(price);
	this->repo.at(pos).set_quantity(quantity);
	this->repo.at(pos).set_size(size);
}

//returns true if the elem that has the link photo is in the vector and false otherwise
bool Repository::search_elem_with_link(const std::string & photo)
{
	for (auto i:this->repo)
		if (i.get_photograph().find(photo)==0)
			return true;
	return false;
}


//getter for the size of the vector
int Repository::get_size()
{
	return this->repo.size();
}
//returns the number of elems from the vector that have the size=int size
int Repository::get_number_of_dresses_after_size(int size)
{
	int count = 0;
	for (auto i : this->repo)
	{
		if (i.get_size()==size)
			count++;
	}
	return count;
}
//destructor
Repository::~Repository()
{
}

