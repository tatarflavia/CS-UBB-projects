#include "Repo.h"
#include <algorithm>


Repo::Repo()
{
	this->read_from_file();
}

std::vector<Task> Repo::get_all()
{
	return this->repo;
}

std::vector<Task> Repo::get_sort()
{
	std::vector<Task> aux = this->repo;
	for (int i = 0; i < aux.size() - 1; i++)
	{
		for (int j = i+1; j < aux.size(); j++)
		{
			if (aux.at(i).get_priority() > aux.at(j).get_priority())
				std::swap(aux.at(i), aux.at(j));
		}
	}
	return aux;
}

void Repo::read_from_file()
{
	std::ifstream f("File.txt");
	std::string description;
	int duration;
	int priority;
	while (f >> description >> duration >> priority)
	{
		Task tas{ description,duration,priority };
		this->repo.push_back(tas);
	}
}


Repo::~Repo()
{
}
