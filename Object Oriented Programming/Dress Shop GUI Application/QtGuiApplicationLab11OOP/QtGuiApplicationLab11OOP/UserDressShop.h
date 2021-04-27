#pragma once

#include <QWidget>
#include "ui_UserDressShop.h"
#include "Controller.h"
#include "ShoppingBasketWindow.h"

class UserDressShop : public QWidget
{
	Q_OBJECT

public:
	UserDressShop(Controller ctrl,QWidget *parent = Q_NULLPTR);
	~UserDressShop();

private:
	Ui::UserDressShop ui;
	void fill_repo_list();
	void add_dress();
	void see_shopping_model();

	Controller ctrl;
};
