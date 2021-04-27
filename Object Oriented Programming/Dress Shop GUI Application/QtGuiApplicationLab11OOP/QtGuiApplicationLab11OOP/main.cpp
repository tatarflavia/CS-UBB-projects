#include "QtGuiApplicationLab11OOP.h"
#include <QtWidgets/QApplication>
#include<iostream>
#include "WeddingDress.h"
#include "Repository.h"
#include "Controller.h"
#include "RepoForBasket.h"
#include "Validator.h"
#include "DressShop.h"
#include "UserDressShop.h"

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	Repository repo{ };
	WeddingDressValidator validate{};
	RepoForBasket repoBasket{};
	Controller ctlr{ repo,repoBasket,validate };


	

	QtGuiApplicationLab11OOP gui{ctlr};
	gui.show();
	return a.exec();
}
