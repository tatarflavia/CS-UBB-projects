class FuzzySystem:
    #Receives variable descriptions and rules and outputs the defuzzified result of the system

    def __init__(self, rules):
        self.inputDescriptions = {}  #a dictionarry of form {descName:desc}
        self.outDescription = None #a single desc : time
        self.rules = rules

    def add_description(self, descName, description, out=False):
        #adds a new desc to the inputDesc dictionarry
        if out:
            if self.outDescription is None:
                self.outDescription = description
        else:
            self.inputDescriptions[descName] = description

    def computeSystem(self, inputs):#inputs is a dictionarry of form {desc:value for the desc}

        #sugeno model : algorithm

        # FUZIFICATION PROCESS


        fuzzyValues = self.makeComputationsForDescriptions(inputs)
        print("the descriptions after fuzification:")
        print(fuzzyValues)
        print()

        # apply all rules for the fuzzy values taken from the input transformed
        ruleValues = self.applyAllRules(fuzzyValues)
        #print(ruleValues)

        # only some changes to the fuzzy outputs by crossing the time word => [('short', 0.1), ('medium', 0.5)]
        fuzzyOutput= [(list(description[0].values())[0], description[1]) for description in ruleValues]
        print("time regions and the values after applying the rules:")
        print(fuzzyOutput)
        print()

        # AGGREGATE RESULTS PROCESS + defuzification

        #calculate the sugeno alg and get the result after dezzification
        weightedTotal = 0
        weightSum = 0
        for output in fuzzyOutput:
            weightSum += output[1]
            #deffuzificate result too
            weightedTotal += self.outDescription.defuzzify(*output) * output[1]

        #print(weightedTotal)
        return weightedTotal/weightSum

    def makeComputationsForDescriptions(self, inputs):
        # takes each value of the desc in the inputs dict and calculates fuzzy value for that desc
        #  => dict of {descName: {regName: value of membershipFuncion for that region,....}, descName:{}..}
        return {descName: self.inputDescriptions[descName].fuzzify(inputs[descName]) for descName, val in inputs.items()}

    def applyAllRules(self, fuzzyValues):
        # returns the fuzzy output of all rules into an array
        # => array of form [[{'time': 'short'}, 0.1], [{'time': 'medium'}, 0.5]]
        return [rule.applyRule(fuzzyValues) for rule in self.rules if rule.applyRule(fuzzyValues)[1] != 0]