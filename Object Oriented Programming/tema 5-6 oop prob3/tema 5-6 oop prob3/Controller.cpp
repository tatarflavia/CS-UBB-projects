#include "Controller.h"
#include<string>
#include<iostream>

//constructor
Controller::Controller(Repository * Repo, RepoForBasket& RepoBasket, WeddingDressValidator& valid) :repo{ Repo }, repoBasket{ RepoBasket }, validate{valid}
{
}
//returns the size of the repo
int Controller::size()
{
	return this->repo->get_size();
}
//returns the size of the shopping basket
int Controller::size_of_basket()
{
	return this->repoBasket.get_size();
}
//returns the price from the shopping basket
float Controller::get_price()
{
	return this->repoBasket.get_total();
}

//makes an elem and adds it to the repo
void Controller::add(int size, const std::string & colour, float price, int quantity, const std::string & photograph)
{
	WeddingDress dress{ size,colour,price,quantity,photograph };
	this->validate.validate(dress);
	this->repo->add(dress);
}
//adds the elem dress to the basket
void Controller::addToBasket(WeddingDress & dress)
{
	this->repoBasket.add(dress);
	this->repoBasket.add_to_total(dress.get_price());
}
//returns the number of dresses that have the size=int size from the repo
int Controller::get_number_of_dresses_after_size(int size)
{
	return this->repo->get_number_of_dresses_after_size(size);
}
//removes an elem from the repo that has the link=photo
void Controller::remove(const std::string & photograph)
{
	this->repo->remove(photograph);
}
//updates an elem from the repo that has the link=photo
void Controller::update(const std::string & photo,int size,float price,int quantity)
{
	this->repo->update(photo, size, price, quantity);
}
//returns the elems from the repo
Repository * Controller::get_repo()
{
	return this->repo;
}
//returns the elems from the basket
RepoForBasket & Controller::get_basket()
{
	return this->repoBasket;
}
//destructor
Controller::~Controller()
{
}

std::vector<WeddingDress> Controller::sort_by_size()
{
	CompareDescendingBySize* comp_by_size = new CompareDescendingBySize;
	std::vector<WeddingDress> sorted_dresses = this->Generic_sort(comp_by_size, this->repo->get_all());
	std::cout << "\n";
	std::cout << "\n";
	std::cout << "The dresses sorted descendingly by size are:\n";
	for (auto i : sorted_dresses)
	{
		std::cout <<"The dress has the size: "<< i.get_size() <<", the colour: "<< i.get_colour() << ", the price: " << i.get_price() << ", the quantity: " << i.get_quantity() << " and the photo: " << i.get_photograph()<<"\n";
	}
	delete comp_by_size;
	return sorted_dresses;
}

std::vector<WeddingDress> Controller::sort_by_price()
{
	CompareAscendingByPrice* comp_by_price = new CompareAscendingByPrice;
	std::vector<WeddingDress> sorted_by_price_dresses = this->Generic_sort(comp_by_price, this->repo->get_all());
	std::cout << "\n";
	std::cout << "\n";
	std::cout << "The dresses sorted ascendingly by price are:\n";
	for (auto i : sorted_by_price_dresses)
	{
		std::cout << "The dress has the size: " << i.get_size() << ", the colour: " << i.get_colour() << ", the price: " << i.get_price() << ", the quantity: " << i.get_quantity() << " and the photo: " << i.get_photograph() << "\n";
	}
	delete comp_by_price;
	return sorted_by_price_dresses;
}



std::vector<WeddingDress> Controller::Generic_sort(Comparator<WeddingDress>* compare, std::vector<WeddingDress> Repo)
{
	for (int i = 0; i < this->repo->get_size()-1; i++)
	{
		for (int j = i + 1; j < this->repo->get_size(); j++)
		{
			if (not(compare->relation(Repo[i], Repo[j])))
			{
				WeddingDress aux;
				aux = Repo[i];
				Repo[i] = Repo[j];
				Repo[j] = aux;
			}
		}
	}
	return Repo;
}
