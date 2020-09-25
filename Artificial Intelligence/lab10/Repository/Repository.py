from Logic.Description import *
from Logic.Rule import *

class Repository:
    def __init__(self):
        #first prepare the 3 descriptions for the systhem
        self.temperatureDescription = FuzzyDescription()
        self.humidityDescription = FuzzyDescription()
        self.timeDescription = FuzzyDescription()
        self.rules = []
        self.prepareRulesAndDescriptions()

    def trap_region(self, a, b, c, d):
        #returns a higher order function for a trapezoidal fuzzy region
        return lambda x: max(0, min((x - a) / (b - a), 1, (d - x) / (d - c)))

    def tri_region(self,a, b, c):
        #returns a higher order function for a triangular fuzzy region
        return self.trap_region(a, b, b, c)

    def inverse_line(self,a, b):
        #returns a inverse for a line
        return lambda val: val * (b - a) + a

    def inverse_tri(self,a, b, c):
        #returns a higher order inverse function for a fuzzy region
        return lambda val: (self.inverse_line(a, b)(val) + self.inverse_line(c, b)(val)) / 2

    def prepareRulesAndDescriptions(self):
        file1 = open('E:\\School\\inteligenta artificiala an 2 sem 2\\lab10\\tema10\\input.txt', 'r')
        Lines = file1.readlines()
        count=1
        for line in Lines:
            rowStrings = line.strip().split(";")
            if count <=5:
                self.temperatureDescription.add_region(eval(rowStrings[0]), eval(rowStrings[1]))
            elif count<=8:
                self.humidityDescription.add_region(eval(rowStrings[0]), eval(rowStrings[1]))
            elif count<=11:
                self.timeDescription.add_region(eval(rowStrings[0]), eval(rowStrings[1]),eval(rowStrings[2]))
            else:
                self.rules.append(FuzzyRule(eval(rowStrings[0]), eval(rowStrings[1])))
            count+=1


    def getTemperatureDesc(self):
        return self.temperatureDescription

    def getRules(self):
        return self.rules

    def getTimeDesc(self):
        return self.timeDescription

    def getHumidityDesc(self):
        return self.humidityDescription

    #def prepareRulesAndDescriptions(self):
        # self.temperatureDescription.add_region('very cold', self.trap_region(-1000, -30, -20, 5))
        # self.temperatureDescription.add_region('cold', self.tri_region(-5, 0, 10))
        # self.temperatureDescription.add_region('normal', self.trap_region(5, 10, 15, 20))
        # self.temperatureDescription.add_region('warm', self.tri_region(15, 20, 25))
        # self.temperatureDescription.add_region('hot', self.trap_region(25, 30, 35, 1000))
        #
        # self.humidityDescription.add_region('dry', self.tri_region(-1000, 0, 50))
        # self.humidityDescription.add_region('normal', self.tri_region(0, 50, 100))
        # self.humidityDescription.add_region('wet', self.tri_region(50, 100, 1000))
        #
        # self.timeDescription.add_region('short', self.tri_region(-1000, 0, 50), self.inverse_line(50, 0))
        # self.timeDescription.add_region('medium', self.tri_region(0, 50, 100), self.inverse_tri(0, 50, 100))
        # self.timeDescription.add_region('long', self.tri_region(50, 100, 1000), self.inverse_line(50, 100))

        #self.prepareRules()
        # self.rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'wet'}, {'time': 'short'}))
        # self.rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'wet'}, {'time': 'short'}))
        # self.rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'wet'}, {'time': 'short'}))
        # self.rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'wet'}, {'time': 'short'}))
        # self.rules.append(FuzzyRule({'temperature': 'hot', 'humidity': 'wet'}, {'time': 'medium'}))
        #
        # self.rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'normal'}, {'time': 'short'}))
        # self.rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'normal'}, {'time': 'medium'}))
        # self.rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'normal'}, {'time': 'medium'}))
        # self.rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'normal'}, {'time': 'medium'}))
        # self.rules.append(FuzzyRule({'temperature': 'hot', 'humidity': 'normal'}, {'time': 'long'}))
        #
        # self.rules.append(FuzzyRule({'temperature': 'very cold', 'humidity': 'dry'}, {'time': 'medium'}))
        # self.rules.append(FuzzyRule({'temperature': 'cold', 'humidity': 'dry'}, {'time': 'long'}))
        # self.rules.append(FuzzyRule({'temperature': 'normal', 'humidity': 'dry'}, {'time': 'long'}))
        # self.rules.append(FuzzyRule({'temperature': 'warm', 'humidity': 'dry'}, {'time': 'long'}))
        # self.rules.append(FuzzyRule({'temperature': 'hot', 'humidity': 'dry'}, {'time': 'long'}))


