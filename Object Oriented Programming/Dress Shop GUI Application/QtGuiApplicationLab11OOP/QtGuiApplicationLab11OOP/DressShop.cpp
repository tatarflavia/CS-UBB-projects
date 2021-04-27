#include "DressShop.h"
#include "WeddingDress.h"
#include <QKeyEvent>
#include <QDebug>
#include <QKeySequence>

DressShop::DressShop(Controller & controller) :ctrl{controller}
{
	this->init();
	this->fill_repo_list();
	QObject::connect(this->sort, &QRadioButton::clicked, this, &DressShop::sort_repo_list);
	QObject::connect(this->shuffle, &QRadioButton::clicked, this, &DressShop::random_shuffle);
	QObject::connect(this->addDress, &QPushButton::clicked, this, &DressShop::add_dress);
	QObject::connect(this->removeDress, &QPushButton::clicked, this, &DressShop::remove_dress);
	QObject::connect(this->updateDress, &QPushButton::clicked, this, &DressShop::update_dress);
	QObject::connect(this->undo_button, &QPushButton::clicked, this, &DressShop::undo);
	QObject::connect(this->redo_button, &QPushButton::clicked, this, &DressShop::redo);
	//keyF11 = new QShortcut(QKeySequence(tr("Ctrl+O", "File|Open")),parent);   // Initialize the object
	//keyF11->setKey(Qt::Key_F11);    // Set the key code
	//// connect handler to keypress
	//connect(keyF11, SIGNAL(activated()), this, SLOT(slotShortcutF11()));
}


void DressShop::init()
{
	QVBoxLayout* big_layout = new QVBoxLayout{ this };
	this->repo = new QListWidget{  };
	big_layout->addWidget(this->repo);

	QVBoxLayout* under_list = new QVBoxLayout{};

	big_layout->addLayout(under_list);

	QFormLayout* dressdataLayout = new QFormLayout{};
	this->price = new QLineEdit{};
	dressdataLayout->addRow("Price", this->price);
	this->quantity = new QLineEdit{};
	dressdataLayout->addRow("Quantity", this->quantity);
	this->colour = new QLineEdit{};
	dressdataLayout->addRow("Colour", this->colour);
	this->size = new QLineEdit{};
	dressdataLayout->addRow("Size", this->size);
	this->photograph = new QLineEdit{};
	dressdataLayout->addRow("Photograph", this->photograph);

	under_list->addLayout(dressdataLayout);


	QHBoxLayout* buttons = new QHBoxLayout{};
	this->addDress = new QPushButton("Add");
	buttons->addWidget(this->addDress);
	this->removeDress = new QPushButton("Remove");
	buttons->addWidget(this->removeDress);
	this->updateDress = new QPushButton("Update");
	buttons->addWidget(this->updateDress);

	under_list->addLayout(buttons);

	QHBoxLayout* radio_buttons = new QHBoxLayout{};
	this->shuffle = new QRadioButton("Shuffle");
	radio_buttons->addWidget(this->shuffle);
	this->sort = new QRadioButton("Sort");
	radio_buttons->addWidget(this->sort);

	under_list->addLayout(radio_buttons);

	QHBoxLayout* undo_redo = new QHBoxLayout{};
	this->undo_button = new QPushButton("Undo");
	undo_redo->addWidget(this->undo_button);
	this->redo_button = new QPushButton("Redo");
	undo_redo->addWidget(this->redo_button);

	under_list->addLayout(undo_redo);

}

void DressShop::fill_repo_list()
{
	for (auto i : this->ctrl.get_repo().get_all())
	{
		this->repo->addItem(QString::fromStdString("The dress has the price:"+std::to_string(i.get_price())+" ,") + QString::fromStdString(" the colour: "+i.get_colour()+" ,") + QString::fromStdString(" the size: "+std::to_string(i.get_size())+" ,") + QString::fromStdString("the quantity: "+std::to_string(i.get_quantity())+", ") +QString::fromStdString("and the photo: "+i.get_photograph())+".");
	}
}

void DressShop::add_dress()
{
	std::string photo;
	int size = 0;
	std::string colour;
	float price = 0;
	int quantity = 0;

	QString ph = this->photograph->text();
	photo = ph.toStdString();
	QString siz = this->size->text();
	size = std::stoi(siz.toStdString());
	QString col = this->colour->text();
	colour = col.toStdString();
	QString pric = this->price->text();
	price = std::stoi(pric.toStdString());
	QString quant = this->quantity->text();
	quantity = std::stoi(quant.toStdString());

	this->ctrl.add(size, colour, price, quantity, photo);
	this->repo->clear();
	this->fill_repo_list();
}

void DressShop::remove_dress()
{
	std::string photo;
	QString ph = this->photograph->text();
	photo = ph.toStdString();
	this->ctrl.remove(photo);
	this->repo->clear();
	this->fill_repo_list();
}

void DressShop::update_dress()
{
	std::string photo;
	int size = 0;
	float price = 0;
	int quantity = 0;

	QString ph = this->photograph->text();
	photo = ph.toStdString();
	QString siz = this->size->text();
	size = std::stoi(siz.toStdString());
	QString pric = this->price->text();
	price = std::stoi(pric.toStdString());
	QString quant = this->quantity->text();
	quantity = std::stoi(quant.toStdString());
	this->ctrl.update(photo,size,price,quantity);
	this->repo->clear();
	this->fill_repo_list();
}

void DressShop::sort_repo_list()
{
	this->repo->clear();
	for (auto i : this->ctrl.sort_by_price())
	{
		this->repo->addItem(QString::fromStdString("The dress has the price:" + std::to_string(i.get_price()) + " ,") + QString::fromStdString(" the colour: " + i.get_colour() + " ,") + QString::fromStdString(" the size: " + std::to_string(i.get_size()) + " ,") + QString::fromStdString("the quantity: " + std::to_string(i.get_quantity()) + ", ") + QString::fromStdString("and the photo: " + i.get_photograph()) + ".");
	}
}

void DressShop::random_shuffle()
{
	this->repo->clear();
	for (auto i : this->ctrl.random_shuffle())
	{
		this->repo->addItem(QString::fromStdString("The dress has the price:" + std::to_string(i.get_price()) + " ,") + QString::fromStdString(" the colour: " + i.get_colour() + " ,") + QString::fromStdString(" the size: " + std::to_string(i.get_size()) + " ,") + QString::fromStdString("the quantity: " + std::to_string(i.get_quantity()) + ", ") + QString::fromStdString("and the photo: " + i.get_photograph()) + ".");
	}
}

void DressShop::undo()
{
	this->ctrl.undo();
	this->repo->clear();
	for (auto i : this->ctrl.get_repo().get_all())
	{
		this->repo->addItem(QString::fromStdString("The dress has the price:" + std::to_string(i.get_price()) + " ,") + QString::fromStdString(" the colour: " + i.get_colour() + " ,") + QString::fromStdString(" the size: " + std::to_string(i.get_size()) + " ,") + QString::fromStdString("the quantity: " + std::to_string(i.get_quantity()) + ", ") + QString::fromStdString("and the photo: " + i.get_photograph()) + ".");
	}
}

void DressShop::redo()
{
	this->ctrl.redo();
	this->repo->clear();
	for (auto i : this->ctrl.get_repo().get_all())
	{
		this->repo->addItem(QString::fromStdString("The dress has the price:" + std::to_string(i.get_price()) + " ,") + QString::fromStdString(" the colour: " + i.get_colour() + " ,") + QString::fromStdString(" the size: " + std::to_string(i.get_size()) + " ,") + QString::fromStdString("the quantity: " + std::to_string(i.get_quantity()) + ", ") + QString::fromStdString("and the photo: " + i.get_photograph()) + ".");
	}
}

void DressShop::keyPressEvent(QKeyEvent *event)
{
	QWidget::keyPressEvent(event);
	if (event->matches(QKeySequence::Undo))
	{
		this->undo();
	}
	else if (event->matches(QKeySequence::Redo))
	{
		this->redo();
	}	
	
}


DressShop::~DressShop()
{
}
