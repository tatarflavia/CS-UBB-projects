
#pragma once
#include "SortedSet.h"
#include <iostream>

typedef int TComp;

typedef TComp TElem;


class SortedSet;

class SortedSetIterator
{
	friend class SortedSet;

private:
	//the iterator stores a reference to the container 
	const SortedSet& set;
	//other specific attributes: current, etc
	int current;
	SortedSetIterator(const SortedSet& set);

public:
	TComp getCurrent();
	bool valid();
	void next();
	void first();
};


