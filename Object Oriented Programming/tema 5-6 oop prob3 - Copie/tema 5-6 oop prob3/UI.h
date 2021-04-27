#pragma once
#include "Controller.h"
class UI
{
private:
	Controller& ctlr;
public:
	UI(Controller& cont);
	void first_menu();
	void user_menu();
	void administrator_menu();
	void administrator_run();
	void user_run();
	int first_run();
	int user_shop_run(int size);
	~UI();
};

