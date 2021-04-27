#include "RepoForBasket.h"
#include<iostream>
#include <Windows.h>

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
void RepoForBasket::execute_application()
{
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




RepoForBasketCSV::RepoForBasketCSV(std::string csv) :CSVfile{csv}
{
}

void RepoForBasketCSV::writeToCSV()
{
	std::ofstream fout;
	fout.open(this->CSVfile, std::ofstream::out | std::ofstream::trunc); //open for trunc, erases what was in the file before
	int i = 0;
	for (auto i : this->repo)
	{
		fout << i.get_price() <<","<<i.get_size()<<","<<i.get_colour()<<","<<i.get_quantity()<<","<<i.get_photograph()<< "\n";
	}
	fout.close();
}

void RepoForBasketCSV::add(WeddingDress & dress)
{
	this->RepoForBasket::add(dress);
	this->writeToCSV();
}

void RepoForBasketCSV::execute_application()
{
	ShellExecuteA(NULL, NULL, "excel.exe", this->CSVfile.c_str(), NULL, SW_SHOWMAXIMIZED);
}

RepoForBasketHTML::RepoForBasketHTML(std::string html) :HTMLfile{html}
{
}

void RepoForBasketHTML::writeToHTML()
{
	std::ofstream fout(this->HTMLfile);
	fout << "<!DOCTYPE html>" << "\n";
	fout << "<html>" << "\n";
	fout << "<head>" << "\n";
	fout << "<title>Wedding Dresses shopping basket</title>" << "\n";
	fout << "</head>" << "\n";
	fout << "<body>" << "\n";
	fout << "<table border = \"1\">" << "\n"; //maybe an error in html here
	fout << "<tr>" << "\n";
	fout << "<td>Price</td>" << "\n";
	fout << "<td>Size</td>" << "\n";
	fout << "<td>Colour</td>" << "\n";
	fout << "<td>Quantity</td>" << "\n";
	fout << "<td>Photo link</td>" << "\n";
	fout << "</tr>" << "\n";

	int i = 0;
	for (auto i : this->repo)
	{
		fout << "<tr>" << "\n";
		fout << "<td>"<<i.get_price()<<"</td>" << "\n";
		fout << "<td>" << i.get_size()<< "</td>" << "\n";
		fout << "<td>" << i.get_colour() << "</td>" << "\n";
		fout << "<td>" << i.get_quantity() << "</td>" << "\n";
		fout << "<td><a href = \"" << i.get_photograph() << "\">Link</a></td>" << "\n";
		fout << "</tr>" << "\n";
	}

	fout << "</table>" << "\n";
	fout << "</body>" << "\n";
	fout << "</html>" << "\n";
	
	fout.close();

}

void RepoForBasketHTML::add(WeddingDress & dress)
{
	this->RepoForBasket::add(dress);
	this->writeToHTML();
}

void RepoForBasketHTML::execute_application()
{
	ShellExecuteA(NULL, NULL, "chrome.exe", this->HTMLfile.c_str(), NULL, SW_SHOWMAXIMIZED);
}
