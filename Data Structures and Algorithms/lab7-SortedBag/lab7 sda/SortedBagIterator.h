#pragma once
#include "SortedBag.h"
#include <iostream>
#include <stack>

typedef int TComp;

typedef TComp TElem;

class SortedBag;

class SortedBagIterator
{
	friend class SortedBag;
private:
	//the iterator stores a reference to the container 
	const SortedBag& bag;
	//other specific attributes: current, current frequency
	
	int CurrentFreq;
	TComp current_elem;
	std::stack<TComp> stack; //empty stack
	//constructor
	SortedBagIterator(const SortedBag& bag);

public:
	TComp getCurrent();
	bool valid();
	void next();
	void first();
};


