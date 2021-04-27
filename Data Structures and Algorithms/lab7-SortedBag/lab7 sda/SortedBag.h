#pragma once
#include "SortedBagIterator.h"
#include <iostream>

typedef int TComp;

typedef TComp TElem;

typedef bool(*Relation)(TComp, TComp);



class Node {
	friend class SortedBag;
	friend class SortedBagIterator;
private:
	TComp info; //info of the node
	int frequency; //frequency of the elem
	bool nil; //nil is false when the node is an empty one
public:
	//constructor
	Node(TComp info);
	Node();
	//destructor
	~Node();
	TComp get_info();
	int get_freq();
	void toStr();
	void set_freq(int i);
	
};

class SortedBag {

	friend class SortedBagIterator;
	friend class Node;

private:

	/*representation of SortedBag*/
	Node* elems; //array for the elems, it has nodes
	int* parents; //array for the indexes
	int* left_child; //array for left children
	int* right_child; //array for the right children
	int capacity; //cap of the array
	int root; //index for the rooot
	Relation rel;
	int firstEmpty; //firstEmpty increases only when a new node is put in the elems array, not when only a node is modified
	int sizeOfArray; //size of the elems array, if it has 4 4 => 2 elems
	int get_pos(TComp e) const; //=> the position of the elem e
	int get_min_pos( int pos); //searches for the min_elem in the left side of the elem from that pos

public:

	//constructor

	SortedBag(Relation r);

	void toStr();

	//adds an element to the sorted bag

	void add(TComp e);



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

	void get_intersection(SortedBag new_bag);



	//destructor

	~SortedBag();

};