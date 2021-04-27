#pragma once
#include "WeddingDress.h"
template <typename Elems>
class Comparator
{
public:
	virtual bool relation(Elems& el1, Elems& el2) = 0;
	virtual ~Comparator() {};
};

class CompareDescendingBySize :public Comparator<WeddingDress>
{
public:
	bool relation(WeddingDress& el1, WeddingDress& el2) override { return el1.get_size() > el2.get_size(); }

};

class CompareAscendingByPrice :public Comparator<WeddingDress>
{
public:
	bool relation(WeddingDress& el1, WeddingDress& el2) override { return el1.get_price() < el2.get_price(); }
};