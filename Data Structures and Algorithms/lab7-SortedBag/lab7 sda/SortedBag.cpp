#include "SortedBag.h"
#include <stack>

//Time complexity: BC=teta(1) if the elem is found quicly
//WC=teta(n) if the tree is degenerated, AC=teta(n),total=O(n),n=nr of nodes in the tree
//extra space complexity:teta(1)
int SortedBag::get_pos(TComp e) const
{
	int current = this->root;
	while (this->elems[current].nil != true)
	{
		if (this->elems[current].get_info() == e)
			return current;
		else
			if (this->rel(this->elems[current].get_info(), e))
			{
				if (this->right_child[current] == -1)
					break;
				else {
					int pos = current;
					current = this->right_child[pos];
				}

			}
			else {
				if (this->left_child[current] == -1)
					break;
				else {
					int pos = current;
					current = this->left_child[pos];
				}
			}
	}
}

//Time complexity: BC=teta(1), if the while is exited quickly
//WC=teta(n) if the tree is a degenerated one, AC=teta(length of the tree),total=O(n),n=nr of nodes in the bag
//extra space complexity:teta(1)
int SortedBag::get_min_pos(int pos)
{
	int current = pos;
	while (this->elems[current].nil != true && this->left_child[current] != -1)
		current = this->left_child[current];
	return current;
}

void SortedBag::toStr()
{
	std::cout << "elems:\n";
	for (int i = 0; i < this->capacity ; i++)
		this->elems[i].toStr();
	std::cout << "left_child:\n";
	for (int i = 0; i < this->capacity ; i++)
		std::cout << this->left_child[i] << ",";
	std::cout << "\n right_child:\n";
	for (int i = 0; i < this->capacity ; i++)
		std::cout << this->right_child[i] << ",";
	std::cout << "\n parents:\n";
	for (int i = 0; i < this->capacity; i++)
		std::cout << this->parents[i] << ",";
	std::cout << "\n";
}

//constructorfor the sorted bag
//Time complexity:always teta(capacity) for the for's
//extra space complexity:teta(1)
SortedBag::SortedBag(Relation r)
{
	this->sizeOfArray = 0;
	this->capacity = 5;
	this->elems = new Node[capacity];

	this->left_child = new int[capacity];
	//init for the left list
	for (int i = 0; i < this->capacity ; i++)
		this->left_child[i] = -1;
	this->parents= new int[capacity];
	//init for the parent list
	for (int i = 0; i < this->capacity ; i++)
		this->parents[i] = -1;
	this->right_child= new int[capacity];
	//init for the right list
	for (int i = 0; i < this->capacity ; i++)
		this->right_child[i] =-1;
	this->root = -1;
	this->rel = r;
	this->firstEmpty = 0;
}


//Time complexity: BC=teta(1),if thefirstempty=0 or 1
//WC=O(n), where n is the number of nodes from search for an empty pos + teta(capacity) for resize => teta(capacity of array)
//total=O(capacity of array)
//extra space complexity:teta(1)
void SortedBag::add(TComp e)
{
	//is bag is empty
	if(this->isEmpty())
	{ 
		this->root = 0;
		this->elems[this->root] = Node{ e };
		this->elems[this->root].nil = false;
		this->firstEmpty = 1;
		this->sizeOfArray = 1;
	}
	//it only has the root : teta(1)
	else if (this->firstEmpty == 1)
	{
		if (this->elems[this->root].get_info() == e)
		{
			this->elems[this->root].set_freq(this->elems[this->root].get_freq() + 1);
			this->sizeOfArray++;
		}
		else {if (this->rel(this->elems[this->root].get_info(), e))
		{
			this->right_child[this->root] = 1;
			this->elems[this->firstEmpty] = Node{ e };
			this->parents[this->firstEmpty] = this->root;
			this->elems[this->firstEmpty].nil = false;
			this->firstEmpty++;
			this->sizeOfArray++;
		}
		else {
			this->left_child[this->root] = 1;
			this->elems[this->firstEmpty] = Node{ e };
			this->parents[this->firstEmpty] = this->root;
			this->elems[this->firstEmpty].nil = false;
			this->firstEmpty++;
			this->sizeOfArray++;
		}}
		
	}
	else
	{
		//complex:teta(n)
	if (this->firstEmpty == this->capacity)
		{
			//resize:teta(capacity of the array of the bag)
			//double the capacity,copy the elems and new init for the indexes
			int cap = this->capacity;
			this->capacity = this->capacity * 2;
			Node* el = new Node[this->capacity];
			for (int i = 0; i < this->firstEmpty; i++)
				el[i] = this->elems[i];
			delete[] this->elems;
			this->elems = el;
			int* ele = new int[this->capacity];
			for (int i = 0; i < this->firstEmpty; i++)
				ele[i] = this->right_child[i];
			delete[] this->right_child;
			this->right_child = ele;
			for (int i = cap; i < this->capacity; i++)
				this->right_child[i] = -1;
			int* elem = new int[this->capacity];
			for (int i = 0; i < this->firstEmpty; i++)
				elem[i] = this->left_child[i];
			delete[] this->left_child;
			this->left_child = elem;
			for (int i = cap; i < this->capacity; i++)
				this->left_child[i] = -1;
			int* er = new int[this->capacity];
			for (int i = 0; i < this->firstEmpty; i++)
				er[i] = this->parents[i];
			delete[] this->parents;
			this->parents = er;
			for (int i = cap; i < this->capacity; i++)
				this->parents[i] = -1;
		}
		//search for an empty pos: O(n) for that search
		int current = this->root;
		int empty_parent = -1;
		bool right = true;
		while (this->elems[current].nil != true && empty_parent==-1)
		{
			if (this->elems[current].get_info() == e)
			{
				this->elems[current].set_freq(this->elems[current].get_freq() + 1);
				empty_parent = -100;
				this->sizeOfArray++;
			}
			else {if (this->rel(this->elems[current].get_info(), e))
			{
				if (this->right_child[current] == -1)
					empty_parent = current;
				else {int pos = current;
				current = this->right_child[pos];}	
			}
			else {
				if (this->left_child[current] == -1)
				{
					empty_parent = current;
					right = false;
				}
				else {int pos = current;
				current = this->left_child[pos];}
			}}
		
		}
		//teta(1)
		if (empty_parent != -100)
		{
			this->elems[this->firstEmpty] = Node{ e };
			this->elems[this->firstEmpty].nil = false;
			this->parents[this->firstEmpty] = empty_parent;
			if (right)
				this->right_child[empty_parent] = this->firstEmpty;
			else
				this->left_child[empty_parent] = this->firstEmpty;
			this->elems[this->firstEmpty].nil = false;
			this->sizeOfArray++;
			this->firstEmpty++;
		}
	}
}

//Time complexity: BC=O(n), where n is the number of nodes, if the size=0, or if the elem found has a frequency>1, cause we still have to find it
//or if the elem found has no children or one child 
//WC=O(n) from search + O(n) from get_min_pos() function when the node found has 2 children => O(n)
//total=O(n),n=nr of nodes in the tree
//extra space complexity:teta(1)
bool SortedBag::remove(TComp e)
{
	//teta(1)
	if (this->sizeOfArray == 0)
		return false;
	//O(n) where n is the number of nodes
	if (not(this->search(e)))
		return false;
	else 
	{//we search for it
		int current = this->root;
		bool found = false;
		while (this->elems[current].nil != true && found==false)
		{
			if (this->elems[current].get_info() == e)
			{
				//teta(1)
				if (this->elems[current].get_freq()>1)
				{
					this->elems[current].set_freq(this->elems[current].get_freq() - 1);
					this->sizeOfArray--;
					return true;
				}
				else
				{//we found it
				if (this->right_child[current] == -1 and this->left_child[current] == -1)
				{
					//it's a leaf:teta(1)
					this->elems[current] = Node{}; //put an empty node there
					this->parents[current] = -1;
					this->sizeOfArray--;
					return true;
				}
				else if (this->right_child[current] != -1 && this->left_child[current] == -1)
				{
					//it only has a right child:teta(1)
					int pos_of_the_child = this->right_child[current];
					this->elems[current]=this->elems[this->right_child[current]];
					this->right_child[current] = this->right_child[pos_of_the_child];
					this->left_child[current] = this->left_child[pos_of_the_child];
					this->parents[current] = this->parents[pos_of_the_child];
					this->elems[pos_of_the_child] = Node{};
					this->parents[pos_of_the_child] = -1;
					this->left_child[pos_of_the_child] = -1;
					this->right_child[pos_of_the_child] = -1;
					this->sizeOfArray--;
					return true;
				}
				else if (this->right_child[current] == -1 && this->left_child[current] != -1)
				{
					//it only has a left child:teta(1)
					int pos_of_the_child = this->left_child[current];
					this->elems[current] = this->elems[this->left_child[current]];
					this->right_child[current] = this->right_child[pos_of_the_child];
					this->left_child[current] = this->left_child[pos_of_the_child];
					this->parents[current] = this->parents[pos_of_the_child];
					this->elems[pos_of_the_child] = Node{};
					this->parents[pos_of_the_child] = -1;
					this->left_child[pos_of_the_child] = -1;
					this->right_child[pos_of_the_child] = -1;
					this->sizeOfArray--;
					return true;
				}
				else 
				{
					//it has 2 children
					int pos_to_be_emptied = this->get_min_pos(this->right_child[current]);
					this->elems[current] = this->elems[pos_to_be_emptied];
					//this->right_child[current] = this->right_child[pos_to_be_emptied];
					if (this->right_child[pos_to_be_emptied] != -1)
					{
						int pos_of_the_child = this->right_child[pos_to_be_emptied];
						int curen = pos_to_be_emptied;
						this->elems[curen] = this->elems[pos_of_the_child];
						this->right_child[curen] = this->right_child[pos_of_the_child];
						this->left_child[curen] = this->left_child[pos_of_the_child];
						this->parents[curen] = this->parents[pos_of_the_child];
						this->elems[pos_of_the_child] = Node{};
						this->parents[pos_of_the_child] = -1;
						this->left_child[pos_of_the_child] = -1;
						this->right_child[pos_of_the_child] = -1;
						this->sizeOfArray--;
						return true;
					}
					else {
						this->elems[pos_to_be_emptied] = Node{}; //put an empty node there
						this->parents[pos_to_be_emptied] = -1;
						this->sizeOfArray--;
						return true;
					}
				}
				}
			}
			else
				//this search has O(n) complexity
				if (this->rel(this->elems[current].get_info(), e))
				{
					if (this->right_child[current] == -1)
						break;
					else {
						int pos = current;
						current = this->right_child[pos];
					}

				}
				else {
					if (this->left_child[current] == -1)
						break;
					else {
						int pos = current;
						current = this->left_child[pos];
					}
				}
		}
		}
	}

//Time complexity: BC=teta(1) if the elem is found quicly
//WC=teta(n) if the tree is degenerated, AC=teta(n),total=O(n),n=nr of nodes in the tree
//extra space complexity:teta(1)
bool SortedBag::search(TComp e) const
{
	if (this->nrOccurrences(e) == 0)
		return false;
	else
		return true;
}

//Time complexity: BC=teta(1) if the elem is found quicly
//WC=teta(n) if the tree is degenerated, AC=teta(n),total=O(n),n=nr of nodes in the tree
//extra space complexity:teta(1)
int SortedBag::nrOccurrences(TComp e) const
{
	if (this->isEmpty())
		return 0;
	//else we search for it
	//check if it is the first one
	if (this->elems[this->root].get_info() == e)
		return this->elems[this->root].get_freq();
	//we search for it
	int current = this->root;
	while (this->elems[current].nil != true)
	{
		if (this->elems[current].get_info() == e)
			return this->elems[current].get_freq();
		else
			if (this->rel(this->elems[current].get_info(), e))
			{
				if (this->right_child[current] == -1)
					break;
				else { int pos = current;
				current = this->right_child[pos]; }
				
			}
			else {
				if (this->left_child[current] == -1)
					break;
				else {
					int pos = current;
					current = this->left_child[pos];
				}
			}		
	}
	return 0;
}
//Time complexity:always teta(1),makes only a return
//extra space complexity:teta(1)

int SortedBag::size() const
{
	return this->sizeOfArray;
}

//Time complexity:always teta(1),makes only a return
//extra space complexity:teta(1)
SortedBagIterator SortedBag::iterator() const
{
	return SortedBagIterator(*this);
}
//Time complexity:always teta(1),makes only a verfication
//extra space complexity:teta(1)
bool SortedBag::isEmpty() const
{
	if (this->sizeOfArray == 0)
		return true;
	else
		return false;
}

//complexity: for traversal: teta(n) and extra space: O(n) and for the search O(n) and for the remove : O(n)
void SortedBag::get_intersection(SortedBag new_bag)
{
	int current = this->root;
	bool go_further = true;
	std::stack<std::pair<Node,int>> stack;
	//int empty_parent = -1;
	//bool right = true;
	while (this->elems[current].nil != true && go_further)
	{
		stack.push(std::make_pair(this->elems[current],current));
		if (this->left_child[current] == -1)
			go_further = false;
		else current = this->left_child[current];
	}
	while (not(stack.empty()))
	{
		Node t = stack.top().first;// we have the node
		int pos_to_be_erased = stack.top().second;
		bool found = false;

		//else we search for it
		//check if it is the first one
		if (new_bag.elems[new_bag.root].get_info() == t.info)
		{
			found = true;
		}

		//we search for it
		int current2 = new_bag.root;
		bool get_out = true;
		while (new_bag.elems[current2].nil != true && get_out)
		{
			if (new_bag.elems[current2].get_info() == t.info)
			{
				found = true;
				get_out = false;
			}

			else
				if (this->rel(new_bag.elems[current2].get_info(), t.info))
				{
					if (new_bag.right_child[current2] == -1)
					{
						get_out = false;
						break;
					
					}
						
					else {
						int pos = current2;
						current = new_bag.right_child[pos];
					}

				}
				else {
					if (new_bag.left_child[current2] == -1)
					{
						get_out = false;
						break;

					}
					else {
						int pos = current2;
						current = new_bag.left_child[pos];
					}
				}
		}
		if (found == false) //we have to remove it t.info from our bag
		{
			if (this->right_child[pos_to_be_erased] == -1 and this->left_child[pos_to_be_erased] == -1)
			{
				//it's a leaf:teta(1)
				this->elems[pos_to_be_erased] = Node{}; //put an empty node there
				this->parents[pos_to_be_erased] = -1;
				this->sizeOfArray--;
			}
			else if (this->right_child[pos_to_be_erased] != -1 && this->left_child[pos_to_be_erased] == -1)
			{
				//it only has a right child:teta(1)
				int pos_of_the_child = this->right_child[pos_to_be_erased];
				this->elems[pos_to_be_erased] = this->elems[this->right_child[pos_to_be_erased]];
				this->right_child[pos_to_be_erased] = this->right_child[pos_of_the_child];
				this->left_child[pos_to_be_erased] = this->left_child[pos_of_the_child];
				this->parents[pos_to_be_erased] = this->parents[pos_of_the_child];
				this->elems[pos_of_the_child] = Node{};
				this->parents[pos_of_the_child] = -1;
				this->left_child[pos_of_the_child] = -1;
				this->right_child[pos_of_the_child] = -1;
				this->sizeOfArray--;
			}
			else if (this->right_child[pos_to_be_erased] == -1 && this->left_child[pos_to_be_erased] != -1)
			{
				//it only has a left child:teta(1)
				int pos_of_the_child = this->left_child[pos_to_be_erased];
				this->elems[pos_to_be_erased] = this->elems[this->left_child[pos_to_be_erased]];
				this->right_child[pos_to_be_erased] = this->right_child[pos_of_the_child];
				this->left_child[pos_to_be_erased] = this->left_child[pos_of_the_child];
				this->parents[pos_to_be_erased] = this->parents[pos_of_the_child];
				this->elems[pos_of_the_child] = Node{};
				this->parents[pos_of_the_child] = -1;
				this->left_child[pos_of_the_child] = -1;
				this->right_child[pos_of_the_child] = -1;
				this->sizeOfArray--;
			}
			else
			{
				//it has 2 children
				int pos_to_be_emptied = this->get_min_pos(this->right_child[pos_to_be_erased]);
				this->elems[pos_to_be_erased] = this->elems[pos_to_be_emptied];
				//this->right_child[current] = this->right_child[pos_to_be_emptied];
				if (this->right_child[pos_to_be_emptied] != -1)
				{
					int pos_of_the_child = this->right_child[pos_to_be_emptied];
					int curen = pos_to_be_emptied;
					this->elems[curen] = this->elems[pos_of_the_child];
					this->right_child[curen] = this->right_child[pos_of_the_child];
					this->left_child[curen] = this->left_child[pos_of_the_child];
					this->parents[curen] = this->parents[pos_of_the_child];
					this->elems[pos_of_the_child] = Node{};
					this->parents[pos_of_the_child] = -1;
					this->left_child[pos_of_the_child] = -1;
					this->right_child[pos_of_the_child] = -1;
					this->sizeOfArray--;
				}
				else {
					this->elems[pos_to_be_emptied] = Node{}; //put an empty node there
					this->parents[pos_to_be_emptied] = -1;
					this->sizeOfArray--;
				}
			}
		}
		if (this->right_child[current] == -1)
			go_further = false;
		else current = this->right_child[current];
		bool go_further2 = true;
		while (this->elems[current].nil != true && go_further2)
		{
			stack.push(std::make_pair(this->elems[current],current));
			if (this->left_child[current] == -1)
				go_further2 = false;
			else current = this->left_child[current];
		}

	}
}


//destructor
//Time complexity:always teta(1),makes only 4 steps
//extra space complexity:teta(1)
SortedBag::~SortedBag()
{
	delete[] this->elems;
	delete[] this->left_child;
	delete[] this->right_child;
	delete[] this->parents;
}

//Time complexity:always teta(1) 
//extra space complexity:teta(1)
Node::Node(TComp info) :info{ info }, frequency{ 1 }, nil{true}
{
}
//Time complexity:always teta(1)
//extra space complexity:teta(1)
Node::Node()
{
	this->info = -900000;
	this->frequency = -1;
}

//destructor
//Time complexity:always teta(1)
//extra space complexity:teta(1)
Node::~Node()
{
}

//Time complexity:always teta(1),makes only a return
//extra space complexity:teta(1)
TComp Node::get_info()
{
	return this->info;
}

//Time complexity:always teta(1),makes only a return
//extra space complexity:teta(1)
int Node::get_freq()
{
	return this->frequency;
}

void Node::toStr()
{
	std::cout << this->info << "," << this->frequency<<"\n";
}

//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
void Node::set_freq(int i)
{
	this->frequency = i;
}
