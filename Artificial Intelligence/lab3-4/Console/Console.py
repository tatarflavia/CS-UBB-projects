from dialog import *
import sys
from Service.Controller import Controller
from Console.ConsoleForResults import Console2

class Console(Ui_MainWindow):
    def __init__(self,window,ctrl):
        self.ctrl=ctrl
        #self.console2=console2
        self.setupUi(window)
        self.eaAlgButton.clicked.connect(self.doEa)
        self.hillAlgButton.clicked.connect(self.doHillClimbing)
        self.validateTestEA.clicked.connect(self.validateTestEAFct)
        self.psoAlgButton.clicked.connect(self.doPSO)
        self.validateTestPSO.clicked.connect(self.validateTestPSOFct)

    def doEa(self):
        matrixDim=int(self.inputMatrixDimEA.text())
        mutation=float(self.inputMutationEA.text())
        crossover=float(self.inputCrossOverEA.text())
        trials=int(self.inputTrialsEA.text())
        populationSize=int(self.inputPopulationEA.text())
        res=self.ctrl.doEA(matrixDim,populationSize,trials,mutation,crossover)
        self.solutionShowWidget.clear()
        self.solutionShowWidget.addItem(str(res))

    def doHillClimbing(self):
        number=int(self.inputMatrixDimHill.text())
        trials = int(self.inputTrialsHill.text())
        res=self.ctrl.doHillAlg(number,trials)
        self.solutionShowWidget.clear()
        self.solutionShowWidget.addItem(str(res))

    def doPSO(self):
        n=int(self.inputMatrixDimPSO.text())
        noParticles=int(self.inputPopSizePSO.text())
        sizeOfNeighbourhood=int(self.inputSizeOfNeighbourhoodPSO.text())
        w=float(self.inputWPSO.text())
        c1=float(self.inputC1PSO.text())
        c2=float(self.inputC2PSO.text())
        nrOfTrials=int(self.inputTrialsPSO.text())
        res=self.ctrl.doPSO(n, noParticles, sizeOfNeighbourhood , w, c1, c2, nrOfTrials)
        print(str(res))
        self.solutionShowWidget.clear()
        self.solutionShowWidget.addItem(str(res))

    def validateTestEAFct(self):
        matrixDim = int(self.inputMatrixDimEA.text())
        mutation = float(self.inputMutationEA.text())
        crossover = float(self.inputCrossOverEA.text())
        self.ctrl.validationTest(matrixDim,mutation,crossover)

    def validateTestPSOFct(self):
        n = int(self.inputMatrixDimPSO.text())
        sizeOfNeighbourhood = int(self.inputSizeOfNeighbourhoodPSO.text())
        w = float(self.inputWPSO.text())
        c1 = float(self.inputC1PSO.text())
        c2 = float(self.inputC2PSO.text())
        self.ctrl.validationTestPSO(n,sizeOfNeighbourhood,w,c1,c2)






