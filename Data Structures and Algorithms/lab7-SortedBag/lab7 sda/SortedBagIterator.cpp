#include "SortedBagIterator.h"


//Time complexity: BC=teta(1) when the first if is called, the bag is empty
//WC=teta(len of tree) if the tree is degenerated and else is ok, AC=teta(n),total=O(n),n=nr of nodes in the tree
//extra space complexity:teta(1)
SortedBagIterator::SortedBagIterator(const SortedBag& bag) :bag{bag}
{
	int current = this->bag.root;
	if(this->bag.size()==0)
		this->current_elem = -90000; //teta(1)

	//else has complexity: teta(length of the tree) as it goes only in the left side of every node
	else {this->CurrentFreq = 0;
	while (this->bag.elems[current].nil != true)
	{
		while (this->CurrentFreq < this->bag.elems[current].get_freq())
		{
			this->stack.push(this->bag.elems[current].get_info());
			this->CurrentFreq++;
		}
		this->CurrentFreq = 0;
		if (this->bag.left_child[current] == -1)
			break;
		current = this->bag.left_child[current];
	}
	//teta(1)
	if (not(this->stack.empty()))
	{
		this->current_elem = this->stack.top();
	}
	else
	{
		this->current_elem = -90000;
	}}
	
}

//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
TComp SortedBagIterator::getCurrent()
{
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");
	return this->current_elem;
}

//Time complexity:always teta(1),makes only a verification
//extra space complexity:teta(1)
bool SortedBagIterator::valid()
{
	if (this->current_elem == -90000)
		return false;
	else
		return true;
}

//Time complexity: teta(n),n=nr of nodes in the tree
//extra space complexity:teta(1)
void SortedBagIterator::next()
{
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");
	TComp node = this->stack.top();
	this->stack.pop();
	this->CurrentFreq = 0;
	int pos = this->bag.get_pos(node);
	if (this->bag.right_child[pos]!=-1)
	{
		pos = this->bag.right_child[pos];
		while (this->bag.elems[pos].nil!=true && this->bag.left_child[pos]!=-1)
		{
			while (this->CurrentFreq < this->bag.elems[pos].get_freq())
			{
				this->stack.push(this->bag.elems[pos].get_info());
				this->CurrentFreq++;
			}
			this->CurrentFreq = 0;
			pos = this->bag.left_child[pos];
		}
	}
	if (not(this->stack.empty()))
	{
		this->current_elem = this->stack.top();
	}
	else { this->current_elem = -90000; }
}

//Time complexity: BC=teta(len of tree)
//WC=teta(len of tree) if the tree is degenerated and else is ok, AC=teta(n),total=O(n),n=nr of nodes in the tree
//extra space complexity:teta(1)
void SortedBagIterator::first()
{
	this->stack= std::stack<TComp>();
	int current = this->bag.root;
	this->CurrentFreq = 0;
	while (this->bag.elems[current].nil != true)
	{
		while (this->CurrentFreq < this->bag.elems[current].get_freq())
		{
			this->stack.push(this->bag.elems[current].get_info());
			this->CurrentFreq++;
		}
		this->CurrentFreq = 0;
		if (this->bag.left_child[current] == -1)
			break;
		current = this->bag.left_child[current];
	}
	if (not(this->stack.empty()))
	{
		this->current_elem = this->stack.top();
	}
	else
	{
		this->current_elem = -90000;
	}

}

