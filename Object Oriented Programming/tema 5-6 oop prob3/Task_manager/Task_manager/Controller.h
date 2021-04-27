#pragma once
#include "Repo.h"
#include <vector>
#include <iostream>

class Controller
{
private:
	Repo& repo;
public:
	Controller(Repo& repo);
	std::vector<Task> get_repo();
	std::vector<Task> get_sort();
	int get_total_duration(int priority);
	~Controller();
};

