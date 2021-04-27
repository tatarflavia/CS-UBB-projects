#pragma once
#include <string>
#include <iostream>
class Task
{
private:
	std::string description;
	int duration;
	int priority;
public:
	Task(std::string descrip,int dur,int prio);
	std::string get_desc();
	int get_durartion();
	int get_priority();
	~Task();
};

