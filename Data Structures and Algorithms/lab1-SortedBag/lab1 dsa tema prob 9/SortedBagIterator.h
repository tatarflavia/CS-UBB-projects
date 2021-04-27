
#pragma once
#include "SortedBag.h"
#include <iostream>

typedef int TComp;

typedef TComp TElem;


class SortedBag;

class SortedBagIterator
{
	friend class SortedBag;

private:
	//the iterator stores a reference to the container 
	const SortedBag& bag;
	//other specific attributes: current, etc
	int current;
	SortedBagIterator(const SortedBag& bag);

public:
	TComp getCurrent();
	bool valid();
	void next();
	void first();
};


