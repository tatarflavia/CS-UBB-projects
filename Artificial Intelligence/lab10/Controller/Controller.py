from Logic.System import FuzzySystem



class Controller:
    def __init__(self,repository):
        self.system = FuzzySystem(repository.getRules())
        self.system.add_description('temperature', repository.getTemperatureDesc())
        self.system.add_description('humidity', repository.getHumidityDesc())
        self.system.add_description('time', repository.getTimeDesc(), out=True)

    def startComputation(self, inputs):
        return self.system.computeSystem(inputs)