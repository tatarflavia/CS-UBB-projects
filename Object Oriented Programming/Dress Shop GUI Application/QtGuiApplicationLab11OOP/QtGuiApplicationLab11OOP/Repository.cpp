#include "Repository.h"
#include <iostream>

using namespace std;

//constructor
Repository::Repository()
{
	this->readFromFile();
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

void Repository::readFromFile()
{
	std::ifstream fin("file.txt");
	if (!fin.is_open())
		return;
	WeddingDress dress{};
	while (fin >> dress)
		this->repo.push_back(dress);
}

std::vector<WeddingDress> Repository::get_all()
{
	return this->repo;
}

//returns an element from position pos
WeddingDress Repository::get_elem_from_pos(int pos)
{
	return this->repo.at(pos);
}



//adds an elem to the dynamicVect
void Repository::add(WeddingDress & dress)
{
	if (this->search_elem_with_link(dress.get_photograph()))
		throw RepoError("It's already in the database!\n");
	this->repo.push_back(dress);
}
//removes an elem from the vector
void Repository::remove(const std::string & photo)
{
	if (this->search_elem_with_link(photo))
	{
		int pos = this->get_pos(photo);
		this->repo.erase(this->repo.begin() + pos);
	}
	
	
	

}
//updates an elem from the vector
void Repository::update(const std::string & photo, int size, float price, int quantity)
{
	if (this->search_elem_with_link(photo))
	{
		int pos = this->get_pos(photo);
		this->repo.at(pos).set_price(price);
		this->repo.at(pos).set_quantity(quantity);
		this->repo.at(pos).set_size(size);
	}
		
	
}

//returns true if the elem that has the link photo is in the vector and false otherwise
bool Repository::search_elem_with_link(const std::string & photo)
{
	for (auto i : this->repo)
		if (i.get_photograph().find(photo) == 0)
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
		if (i.get_size() == size)
			count++;
	}
	return count;
}
//destructor
Repository::~Repository()
{
}

FileRepository::FileRepository(std::string File) :file{ File }
{
	this->readFromFile();
}

void FileRepository::readFromFile()
{
	std::ifstream fin(this->file);
	if (!fin.is_open())
		return;
	WeddingDress dress{};
	while (fin >> dress)
		this->repo.push_back(dress);
	fin.close();

}

void FileRepository::writeToFile()
{
	std::ofstream fout(this->file);
	int i = 0;
	for (auto i : this->repo)
	{
		fout << i << "\n";
	}
	fout.close();
}

void FileRepository::add(WeddingDress & dress)
{
	this->Repository::add(dress);
	this->writeToFile();
}

void FileRepository::remove(const std::string & photo)
{
	this->Repository::remove(photo);
	this->writeToFile();
}

void FileRepository::update(const std::string & photo, int size, float price, int quantity)
{
	this->Repository::update(photo, size, price, quantity);
	this->writeToFile();
}
