#pragma once
#include "BagIterator.h"


typedef int TElem;
typedef int TKey;



class Bag {
	friend class BagIterator;


private:
	TElem* elems; //array for the TPositions in the hash table
	TElem* state_of_elems; //array for the state of the elem -1 for empty, 0 for occupied, 1 for deleted
	int m; //sort of capacity for the hash table
	TElem h(TElem k,int i) const;
	TElem hashCode(TElem k) const;
	int sizeOfTheBag;
	bool isPrime(int number);
	int firstPrime(int number);

public:
	
	//constructor

	Bag();



	//adds an element to the bag

	void add(TElem e);



	//removes one occurrence of an element from a bag

	//returns true if an element was removed, false otherwise (if e was not part of the bag)

	bool remove(TElem e);



	//checks if an element appearch is the bag

	bool search(TElem e) const;



	//returns the number of occurrences for an element in the bag

	int nrOccurrences(TElem e) const;



	//returns the number of elements from the bag

	int size() const;



	//returns an iterator for this bag

	BagIterator iterator() const;



	//checks if the bag is empty

	bool isEmpty() const;

	void toStr();

	//destructor

	~Bag();

};