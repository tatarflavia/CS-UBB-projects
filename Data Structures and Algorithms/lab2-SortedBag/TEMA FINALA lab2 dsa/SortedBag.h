#pragma once
#include "SortedBagIterator.h"
#define NULL_TCOMP 0
class SortedBagIterator;
typedef int TComp;

typedef TComp TElem;

typedef bool(*Relation)(TComp, TComp);


class DLLNode {
	friend class SortedBag;
	friend class SortedBagIterator;
private:
	DLLNode* next;
	DLLNode* prev;
	TComp info;
	int frequency;
public:
	//constructor
	DLLNode(TComp info);
	DLLNode();
	//destructor
	~DLLNode();
	DLLNode & operator=(const DLLNode & node);
	TComp get_info();
	DLLNode* get_next();
	DLLNode* get_prev();
	int get_freq();
	void toStr();
	void set_freq(int i);
	void set_info(TComp i);
	void set_next(DLLNode* next);
	void set_prev(DLLNode* prev);
};

class SortedBag {
	friend class DLLNode;
	friend class SortedBagIterator;

private:

	//declaration of double linked list
	DLLNode* head;
	DLLNode* tail;
	Relation rel;



public:

	//constructor

	SortedBag(Relation r);

	//copy constructor
	//SortedBag(const SortedBag& bag);

	//adds an element to the sorted bag

	void add(TComp e);

	//assignment operator
	//SortedBag& operator=(const SortedBag& bag);

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

	TComp return_smallest_freq();

	//destructor

	~SortedBag();


};
