#pragma once
#include <iostream>
#include "SortedSetIterator.h"


typedef int TElem;

typedef TElem TComp;



typedef bool(*Relation)(TComp, TComp);



class SortedSet {
	friend class SortedSetIterator;

private:

	TComp* elems; //array for the elems
	int* indexes; //array for the indexes
	int capacity; 
	int head; //index for the head
	Relation rel;
	int firstEmpty;

public:

	//constructor

	SortedSet(Relation r);



	//adds an element to the sorted set

	//if the element was added, the operation returns true, otherwise (if the element was already in the set) 

	//it returns false

	bool add(TComp e);





	//removes an element from the sorted set

	//if the element was removed, it returns true, otherwise false

	bool remove(TComp e);



	//checks if an element is in the sorted set

	bool search(TComp elem) const;





	//returns the number of elements from the sorted set

	int size() const;



	//checks if the sorted set is empty

	bool isEmpty() const;

	void tostr();


	int getRange() const;


	//returns an iterator for the sorted set

	SortedSetIterator iterator() const;



	// destructor

	~SortedSet();




};
