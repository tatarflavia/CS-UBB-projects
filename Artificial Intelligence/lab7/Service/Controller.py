from Logic.GradientDescentAlgorithm import GradientDescentAlgorithm


class Controller(object):
    def __init__(self,repo):
        self.repo=repo

    def callMainForGradientDescentRegressionProblem(self):

        alg=GradientDescentAlgorithm(self.repo.getXValues(),self.repo.getYValues())
        return alg.main()
