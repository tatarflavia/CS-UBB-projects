class FuzzyDescription:
    #description representation: holds regions and inverse functions for the sugeno Fuzzy model

    def __init__(self):
        self.regions = {} #dictionarry: holds regions of the description
        self.inverseFunction = {}

    def add_region(self, regionName, membershipFunction, inverse=None):
        #Adds a region with a given membership function to the regions dictionarry: dry,hot,...

        self.regions[regionName] = membershipFunction
        self.inverseFunction[regionName] = inverse

    def fuzzify(self, value):
        #returns a dictionarry of fuzzified values for each region
        #basically it calculates for each regionName the membership function value for the value received

        return {name: membershipFunction(value) for name, membershipFunction in self.regions.items()}

    def defuzzify(self, regionName, value):
        #returns a single value for deffuzification of the value in the region asked
        return self.inverseFunction[regionName](value)