#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_QtGuiApplicationLab11OOP.h"
#include "Controller.h"
#include "DressShop.h"
#include "UserDressShop.h"

class QtGuiApplicationLab11OOP : public QMainWindow
{
	Q_OBJECT

public:
	QtGuiApplicationLab11OOP(Controller ctrl,QWidget *parent = Q_NULLPTR);

private:
	Ui::QtGuiApplicationLab11OOPClass ui;
	Controller ctrl;
	void admin();
	void dress_shop();
};
