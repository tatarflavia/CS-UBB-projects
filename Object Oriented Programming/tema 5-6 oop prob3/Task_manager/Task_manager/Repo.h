#pragma once
#include "Task.h"
#include <vector>
#include <iostream>
#include <string>
#include <fstream>
class Repo
{
private:
	std::vector<Task> repo;
public:
	Repo();
	std::vector<Task> get_all();
	std::vector<Task> get_sort();
	void read_from_file();
	~Repo();
};

