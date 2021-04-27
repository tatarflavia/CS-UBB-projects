#include <iostream>
#include "SortedBag.h"
#include "SortedBagIterator.h"
#include "ShortTest.h"
#include "ExtendedTest.h"

using namespace std;

bool rel(TComp r1, TComp r2) {
		return r1 <= r2;
	}
int main() {

	
	testAll();
	testAllExtended();
	cout << "END\n";
	SortedBag bag{ rel };
	bag.add(9);
	bag.add(2);
	bag.add(5);
	bag.add(5);
	bag.add(0);
	bag.add(0);
	bag.add(2);
	bag.toStr();
	cout << "The TComp with the smallest frequency is:" << bag.return_smallest_freq();
	int a = 10;
	cin >> a;
	return 0;

}
