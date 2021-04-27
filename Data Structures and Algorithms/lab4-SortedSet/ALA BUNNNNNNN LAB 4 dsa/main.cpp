#include "SortedSet.h"
#include "SortedSetIterator.h"
#include "ShortTest.h"
#include "ExtendedTest.h"
#include <iostream>

using namespace std;
bool rel2(TComp e1, TComp e2) {
	if (e1 <= e2) {
		return true;
	}
	else {
		return false;
	}
}

int main() {



	int a = 10;


	SortedSet s1(rel2);
	s1.add(5);
	s1.add(1);
	s1.add(6);
	s1.add(1);
	s1.add(10);
	std::cout<<"The range is: "<<s1.getRange()<<"\n";

	
	testAll();
	testAllExtended();
	cout << "END\n";
	cin >> a;
	return 0;

}
