#include "Task_manager.h"
#include <QtWidgets/QApplication>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	Repo repo{};
	Controller ctrl{ repo };
	Task_manager w{ctrl};
	w.show();
	return a.exec();
}
