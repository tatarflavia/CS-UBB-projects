#pragma once
#include "Bag.h"
#include <iostream>
typedef int TElem;
class BagIterator
{
	friend class Bag;

private:
	//the iterator stores a reference to the container 
	const Bag& bag;
	//other specific attributes: current, etc
	//we might have empty spaces so we need a position to know where we are in the in the bag and we need a current to know how many elements have passed
	int current; //current counts how many elements have passe
	int position; //the position of the current element
	

public:
	BagIterator(const Bag& THEbag);
	TElem getCurrent();
	bool valid();
	void next();
	void first();
	
};

