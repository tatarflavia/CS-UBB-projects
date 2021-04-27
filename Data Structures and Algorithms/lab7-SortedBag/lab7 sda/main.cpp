#include"ExtendedTest.h"
#include"ShortTest.h"
#include"SortedBag.h"
#include"SortedBagIterator.h"
#include <iostream>


bool relationtr(TComp e1, TComp e2) {
	return e1 < e2;
}

int main()
{
	int a = 0;
	SortedBag bag1{ relationtr };
	bag1.add(1);
	bag1.add(2);
	bag1.add(3);
	bag1.add(4);
	bag1.add(5);
	SortedBag bag2{ relationtr };
	bag2.add(1);
	bag2.add(2);
	bag2.add(3);
	bag2.add(7);
	bag2.add(8);
	bag1.toStr();
	bag2.toStr();
	//bag1.get_intersection(bag2);
	bag1.toStr();
	testAll();
	std::cout << "short test passed!\n";
	testAllExtended();
	std::cout << "extended passed!\n";
	std::cin >> a;
	return 0;
}