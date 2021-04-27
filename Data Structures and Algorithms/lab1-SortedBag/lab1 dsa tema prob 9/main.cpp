#include "SortedBag.h"
#include "SortedBagIterator.h"
#include "ShortTest.h"
#include "ExtendedTest.h"
#include <iostream>

using namespace std;


int main() {


	
	int a = 10;
	/*bool relation2(TComp r1, TComp r2) {
		return r1 <= r2;
	}
	SortedBag sb(relation2);
	for (int i = 0; i < 100; i++) {
		sb.add(i);
	}
	for (int i = 0; i < 100; i++) {
		sb.add(i);
	}
	sb.toStr();
	cout<<sb.toSet();
	sb.toStr();*/
	testAll();
	testAllExtended();
	cout << "END\n";
	cin >> a;
	return 0;

}
