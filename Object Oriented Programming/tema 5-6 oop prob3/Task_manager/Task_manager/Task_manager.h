#pragma once
#include "Controller.h"
#include <QtWidgets/QMainWindow>
#include "ui_Task_manager.h"

class Task_manager : public QMainWindow
{
	Q_OBJECT

private:
	Controller ctrl;
public:
	Task_manager(Controller ctrl,QWidget *parent = Q_NULLPTR);
	void populate_repo();
	void get_total_dration();
private:
	Ui::Task_managerClass ui;
};
