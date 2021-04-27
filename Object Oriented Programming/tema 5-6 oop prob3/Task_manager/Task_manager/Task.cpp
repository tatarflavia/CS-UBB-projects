#include "Task.h"



Task::Task(std::string descrip, int dur, int prio) :description{ descrip }, duration{ dur }, priority{prio}
{
}

std::string Task::get_desc()
{
	return this->description;
}

int Task::get_durartion()
{
	return this->duration;
}

int Task::get_priority()
{
	return this->priority;
}


Task::~Task()
{
}
