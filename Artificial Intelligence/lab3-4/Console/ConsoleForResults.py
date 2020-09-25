from Console.Threading import MyThread
from Service.Controller import Controller
from show import *
from PyQt5.QtCore import (QCoreApplication, QObject, QRunnable, QThread,
                          QThreadPool, pyqtSignal)
import sys
class Console2(Ui_MainWindow):
    def __init__(self,window,ctrl):
        self.ctrl=ctrl
        self.setupUi(window)


    def doEa(self,matrixDim,populationSize,trials,mutation,crossover):
        app = QCoreApplication([])
        thread = MyThread()
        thread.finished.connect(app.exit)
        res = self.ctrl.doEA(matrixDim, populationSize, trials, mutation, crossover)
        self.solutionShowWidget.clear()
        self.solutionShowWidget.addItem(str(res))
        thread.start()
        #sys.exit(app.exec_())


    def doHillClimbing(self):
        number=int(self.inputMatrixDimHill.text())
        trials = int(self.inputTrialsHill.text())
        res=self.ctrl.doHillAlg(number,trials)
        #self.solutionShowWidget.clear()
        #self.solutionShowWidget.addItem(str(res))

    def validateTestEAFct(self):
        matrixDim = int(self.inputMatrixDimEA.text())
        mutation = float(self.inputMutationEA.text())
        crossover = float(self.inputCrossOverEA.text())
        self.ctrl.validationTest(matrixDim,mutation,crossover)




