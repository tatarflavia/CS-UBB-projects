#pragma once
#include "SortedBagIterator.h"
class SortedBagIterator;
typedef int TComp;

typedef TComp TElem;

typedef bool(*Relation)(TComp, TComp);


class SortedBag {

	friend class SortedBagIterator;

private:

	//declaration of dynamicArray
	TComp* elems;
	int siz;
	int capacity;
	Relation rel;



public:

	//constructor

	SortedBag(Relation r);

	//copy constructor
	SortedBag(const SortedBag& bag);

	//adds an element to the sorted bag

	void add(TComp e);

	//assignment operator
	SortedBag& operator=(const SortedBag& bag);

	//implemented in class
	int toSet();

	void toStr();

	//removes one occurrence of an element from a sorted bag

	//returns true if an element was removed, false otherwise (if e was not part of the sorted bag)

	bool remove(TComp e);



	//checks if an element appearch is the sorted bag

	bool search(TComp e) const;



	//returns the number of occurrences for an element in the sorted bag

	int nrOccurrences(TComp e) const;



	//returns the number of elements from the sorted bag

	int size() const;



	//returns an iterator for this sorted bag

	SortedBagIterator iterator() const;



	//checks if the sorted bag is empty

	bool isEmpty() const;



	//destructor

	~SortedBag();

};