#include "Controller.h"



Controller::Controller(Repo& repo) : repo{repo}
{
}

std::vector<Task> Controller::get_repo()
{
	return this->repo.get_all();
}

std::vector<Task> Controller::get_sort()
{
	return this->repo.get_sort();
}

int Controller::get_total_duration(int priority)
{
	int dur = 0;
	for (auto i : this->repo.get_all())
	{
		if (i.get_priority() == priority)
			dur += i.get_durartion();
	}
	return dur;
}


Controller::~Controller()
{
}
