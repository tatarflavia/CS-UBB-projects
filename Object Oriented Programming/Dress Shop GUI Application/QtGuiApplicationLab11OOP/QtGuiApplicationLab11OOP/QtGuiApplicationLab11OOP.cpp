#include "QtGuiApplicationLab11OOP.h"

QtGuiApplicationLab11OOP::QtGuiApplicationLab11OOP(Controller ctrl, QWidget *parent)
	: QMainWindow(parent), ctrl{ctrl}
{
	ui.setupUi(this);
	QObject::connect(this->ui.AdministratorMode, &QPushButton::clicked, this, &QtGuiApplicationLab11OOP::admin);
	QObject::connect(this->ui.UserMode, &QPushButton::clicked, this, &QtGuiApplicationLab11OOP::dress_shop);
}

void QtGuiApplicationLab11OOP::admin()
{
	DressShop* shop = new DressShop{ ctrl };
	shop->show();
	this->hide();
}

void QtGuiApplicationLab11OOP::dress_shop()
{
	UserDressShop* user = new UserDressShop{ ctrl };
	user->show();
	this->hide();
}
