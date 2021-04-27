#include "Task_manager.h"

Task_manager::Task_manager(Controller ctrl,QWidget *parent)
	: ctrl{ctrl},QMainWindow(parent)
{
	ui.setupUi(this);
	this->populate_repo();
	QObject::connect(this->ui.duration_button, &QPushButton::clicked, this, &Task_manager::get_total_dration);
}

void Task_manager::populate_repo()
{
	QFont font;
	font.setBold(true);
	std::vector<Task> aux = this->ctrl.get_sort();
	for (auto i : aux)
	{
		this->ui.repo_list->addItem(QString::fromStdString(i.get_desc()) +QString::fromStdString(",")+ QString::fromStdString(std::to_string(i.get_priority())));
		
		
	}
}

void Task_manager::get_total_dration()
{
	QString str = this->ui.priority_lineEdit->text();
	int priority = std::stoi(str.toStdString());
	int total_duration = this->ctrl.get_total_duration(priority);
	this->ui.total_duration_list->clear();
	this->ui.total_duration_list->addItem(QString::fromStdString(std::to_string(total_duration)));
}
