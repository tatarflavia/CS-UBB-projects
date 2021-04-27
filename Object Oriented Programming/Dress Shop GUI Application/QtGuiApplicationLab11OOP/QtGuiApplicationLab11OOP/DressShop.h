#pragma once
#include <qwidget.h>
#include "Controller.h"
#include <qlistwidget.h>
#include "qlineedit.h"
#include "qpushbutton.h"
#include <QHBoxLayout>
#include <QFormLayout>
#include <QRadioButton>

class DressShop :
	public QWidget
{
private:
	Controller & ctrl;
	QListWidget* repo;
	QLineEdit* size;
	QLineEdit* colour;
	QLineEdit* price;
	QLineEdit* quantity;
	QLineEdit* photograph;
	QPushButton* addDress;
	QPushButton* removeDress;
	QPushButton* updateDress;
	QRadioButton* shuffle;
	QRadioButton* sort;
	QPushButton* undo_button;
	QPushButton* redo_button;
	QShortcut* keyF11;

private:
	void init();
	void fill_repo_list();
	void add_dress();
	void remove_dress();
	void update_dress();
	void sort_repo_list();
	void random_shuffle();
	void undo();
	void redo();
protected:
	void keyPressEvent(QKeyEvent *event) Q_DECL_OVERRIDE;
public:
	DressShop(Controller & controller);

	~DressShop();
};

