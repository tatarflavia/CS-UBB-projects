class FuzzyRule(object):
    # conjunctive fuzzy rule; temperature and humidity => time

    def __init__(self, inputs, out):
        self.inputDescriptions = inputs #dictionary of descriptionName and region chosen
        self.outputDescription = out #expected time region for the inputs given


    def applyRule(self, fuzzyInputs):
        # transforms fuzzy inputs into fuzzy output by applying this rule
        # Receives a dictionary of all the input values {'humidity': {'dry': 0, 'normal': 0.9, 'wet': 0.1}, 'temperature': {..}} and returns the conjunction of their values,getting the min for each description
        # and = min ; or = max
        # returns a fuzzy value : of form [{time: '..'},Minvalue]

        return [self.outputDescription,
                min([fuzzyInputs[descName][regionName] for descName, regionName in self.inputDescriptions.items()])
                ]

