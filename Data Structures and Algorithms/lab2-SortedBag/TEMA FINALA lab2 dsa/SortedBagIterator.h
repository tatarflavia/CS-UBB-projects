
#pragma once
#include "SortedBag.h"
#include <iostream>

typedef int TComp;

typedef TComp TElem;

class SortedBag;

class SortedBagIterator
{
	friend class SortedBag;
	friend class DLLNode;
private:
	//the iterator stores a reference to the container 
	const SortedBag& bag;
	//other specific attributes: current, current frequency
	DLLNode* current;
	int CurrentFreq;
	//constructor
	SortedBagIterator(const SortedBag& bag);

public:
	TComp getCurrent();
	bool valid();
	void next();
	void first();
};


