#include "ShortTest.h"
#include "ExtendedTest.h"
#include <iostream>

int main()
{
	testAll();
	testAllExtended();
	std::cout << "END";
	return 0;
}