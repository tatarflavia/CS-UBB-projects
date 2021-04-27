#include <iostream>
#include "Bag.h"
#include "BagIterator.h"
#include "ExtendedTest.h"
#include "ShortTest.h"

int main()
{
	int a = 0;
	Bag b{};
	testAll();
	std::cout << "Short test passed!\n";
	//std::cout << b.h(29704, 72293);
	testAllExtended();
	std::cout << "Extended test passed!\n";
	std::cin >> a;
	return 0;

}