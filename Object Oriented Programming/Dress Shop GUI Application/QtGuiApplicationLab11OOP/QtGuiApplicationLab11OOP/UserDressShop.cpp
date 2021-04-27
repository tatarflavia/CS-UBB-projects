#include "UserDressShop.h"

UserDressShop::UserDressShop(Controller ctrl,QWidget *parent)
	: QWidget(parent), ctrl{ctrl}
{
	ui.setupUi(this);
	this->fill_repo_list();
	QObject::connect(this->ui.BuyDress, &QPushButton::clicked, this, &UserDressShop::add_dress);
	QObject::connect(this->ui.shopping_basket_button, &QPushButton::clicked, this, &UserDressShop::see_shopping_model);
}

UserDressShop::~UserDressShop()
{
}

void UserDressShop::fill_repo_list()
{
	for (auto i : this->ctrl.get_repo().get_all())
	{
		this->ui.repoBasket->addItem(QString::fromStdString(i.get_colour() + " , ") + QString::fromStdString(i.get_photograph() + ",") + QString::fromStdString(std::to_string(i.get_size()) + ",") + QString::fromStdString(std::to_string(i.get_quantity()) + ",") + QString::fromStdString(std::to_string(i.get_price()) + "."));
	}
}

void UserDressShop::add_dress()
{
	QString string4;

	string4 = this->ui.repoBasket->currentItem()->text();
	std::string string2 = string4.toStdString();

	std::string photo;
	std::size_t found = string2.find_first_of(",");
	std::size_t found2 = string2.find_first_of(",", found + 1);

	photo.assign(string2, found + 2, found2 - found - 2);

	this->ctrl.addToBasket(photo);
	QString string3 = QString::fromStdString(photo);
	this->ui.shoppingBasket->addItem(string3);
	this->ui.repoBasket->clear();
	this->fill_repo_list();
	
}

void UserDressShop::see_shopping_model()
{
	BasketTableModel* new_model = new BasketTableModel{ this->ctrl.get_basket() };
	ShoppingBasketWindow* basket = new ShoppingBasketWindow{ new_model };
	basket->show();

}

