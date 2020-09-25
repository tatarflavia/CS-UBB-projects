from Service.Controller import Controller
from dialog import *
import sys
from Console.Console import Console
from Console.ConsoleForResults import Console2

app=QtWidgets.QApplication(sys.argv)
MainWindow=QtWidgets.QMainWindow()
ctrl=Controller()
#create obj of app
ui=Console(MainWindow,ctrl)
#show the window and start the app
MainWindow.show()
app.exec()

#ctrl.doEA(3,100,1000,0.5,0.5);
#ctrl.validationTest(3,0.5,0.5)
#ctrl.doPSO(3,100,20,1,1,2.5,100)
#ctrl.validationTestPSO(3,10,1,1,2.5)