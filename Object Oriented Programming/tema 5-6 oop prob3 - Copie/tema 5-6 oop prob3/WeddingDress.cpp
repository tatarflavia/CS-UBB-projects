#include "WeddingDress.h"
#include <Windows.h>
#include<iostream>

//default constructor
WeddingDress::WeddingDress() :size{ 38 }, colour{ "white" }, price{ 3000 }, quantity{ 50 }, photograph{"https://media.brides.com/photos/58fc14b71fc7a71d29203abe/master/w_767,c_limit/julie-vino-wedding-dresses-spring-2018-009.jpg"}
{
}

//setter for size used in updating
void WeddingDress::set_size(int siz)
{
	this->size = siz;
}

//setter for price used in updating
void WeddingDress::set_price(float price)
{
	this->price = price;
}

//setter for quantity used in updating
void WeddingDress::set_quantity(int quantity)
{
	this->quantity = quantity;
}

void WeddingDress::set_photo(const std::string & photograph)
{
	this->photograph = photograph;
}

void WeddingDress::set_colour(const std::string & colour)
{
	this->colour = colour;
}

//constructor
WeddingDress::WeddingDress(int siz, const std::string& col, float pric, int qu, const std::string& photo) : size{ siz }, colour{ col }, price{ pric }, quantity{ qu }, photograph{photo}
{
}

//getter for size
int WeddingDress::get_size() 
{
	return this->size;
}
//getter for colour
std::string WeddingDress::get_colour()
{
	return this->colour;
}
//getter for price
float WeddingDress::get_price()
{
	return this->price;
}
//getter for quantity
int WeddingDress::get_quantity()
{
	return this->quantity;
}
//getter for link photo
std::string WeddingDress::get_photograph()
{
	return this->photograph;
}

WeddingDress & WeddingDress::operator=(WeddingDress  dress)
{
	this->colour = dress.get_colour();
	this->photograph = dress.get_photograph();
	this->size = dress.get_size();
	this->price = dress.get_price();
	this->quantity = dress.get_quantity();
	return *this;
}

bool WeddingDress::operator==(WeddingDress& dress)
{
	return this->get_colour() == dress.get_colour() && this->get_price() == dress.get_price() && this->get_price() == dress.get_price() && this->get_quantity() == dress.get_quantity() && this->get_size() == dress.get_size();
}

std::ofstream & operator<<(std::ofstream & fout, const WeddingDress & dress)
{
	fout << dress.get_size() << " " << dress.get_colour() << " " << dress.get_price() << " " << dress.get_quantity() << " " << dress.get_photograph();
	return fout;
}

std::ifstream & operator>>(std::ofstream & fin, WeddingDress & dress)
{
	int size = 0;
	std::string colour;
	float price = 0;
	int quantity = 0;
	std::string photograph;
	fin >> size >> colour >> price >> quantity >> photograph);
	dress.set_price(price);
	dress.set_quantity(quantity);
	dress.set_size(size);
	dress.set_colour(colour);
	dress.set_photo(photograph);
	return fout;
}

//WeddingDress & WeddingDress::operator=(WeddingDress dress)
//{
//	this->colour = dress.get_colour();
//	this->photograph = dress.get_photograph();
//	this->size = dress.get_size();
//	this->price = dress.get_price();
//	this->quantity = dress.get_quantity();
//	return *this;
//}

//destructor
WeddingDress::~WeddingDress()
{
}
